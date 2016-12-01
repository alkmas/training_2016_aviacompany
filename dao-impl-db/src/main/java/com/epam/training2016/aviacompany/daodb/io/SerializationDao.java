package com.epam.training2016.aviacompany.daodb.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Repository;

@Repository
public class SerializationDao {
	

	public void save(Object entity, String fileName) {
		byte[] data;
		try {
			data = serialize(entity);
			File file = new File(fileName);
			if (!file.exists()) {
				file.createNewFile();
			}
			FileUtils.writeByteArrayToFile(file, data);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public Object load(String fileName) {
		Object deSerializesdList;
		try {
			File file = new File(fileName);
			if (!file.exists()) {
				return null;
			}
			byte[] readFileToByteArray = FileUtils.readFileToByteArray(file);
			deSerializesdList = deSerialize(readFileToByteArray);
	        return deSerializesdList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
    }

	public static byte[] serialize(Object entity) throws IOException {
		ByteArrayOutputStream bytes = new ByteArrayOutputStream();
		ObjectOutputStream out = new ObjectOutputStream(bytes);
		out.writeObject(entity);
		out.close();
		return bytes.toByteArray();
	}

	public static Object deSerialize(byte[] bytes) throws IOException, ClassNotFoundException {
		ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(bytes));
		Object entity = in.readObject();
		in.close();
		return entity;
	}
}
