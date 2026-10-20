package com.ecommerce.service;

import com.ecommerce.dao.*;
import com.ecommerce.exception.*;
import com.ecommerce.model.*;
import java.util.List;

public class ProductService {
	private ProductDAO productDAO;

	public ProductService() {
		productDAO = new ProductDAO();
	}

	public boolean addProduct(Product product) throws ProductException {
		validateProduct(product);

		boolean result = productDAO.addProduct(product);

		if (!result) {
			throw new ProductException("Unable to create the product");
		}
		return true;
	}

	private void validateProduct(Product product) throws ProductException {
		if (product == null) {
			throw new ProductException("Product cannot be null!");
		}
		if (product.getProductName() == null || product.getProductName().trim().isEmpty()) {
			throw new ProductException("Product name cannot be empty!");
		}
		if (product.getPrice() <= 0) {
			throw new ProductException("Product price must be greater than 0");
		}
		if (product.getStockQuantity() < 0) {
			throw new ProductException("Stock quantity cannot be negative");
		}
	}

	// service for get product by id
	public Product getProductById(int productId) throws ProductException {
		if (productId < 0) {
			throw new ProductException("Invalid Product Id");
		}
		Product product = productDAO.getProductById(productId);

		if (product == null) {
			throw new ProductException("Product not found with Id: " + productId);
		}
		return product;
	}

	// get all products
	public List<Product> getAllProducts() {
		return productDAO.getAllProducts();
	}

	// update the product
	public boolean updateProduct(Product product) throws ProductException {
		validateProduct(product);

		Product existingProduct = productDAO.getProductById(product.getProductId());

		if (existingProduct == null) {
			throw new ProductException("Product does not exist:(");
		}
		boolean result = productDAO.updateProduct(product);

		if (!result) {
			throw new ProductException("Unable to update product");
		}
		return true;
	}

	// delete product
	public boolean deleteProduct(int productId) throws ProductException {
		if (productId < 0) {
			throw new ProductException("Invalid Product ID");
		}
		Product existingProduct = productDAO.getProductById(productId);
		if (existingProduct == null) {
			throw new ProductException("Product does not exist:(");
		}
		boolean result = productDAO.deleteProduct(productId);

		if (!result) {
			throw new ProductException("Unable to delete the product");
		}
		return true;
	}
}
