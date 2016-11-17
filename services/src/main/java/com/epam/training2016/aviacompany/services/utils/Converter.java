package com.epam.training2016.aviacompany.services.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.epam.training2016.aviacompany.datamodel.Employee;
import com.thoughtworks.xstream.XStream;

public class Converter<T> {

    private XStream xstream;
    private File file;

	
    private void intialize() throws IOException {
        Class clazz = Employee.class;
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

    
	@SuppressWarnings("unchecked")
    private List<Employee> readCollection() {
        return (List<Employee>) xstream.fromXML(file);
    }

    
    protected void writeCollection(List<Employee> newList) {
        try {
            xstream.toXML(newList, new FileOutputStream(file));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    
	
	
	public static void main(String[] args) {
		

	}

}
