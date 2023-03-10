package com.greenlight.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greenlight.shop.domain.Township;
import com.greenlight.shop.repository.TownshipRepo;

@Service
public class TownshipServiceImpl implements TownshipService{
	
	@Autowired
	TownshipRepo townshipRepo;

	@Override
	public List<Township> getTownship() {
		return townshipRepo.findAll();
	}

	@Override
	public void saveTownship(Township township) {
		townshipRepo.save(township);
	}

	@Override
	public Township getTownshipById(int townshipId) {
		return townshipRepo.findById(townshipId).get();
	}

	@Override
	public void updateTownship(Township township) {
		townshipRepo.save(township);
	}

	@Override
	public void removeTownship(int townshipId) {
		townshipRepo.deleteById(townshipId);
	}


}
