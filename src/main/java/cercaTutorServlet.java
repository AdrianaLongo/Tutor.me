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
    boolean checkCourse = false;

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
        checkCourse = dao.checkCourse(corso);
        if (checkCourse == true) {
            tutor = dao.mostraDocentiConCorso(corso); //mostra i tutor in base al corso scelto
            System.out.print("Tutor recuperati");
            Type type = new TypeToken<ArrayList<Docente>>() {
            }.getType(); //stabilisce il tipo di Docente
            String jsonTutor = gson.toJson(tutor, type); //e se io voglio passare più dati Json sulla stessa pagina ?
            out.print(jsonTutor);//stampa il tutor
            out.close(); //chiude il PrintWriter della response
        }
        else {
            Useful error = new Useful("Course doesn't exist", -1); //vedere class Useful
            Type type = new TypeToken<Useful>() {}.getType();
            String Json = gson.toJson(error, type); //serializza l'oggetto in una stringa formato Json
            out.println(Json);//mando un json al fronto di mancata operazione
            out.flush();

        }
    } catch (SQLException e) {
        System.out.println(e.getMessage()); //recupera messaggio possibile errore query
        Useful error = new Useful("Courses not retrieved", -1); //vedere class Useful
        String Json = gson.toJson(error); //serializza l'oggetto in una stringa formato Json
        out.println(Json);//mando un json al fronto di mancata operazione
        out.flush();
    }

    }
}
