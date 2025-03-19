package controller.admin;

import repository.imp.UsersDAO;
import model.Users;
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

@WebServlet(name = "AccountController", urlPatterns = {"/accountmanager"})

public class AccountController extends HttpServlet {

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
                service = "accountlist";
            }

            if (service.equals("accountlist")) {
                Vector<Users> vector = new UsersDAO().getAllUser();
                Vector<Users> vectorU = new Vector<>();
                for (Users user : vector) {
                    if (user.getRole() == 2) {
                        vectorU.add(user);
                    }
                }
                request.setAttribute("vectorU", vectorU);
                request.getRequestDispatcher("account_manager.jsp").forward(request, response);
            }

            if (service.equals("updatestatusaccount")) {
                int accid = Integer.parseInt(request.getParameter("accid"));
                new UsersDAO().updateStatus(accid);

                Vector<Users> vector = new UsersDAO().getAllUser();
                Vector<Users> vectorU = new Vector<>();
                for (Users user : vector) {
                    if (user.getRole() == 2) {
                        vectorU.add(user);
                    }
                }
                request.setAttribute("vectorU", vectorU);
                request.getRequestDispatcher("account_manager.jsp").forward(request, response);
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

    }

}
