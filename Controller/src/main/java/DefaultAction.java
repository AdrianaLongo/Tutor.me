import dao.DAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DefaultAction implements IAction {

    private final String name = "default";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response, DAO dao) throws ServletException, IOException {}

    @Override
    public String getName() {
        return name;
    }
}
