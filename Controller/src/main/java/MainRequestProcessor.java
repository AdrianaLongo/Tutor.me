import dao.DAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

public class MainRequestProcessor implements IRequestProcessor{

    //Questa hashmap viene riempita dalla mainServlet, tramite il metodo registerAction,
    //con tutte le action definite nelle varie servlet.
    private HashMap<String, IAction> actions = new HashMap<>();
    private IAction defaultAction;
    private DAO dao;

    public MainRequestProcessor(IAction DefaultAction, DAO dao){
        this.defaultAction = DefaultAction;
        this.dao = dao;
    }


    @Override
    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String actName = request.getParameter("action");    //ad esempio login
        /*String method = request.getParameter("method");     //recupero il metodo necessario da richiamare una volta trovata la servlet*/
        IAction action = actions.get(actName);                 //recupero la servlet con la stessa action recuperata dalla richiesta

        //TODO una volta finito tutto rimuovere
        System.out.println("MainRequestProcessor: Ho recuperato la richiesta " + actions.get(actName));
        //System.out.println("MainRequestProcessor: Ho recuperato il metodo " + method);

        if(action != null) {
            actions.get(actName).execute(request, response, dao);
        }
        /*if(action != null){
            if(method.equals("doPost"))
                actions.get(actName).doPost(request, response);
            else if(method.equals(("doGet")))
                actions.get(actName).doGet(request, response);
        }else{
            if(method.equals("doPost"))
                defaultAction.doPost(request, response);
            else if(method.equals(("doGet")))
                defaultAction.doGet(request, response);
        }*/
    }

    @Override
    public void registerAction(IAction action) {
        actions.put(action.getName(), action);
    }
}
