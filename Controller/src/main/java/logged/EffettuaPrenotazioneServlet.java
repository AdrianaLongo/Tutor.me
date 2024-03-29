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

//        HttpSession s = request.getSession(false);
        String jSession = request.getParameter("jSessionId");

        Cookie toCheck[] = request.getCookies();

        if (IdentifyUsers.identifyIdCookie(toCheck)) {

            String utente = IdentifyUsers.getUserId(toCheck); //getParameter recupera dal campo Nome nell'<input>Html il valore
            String docente = request.getParameter("idDocente");
            String slot = request.getParameter("slot");
            String nomeCorso = request.getParameter("nomeCorso");

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
                } else if (resstate == 0) {
                    message = new Useful("That tutor is already occupied", -1, null);
                    System.out.println("Unsuccessfully deleted reservation");
                }
            }
        } else {
            message = new Useful("Sorry you're not logged", -1, null);
        }

        Type type = new TypeToken<Useful>() {
        }.getType(); //genera il token corrispondente ad oggetto Utils.Useful
        Json = gson.toJson(message, type); //trasforma l'oggetto in una stringa Json

        out.print(Json);
        out.flush();

    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
