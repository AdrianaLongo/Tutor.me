package logged;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import dao.DAO;
import utils.IdentifyUsers;
import utils.Useful;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.sql.SQLException;

@WebServlet(name = "CourseTutorAssociationServlet", urlPatterns = "/CourseTutorAssociationServlet")
public class CourseTutorAssociationServlet extends HttpServlet {

    DAO dao = null;

    public void init(ServletConfig conf) throws ServletException {

        super.init(conf);
        ServletContext ctx = conf.getServletContext(); //prendo il context per accedere a web.xml
        String url = ctx.getInitParameter("DB-Url"); //indirizzo DB nel web.xml
        String user = ctx.getInitParameter("user");
        String pwd = ctx.getInitParameter("password");
        dao = new DAO(url, user, pwd); //creo un nuovo oggetto DAO, vedere costruttore in DAO
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("application/json, charset=UTF-8");

        String opCode = request.getParameter("opCode");
        Gson gson = new Gson();
        String Json;
        PrintWriter out = response.getWriter();
        Useful message;

        String idDocente;
        String nomeDocente = "";
        String cognomeDocente = "";
        String nomeCorso = "";
        int idTutor = 0;
        boolean checkCourse;

        Cookie toCheck[] = request.getCookies();

        if (IdentifyUsers.identifyIdCookie(toCheck)) {
            if (IdentifyUsers.identifyRoleCookie(toCheck)) {
                System.out.println("utente identificato");
                nomeCorso = request.getParameter("nomeCorso");
                System.out.println("nomeCorso: " + nomeCorso);
                nomeDocente = request.getParameter("nomeDocente");
                System.out.println("nomeDocente" + nomeDocente);
                cognomeDocente = request.getParameter("cognomeDocente");
                System.out.println("cognomeDocente" + cognomeDocente);

                try {
                    checkCourse = dao.checkCourse(nomeCorso);
                    if (!checkCourse) {
                        dao.addCourse(nomeCorso);
                    }
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                    message = new Useful("Error in step 1 adding the course", -1, null);
                    Json = gson.toJson(message);
                    out.write(Json);
                    out.flush();
                }

                if (opCode.equals("insertCourse")) {
                    System.out.println("Inserisco nuovo corso per tutor esistente");
                    idDocente = request.getParameter("idDocente");
                    System.out.println("nomeCorso: " + nomeCorso);
                    System.out.println("nomeDocente" + nomeDocente);
                    System.out.println("cognomeDocente" + cognomeDocente);
                    System.out.println("idDocente: " + idDocente);
                    idTutor = Integer.parseInt(idDocente);
                } else if (opCode.equals("insertCourseAndTutor")) {
                    System.out.println("Inserisco nuovo corso per nuovo tutor");
                    idTutor = Useful.generateId();
//                    TODO: decidere se decommentare
//                    try {
//                        //anche se controllo con checkDocente se esiste
////                        //è deleterio, potrebbe esse un omonimo
//                        dao.addDocente(idTutor, nomeDocente, cognomeDocente);
//                    } catch (SQLException e) {
//                        System.out.println(e.getMessage());
//                        message = new Useful("Error in adding tutor to catalogue", -1, null);
//                        Json = gson.toJson(message);
//                        out.write(Json);
//                        out.flush();
//                    }
                }
                try {
                    System.out.println("Provo ad associare nuovo corso a docente esistente");
                    dao.insertCorsoDocenteAssociation(nomeCorso, idTutor, nomeDocente, cognomeDocente);
                    message = new Useful("Correctly added association", 1, null);
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                    message = new Useful("Error in adding association", -1, null);
                    Json = gson.toJson(message);
                    out.write(Json);
                    out.flush();

                }
            } else {
                message = new Useful("Sorry but you don't have admin privleges", -1, null);
            }
        } else {
            message = new Useful("Sorry you don't appear to be logged in", -1, null);

        }
        Type type = new TypeToken<Useful>() {
        }.getType();
        Json = gson.toJson(message, type);
        out.write(Json);
        out.flush();
    }


}
