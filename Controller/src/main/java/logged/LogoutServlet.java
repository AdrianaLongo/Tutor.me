package logged;

import com.google.gson.reflect.TypeToken;
import utils.IdentifyUsers;

import utils.Useful;
import com.google.gson.Gson;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;

@WebServlet(name = "LogoutServlet", urlPatterns = "/LogoutServlet")
public class LogoutServlet extends HttpServlet {

    public void init(ServletConfig conf) throws ServletException {

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html, charset=UTF-8");

        PrintWriter out = response.getWriter();

        Gson gson = new Gson();
        Useful message;
        String Json;

        Cookie[] toInvalidate = request.getCookies();

        if (toInvalidate != null) {
            if (IdentifyUsers.identifyIdCookie(toInvalidate)) {
                for (Cookie cookie : toInvalidate) {
                    cookie.setValue("");
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                }
                message = new Useful("Logout successful", 1, null);
            } else {
                message = new Useful("Your id doesn't match the one in store", -1, null);

            }
        }else{
            message = new Useful("I don't think you're logged", -1, null);
        }

        Type type = new TypeToken<Useful>() {
        }.getType();
        Json = gson.toJson(message, type);
        out.write(Json);
        out.flush();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
