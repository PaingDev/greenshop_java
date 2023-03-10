package com.greenlight.shop.dto;

import javax.validation.constraints.Min;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UnitDto {
	
	@Min(value = 1, message = "Id must not zero")
	int unitId;
	String unitName;
	String unitNameMm;

	public UnitDto(int unitId, String unitName) {
		super();
		this.unitId = unitId;
		this.unitName = unitName;
	}
	
	

}
