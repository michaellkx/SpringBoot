package main.order.web.interception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//此类用来把重写的拦截器加入Spring管理
@SuppressWarnings("all")
@Configuration
public class register implements WebMvcConfigurer {
    @Autowired
    private DefInterceptorImpl defInterceptor;

    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(defInterceptor);
    }

}
