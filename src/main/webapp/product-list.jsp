<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.ecommerce.model.Product" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Product Management - Mini E-Commerce</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8fafc;
            font-family: 'Segoe UI', system-ui, -apple-system, sans-serif;
        }
        .navbar-brand {
            font-weight: 700;
            letter-spacing: -0.5px;
        }
        .card {
            border: none;
            box-shadow: 0 4px 6px -1px rgba(0, 0, 0, 0.05), 0 2px 4px -2px rgba(0, 0, 0, 0.05);
            border-radius: 12px;
        }
        .table > :not(caption) > * > * {
            padding: 1rem 1rem;
            vertical-align: middle;
        }
        .badge-active {
            background-color: #dcfce7;
            color: #15803d;
            font-weight: 600;
            padding: 0.35rem 0.65rem;
            border-radius: 20px;
            font-size: 0.78rem;
        }
        .badge-inactive {
            background-color: #fee2e2;
            color: #b91c1c;
            font-weight: 600;
            padding: 0.35rem 0.65rem;
            border-radius: 20px;
            font-size: 0.78rem;
        }
        .stock-pill {
            font-size: 0.85rem;
            font-weight: 500;
        }
        .btn-action {
            display: inline-flex;
            align-items: center;
            gap: 0.25rem;
            border-radius: 8px;
            font-size: 0.85rem;
            padding: 0.35rem 0.65rem;
        }
    </style>
