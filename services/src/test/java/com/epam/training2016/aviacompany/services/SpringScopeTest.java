package com.epam.training2016.aviacompany.services;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringScopeTest {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext springContext = new ClassPathXmlApplicationContext("service-context-test.xml");

        FlightService flightService = springContext.getBean(FlightService.class);
        flightService.getById(1L);
        System.out.println(springContext.getBean(FlightService.class).hashCode());
//        System.out.println(springContext.getBean(Airport.class).hashCode());
    }
}
