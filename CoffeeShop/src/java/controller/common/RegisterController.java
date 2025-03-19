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
import java.io.IOException;
import java.util.Vector;
import jakarta.servlet.annotation.WebServlet;
@WebServlet(name = "RegisterController", urlPatterns = {"/register"})

public class RegisterController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("register.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // lay ra thong tin nguoi dung input o form signup
        String username = request.getParameter("username");
        String pass = request.getParameter("password");
        String cfpass = request.getParameter("cfpassword");

        // lay ra tat ca nhung tai khoan da ton tai trong he thong
        UsersDAO ud = new UsersDAO();
        Vector<Users> vectorU = ud.getAllUser();

        if (checkUserExist(username, vectorU)) {
            request.setAttribute("mess", "Username already exists");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        } else if (!pass.equals(cfpass)) {
            // kiem tra mat khau va nhap lai mat khau co khop nhau hay khong
            request.setAttribute("mess", "Password not match!");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        } else {
            // neu khong co van de gì -> dang ky tai khoan thành công
            ud.Register(username, pass);
            response.sendRedirect("login.jsp");

        }

    }

    public static boolean checkUserExist(String username, Vector<Users> vectorU) {
        for (Users u : vectorU) {
            if (u.getUsername().equalsIgnoreCase(username)) {
                return true;
            }
        }
        return false;
    }
}
