<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.ecommerce.model.Product" %>
<%
    Product product = (Product) request.getAttribute("product");
    boolean isEdit = (product != null && product.getProductId() > 0);
    String errorMessage = (String) request.getAttribute("errorMessage");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><%= isEdit ? "Edit Product" : "Add Product" %> - Mini E-Commerce</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8fafc;
            font-family: 'Segoe UI', system-ui, -apple-system, sans-serif;
        }
        .form-card {
            border: none;
            border-radius: 14px;
            box-shadow: 0 10px 25px -5px rgba(0, 0, 0, 0.05), 0 8px 10px -6px rgba(0, 0, 0, 0.04);
            background: #ffffff;
        }
        .form-label {
            font-weight: 600;
            color: #334155;
            font-size: 0.9rem;
        }
    </style>
</head>
<body>
    <%-- Navbar --%>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark mb-4 shadow-sm">
        <div class="container">
            <a class="navbar-brand text-primary fw-bold" href="<%= request.getContextPath() %>/">
                <i class="bi bi-cart3 me-2"></i>Mini E-Commerce
            </a>
            <div class="navbar-nav ms-auto">
                <a class="nav-link text-white" href="<%= request.getContextPath() %>/"><i class="bi bi-house me-1"></i>Home</a>
                <a class="nav-link text-white active fw-semibold" href="<%= request.getContextPath() %>/products"><i class="bi bi-box-seam me-1"></i>Products</a>
            </div>
        </div>
    </nav>

    <div class="container pb-5">
        <div class="row justify-content-center">
            <div class="col-lg-8 col-md-10">

                <%-- Breadcrumb --%>
                <nav aria-label="breadcrumb" class="mb-3">
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item"><a href="<%= request.getContextPath() %>/products">Products</a></li>
                        <li class="breadcrumb-item active" aria-current="page"><%= isEdit ? "Edit Product" : "New Product" %></li>
                    </ol>
                </nav>

                <%-- Error Notification --%>
                <% if (errorMessage != null && !errorMessage.trim().isEmpty()) { %>
                    <div class="alert alert-danger alert-dismissible fade show shadow-sm mb-4" role="alert">
                        <i class="bi bi-exclamation-triangle-fill me-2"></i>
                        <strong>Error:</strong> <%= errorMessage %>
                        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                    </div>
                <% } %>

                <%-- Form Card --%>
                <div class="form-card p-4 p-md-5">
                    <div class="d-flex align-items-center mb-4 pb-3 border-bottom">
                        <div class="me-3 bg-primary bg-opacity-10 p-3 rounded-circle text-primary">
                            <i class="bi <%= isEdit ? "bi-pencil-square" : "bi-plus-circle" %> fs-3"></i>
                        </div>
                        <div>
                            <h3 class="fw-bold mb-1 text-dark">
                                <%= isEdit ? "Edit Product #" + product.getProductId() : "Add New Product" %>
                            </h3>
                            <p class="text-muted mb-0 small">
                                <%= isEdit ? "Update existing product details in the database" : "Fill out the form below to create a new product" %>
                            </p>
                        </div>
                    </div>

                    <form action="<%= request.getContextPath() %>/products?action=<%= isEdit ? "update" : "insert" %>" method="post">
                        <% if (isEdit) { %>
                            <input type="hidden" name="productId" value="<%= product.getProductId() %>">
                        <% } %>

                        <div class="row g-3">
                            <%-- Product Name --%>
                            <div class="col-md-8">
                                <label for="productName" class="form-label">Product Name <span class="text-danger">*</span></label>
                                <input type="text" class="form-control form-control-lg fs-6" id="productName" name="productName"
                                       placeholder="e.g. Dell XPS 15" required
                                       value="<%= (product != null && product.getProductName() != null) ? product.getProductName() : "" %>">
                            </div>

                            <%-- Category ID --%>
                            <div class="col-md-4">
                                <label for="categoryId" class="form-label">Category ID</label>
                                <input type="number" class="form-control form-control-lg fs-6" id="categoryId" name="categoryId" min="1"
                                       value="<%= (product != null && product.getCategoryId() > 0) ? product.getCategoryId() : 1 %>">
                            </div>

                            <%-- Price --%>
                            <div class="col-md-6">
                                <label for="price" class="form-label">Price (&#8377;) <span class="text-danger">*</span></label>
                                <div class="input-group">
                                    <span class="input-group-text">&#8377;</span>
                                    <input type="number" class="form-control form-control-lg fs-6" id="price" name="price"
                                           placeholder="0.00" step="0.01" min="0.01" required
                                           value="<%= (product != null && product.getPrice() > 0) ? String.valueOf(product.getPrice()) : "" %>">
                                </div>
                            </div>

                            <%-- Stock Quantity --%>
                            <div class="col-md-6">
                                <label for="stockQuantity" class="form-label">Stock Quantity <span class="text-danger">*</span></label>
                                <input type="number" class="form-control form-control-lg fs-6" id="stockQuantity" name="stockQuantity"
                                       placeholder="0" min="0" required
                                       value="<%= (product != null) ? String.valueOf(product.getStockQuantity()) : "10" %>">
                            </div>

                            <%-- Status --%>
                            <div class="col-md-6">
                                <label for="status" class="form-label">Status</label>
                                <select class="form-select form-select-lg fs-6" id="status" name="status">
                                    <option value="ACTIVE" <%= (product == null || "ACTIVE".equalsIgnoreCase(product.getStatus())) ? "selected" : "" %>>ACTIVE</option>
                                    <option value="INACTIVE" <%= (product != null && "INACTIVE".equalsIgnoreCase(product.getStatus())) ? "selected" : "" %>>INACTIVE</option>
                                </select>
                            </div>

                            <%-- Image URL --%>
                            <div class="col-md-6">
                                <label for="imageUrl" class="form-label">Image URL</label>
                                <input type="text" class="form-control form-control-lg fs-6" id="imageUrl" name="imageUrl"
                                       placeholder="https://example.com/image.jpg"
                                       value="<%= (product != null && product.getImageUrl() != null) ? product.getImageUrl() : "" %>">
                            </div>

                            <%-- Description --%>
                            <div class="col-12">
                                <label for="description" class="form-label">Description</label>
                                <textarea class="form-control" id="description" name="description" rows="3"
                                          placeholder="Enter brief product details..."><%= (product != null && product.getDescription() != null) ? product.getDescription() : "" %></textarea>
                            </div>

                            <%-- Action Buttons --%>
                            <div class="col-12 pt-3 border-top mt-4 d-flex justify-content-end gap-2">
                                <a href="<%= request.getContextPath() %>/products" class="btn btn-outline-secondary px-4 py-2 rounded-pill">
                                    Cancel
                                </a>
                                <button type="submit" class="btn btn-primary px-4 py-2 rounded-pill shadow-sm">
                                    <i class="bi <%= isEdit ? "bi-check2-circle" : "bi-plus-circle" %> me-1"></i>
                                    <%= isEdit ? "Update Product" : "Save Product" %>
                                </button>
                            </div>
                        </div>
                    </form>
                </div>

            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
