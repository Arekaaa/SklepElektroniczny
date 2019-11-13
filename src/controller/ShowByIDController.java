package controller;

import beans.ProductBean;
import dao.ProductDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/showByID")
public class ShowByIDController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductBean produkt = new ProductBean();
        ProductDao productDao = new ProductDao();
        produkt.setId(Integer.parseInt(request.getParameter("productID")));
        try{
            List<ProductBean> produktById = productDao.showProductByID(produkt);
            request.setAttribute("produktById", produktById);
            request.getRequestDispatcher("editProduct.jsp").forward(request, response);
            return;
        } catch (ServletException | SQLException e) {
            e.printStackTrace();
        }
        response.sendRedirect("editProduct.jsp");
    }
}
