package com.project.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.project.business.bean.FileBean;
import com.project.business.exceptions.TestBusinessException;
import com.project.iservices.IUploadFileService;
import com.project.iservices.TestServiceResponse;
import com.project.services.common.MessageHandler;
import com.project.services.common.ServiceCommonConstants;
import com.project.services.exception.TestServiceException;
import com.project.transaction.FileTransactionBo;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

@Component
@Path("/uploadFile")
public class UploadFileService implements IUploadFileService {
	
	@Autowired
	public FileTransactionBo fileTransactionBo;		
	
	@Autowired
	TestServiceResponse testServiceResponse;
	
	private static final Logger logger = Logger.getLogger(UploadFileService.class);
	
	/**
	 * 
	 * @param uploadedInputStream
	 * @param fileDetail
	 * @return
	 */
	  @POST   
	  @Consumes(MediaType.MULTIPART_FORM_DATA) 
	  @Produces(MediaType.APPLICATION_JSON) 
	  public Response uploadFile(@FormDataParam("file") InputStream uploadedInputStream,
			  					 @FormDataParam("file") FormDataContentDisposition fileDetail,
			  					 @FormDataParam("device") String device,
			  					 @FormDataParam("user") String user) {	
		  
		  if(logger.isDebugEnabled()){
			  logger.debug("INIT - 'uploadFile' method");
			  logger.debug("invoked from 'Device'="+device); 
			  logger.debug("Invoked by User="+user); 
		  }
		  
		  testServiceResponse.addTestServiceResponse("ID00000", IUploadFileService.UPLOAD_FILE_SERVICE_NAME);	      
		  		  
		  if(fileDetail == null || 
				  (fileDetail !=null && 
				  	(fileDetail.getFileName() == null || fileDetail.getFileName().equals(""))) ){
			  logger.warn("Input param 'fileDetail' is Null or fileDetail.Name is Null or empty");			  
			  testServiceResponse.getTestServiceResponseBean().setServiceMessage(MessageHandler.getMessage(ServiceCommonConstants.ERROR_CODE_INPUT_PARAM_NULL_PROP, new Object [] {fileDetail}));	
			  testServiceResponse.getTestServiceResponseBean().setSuccess(Boolean.FALSE);  
			  throw new TestServiceException(testServiceResponse.getTestServiceResponseBean()); 
			  
		  }	
		  else if(uploadedInputStream == null){
			  logger.warn("Input param 'uploadedInputStream' is '"+uploadedInputStream + "'");
			  testServiceResponse.getTestServiceResponseBean().setServiceMessage(MessageHandler.getMessage(ServiceCommonConstants.ERROR_CODE_INPUT_PARAM_NULL_PROP, new Object [] {uploadedInputStream}));	
			  testServiceResponse.getTestServiceResponseBean().setSuccess(Boolean.FALSE);  
			  throw new TestServiceException(testServiceResponse.getTestServiceResponseBean()); 
		  }	
	  						  
		  try {
			saveToFile(uploadedInputStream, IUploadFileService.FILE_PATH_LOCATION+fileDetail.getFileName());
		  } catch (IOException ioe) {
			logger.error("'Exception thrown: " + ioe.getMessage());
			testServiceResponse.getTestServiceResponseBean().setServiceMessage(MessageHandler.getMessage(ioe.getMessage(), new Object [] {ioe.getClass().getName()}));	
			testServiceResponse.getTestServiceResponseBean().setSuccess(Boolean.FALSE);  
			throw new TestServiceException(testServiceResponse.getTestServiceResponseBean(), ioe); 
		  }	
		  
		  try {
			  FileBean fileBean = new FileBean();
			  fileBean.setName(fileDetail.getFileName());
			  fileBean.setPathLocation(IUploadFileService.FILE_PATH_LOCATION);
			  fileBean.setSize(fileDetail.getSize());			  
			  fileTransactionBo.saveFile(fileBean);
			  
	      } catch (TestBusinessException tbe) {
				logger.error("TestBusinessException: " + tbe.getCause()); 	
				tbe.printStackTrace();	
				testServiceResponse.getTestServiceResponseBean().setServiceMessage(ServiceCommonConstants.ERROR_CODE_INPUT_PARAM_NULL_PROP);			
				testServiceResponse.getTestServiceResponseBean().setSuccess(Boolean.FALSE);						
				throw new TestServiceException(testServiceResponse.getTestServiceResponseBean());			
	      }	

		  if(logger.isDebugEnabled()){
			  logger.debug("END - 'uploadFile' method");
		  }
		  testServiceResponse.getTestServiceResponseBean().setServiceMessage(MessageHandler.getMessage(ServiceCommonConstants.MSG_CODE_SUCCESS_RESPONSE_TRANSACTION_PROP, new Object [] {fileDetail.getFileName()}));			
		  testServiceResponse.getTestServiceResponseBean().setSuccess(Boolean.TRUE);  
		  return Response.ok(testServiceResponse.getTestServiceResponseBean()).build();
	  }
	  
	  
	  /**
	   * 
	   * @param uploadedInputStream
	   * @param uploadedFileLocation
	   */
	  private FileOutputStream saveToFile(InputStream uploadedInputStream, String uploadedFileLocation) 
			  throws IOException{
		  
		  FileOutputStream fout = null;
		  if(logger.isDebugEnabled()){
			  logger.debug(" INIT - saveToFile method");
		  }
		  
		  int read = 0;
          byte[] bytes = new byte[1024];          
          fout = new FileOutputStream(new File(uploadedFileLocation));
          while ((read = uploadedInputStream.read(bytes)) != -1) {
        	  fout.write(bytes, 0, read);
          }
          fout.flush();
          fout.close();
          
          if(logger.isDebugEnabled()){
			  logger.debug(" END - saveToFile method");
		  }
        
		  return fout;
	  }

}
