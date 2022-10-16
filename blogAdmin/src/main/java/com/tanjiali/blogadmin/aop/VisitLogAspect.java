package com.tanjiali.blogadmin.aop;
import java.util.Date;

import cn.hutool.json.JSONObject;
import com.tanjiali.blogadmin.service.log.VisitLogService;
import com.tanjiali.blogpublicapi.annotation.VisitLog;
import com.tanjiali.blogpublicapi.constant.AdminConstant;
import com.tanjiali.blogpublicapi.util.IpUtil;
import com.tanjiali.blogpublicapi.util.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import nl.bitwalker.useragentutils.UserAgent;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * @ClassName 访问日志AOP
 * @Description TODO
 * @Author Tanjiali
 * @Date 2022/10/16 16:56
 * @Version 1.0
 **/
@Aspect
@Component
@Slf4j
public class VisitLogAspect {
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private VisitLogService visitLogService;

    ThreadLocal<Long> time = new ThreadLocal<>();


    /**
     * 设置操作日志切入点 记录操作日志 在注解的位置切入代码
     */
    @Pointcut("@annotation(visitLog)")
    public void visitLogPoinCut(VisitLog visitLog) {
    }

    @Around(value = "visitLogPoinCut(visitLog)")
    public Object saveOperLog(ProceedingJoinPoint joinPoint, VisitLog visitLog) throws Throwable {
        time.set(System.currentTimeMillis());
        Object result = joinPoint.proceed();
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);
        log.info("=========================================================================================");
        //todo 获取用户相关信息
        //操作者用户名
        String header = request.getHeader(AdminConstant.REQUESTHEADER.getValue());
        header = header.substring(header.lastIndexOf(":") + 1);
        String username = jwtTokenUtil.getUserNameFromToken(header);
        log.info(username);
        //请求接口
        String uri = request.getRequestURI();
        log.info(uri);
        //请求方式
        String method = request.getMethod();
        log.info(method);
        //请求参数
        HashMap<String, Object> params = new HashMap<>();
        Object[] values = joinPoint.getArgs();
        String[] names = ((MethodSignature) joinPoint.getSignature()).getParameterNames();
        for (int i = 0; i <values.length; i++) {
            params.put(names[i],values[i]);
        }
        JSONObject param = new JSONObject(params);
        log.info(param.toString());
        //操作描述
        String description = visitLog.value();
        log.info(description);
        //ip
        String ip = IpUtil.getIpAddr(request);
        //ip来源
        boolean internalIp = IpUtil.internalIp(ip);
        String ipSource;
        if (internalIp) {
            ipSource = "内外IP";
        }else {
            ipSource = "外网IP";
        }
        log.info(ipSource);
        //用户代理信息
        String userAgentInfo = request.getHeader(AdminConstant.USERAGENT.getValue());
        log.info(userAgentInfo);
        // os操作系统
        UserAgent userAgent = UserAgent.parseUserAgentString(userAgentInfo);
        String os = userAgent.getOperatingSystem().getName();
        // browser浏览器
        String browser = userAgent.getBrowser().getName();
        //请求耗时（毫秒）
        int times = (int) (System.currentTimeMillis() - time.get());
        log.info("用时："+times);
        com.tanjiali.blogadmin.pojo.log.VisitLog visitLogObject = new com.tanjiali.blogadmin.pojo.log.VisitLog();
        visitLogObject.setUuid("");
        visitLogObject.setUri(uri);
        visitLogObject.setMethod(method);
        visitLogObject.setParam(param.toString());
        visitLogObject.setBehavior("");
        visitLogObject.setContent("");
        visitLogObject.setRemark("");
        visitLogObject.setIp(ip);
        visitLogObject.setIpSource(ipSource);
        visitLogObject.setOs(os);
        visitLogObject.setBrowser(browser);
        visitLogObject.setTimes(times);
        visitLogObject.setUserAgent(userAgentInfo);
        visitLogService.saveVisitLog(visitLogObject);
        return result;
    }
}
