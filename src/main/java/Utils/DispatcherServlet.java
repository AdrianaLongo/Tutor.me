package Utils;

import logged.EffettuaPrenotazioneServlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "DispatcherServlet", value = "/DispatcherServlet")
public class DispatcherServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {

        ServletContext ctx = getServletContext();
        String page = "";

        String action = request.getParameter("action");

        if (action.equals("login"))
            page = "LoginServlet";

        if(action.equals("getCourses"))
            page = "PopulateCorsiServlet";

        if(action.equals("searchProf"))
            page = "cercaTutorServlet";

        if(action.equals("doBooking"))
            page = "EffettuaPrenotazioneServlet";

        if(action.equals("getUserHistory"))
            page = "RetrievePrenotazioniUtenteServlet";

        if(action.equals("lessonDone"))
            page = "PrenotazioneEffettuataServlet";

        if(action.equals("logout"))
            page = "LogoutServlet";

        if(action.equals("cancelReservation"))
            page = "DisdettaServlet";

        if(action.equals("getGlobalHistory"))
            page = "PopolaStoricoServlet";

        if(action.equals("getCourses"))
            page = "CercaCorsoServlet";

        if(action.equals("getTutor"))
            page = "cercaTutorServlet";

        if(action.equals("getTutorAvailability"))
            page = "DisponibilitaTutorServlet";

        if(action.equals("populateTutors"))
            page = "PopolaDocenteServlet";

        if(action.equals("populateCourses"))
            page = "PopulateCorsiServlet";

        RequestDispatcher reqDisp = ctx.getRequestDispatcher(page);
        reqDisp.forward(request,response);

    }
}


