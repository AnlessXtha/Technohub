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

@WebFilter("/*")
public class AuthenticationFilter implements Filter {
    public AuthenticationFilter() {}

    public void destroy() {}

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String uri = req.getRequestURI();

        if (uri.endsWith(".css") || uri.endsWith(".png") || uri.endsWith(".jpg")|| uri.endsWith(StringUtils.CUSTOMER_HOME_PAGE)
                || uri.endsWith(StringUtils.LOGIN_PAGE)) {
            chain.doFilter(request, response);
            return;
        }

        boolean isLogin = uri.endsWith(StringUtils.URL_LOGIN);
        boolean isRegister = uri.endsWith(StringUtils.URL_REGISTER);
        boolean isCustomerHome = uri.endsWith(StringUtils.URL_CUSTOMER_HOME);
        boolean isAdminDashboard = uri.endsWith(StringUtils.URL_ADMIN_DASHBOARD);
        boolean isAddProduct = uri.endsWith(StringUtils.URL_ADD_PRODUCT);

        boolean isLoginServlet = uri.endsWith(StringUtils.SERVLET_URL_LOGIN);
        boolean isLogoutServlet = uri.endsWith(StringUtils.SERVLET_URL_LOGOUT);
        boolean isRegisterServlet = uri.endsWith(StringUtils.SERVLET_URL_REGISTER);
        boolean isProductListServlet = uri.endsWith(StringUtils.SERVLET_URL_ADDPRODUCT);
        boolean isAddProductServlet = uri.endsWith(StringUtils.SERVLET_URL_ADDPRODUCT);
        boolean isHomeListServlet = uri.endsWith(StringUtils.SERVLET_URL_HOME_LIST_PAGE);
        
        HttpSession session = req.getSession(false);

        boolean isLoggedIn = session != null && session.getAttribute("userName") != null;


        if (!isLoggedIn) {
            if (!isLogin && !isLoginServlet && !isRegister && !isRegisterServlet) {
                res.sendRedirect(req.getContextPath() + StringUtils.LOGIN_PAGE);
            } else {
                chain.doFilter(request, response);
            }
        } else if (isLoggedIn && !(!isLogin || isLogoutServlet)) {
        	res.sendRedirect(req.getContextPath() + StringUtils.SERVLET_URL_HOME_LIST_PAGE);
        } 
        else {
            int userType = (int) session.getAttribute("userType");

            if (userType == 0 && (isAdminDashboard || isAddProduct || isAddProductServlet || isProductListServlet)) {
                res.sendRedirect(req.getContextPath() + StringUtils.SERVLET_URL_HOME_LIST_PAGE);
            } else if (userType == 1 && (isCustomerHome || isHomeListServlet)) {
                res.sendRedirect(req.getContextPath() + StringUtils.SERVLET_URL_PRODUCTLIST);
            } else {
                chain.doFilter(request, response);
            }
        }
    }

    public void init(FilterConfig fConfig) throws ServletException {}
}