</head>
<body>
    <%-- Navbar --%>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark mb-4 shadow-sm">
        <div class="container">
            <a class="navbar-brand text-primary" href="<%= request.getContextPath() %>/">
                <i class="bi bi-cart3 me-2"></i>Mini E-Commerce
            </a>
            <div class="navbar-nav ms-auto">
                <a class="nav-link text-white" href="<%= request.getContextPath() %>/"><i class="bi bi-house me-1"></i>Home</a>
                <a class="nav-link text-white active fw-semibold" href="<%= request.getContextPath() %>/products"><i class="bi bi-box-seam me-1"></i>Products</a>
            </div>
        </div>
    </nav>

    <div class="container pb-5">
        <%
            String msg = request.getParameter("msg");
            String error = request.getParameter("error");
            List<Product> products = (List<Product>) request.getAttribute("products");
            int productCount = (products != null) ? products.size() : 0;
        %>

        <%-- Feedback Messages --%>
        <% if ("added".equals(msg)) { %>
            <div class="alert alert-success alert-dismissible fade show shadow-sm mb-4" role="alert">
                <i class="bi bi-check-circle-fill me-2"></i>
                <strong>Success!</strong> Product was added successfully.
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
        <% } else if ("updated".equals(msg)) { %>
            <div class="alert alert-info alert-dismissible fade show shadow-sm mb-4" role="alert">
                <i class="bi bi-info-circle-fill me-2"></i>
                <strong>Updated!</strong> Product details were updated successfully.
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
        <% } else if ("deleted".equals(msg)) { %>
            <div class="alert alert-warning alert-dismissible fade show shadow-sm mb-4" role="alert">
                <i class="bi bi-trash-fill me-2"></i>
                <strong>Deleted!</strong> Product was removed from the inventory.
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
        <% } else if (error != null && !error.trim().isEmpty()) { %>
            <div class="alert alert-danger alert-dismissible fade show shadow-sm mb-4" role="alert">
                <i class="bi bi-exclamation-triangle-fill me-2"></i>
                <strong>Error:</strong> <%= error %>
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
        <% } %>

        <%-- Header Section --%>
        <div class="d-flex flex-column flex-sm-row justify-content-between align-items-sm-center gap-3 mb-4">
            <div>
                <h2 class="fw-bold mb-1 text-dark">Product Management</h2>
                <p class="text-muted mb-0">Manage and view all products in your inventory</p>
            </div>
            <div class="d-flex align-items-center gap-2">
                <span class="badge bg-secondary fs-7 px-3 py-2 rounded-pill">
                    Total: <%= productCount %>
                </span>
                <a href="<%= request.getContextPath() %>/products?action=new" class="btn btn-primary px-3 py-2 rounded-pill shadow-sm d-inline-flex align-items-center gap-2">
                    <i class="bi bi-plus-circle"></i>
                    <span>Add New Product</span>
                </a>
            </div>
        </div>

        <%-- Content Card --%>
        <div class="card overflow-hidden">
            <div class="table-responsive">
                <table class="table table-hover align-middle mb-0">
                    <thead class="table-light border-bottom">
                        <tr class="text-secondary text-uppercase fs-7 fw-semibold">
                            <th scope="col" style="width: 70px;">ID</th>
                            <th scope="col">Product Name</th>
                            <th scope="col">Price</th>
                            <th scope="col">Description</th>
                            <th scope="col" class="text-center">Stock</th>
                            <th scope="col" class="text-center">Status</th>
                            <th scope="col" class="text-end pe-4" style="width: 170px;">Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            if (products != null && !products.isEmpty()) {
                                for (Product product : products) {
                                    boolean isActive = "ACTIVE".equalsIgnoreCase(product.getStatus());
                        %>
                        <tr>
                            <td class="fw-bold text-secondary">
                                #<%= product.getProductId() %>
                            </td>
                            <td>
                                <span class="fw-semibold text-dark"><%= product.getProductName() != null ? product.getProductName() : "N/A" %></span>
                            </td>
                            <td>
                                <span class="fw-bold text-success">
                                    &#8377; <%= String.format("%,.2f", product.getPrice()) %>
                                </span>
                            </td>
                            <td class="text-muted" style="max-width: 250px;">
                                <div class="text-truncate">
                                    <%= product.getDescription() != null && !product.getDescription().isEmpty() ? product.getDescription() : "-" %>
                                </div>
                            </td>
                            <td class="text-center">
                                <% if (product.getStockQuantity() > 10) { %>
                                    <span class="badge bg-success-subtle text-success border border-success-subtle stock-pill px-2.5 py-1">
                                        <%= product.getStockQuantity() %> in stock
                                    </span>
                                <% } else if (product.getStockQuantity() > 0) { %>
                                    <span class="badge bg-warning-subtle text-warning-emphasis border border-warning-subtle stock-pill px-2.5 py-1">
                                        <%= product.getStockQuantity() %> low stock
                                    </span>
                                <% } else { %>
                                    <span class="badge bg-danger-subtle text-danger border border-danger-subtle stock-pill px-2.5 py-1">
                                        Out of stock
                                    </span>
                                <% } %>
                            </td>
                            <td class="text-center">
                                <% if (isActive) { %>
                                    <span class="badge-active">ACTIVE</span>
                                <% } else { %>
                                    <span class="badge-inactive"><%= product.getStatus() != null ? product.getStatus() : "INACTIVE" %></span>
                                <% } %>
                            </td>
                            <td class="text-end pe-4">
                                <a href="<%= request.getContextPath() %>/products?action=edit&id=<%= product.getProductId() %>"
                                   class="btn btn-outline-primary btn-action" title="Edit Product">
                                    <i class="bi bi-pencil-square"></i>
                                    <span>Edit</span>
                                </a>
                                <a href="<%= request.getContextPath() %>/products?action=delete&id=<%= product.getProductId() %>"
                                   class="btn btn-outline-danger btn-action" title="Delete Product"
                                   onclick="return confirm('Are you sure you want to delete \'<%= product.getProductName() != null ? product.getProductName().replace("'", "\\'") : "" %>\'?');">
                                    <i class="bi bi-trash3"></i>
                                    <span>Delete</span>
                                </a>
                            </td>
                        </tr>
                        <%
                                }
                            } else {
                        %>
                        <tr>
                            <td colspan="7" class="text-center py-5">
                                <div class="text-muted">
                                    <i class="bi bi-inbox fs-1 d-block mb-2 text-secondary"></i>
                                    <h5>No products found</h5>
                                    <p class="small text-muted mb-3">No products are currently available in the database.</p>
                                    <a href="<%= request.getContextPath() %>/products?action=new" class="btn btn-sm btn-primary rounded-pill px-3">
                                        <i class="bi bi-plus-circle me-1"></i> Add First Product
                                    </a>
                                </div>
                            </td>
                        </tr>
                        <%
                            }
                        %>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>