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

@WebServlet(name = "DeleteCourseServlet", urlPatterns = "/DeleteCourseServlet")
public class DeleteCourseServlet extends HttpServlet {

    DAO dao = null;
    Gson gson = new Gson();
    String Json;

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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json, charset=UTF-8");

        Useful message;
        Gson gson = new Gson();
        String toSendJson = "";

        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession(false);

        if (session != null) {
            String sessionId = request.getParameter("jSessionId");
            if (sessionId.equals(session.getId())) {
                if (session.getAttribute("ruolo").equals("admin")) {
                    String course = request.getParameter("nomeCorso");

                    try {
                        boolean check = dao.checkCourse(course);

                        if (check) {
                            dao.deleteCourse(course);
                            message = new Useful("Course correctly deleted", 1, null);
                        } else {
                            message = new Useful("Course doesn't exist", -1, null);
                        }
                    } catch (SQLException e) {
                        System.out.println(e.getMessage());
                        message = new Useful("There was some problem querying the course", -1, null);
                        toSendJson = gson.toJson(message);
                        out.write(toSendJson);
                        out.flush();
                    }
                } else {
                    message = new Useful("Sorry you don't have admin privileges", -1, null);
                }

            }
            else
                message = new Useful("Sorry your sessionId doesn't match", -1, null);

        }else
            message = new Useful("Sorry you're not logged in", -1, null);


        toSendJson = gson.toJson(message);
        out.write(toSendJson);
        out.flush();
    }
}
