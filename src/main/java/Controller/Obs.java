/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Dao.EstudianteDAO;
import Dao.ObservadorDAO;
import Model.Estudiante;
import Model.Profesor;
import Model.Observador;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author oscarromero
 */
public class Obs extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Profesor p = (Profesor) request.getSession().getAttribute("profesor");
            EstudianteDAO pro = new EstudianteDAO();
            ArrayList<Estudiante> estudiantes = pro.getEstudiantesByIDCurso(p.getId_curso());
            request.setAttribute("respuesta", estudiantes);
            request.getRequestDispatcher("buscObs.jsp").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Obs.class.getName()).log(Level.SEVERE, null, ex);
        } catch (URISyntaxException ex) {
            Logger.getLogger(Obs.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int idEst = Integer.parseInt(request.getParameter("estudiante"));
        try {
            ObservadorDAO od = new ObservadorDAO();
            EstudianteDAO ed = new EstudianteDAO();
            Estudiante e = ed.getEstudianteById(idEst);
            ArrayList<Observador> notasObs = od.getObservadorByID(idEst);
            request.setAttribute("notas", notasObs);
            request.setAttribute("nombre", e.getNombre_estudiante());
            request.getRequestDispatcher("observador.jsp").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Obs.class.getName()).log(Level.SEVERE, null, ex);
        } catch (URISyntaxException ex) {
            Logger.getLogger(Obs.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
