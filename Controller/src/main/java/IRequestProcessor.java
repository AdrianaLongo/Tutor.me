import dao.DAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface IRequestProcessor {
    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
    public void registerAction(IAction action);
}
