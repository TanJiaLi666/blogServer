package com.tanjiali.blogadmin.aop;
import java.util.Date;

import cn.hutool.json.JSONObject;
import com.tanjiali.blogadmin.pojo.log.OperationLog;
import com.tanjiali.blogadmin.service.log.OperationLogService;
import com.tanjiali.blogpublicapi.annotation.OperaLog;
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
 * @ClassName 操作日志切面
 * @Description TODO
 * @Author Tanjiali
 * @Date 2022/10/14 18:14
 * @Version 1.0
 **/
@Aspect
@Component
@Slf4j
public class OperaLogAspect {
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private OperationLogService operationLogService;

    ThreadLocal<Long> time = new ThreadLocal<>();


    /**
     * 设置操作日志切入点 记录操作日志 在注解的位置切入代码
     */
    @Pointcut("@annotation(operaLog)")
    public void operLogPoinCut(OperaLog operaLog) {
    }

    @Around(value = "operLogPoinCut(operaLog)")
    public Object saveOperLog(ProceedingJoinPoint joinPoint, OperaLog operaLog) throws Throwable {
        /**
         *    getRequestURL方法返回客户端发出请求时的完整URL。
         * 　　getRequestURI方法返回请求行中的资源名部分。
         * 　　getQueryString 方法返回请求行中的参数部分。
         * 　　getPathInfo方法返回请求URL中的额外路径信息。额外路径信息是请求URL中的位于Servlet的路径之后和查询参数之前的内容，它以“/”开头。
         * 　　getRemoteAddr方法返回发出请求的客户机的IP地址。
         * 　　getRemoteHost方法返回发出请求的客户机的完整主机名。
         * 　　getRemotePort方法返回客户机所使用的网络端口号。
         * 　　getLocalAddr方法返回WEB服务器的IP地址。
         * 　　getLocalName方法返回WEB服务器的主机名。
         */
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
        String description = operaLog.value();
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
        OperationLog operationLog = new OperationLog();
        operationLog.setUsername(username);
        operationLog.setUri(uri);
        operationLog.setMethod(method);
        operationLog.setParam(param.toString());
        operationLog.setDescription(description);
        operationLog.setIp(ip);
        operationLog.setIpSource(ipSource);
        operationLog.setOs(os);
        operationLog.setBrowser(browser);
        operationLog.setTimes(times);
        operationLog.setUserAgent(userAgentInfo);
        operationLogService.saveOperationLog(operationLog);
        return result;
    }
}
