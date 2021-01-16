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

@WebServlet(name = "DisdettaServlet", urlPatterns = "/DisdettaServlet")
public class DisdettaServlet extends HttpServlet {

    DAO dao;
    int resstate = 0;
    String JsonResponse;
    Gson gson = new Gson();
    Useful message;
    boolean checkDocente = false;

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
        response.setContentType("application/json, charset=UTF-8");
        if (s != null) {
            String ruoloUtente = (String) s.getAttribute("ruoloUtente");
            if (ruoloUtente.equals("Utente") || ruoloUtente.equals("Admin")) {
                String utente =(String) s.getAttribute("idUtente");
                String docente = request.getParameter("idDocente");
                String slot = request.getParameter("slot"); //sostituire con il parsing Json forse
                String nomeCorso = request.getParameter("nomeCorso"); //sostituire con il parsin Json forse
                try {
                    int utenteint = Integer.parseInt(utente);
                    int docenteint = Integer.parseInt(docente);
                    //int utenteint = Integer.parseInt(utente);
                    checkDocente = dao.checkTutor(docenteint);
                    if(checkDocente) {
                        dao.deletePrenotazione(nomeCorso, docenteint,utenteint, slot);//trasforma stato prenotazione in disdetta
                        resstate = 1;
                    }
                    else {
                        Useful error = new Useful("Tutor doesn't exist", -1); //vedere class Useful
                        Type type = new TypeToken<Useful>() {}.getType();
                        String Json = gson.toJson(error, type); //serializza l'oggetto in una stringa formato Json
                        out.println(Json);//mando un json al fronto di mancata operazione
                        out.flush();
                    }
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
        Type type = new TypeToken<Useful>() {}.getType(); //stabilisco il tipo di Useful
        JsonResponse = gson.toJson(message, type); //serializzo l'oggetto in Json
        out.print(JsonResponse); //printa la stringa geson
        out.flush(); //chiudo la chiamata
        //reqDisp.forward(request,response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}