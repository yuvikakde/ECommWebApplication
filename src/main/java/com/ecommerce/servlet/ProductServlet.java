package com.ecommerce.servlet;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.ecommerce.exception.ProductException;
import com.ecommerce.model.Product;
import com.ecommerce.service.ProductService;

@WebServlet(name = "ProductServlet", urlPatterns = "/products")
public class ProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private ProductService productService;

    @Override
    public void init() throws ServletException {
        productService = new ProductService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }

        try {
            switch (action) {
                case "new":
                    showNewForm(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case "delete":
                    deleteProduct(request, response);
                    break;
                case "list":
                default:
                    listProducts(request, response);
                    break;
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }

        try {
            switch (action) {
                case "insert":
                    insertProduct(request, response);
                    break;
                case "update":
                    updateProduct(request, response);
                    break;
                default:
                    response.sendRedirect(request.getContextPath() + "/products");
                    break;
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    // Displays the list of all products
    private void listProducts(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Product> products = productService.getAllProducts();
        request.setAttribute("products", products);
        request.getRequestDispatcher("/product-list.jsp").forward(request, response);
    }

    // Shows empty form for adding a new product
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("product", null);
        request.getRequestDispatcher("/product-form.jsp").forward(request, response);
    }

    // Shows form pre-populated with existing product details for editing
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            Product existingProduct = productService.getProductById(id);
            request.setAttribute("product", existingProduct);
            request.getRequestDispatcher("/product-form.jsp").forward(request, response);
        } catch (Exception e) {
            String errorMsg = URLEncoder.encode(e.getMessage() != null ? e.getMessage() : "Error loading product", StandardCharsets.UTF_8);
            response.sendRedirect(request.getContextPath() + "/products?error=" + errorMsg);
        }
    }

    // Handles adding a new product
    private void insertProduct(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Product newProduct = extractProductFromRequest(request);
        try {
            productService.addProduct(newProduct);
            response.sendRedirect(request.getContextPath() + "/products?msg=added");
        } catch (ProductException | IllegalArgumentException e) {
            request.setAttribute("errorMessage", e.getMessage());
            request.setAttribute("product", newProduct);
            request.getRequestDispatcher("/product-form.jsp").forward(request, response);
        }
    }

    // Handles updating an existing product
    private void updateProduct(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Product product = extractProductFromRequest(request);
        try {
            int id = Integer.parseInt(request.getParameter("productId"));
            product.setProductId(id);
            productService.updateProduct(product);
            response.sendRedirect(request.getContextPath() + "/products?msg=updated");
        } catch (ProductException | IllegalArgumentException e) {
            request.setAttribute("errorMessage", e.getMessage());
            request.setAttribute("product", product);
            request.getRequestDispatcher("/product-form.jsp").forward(request, response);
        }
    }

    // Handles deleting a product
    private void deleteProduct(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            productService.deleteProduct(id);
            response.sendRedirect(request.getContextPath() + "/products?msg=deleted");
        } catch (Exception e) {
            String errorMsg = URLEncoder.encode(e.getMessage() != null ? e.getMessage() : "Unable to delete product", StandardCharsets.UTF_8);
            response.sendRedirect(request.getContextPath() + "/products?error=" + errorMsg);
        }
    }

    // Helper to safely parse and bind product properties from request
    private Product extractProductFromRequest(HttpServletRequest request) {
        String name = request.getParameter("productName");
        String description = request.getParameter("description");
        String status = request.getParameter("status");
        if (status == null || status.trim().isEmpty()) {
            status = "ACTIVE";
        }
        String imageUrl = request.getParameter("imageUrl");

        double price = 0.0;
        try {
            String priceParam = request.getParameter("price");
            if (priceParam != null && !priceParam.trim().isEmpty()) {
                price = Double.parseDouble(priceParam.trim());
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid price value: must be a valid number.");
        }

        int stock = 0;
        try {
            String stockParam = request.getParameter("stockQuantity");
            if (stockParam != null && !stockParam.trim().isEmpty()) {
                stock = Integer.parseInt(stockParam.trim());
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid stock quantity: must be an integer.");
        }

        int categoryId = 1;
        try {
            String catParam = request.getParameter("categoryId");
            if (catParam != null && !catParam.trim().isEmpty()) {
                categoryId = Integer.parseInt(catParam.trim());
            }
        } catch (NumberFormatException ignored) {
            categoryId = 1;
        }

        Product product = new Product();
        product.setProductName(name != null ? name.trim() : "");
        product.setDescription(description != null ? description.trim() : "");
        product.setPrice(price);
        product.setStockQuantity(stock);
        product.setCategoryId(categoryId);
        product.setStatus(status.trim().toUpperCase());
        product.setImageUrl(imageUrl != null ? imageUrl.trim() : "");

        return product;
    }
}
