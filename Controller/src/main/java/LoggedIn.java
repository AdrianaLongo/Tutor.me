import dao.DAO;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//TODO capire come implementare questa classe per riuscire a ricevere indietro il parametro che attesta se è loggato o meno
public class LoggedIn extends HttpServlet implements IAction {

    private final String name;

    public LoggedIn(String name) {
        this.name = name;
    }

    public LoggedIn() {
        this.name = "logged_in";
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response, DAO dao) throws ServletException, IOException {

    }

    @Override
    public String getName() {
        return name;
    }

}
