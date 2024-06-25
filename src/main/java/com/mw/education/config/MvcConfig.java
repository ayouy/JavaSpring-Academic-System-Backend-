package com.mw.education.config;

import com.mw.education.component.TermOperationInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Autowired
    private TermOperationInterceptor termOperationInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
       // registry.addInterceptor(termOperationInterceptor).addPathPatterns("/terms");
        registry.addInterceptor(new LoginInterceptor())
                .excludePathPatterns("/login", "/logout", "/login-info")
                .addPathPatterns("/**");
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addFormatter(new DateFormatter("hh:mm:ss"));
    }

    public static class LoginInterceptor implements HandlerInterceptor {
        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
            // 在LoginController中，登录成功则设置以下session attribute
            if(request.getSession().getAttribute("user-code") != null
                    && request.getSession().getAttribute("user-type") != null) {
                return true;
            }
            else {
                // 未登录或者session过期等，返回与AjaxResult一致格式的Json
                response.setContentType("application/json");
                response.getWriter().print(
                        "{"
                            + "\"code\" : 401, "
                            + "\"msg\" :  \"not login\" "
                        + "}"
                );
                return false;
            }
        }
    }
}
