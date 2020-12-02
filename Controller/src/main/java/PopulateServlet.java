import com.google.gson.reflect.TypeToken;
import dao.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import com.google.gson.Gson;

@WebServlet(name = "PopulateServlet", urlPatterns = "/PopulateServlet")
public class PopulateServlet extends HttpServlet {
    DAO dao;
    ArrayList<Corso> corsi;
    Gson gson = new Gson();

    public void init(ServletConfig conf) throws ServletException {
        super.init(conf);

        /* OPZIONE 1 (Luca, opzione migliore):
         * Mantenere url, user e pwd nel web.xml e creare un nuovo DAO con questi parametri:
         * Nel DAO bisogna avere quindi un costruttore che abbia al suo interno la registrazione del driver

        ServletContext ctx = conf.getServletContext(); //prendo il context per accedere a web.xml
        String url = ctx.getInitParameter("DB-Url"); //indirizzo DB nel web.xml
        String user = ctx.getInitParameter("user");
        String pwd = ctx.getInitParameter("password");
        dao = new DAO (url, user, pwd);

         */

        /* OPZIONE 2 (Adri, meno sicura):
        * Tenere url, user e pwd come parametri privati nel DAO.java e registrare il driver con un metodo apposito */
        dao.registerDriver();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json, charset=UTF-8");
        corsi = dao.mostraCorsi();
        Type type = new TypeToken<ArrayList<Corso>>(){}.getType();
        String jsonCorsi = gson.toJson(corsi, type);
        PrintWriter out= response.getWriter();
        out.print(jsonCorsi);
        System.out.println(jsonCorsi);
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
