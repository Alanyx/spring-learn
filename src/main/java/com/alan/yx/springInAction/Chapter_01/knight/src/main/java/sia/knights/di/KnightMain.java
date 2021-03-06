package com.alan.yx.springInAction.Chapter_01.knight.src.main.java.sia.knights.di;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Spring 通过 ApplicationContext 装配 bean 的定义并组装起来
 */
public class KnightMain {

    public static void main(String[] args) throws Exception {
        // 加载 spring 上下文
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("META-INF/INF/spring/knight.xml");
        // 获取 knight bean
        Knight knight = context.getBean(Knight.class);
        // 使用 knight bean
        knight.embarkOnQuest();
        context.close();
    }

}
