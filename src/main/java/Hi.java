import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/Hi/*")
public class Hi extends Hello{
    protected void fun(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
        System.out.println("fun");
    }
}