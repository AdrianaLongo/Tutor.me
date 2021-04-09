package logged;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import dao.DAO;
import utils.IdentifyUsers;
import utils.Useful;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.sql.SQLException;

@WebServlet(name = "DeleteTutorServlet", urlPatterns = "/DeleteTutorServlet")
public class DeleteTutorServlet extends HttpServlet {

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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("application/json, charset=UTF-8");

        Useful message = new Useful();
        Gson gson = new Gson();
        String toSendJson = "";

        PrintWriter out = response.getWriter();

        Cookie toCheck[] = request.getCookies();


        if (IdentifyUsers.identifyIdCookie(toCheck)) {
            if (IdentifyUsers.identifyRoleCookie(toCheck)) {

                try {
                    int idDocente = Integer.parseInt(request.getParameter("idDocente"));
                    boolean check = dao.checkTutor(idDocente);

                    if (check) {
                        dao.deleteDocente(idDocente);
                        message = new Useful("Tutor correctly deleted", 1, null);
                    } else {
                        message = new Useful("Tutor doesn't exist", -1, null);
                    }
                } catch (SQLException | NumberFormatException e) {
                    System.out.println(e.getMessage());
                    message = new Useful("There was some problem querying the tutor", -1, null);
                    Type type = new TypeToken<Useful>() {
                    }.getType();
                    toSendJson = gson.toJson(message, type);
                    out.write(toSendJson);
                    out.flush();
                }
            } else {
                message = new Useful("Sorry you don't have admin privileges", -1, null);
            }
        } else {
            message = new Useful("Sorry your sessionId doesn't match", -1, null);
        }
        Type type = new TypeToken<Useful>() {
        }.getType();
        toSendJson = gson.toJson(message, type);
        out.write(toSendJson);
        out.flush();
    }
}
