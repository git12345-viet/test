/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.common;

import repository.imp.UsersDAO;
import model.Users;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import jakarta.servlet.annotation.WebServlet;

@WebServlet(name = "LoginController", urlPatterns = {"/login"})
public class LoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // dang nhap
        UsersDAO ud = new UsersDAO();
        Users u = ud.Login(username, password);

        // neu tai khoan chua ton tai
        if (u == null) {
            request.setAttribute("mess", "Wrong username or password!");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else if (u.getRole() == 2 && u.isActive() == true) { // neu tai khoan tont tai, tao session -> tra ve home (servlet)
            HttpSession session = request.getSession();
            session.setAttribute("user", u);
            response.sendRedirect("customer");
        } else if (u.getRole() == 2 && u.isActive() == false) { // neu tai khoan tont tai, tao session -> tra ve home (servlet)
            request.setAttribute("mess", "Your account have been locked by admin");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else if (u.getRole() == 1) {
            HttpSession session = request.getSession();
            session.setAttribute("user", u);
            response.sendRedirect("accountmanager");
        }
    }

}
