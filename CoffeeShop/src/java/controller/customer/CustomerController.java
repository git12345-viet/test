package controller.customer;

import repository.imp.ProductsDAO;
import repository.imp.CategoriesDAO;
import model.Products;
import model.Categories;
import java.util.*;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "CustomerController", urlPatterns = {"/customer"})

public class CustomerController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String service = request.getParameter("service");
        List<Categories> cates = (new CategoriesDAO().getCategories());
        request.setAttribute("cates", cates);

        if (service == null) {
            service = "getAllProducts";
        }

        if (service.equals("getAllProducts")) {
            List<Products> products = new ProductsDAO().getAllProducts();
            request.setAttribute("products", products);
            //out.print(products);

            request.getRequestDispatcher("content.jsp").forward(request, response);
        }

        if (service.equals("getProductsByCate")) {
            int cateId = Integer.parseInt(request.getParameter("cateId"));
            List<Products> products = new ArrayList<>();

            if (cateId == 0) {
                products = (new ProductsDAO()).getAllProducts();
            } else {
                products = (new ProductsDAO()).getProductsByCategoryID(cateId);
            }

            request.setAttribute("products", products);
            request.getRequestDispatcher("content.jsp").forward(request, response);
        }

        if (service.equals("searchByKeywords")) {
            String keywords = request.getParameter("keywords");

            List<Products> products = (new ProductsDAO().searchProductByName(keywords));

            request.setAttribute("products", products);
            request.getRequestDispatcher("content.jsp").forward(request, response);
        }

        if (service.equals("getProductDetail")) {
            int productId = Integer.parseInt(request.getParameter("productId"));
            Products p = new ProductsDAO().getProductByID(productId);
            request.setAttribute("product", p);

            Categories c = new CategoriesDAO().getCategoryByProductId(productId);
            request.setAttribute("cate", c);

            // lay san pham lien quan
            List<Products> relatedProducts = new ArrayList<>();
            List<Products> listP = new ProductsDAO().getProductsByCategoryID(c.getID());

// Remove the current product from the list of related products
            Iterator<Products> iterator = listP.iterator();
            while (iterator.hasNext()) {
                Products product = iterator.next();
                if (product.getID() == productId) {
                    iterator.remove();
                    break; // 
                }
            }

// Get at most 3 related products
            int count = 0;
            for (Products product : listP) {
                relatedProducts.add(product);
                count++;
                if (count >= 3) {
                    break;
                }
            }

            request.setAttribute("relatedProducts", relatedProducts);

            request.getRequestDispatcher("product-detail.jsp").forward(request, response);
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

    }

}
