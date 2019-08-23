package com.yungoal.build;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;


@ComponentScan({"com.yungoal"})
@SpringBootApplication
@PropertySource({"classpath:config/resource.properties"})
public class ApplicationServer {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(ApplicationServer.class);
        application.setBannerMode(Banner.Mode.OFF);//关闭启动时的Banner展示
        application.run(args);

        //等价用法
        //SpringApplication.run(ApplicationServer.class, args);
    }
}