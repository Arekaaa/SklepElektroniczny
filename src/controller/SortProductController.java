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
import java.util.List;

@WebServlet("/sortProduct")
public class SortProductController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
            try{
                ProductDao productDao = new ProductDao();
                productDao.setTypWyszukiwania(request.getParameter("typ"));
                productDao.setWprowadzonaWartosc(request.getParameter("wartosc"));
                productDao.setTypSortowania(request.getParameter("sortID"));
                productDao.setDescOrAsc(request.getParameter("descOrAsc"));
                productDao.setMetodaSortowania(Integer.parseInt(request.getParameter("metoda")));
                String typWyszukiwania = request.getParameter("typ");
                String wprowadzonaWartosc = request.getParameter("wartosc");
                int metoda = Integer.parseInt(request.getParameter("metoda"));

                List<ProductBean> listaProduktowPosortowana = productDao.sortProductMethod(productDao); //Do naszej listy produktów inicjalizujemy dane odczytane z fieldów JSP
                // aby metoda searchProduct w klasie ProductDao miała do nich dostęp
                    request.setAttribute("metoda",metoda);
                    request.setAttribute("typWyszukiwania",typWyszukiwania);
                    request.setAttribute("wprowadzonaWartosc",wprowadzonaWartosc);
                    request.setAttribute("powitanie", LoginDao.getWitaj());
                    request.setAttribute("iloscProduktow", productDao.getIlosc());
                    request.setAttribute("kwotaProduktow", productDao.getKwota());
                    request.setAttribute("listaProduktow", listaProduktowPosortowana);
                    request.getRequestDispatcher("userLogged.jsp").forward(request, response);

            } catch (NumberFormatException | ServletException e) { // Wyjątek dotyczący odczytania w funkcji parse stringu zamiast liczby
                e.printStackTrace();
                throw new RuntimeException("Błąd związany z servletem SortProduct");
            }
    }
}
