package com.ecommerce.model;

public class CartItem {
	private int cardItemId;
	private int cartId;
	private Product product;
	private int quantity;
	
	
	public CartItem() {
		super();
	}


	public CartItem(int cardItemId, int cartId, Product product, int quantity) {
		super();
		this.cardItemId = cardItemId;
		this.cartId = cartId;
		this.product = product;
		this.quantity = quantity;
	}


	public int getCardItemId() {
		return cardItemId;
	}


	public void setCardItemId(int cardItemId) {
		this.cardItemId = cardItemId;
	}


	public int getCartId() {
		return cartId;
	}


	public void setCartId(int cartId) {
		this.cartId = cartId;
	}


	public Product getProduct() {
		return product;
	}


	public void setProduct(Product product) {
		this.product = product;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public double getSubTotal() {
		if (product == null) {
			return 0 ;
		}
		
		return product.getPrice() * quantity;
	}


	@Override
	public String toString() {
		return "CartItem [cardItemId=" + cardItemId + ", cartId=" + cartId + ", product=" + product + ", quantity="
				+ quantity + "]";
	}
	
	
	
}