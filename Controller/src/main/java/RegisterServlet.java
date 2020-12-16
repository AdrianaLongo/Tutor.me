import dao.DAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "RegisterServlet", urlPatterns = "/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    DAO dao = null;

    public void init(ServletConfig conf) throws ServletException {
        super.init(conf);
        ServletContext ctx = conf.getServletContext();
        String url = ctx.getInitParameter("DB-Url");
        String user = ctx.getInitParameter("user");
        String pwd = ctx.getInitParameter("password");
        dao = new DAO (user, pwd, url);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //sto usando Post perchè son dati sensibili e non voglio che si vedano nella barra dell'url
        response.setContentType("text/html, charset=UTF-8");
        PrintWriter out = response.getWriter();

        String utente = request.getParameter("Nome"); //getParameter recupera dal campo Nome nell'<input>Html il valore
        String cognome = request.getParameter("Cognome");

        HttpSession s = request.getSession();
        if (utente != null) //molto probabilmente io voglio un oggetto con nome Utente e tipo di utente
            s.setAttribute("nomeUtente", utente);

        try {
            dao.addUtente(utente, cognome); //aggiungo l'utente al database
        } catch (SQLException throwables) {
            ServletException ex = new ServletException(throwables.getMessage());
            throw ex; //Cerco di catturare la SQL exception come ServletException, un pò di ridondanza
        }

        RequestDispatcher reqDisp = getServletContext().getNamedDispatcher("index.html");
        reqDisp.forward(request, response); //se già arrivo da home con un include invece che forward, mi serve il forward ?
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
