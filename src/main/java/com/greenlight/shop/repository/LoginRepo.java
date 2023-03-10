package com.greenlight.shop.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.greenlight.shop.domain.Useraccount;

@Repository
public interface LoginRepo extends CrudRepository<Useraccount, Integer>{

	Useraccount getUseraccountByUserName(String username);
	

}
