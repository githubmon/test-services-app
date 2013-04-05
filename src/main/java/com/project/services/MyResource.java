
package com.project.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.project.business.bean.FileBean;
import com.project.business.exceptions.TestBusinessException;
import com.project.transaction.FileTransactionBo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/** Example resource class hosted at the URI path "/myresource"
 */
@Component
@Path("/myresourceservice")
public class MyResource {
	
	
	@Autowired
	public FileTransactionBo fileTransactionBo;
	
	/** Method processing HTTP GET requests, producing "text/plain" MIME media
     * type.
     * @return String that will be send back as a response of type "text/plain".
     */
    @GET 
    @Produces("text/plain")
    public String getIt() {   
    	FileBean fileBean = new FileBean();
    	try {
			fileTransactionBo.saveFile(fileBean);
		} catch (TestBusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return "Hi there!";
    }
}
