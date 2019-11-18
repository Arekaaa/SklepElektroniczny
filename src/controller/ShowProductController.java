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

@WebServlet("/showProduct")
public class ShowProductController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        try {
                ProductDao productDao = new ProductDao();
                int metoda =0;
                List<ProductBean> listaProduktow = productDao.showProducts();

                if(productDao.isNieZnaleziono()){
                    productDao.setMetodaSortowania(metoda);
                    request.setAttribute("metoda",metoda);
                    request.setAttribute("powitanie", LoginDao.getWitaj());
                    request.setAttribute("iloscProduktow", productDao.getIlosc());
                    request.setAttribute("kwotaProduktow",productDao.getKwota());
                    request.setAttribute("pustyWynik", "Nie znaleziono produktów");
                    request.getRequestDispatcher("userLogged.jsp").forward(request, response);
                }
                else {
                    productDao.setMetodaSortowania(metoda);
                    request.setAttribute("metoda",metoda);
                    request.setAttribute("powitanie", LoginDao.getWitaj());
                    request.setAttribute("iloscProduktow", productDao.getIlosc());
                    request.setAttribute("kwotaProduktow",productDao.getKwota());
                    request.setAttribute("listaProduktow", listaProduktow);
                    request.getRequestDispatcher("userLogged.jsp").forward(request, response);
                }
            } catch (ServletException e) {
            e.printStackTrace();
            throw new RuntimeException("Błąd związany z servletem ShowProduct");
            }
        }
    }

