package com.ecommerce.test;

import com.ecommerce.exception.*;
import com.ecommerce.service.*;
import com.ecommerce.model.*;
import com.ecommerce.dao.*;

public class ProductServiceTest {
	public static void main(String[]args) {

		ProductService productService = new ProductService();

		Product product = new Product();

		product.setCategoryId(3);
		product.setProductName("Acer Aspire 5");
		product.setDescription("Powerful laptop");
		product.setPrice(44444);
		product.setStockQuantity(11);
		product.setImageUrl("https://picsum.photos/420/102");
		product.setStatus("ACTIVE");

		try {
			boolean result = productService.addProduct(product);
			if(result) {
				System.out.println("Product Added successfully");
			}
		}catch (Exception e) {
			System.out.println("Error : "+e.getMessage());
		}

		//delete the product
		try {
			boolean result = productService.deleteProduct(1);
			if(result) {
				System.out.println("Product Deleted successfully");
			}
		}catch (Exception e) {
			System.out.println("Error : "+e.getMessage());
		}
	}
}