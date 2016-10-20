package com.epam.training2016.aviacompany.services;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.epam.training2016.aviacompany.datamodel.Airport;

public class SpringScopeTest {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext springContext = new ClassPathXmlApplicationContext("service-context.xml");

        System.out.println(springContext.getBean(Airport.class).hashCode());
        System.out.println(springContext.getBean(Airport.class).hashCode());
    }
}
