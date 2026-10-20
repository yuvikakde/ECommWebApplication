<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
//directive tag      
<%@ page import="java.util.List" %>
<%@ page import="com.ecommerce.model.Product" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Product Management</title>
<link 
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" 
	rel="stylesheet">
	
</head>
<body class="bg-light"> 
	<h1>Product Management here</h1>
	<%//scriptlet tag %
	List<Product> products = (List<Product>)request.getAttribute("products");
	%>
	
	<table border="1">
		<tr>
			<th>Sr No</th>
			<th>Name</th>
			<th>Price</th>
			<th>Description</th>
			<th>Stock</th>
			<th>Status</th>
		</tr>
		
		<%
			for(Product product : products){
		%>
		
			<tr>
				<td>
					<%= product.getProductId() %>
				</td>
				<td>
					<%= product.getProductName() %>
				</td>
				<td>
					<%= product.getDescription() %>
				</td>
				<td>
					<%= product.getStockQuantity() %>
				</td>
				<td>
					<%= product.getStatus() %>
				</td>
			</tr>
			<%
			
			%>
			
	</table>
</body>
</html>