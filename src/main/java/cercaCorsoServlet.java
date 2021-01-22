import Utils.Useful;
import com.google.gson.Gson;
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

/** Recupera i corsi in base ad un idDocente fornito
 * All'interno del blocco try cerca di trasformare il valore fornito che prima era una stringa in Int0
 * In caso non si recuperi nessun corso restituisce un messaggio Json vuoto per lasciare alla frontend
 * la libertà di dire che niente è sbagliato, semplicemente con quel professore non ci sono corsi associati
 * */

@WebServlet(name = "cercaCorsoServlet", urlPatterns = "/cercaCorsoServlet")
public class cercaCorsoServlet extends HttpServlet {

        DAO dao = null;

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

        ArrayList<Corso> corsi;
        Gson gson = new Gson();

        boolean checkId;

        String idDocenteS = request.getParameter("idDocente");//recupera il campo idDocente mandato dal frontend

        try {

            int idDocente = Integer.parseInt(idDocenteS);
            checkId = dao.checkTutor(idDocente);

            if (checkId) {
                corsi = dao.mostraCorsiConDocenti(idDocente); //recupera i corsi tramite l'id del docente
                System.out.print("Corsi recuperati");
                Type type = new TypeToken<ArrayList<Corso>>() {}.getType(); //trova il tipo dell'oggetto
                String jsonCorso = gson.toJson(corsi, type);

                out.print(jsonCorso);
                out.close();
            }
            else {
                Useful error = new Useful("Professor doesn't exist", -1,null); //vedere class Utils.Useful
                Type type = new TypeToken<Useful>() {}.getType();
                String Json = gson.toJson(error, type); //serializza l'oggetto in una stringa formato Json

                out.println(Json);//mando un json al fronto di mancata operazione
                out.flush();
            }
        } catch(SQLException | NumberFormatException ex){

            System.out.println(ex.getMessage());

            Useful error = new Useful("Courses not retrieved", -1, null);
            Type typer = new TypeToken<Useful>() {}.getType(); //genero il tipo di Utils.Useful
            String Json = gson.toJson(error, typer); //serializzo l'oggetto Utils.Useful

            out.println(Json);//mando un json al front di mancata operazione
            out.flush();
        }

    }
}
