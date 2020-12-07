import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import dao.DAO;
import dao.Docente;

import javax.servlet.RequestDispatcher;
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

@WebServlet(name = "DisdettaServlet", urlPatterns = "/DisdettaServlet")
public class DisdettaServlet extends HttpServlet {

    DAO dao;
    int resstate = 0;
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
        /*
        StringBuffer requestURL = request.getRequestURL();
        if (request.getQueryString() != null) {
            requestURL.append("?").append(request.getQueryString()); //blocco per prendere l'url della richiesta
        }                                                            // per poi attivare il reader
        String completeURL = requestURL.toString();
        useful.fetchJson(completeURL); //queste prime 5 righe siam sicuri che servano a qualcosa ?
        */
        PrintWriter out = response.getWriter();
        HttpSession s = request.getSession(false);
        if (s != null) {
            String ruoloUtente = (String) s.getAttribute("ruoloUtente");
            if (ruoloUtente == "Utente" || ruoloUtente == "Admin") {
                response.setContentType("application/json, charset=UTF-8");
                String utente = request.getParameter("idUtente"); //getParameter recupera dal campo Nome nell'<input>Html il valore
                String docente = request.getParameter("idDocente");
                int utenteint = Integer.parseInt(utente); //Trasforma in int la stringa utente
                int docenteint = Integer.parseInt(docente);
                String slot = request.getParameter("slot"); //sostituire con il parsing Json forse
                String nomeCorso = request.getParameter("nomeCorso"); //sostituire con il parsin Json forse
                try {
                    dao.deletePrenotazione(nomeCorso, docenteint, utenteint, slot);
                    resstate = 1;
                } catch (SQLException | NumberFormatException e) {
                    System.out.println(e.getMessage());
                    resstate = -1;
                } finally {
                    if (resstate == 1) {
                        message = new Useful("Successfully deleted reservation", 1); //vedere class Useful
                        System.out.println("Successfully deleted reservation");
                    } else {
                        message = new Useful("Unsuccessfully deleted reservation", -1);
                        System.out.println("Unsuccessfully deleted reservation");

                    }
                }
            }
        }
        else {
            message = new Useful("Sorry you're not logged", -1);
        }
        Type type = new TypeToken<Useful>() {}.getType();
        Json = gson.toJson(message, type); //trasforma l'oggetto in una stringa Json
        out.print(Json);
        out.flush();
        //reqDisp.forward(request,response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
