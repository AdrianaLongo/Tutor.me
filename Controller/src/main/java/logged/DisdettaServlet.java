package logged;

import utils.Useful;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import dao.DAO;

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

/** Applica allo stato della prenotazione lo stato disdetta
 *    MA
 * non elimina la prenotazione in modo da poter ripresentare le prenotazioni nello storico
 * 1) Controllo che ci sia una sessione utente, parametro false nel request.getSession serve ad evitare che crei una
 * sessione in caso non ci sia. La sessione si crea a login
 * 2) Prendo l'attributo ruolo utente assegnato al login in caso esista
 * 3) Recupero il ruolo utente dalla sessione
 * 4) Controllo che il ruolo utente sia Utente o Admin per sicurezza
 * 5) Recupero dalla request idUtente, idDocente, lo slot ed il nome delCorso (da rivedere cosa recupero dal front-end
 *    per evitare di dare la possibilità all'utente di cancellare prenotazioni altri.
 *
 * Alcune gestioni di errori andranno riconsiderate in base all'implementazione nel front-end.
 * Quello che sarebbe utile secondo me sarebbe non dare la possibilità all'utente di inserire dati non suoi o dati
 * sbagliati (quest'ultimi grazie a menu a tendina)
 * */

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

        HttpSession s = request.getSession(false);

        if (s != null) {

            String jSessionId = s.getId().toString();
            String idToVerify = request.getParameter("jSessionId");

            if (jSessionId.equals(idToVerify)) {

                String ruoloUtente = s.getAttribute("ruoloUtente").toString();

                if (ruoloUtente.equals("Utente") || ruoloUtente.equals("Admin")) {


                    try {
                        int idPrenotazione = Integer.parseInt(request.getParameter("idPrenotazione"));
                        //int utenteint = Integer.parseInt(utente);
                        dao.deletePrenotazione(idPrenotazione);//trasforma stato prenotazione in disdetta
                        resstate = 1;

                    } catch (SQLException | NumberFormatException e) {
                        System.out.println(e.getMessage());
                        resstate = -1;

                    } finally {

                        if (resstate == 1) {
                            message = new Useful("Successfully deleted reservation", 1, null); //vedere class Utils.Useful
                            System.out.println("Successfully deleted reservation");
                        } else {
                            message = new Useful("Unsuccessfully deleted reservation", -1, null);
                            System.out.println("Unsuccessfully deleted reservation");
                        }

                        Type type = new TypeToken<Useful>() {}.getType(); //stabilisco il tipo di Utils.Useful
                        JsonResponse = gson.toJson(message, type); //serializzo l'oggetto in Json

                        out.print(JsonResponse); //printa la stringa geson
                        out.flush();
                    }
                }
            }
        } else {
            message = new Useful("Sorry you're not logged", -1, null);
            Type type = new TypeToken<Useful>() {}.getType(); //stabilisco il tipo di Utils.Useful
            JsonResponse = gson.toJson(message, type); //serializzo l'oggetto in Json

            out.print(JsonResponse); //printa la stringa geson
            out.flush();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
