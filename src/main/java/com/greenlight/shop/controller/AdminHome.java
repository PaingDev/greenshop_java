package com.greenlight.shop.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.rabbitconverter.rabbit.Rabbit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.greenlight.shop.domain.Orderitem;
import com.greenlight.shop.domain.Orderitemlist;
import com.greenlight.shop.dto.OrderDetail;
import com.greenlight.shop.dto.OrderRangeDto;
import com.greenlight.shop.dto.OrderStatus;
import com.greenlight.shop.dto.VoucherItem;
import com.greenlight.shop.service.OrderService;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.SimpleFileResolver;
import net.sf.jasperreports.export.Exporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

@Controller()
@RequestMapping("/admin")
public class AdminHome {

	@Autowired
	OrderService orderService;
	

	@ModelAttribute("selectedMenu")
	public String selectedMenu() {
		return "home";
	}

	@GetMapping("/home")
	public String adminHome(@ModelAttribute OrderRangeDto orderRangeDto, Model model) {
		List<Orderitem> listOrder = new ArrayList<Orderitem>();
		// listOrder.addAll(orderService.getOrderByDate(orderRangeDto.getFromDate(),
		// orderRangeDto.getToDate()));
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		SimpleDateFormat of = new SimpleDateFormat("hh:mm a");

		listOrder = orderService.getOrderListByDate(orderRangeDto.getFromDate(), orderRangeDto.getToDate());
		for (Orderitem item : listOrder) {
			String time = item.getPreferedTime();
			int len = time.split(":").length;
			if (len != 4) {

				try {
					Date date = sf.parse(time);
					time = of.format(date);
					item.setPreferedTime(time);
				} catch (ParseException e) {
					//e.printStackTrace();
				}

			}
		}

		List<String> list = new ArrayList<>();
		list.add("ORDER");
		list.add("ACCEPT");
		list.add("REJECT");
		list.add("DELIVERING");
		list.add("COMPLETE");
		

		model.addAttribute("listOrder", listOrder);
		model.addAttribute("listStatus", list);

		return "adminHome";
	}

	@ResponseBody
	@PostMapping("/orderStatus")
	public OrderStatus editAdminHome(@ModelAttribute OrderStatus orderStatus) {
		System.out.println(orderStatus);
		orderService.changeStatus(orderStatus.getOrderItemId(), orderStatus.getStatus());
		//SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		//redirect.addAttribute("fromDate", df.format(orderStatus.getFromDate()));
		//redirect.addAttribute("toDate", df.format(orderStatus.getToDate()));
		return orderStatus;
	}

