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

@WebServlet("/showProduct")
public class ShowProductController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductDao productDao = new ProductDao();
            try {
                List<ProductBean> listaProduktow = productDao.showProducts();

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
                    request.setAttribute("kwotaProduktow",productDao.getKwota());
                    request.setAttribute("listaProduktow", listaProduktow);
                    request.getRequestDispatcher("userLogged.jsp").forward(request, response);
                }
            } catch (ServletException | SQLException ex) {
                throw new ServletException("Nie można pobrać produktów z bazy danych", ex);
            }
        }
    }

