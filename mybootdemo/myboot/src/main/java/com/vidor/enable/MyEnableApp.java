package com.vidor.enable;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;


/**
 * EnableConfigurationProperties 用来启用一个特性，这个特性就是把配置文件的属性注入到bean里面
 * EnableAsync 启用异步，一般与@Async一起使用
 * Improt 用来导入一个或多个实体类或配置类到spring容器
 * ImprotSelect--selectImports方法的返回值，必须时一个class(全称)
 * ImportBeanDefinitionRegistrar--自己把bean注入容器
 */

@EnableAutoConfiguration
@ComponentScan
@EnableLog("my logger")
public class MyEnableApp {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(MyEnableApp.class,args);
        System.out.println(context.getBean(TomcatProperty.class).toString());
        context.close();
    }
}
