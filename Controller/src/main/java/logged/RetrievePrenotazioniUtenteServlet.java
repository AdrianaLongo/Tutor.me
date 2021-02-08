package logged;

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
 * Recupera le prenotazioni dell'utente basandosi sull'attributo segnato nella sessione recuperata ad inizio e
 * creata al login.
 * Il parametro false in request.getSession impedisce di creare una sessione nuova in caso non esista (rovinerebbe
 * i parametri segnati al login)
 */

@WebServlet(name = "RetrievePrenotazioniUtenteServlet", urlPatterns = "/RetrievePrenotazioniUtenteServlet")
public class RetrievePrenotazioniUtenteServlet extends HttpServlet {
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
        //HttpSession s = request.getSession(false);

        ArrayList<Prenotazione> prenotazioni;
        PrintWriter out = response.getWriter();

        String Json;
        Gson gson = new Gson();
        Useful message = new Useful();


        //String jSessionId = s.getId();
        String idToVerify = request.getParameter("jSessionId");

        Cookie cookies[] = request.getCookies();


        if (Useful.identifyCookie(cookies)) {
            //String ruoloUtente = (String) s.getAttribute("ruoloUtente");
            //if (ruoloUtente.equals("Utente") || ruoloUtente.equals("Admin")) {

            try {
                String idUtentee = "1"; //(String) s.getAttribute("idUtente");
                int idUtente = Integer.parseInt(idUtentee);
                prenotazioni = dao.retrievePrenotazioniUtente(idUtente);

                Type type = new TypeToken<ArrayList<Prenotazione>>() {
                }.getType();
                String jsonPrenotazioni = gson.toJson(prenotazioni, type);

                out.print(jsonPrenotazioni);
                out.close();

            } catch (SQLException | NumberFormatException ex) {
                System.out.println(ex.getMessage());
                Useful error = new Useful("Unable to retrieve reservations", -1, null);
                Type type = new TypeToken<Useful>() {
                }.getType();
                Json = gson.toJson(error, type);

                out.println(Json);//mando un json al fronto di mancata operazione
                out.flush();
            }
        } else {
            message = new Useful("Sorry your role doesn't match the requirements", -1, null);
        }
        Type type = new TypeToken<Useful>() {
        }.getType();
        Json = gson.toJson(message, type); //trasforma l'oggetto in una stringa Json

        /*else{
            message = new Useful("Sorry you're not logged", -1, null);
            Type type = new TypeToken<Useful>() {
            }.getType();
            Json = gson.toJson(message, type); //trasforma l'oggetto in una stringa Json

        }*/
        out.print(Json);
        out.flush();
    }
}
