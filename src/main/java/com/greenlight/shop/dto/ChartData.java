package com.greenlight.shop.dto;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ChartData {
	String label;// "Chicken Price(1Unit)",;
	//String backgroundColor = '#acc236',
	String borderColor;
	boolean fill = false;
	List<TimeData> data;
			
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class TimeData{
		Date t;
		int y;
	}
}
