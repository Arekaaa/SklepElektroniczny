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

@WebServlet("/removeProduct")
public class RemoveProductController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try{
            ProductBean product = new ProductBean();
            ProductDao productDao = new ProductDao();
            product.setId(Integer.parseInt(request.getParameter("productID")));

            productDao.deleteProduct(product);
            List<ProductBean> listaProduktow = productDao.showProducts();

            request.setAttribute("powitanie", LoginDao.getWitaj());
            request.setAttribute("iloscProduktow",productDao.getIlosc());
            request.setAttribute("kwotaProduktow",productDao.getKwota());
            request.setAttribute("listaProduktow", listaProduktow);
            request.getRequestDispatcher("userLogged.jsp").forward(request, response);
        } catch (NumberFormatException | ServletException e) {
            e.printStackTrace();
            throw new RuntimeException("Błąd związany z servletem RemoveProduct");
        }
    }
}

