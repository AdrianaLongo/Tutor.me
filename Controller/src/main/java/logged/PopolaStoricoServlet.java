package logged;

import utils.IdentifyUsers;
import utils.Useful;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import dao.DAO;
import dao.Prenotazione;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Restituisce tutte le prenotazione attive, effettuate e disdette
 */

@WebServlet(name = "PopolaStoricoServlet", urlPatterns = "/PopolaStoricoServlet")
public class PopolaStoricoServlet extends HttpServlet {

    DAO dao;

    public void init(ServletConfig conf) throws ServletException {

        super.init(conf);
        ServletContext ctx = conf.getServletContext(); //prendo il context per accedere a web.xml
        String url = ctx.getInitParameter("DB-Url"); //indirizzo DB nel web.xml
        String user = ctx.getInitParameter("user");
        String pwd = ctx.getInitParameter("password");
        dao = new DAO(url, user, pwd); //creo un nuovo oggetto DAO, vedere costruttore in DAO

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("application/json, charset=UTF-8");
        PrintWriter out = response.getWriter();

        ArrayList<Prenotazione> prenotazioni;
        String Json;
        Gson gson = new Gson();
        Useful message;

        Cookie toCheck[] = request.getCookies();


        if (IdentifyUsers.identifyIdCookie(toCheck)) {
            if(IdentifyUsers.identifyRoleCookie(toCheck)) {

                try {
                    prenotazioni = dao.retrievePrenotazioni(); //click tasto destro goTO
                    Type type = new TypeToken<ArrayList<Prenotazione>>() {
                    }.getType();
                    String jsonPrenotazioni = gson.toJson(prenotazioni, type);

                    out.print(jsonPrenotazioni);
                    out.close();

                } catch (SQLException ex) {

                    System.out.println(ex.getMessage());
                    message= new Useful("Courses not retrieved", -1, null);
                    Json = gson.toJson(message);

                    out.println(Json);//mando un json al fronto di mancata operazione
                    out.flush();
                }
            } else {
                Useful error = new Useful("Sorry you need to be an admin for this service", -1, null);
                Json = gson.toJson(error);

                out.println(Json);
                out.flush();
            }
        } else {

            Useful error = new Useful("Sorry you're not logged", -1, null);
            Type type = new TypeToken<Useful>() {}.getType();
            Json = gson.toJson(error, type); //trasforma l'oggetto in una stringa Json

            out.print(Json);
            out.flush();
        }

    }
}
