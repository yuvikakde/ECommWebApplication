package com.ecommerce.dao;

import java.sql.*;
import java.util.*;

import com.ecommerce.model.*;
import com.ecommerce.util.DBConnection;

public class ProductDAO {

	public static void main(String[] args) {

	}

	public boolean addProduct(Product product) {

		String sql = "INSERT INTO ecommproducts "
				+ "(category_id, product_name, description,"
				+ "price, stock_quantity, image_url,status)"
				+ "VALUES(?,?,?,?,?,?,?)";

		try (Connection connection = DBConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {

			statement.setInt(1, product.getCategoryId());
			statement.setString(2, product.getProductName());
			statement.setString(3, product.getDescription());
			statement.setDouble(4, product.getPrice());
			statement.setInt(5, product.getStockQuantity());
			statement.setString(6, product.getImageUrl());
			statement.setString(7, product.getStatus());

			int rows = statement.executeUpdate();

			return rows > 0;

		} catch (Exception e) {

			e.printStackTrace();
			return false;
		}
	}

	// Fetch product by id
	public Product getProductById(int productId) {

		String sql = "select * from ecommproducts where product_id = ?";

		try (Connection connection = DBConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {

			statement.setInt(1, productId);

			try (ResultSet resultSet = statement.executeQuery()) {

				if (resultSet.next()) {

					Product product = new Product();

					product.setProductId(resultSet.getInt("product_id"));

					product.setCategoryId(resultSet.getInt("category_id"));

					product.setProductName(resultSet.getString("product_name"));

					product.setDescription(resultSet.getString("description"));

					product.setPrice(resultSet.getDouble("price"));

					product.setStockQuantity(resultSet.getInt("stock_quantity"));

					product.setImageUrl(resultSet.getString("image_url"));

					product.setStatus(resultSet.getString("status"));

					return product;
				}
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return null;
	}

	// Fetch all products
	public List<Product> getAllProducts() {
		List<Product> products = new ArrayList<>();

		String sql = "select * from ecommproducts order by product_id asc";

		try (Connection connection = DBConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql);
				ResultSet resultSet = statement.executeQuery();) {
			while (resultSet.next()) {
				Product product = new Product();
				product.setProductId(resultSet.getInt("product_id"));

				product.setCategoryId(resultSet.getInt("category_id"));

				product.setProductName(resultSet.getString("product_name"));

				product.setDescription(resultSet.getString("description"));

				product.setPrice(resultSet.getDouble("price"));

				product.setStockQuantity(resultSet.getInt("stock_quantity"));

				product.setImageUrl(resultSet.getString("image_url"));

				product.setStatus(resultSet.getString("status"));

				products.add(product);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return products;
	}

	// update product
	public boolean updateProduct(Product product) {
		String sql = "UPDATE ecommproducts SET "
				+ "category_id=?, product_name=?, description=?, "
				+ "price=?, stock_quantity=?, image_url=?, status=? "
				+ "WHERE product_id=?";
		try (Connection connection = DBConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setInt(1, product.getCategoryId());
			statement.setString(2, product.getProductName());
			statement.setString(3, product.getDescription());
			statement.setDouble(4, product.getPrice());
			statement.setInt(5, product.getStockQuantity());
			statement.setString(6, product.getImageUrl());
			statement.setString(7, product.getStatus());
			statement.setInt(8, product.getProductId());
			return statement.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// delete product
	public boolean deleteProduct(int productId) {
		String sql = "DELETE FROM ecommproducts WHERE product_id = ?";
		try (Connection connection = DBConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setInt(1, productId);
			return statement.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
