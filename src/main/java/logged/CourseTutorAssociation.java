package logged;

import com.google.gson.Gson;
import dao.DAO;
import utils.Useful;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "logged.AddAssociationServlet", urlPatterns = "/logged.AddAssociationServlet")
public class CourseTutorAssociation extends HttpServlet {

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

        HttpSession session = request.getSession(false);

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

        if (session != null) {
            String sessionId = request.getParameter("jSessionId");
            if (sessionId.equals(session.getId())) {

                nomeCorso = request.getParameter("nomeCorso");
                nomeDocente = request.getParameter("nomeDocente");
                cognomeDocente = request.getParameter("cognomeDocente");

                try {
                    checkCourse = dao.checkCourse(nomeCorso);
                    if (!checkCourse) {
                        dao.addCourse(nomeCorso);
                    }
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                    message = new Useful("Error in step 1 adding the course", -1,null);
                    Json = gson.toJson(message);
                    out.write(Json);
                    out.flush();
                }

                if (opCode.equals("button1")) {

                    idDocente = request.getParameter("idDocente");
                    idTutor = Integer.parseInt(idDocente);
                }


                if (opCode.equals("button2")) {

                    idTutor = Useful.generateId();

                    try {
                        //anche se controllo con checkDocente se esiste
                        //è deleterio, potrebbe esse un omonimo
                        dao.addDocente(idTutor, nomeDocente, cognomeDocente);
                    } catch (SQLException e) {
                        System.out.println(e.getMessage());
                        message = new Useful("Error in adding tutor to catalogue", -1,null);
                        Json = gson.toJson(message);
                        out.write(Json);
                        out.flush();
                    }
                }

                try {
                    dao.insertCorsoDocenteAssociation(nomeCorso, idTutor, nomeDocente, cognomeDocente);
                    message = new Useful("Correctly added association", 1, null);
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                    message = new Useful("Error in adding association", -1,null);
                    Json = gson.toJson(message);
                    out.write(Json);
                    out.flush();

                }
                Json = gson.toJson(message);
                out.write(Json);
                out.flush();
            }

        }
    }

        protected void doGet (HttpServletRequest request, HttpServletResponse response) throws
        ServletException, IOException {

        }
    }
