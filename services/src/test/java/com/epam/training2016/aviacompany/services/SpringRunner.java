package com.epam.training2016.aviacompany.services;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.epam.training2016.aviacompany.services.impl.AirportServiceImpl;

public class SpringRunner {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext springContext = new ClassPathXmlApplicationContext("service-context-test.xml");

        String[] beanDefinitionNames = springContext.getBeanDefinitionNames();
        for (String beanName : beanDefinitionNames) {
            System.out.println(beanName);
        }

        AirportServiceImpl airportServiceBean = springContext.getBean(AirportServiceImpl.class);
        System.out.println("AirportService exists:" + (airportServiceBean != null ? true : false));

        System.out.println("AirportService.dao exists:" + (airportServiceBean.isDaoExist() ? true : false));

    }
}