	@GetMapping("/orderReport")
	public HttpEntity<byte[]> adminExcel(@ModelAttribute OrderRangeDto orderRangeDto, Model model) {
		List<Orderitem> listOrder = new ArrayList<Orderitem>();
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		SimpleDateFormat of = new SimpleDateFormat("hh:mm a");
		XSSFWorkbook workbook = new XSSFWorkbook();
		String fileName = df.format(orderRangeDto.getFromDate()) + "_" + df.format(orderRangeDto.getToDate());
		fileName = fileName.replaceAll("/", "_") + ".xlsx";
		XSSFSheet sheet = workbook.createSheet(fileName);
		XSSFCellStyle style = workbook.createCellStyle();
		style.setWrapText(true);
		style.setAlignment(XSSFCellStyle.ALIGN_LEFT);
		style.setVerticalAlignment(XSSFCellStyle.VERTICAL_JUSTIFY);
		listOrder = orderService.getOrderListByDate(orderRangeDto.getFromDate(), orderRangeDto.getToDate());
		for (Orderitem item : listOrder) {
			String time = item.getPreferedTime();
			int len = time.split(":").length;
			if (len != 4) {

				try {
					Date date = sf.parse(time);
					time = of.format(date);
					item.setPreferedTime(time);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		}
		sheet.setColumnWidth(0, 300);
		sheet.setColumnWidth(1, 1000);
		sheet.setColumnWidth(2, 3500);
		sheet.setColumnWidth(3, 3500);
		sheet.setColumnWidth(4, 3500);
		sheet.setColumnWidth(5, 8000);
		sheet.setColumnWidth(6, 2000);
		sheet.setColumnWidth(7, 1000);
		sheet.setColumnWidth(8, 1000);
		sheet.setColumnWidth(9, 2000);
		sheet.setColumnWidth(10, 8000);
		int rowCount = 0;
		Row row0 = sheet.createRow(rowCount);
		row0.createCell(0).setCellValue("No.");
		row0.createCell(1).setCellValue("Id");
		row0.createCell(2).setCellValue("Phone");
		row0.createCell(3).setCellValue("Township");
		row0.createCell(4).setCellValue("OrderDate(InsertDate)");
		row0.createCell(5).setCellValue("Address");
		row0.createCell(6).setCellValue("Amount");
		row0.createCell(7).setCellValue("DeliveryFee");
		row0.createCell(8).setCellValue("Discount");
		row0.createCell(9).setCellValue("Total");
		row0.createCell(10).setCellValue("Items");

		for (Orderitem item : listOrder) {
			Row row = sheet.createRow(++rowCount);
			row.setRowStyle(style);
			Cell cell0 = row.createCell(0);
			cell0.setCellValue(rowCount);
			Cell cell = row.createCell(1);
			cell.setCellValue(item.getOrderItemId());
			Cell cell2 = row.createCell(2);
			cell2.setCellValue(item.getPhoneNo());
			Cell cell3 = row.createCell(3);
			cell3.setCellValue(item.getTownship().getTownshipNameMm());
			Cell cell4 = row.createCell(4);
			cell4.setCellValue(item.getDate() + ":" + item.getPreferedTime() + "(" + item.getDate() + ")");
			Cell cell5 = row.createCell(5);
			cell5.setCellValue(item.getAddress());
			Cell cell6 = row.createCell(6);
			cell6.setCellValue(item.getAmount());
			Cell cell7 = row.createCell(7);
			cell7.setCellValue(item.getDeliveryFee());
			Cell cell8 = row.createCell(8);
			cell8.setCellValue(item.getDiscount());
			Cell cell9 = row.createCell(9);
			cell9.setCellValue(item.getTotal());
			Cell cell10 = row.createCell(10);

			List<String> listData = item.getOrderitemlists().stream().map(data -> {
				return data.getItemName() + " (Qty " + data.getQty() + ")  (Price " + data.getUnitPrice() + ") (Amt "
						+ data.getAmount() + ")";
			}).collect(Collectors.toList());
			row.setHeightInPoints((item.getOrderitemlists().size() * sheet.getDefaultRowHeightInPoints()));
			cell10.setCellValue(String.join(System.lineSeparator(), listData));
			cell10.setCellStyle(style);

		}
		try {
			ByteArrayOutputStream out = new ByteArrayOutputStream();

			workbook.write(out);

			byte[] xls = out.toByteArray();

			out.close();
			workbook.close();
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			// headers.add("Content-Disposition", "attachment; filename="+fileName);
			headers.setContentDisposition(ContentDisposition.parse("attachment; filename=" + fileName));
			return new HttpEntity<byte[]>(xls, headers);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	@GetMapping("/orderReport/{orderId}")
	public void orderReportById(@PathVariable("orderId") int orderId, HttpServletResponse response) {
		Map<String, Object> param = new HashMap<>();
		Orderitem order = orderService.getOrderById(orderId);
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		SimpleDateFormat of = new SimpleDateFormat("hh:mm a");
		param.put("date",  df.format(order.getOrderDate()));
		param.put("address", Rabbit.uni2zg(order.getAddress()));
		param.put("location", order.getTownship().getTownshipName());
		param.put("phoneNo", order.getPhoneNo());
		param.put("orderId", order.getEncryptedId());
		String time = order.getPreferedTime();
		int len = time.split(":").length;
		if (len != 4) {

			try {
				Date date = sf.parse(time);
				time = of.format(date);
				order.setPreferedTime(time);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		param.put("time", order.getPreferedTime());
		param.put("subTotalMain", order.getAmount());
		param.put("deliveryChargeMain", order.getDeliveryFee());
		param.put("discountMain", order.getDiscount());
		param.put("totalMain", order.getTotal());
		InputStream logo = getClass().getResourceAsStream("/logo.png");
		param.put("logo", logo);
		try {
			
			List<VoucherItem> listVoucher = order.getOrderitemlists().stream().map(data->{
				VoucherItem voucherItem = new VoucherItem(0, "("+ data.getOrderItemListId() +")"+Rabbit.uni2zg(data.getItemName()+" - "+ data.getSellingprice().getUnit().getUnitNameMm()) , data.getQty(), data.getAmount());				
				return voucherItem;
			}).collect(Collectors.toList());
			int i=1;
			for(VoucherItem item: listVoucher) {
				item.setNo(i);
				i++;
			}
			
			System.out.println(listVoucher.size());
			
			param.put("dataSource", new JRBeanCollectionDataSource(listVoucher));
			
			JasperPrint jasperPrint = JasperFillManager
					.fillReport(getClass().getResourceAsStream("/voucher.jasper"), param, new JREmptyDataSource());

			JRPdfExporter pdfExporter = new JRPdfExporter();
			pdfExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
			ByteArrayOutputStream pdfReportStream = new ByteArrayOutputStream();
			pdfExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(pdfReportStream));
			pdfExporter.exportReport();
			
//			Exporter exporter = new HtmlExporter();
//			final ByteArrayOutputStream out = new ByteArrayOutputStream();
//			OutputStream responseOutputStream = response.getOutputStream();
//			exporter.setExporterOutput(new SimpleHtmlExporterOutput(out));
//			
//			exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
//		    exporter.exportReport();
			 
			 
			response.setContentType("application/pdf");
			response.setHeader("Content-Length",  String.valueOf(pdfReportStream.size()));
			response.addHeader("Content-Disposition", "inline; filename="+ orderId +".pdf;");

			OutputStream responseOutputStream = response.getOutputStream();
			responseOutputStream.write(pdfReportStream.toByteArray());
			responseOutputStream.close();
			pdfReportStream.close();
			
		} catch (JRException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
