package logged;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
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
import java.lang.reflect.Type;
import java.sql.SQLException;

/**
 * 1) Prendo l'http session con parametro false per non creare una session nuova
 * 2) Controllo che la sessione esista, controllo il ruolo Utente, prendo il parametro idUtente dalla sessione (creato
 * 3) durante il login; il resto dei parametri li prendo dallla quest
 * 4) Faccio un tentativo di parsare i due id, in caso non siano numeri lancio l'eccezione
 * 5) Verifico la disponibilità del docente in quello slot: in caso la prenotazione sia possibile prenoto
 * Mando 3 messaggi diversi...
 * 1) in caso la reservation sia avvenuta
 * 2) in caso in cui catturo un errore SQL con i dati
 * 3) in caso in cui si sia capito che il tutor sia già occupato
 */

@WebServlet(name = "EffettuaPrenotazioneServlet", urlPatterns = "/EffettuaPrenotazioneServlet")
public class EffettuaPrenotazioneServlet extends HttpServlet {

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

        PrintWriter out = response.getWriter(); //per comunicazione messaggio "Avvenuto"

        int resstate = 0;
        String Json;
        Gson gson = new Gson();
        Useful message = new Useful();
        boolean disponibilita;

        HttpSession s = request.getSession(false);
        String jSession = request.getParameter("jSessionId");

        if (s.getId().equals(jSession)) {
            String ruoloUtente = (String) s.getAttribute("ruoloUtente");

            if (ruoloUtente.equals("utente") || ruoloUtente.equals("admin")) {

                String utente = s.getAttribute("idUtente").toString(); //getParameter recupera dal campo Nome nell'<input>Html il valore
                String docente = request.getParameter("idDocente");
                String slot = request.getParameter("slot");
                String nomeCorso = request.getParameter("nomeCorso");

                System.out.println("73 EffettuaprenotazioneServlet: Sto leggendo i dati passati dal client: " + "idUtente: " + utente + " " +
                        "idDocente: " + docente + " " + "slot: " + slot + " " + "nomeCorso: " + nomeCorso);
                try {

                    int utenteint = Integer.parseInt(utente); //Trasforma in int la stringa utente
                    int docenteint = Integer.parseInt(docente);
                    disponibilita = dao.isDisponibile(slot, docenteint); //controlla la disponibilità del prof

                    if (disponibilita) {
                        dao.prenotaRipetizione(nomeCorso, docenteint, utenteint, slot); //scrive nel db la prenotazione
                        resstate = 1; //stato risposte per poi creare l'oggetto Utils.Useful giusto (giusto messaggio)
                    } else {
                        resstate = 0;
                    }

                } catch (SQLException | NumberFormatException e) {
                    System.out.println(e.getMessage());
                    resstate = -1;

                } finally {

                    if (resstate == 1) {
                        message = new Useful("Successfully added reservation", 1, null); //vedere class Utils.Useful
                        System.out.println("Successfully made reservation reservation");
                    } else if (resstate == -1) {
                        message = new Useful("Unsuccessfully added reservation", -1, null);
                        System.out.println("Unsuccessful  reservation");
                    } else {
                        message = new Useful("That tutor is already occupied", -1, null);
                        System.out.println("Unsuccessfully deleted reservation");
                    }

                    Type type = new TypeToken<Useful>() {}.getType(); //genera il token corrispondente ad oggetto Utils.Useful
                    Json = gson.toJson(message, type); //trasforma l'oggetto in una stringa Json

                    out.print(Json);
                    out.flush();
                }
            }
        }
        else {
            message = new Useful("Sorry you're not logged", -1, null);
            Type type = new TypeToken<Useful>() {}.getType(); //genera il token corrispondente ad oggetto Utils.Useful
            Json = gson.toJson(message, type); //trasforma l'oggetto in una stringa Json

            out.print(Json);
            out.flush();
        }

    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}