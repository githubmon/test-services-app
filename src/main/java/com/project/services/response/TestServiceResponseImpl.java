package com.project.services.response;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.project.iservices.TestServiceResponse;
import com.project.services.bean.TestServiceResponseBean;
import com.project.services.exception.TestServiceException;

public class TestServiceResponseImpl implements TestServiceResponse{
	
	@Autowired
	private TestServiceResponseBean testServiceResponseBean;
	
	public TestServiceResponseBean getTestServiceResponseBean() {
		return testServiceResponseBean;
	}

	public void setTestServiceResponseBean(
			TestServiceResponseBean testServiceResponseBean) {
		this.testServiceResponseBean = testServiceResponseBean;
	}
	
	public TestServiceResponseBean addTestServiceResponse(String id, String name) throws TestServiceException{				
		this.testServiceResponseBean.setCreated(new Date());
		this.testServiceResponseBean.setId(id);
		this.testServiceResponseBean.setServiceName(name);
		return testServiceResponseBean;
	}
}
