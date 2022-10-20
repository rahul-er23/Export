package com.rahul.order.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;
import com.lowagie.text.DocumentException;
import com.rahul.order.entity.OrderDetail;
import com.rahul.order.service.OrderDetailService;
import com.rahul.order.service.support.ExcelSupport;
import com.rahul.order.service.support.FileName;
import com.rahul.order.service.support.PdfSupport;

@Controller
@RequestMapping("/InvoiceDetails")
public class OrderDetailController {

	@Autowired
	private OrderDetailService service;

	@GetMapping("/ExportPDF")
	public void exportToPdf(HttpServletResponse response) throws DocumentException, IOException {
		HttpServletResponse response2 = new FileName().fileN(response, "pdf");
		List<OrderDetail> listOrders = service.getAll();
		PdfSupport support = new PdfSupport(listOrders);
		support.exportPdf(response2);
	}

	@GetMapping("/ExportCSV")
	public void exportToCSV(HttpServletResponse response) throws DocumentException, IOException {
		HttpServletResponse response2 = new FileName().fileN(response, "csv");
		ICsvBeanWriter csvWriter = new CsvBeanWriter(response2.getWriter(), CsvPreference.STANDARD_PREFERENCE);
		String[] csvHeader = { "Id", "Invoice#", "Name", "Address", "Pincode", "Status" };
		String[] nameMapping = { "id", "iNumber", "name", "Address", "pincode", "Status" };
		csvWriter.writeHeader(csvHeader);
		List<OrderDetail> listOrders = service.getAll();
		for (OrderDetail order : listOrders) {
			csvWriter.write(order, nameMapping);
		}
		csvWriter.close();
	}

	@GetMapping("/ExportExcel")
	public void exportToExcel(HttpServletResponse response) throws IOException {
		HttpServletResponse response2 = new FileName().fileN(response, "excel");
		List<OrderDetail> listOrders = service.getAll();
		ExcelSupport excelsupport = new ExcelSupport(listOrders);
		excelsupport.export(response2);
	}
}