package controller;

import beans.ProductBean;
import dao.ProductDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/removeProduct")
public class RemoveProductController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try{
            ProductBean product = new ProductBean();
            ProductDao productDao = new ProductDao();
            product.setId(Integer.parseInt(request.getParameter("productID")));
            productDao.deleteProduct(product);
            request.getRequestDispatcher("/showProduct").forward(request, response);
        } catch (NumberFormatException | ServletException e) {
            e.printStackTrace();
            throw new RuntimeException("Błąd związany z servletem RemoveProduct");
        }
    }
}

