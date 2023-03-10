package com.greenlight.shop.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.greenlight.shop.domain.Item;
import com.greenlight.shop.domain.Sell;
import com.greenlight.shop.domain.Sellingprice;
import com.greenlight.shop.dto.SaleItemDto;

@Repository
public interface ItemRepo extends JpaRepository<Item, Integer>{
	
	
	@Query(value = "SELECT new com.greenlight.shop.dto.SaleItemDto(i.itemId,i.itemName,i.itemNameMm,i.discountPrice,sp.unitPrice,'MMK', sp.unit.unitName, sp.unit.unitNameMm, sp.unit.unitId, sp.date, i.frontImg, i.backImg) FROM Sellingprice sp JOIN sp.item i WHERE sp.priceId IN ("
			+ "SELECT MAX(spo.priceId) as id FROM Sellingprice spo WHERE i.status='ACTIVE' AND spo.unitPrice!=0 GROUP BY spo.unit.unitId,spo.item.itemId"
			+ ") ORDER BY i.itemId")
	public List<SaleItemDto> getAllItem();

	@Query(value = "SELECT new com.greenlight.shop.dto.SaleItemDto(i.itemId,i.itemName, i.itemNameMm,i.discountPrice,sp.unitPrice,'MMK', sp.unit.unitName,sp.unit.unitNameMm, sp.unit.unitId, sp.date,i.frontImg, i.backImg) FROM Sellingprice sp JOIN sp.item i WHERE i.status='ACTIVE' AND  i.category.categoryId=?1 AND sp.priceId IN (" + 
			" SELECT MAX(spo.priceId) as id FROM Sellingprice spo WHERE i.status='ACTIVE' AND spo.unitPrice!=0 GROUP BY spo.unit.unitId,spo.item.itemId) " + 
			" ORDER BY i.itemId")
	public List<SaleItemDto> getItemByCategoryId(int categoryId);
	

	@Query(value = "SELECT new com.greenlight.shop.dto.SaleItemDto(i.itemId,i.itemName,i.itemNameMm,i.discountPrice,sp.unitPrice,'MMK', sp.unit.unitName, sp.unit.unitNameMm, sp.unit.unitId, sp.date,i.frontImg, i.backImg) FROM Sellingprice sp JOIN sp.item i WHERE i.status='ACTIVE' AND  i.category.categoryId=?1 AND sp.priceId IN (" + 
			" SELECT MAX(spo.priceId) as id FROM Sellingprice spo WHERE i.status='ACTIVE' AND spo.unitPrice!=0 GROUP BY spo.unit.unitId,spo.item.itemId) " )
	public Page<SaleItemDto> getItemByCategoryId(int categoryId, Pageable paging);

	//SELECT spo.priceId, spo.unitPrice,spo.unitId,spo.itemId FROM sellingprice spo WHERE spo.priceId IN (
	//SELECT MAX(sp.priceId) as id FROM sellingprice sp JOIN unit u ON sp.unitId=u.unitId JOIN item i ON sp.itemId=i.itemId GROUP BY i.itemId,u.unitId
	//	);
	//@Query(value = "SELECT new com.greenlight.shop.dto.SaleItemDto(i.itemId,i.itemName,sp.unitPrice,'MMK', sp.unit.unitName, sp.unit.unitId) FROM Sellingprice sp JOIN sp.item i")
	@Query(value = "SELECT new com.greenlight.shop.dto.SaleItemDto(i.itemId,i.itemName, i.itemNameMm,i.discountPrice,sp.unitPrice,'MMK', sp.unit.unitName, sp.unit.unitNameMm, sp.unit.unitId, sp.date,i.frontImg, i.backImg) FROM Sellingprice sp JOIN sp.item i WHERE sp.priceId IN ("
			+ "SELECT MAX(spo.priceId) as id FROM Sellingprice spo WHERE i.status='ACTIVE' AND spo.unitPrice!=0 GROUP BY spo.unit.unitId,spo.item.itemId"
			+ ")")
	public Page<SaleItemDto> getAllItem(Pageable paging);
	
	@Modifying
	@Query(value="UPDATE item SET status='DELETE' WHERE itemId=?1",nativeQuery = true)
	public void removeItem(int itemId);

	@Query(value = "SELECT new com.greenlight.shop.dto.SaleItemDto(i.itemId,i.itemName, i.itemNameMm,i.discountPrice, sp.unitPrice,'MMK', sp.unit.unitName, sp.unit.unitNameMm, sp.unit.unitId, sp.date,i.frontImg, i.backImg) FROM Sellingprice sp JOIN sp.item i WHERE sp.priceId IN ("
			+ "SELECT MAX(spo.priceId) as id FROM Sellingprice spo WHERE i.status='ACTIVE' AND i.itemName LIKE ?1% OR i.itemNameMm LIKE ?1% AND spo.unitPrice!=0  GROUP BY spo.unit.unitId,spo.item.itemId"
			+ ")  ") 
	public List<SaleItemDto> searchItem(String itemName,Sort sort);

	//SELECT i FROM Item i LEFT JOIN FETCH i.sellingprices s where i.status<>'DELETE' GROUP BY s.item.itemId, s.unit.unitId ORDER BY s.priceId,s.item.itemId
	@Query(value = "SELECT s FROM Sell s LEFT JOIN s.item i LEFT JOIN i.category c where i.status<>'DELETE' AND c.categoryId=?2 AND (i.itemName LIKE %?1% OR i.itemNameMm LIKE %?1%) GROUP BY s.item.itemId, s.unit.unitId ORDER BY s.priceId,s.item.itemId")
	public Page<Sell> getItemByCategoryIdAndSearch(String text, int categoryId, PageRequest request);

	@Query(value = "SELECT i FROM Item i LEFT JOIN i.category c where i.status<>'DELETE' AND c.categoryId=?2 AND (i.itemName LIKE %?1% OR i.itemNameMm LIKE %?1%) AND i.itemId NOT IN (SELECT s.item.itemId FROM Sell s) ORDER BY i.itemId")
	public Page<Item> getUnlistedItemByCategoryIdAndSearch(String text, int categoryId, PageRequest request); 
	
}
