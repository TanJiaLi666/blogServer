package com.tanjiali.blogadmin.interceptor;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tanjiali.blogpublicapi.annotation.LoginCheck;
import com.tanjiali.blogpublicapi.api.PublicResult;
import com.tanjiali.blogpublicapi.constant.AdminConstant;
import com.tanjiali.blogpublicapi.util.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @ClassName 管理端——登录状态拦截器
 * @Description TODO
 * @Author Tanjiali
 * @Date 2022/10/16 20:19
 * @Version 1.0
 **/
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {
    private final static JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            //必须强转为HandlerMethod
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            //判断类上是否有打该注解
            boolean class_isLoginCheck = handlerMethod.getBeanType().isAnnotationPresent(LoginCheck.class);
            //判断方法上是否有打该注解
            boolean method_isLoginCheck = handlerMethod.getMethod().isAnnotationPresent(LoginCheck.class);
            if (!class_isLoginCheck  && !method_isLoginCheck ) {
                return true;
            }
            //判断用户是否登录
            String jwt = request.getHeader(AdminConstant.REQUESTHEADER.getValue());
            if (StrUtil.isEmpty(jwt)) {
                response.setContentType("application/json;charset=utf-8");
                PrintWriter out = response.getWriter();
                ObjectMapper objectMapper = new ObjectMapper();
                PublicResult result = PublicResult.failed(400, "缺少请求参数或请求失效",false);
                out.write(objectMapper.writeValueAsString(result));
                out.flush();
                out.close();
                return false;
            }
            jwt = jwt.substring(jwt.indexOf(":")+1);
            String username = jwtTokenUtil.getUserNameFromToken(jwt);
            if (!StrUtil.isEmpty(username)) {
                return true;
            }else{
                response.setContentType("application/json;charset=utf-8");
                PrintWriter out = response.getWriter();
                PublicResult result = null;
                LoginCheck annotation = handlerMethod.getMethodAnnotation(LoginCheck.class);
                if (annotation != null) {
                    result = PublicResult.failed(403, annotation.value(),false);
                }
                LoginCheck clazzAnnotation = handlerMethod.getBeanType().getAnnotation(LoginCheck.class);
                if (clazzAnnotation != null) {
                    result = PublicResult.failed(403, clazzAnnotation.value(),false);
                }
                ObjectMapper objectMapper = new ObjectMapper();
                out.write(objectMapper.writeValueAsString(result));
                out.flush();
                out.close();
                return false;
            }
        }
       /* //获取类上的注解
        LoginCheck clazzAnnotation = handlerMethod.getBeanType().getAnnotation(LoginCheck.class);
        //获取方法上的注解 方式1
        LoginCheck methodAnnotation_1 = handlerMethod.getMethodAnnotation(LoginCheck.class);
        //获取方法上的注解 方式2
        LoginCheck methodAnnotation_2 = handlerMethod.getMethod().getAnnotation(LoginCheck.class);*/
        return true;
    }
}
