package controller;

import beans.ProductBean;
import dao.LoginDao;
import dao.ProductDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/searchProduct")
public class SearchProductController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductBean product = new ProductBean();
        ProductDao productDao = new ProductDao();
        product.setTypWyszukiwania(request.getParameter("typWyszukiwania"));
        product.setWprowadzonaWartosc(request.getParameter("szukaj"));
        try {
                List<ProductBean> listaProduktowWyszukana = productDao.searchProduct(product); //Do naszej listy produktów inicjalizujemy dane odczytane z fieldów JSP
                                                                                                // aby metoda searchProduct w klasie ProductDao miała do nich dostęp
                if(productDao.isNieZnaleziono()){
                    request.setAttribute("powitanie", LoginDao.getWitaj());
                    request.setAttribute("iloscProduktow", productDao.getIlosc());
                    request.setAttribute("kwotaProduktow",productDao.getKwota());
                    request.setAttribute("pustyWynik", "Nie znaleziono produktów");
                    request.getRequestDispatcher("userLogged.jsp").forward(request, response);
                }
                else {
                    request.setAttribute("powitanie", LoginDao.getWitaj());
                    request.setAttribute("iloscProduktow", productDao.getIlosc());
                    request.setAttribute("kwotaProduktow", productDao.getKwota());
                    request.setAttribute("listaProduktow", listaProduktowWyszukana);
                    request.getRequestDispatcher("userLogged.jsp").forward(request, response);
                }
            } catch (ServletException | SQLException ex) {
                throw new ServletException("Nie można pobrać produktów z bazy danych", ex);
            }
        }
    }

