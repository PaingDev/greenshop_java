package com.greenlight.shop.service;

import java.util.List;

import com.greenlight.shop.domain.Township;

public interface TownshipService {

	List<Township> getTownship();

	void saveTownship(Township township);

	Township getTownshipById(int townshipId);

	void updateTownship(Township township);

	void removeTownship(int townshipId);


}
