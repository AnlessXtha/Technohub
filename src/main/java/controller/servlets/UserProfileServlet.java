package controller.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.database.DatabaseController;
import model.UserModel;
import util.StringUtils;

/**
 * Servlet implementation class UserProfileServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/UserProfileServlet" })
public class UserProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DatabaseController databaseController = new DatabaseController();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserProfileServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession userSession = request.getSession(false);
		String username = (String) userSession.getAttribute("userName");

		if (username != null) {
			UserModel user = databaseController.getCurrentUserInfoByUserName(username);

			if (user != null) {
				request.setAttribute("user", user);
				request.getRequestDispatcher(StringUtils.USER_PROFILE_PAGE).forward(request, response);
			} else {
				response.getWriter().println("User not found");
			}
		} else {
			response.getWriter().println("No Current User");
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
