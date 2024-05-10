package controller.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.database.DatabaseController;
import model.CartModel;
import model.ProductModel;
import util.StringUtils;

/**
 * Servlet implementation class AddToCartServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/AddToCartServlet" })
public class AddToCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	DatabaseController dbController = new DatabaseController();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddToCartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession userSession = request.getSession(false);
		int cartID = (int) userSession.getAttribute("cartID");
		
        int productID = Integer.parseInt(request.getParameter("productID"));
        int quantity = Integer.parseInt(request.getParameter("cartProductQuantity"));

        // Retrieve product details from the database using productID
        DatabaseController databaseController = new DatabaseController();
        ProductModel product = databaseController.getCurrentProductInfo(productID);

        if (product != null) {
            // Calculate total price
            double unitPrice = product.getUnitPrice();
            double totalPrice = unitPrice * quantity;

            // Store the product details in the user's cart
            CartModel cartItem = new CartModel(cartID, productID, quantity, totalPrice);
            int result = databaseController.addToCart(cartItem);

            switch(result) {
    		case 1 -> {
    			request.setAttribute(StringUtils.SUCCESS_MESSAGE, "Added To Cart");
    			response.sendRedirect(request.getContextPath() + StringUtils.SERVLET_URL_SINGLE_PRODUCT + "?updateID=" + productID);
    		}
    		case 0 -> {
    			request.setAttribute(StringUtils.ERROR_MESSAGE, "Stock Unavailable!!!");
    			request.getRequestDispatcher(StringUtils.SERVLET_URL_SINGLE_PRODUCT+ "?updateID=" + productID).forward(request, response);
    		}
    		case -1 -> {
    			request.setAttribute(StringUtils.ERROR_MESSAGE, StringUtils.SERVER_ERROR_MESSAGE);
    			request.getRequestDispatcher(StringUtils.SERVLET_URL_SINGLE_PRODUCT+ "?updateID=" + productID).forward(request, response);
    		}
    		}
        }
	}
}
