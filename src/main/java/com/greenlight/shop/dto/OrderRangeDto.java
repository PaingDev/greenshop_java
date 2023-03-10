package com.greenlight.shop.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRangeDto {
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	Date fromDate;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	Date toDate;
}
