package com.intelitune.nwms.service;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import java.util.ResourceBundle;

public class PropertyService {
	
	//读配置文件片段

	public String getPropertyValue(String key){
//	  Properties pro = new Properties();
//		Properties property = System.getProperties();
	  try {
		    ResourceBundle     resources     =     ResourceBundle.getBundle("urlSetting");   
		    String     value     =     resources.getString(key);   
//	   InputStream in = new FileInputStream(property.getProperty("user.dir")+"\\urlSetting.properties");  //加载配置文件
//	   try {
//	    pro.load(in);
//	   } finally {
//	    in.close();
//	   }
		    return value;
//	   return pro.getProperty(key);
	  } catch (Exception e) {
	   e.printStackTrace();

	   return null;
	  }

	}

}
