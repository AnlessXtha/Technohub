package controller.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.database.DatabaseController;
import util.StringUtils;

/**
 * Servlet implementation class ModifyCartServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/ModifyCartServlet" })
public class ModifyCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	DatabaseController databaseController= new DatabaseController(); 
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyCartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("put triggered in do post modify");
    	
		String removeID = request.getParameter(StringUtils.REMOVE_ID);
		
		System.out.println("removeID"+removeID);

		if (removeID != null && !removeID.isEmpty()) {
			doDelete(request, response);
		}

	}
	
	@Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("remove triggered");
        
        String productID = req.getParameter("removeID");
		int productId = Integer.parseInt(productID);
		
        if (databaseController.removeProductFromCart(productId) == 1) {
            req.setAttribute(StringUtils.SUCCESS_MESSAGE, StringUtils.SUCCESSFUL_DELETE_MESSAGE);
            resp.sendRedirect(req.getContextPath() + StringUtils.SERVLET_URL_CARTDETAILS);
        } else {
            req.setAttribute(StringUtils.ERROR_MESSAGE, StringUtils.ERROR_DELETE_MESSAGE);
            resp.sendRedirect(req.getContextPath() + StringUtils.SERVLET_URL_CARTDETAILS);
        }
    }

}
