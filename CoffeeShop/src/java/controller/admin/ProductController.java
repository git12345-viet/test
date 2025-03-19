package controller.admin;

import repository.imp.ProductsDAO;
import repository.imp.CategoriesDAO;
import model.Users;
import model.Products;
import model.Categories;
import controller.common.Authentication;
import java.util.*;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "ProductController", urlPatterns = {"/productmanager"})

public class ProductController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession();
        Users u = (Users) session.getAttribute("user");
        if (!Authentication.isAdmin(u)) {
            Authentication.accessDenied(session, response);
        } else {
            String service = request.getParameter("service");
            List<Categories> vectorC = new CategoriesDAO().getCategories();
            request.setAttribute("vectorC", vectorC);

            if (service == null) {
                service = "productlist";
            }

            if (service.equals("productlist")) {
                loadData(request, response);
            }

            if (service.equals("displayproduct")) {
                int productId = Integer.parseInt(request.getParameter("productId"));
                Products p = new ProductsDAO().getProductByID(productId);

                request.setAttribute("p", p);
                request.getRequestDispatcher("edit-product.jsp").forward(request, response);
            }
            if (service.equals("add")) {
                Products p = getProductFromUI(request);
                new ProductsDAO().addProduct(p);

                loadData(request, response);

            }

            if (service.equals("edit")) {
                Products p = getProductFromUI(request);
                p.setID(Integer.parseInt(request.getParameter("id")));

                new ProductsDAO().editProduct(p.getID(), p);

                loadData(request, response);

            }

            if (service.equals("delete")) {
                int productId = Integer.parseInt(request.getParameter("productId"));

                new ProductsDAO().deleteProduct(productId);

                loadData(request, response);
            }
        }
    }

    private Products getProductFromUI(HttpServletRequest request) {
        // int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String image = "img/product/" + request.getParameter("image");
        double price = Double.parseDouble(request.getParameter("price"));
        int categoryId = Integer.parseInt(request.getParameter("category"));
        String des = request.getParameter("des");

        Products p = new Products(name, des, price, image, categoryId);

        return p;
    }

    private void loadData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Vector<Products> vectorP = new ProductsDAO().getAllProducts();
        request.setAttribute("vectorP", vectorP);
        request.getRequestDispatcher("product-list.jsp").forward(request, response);
    }
}
