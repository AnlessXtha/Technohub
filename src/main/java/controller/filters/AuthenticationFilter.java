package controller.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import util.StringUtils;

/**
 * Servlet Filter implementation class AuthenticationFilter
 */
@WebFilter("/*")
public class AuthenticationFilter implements Filter {

    /**
     * Default constructor. 
     */
    public AuthenticationFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// Cast the request and response to HttpServletRequest and HttpServletResponse
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		// Get the requested URI
		String uri = req.getRequestURI();
		System.out.println("Running Filter in"+uri);

		// Immediately allows requests for CSS files and the index page to pass
		// through without further checks.
		// If you want other files to directly open, include them in this
		// condition
		if (uri.endsWith(".css") || uri.endsWith(StringUtils.CUSTOMER_HOME_PAGE ) || uri.endsWith(StringUtils.LOGIN_PAGE)) {
			// it delegates control to the next filter in the chain, or if this filter is
			// the last one in the chain, to the servlet that services the incoming request.
			chain.doFilter(request, response);
			return;
		}

		// Check if the requested URI indicates a login page (e.g., /login.jsp)
		boolean isLogin = uri.endsWith(StringUtils.URL_LOGIN);
		boolean isRegister = uri.endsWith(StringUtils.URL_REGISTER);
		
		boolean isCustomerHome = uri.endsWith(StringUtils.URL_CUSTOMER_HOME);
		
		boolean isAdminDashboard = uri.endsWith(StringUtils.URL_ADMIN_DASHBOARD);
		boolean isAddProduct = uri.endsWith(StringUtils.URL_ADD_PRODUCT);
		boolean isErrorProduct = uri.endsWith(StringUtils.URL_CUSTOMER_HOME);
		
		

		// Check if the requested URI indicates a login servlet (e.g., /login)
		boolean isLoginServlet = uri.endsWith(StringUtils.SERVLET_URL_LOGIN);

		// Check if the requested URI indicates a logout servlet (e.g., /logout)
		boolean isLogoutServlet = uri.endsWith(StringUtils.SERVLET_URL_LOGOUT);
		boolean isRegisterServlet = uri.endsWith(StringUtils.SERVLET_URL_REGISTER);
		
		boolean isProductListServlet = uri.endsWith(StringUtils.SERVLET_URL_ADDPRODUCT);
		boolean isAddProductServlet = uri.endsWith(StringUtils.SERVLET_URL_ADDPRODUCT);
		
		// Attempt to retrieve the current session associated with the request.
		// If 'false' is passed as an argument and no session exists, it returns null.
		HttpSession session = req.getSession(false);

		// Check if a session exists and if the 'USERNAME' attribute is set in the
		// session.
		// If both conditions are true, it indicates that the user is logged in.
		boolean isLoggedIn = session != null && session.getAttribute("userName") != null;
		
		/*
		 * if (isLoggedIn) { String currentUser = (String)
		 * session.getAttribute("userName"); System.out.println("current user:" +
		 * currentUser);
		 * 
		 * int currentUserType = (int) session.getAttribute("userType");
		 * System.out.println("current user type:" + currentUserType); }
		 */

		// If the user is not logged in and the requested URI does not indicate an
		// attempt to access the login page or login servlet,
		// redirect the user to the login page to authenticate.
		
		
		if (!isLoggedIn) {
			if (!isLogin && !isLoginServlet && !isRegister && !isRegisterServlet) {
				res.sendRedirect(req.getContextPath() + StringUtils.LOGIN_PAGE);
			}else {
				chain.doFilter(request, response);
			}
		}
		// If the user is logged in and the requested URI does not indicate an attempt
		// to access the login page or logout servlet,
		// redirect the user to the home page to prevent access to login-related pages.
		else if (isLoggedIn && !(!isLogin || isLogoutServlet)) {
			System.out.println("hit1");

			int currentUserType = (int) session.getAttribute("userType");
			
			if (currentUserType == 0) {
				res.sendRedirect(req.getContextPath() + StringUtils.CUSTOMER_HOME_PAGE);				
			} else if (currentUserType == 1) {
				System.out.println("hit2");
					res.sendRedirect(req.getContextPath() + StringUtils.SERVLET_URL_PRODUCTLIST);				 
			}
			
			
			
		}
		
		
		// If none of the above conditions are met, allow the request to continue down
		// the filter chain.
		else {
			chain.doFilter(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
