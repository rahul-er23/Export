package com.rahul.order.service.support;

import java.awt.Color;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.rahul.order.entity.OrderDetail;

public class PdfSupport {
	private List<OrderDetail> listOrders;
    
    public PdfSupport(List<OrderDetail> listOrders) {
        this.listOrders = listOrders;
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
		for (OrderDetail order : listOrders) {
			table.addCell(String.valueOf(order.getId()));
			table.addCell(String.valueOf(order.getiNumber()));
			table.addCell(order.getName());
			table.addCell(order.getAddress());
			table.addCell(String.valueOf(order.getPincode()));
			table.addCell(order.getStatus());			
		}
	}

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
