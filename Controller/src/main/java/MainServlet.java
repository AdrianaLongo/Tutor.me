
import dao.DAO;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "MainServlet", urlPatterns = "/MainServlet")
public class MainServlet extends HttpServlet {
    private IRequestProcessor requestProcessor;
    private DAO dao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext ctx = config.getServletContext(); //prendo il context per accedere a web.xml
        String url = ctx.getInitParameter("DB-Url"); //indirizzo DB nel web.xml
        String user = ctx.getInitParameter("user");
        String pwd = ctx.getInitParameter("password");

        System.out.println("PopulateCorsiServlet: Sto inizializzando");

        dao = new DAO(url, user, pwd); //creo un nuovo oggetto DAO, vedere costruttore in DAO

        requestProcessor = new MainRequestProcessor(new DefaultAction(), dao);
        requestProcessor.registerAction(new LoginServlet());
        requestProcessor.registerAction(new PopulateCorsiServlet());
        requestProcessor.registerAction(new CercaTutorServlet());
        requestProcessor.registerAction(new DisponibilitaTutorServlet());
        requestProcessor.registerAction(new EffettuaPrenotazioneServlet());
        System.out.println("MainServlet: ho inizializzato la mainservlet");
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        System.out.println("MainServlet: Ho fatto processRequest della main servlet");
        requestProcessor.processRequest(request,response);

    }

    /*protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        System.out.println("MainServlet: Ho fatto processRequest della main servlet");
        String servlet = request.getParameter("action");
        switch(servlet){
            case "login":

        }

    }*/

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }
}
