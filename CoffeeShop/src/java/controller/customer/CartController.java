package controller.customer;

import repository.imp.ProductsDAO;
import repository.imp.OrderItemsDAO;
import repository.imp.OrdersDAO;
import repository.imp.CategoriesDAO;
import model.Users;
import model.Products;
import model.Categories;
import model.Cart;
import model.Orders;
import java.util.*;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDate;

@WebServlet(name = "CartController", urlPatterns = {"/cart"})

public class CartController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        Users currentUser = (Users) session.getAttribute("user");

        if (cart == null) {
            cart = new Cart();
        }

        double totalPrice = cart.getTotalPrice();

        String service = request.getParameter("service");
        if (service == null) {
            service = "showCart";
        }

        if (service.equals("addToCart")) {
            Products p = getProduct(request);
            if (p != null) {
                cart.addToCart(p, 1);

                List<Categories> cates = (new CategoriesDAO().getCategories());
                request.setAttribute("cates", cates);

                List<Products> products = new ProductsDAO().getAllProducts();
                request.setAttribute("products", products);

                session.setAttribute("cart", cart);
            }

            request.getRequestDispatcher("content.jsp").forward(request, response);
        }

        if (service.equals("updateCart")) {
            // Lấy tất cả các tham số từ request để xem sản phẩm nào cần cập nhật số lượng
            Enumeration<String> params = request.getParameterNames();
            while (params.hasMoreElements()) {
                String paramName = params.nextElement();
                // Kiểm tra xem tên tham số có bắt đầu bằng "product" hay không
                if (paramName.startsWith("product")) {
                    // Lấy ID sản phẩm từ tên tham số
                    int productId = Integer.parseInt(paramName.substring(7));
                    // Lấy số lượng mới từ tham số
                    int newQuantity = Integer.parseInt(request.getParameter(paramName));
                    // Lấy thông tin sản phẩm từ ID
                    Products p = new ProductsDAO().getProductByID(productId);

                    if (newQuantity == 0) {
                        cart.removeFromCart(p);
                    } else {
                        cart.updateProductQuantityInCart(p, newQuantity);

                    }
                }
            }
            totalPrice = cart.getTotalPrice();
            request.setAttribute("cart", cart);
            request.setAttribute("total", totalPrice);
            request.getRequestDispatcher("cart.jsp").forward(request, response);
        }

        if (service.equals("removeFromCart")) {
            Products p = getProduct(request);

            cart.removeFromCart(p);
            totalPrice = cart.getTotalPrice();

            request.setAttribute("cart", cart);
            request.setAttribute("total", totalPrice);

            request.getRequestDispatcher("cart.jsp").forward(request, response);
        }

        if (service.equals("clearCart")) {
            cart.clearCart();
            totalPrice = 0;

            request.setAttribute("cart", cart);
            request.setAttribute("total", totalPrice);

            request.getRequestDispatcher("cart.jsp").forward(request, response);

        }

        if (service.equals("showCart")) {
            request.setAttribute("cart", cart);
            request.setAttribute("total", totalPrice);
            request.getRequestDispatcher("cart.jsp").forward(request, response);
        }

        if (service.equals("displayCheckout")) {
            request.setAttribute("cart", cart);
            request.setAttribute("total", totalPrice);
            request.getRequestDispatcher("checkout.jsp").forward(request, response);
        }

        if (service.equals("checkout")) {
            String name = request.getParameter("name");
            String phone = request.getParameter("phone");
            String address = request.getParameter("address");

            Orders o = new Orders(currentUser.getID(), name, phone, address, LocalDate.now(), cart.getTotalPrice());
            new OrdersDAO().createOrder(o);

            for (Map.Entry<Products, Integer> entry : cart.getCartItems().entrySet()) {
                Products p = entry.getKey();
                int quantity = entry.getValue();
                new OrderItemsDAO().insert(p.getID(), quantity);
            }

            // Xóa giỏ hàng sau khi đã thanh toán
            cart.clearCart();
            response.sendRedirect("customer");

        }

    }

    private Products getProduct(HttpServletRequest request) {
        String strProductId = request.getParameter("productId");
        if (strProductId == null) {
            return null;
        }
        int productId = Integer.parseInt(strProductId);
        Products p = new ProductsDAO().getProductByID(productId);

        return p;
    }

}
