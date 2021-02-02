package utils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DispatcherServlet", value = "/DispatcherServlet")
public class DispatcherServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
        System.out.println("23 DispatcherServlet: sono dentro");

        ServletContext ctx = getServletContext();
        String page = "";

        String action = request.getParameter("action");

        if (action.equals("login")) {
            System.out.println("31 DispatcherServlet: sono dentro la richiesta per il login");
            page = ctx.getInitParameter("LoginServlet");
        }
        if(action.equals("searchProf"))
            page = ctx.getInitParameter("cercaTutorServlet");

        if(action.equals("doBooking"))
            page = ctx.getInitParameter("EffettuaPrenotazioneServlet");

        if(action.equals("getUserHistory"))
            page = ctx.getInitParameter("RetrievePrenotazioniUtenteServlet");

        if(action.equals("lessonDone"))
            page = ctx.getInitParameter("PrenotazioneEffettuataServlet");

        if(action.equals("logout"))
            page = ctx.getInitParameter("LogoutServlet");

        if(action.equals("cancelReservation"))
            page = ctx.getInitParameter("DisdettaServlet");

        if(action.equals("getGlobalHistory"))
            page = ctx.getInitParameter("PopolaStoricoServlet");

        if(action.equals("getCourses"))
            page = ctx.getInitParameter("CercaCorsoServlet");

        if(action.equals("getTutor"))
            page = ctx.getInitParameter("cercaTutorServlet");

        if(action.equals("getTutorAvailability"))
            page = ctx.getInitParameter("DisponibilitaTutorServlet");

        if(action.equals("populateTutors"))
            page = ctx.getInitParameter("PopolaDocenteServlet");

        if(action.equals("populateCourses")) {
            System.out.println("70 DispatcherServlet: sono dentro la richiesta per i corsi");
            page = ctx.getInitParameter("PopulateCorsiServlet");
        }
        RequestDispatcher reqDisp = ctx.getRequestDispatcher(page);
        System.out.println("74 DispatcherServlet: sono oltre il reqdisp");
        reqDisp.forward(request,response);
        System.out.println("76 DispatcherServlet: sono oltre il forward");

    }
}