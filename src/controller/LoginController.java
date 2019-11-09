package controller;

import beans.UserBean;
import dao.LoginDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/login")
public class LoginController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            UserBean user = new UserBean();
            user.setLogin(request.getParameter("login"));
            user.setHaslo(request.getParameter("haslo"));

            LoginDao.login(user);

            if (user.getLogin().equals("") || user.getHaslo().equals("")){
                request.setAttribute("fieldAlert", "2px solid red");
                request.setAttribute("alert", "Pola nie mogą być puste!");
                request.getRequestDispatcher("login.jsp").forward(request, response);
                return; // w celu usunięcia wyjątków
            }
            if(user.isZalogowany()) { //Wyświetlanie informacji Witaj: login
                request.setAttribute("powitanie", user.getLogin());
                request.getRequestDispatcher("userLogged.jsp").forward(request, response);
                return;
            }
            if (user.isZalogowany()) {
                response.sendRedirect("userLogged.jsp"); //Przejście na stronę zalogowania
            } else {
                request.setAttribute("alert", "Nie znaleziono użytkownika w bazie!");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}






