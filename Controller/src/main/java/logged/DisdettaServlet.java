package logged;

import utils.IdentifyUsers;
import utils.Useful;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import dao.DAO;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.sql.SQLException;

@WebServlet(name = "DisdettaServlet", urlPatterns = "/DisdettaServlet")
public class DisdettaServlet extends HttpServlet {

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

        response.setContentType("application/json, charset=UTF-8");

        PrintWriter out = response.getWriter();

        int resstate = 0;
        String JsonResponse;
        Gson gson = new Gson();
        Useful message;

        Cookie toCheck[] = request.getCookies();

        if (IdentifyUsers.identifyIdCookie(toCheck)) {

            try {
                int idPrenotazione = Integer.parseInt(request.getParameter("idPrenotazione"));
                //int utenteint = Integer.parseInt(utente);
                System.out.println("idPrenotazione da cancellare: " + idPrenotazione);
                dao.deletePrenotazione(idPrenotazione);//trasforma stato prenotazione in disdetta
                resstate = 1;

            } catch (SQLException | NumberFormatException e) {
                System.out.println(e.getMessage());
                System.out.println("idPrenotazione da cancellare" +request.getParameter("idPrenotazione"));
                resstate = -1;

            } finally {

                if (resstate == 1) {
                    message = new Useful("Successfully deleted reservation", 1, null); //vedere class Utils.Useful
                    System.out.println("Successfully deleted reservation");
                } else {
                    message = new Useful("Unsuccessfully deleted reservation", -1, null);
                    System.out.println("Unsuccessfully deleted reservation");
                }

                Type type = new TypeToken<Useful>() {
                }.getType(); //stabilisco il tipo di Utils.Useful
                JsonResponse = gson.toJson(message, type); //serializzo l'oggetto in Json

                out.print(JsonResponse); //printa la stringa geson
                out.flush();
            }
        } else {
            message = new Useful("Sorry you're not logged", -1, null);
            Type type = new TypeToken<Useful>() {
            }.getType(); //stabilisco il tipo di Utils.Useful
            JsonResponse = gson.toJson(message, type); //serializzo l'oggetto in Json

            out.print(JsonResponse); //printa la stringa geson
            out.flush();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
