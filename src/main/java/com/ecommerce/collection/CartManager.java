package com.ecommerce.collection;
import java.util.Collections;
import java.util.*;
import com.ecommerce.model.CartItem;

public class CartManager {
 private Map<Integer,CartItem> cartItems;
 
 public CartManager() {
	 cartItems = new HashMap<>();
 }
 //add item in cart
 public void addItem(CartItem item) {
	 int  productId = item.getProduct().getProductId();
	 
	 if(cartItems.containsKey(productId)) {
		  CartItem existingItem = cartItems.get(productId);
		  
		  existingItem.setQuantity(existingItem.getQuantity()+ item.getQuantity());
	 }
	 else {
		 cartItems.put(productId, item);
	 }
 }
 public void updateQuantity(int productId, int quantity) {
	 if(cartItems.containsKey(productId)) {
		 cartItems.get(productId).setQuantity(quantity); 
	 }
 }
 //remove item from cart
 public void removeItem(int productId) {
	 if(cartItems.containsKey(productId)) {
		 cartItems.remove(productId); 
	 }
 }
 
 public Collection<CartItem>getItems(){
	 return cartItems.values();
 }
 
 public double calculateTotal() {
	 double total = 0;
	 for(CartItem item : cartItems.values()) {
		 total += item.getSubTotal();
	 }
	 return total;
 }
 
 public int getCartSize() {
	 return cartItems.size();
 }
}
