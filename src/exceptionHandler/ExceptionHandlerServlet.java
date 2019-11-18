package exceptionHandler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/exceptionHandlerServlet")
public class ExceptionHandlerServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
        Integer kodBledu = (Integer) request.getAttribute("javax.servlet.error.status_code");
        String Uri = (String) request.getAttribute("javax.servlet.error.request_uri");

        if (Uri == null) {
            Uri = "Nieznane";
        }

    try {
        if (kodBledu == 404) {
            request.setAttribute("kodBledu", kodBledu);
            request.setAttribute("Uri", Uri);
            request.getRequestDispatcher("error.jsp").forward(request, response);
        } else {
            request.setAttribute("tresc", throwable.getMessage());
            request.setAttribute("nazwa", throwable.getClass().getName());
            request.setAttribute("funkcja", throwable.getStackTrace()[0].getMethodName());
            request.setAttribute("klasa", throwable.getStackTrace()[0].getClassName());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
        }catch(ServletException e){
        e.printStackTrace();
        }
    }
}
