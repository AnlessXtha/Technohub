package controller.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import controller.database.DatabaseController;
import model.ProductModel;
import model.UserModel;
import util.StringUtils;

@WebServlet(asyncSupported = true, urlPatterns = { "/ModifyUserProfileServlet" })
public class ModifyUserProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	DatabaseController databaseController= new DatabaseController(); 
       
    public ModifyUserProfileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("put triggered in do get modify");
    	String updateId = request.getParameter("updateID");
    	if (updateId != null && !updateId.isEmpty()) {
    	    String username = updateId;
    	    UserModel userModel = databaseController.getCurrentUserInfoByUserName(username);
    	    request.setAttribute("user", userModel);
    	    request.getRequestDispatcher(StringUtils.EDIT_USER_PROFILE_PAGE).forward(request, response);
    	} else {
    		System.out.println("updateID parameter is null.");
    	}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String updateId = request.getParameter(StringUtils.UPDATE_ID);
		
		if (updateId != null && !updateId.isEmpty()) {
			doPut(request, response);
		}
	}
	
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("put triggered");
		
		String username = request.getParameter(StringUtils.USER_NAME);
		String firstName = request.getParameter(StringUtils.FIRST_NAME);
		String lastName = request.getParameter(StringUtils.LAST_NAME);
		String contactNumber = request .getParameter(StringUtils.CONTACT_NUMBER);
		String address= request.getParameter(StringUtils.ADDRESS) ;
		String email = request.getParameter(StringUtils.EMAIL);
		
		UserModel userModel = new UserModel (firstName, lastName, address, contactNumber, email, username);
	
        int result = databaseController.updateProfileInfo(userModel,username);
        System.out.println("register result value:" +result);
		switch(result) {
		case 1 -> {
			request.setAttribute(StringUtils.SUCCESS_MESSAGE, "Updated Profile");
			response.sendRedirect(request.getContextPath() + StringUtils.SERVLET_URL_USERPROFILE);
		}
		case 0 -> {
			request.setAttribute(StringUtils.ERROR_MESSAGE, "Incorrect values of data provided.");
			request.getRequestDispatcher(StringUtils.SERVLET_URL_EDITPROFILE).forward(request, response);
		}
		case -1 -> {
			request.setAttribute(StringUtils.ERROR_MESSAGE, StringUtils.SERVER_ERROR_MESSAGE);
			request.getRequestDispatcher(StringUtils.SERVLET_URL_EDITPROFILE).forward(request, response);
		}
		}
	}

}
