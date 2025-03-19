package controller.admin;

import repository.imp.OrderItemsDAO;
import repository.imp.OrdersDAO;
import model.Users;
import model.OrderItems;
import model.Orders;
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

@WebServlet(name = "BillController", urlPatterns = {"/billmanager"})

public class BillController extends HttpServlet {

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

            if (service == null) {
                service = "billlist";
            }

            if (service.equals("billlist")) {
                Vector<Orders> vectorOd = new OrdersDAO().getAllOrders();

                request.setAttribute("vectorOd", vectorOd);
                request.getRequestDispatcher("bill-manager.jsp").forward(request, response);
            }

            if (service.equals("nextstatus")) {
                int odid = Integer.parseInt(request.getParameter("odid"));
                Orders o = new OrdersDAO().getOrderByOdId(odid);
                new OrdersDAO().nextStatus(odid, o.getStatus());

                Vector<Orders> vectorOd = new OrdersDAO().getAllOrders();

                request.setAttribute("vectorOd", vectorOd);
                request.getRequestDispatcher("bill-manager.jsp").forward(request, response);
            }

            if (service.equals("detail")) {
                int odid = Integer.parseInt(request.getParameter("odid"));

                Vector<OrderItems> vectorOdItem = new OrderItemsDAO().getOdDetailByOdId(odid);
                request.setAttribute("vectorOdItem", vectorOdItem);
                request.getRequestDispatcher("bill-details.jsp").forward(request, response);
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

    }

}
