package com.project.iservices;

import com.project.services.bean.TestServiceResponseBean;
import com.project.services.exception.TestServiceException;

public interface TestServiceResponse {
	
	public TestServiceResponseBean addTestServiceResponse(String id, String name) throws TestServiceException;
	
	public TestServiceResponseBean getTestServiceResponseBean();
	
	public void setTestServiceResponseBean(TestServiceResponseBean testServiceResponseBean);
	

}
