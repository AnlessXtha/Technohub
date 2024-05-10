package controller.servlets;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.database.DatabaseController;
import model.CartModel;
import model.OrderModel;
import model.ProductModel;
import model.UserModel;
import util.StringUtils;

/**
 * Servlet implementation class CheckoutServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/CheckoutServlet" })
public class CheckoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DatabaseController databaseController = new DatabaseController();

	public CheckoutServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession userSession = request.getSession(false);
		String sessionUsername = (String) userSession.getAttribute("userName");
		if (sessionUsername != null) {
		    UserModel user = databaseController.getCurrentUserInfoByUserName(sessionUsername);
		    int cartID = (int) userSession.getAttribute("cartID");
		    List<CartModel> cartProductsList = databaseController.getAllProductsFromCart(cartID);

		    try {
		    	LocalDate orderDate = LocalDate.now();
		    	Date convertedOrderDate = Date.valueOf(orderDate);
		        double orderTotal = calculateOrderTotal(cartProductsList);
		        String username = user.getUsername();
		        String orderStatus = "Pending"; 
		        
		        int orderID = addOrder(convertedOrderDate, orderTotal, username, orderStatus);
		       
		        
		        for (CartModel cartProduct : cartProductsList) {
		            int productID = cartProduct.getProductID();
		            int orderQuantity = cartProduct.getCartProductQuantity();
		            double lineTotal = cartProduct.getCartLineTotal();
		            addOrderProductDetails(orderID, productID, orderQuantity, lineTotal);
		            updateProductStock(productID, orderQuantity);
		        }
		        
		        request.getSession().removeAttribute("cartProductsList");
		        response.sendRedirect(request.getContextPath() + StringUtils.SERVLET_URL_ORDERDETAILS);
		    } catch (Exception e) {
		        e.printStackTrace();
		    	}
		    }
		}

	// Calculate total order amount
	private double calculateOrderTotal(List<CartModel> cartProductsList) {
		double total = 0;
		for (CartModel cartProduct : cartProductsList) {
			total += cartProduct.getCartLineTotal();
		}
		return total;
	}

	// Insert order into the database
	private int addOrder(Date orderDate, double orderTotal, String username, String orderStatus) {
		OrderModel order = new OrderModel(orderDate, orderTotal, username, orderStatus);
		DatabaseController dbController = new DatabaseController();
		return dbController.addOrder(order);
	}

	// Insert order product details into the database
	private void addOrderProductDetails(int orderID, int productID, int orderQuantity, double lineTotal) {
		OrderModel orderProductDetails = new OrderModel(orderID, productID, orderQuantity, lineTotal);
		DatabaseController dbController = new DatabaseController();
		dbController.addOrderProductDetails(orderProductDetails);
	}

	// Update product stock quantity in the database
	private void updateProductStock(int productID, int orderQuantity) {
		DatabaseController dbController = new DatabaseController();
		ProductModel product = dbController.getCurrentProductInfo(productID);
		if (product != null) {
			int newStock = product.getStock() - orderQuantity;
			product.setStock(newStock);
			dbController.updateProductStock(product);
		}
	}
}
