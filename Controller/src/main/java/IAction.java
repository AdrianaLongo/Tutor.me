import dao.DAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//interfaccia necessaria per identificare la servlet da chiamare nella mainServlet
public interface IAction {
    /*public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;*/

    public void execute(HttpServletRequest request, HttpServletResponse response, DAO dao) throws ServletException, IOException;
    public String getName();
}
