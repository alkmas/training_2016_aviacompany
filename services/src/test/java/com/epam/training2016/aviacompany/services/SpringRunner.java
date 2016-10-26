package com.epam.training2016.aviacompany.services;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringRunner {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext springContext = new ClassPathXmlApplicationContext("service-context.xml");

        String[] beanDefinitionNames = springContext.getBeanDefinitionNames();
        for (String beanName : beanDefinitionNames) {
            System.out.println(beanName);
        }

        AirportService airportServiceBean = springContext.getBean(AirportService.class);
        System.out.println("AirportService exists:" + (airportServiceBean != null ? true : false));

        System.out.println("AirportService.dao exists:" + (airportServiceBean.isDaoExist() ? true : false));

    }
}
