package controller;

import beans.UserBean;
import dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/login")
public class LoginController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter pw = response.getWriter();
        pw.println("Logowanie");*/
        boolean zalogowany;
        try {

            UserBean user = new UserBean();
            UserDao userDao = new UserDao();
            user.setLogin(request.getParameter("login"));
            user.setHaslo(request.getParameter("haslo"));

            user = UserDao.login(user);

            if (user.getLogin().equals("") || user.getHaslo().equals("")) {
                request.setAttribute("fieldSettings", "2px solid red");
                request.setAttribute("alert", "Pola nie mogą być puste!");
                request.getRequestDispatcher("login.jsp").forward(request, response);
                return; // w celu usunięcia wyjątków
            }
            if(user.isZalogowany() == true) { //Wyświetlanie informacji Witaj: login
                request.setAttribute("powitanie", user.getLogin());
                request.getRequestDispatcher("userLogged.jsp").forward(request, response);
                return;
            }
            if (user.isZalogowany() == true) {
                response.sendRedirect("userLogged.jsp"); //Przejście na stronę zalogowania
                return;
            } else {
                request.setAttribute("alert", "Nie znaleziono użytkownika w bazie!");
                request.getRequestDispatcher("login.jsp").forward(request, response);
                return;
            }

        } catch (Throwable theException) {
            System.out.println(theException);
        }

    }
}






