/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp_poo_7.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import tp_poo4_4.dao.DaoFactory;
import tp_poo_7.dao.UserDao;
import tp_poo_7.metier.Users;

/**
 *
 * @author Nicolas
 */
public class Controller extends HttpServlet {

    private HttpServletRequest request;
    private HttpServletResponse response;
    
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
        
        this.request = request;
        this.response = response;
        
        String action = request.getParameter("action");
        try (PrintWriter out = response.getWriter()) {
            switch(action){
                case "login" :
                    doVerif(); /* méthode vérif utilisateur */
                    break;
                case "logout" :
                    //doLogout(request, response);
                    break;
                default :
                    response.sendError(response.SC_BAD_REQUEST, "error");
                    break;
            }
        }
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Controller</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Controller at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }
    
    private void doVerif(){
        //verification utilisateur
        String login = request.getParameter("login");
        String passw = request.getParameter("password");
        
        UserDao userDao = DaoFactory
                .getDaoFactory(DaoFactory.PersistenceType.JPA)
                .getUserDao();
        
        if(userDao.findAll().isEmpty()) {
            System.out.println("Create user");
            Users us = new Users();
            us.setLogin("Login");
            us.setPassword("Password");
            us.setUser_role(Users.Role.USER.name());
            userDao.create(us);
        }
        
        Users user = userDao.verifDanger(login, passw);
        
        if(user == null) {
            request.setAttribute("error", "Bad credentials.");
            
            forwardTo("/vue/login.jsp");
            return;
        }
        
        if(user.getUser_role().equals(Users.Role.ADMIN.name())) {
            
            forwardTo("/vue/admin.jsp");
            return;
        }
        
        forwardTo("/vue/client.jsp");
        
    }

    private void forwardTo(String url) {
        RequestDispatcher rd = request.getRequestDispatcher(url);

        try {
            rd.forward(request, response);
        } catch (ServletException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
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
