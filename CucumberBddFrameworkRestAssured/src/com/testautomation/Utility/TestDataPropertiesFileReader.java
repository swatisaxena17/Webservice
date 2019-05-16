package com.testautomation.Utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestDataPropertiesFileReader {

	public Properties getProperty() throws IOException{
		
		FileInputStream inputstream = null;
		
		Properties properties = new Properties();
		try {
			
			properties.load(new FileInputStream("resourse/testdata-config.properties"));
		}catch(Exception e)
		{
			System.out.println("Exception  :"+  e);
		}
		
		return properties;
	}
}
