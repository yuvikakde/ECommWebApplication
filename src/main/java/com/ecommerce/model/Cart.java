package com.ecommerce.model;

import java.util.ArrayList;
import java.util.List;

public class Cart {
	private int cartId;
	private int userId;
	private List<CartItem> items;
	
	public Cart() {
		items = new ArrayList<>();
		
	}

	public Cart(int cartId, int userId) {
		super();
		this.cartId = cartId;
		this.userId = userId;
		this.items = new ArrayList<>();
	}

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public List<CartItem> getItems() {
		return items;
	}

	public void setItems(List<CartItem> items) {
		this.items = items;
	}
	
	public void addItem(CartItem item) {
		items.add(item);
	}
	public void removeItem(CartItem item) {
		items.remove(item);
	}
	public double calculateTotal() {
		double total =0;
		for(CartItem item:items) {
			total += item.getProduct().getPrice()* item.getQuantity();
		}
		return total;
	}

	@Override
	public String toString() {
		return "Cart [cartId=" + cartId + ", userId=" + userId + ", items=" + items + "]";
	}
	
}
