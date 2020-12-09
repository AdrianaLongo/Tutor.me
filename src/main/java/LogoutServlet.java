import dao.DAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "LogoutServlet", urlPatterns = "/LogoutServlet")
public class LogoutServlet extends HttpServlet {

    public void init(ServletConfig conf) throws ServletException {

    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*
        if(session.USER_AGENT == "Android") { // è un soft check sul nome del parametro e non il contenuto, minchiata ?
            response.setContentType("application/json, charset=UTF-8");
        }//chiedere a Giorgio il nome dell'HTTP session parameter al posto di USER_AGENT
        else {
            response.setContentType("text/html, charset=UTF-8");
        }
        DOVRO GESTIRE ANCHE IL REQUEST DISPATCHER PER ANDROID / WEBAPP
        */
        response.setContentType("text/html, charset=UTF-8");

        HttpSession s = request.getSession(false);//Distruggo la sessione per evitare che logout+rilogin segnino parametri a cazzo
        //System.out.println(s.getAttribute("ruoloUtente"));
        s.invalidate();

        /*Cookie cookies[] =request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("JSESSIONID")) {
                    System.out.println(cookie.getValue());
                }
            }
        }

         */
        RequestDispatcher reqDisp = request.getRequestDispatcher("/index.html");
        reqDisp.forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
