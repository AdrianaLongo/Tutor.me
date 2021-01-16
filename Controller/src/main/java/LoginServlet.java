
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

@WebServlet(name = "LoginServlet", urlPatterns = "/LoginServlet")
public class LoginServlet extends HttpServlet implements IAction {

    private final String name;
    DAO dao = null;
    Utente user;
    Gson gson = new Gson();


    public LoginServlet() {
        this.name = "login";
    }

    public void init(ServletConfig conf) throws ServletException {
        super.init(conf);
        ServletContext ctx = conf.getServletContext(); //prendo il context per accedere a web.xml
        String url = ctx.getInitParameter("DB-Url"); //indirizzo DB nel web.xml
        String user = ctx.getInitParameter("user");
        String pwd = ctx.getInitParameter("password");
        dao = new DAO(url, user, pwd); //creo un nuovo oggetto DAO, vedere costruttore in DAO
        System.out.println("LoginServlet: Sono nella init del login");
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html, charset=UTF-8");
        RequestDispatcher reqDisp = request.getRequestDispatcher("/Logout.html");

        //ContainerRequestContext requestContext = null;
        /*
        if(session.USER_AGENT == "Android") { // è un soft check sul username del parametro e non il contenuto, minchiata ?
            response.setContentType("application/json, charset=UTF-8");
        }//chiedere a Giorgio il username dell'HTTP session parameter al posto di USER_AGENT
        else {
            response.setContentType("text/html, charset=UTF-8"); //nope deve comunicare in json in entrambi i lati
        }
        */
        //prendo la sessione
        PrintWriter out = response.getWriter();

        //Questo andrà cambiato per accettare Json e non parametri html
        String username = request.getParameter("username"); //getParameter recupera dal campo Nome nell'<input>Html il valore
        String password = request.getParameter("password");
        System.out.println("LoginServlet: " + username + " " + password);

        try {
            user = dao.retrieveUtente(username, password);
            System.out.println("LoginServlet: Ho trovato l'utente");
            System.out.println("user: " + user.getUsername() + " password: " + user.getPassword());
        } catch (SQLException e) {
            System.out.println("LoginServlet: Non ho trovato l'utente");
            throw new ServletException(e.getMessage());
        } finally {
            if (user != null) {
                if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                    HttpSession session = request.getSession(); //se l'utente corrisponde ai dati inseriti, creo la sessione
                    System.out.println("LoginServlet: Sei loggato");
                    System.out.println("LoginServlet: " + user);
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
                    session.setAttribute("Login", "true");
                    session.setAttribute("User", username);
                    session.setAttribute("ruoloUtente", user.getRuolo());
                    session.setAttribute("Idutente", user.getId());
                    Useful error = new Useful("Successful login", 1);
                    String Json = gson.toJson(error);
                    out.println(Json);//mando un json al fronto di mancata operazione
                    out.flush();
                    reqDisp.forward(request, response);
                } else {
                    System.out.println("Spiace username/password non corrispondono");
                    Useful error = new Useful("Login unsuccessful", -1);
                    String Json = gson.toJson(error);
                    out.println(Json);//mando un json al fronto di mancata operazione
                    out.flush();
                }
            } else {
                System.out.println("LoginServlet: Spiace il username non corrisponde");
                Useful error = new Useful("Login unsuccessful", -1);
                String Json = gson.toJson(error);
                out.println(Json);//mando un json al fronto di mancata operazione
                out.flush();
            }
        }
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response, DAO dao) throws ServletException, IOException {
        response.setContentType("text/html, charset=UTF-8");
        RequestDispatcher reqDisp = request.getRequestDispatcher("/Logout.html");

        //ContainerRequestContext requestContext = null;
        /*
        if(session.USER_AGENT == "Android") { // è un soft check sul username del parametro e non il contenuto, minchiata ?
            response.setContentType("application/json, charset=UTF-8");
        }//chiedere a Giorgio il username dell'HTTP session parameter al posto di USER_AGENT
        else {
            response.setContentType("text/html, charset=UTF-8"); //nope deve comunicare in json in entrambi i lati
        }
        */
        //prendo la sessione
        PrintWriter out = response.getWriter();

        //Questo andrà cambiato per accettare Json e non parametri html
        String username = request.getParameter("username"); //getParameter recupera dal campo Nome nell'<input>Html il valore
        String password = request.getParameter("password");
        System.out.println("LoginServlet: " + username + " " + password);

        try {
            user = dao.retrieveUtente(username, password);
            System.out.println("LoginServlet: Ho trovato l'utente");
            System.out.println("user: " + user.getUsername() + " password: " + user.getPassword());
        } catch (SQLException e) {
            System.out.println("LoginServlet: Non ho trovato l'utente");
            throw new ServletException(e.getMessage());
        } finally {
            if (user != null) {
                if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                    HttpSession session = request.getSession(); //se l'utente corrisponde ai dati inseriti, creo la sessione
                    System.out.println("LoginServlet: Sei loggato");
                    System.out.println("LoginServlet: " + user);
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
                    session.setAttribute("Login", "true");
                    session.setAttribute("User", username);
                    session.setAttribute("ruoloUtente", user.getRuolo());
                    session.setAttribute("Idutente", user.getId());
                    Useful error = new Useful("Successful login", 1);
                    String idUtenteJson = gson.toJson((String) session.getAttribute("idUtente"));
                    response.addHeader("idUtente",idUtenteJson);
                    String Json = gson.toJson(error);
                    out.println(Json);
                    out.flush();
                    reqDisp.forward(request, response);
                } else {
                    System.out.println("Spiacente username/password non corrispondono");
                    Useful error = new Useful("Login unsuccessful", -1);
                    String Json = gson.toJson(error);
                    out.println(Json);//mando un json al fronto di mancata operazione
                    out.flush();
                }
            } else {
                System.out.println("LoginServlet: Spiacente l' username non corrisponde");
                Useful error = new Useful("Login unsuccessful", -1);
                String Json = gson.toJson(error);
                out.println(Json);//mando un json al fronto di mancata operazione
                out.flush();
            }
        }
    }
    @Override
    public String getName() {
        return name;
    }
}
