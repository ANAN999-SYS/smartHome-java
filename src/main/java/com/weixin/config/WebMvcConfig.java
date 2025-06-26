package com.weixin.config;

import com.weixin.commons.UserLoginInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowedMethods("GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS")
                .allowCredentials(true)
                .maxAge(3600)
                .allowedHeaders("*");
    }
    /**
     * 注册自定义拦截器
     *
     * @param registry ·
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new UserLoginInterceptor())
                .addPathPatterns("/**") // 拦截地址
//                .excludePathPatterns("/weixinLogin/**","/adminLogin/Login","/upload/static/**","/Devices/testZC","/Devices/testYC");// 开放登录路径
                .excludePathPatterns("/**");
    }
    @Value("${upload.ImgPath}")
    private String imagePath;//上传地址

    @Value("${upload.reflexFolder}")
    private String reflexFolder;//重定向地址

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //addResourceHandler()配置需要映射的文件夹
        //addResourceLocations()配置文件夹在系统中的路径，使用绝对路径，格式为“file:你的路径”
        //registry.addResourceHandler(logoRealFolderPath + "**").addResourceLocations("file:C:\\zy\\upload\\");
        registry.addResourceHandler(reflexFolder + "**").addResourceLocations("file:" + imagePath);
    }
}

