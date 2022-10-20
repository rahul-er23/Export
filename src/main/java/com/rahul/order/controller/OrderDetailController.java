package com.rahul.order.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.lowagie.text.DocumentException;
import com.rahul.order.service.OrderDetailService;
import com.rahul.order.service.OrderDetailServiceImpl;

@Controller
@RequestMapping("/InvoiceDetails")
public class OrderDetailController {
	
	@Autowired
	private OrderDetailServiceImpl service;
	
	@GetMapping("/ExportPDF")
	public void exportToPdf(HttpServletResponse response) throws DocumentException, IOException {
		
		response.setContentType("application/pdf");
		 DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
	        String currentDateTime = dateFormatter.format(new Date());
	        
	        String headerKey = "Content-Disposition";
	        String headerValue = "attachment; filename=orders_" + currentDateTime + ".pdf";
	        response.setHeader(headerKey, headerValue);
	        service.exportPdf(response);
	}
	
/*	@GetMapping("/ExportCSV")
	public void exportToCSV(HttpServletResponse response) throws DocumentException, IOException {
		
		response.setContentType("text/csv");
		 DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
	        String currentDateTime = dateFormatter.format(new Date());
	        
	        String headerKey = "Content-Disposition";
	        String headerValue = "attachment; filename=orders_" + currentDateTime + ".pdf";
	        //response.setHeader(headerKey, headerValue);
	        //service.exportPdf(response);
	        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
	        String[] csvHeader = {"User ID", "E-mail", "Full Name", "Roles", "Enabled"};
	        String[] nameMapping = {"id", "email", "fullName", "roles", "enabled"};
	         
	        csvWriter.writeHeader(csvHeader);
	         
	       // for (User user : listUsers) {
	        //    csvWriter.write(user, nameMapping);
	       // }
	         
	        //csvWriter.close();
         
	    }
*/		
	
}
