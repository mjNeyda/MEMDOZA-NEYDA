/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.emergentes.controlador;

import com.emergentes.dao.CategoriaDAO;
import com.emergentes.dao.CategoriaDAOimpl;
import com.emergentes.modelo.Categoria;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Rene
 */
@WebServlet(name = "CategoriaController", urlPatterns = {"/CategoriaController"})
public class CategoriaController extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
     CategoriaDAO dao = new CategoriaDAOimpl();
        Categoria avi = new Categoria();
        int id;
      
       String action = (request.getParameter("action")!=null) ? request.getParameter("action") : "view";
       switch (action){
           case "add":
               request.setAttribute("aviso", avi);
               request.getRequestDispatcher("frmcategoria.jsp").forward(request, response);
               break;
           case "edit":
               id = Integer.parseInt(request.getParameter("id"));
            
                try {
                    avi = dao.getById(id);
                } catch (Exception ex) {
                    System.out.println("Error al obtener registro");
                }
            
               request.setAttribute("aviso", avi);
               request.getRequestDispatcher("frmcategoria.jsp").forward(request, response);
               
               break;

           case "delete":
               id = Integer.parseInt(request.getParameter("id"));
               try {
                   dao.delete(id);
               } catch (Exception ex) {
                   System.out.println("Error al eliminar: "+ ex.getMessage());
               }
               response.sendRedirect("CategoriaController");
               break;
           case "view":
               List<Categoria> lista = new ArrayList<Categoria>();
            try {
                lista = dao.getAll();
            } catch (Exception ex) {
                System.out.println("Error al listar "+ex.getMessage());
            }
               request.setAttribute("avisos", lista);
               request.getRequestDispatcher("categorias.jsp").forward(request, response);
               break;
               default:
                   break; 
                   
       } 
 System.out.println("******Iniciando..*******");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 int id= Integer.parseInt(request.getParameter("id"));
      String categoria = request.getParameter("categoria");
      
      Categoria avi = new Categoria();
      
      avi.setId(id);
      avi.setCategoria(categoria);
      
      CategoriaDAO dao = new CategoriaDAOimpl();
      
      if (id==0){
          try {
              dao.insert(avi);
          } catch (Exception ex) {
              System.out.println("Error al insertar "+ex.getMessage());
          }
          
      }
      else{
          try {
              dao.update(avi);
          } catch (Exception ex) {
              
              System.err.println("Error al editar "+ ex.getMessage());
          }
      }
      response.sendRedirect("CategoriaController");
              
    }    
}
