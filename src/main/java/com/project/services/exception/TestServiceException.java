package com.project.services.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.project.services.bean.TestServiceResponseBean;
import com.sun.jersey.api.Responses;

public class TestServiceException extends WebApplicationException {

	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = Logger.getLogger(TestServiceException.class);
	
	/**
	 * Default constructor
	 */
	public TestServiceException() {		
		 super(Responses.notFound().build());
	}		
	
	public TestServiceException(TestServiceResponseBean srb) {		
		super(Response.ok(srb).type(MediaType.APPLICATION_JSON).build());
		logger.error("TestServiceException:" + srb.getServiceName() + "\n\t" + srb.getServiceMessage());				
	}
	
	public TestServiceException(TestServiceResponseBean srb, Exception e) {		
		
		super(Response.ok(srb).type(MediaType.APPLICATION_JSON).build());
	    
		StringWriter stack = new StringWriter();
	    e.printStackTrace(new PrintWriter(stack));	    
		logger.error("JVM Exception:" + e.getMessage() + "\n\t Stacktrace:" + stack.toString());				
	}

}
