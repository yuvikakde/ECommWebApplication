package com.ecommerce.test;
import com.ecommerce.model.Product;
public class ModelTest {
	public static void main(String[] args) {
		Product product = new Product();
		
		product.setProductId(1);
		product.setCategoryId(1);
		product.setProductName("Dell inspiron 15");
		product.setDescription("15inch Screen");
		product.setPrice(55555.00);
		product.setStockQuantity(20);
		product.setStatus("ACTIVE");
		
		System.out.println("ProductID: "+product.getProductId());
		System.out.println("Product Name: "+product.getProductName());
		System.out.println("Product Price: "+product.getPrice());
		System.out.println("Product Stock: "+product.getStockQuantity());
		System.out.println("\nCompleteProduct: ");
		System.out.println(product);
	}
}
