package org.activiti.app.ui;

import org.activiti.app.conf.ApplicationConfiguration;
import org.activiti.app.servlet.ApiDispatcherServletConfiguration;
import org.activiti.app.servlet.AppDispatcherServletConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;


import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * @PROJECT_NAME: activiti-root
 * @PACKAGE_NAME: org.activiti.app.ui
 * @Classname:    ActivitiUiApplication
 * @Date:         2019 08 2019/8/9 14:49
 * @Created by:   13662
 * @Description:  UI启动类
 * @author        李阳
 */
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class,
        HibernateJpaAutoConfiguration.class,
        org.activiti.spring.boot.SecurityAutoConfiguration.class
})
@Import({ApplicationConfiguration.class})
public class ActivitiUiApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(ActivitiUiApplication.class,args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(ActivitiUiApplication.class);
    }
    @Bean
    public ServletRegistrationBean apiDispatcher(){
        DispatcherServlet api = new DispatcherServlet();
        api.setContextClass(AnnotationConfigWebApplicationContext.class);
        api.setContextConfigLocation(ApiDispatcherServletConfiguration.class.getName());
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean();
        servletRegistrationBean.setServlet(api);
        servletRegistrationBean.addUrlMappings("/api/*");
        servletRegistrationBean.setLoadOnStartup(1);
        servletRegistrationBean.setAsyncSupported(true);
        servletRegistrationBean.setName("api");
        return servletRegistrationBean;
    }
    @Bean
    public ServletRegistrationBean appDispatcher(){
        DispatcherServlet api = new DispatcherServlet();
        api.setContextClass(AnnotationConfigWebApplicationContext.class);
        api.setContextConfigLocation(AppDispatcherServletConfiguration.class.getName());
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean();
        servletRegistrationBean.setServlet(api);
        servletRegistrationBean.addUrlMappings("/app/*");
        servletRegistrationBean.setLoadOnStartup(1);
        servletRegistrationBean.setAsyncSupported(true);
        servletRegistrationBean.setName("app");
        return servletRegistrationBean;
    }
}
