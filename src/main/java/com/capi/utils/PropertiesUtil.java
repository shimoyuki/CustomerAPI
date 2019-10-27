package com.capi.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesUtil {
	public static Properties properties = null ;

    public static String getPropertiesValue(String file, String key){
        try {
            if(properties==null){
                properties = new Properties();
                String path = PropertiesUtil.class.getClassLoader().getResource("").getPath();
                FileInputStream in = new FileInputStream(new File( path + file));
                properties.load(in);
                in.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties.getProperty(key);
    }
}
