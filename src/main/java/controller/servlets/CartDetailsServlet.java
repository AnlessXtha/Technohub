package controller.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.database.DatabaseController;
import model.CartModel;
import util.StringUtils;

/**
 * Servlet implementation class CartDetailsServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/CartDetailsServlet" })
public class CartDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private DatabaseController databaseController = new DatabaseController(); 
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartDetailsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession userSession = request.getSession(false);
		int cartID = (int) userSession.getAttribute("cartID");
		
		List<CartModel> cartProductsList= databaseController.getAllProductsFromCart(cartID);
		request.setAttribute("cartProductsList", cartProductsList);
		request.getRequestDispatcher(StringUtils.CART_PAGE).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
