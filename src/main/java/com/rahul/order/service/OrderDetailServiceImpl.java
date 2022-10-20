package com.rahul.order.service;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import java.awt.Color;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import com.rahul.order.entity.OrderDetail;
import com.rahul.order.repository.OrderDetailRepository;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {

	private OrderDetailRepository orderepo;

	@Autowired
	public OrderDetailServiceImpl(OrderDetailRepository orderepo) {
		this.orderepo = orderepo;	
	}
	
	@Override
	public List<OrderDetail> getAll() {
		return orderepo.findAll();
	}

	public void pdfHeader(PdfPTable table) {
		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(Color.ORANGE);
		cell.setPadding(5);

		Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
		font.setSize(8);
		font.setColor(Color.BLACK);

		cell.setPhrase(new Phrase("Id", font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Invoice#", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Name", font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Address", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Pincode", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Status", font));
		table.addCell(cell);
	}

	public void pdfTableData(PdfPTable table) {
		List<OrderDetail> listOrders = getAll();
		for (OrderDetail order : listOrders) {
			table.addCell(String.valueOf(order.getId()));
			table.addCell(String.valueOf(order.getiNumber()));
			table.addCell(order.getName());
			table.addCell(order.getAddress());
			table.addCell(String.valueOf(order.getPincode()));
			table.addCell(order.getStatus());			
		}
	}

	@Override
	public void exportPdf(HttpServletResponse response){
		try {
		Document d = new Document(PageSize.A4);
		PdfWriter.getInstance(d, response.getOutputStream());
		d.open();
		
		Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		font.setSize(18);
		font.setColor(Color.MAGENTA);
		
		Paragraph p = new Paragraph("List of Orders",font);
		p.setAlignment(Paragraph.ALIGN_CENTER);
		d.add(p);
		
		PdfPTable table = new PdfPTable(6);
		table.setWidthPercentage(100f);
        table.setWidths(new float[] {1.0f, 3.0f, 3.0f, 2.5f, 3.0f,2.0f});
        table.setSpacingBefore(10);        
        pdfHeader(table);
        pdfTableData(table);
         
        d.add(table);
        d.close();}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
