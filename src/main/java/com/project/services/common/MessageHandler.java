package com.project.services.common;

import java.util.Locale;

import org.apache.log4j.Logger;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class MessageHandler {
	
	private static final Logger logger = Logger.getLogger(MessageHandler.class);
	private static MessageSource resources;	  
	
	public static String getMessage(String key, Object [] params) {
	   try {
		   		resources = new ClassPathXmlApplicationContext("applicationContext.xml");
		   		return resources.getMessage(key, params, Locale.getDefault());
	        }
	        catch (Exception e) {
	        	logger.error("Exception " + e.getMessage() + "- Unresolved key: " + key  );
	            return "Unresolved key: " + key;
	        }
	    }
}
