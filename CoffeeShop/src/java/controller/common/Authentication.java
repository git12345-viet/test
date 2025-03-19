/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.common;

import model.Users;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

/**
 *
 * @author havie
 */
public class Authentication {

     public static boolean isCustomer(Users user) {
        int customerRole = 2;
        return user != null && user.getRole() == customerRole;
    }

    public static boolean isAdmin(Users user) {
        int adminRole = 1;
        return user != null && user.getRole() == adminRole;
    }

    public static void accessDenied(HttpSession session, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("access_denied.jsp");
    }
}
