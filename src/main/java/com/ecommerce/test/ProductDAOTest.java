package com.ecommerce.test;

import com.ecommerce.model.*;
import com.ecommerce.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Scanner;

import com.ecommerce.dao.*;

public class ProductDAOTest {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		Product product = new Product();

		ProductDAO productDAO = new ProductDAO();

		System.out.println("Please select option 1.Create Product\n 2.Fetch Product by id\n 3.Get All Products");

		int choice = sc.nextInt();

		switch (choice) {

		case 1:

			product.setCategoryId(3);
			product.setProductName("Lenovo IdeaPad");
			product.setDescription("15 inch laptop");
			product.setPrice(12314.11);
			product.setStockQuantity(20);
			product.setImageURL("https://rukminim3.flixcart.com/image/480/640/xif0q/computer/o/t/j/-original-imahg5fxwuwbrzff.jpeg?q=90");
			product.setStatus("ACTIVE");

			boolean result = productDAO.addProduct(product);

			if(result) {
				System.out.println("Product inserted succesfully!");
			}
			else {
				System.out.println("Product insertion failed:(");
			}

			break;

		case 2:

			Product product2 = productDAO.getProductById(10);

			if(product2 != null) {

				System.out.println("Product Found");

				System.out.println("Id: " + product2.getProductId());

				System.out.println("Category Id: " + product2.getCategoryId());

				System.out.println("Product Name: " + product2.getProductName());

				System.out.println("Description: " + product2.getDescription());

				System.out.println("Price: " + product2.getPrice());

				System.out.println("Stock Quantity: " + product2.getStockQuantity());

				System.out.println("Image URL: " + product2.getImageURL());

				System.out.println("Status: " + product2.getStatus());
			}
			else {
				System.out.println("Product not found:(");
			}

			break;
			
			
		case 3:
			System.out.println("\n------------All Products-------------");
			List<Product> products = productDAO.getAllProducts();
			
			for(Product product3 : products) {
				System.out.println(product3.getCategoryId()
						+" | "
						+product3.getProductName()
						+" | Rs. "
						+product3.getPrice()
						+" | Stock: "
						+product3.getStockQuantity());
			}
			break; 
			default:
				System.out.println("Invalid choice");
		}
	}
}