import utils.Useful;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import dao.DAO;
import dao.Slot;
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

@WebServlet(name = "DisponibilitaTutorServlet", urlPatterns = "/DisponibilitaTutorServlet")
public class DisponibilitaTutorServlet extends HttpServlet {

    DAO dao;

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

        Gson gson = new Gson();
        ArrayList<Slot> slotOccupati;
        ArrayList<Slot> slotLiberi;
        Type type;

        String idDocente = request.getParameter("idDocente");

        try {
            int idTutor = Integer.parseInt(idDocente);

            slotOccupati = dao.getSlotOccupati(idTutor);
            slotLiberi = Useful.getSlotLiberi(slotOccupati);

            type = new TypeToken<ArrayList<Slot>>() {}.getType(); //crea il token corrisp all'argomento passato
            String jsonCorsi = gson.toJson(slotLiberi, type); //e se io voglio passare più dati Json sulla stessa pagina ?

            out.print(jsonCorsi); //printa il Json
            out.close();
        }
        catch(SQLException | NumberFormatException e) {

            System.out.println(e.getMessage());
            Useful error = new Useful("Error in getting slots", -1, null); //oggetto messaggio da passare al front
            String Json = gson.toJson(error);//converte in Stringa l'oggetto messaggio

            out.println(Json);//mando un json al fronto di mancata operazione
            out.flush();
        }
    }
}
