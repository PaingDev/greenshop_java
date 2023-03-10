package com.greenlight.shop.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.greenlight.shop.domain.Sellingprice;

@Repository
public interface SellingPriceRepo extends JpaRepository<Sellingprice, Integer>{
	
	@Query("SELECT s FROM Sellingprice s WHERE s.item.itemId=?1")
	public List<Sellingprice> getSellingPriceByItemId(int itemId);

	@Query("SELECT sp FROM Sellingprice sp WHERE sp.priceId IN (SELECT MAX(s.priceId) as id FROM Sellingprice s WHERE s.item.itemId=?1 AND s.unit.unitId=?2 GROUP BY s.item.itemId, s.unit.unitId)")
	public Sellingprice getSellingPriceByItemAndUnitId(int itemId, int unitId);

	@Modifying
	@Query(nativeQuery = true, value = "DELETE FROM sellingprice  where itemId=?1 AND unitId=?2")
	public void deleteSellingPriceByItemAndUnitId(int itemId, int unitId);
	
}
