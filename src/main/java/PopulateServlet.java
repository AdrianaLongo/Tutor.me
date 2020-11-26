import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import dao.*;

import javax.servlet.RequestDispatcher;
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

@WebServlet(name = "PopulateServlet", urlPatterns = "/PopulateServlet")
public class PopulateServlet extends HttpServlet {
    ArrayList<Corso> corso;
    Gson gson = new Gson();

    public void init(ServletConfig conf) throws ServletException {
        super.init(conf);
        DAO.registerDriver();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Luca la mette nella doPost
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        response.setContentType("application/json, charset=UTF-8");
        RequestDispatcher reqDisp = request.getRequestDispatcher("index.html");
        corso = DAO.mostraCorsi();
        System.out.println("Corsi recuperati");
        Type type = new TypeToken<ArrayList<Corso>>(){}.getType();
        System.out.println("Creo arraylist");
        String jsonCorsi = gson.toJson(corso, type);
        System.out.println("Creo stringa jsonCorsi");
        PrintWriter out= response.getWriter();
        System.out.println("Stampo output");
        out.print(jsonCorsi);
        System.out.println(jsonCorsi);
        out.close();
        reqDisp.forward(request,response);
    }
}
