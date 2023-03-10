package com.greenlight.shop.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.greenlight.shop.domain.Item;
import com.greenlight.shop.domain.Sellingprice;
import com.greenlight.shop.domain.Unit;
import com.greenlight.shop.dto.ChartData;
import com.greenlight.shop.service.ItemService;
import com.greenlight.shop.service.SellingPriceService;
import com.greenlight.shop.service.UnitService;

@Controller
@RequestMapping("/admin")
public class PriceChartController {
	@Autowired
	SellingPriceService sellingPriceService;
	
	@Autowired
	UnitService unitService;
	
	@Autowired
	ItemService itemService;
	
	public String[] mColors = {
            "#39add1", // light blue
            "#3079ab", // dark blue
            "#c25975", // mauve
            "#e15258", // red
            "#f9845b", // orange
            "#838cc7", // lavender
            "#7d669e", // purple
            "#53bbb4", // aqua
            "#51b46d", // green
            "#e0ab18", // mustard
            "#637a91", // dark gray
            "#f092b0", // pink
            "#b7c0c7"  // light gray
    };

	@GetMapping("/priceChart")
	String getPriceChart(@RequestParam(value = "itemId",required = false, defaultValue = "0") int itemId, Model model) {
		List<Sellingprice> listPrice = sellingPriceService.getPriceListByItemId(itemId);
		Map<Integer, List<Sellingprice>> mapPrice = new HashMap<>();
		listPrice.stream().forEach(value->{
			int unitId = value.getUnit().getUnitId();
			System.out.println(unitId);
			List<Sellingprice> list = mapPrice.getOrDefault(unitId, new ArrayList<Sellingprice>());
			list.add(value);
			mapPrice.put(unitId, list);
		});
		List<ChartData> listChart = new ArrayList<>();
		int i=0;
		for(Entry<Integer, List<Sellingprice>> entry:mapPrice.entrySet()) {
			List<Sellingprice> listResultPrice = entry.getValue();
			Unit unit = unitService.getUnitById(entry.getKey());
			Item item = itemService.getItemById(itemId);
			ChartData chartData = new ChartData();
			chartData.setLabel(item.getItemName() + "("+unit.getUnitName()+")");
			List<ChartData.TimeData> listTimeData = listResultPrice.stream().map(data->{
				return new ChartData.TimeData(data.getDate(), data.getUnitPrice());
			}).collect(Collectors.toList());
			chartData.setBorderColor(mColors[i]);
			chartData.setData(listTimeData);
			listChart.add(chartData);
			i++;
		}
		model.addAttribute("data", listChart);
		return "priceChart";
	}
	
}
