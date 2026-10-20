package com.ecommerce.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import com.ecommerce.model.Product;

public class ProductCollectionDemo {
	
	// Search product by Name
	
		public static Product searchByName(List<Product> products, String searchText) {
			
			for (Product product : products) {
			    if (product.getProductName()
			            .toLowerCase()
			            .contains(searchText.toLowerCase())) {
			        return product;
			    }
			}	
			return null;
	}
		// Filter products by category
		public static List<Product> filterByCategory(
		        List<Product> products, int categoryId) {

		    List<Product> filteredProducts = new ArrayList<>();

		    for (Product product : products) {

		        if (product.getCategoryId() == categoryId) {
		            filteredProducts.add(product);
		        }
		    }

		    return filteredProducts;
		}
		
	public static void main(String[] args) {
		
		List<Product> products = new ArrayList<>();
		
		Product p1 = new Product();
		Product p2 = new Product();
		Product p3 = new Product();
		Product p4 = new Product();
		Product p5 = new Product();
		
		p1.setProductId(1);
		p1.setCategoryId(3);
		p1.setProductName("Dell Inspiron 15");
		p1.setDescription("15 inch laptop with Intel Core i5 processor");
		p1.setPrice(55555.00);
		p1.setStockQuantity(20);
		p1.setStatus("ACTIVE");

		p2.setProductId(2);
		p2.setCategoryId(2);
		p2.setProductName("Samsung Galaxy S25");
		p2.setDescription("Samsung smartphone with advanced camera");
		p2.setPrice(74999.00);
		p2.setStockQuantity(15);
		p2.setStatus("ACTIVE");
		
		p3.setProductId(3);
		p3.setCategoryId(1);
		p3.setProductName("Sony Headphones");
		p3.setDescription("Wireless noise cancelling headphones");
		p3.setPrice(4999.00);
		p3.setStockQuantity(25);
		p3.setStatus("ACTIVE");

		p4.setProductId(4);
		p4.setCategoryId(4);
		p4.setProductName("Logitech Wireless Mouse");
		p4.setDescription("Wireless mouse for laptop and desktop");
		p4.setPrice(1299.00);
		p4.setStockQuantity(50);
		p4.setStatus("ACTIVE");

		p5.setProductId(5);
		p5.setCategoryId(5);
		p5.setProductName("Nike Sports T-Shirt");
		p5.setDescription("Comfortable cotton sports t-shirt");
		p5.setPrice(1999.00);
		p5.setStockQuantity(30);
		p5.setStatus("ACTIVE");
		 
		products.add(p1);
		products.add(p2);
		products.add(p3);
		products.add(p4);
		products.add(p5);
		
		System.out.println("-----Product List -----");
		for(Product product: products) {
			System.out.println(
					product.getProductId()
					+ " | " 
					+ product.getProductName()
					+ " | Rs."
					+ product.getPrice()
					
					);
		}
		
		Product result = searchByName(products,"Dell");
		
		if(result != null) {
			System.out.println("\nProduct Found:  " + result.getProductName());
		}
		else {
			System.out.println("Product Not Found");
		}
		
		// Filter by category call
		List<Product> laptopProducts = filterByCategory(products, 3);

		System.out.println("\n----- Laptops -----");

		for (Product product : laptopProducts) {
		    System.out.println(product.getProductName() + " | Rs. " + product.getPrice());
		}
		
		// Sort By name
		Collections.sort(products);
		
		System.out.println("\n----- Sorting by name-----");
		for (Product product : products) {
		    System.out.println(product.getProductName());
		}
		
		// Sort By price low to high
		products.sort(Comparator.comparingDouble(Product::getPrice));

		System.out.println("\n----- Sorting by Price LOW to High -----");

		for (Product product : products) {
		    System.out.println(
		            product.getProductName()
		            + " | Rs."
		            + product.getPrice()
		    );
		}


		// Sort By price high to low
		products.sort(Comparator.comparingDouble(Product::getPrice).reversed());

		System.out.println("\n----- Sorting by Price HIGH TO LOW -----");

		for (Product product : products) {
		    System.out.println(
		            product.getProductName()
		            + " | Rs."
		            + product.getPrice()
		    );
		}
		
		//hashMap
		Map<Integer, Product>productMap = new HashMap<>();
		
		//AddProduct   very fast process for product add
		for(Product product : products) {
			productMap.put(product.getProductId(), product);
			
		}
		Product product = productMap.get(4);
		System.out.println("\nProduct ID "+product.getProductId()+": "+product.getProductName());
		}
		
}