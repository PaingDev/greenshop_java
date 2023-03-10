package com.greenlight.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greenlight.shop.domain.Unit;
import com.greenlight.shop.repository.CategoryRepo;
import com.greenlight.shop.repository.UnitRepo;

@Service
public class UnitServiceImpl implements UnitService{
	
	@Autowired
	CategoryRepo categoryRepo;
	@Autowired
	UnitRepo unitRepo;

	@Override
	public Unit getUnitById(int unitId) {
		Unit unit = unitRepo.findById(unitId).get();
		return unit;
	}

	@Override
	public List<Unit> getUnitList() {
		List<Unit> listUnit = unitRepo.findAll();
		return listUnit;
	}

	@Override
	public void saveUnit(Unit unit) {
		unitRepo.save(unit);
	}

	@Override
	public void updateUnit(Unit unit) {
		unitRepo.save(unit);
		
	}

	@Override
	public void removeUnit(int unitId) {
		unitRepo.deleteById(unitId);
		
	}


}
