package com.project.iservices;

import java.io.InputStream;

import javax.ws.rs.core.Response;

import com.sun.jersey.core.header.FormDataContentDisposition;

public interface IUploadFileService {	
	
	public static final String FILE_PATH_LOCATION = "E://uploadsTest2/";	
	public static final String UPLOAD_FILE_SERVICE_NAME = "UploadFile";
	
	public Response uploadFile(InputStream uploadedInputStream,
			 					FormDataContentDisposition fileDetail,
			 					String device,
			 					String user);

}
