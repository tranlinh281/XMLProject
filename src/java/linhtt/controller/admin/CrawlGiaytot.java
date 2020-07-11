/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linhtt.controller.admin;

import linhtt.constants.Sources;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.xml.transform.dom.DOMResult;
import linhtt.resolvers.Crawler;
import linhtt.resolvers.DataResolver;
//import linhtt.resolvers.DataResolver;

/**
 *
 * @author TranLinh
 */
@WebServlet(name = "CrawlGiaytot", urlPatterns = {"/CrawlGiaytot"})
public class CrawlGiaytot extends HttpServlet {
    private static final String ADMIN = "admin.jsp";
    private static final String ERROR = "error.jsp";
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String realPath = request.getServletContext().getRealPath("/");
            String xmlPath = realPath + Sources.XML_PATH;
            for (String path: Sources.STYLE_SHEETS_GIAYTOT) {
                String xslPath = realPath + path;
              
               
                DOMResult result = Crawler.crawl(xmlPath, xslPath);
                 DataResolver dataResolver = new DataResolver();
                 dataResolver.saveDomResultToDatabase(result);
                   
           }
            
            url = ADMIN;
        }catch(Exception e){
            request.setAttribute("ERROR", "Could not crawl data in CrawlGiaytot");
            System.out.println(e.toString());
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
