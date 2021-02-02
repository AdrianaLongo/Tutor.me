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

/** Segna la prenotazione come effettua, cambia quindi il campo stato della prenotazione */

@WebServlet(name = "PrenotazioneEffettuataServlet", urlPatterns = "/PrenotazioneEffettuataServlet")
public class PrenotazioneEffettuataServlet extends HttpServlet {

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

        String Json;
        Gson gson = new Gson();
        Useful message;

        response.setContentType("application/json, charset=UTF-8");
        HttpSession s = request.getSession(false);

        PrintWriter out = response.getWriter();

        if (s != null) {

            String jSessionId = s.getId();
            String idToVerify = request.getParameter("jSessionId");

            if(jSessionId.equals(idToVerify)) {
                String ruoloUtente = (String) s.getAttribute("ruoloUtente");
                if (ruoloUtente.equals("Utente") || ruoloUtente.equals("Admin")) {
                    try {

                        int idPrenotazione = Integer.parseInt(request.getParameter("idPrenotazione"));
                        dao.prenotazioneEffettuata(idPrenotazione);

                        message = new Useful("Prenotazione effettuata", 1, null);

                    } catch (SQLException | NumberFormatException ex) {

                        System.out.println(ex.getMessage());
                        message = new Useful("Reservation unsuccessful", -1, null);
                        Json = gson.toJson(message);

                        out.println(Json);//mando un json al fronto di mancata operazione
                        out.flush();
                    }
                }
                else {
                    message = new Useful("Sorry but you don't have admin privileges", -1, null);
                }
            }
            else {
                message = new Useful("Sorry but your session id doesn't match", -1, null);
            }
        }
        else {
            message = new Useful("Sorry you're not logged", -1, null);

        }
        Type type = new TypeToken<Useful>() {
        }.getType();
        Json = gson.toJson(message, type); //trasforma l'oggetto in una stringa Json
        out.print(Json);
        out.flush();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
