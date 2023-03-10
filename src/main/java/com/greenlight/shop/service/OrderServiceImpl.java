package com.greenlight.shop.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.greenlight.shop.domain.Item;
import com.greenlight.shop.domain.Orderitem;
import com.greenlight.shop.domain.Orderitemlist;
import com.greenlight.shop.domain.Sellingprice;
import com.greenlight.shop.domain.Township;
import com.greenlight.shop.domain.Unit;
import com.greenlight.shop.domain.Useraccount;
import com.greenlight.shop.dto.OrderDetail;
import com.greenlight.shop.dto.OrderDto;
import com.greenlight.shop.dto.OrderItemDto;
import com.greenlight.shop.repository.OrderItemListRepo;
import com.greenlight.shop.repository.OrderItemRepo;
import com.greenlight.shop.repository.SellingPriceRepo;
import com.greenlight.shop.util.IDGenerator;

@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	OrderItemRepo orderItemRepo;
	
	@Autowired
	OrderItemListRepo orderItemListRepo;
	
	@Autowired
	SellingPriceRepo sellingPriceRepo;
	
	@PersistenceContext
	EntityManager em;

	@Override
	public String order(OrderDto orderDto) {
		Orderitem order = new Orderitem();//orderItemRepo.getOne(m.getItemId());
		Useraccount userAccount = (Useraccount) SecurityContextHolder.getContext().getAuthentication().getDetails();
		List<Orderitemlist> listItem = orderDto.getListDto().stream().map(m -> {
			
			Orderitemlist orderItem = new Orderitemlist();
			
			orderItem.setOrderitem(order);
			//orderItem.setOrderitem(order);
			
			orderItem.setItemName(m.getItemNameMm());
			orderItem.setQty(m.getQty());
			
			
			Sellingprice sellPrice = sellingPriceRepo.getSellingPriceByItemAndUnitId(m.getItemId(), m.getUnitId());
			orderItem.setSellingprice(sellPrice);
			int unitPrice = sellPrice.getUnitPrice();
			orderItem.setUnitPrice(unitPrice);
			orderItem.setAmount(orderItem.getQty()*orderItem.getUnitPrice());
			
			
			//calcaulate total for all item
			order.setAmount(order.getAmount()+ orderItem.getAmount());
			
			
			return orderItem;
		}).collect(Collectors.toList());
		order.setStatus("ORDER");
		order.setUseraccount(userAccount);
		order.setAddress(orderDto.getAddress());
		order.setPhoneNo(orderDto.getPhoneNo());
		order.setPreferedTime(orderDto.getPreferedTime());
		order.setTownship(new Township(orderDto.getTownshipId()));
		order.setDiscount(0);
		order.setOrderDate(orderDto.getDate());
		order.setDeliveryFee(orderDto.getDeliveryCharge());
		order.setTotal(order.getAmount() + order.getDeliveryFee() - order.getDiscount());
		order.setDate(new Date());
		
		orderItemRepo.save(order);
		for(Orderitemlist item:listItem) {
			orderItemListRepo.save(item);
		}		
		return IDGenerator.generateId(order.getOrderItemId());
	}


	@Override
	public List<OrderDetail> getOrderByDate(Date fromDate, Date toDate) {
		List<OrderDetail> listOrder = new ArrayList<>();
		List<Object[]> listObj = orderItemRepo.getOrderDetailList(fromDate, toDate);
		for(Object[] arrObj: listObj) {
			//o.orderItemId, t.townshipName, o.address, o.phoneNo, o.preferedTime, o.orderDate , oi.itemName
			int orderItemId = (int) arrObj[0];
			String township = (String) arrObj[1];
			String address = (String) arrObj[2];
			String phoneNo = (String) arrObj[3];
			String time = (String)arrObj[4];
			Date orderDate = (Date)arrObj[5];
			String itemName = (String)arrObj[6];
			int price = (int) arrObj[7];
			int qty = (int) arrObj[8];
			int amount = (int) arrObj[9];
			String unitNames = (String) arrObj[10];
			//String[] arrPrice = prices.split(",");
			//String[] arrQty = qtys.split(",");
			//String[] arrAmount = amounts.split(",");
			String[] arrUnitName = unitNames.split(",");
			OrderDetail order = new OrderDetail(orderItemId, township, address, phoneNo, time, orderDate, itemName);
			order.setQty(qty);
			order.setAmount(amount);
			//order.set
			//order.setItemDetail(amounts + " : " + unitNames);
		}
		
		return listOrder;
	}


	@Override
	public List<Orderitem> getOrderListByDate(Date fromDate, Date toDate) {
		
		TypedQuery<Orderitem> query = em.createQuery("SELECT oi FROM Orderitem oi WHERE oi.orderDate BETWEEN :fromDate AND :toDate", Orderitem.class);
		return query.setParameter("fromDate", fromDate).setParameter("toDate", toDate).getResultList();
	}


	@Override
	public List<OrderDto> getOrderListByUserId(int userAccountId) {
		List<OrderDto> listOrderDto = new ArrayList<>();
		List<Orderitem> listOrder = orderItemRepo.getOrderListByUserId(userAccountId);
		for(Orderitem order:listOrder) {
			OrderDto orderDto = new OrderDto();
			orderDto.setAddress(order.getAddress());
			orderDto.setDate(order.getDate());
			//orderDto.setItemDetail(order);
			//orderDto.setListDto(listDto);
			orderDto.setPhoneNo(order.getPhoneNo());
			orderDto.setPreferedTime(order.getPreferedTime());
			orderDto.setTotal(order.getTotal());
			orderDto.setTownshipId(order.getTownship().getTownshipId());
			orderDto.setTownshipName(order.getTownship().getTownshipName());
			String encId = IDGenerator.generateId(order.getOrderItemId());
			if(order.getStatus().equals("ORDER")) {
				orderDto.setOrderId(encId + " - Pending");
			}else {
				orderDto.setOrderId(encId + " - "+ order.getStatus());
			}
			
			List<Orderitemlist> listitem = order.getOrderitemlists();
			List<OrderItemDto> listOrderItem = new ArrayList<OrderItemDto>();
			for(Orderitemlist orderItem: listitem) {
				OrderItemDto orderItemDto = new OrderItemDto();
				Sellingprice sp = orderItem.getSellingprice();
				Item item = sp.getItem();
				orderItemDto.setBackImg(item.getBackImg());
				orderItemDto.setCurrency("MMK");
				orderItemDto.setDate(order.getDate());
				orderItemDto.setFrontImg(item.getFrontImg());
				orderItemDto.setItemId(item.getItemId());
				orderItemDto.setItemName(orderItem.getItemName());
				orderItemDto.setPrice(orderItem.getUnitPrice());
				orderItemDto.setQty(orderItem.getQty());
				Unit unit = sp.getUnit();
				orderItemDto.setUnitId(unit.getUnitId());
				orderItemDto.setUnitName(unit.getUnitName());
				listOrderItem.add(orderItemDto);
			}
			orderDto.setListDto(listOrderItem);
			listOrderDto.add(orderDto);
		}
		
		return listOrderDto;
	}


	@Transactional(readOnly = false)
	@Override
	public void changeStatus(int orderItemId, String status) {
		Orderitem item = orderItemRepo.getOne(orderItemId);
		item.setStatus(status);
	}


	@Transactional(readOnly = true)
	@Override
	public Orderitem getOrderById(int orderId) {
		Orderitem item = orderItemRepo.getOne(orderId);
		return item;
	}

}
