package utils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
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

        ServletContext ctx = getServletContext();
        String page = "";

        String action = request.getParameter("action");

        if (action.equals("login"))
            page = ctx.getInitParameter("LoginServlet");

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

        if(action.equals("populateCourses"))
            page = ctx.getInitParameter("PopulateCorsiServlet");

        if(action.equals("courseTutorAssociation"))
            page = ctx.getInitParameter("CourseTutorAssociationServlet");

        if(action.equals("deleteAssociation"))
            page = ctx.getInitParameter("DeleteAssociationServlet");

        if(action.equals("deleteCourse"))
            page = ctx.getInitParameter("DeleteCourseServlet");

        if(action.equals("deleteTutor"))
            page = ctx.getInitParameter("DeleteTutorServlet");

        if(action.equals("existingAssociation"))
            page = ctx.getInitParameter("ExistingEntAssociationServlet");

        if(action.equals("tutorCourseAssociation"))
            page = ctx.getInitParameter("TutorCourseAssociationServlet");

        RequestDispatcher reqDisp = ctx.getRequestDispatcher(page);
        reqDisp.forward(request,response);

    }
}


