package controller.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.database.DatabaseController;
import model.ProductModel;
import util.StringUtils;

/**
 * Servlet implementation class ProductListServletCustomer
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/ProductListCustomerServlet" })
public class ProductListCustomerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private DatabaseController databaseController = new DatabaseController();    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductListCustomerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        List<ProductModel> productsListCustomer= databaseController.getAllProductsInfo();
        request.setAttribute("productsListCustomer", productsListCustomer);
        request.getRequestDispatcher(StringUtils.PRODUCTS_PAGE).forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}