package com.greenlight.shop.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.greenlight.shop.domain.Useraccount;

@Repository
public interface UserAccountRepo extends JpaRepository<Useraccount, Integer>{

	Useraccount findByFacebookId(String id);

	@Query("SELECT ua FROM Useraccount ua WHERE ua.firebaseId=?1")
	Useraccount findByFirebaseId(String firebaseId);
	
}
