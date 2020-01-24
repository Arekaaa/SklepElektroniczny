package controller;

import beans.UserBean;
import dao.RegisterDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegisterController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException { // doGet przekazuje parametry w adresie URL od użytkownika do servletu
           try{
                UserBean user = new UserBean();
                user.setLogin(request.getParameter("rLogin"));
                user.setHaslo(request.getParameter("rHaslo"));
                user.setImie(request.getParameter("rImie"));
                user.setNazwisko(request.getParameter("rNazwisko"));
                user.setHasloRepeat(request.getParameter("rHasloRepeat"));
                user.setMail(request.getParameter("rMail"));

                 RegisterDao.register(user); //Inicjalizujemy dane odczytane z fieldów JSP aby metoda register w klasie RegisterDao miała do nich dostęp

                if (user.isZarejestrowany()) {
                    request.setAttribute("alertColor", "green");
                    request.setAttribute("alert", "Zarejestrowano !");
                    request.setAttribute("alert2", "Twoje dane logowania to: ");
                    request.setAttribute("daneLogowania", "Login: " + user.getLogin());
                    request.setAttribute("daneLogowania1", "Hasło: " + user.getHaslo());
                    request.getRequestDispatcher("register.jsp").forward(request, response);
                    //return --> tu nie trzeba return poniewaz nie ma juz w kodzie nastepnego bloku try/catch wiec nie trzeba sprawdzac nastepnych blokow
                } else {
                    request.setAttribute("alertColor", "red");
                    request.setAttribute("alert", "Taki login lub adres e-mail jest już zajęty! Spróbuj ponownie.");
                    request.getRequestDispatcher("register.jsp").forward(request, response);
                }
        } catch (ServletException e) {
               e.printStackTrace();
               throw new RuntimeException("Błąd związany z servletem RegisterController");
        }
    }
}
