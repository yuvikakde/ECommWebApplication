package com.ecommerce.model;

import java.util.ArrayList;
import java.util.List;

public class Order {
	private int orderId;
	private int userId;
	private double totalAmount;
	private String orderStatus;
	private String paymentMethod;
	private String shippingAddress;
	private String city;
	private String state;
	private String pincode;
	private List<OrderItem> items;
	
	
	public Order() {
		super();
		items = new ArrayList<>();
	}


	public Order(int orderId, int userId, double totalAmount, String orderStatus, String paymentMethod,
			String shippingAddress, String city, String state, String pincode) {
		super();
		this.orderId = orderId;
		this.userId = userId;
		this.totalAmount = totalAmount;
		this.orderStatus = orderStatus;
		this.paymentMethod = paymentMethod;
		this.shippingAddress = shippingAddress;
		this.city = city;
		this.state = state;
		this.pincode = pincode;
		
		this.items = new ArrayList<>();
	
	}


	public int getOrderId() {
		return orderId;
	}


	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}


	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	public double getTotalAmount() {
		return totalAmount;
	}


	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}


	public String getOrderStatus() {
		return orderStatus;
	}


	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}


	public String getPaymentMethod() {
		return paymentMethod;
	}


	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}


	public String getShippingAddress() {
		return shippingAddress;
	}


	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public String getPincode() {
		return pincode;
	}


	public void setPincode(String pincode) {
		this.pincode = pincode;
	}


	public List<OrderItem> getItems() {
		return items;
	}


	public void setItems(List<OrderItem> items) {
		this.items = items;
	}

	
	public void addItem(OrderItem item) {
		items.add(item);
	}
	
	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", userId=" + userId + ", totalAmount=" + totalAmount + ", orderStatus="
				+ orderStatus + ", paymentMethod=" + paymentMethod + ", shippingAddress=" + shippingAddress + ", city="
				+ city + ", state=" + state + ", pincode=" + pincode + ", items=" + items + "]";
	}
	
}