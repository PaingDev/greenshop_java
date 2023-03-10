package com.greenlight.shop.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.greenlight.shop.domain.Unit;

@Repository
public interface UnitRepo extends JpaRepository<Unit, Integer>{
	
}
