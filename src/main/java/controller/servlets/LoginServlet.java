package controller.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.database.DatabaseController;
import model.UserModel;
import util.StringUtils;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { StringUtils.SERVLET_URL_LOGIN })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	DatabaseController dbController = new DatabaseController();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		PrintWriter printOut = response.getWriter();
		
		int loginResult = dbController.getUserInfo(userName, password);
		System.out.println(loginResult);
	    
	    int userType = dbController.checkUserType(userName);
	    System.out.println(userType);
	    
	    int cartID = dbController.getCurrentCartID(userName);
	    
		if (loginResult == 1) {
			
			//Login Successful
			HttpSession userSession = request.getSession();
			userSession.setAttribute("userName", userName);
			userSession.setAttribute("userType", userType);
			userSession.setAttribute("cartID", cartID);
			userSession.setMaxInactiveInterval(30*60);
			
			Cookie userNameCookie = new Cookie("username", userName);
			userNameCookie.setMaxAge(30*60);
			response.addCookie(userNameCookie);
			
			Cookie userTypeCookie = null;
			
			if (userType == 0) {
				 userTypeCookie = new Cookie("userType", "Customer");
			} else if (userType == 1) {
				 userTypeCookie = new Cookie("userType", "Admin");
			}
			
			if (userTypeCookie != null) {
			    userTypeCookie.setMaxAge(30*60);
			    response.addCookie(userTypeCookie);
			}
			
			Cookie userCartIDCookie = new Cookie("cartID", String.valueOf(cartID));
			userCartIDCookie.setMaxAge(30*60);
			response.addCookie(userCartIDCookie);
			
			if (userType == 0) {
				request.setAttribute(StringUtils.SUCCESS_MESSAGE, StringUtils.LOGIN_REGISTER_MESSAGE);
				response.sendRedirect(request.getContextPath() + StringUtils.SERVLET_URL_HOME_LIST_PAGE);
		    } else if (userType == 1) {
		    	request.setAttribute(StringUtils.SUCCESS_MESSAGE, StringUtils.LOGIN_REGISTER_MESSAGE);
				response.sendRedirect(request.getContextPath() + StringUtils.SERVLET_URL_PRODUCTLIST);
		    }		
			
		} else if (loginResult == 0) {
			printOut.println("<h1>Please enter the valid login creditaionals! </h1>");
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
