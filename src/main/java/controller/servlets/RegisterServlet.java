package controller.servlets;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.database.DatabaseController;
import model.UserModel;
import util.StringUtils;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/RegisterServlet" })
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	DatabaseController dbController = new DatabaseController();

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
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
		// TODO Auto-generated method stub
		
		String userName = request.getParameter(StringUtils.USER_NAME);
		String firstName = request.getParameter(StringUtils.FIRST_NAME);
		String lastName = request.getParameter(StringUtils.LAST_NAME);
		String userType= request.getParameter(StringUtils.USER_TYPE);
		String contactNumber = request .getParameter(StringUtils.CONTACT_NUMBER);
		String address= request.getParameter(StringUtils.ADDRESS) ;
		String email = request.getParameter(StringUtils.EMAIL);
		String password = request.getParameter(StringUtils.PASSWORD);
		String retypePassword = request.getParameter(StringUtils.RETYPE_PASSWORD);
		
		UserModel userModel = new UserModel (firstName, lastName, userType, address, contactNumber, email, userName, password);
		
		if (password.equals(retypePassword)) {
			int result = dbController.registerUser(userModel);
			
			if(result == 1) {
				request.setAttribute(StringUtils.SUCCESS_MESSAGE, StringUtils.SUCCESS_REGISTER_MESSAGE );
				response.sendRedirect(request.getContextPath() + StringUtils.LOGIN_PAGE); 
				// .sendRedirect(request.getContextPath(),<path>) will provide the absolute path
			} else if (result == 0) {
				// Redirect to the same register page with form data mistake
				String errorMessage = "Please correct the form data.";
				request.setAttribute(StringUtils.ERROR_MESSAGE, StringUtils.ERROR_REGISTER_MESSAGE);
				request.getRequestDispatcher(StringUtils.REGISTER_PAGE).forward(request, response);
				// getRequestDispatcher() is required to have absolute path in jsp pages.
			}  
			else {
				// Redirect to the same register page with server error
				String errorMessage = "An unexpect server error ocurred.";
				request.setAttribute(StringUtils.ERROR_MESSAGE, StringUtils.SERVER_ERROR_MESSAGE);
				request.getRequestDispatcher(StringUtils.REGISTER_PAGE).forward(request, response);
			}
		}
		
	}

}
