import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import dao.Corso;
import dao.DAO;
import dao.Docente;

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

@WebServlet(name = "cercaTutorServlet", urlPatterns = "/cercaTutorServlet")
public class cercaTutorServlet extends HttpServlet {
    DAO dao = null;
    ArrayList<Docente> tutor;
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
        //funzione mostraCorsi riga 107 DAO
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json, charset=UTF-8");

        PrintWriter out = response.getWriter();
    try{
        String corso = request.getParameter("corso");
        System.out.println("Mostro tutor per il corso " + corso);
        tutor = dao.mostraDocentiConCorso(corso);
        Type type = new TypeToken<ArrayList<Docente>>() {}.getType();
        String jsonTutor = gson.toJson(tutor, type); //e se io voglio passare più dati Json sulla stessa pagina ?
        out.print(jsonTutor);
        System.out.println(jsonTutor);
        out.close();
    } catch (SQLException e) {
        System.out.println(e.getMessage());
        Useful error = new Useful("Tutors not retrieved", -1);
        String Json = gson.toJson(error);
        out.println(Json);//mando un json al fronto di mancata operazione
        out.flush();
    }

    }
}
