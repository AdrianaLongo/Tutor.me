import com.google.gson.Gson;
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
import java.util.ArrayList;

@WebServlet(name = "AddAssociationServlet", urlPatterns = "/AddAssociationServlet")
public class AddAssociationServlet extends HttpServlet {

    DAO dao = null;
    ArrayList<Corso> corsi;
    Gson gson = new Gson();
    String Json;

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
        String course = request.getParameter("nomeCorso");
        String nomeDocente = request.getParameter("nomeDocente");
        String cognomeDocente = request.getParameter("cognomeDocente");

       // try{
            //dao.insertCorsoDocenteAssociation();
       // }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
