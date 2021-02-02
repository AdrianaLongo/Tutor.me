package logged;

import com.google.gson.Gson;
import dao.DAO;
import utils.Useful;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "ExistingEntAssociationServlet", urlPatterns = "/ExistingEntAssociationServlet")
public class ExistingEntAssociationServlet extends HttpServlet {

    DAO dao = null;

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

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json, charset=UTF-8");

        HttpSession session = request.getSession(false);

        String opCode = request.getParameter("opCode");
        Gson gson = new Gson();
        String Json;
        Useful message;
        PrintWriter out = response.getWriter();

        int idDocente = 0;
        String nomeDocente = "";
        String cognomeDocente = "";
        String nomeCorso = "";

        if (session != null) {
            String sessionId = request.getParameter("jSessionId");
            if (sessionId.equals(session.getId())) {

                nomeCorso = request.getParameter("nomeCorso");
                nomeDocente = request.getParameter("nomeDocente");
                cognomeDocente = request.getParameter("cognomeDocente");
                idDocente = Integer.parseInt(request.getParameter("idDocente"));

                try {
                    dao.insertCorsoDocenteAssociation(nomeCorso, idDocente, nomeDocente, cognomeDocente);
                    message = new Useful("Successfully added association", 1, null);
                    Json = gson.toJson(message);
                    out.write(Json);
                    out.flush();

                }catch (SQLException e){
                    System.out.println(e.getMessage());
                    message = new Useful("There was some problem associating the existing courses", -1, null);
                    Json = gson.toJson(message);
                    out.write(Json);
                    out.flush();
                }
            }
        }



    }
}