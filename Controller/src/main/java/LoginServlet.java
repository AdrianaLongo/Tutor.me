import com.google.gson.Gson;
import dao.DAO;
import dao.Utente;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

/** Si prende cura dell'operazione di Login:
 * 1) Recupera i campi Nome e Cognome dalla request inviata dal frontend
 * 2) Prova a recuperare dal db l'oggetto Utente con i valori presi prima dalla request
 * 3) Se l'oggetto recuperato è diverso da null (quindi nel db esistono nome e cognome dell'Utente), creo un'HTTP
 * session e setto degli attributi per indicare dei dati dell'Utente in un posto accesibile anche al frontend
 * 4) Se il login non va, do al frontend un Json composto da due parti: un messaggio ed un numero generale di failure -1
 * */
@WebServlet(name = "LoginServlet", urlPatterns = "/LoginServlet")
public class LoginServlet extends HttpServlet {

    DAO dao = null;
    Utente user;
    Gson gson = new Gson();

    public void init(ServletConfig conf) throws ServletException {
        super.init(conf);
        ServletContext ctx = conf.getServletContext(); //prendo il context per accedere a web.xml
        String url = ctx.getInitParameter("DB-Url"); //indirizzo DB nel web.xml
        String user = ctx.getInitParameter("user");
        String pwd = ctx.getInitParameter("password");
        dao = new DAO (url, user, pwd); //creo un nuovo oggetto DAO, vedere costruttore in DAO
        System.out.println("LoginServlet inizializzata");

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Entrato in doPost");
        response.setContentType("text/html, charset=UTF-8");
        System.out.println("seContentType fatta");
        RequestDispatcher reqDisp = request.getRequestDispatcher("/Logout.html"); //stabilisce la risorsa a cui andrà/tornerà
        System.out.println("reqDisp fatta");
        //ContainerRequestContext requestContext = null;
        /*
        if(session.USER_AGENT == "Android") { // è un soft check sul nome del parametro e non il contenuto, minchiata ?
            response.setContentType("application/json, charset=UTF-8");
        }//chiedere a Giorgio il nome dell'HTTP session parameter al posto di USER_AGENT
        else {
            response.setContentType("text/html, charset=UTF-8"); //nope deve comunicare in json in entrambi i lati
        }
        */
        //prendo la sessione
        PrintWriter out = response.getWriter();


        //Questo andrà cambiato per accettare Json e non parametri html
        String username = request.getParameter("username"); //getParameter recupera dal campo Nome nell'<input>Html il valore
        System.out.println("username arrivato nella servlet: " + username);
        String password = request.getParameter("password");
        System.out.println("pw arrivata nella servlet: " + password);


        try {
            user = dao.retrieveUtente(username, password);
            System.out.println("L'utente esiste");
        }
        catch (SQLException e) {
            throw new ServletException(e.getMessage());
        }
        finally {
            if (user != null) {
                if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                    HttpSession session = request.getSession(); //se l'utente corrisponde ai dati inseriti, creo la sessione
                    System.out.println("Sei loggato");
                    /*
                    Cookie cookies[] =request.getCookies();
                    if (cookies != null) {
                        for (Cookie cookie : cookies) {
                            if (cookie.getName().equals("JSESSIONID")) {
                                System.out.println(cookie.getValue());
                            }
                        }
                    }
                     */
                    session.setAttribute("login", "true");  //setto degli attributi nella session da far persistere
                    session.setAttribute("username", user.getUsername());
                    session.setAttribute("ruoloUtente", user.getRuolo()); //setto il ruolo per definire i componenti in cui ha accesso l'utente
                    session.setAttribute("idUtente", user.getId()); //un pò un capriccio ma magari serve
                    Useful error = new Useful("Successful login", 1);
                    String Json = gson.toJson(error);
                    out.println(Json);//mando un json al fronto di mancata operazione
                    out.flush();
                    reqDisp.forward(request, response); //questo è necessario ???
                }
                else {
                    System.out.println("Spiace username/password non corrispondono");
                    Useful error = new Useful("Login unsuccessful", -1);
                    String Json = gson.toJson(error);
                    out.println(Json);//mando un json al fronto di mancata operazione
                    out.flush();
                }
            }
            else  {
                System.out.println("User è null");
                Useful error = new Useful("Login unsuccessful", -1);
                String Json = gson.toJson(error);
                out.println(Json);//mando un json al fronto di mancata operazione
                out.flush();

            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}