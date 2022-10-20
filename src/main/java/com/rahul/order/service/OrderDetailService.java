package com.rahul.order.service;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.lowagie.text.pdf.PdfPTable;
import com.rahul.order.entity.OrderDetail;

public interface OrderDetailService {
	
	public List<OrderDetail> getAll();
	//public void pdfHeader(PdfPTable table);
	//public void pdfTableData(PdfPTable table);
	//public void exportPdf(HttpServletResponse response); 
}
