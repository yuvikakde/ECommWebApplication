package com.ecommerce.test;

import java.sql.*;

import com.ecommerce.util.*;

public class ProductJDBCTest {

	public static void main(String[] args) {

		String sql = "select * from ecommproducts";

		try (Connection connection = DBConnection.getConnection();

				Statement statement = connection.createStatement();

				ResultSet resultSet = statement.executeQuery(sql)) {

			System.out.println("---------PRODUCTS---------");

			while(resultSet.next()) {

				int productID = resultSet.getInt("product_id");

				String productName = resultSet.getString("product_name");

				double productPrice = resultSet.getDouble("price");

				int stock = resultSet.getInt("stock_quantity");

				System.out.println(productID + " | " + productName + " | "	+ productPrice + " | " + stock);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
	}
}