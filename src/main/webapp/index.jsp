<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Mini E-Commerce Application</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8fafc;
            font-family: 'Segoe UI', system-ui, -apple-system, sans-serif;
            min-height: 100vh;
            display: flex;
            flex-direction: column;
        }
        .hero-card {
            background: white;
            border-radius: 16px;
            box-shadow: 0 10px 25px -5px rgba(0, 0, 0, 0.05), 0 8px 10px -6px rgba(0, 0, 0, 0.05);
            border: none;
        }
    </style>
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark mb-5 shadow-sm">
        <div class="container">
            <a class="navbar-brand text-primary fw-bold" href="<%= request.getContextPath() %>/">
                <i class="bi bi-cart3 me-2"></i>Mini E-Commerce
            </a>
            <div class="navbar-nav ms-auto">
                <a class="nav-link text-white active fw-semibold" href="<%= request.getContextPath() %>/"><i class="bi bi-house me-1"></i>Home</a>
                <a class="nav-link text-white" href="<%= request.getContextPath() %>/products"><i class="bi bi-box-seam me-1"></i>Products</a>
            </div>
        </div>
    </nav>

    <div class="container my-auto py-5">
        <div class="row justify-content-center">
            <div class="col-md-8 col-lg-6">
                <div class="hero-card p-5 text-center">
                    <div class="mb-4 text-primary">
                        <i class="bi bi-shop display-3"></i>
                    </div>
                    <h2 class="fw-bold mb-3">Welcome to Mini E-Commerce</h2>
                    <p class="text-muted mb-4 lead fs-6">
                        Your Java Servlet & JSP Web Application is running successfully. Explore the product catalog and inventory.
                    </p>
                    <a href="<%= request.getContextPath() %>/products" class="btn btn-primary btn-lg px-4 rounded-pill shadow-sm">
                        <i class="bi bi-box-seam me-2"></i>View Products Catalog
                    </a>
                </div>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>