package com.bankapp.servlet;

import com.bankapp.dao.AdminDAO;
import com.bankapp.model.Admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/adminLogin")
public class AdminLoginServlet extends HttpServlet {
    private AdminDAO adminDAO;

    public void init() {
        adminDAO = new AdminDAO();
    }

   
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            Admin admin = adminDAO.getAdminByUsernameAndPassword(username, password);
            if (admin != null) {
                HttpSession session = request.getSession();
                session.setAttribute("admin", admin);
                response.sendRedirect("adminDashboard.jsp");
            } else {
                response.sendRedirect("adminLogin.jsp?error=Invalid username or password");
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
