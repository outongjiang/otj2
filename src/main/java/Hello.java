import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
@WebServlet("/Hello")
public class Hello extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri=req.getRequestURI();
        Method m=null;
        String methodName=uri.substring(uri.lastIndexOf("/")+1);
        System.out.println(methodName);
        try {
            m=this.getClass().getDeclaredMethod(methodName,HttpServletRequest.class,HttpServletResponse.class);
            System.out.println("对比class:");
            System.out.println(HttpServletResponse.class+"\t"+resp.getClass());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        try {
            m.setAccessible(true);
            m.invoke(this,req,resp);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("servlet...");
    }

}
