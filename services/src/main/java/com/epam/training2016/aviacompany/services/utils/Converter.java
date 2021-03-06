package com.epam.training2016.aviacompany.services.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.epam.training2016.aviacompany.datamodel.Flight2Team;
import com.epam.training2016.aviacompany.services.Flight2TeamService;
import com.thoughtworks.xstream.XStream;

public class Converter<T> {

    private XStream xstream;
    private File file;

	
    Converter(Class<?> clazz) throws IOException {
    	String shortNameClass = clazz.getSimpleName();
    	xstream = new XStream();
        
    	xstream.alias(shortNameClass, clazz);

    	file = new File("D://!XML-BASE//" + shortNameClass + ".xml");
    	if (!file.exists()) {
    		file.createNewFile();
    		xstream.toXML(new ArrayList<>(), new FileOutputStream(
    			file));
        }
    }

    
	@SuppressWarnings({ "unchecked", "unused" })
    private List<T> readCollection() {
        return (List<T>) xstream.fromXML(file);
    }

    
    protected void writeCollection(List<T> newList) {
        try {
            xstream.toXML(newList, new FileOutputStream(file));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    
	
	
	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		Converter<Flight2Team> conv = new Converter<>(Flight2Team.class);
		ClassPathXmlApplicationContext springContext = new ClassPathXmlApplicationContext("service-context-test.xml");
		Flight2TeamService empService = (Flight2TeamService) springContext.getBean("flight2TeamServiceImpl");
		
		conv.writeCollection(empService.getAll());

	}

}
