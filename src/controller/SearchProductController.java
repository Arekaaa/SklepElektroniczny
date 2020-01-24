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

@WebServlet("/searchProduct")
public class SearchProductController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
                ProductDao productDao = new ProductDao();
                productDao.setTypWyszukiwania(request.getParameter("typWyszukiwania"));
                productDao.setWprowadzonaWartosc(request.getParameter("szukaj"));
                String typWyszukiwania = request.getParameter("typWyszukiwania");
                String wprowadzonaWartosc = request.getParameter("szukaj");
                int metoda;

                 // ZMIENNE DOTYCZĄCE PAGINACJI
                int rodzajPaginacji = 2;
                int strona = 1;
                int rekordyNaStrone = 10;
                if(request.getParameter("strona") != null) {
                     strona = Integer.parseInt(request.getParameter("strona"));
                }
                productDao.sumujRekordyWyszukane();
                int liczbaRekordow = productDao.getLiczbaRekordow();
                int liczbaStron = (int) Math.ceil(liczbaRekordow * 1.0 / rekordyNaStrone);

                List<ProductBean> listaProduktowWyszukana = productDao.searchProduct((strona-1)*rekordyNaStrone,rekordyNaStrone); //Do naszej listy produktów inicjalizujemy dane odczytane z fieldów JSP
                                                                                                    // aby metoda searchProduct w klasie ProductDao miała do nich dostęp

                if(productDao.isNieZnaleziono()){
                    metoda =0;
                    productDao.setMetodaSortowania(metoda);
                    request.setAttribute("metoda",metoda);
                    request.setAttribute("powitanie", LoginDao.getWitaj());
                    request.setAttribute("iloscProduktow", productDao.getIlosc());
                    request.setAttribute("kwotaProduktow",productDao.getKwota());
                    request.setAttribute("pustyWynik", "Nie znaleziono produktów");
                    request.getRequestDispatcher("userLogged.jsp").forward(request, response);
                }
                else {
                    metoda = 1;
                    productDao.setMetodaSortowania(metoda);
                    request.setAttribute("metoda",metoda);
                    request.setAttribute("rodzajPaginacji",rodzajPaginacji);
                    request.setAttribute("typWyszukiwania",typWyszukiwania);
                    request.setAttribute("wprowadzonaWartosc",wprowadzonaWartosc);
                    request.setAttribute("powitanie", LoginDao.getWitaj());
                    request.setAttribute("iloscProduktow", productDao.getIlosc());
                    request.setAttribute("kwotaProduktow", productDao.getKwota());
                    request.setAttribute("listaProduktow", listaProduktowWyszukana);
                    request.setAttribute("liczbaStron", liczbaStron);
                    request.setAttribute("biezacaStrona", strona);
                    request.getRequestDispatcher("userLogged.jsp").forward(request, response);
                }
            } catch (NumberFormatException | ServletException e) {
            e.printStackTrace();
            throw new RuntimeException("Błąd związany z servletem SearchProduct");
            }
        }
}

