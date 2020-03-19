package ek.zhou.springboot.config;

import ek.zhou.springboot.component.MyLocaleResolver;
import ek.zhou.springboot.interceptor.LoginHandlerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyWebMVCConfigurer  {

    //使用WebMvcConfigurer代替以前的web.xml文件
    @Bean
   public WebMvcConfigurer webMvcConfigurer(){
        WebMvcConfigurer webMvcConfigurer = new WebMvcConfigurer() {
            //添加视图控制器
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("main/login");
                registry.addViewController("/index.html").setViewName("main/login");
                registry.addViewController("/main.html").setViewName("main/index");
            }

            //添加拦截器
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(new LoginHandlerInterceptor())
                        //设置拦截路径
                        .addPathPatterns("/**")
                        //设置不拦截的路径,Springboot 2.0中要排除静态资源路径,因访问的时候不会加/static,所以配置如下
                        .excludePathPatterns("/","/index.html","/login","/css/**","/js/**","/img/**");
            }
        };
        return webMvcConfigurer;
    }

    //配置自定义的区域信息解析器到容器中
    @Bean
    public LocaleResolver localeResolver(){
        return new MyLocaleResolver();
    }
}
