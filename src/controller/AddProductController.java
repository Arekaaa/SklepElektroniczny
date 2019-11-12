package controller;

import beans.ProductBean;
import dao.ProductDao;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/addProduct")
public class AddProductController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ProductBean produkt = new ProductBean();
        ProductDao productDao = new ProductDao();
        produkt.setNazwa(request.getParameter("nazwa"));
        produkt.setProducent(request.getParameter("producent"));
        produkt.setCena(Float.parseFloat((request.getParameter("cena"))));
        produkt.setIlosc(Integer.parseInt(request.getParameter("ilosc")));

       productDao.addProduct(produkt); //Inicjalizujemy dane odczytane z fieldów JSP aby metoda addProduct w klasie ProductDao miała do nich dostęp

        try{
            if (produkt.isDodany()) {
                request.setAttribute("alertColor", "green");
                request.setAttribute("alert", "Dodano nowy produkt !");
                request.getRequestDispatcher("addProduct.jsp").forward(request, response);
            } else {
                request.setAttribute("alert", "red");
                request.setAttribute("alert", "Duplikat produktu! Taki produkt już istnieje w systemie!");
                request.getRequestDispatcher("addProduct.jsp").forward(request, response);

            }
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }
}