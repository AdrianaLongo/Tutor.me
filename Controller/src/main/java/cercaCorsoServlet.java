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

//Fatta per poter cercare un corso in base al docente
@WebServlet(name = "cercaCorsoServlet", urlPatterns = "/cercaCorsoServlet")
public class cercaCorsoServlet extends HttpServlet {

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

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json, charset=UTF-8");
        PrintWriter out = response.getWriter();
        String idDocenteS = request.getParameter("idDocente"); //recupera il campo idDocente mandato dal frontend

        try {
            int idDocente = Integer.parseInt(idDocenteS);
            corsi = dao.mostraCorsiConDocenti(idDocente); //recupera i corsi tramite l'id del docente
            System.out.print("Corsi recuperati");
            Type type = new TypeToken<ArrayList<Corso>>() {}.getType(); //trova il tipo dell'oggetto
            String jsonCorso = gson.toJson(corsi, type); //e se io voglio passare più dati Json sulla stessa pagina ?
            out.print(jsonCorso);
            out.close();
        } catch(SQLException | NumberFormatException ex){
            System.out.println(ex.getMessage());
            Useful error = new Useful("Courses not retrieved", -1);
            Type typer = new TypeToken<Useful>() {}.getType();
            String Json = gson.toJson(error, typer);
            out.println(Json);//mando un json al fronto di mancata operazione
            out.flush();
        }

    }
}
