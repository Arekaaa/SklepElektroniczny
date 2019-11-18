package controller;

import beans.ProductBean;
import dao.ProductDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/editProduct")
public class EditProductController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
       try{
            ProductBean produkt = new ProductBean();
            ProductDao productDao = new ProductDao();
            produkt.setId(Integer.parseInt(request.getParameter("ID")));
            produkt.setNazwa(request.getParameter("nazwa"));
            produkt.setProducent(request.getParameter("producent"));
            produkt.setCena(Float.parseFloat((request.getParameter("cena"))));
            produkt.setIlosc(Integer.parseInt(request.getParameter("ilosc")));

            productDao.editProduct(produkt); //Inicjalizujemy dane odczytane z fieldów JSP aby metoda editProduct w klasie ProductDao miała do nich dostęp

            if (produkt.isDodany()) {
                request.setAttribute("alertColor", "green");
                request.setAttribute("alert", "Produkt został poprawnie edytowany!");
                request.getRequestDispatcher("productChanged.jsp").forward(request, response);
            }
            else{
                request.setAttribute("alertColor", "red");
                request.setAttribute("alert", "Błąd podczas edycji produktu!");
                request.getRequestDispatcher("productChanged.jsp").forward(request, response);
            }
        } catch (NumberFormatException | ServletException e) {
           e.printStackTrace();
           throw new RuntimeException("Błąd związany z servletem EditProduct");
        }
        //response.sendRedirect("productChanged.jsp");
    }
}
