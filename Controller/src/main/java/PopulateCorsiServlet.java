import utils.Useful;
import com.google.gson.reflect.TypeToken;
import dao.Corso;
import dao.DAO;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.ArrayList;


import com.google.gson.Gson;

/**
 * Restituisce tutti i corsi sotto forma di Json
 */

@WebServlet(name = "PopulateCorsiServlet", urlPatterns = "/PopulateCorsiServlet")
public class PopulateCorsiServlet extends HttpServlet {
    DAO dao = null;
    ArrayList<Corso> corso;
    Gson gson = new Gson();
    Type type;

    public void init(ServletConfig conf) throws ServletException {

        super.init(conf);
        ServletContext ctx = conf.getServletContext(); //prendo il context per accedere a web.xml
        String url = ctx.getInitParameter("DB-Url"); //indirizzo DB nel web.xml
        String user = ctx.getInitParameter("user");
        String pwd = ctx.getInitParameter("password");
        dao = new DAO(url, user, pwd); //creo un nuovo oggetto DAO, vedere costruttore in DAO
        System.out.println("39 PopulateCorsiServlet: fine Init");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //processRequest(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //processRequest(request,response);
        response.setContentType("application/json, charset=UTF-8");
        //RequestDispatcher reqDisp = request.getRequestDispatcher("Logout.html");
        System.out.println("49 PopulateCorsiServlet: sono dentro");
        PrintWriter out = response.getWriter();
        try {
            corso = dao.mostraCorsi(); //prende tutti i corsi
            System.out.println("49 PopulateCorsiServlet: ho trovato i corsi: " + corso.toString());
            System.out.print("Corsi recuperati");
            type = new TypeToken<ArrayList<Corso>>() {
            }.getType(); //crea il token corrisp all'argomento passato
            String jsonCorsi = gson.toJson(corso, type); //e se io voglio passare più dati Json sulla stessa pagina ?
            out.print(jsonCorsi); //printa il Json
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            Useful error = new Useful("Courses not retrieved", -1, null); //oggetto messaggio da passare al front
            String Json = gson.toJson(error);//converte in Stringa l'oggetto messaggio
            out.println(Json);//mando un json al fronto di mancata operazione
        } finally {
            out.flush();
        }
    }
}