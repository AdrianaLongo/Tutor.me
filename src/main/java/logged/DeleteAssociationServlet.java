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

@WebServlet(name = "DeleteAssociationServlet", urlPatterns = "/DeleteAssociationServlet")
public class DeleteAssociationServlet extends HttpServlet {

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

        PrintWriter out = response.getWriter();

        Gson gson = new Gson();
        String Json;

        int idDocente = 0;
        String nomeCorso = "";

        Useful message = new Useful();

        Cookie toCheck[] = request.getCookies();

        if (IdentifyUsers.identifyIdCookie(toCheck)) {

            if (IdentifyUsers.identifyRoleCookie(toCheck)) {

                    nomeCorso = request.getParameter("nomeCorso");
                    idDocente = Integer.parseInt(request.getParameter("idDocente"));
                    try {
                        dao.deleteCorsoDocenteAssociation(nomeCorso, idDocente);
                        message = new Useful("Successfully deleted association", 1, null);

                    } catch (SQLException e) {
                        System.out.println(e.getMessage());
                        message = new Useful("Unsuccessfully deleted association", -1, null);
                        Type type = new TypeToken<Useful>() {
                        }.getType();
                        Json = gson.toJson(message, type);
                        out.write(Json);
                        out.flush();
                    }
            }
            else {
                message = new Useful("Sorry but your role doesn't have access to this function", -1, null);
            }
        }
        else {
            message = new Useful("Sorry but you don't appear to be logged in", -1, null);
        }
        Type type = new TypeToken<Useful>() {
        }.getType();
        Json = gson.toJson(message, type);
        out.write(Json);
        out.flush();
    }
}
