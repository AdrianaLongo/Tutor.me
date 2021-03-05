package logged;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import dao.DAO;
import dao.Prenotazione;
import dao.Utente;
import utils.IdentifyUsers;
import utils.Useful;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "RetrieveClientsServlet", value = "/RetrieveClientsServlet")
public class RetrieveClientsServlet extends HttpServlet {

    DAO dao;

    public void init(ServletConfig conf) throws ServletException {

        super.init(conf);
        ServletContext ctx = conf.getServletContext(); //prendo il context per accedere a web.xml
        String url = ctx.getInitParameter("DB-Url"); //indirizzo DB nel web.xml
        String user = ctx.getInitParameter("user");
        String pwd = ctx.getInitParameter("password");
        dao = new DAO(url, user, pwd); //creo un nuovo oggetto DAO, vedere costruttore in DAO

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json, charset=UTF-8");

        ArrayList<Utente> utentiPerHistory;
        PrintWriter out = response.getWriter();

        String Json;
        Gson gson = new Gson();
        Useful message = new Useful();

        Cookie cookies[] = request.getCookies();


        if (IdentifyUsers.identifyIdCookie(cookies)) {
            if (IdentifyUsers.identifyRoleCookie(cookies)) {

                try {
                    utentiPerHistory = dao.retrieveUtenti();
                    message = new Useful("Correctly retrieved users", 1,utentiPerHistory);
                }catch(SQLException e) {
                    System.out.println(e.getMessage());
                    message = new Useful("Had a problem at the Dao level", -1,null);
                }

            }else {
                message = new Useful("Sorry you don't have admin privileges", -1, null);
            }

        }else {
            message = new Useful("Doesn't seem that you're logged in", -1,null);
        }

        Type type = new TypeToken<Useful>() {
        }.getType();
        Json = gson.toJson(message, type);
        out.println(Json);
        out.flush();
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
