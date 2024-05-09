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
 * Servlet implementation class SingleProductServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/SingleProductServlet" })
public class SingleProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DatabaseController databaseController = new DatabaseController();  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SingleProductServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("put triggered in do get modify");
    	String updateId = request.getParameter("updateID");
    	if (updateId != null && !updateId.isEmpty()) {
    	    int productID = Integer.parseInt(updateId);
    	    ProductModel productModel = databaseController.getCurrentProductInfo(productID);
    	    request.setAttribute("product", productModel);
    	    request.getRequestDispatcher(StringUtils.SINGLE_PRODUCT_PAGE).forward(request, response);
    	    System.out.println(productModel);
    	} else {
    		System.out.println("updateID parameter is null or empty.");
    	}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
