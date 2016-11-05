package com.epam.training2016.aviacompany.services;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.epam.training2016.aviacompany.services.utils.IdNullException;

public class SpringScopeTest {

    public static void main(String[] args) throws IdNullException {
        ClassPathXmlApplicationContext springContext = new ClassPathXmlApplicationContext("service-context.xml");

        FlightService flightService = springContext.getBean(FlightService.class);
        flightService.getById(1L);
        System.out.println(springContext.getBean(FlightService.class).hashCode());
//        System.out.println(springContext.getBean(Airport.class).hashCode());
    }
}
