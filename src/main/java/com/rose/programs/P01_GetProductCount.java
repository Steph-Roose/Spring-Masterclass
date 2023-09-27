package com.rose.programs;

import com.rose.config.AppConfig1;
import com.rose.dao.ProductDao;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class P01_GetProductCount {

    public static void main(String[] args) {
        // dependency
        ProductDao dao;

        // a variable representing the spring container
        AnnotationConfigApplicationContext ctx;

        // object of spring container
        ctx = new AnnotationConfigApplicationContext(AppConfig1.class);

        dao = ctx.getBean("jdbcDao", ProductDao.class);
        System.out.println("dao is an instance of " + dao.getClass().getName());
        System.out.println("There are " + dao.count() + " products.");

        ctx.close();
    }
}
