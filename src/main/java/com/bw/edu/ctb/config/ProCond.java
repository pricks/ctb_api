package com.bw.edu.ctb.config;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

/** protocol condition */
public class ProCond implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata annotatedTypeMetadata) {
        ConfigurableBeanFactory beanFactory = context.getBeanFactory();
        //2、获取类加载器
        ClassLoader loader = context.getClassLoader();
        //3、获取当前环境信息
        Environment environment=context.getEnvironment();
        //4、获取bean定义的注册类
        BeanDefinitionRegistry registry = context.getRegistry();
        String property = environment.getProperty("protocol");
        if("https".equalsIgnoreCase(property)) {
            return true;
        }
        return false;
    }
}
