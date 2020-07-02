/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linhtt.controllers;

import linhtt.daos.AccountDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import linhtt.utils.UTF8StringHelper;

/**
 *
 * @author dungntmse63284
 */
@WebServlet(name = "LoginController", urlPatterns = {"/LoginController"})
public class LoginController extends HttpServlet {
    private static final String ERROR = "error.jsp";
    private static final String ADMIN = "admin.jsp";
    private static final String USER = "user.jsp";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
//            request.setCharacterEncoding("UTF-8");
//            response.setContentType("text/html;charset=utf-8;");  
            String username = request.getParameter("txtUsername");
            String password = request.getParameter("txtPassword");
//            String utTo = UTF8StringHelper.convertToUTF8(username);
//            String toUT = UTF8StringHelper.convertFromUTF8(username);
//            System.out.println("username" + username);
//            System.out.println("utTo : "+ utTo);
//            System.out.println("toUT :" + toUT);
            AccountDAO accountDAO = new AccountDAO();
            String role = accountDAO.checkLogin(username, password);
            System.out.println(username + " a " + password);
            if(role.equals("failed"))
                request.setAttribute("ERROR","Invalid username or password");
            else{
                HttpSession session = request.getSession();
                session.setAttribute("USER", username);
                if(username.equals("admin"))
                    url = ADMIN;
                else if(role.equals("user"))
                    url = USER;                
            }
        } catch (Exception e) {
            log("ERROR at LoginController: " +e.getMessage());
        }finally{
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
