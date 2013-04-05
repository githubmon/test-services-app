package com.project.services.bean;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "TestServiceResponse")
public class TestServiceResponseBean {
	
	private String id; 
	private Date created;
	private String serviceName;	
	private Boolean success ;
	private String serviceMessage;
		
	public Boolean getSuccess() {
		return success;
	}
	public void setSuccess(Boolean success) {
		this.success = success;
	}
	public String getServiceMessage() {
		return serviceMessage;
	}
	public void setServiceMessage(String serviceMessage) {
		this.serviceMessage = serviceMessage;
	}		
	
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
		
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}		
}
	
	
