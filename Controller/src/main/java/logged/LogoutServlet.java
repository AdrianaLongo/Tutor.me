package logged;

import utils.Useful;
import com.google.gson.Gson;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Si prende cura dell'operazione di Logout: per farlo recupera la session.
 */
@WebServlet(name = "LogoutServlet", urlPatterns = "/LogoutServlet")
public class LogoutServlet extends HttpServlet {

    public void init(ServletConfig conf) throws ServletException {

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html, charset=UTF-8");

        PrintWriter out= response.getWriter();

        Gson gson = new Gson();
        Useful message = new Useful();
        String Json = "";

        String sessionId = request.getParameter("jSessionId");
        HttpSession s = request.getSession(false);//Distruggo la sessione per evitare che logout+rilogin segnino parametri a cazzo

        if (s != null) {
            if (s.getId().equals(sessionId)) {
                s.invalidate();
                message = new Useful("Logout successful", 1, null);
                Json = gson.toJson(message);

            } else {
                message = new Useful("I don't think you're logged", -1, null);
                Json = gson.toJson(message);
            }
        }else {
            message = new Useful("I don't think you're logged", -1, null);
            Json = gson.toJson(message);
        }
        System.out.println(("52 LogoutServlet: ti sei sloggato"));
        out.write(Json);
        out.flush();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}