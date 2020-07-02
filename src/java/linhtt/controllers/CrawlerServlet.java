/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linhtt.controllers;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import linhtt.constants.Sources;
import linhtt.resolvers.Crawler;

/**
 *
 * @author TranLinh
 */
@WebServlet(name = "CrawlerServlet", urlPatterns = {"/CrawlerServlet"})
public class CrawlerServlet extends HttpServlet {
    private static final String ERROR = "/WEB-INF/error.jsp";
    private static final String INDEX = "/WEB-INF/user.jsp";
    

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
       
        String url = ERROR;
        try {
            String realPath = request.getServletContext().getRealPath("/");
            String xmlPath =  realPath + Sources.XML_PATH;
            
            for (String path: Sources.STYLE_SHEETS) {
                String xslPath = realPath + path;
              
               
                DOMResult result = Crawler.crawl(xmlPath, xslPath);
                 
                
                   
           }
            
            url = INDEX;
        }catch(Exception e){
            request.setAttribute("ERROR", "Could not crawl data in CrawlCafef");
            System.out.println(e.toString());
        }finally{
            request.getRequestDispatcher(url).forward(request, response);
        }
        /*
        try {
            ServletContext context = request.getServletContext();
            String path = context.getRealPath("/");
            String xmlPath = path + XML_PATH;
            String xslPath = path + XSL_PATH;
            
            DOMResult result = Crawler.crawl(xmlPath, xslPath);
//            TransformerFactory factory = TransformerFactory.newInstance();
//            Transformer tran = factory.newTransformer();
//            StreamResult results = new StreamResult(new FileOutputStream(path + "WEB-INF\\new.xml"));
//            System.out.println("lalala");
//            System.out.println(result);
//            System.out.println("hihih");
//            tran.transform(new DOMSource(result.getNode()), results);
        

        } catch (FileNotFoundException ex) {
            Logger.getLogger(CrawlerServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(CrawlerServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
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
