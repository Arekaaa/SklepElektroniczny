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

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { // doGet przekazuje parametry w adresie URL od użytkownika do servletu

            UserBean user = new UserBean();
            user.setLogin(request.getParameter("rLogin"));
            user.setHaslo(request.getParameter("rHaslo"));
            user.setImie(request.getParameter("rImie"));
            user.setNazwisko(request.getParameter("rNazwisko"));
            user.setHasloRepeat(request.getParameter("rHasloRepeat"));
            user.setMail(request.getParameter("rMail"));

            String odczytLogin = request.getParameter("rLogin");
            String odczytHaslo = request.getParameter("rHaslo");
            String odczytImie = request.getParameter("rImie");
            String odczytNazwisko = request.getParameter("rNazwisko");
            String odczytHasloRepeat = request.getParameter("rHasloRepeat");
            String odczytMail = request.getParameter("rMail");

            RegisterDao.register(user); //Przesyłamy Dane odczytane z fieldów do klasy RegisterDao która potem je pobiera

        try{
            if (odczytLogin.equals("") || odczytHaslo.equals("") || odczytImie.equals("") || odczytNazwisko.equals("") ||
                    odczytHasloRepeat.equals("") || odczytMail.equals("")) {
                request.setAttribute("fieldAlert", "2px solid red");
                request.setAttribute("alert", "Pola nie mogą być puste!");
                request.getRequestDispatcher("register.jsp").forward(request, response);
                return; // Jeśli w servlecie występują funkcje "forward, sendRedirect" itp i jesteśmy w bloku if lub try/catch to aby kompilator mógł przeczytać następne bloki np, try/catch
                        // to musimy użyć "return"
            }
            else if (user.getHaslo().compareTo(user.getHasloRepeat()) != 0) {
                request.setAttribute("passwordAlertField", "2px solid red");
                request.setAttribute("alert", "Wpisane hasła muszą być identyczne!");
                request.getRequestDispatcher("register.jsp").forward(request, response);
                return;
            }
            else if(!odczytMail.contains("@")) {
                request.setAttribute("mailAlertField", "2px solid red");
                request.setAttribute("alertMail", "Twój mail jest niepoprawny!");
                request.getRequestDispatcher("register.jsp").forward(request, response);
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

            try{
                if (user.isZarejestrowany()) {
                    request.setAttribute("alertColor", "green");
                    request.setAttribute("alert", "Zarejestrowano !");
                    request.setAttribute("alert1", "Zapamiętaj swoje dane podawane podczas logowania: ");
                    request.setAttribute("daneLogowania", "Login: " + user.getLogin());
                    request.setAttribute("daneLogowania1", "Hasło: " + user.getHaslo());
                    request.getRequestDispatcher("register.jsp").forward(request, response);
                    //return; //tu nie trzeba return poniewaz nie ma juz w kodzie nastepnego bloku try/catch wiec nie trzeba sprawdzac nastepnych blokow
                } else {
                    request.setAttribute("alert", "red");
                    request.setAttribute("alert", "Już istnieje użytkownik o takim loginie!");
                    request.getRequestDispatcher("register.jsp").forward(request, response);

                }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
