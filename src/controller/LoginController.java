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

            LoginDao.login(user); //Inicjalizujemy dane odczytane z fieldów JSP aby metoda login w klasie LoginDao miała do nich dostęp
            if (user.isZalogowany()) {
                request.getRequestDispatcher("/showProduct").forward(request, response);
            } else {
                request.setAttribute("alert", "Nie znaleziono użytkownika w bazie!");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } catch (ServletException e) {
            e.printStackTrace();
        }

    }
}






