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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.ArrayList;

/** Recupera le prenotazioni dell'utente basandosi sull'attributo segnato nella sessione recuperata ad inizio e
 * creata al login.
 * Il parametro false in request.getSession impedisce di creare una sessione nuova in caso non esista (rovinerebbe
 * i parametri segnati al login) */

@WebServlet(name = "RetrievePrenotazioniUtenteServlet", urlPatterns = "/RetrievePrenotazioniUtenteServlet")
public class RetrievePrenotazioniUtenteServlet extends HttpServlet {
    DAO dao;

    String Json;
    Gson gson = new Gson();
    Useful message;

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
        //col false se non esiste una sessione, non ne crea una nuova
        HttpSession s = request.getSession(false);
        System.out.println("55 RetrievePrenotazioniServlet: sono dentro");
        ArrayList<Prenotazione> prenotazioni;
        PrintWriter out = response.getWriter();

        if (s != null) {

            System.out.println("61 RetrievePrenotazioniServlet: Sono oltre il primo if");
            String jSessionId = s.getId();
            String idToVerify = request.getParameter("jSessionId");

            if(jSessionId.equals(idToVerify)) {
                System.out.println("66 RetrievePrenotazioniServlet: Sono oltre il secondo if");
                String ruoloUtente = s.getAttribute("ruoloUtente").toString();
                if (ruoloUtente.equals("utente") || ruoloUtente.equals("admin")) {

                    System.out.println("66 RetrievePrenotazioniServlet: Sono oltre il terzo if");
                    try {
                        String idUtentee = s.getAttribute("idUtente").toString();

                        System.out.println("74 RetrievePrenotazioniServlet: idUtente= " + idUtentee);

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
                        String Json = gson.toJson(error);

                        out.println(Json);//mando un json al fronto di mancata operazione
                        out.flush();
                    }
                }
            }
        }
        else {
            message = new Useful("Sorry you're not logged", -1, null);
            Type type = new TypeToken<Useful>() {
            }.getType();
            Json = gson.toJson(message, type); //trasforma l'oggetto in una stringa Json

            out.print(Json);
            out.flush();
        }

    }
}