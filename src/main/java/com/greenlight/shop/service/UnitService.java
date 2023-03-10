package com.greenlight.shop.service;

import java.util.List;

import com.greenlight.shop.domain.Unit;

public interface UnitService {

	Unit getUnitById(int unitId);

	List<Unit> getUnitList();

	void saveUnit(Unit unit);

	void updateUnit(Unit unit);

	void removeUnit(int unitId);


}
