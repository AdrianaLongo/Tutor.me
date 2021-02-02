package logged;

import com.google.gson.Gson;
import dao.DAO;
import utils.Useful;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "TutorCourseServlet", urlPatterns = "/TutorCourseServlet")
public class TutorCourseAssociationServlet extends HttpServlet {

    DAO dao = null;

    public void init(ServletConfig conf) throws ServletException {

        super.init(conf);
        ServletContext ctx = conf.getServletContext(); //prendo il context per accedere a web.xml
        String url = ctx.getInitParameter("DB-Url"); //indirizzo DB nel web.xml
        String user = ctx.getInitParameter("user");
        String pwd = ctx.getInitParameter("password");
        dao = new DAO(url, user, pwd); //creo un nuovo oggetto DAO, vedere costruttore in DAO
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {

        response.setContentType("application/json, charset=UTF-8");

        HttpSession session = request.getSession(false);

        String opCode = request.getParameter("opCode");
        Gson gson = new Gson();
        String Json;
        Useful message;
        PrintWriter out = response.getWriter();

        String nomeDocente = "";
        String cognomeDocente = "";
        String nomeCorso = "";
        int idTutor = 0;
        boolean checkCourse;

        if (session != null) {
            String sessionId = request.getParameter("jSessionId");
            if (sessionId.equals(session.getId())) {

                nomeCorso = request.getParameter("nomeCorso");
                nomeDocente = request.getParameter("nomeDocente");
                cognomeDocente = request.getParameter("cognomeDocente");

                idTutor = Useful.generateId();

                if (opCode.equals("button2")) {
                    try {
                        checkCourse = dao.checkCourse(nomeCorso);
                        if (!checkCourse)
                            dao.addCourse(nomeCorso);
                    } catch (SQLException e) {
                        System.out.println(e.getMessage());
                        message = new Useful("There were problems in step 1, adding the new course", -1, null);
                        Json = gson.toJson(message);
                        out.write(Json);
                        out.flush();
                    }
                }

                try {
                    dao.insertCorsoDocenteAssociation(nomeCorso, idTutor, nomeDocente, cognomeDocente);
                    message = new Useful("Correctly associated elements", 1, null);
                    Json = gson.toJson(message);
                    out.write(Json);
                    out.flush();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                    message = new Useful("There were problems in step 2, associating elements", -1, null);
                    Json = gson.toJson(message);
                    out.write(Json);
                    out.flush();
                }

            }
        }

    }
}