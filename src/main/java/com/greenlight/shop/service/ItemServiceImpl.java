package com.greenlight.shop.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.greenlight.shop.domain.Item;
import com.greenlight.shop.domain.Sell;
import com.greenlight.shop.domain.Sellingprice;
import com.greenlight.shop.dto.SaleItemDto;
import com.greenlight.shop.dto.SellingItemDto;
import com.greenlight.shop.repository.ItemRepo;
import com.greenlight.shop.repository.SellingPriceRepo;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	ItemRepo itemRepo;

	@Autowired
	SellingPriceRepo sellingPriceRepo;
	
	@PersistenceContext
	EntityManager entityManager;
	
	@Value("${item.pagesize}")
	int pageSize;

	@Transactional(readOnly = true)
	@Override
	public List<SaleItemDto> getListSaleItem() {
		List<SaleItemDto> saleItemDto = itemRepo.getAllItem();
		return saleItemDto;
	}

	@Transactional(readOnly = true)
	@Override
	public List<SaleItemDto> getItemByCategoryId(int categoryId, int pageNo, int pageSize, String sortBy,String dir) {
		System.out.println(sortBy);
		//String filterBy = (ts != null) ? "date": (itemName != null) ? "i.itemName" : (price != null) ? "unitPrice" : "i.itemId";
        Direction direction = (dir.equals("ASC")) ? Direction.ASC : Direction.DESC;
        String sortQuery = "date".equals(sortBy)?"date": "itemName".equals(sortBy)? "i.itemName": "unitPrice".equals(sortBy)?"unitPrice": "i.itemId";
        System.out.println(sortQuery);
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(direction,sortQuery));
		Page<SaleItemDto> page = itemRepo.getItemByCategoryId(categoryId, paging);
		if (page.hasContent()) {
			return page.getContent();
		} else {
			return new ArrayList<SaleItemDto>();
		}
	}

	@Transactional(readOnly = true)
	@Override
	public List<SaleItemDto> getListSaleItem(int pageNo, int pageSize, String sortBy,
			String dir) {

		//String filterBy = (ts != null) ? "date": (itemName != null) ? "i.itemName" : (price != null) ? "unitPrice" : "i.itemId";
       Direction direction = (dir.equals("ASC")) ? Direction.ASC : Direction.DESC;
       //date, itemName, unitPrice
       String sortQuery = "date".equals(sortBy)?"date": "itemName".equals(sortBy)? "i.itemName": "unitPrice".equals(sortBy)?"unitPrice": "i.itemId";
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(direction, sortQuery));

		Page<SaleItemDto> page = itemRepo.getAllItem(paging);
		if (page.hasContent()) {
			return page.getContent();
		} else {
			return new ArrayList<SaleItemDto>();
		}
	}

	@Override
	public List<Item> getItemList() {		
		List<Item> listItem = entityManager.createQuery("SELECT i FROM Item i LEFT JOIN FETCH i.sellingprices s where i.status<>'DELETE' GROUP BY s.item.itemId, s.unit.unitId ORDER BY s.priceId,s.item.itemId", Item.class)
				.getResultList();
		return listItem;
	}

	@Transactional(readOnly = false)
	@Override
	public void saveItem(Item item) {
		itemRepo.save(item);
	}

	@Override
	public Item getItemById(int itemId) {
		Optional<Item> obj = itemRepo.findById(itemId);

		return obj.get();
	}

	@Transactional(readOnly = false)
	@Override
	public void removeItem(int itemId) {
		itemRepo.removeItem(itemId);

	}

	@Override
	public List<SaleItemDto> searchItem(String itemName, String sortBy, String dir) {
		//String filterBy = (ts != null) ? "date": (item != null) ? "i.itemName" : (price != null) ? "unitPrice" : "i.itemId";
		String sortQuery = "date".equals(sortBy)?"date": "itemName".equals(sortBy)? "i.itemName": "unitPrice".equals(sortBy)?"unitPrice": "i.itemId";
		return itemRepo.searchItem(itemName,Sort.by((dir.equals("ASC"))?Sort.Direction.ASC : Sort.Direction.DESC,sortQuery));
	}

	@Override
	public void saveSellingPrice(Sellingprice sellingPrice) {
		sellingPriceRepo.save(sellingPrice);
	}

	@Override
	public List<Item> getItemListByCategoryId(int categoryId) {
		List<Item> listItem = entityManager.createQuery("SELECT i FROM Item i LEFT JOIN FETCH i.sellingprices s where i.status<>'DELETE' AND i.category.categoryId=:categoryId GROUP BY s.item.itemId, s.unit.unitId ORDER BY s.priceId,i.itemId", Item.class)
				.setParameter("categoryId", categoryId)
				.getResultList();
		return listItem;
	}

	@Transactional(readOnly = true)
	@Override
	public List<Item> getAllItemList() {
		List<Item> listItem = entityManager.createQuery("SELECT i FROM Item i WHERE i.status<>'DELETE'").getResultList();
		return listItem;
	}

	@Transactional(readOnly = false)
	@Override
	public void hideItem(int itemId, boolean hide) {
		Optional<Item> obj = itemRepo.findById(itemId);
		if(obj.isPresent()) {
			if(hide) {
				obj.get().setStatus("HIDE");
			}
			else{
				obj.get().setStatus("ACTIVE");
			}
		}
	}

	@Transactional(readOnly = true)
	@Override
	public Page<SellingItemDto> getItemList(String text, int categoryId, int page) {
		PageRequest request = PageRequest.of(page-1, pageSize);
		//SELECT i FROM Item i LEFT JOIN FETCH i.sellingprices s where i.status<>'DELETE' GROUP BY s.item.itemId, s.unit.unitId ORDER BY s.priceId,s.item.itemId
		Page<Sell> items = itemRepo.getItemByCategoryIdAndSearch(text, categoryId, request);
		
		return items.map(sellItem->{
			Item m = sellItem.getItem();
			SellingItemDto sellingItem = new SellingItemDto(m);
			sellingItem.setSellingPrice(sellItem);
			if (m.getSellingprices().isEmpty()) {
				sellingItem.setSelling(false);
			} else {
				sellingItem.setSelling(true);
				sellingItem.setUnitId(sellItem.getUnit().getUnitId());
			}
			return sellingItem;
		});
		
	}

	@Override
	public Page<SellingItemDto> getUnlistedItemList(String text, int categoryId, int page) {
		PageRequest request = PageRequest.of(page-1, pageSize);
		//SELECT i FROM Item i LEFT JOIN FETCH i.sellingprices s where i.status<>'DELETE' GROUP BY s.item.itemId, s.unit.unitId ORDER BY s.priceId,s.item.itemId
		Page<Item> items = itemRepo.getUnlistedItemByCategoryIdAndSearch(text, categoryId, request);
		
		return items.map(sellItem->{
			Item m = sellItem;
			SellingItemDto sellingItem = new SellingItemDto(m);
			sellingItem.setSellingPrice(new Sell());
			//if (m.getSellingprices().isEmpty()) {
				sellingItem.setSelling(false);
			//} else {
			//	sellingItem.setSelling(true);
			//	sellingItem.setUnitId(sellItem.getUnit().getUnitId());
			//}
			return sellingItem;
		});
	}

}
