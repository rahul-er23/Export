package com.rahul.order.service.support;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.rahul.order.entity.OrderDetail;

public class ExcelSupport {
	private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<OrderDetail> listOrders;
     
    public ExcelSupport(List<OrderDetail> listOrders) {
        this.listOrders = listOrders;
        workbook = new XSSFWorkbook();
    }
 
 
    private void writeHeaderLine() {
        sheet = workbook.createSheet("Orders");
         
        Row row = sheet.createRow(0);
         
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);
         
        createCell(row, 0, "Id", style);      
        createCell(row, 1, "Invoice#", style);       
        createCell(row, 2, "Name", style);    
        createCell(row, 3, "Address", style);
        createCell(row, 4, "Pincode", style);
        createCell(row, 5, "Status", style);    
    }
     
    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        }else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }
     
    private void writeDataLines() {
        int rowCount = 1;
 
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);
                 
        for (OrderDetail order : listOrders) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
             
            createCell(row, columnCount++, order.getId(), style);
            createCell(row, columnCount++, order.getiNumber(), style);
            createCell(row, columnCount++, order.getName(), style);
            createCell(row, columnCount++, order.getAddress().toString(), style);
            createCell(row, columnCount++, order.getPincode(), style);
            createCell(row, columnCount++, order.getStatus(), style);
        }
    }
     
    public void export(HttpServletResponse response) throws IOException {
        writeHeaderLine();
        writeDataLines();
         
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
         
        outputStream.close();
         
    }
}
