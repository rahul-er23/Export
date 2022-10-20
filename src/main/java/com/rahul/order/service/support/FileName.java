package com.rahul.order.service.support;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletResponse;

public class FileName {

	public HttpServletResponse fileN(HttpServletResponse response,String str) throws IOException {
		
		if(str.equals("pdf")){
			response.setContentType("application/pdf");
			
			DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
			String currentDateTime = dateFormatter.format(new Date());

			String headerKey = "Content-Disposition";
			String headerValue = "attachment; filename=orders_" + currentDateTime + ".pdf";
			response.setHeader(headerKey, headerValue);
			return response;
		}else if(str.equals("csv")){
			response.setContentType("text/csv");
			DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
			String currentDateTime = dateFormatter.format(new Date());

			String headerKey = "Content-Disposition";
			String headerValue = "attachment; filename=orders_" + currentDateTime + ".csv";
			response.setHeader(headerKey, headerValue);
			return response;
		}else if(str.equals("excel")){
			response.setContentType("application/octet-stream");
			DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
			String currentDateTime = dateFormatter.format(new Date());

			String headerKey = "Content-Disposition";
			String headerValue = "attachment; filename=users_" + currentDateTime + ".xlsx";
			response.setHeader(headerKey, headerValue);
			return response;
		}
		return response;
	}	
}