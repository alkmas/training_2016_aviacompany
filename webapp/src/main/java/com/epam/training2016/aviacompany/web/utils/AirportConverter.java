package com.epam.training2016.aviacompany.web.utils;

import java.beans.PropertyDescriptor;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.core.convert.converter.Converter;

import com.epam.training2016.aviacompany.datamodel.Airport;
import com.epam.training2016.aviacompany.web.model.AirportModel;

public class AirportConverter implements Converter<Airport, AirportModel>{

	@Override
	public AirportModel convert(Airport airport) {
		AirportModel airModel = new AirportModel();
		BeanWrapper beanWrapperT = new BeanWrapperImpl(airport);
		PropertyDescriptor[] propDescriptorsT = beanWrapperT.getPropertyDescriptors();
		BeanWrapper beanWrapperS = new BeanWrapperImpl(airModel);
		PropertyDescriptor[] propDescriptorsS = beanWrapperS.getPropertyDescriptors();
	
		for (PropertyDescriptor propT: propDescriptorsT) {
			if (!propT.getName().equals("class")) continue;
			for(PropertyDescriptor propS: propDescriptorsS) {
				
					propS.setValue(propT.getName(), propT.getValue(propT.getName()));
				}
			}
			
					
		}
		return airModel;
	}

	public static void main(String[] args) {
		AirportModel model = new AirportConverter().convert(new Airport());
		System.out.println(model);

	}

}
