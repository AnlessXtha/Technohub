package util;

import java.io.File;

public class StringUtils {
	
	public static final String PAGE_TITLE = "TechnoHub";
	
	public static final String IMAGE_DIR_PRODUCT = "Users\\Windows 10\\eclipse-workspace\\Technohub\\src\\main\\webapp\\resources\\images\\products\\";
	public static final String IMAGE_DIR_SAVE_PATH_PRODUCT = "C:" + File.separator + IMAGE_DIR_PRODUCT;
	
	public static final String IMAGE_DIR_USER = "Users\\Windows 10\\eclipse-workspace\\Technohub\\src\\main\\webapp\\resources\\images\\users\\";
	public static final String IMAGE_DIR_SAVE_PATH_USER = "C:" + File.separator + IMAGE_DIR_USER;

	// Start SQL Queries
	
	// For User
	public static final String REGISTER_USER = "INSERT INTO user"
			+ "(username, firstName, lastName, userType, address, contactNumber, email, password)" 
			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	public static final String GET_ALL_USER = "SELECT * FROM user";
    public static final String GET_LOGIN_USER  = "SELECT * FROM user WHERE username = ?";
    public static final String CHECK_USERTYPE  = "SELECT * FROM user WHERE username = ?";
    
    public static final String GET_SINGLE_USER_INFO = "SELECT * FROM user WHERE username = ?";
    
	public static final String QUERY_UPDATE_PROFILE = "UPDATE user " +
            "SET " +
            "    firstName = ?, " +
            "    lastName = ?, " +
            "    address = ?, " +
            "    contactNumber = ?, " +
            "    email = ?, " +
            "    username = ? " +
            "WHERE " +
            "    username = ?;";
    
    // User Validation
	public static final String GET_USERNAME = "SELECT COUNT(*) FROM user WHERE username = ?";
	public static final String GET_CONTACT_NUMBER = "SELECT COUNT(*) FROM user WHERE contactNumber = ?";
	public static final String GET_EMAIL = "SELECT COUNT(*) FROM user WHERE email = ?";
	
	// For Products
	public static final String ADD_PRODUCT = "INSERT INTO products"
			+ "(productName, productDescription, productCategory, stock, unitPrice, productImage)" 
			+ "VALUES (?, ?, ?, ?, ?, ?)";
	
	public static final String GET_ALL_PRODUCTS = "SELECT * FROM products";
	
	public static final String GET_SINGLE_PRODUCT = "SELECT * FROM products WHERE productID = ?";
	 
	public static final String GET_ID_PRODUCTS = "SELECT * FROM products WHERE productID = ?";
	
	public static final String QUERY_UPDATE_PRODUCT = "UPDATE products " +
            "SET " +
            "    productName = ?, " +
            "    productDescription = ?, " +
            "    productCategory = ?, " +
            "    stock = ?, " +
            "    unitPrice = ?, " +
            "    productImage = ? " +
            "WHERE " +
            "    productID = ?;";
	
    public static final String DELETE_PRODUCT = "DELETE FROM products WHERE productName = ?";
    
    // Cart
    
    public static final String GET_ALL_PRODUCTS_FROM_CART = 
    	    "SELECT " +
    	    "	 cpd.cartID," +
    	    "	 cpd.productID," +
    	    "    p.productName," +
    	    "    p.productImage," +
    	    "    p.unitPrice," +
    	    "    cpd.cartProductQuantity," +
    	    "    cpd.cartLineTotal " +
    	    "FROM " +
    	    "    cartproductdetails cpd " +
    	    "JOIN " +
    	    "    products p ON cpd.productID = p.productID " +
    	    "WHERE " +
    	    "    cpd.cartID = ?";
       
    public static final String REMOVE_PRODUCT_FROM_CART = "DELETE FROM cartproductdetails WHERE productID = ?";
    
    public static final String CLEAR_CART = "DELETE FROM cartproductdetails WHERE cartID = ?";
	
    // For Orders
    
    public static final String GET_ALL_ORDERS_FOR_USER = 
    	    "SELECT " +
    	    "    o.orderID, " +
    	    "    o.orderDate, " +
    	    "    o.orderStatus, " +
    	    "    p.productName, " +
    	    "    p.productImage, " +
    	    "    od.orderQuantity, " +
    	    "    od.lineTotal, " +
    	    "    o.orderTotal " +
    	    "FROM " +
    	    "    orders o " +
    	    "JOIN " +
    	    "    orderproductdetails od ON o.orderID = od.orderID " +
    	    "JOIN " +
    	    "    products p ON od.productID = p.productID " +
    	    "WHERE " +
    	    "    o.username = ?";

    
    // End SQL Queries
	
	// Common Variables
	public static final String USER_NAME= "username";
	public static final String FIRST_NAME = "firstName";
	public static final String LAST_NAME= "lastName";
	public static final String USER_TYPE= "userType";
	public static final String CONTACT_NUMBER= "contactNumber";
	public static final String ADDRESS =  "address";
	public static final String EMAIL = "email";
	public static final String PASSWORD = "password";
	public static final String RETYPE_PASSWORD= "retypePassword";
	// Common Variables
	
	// enum research
	
	// Start Message
    public static final String SUCCESS_REGISTER_MESSAGE = "Sucessfully Registered";
    public static final String LOGIN_REGISTER_MESSAGE = "Sucessfully Logged in";
    public static final String ERROR_REGISTER_MESSAGE = "Please correct the form data.";
    public static final String SERVER_ERROR_MESSAGE = "An unexpected server error occurred.";
    public static final String USERNAME_ERROR_MESSAGE = "Username is already registered.";
    public static final String EMAIL_ERROR_MESSAGE = "Email is already registered.";
    public static final String PHONE_NUMBER_ERROR_MESSAGE = "Phone number is already registered.";
    public static final String PASSWORD_UMNATCHED_ERROR_MESSAGE = "Password is not matched.";
    
    public static final String SUCCESSFUL_DELETE_MESSAGE = "Successfully Deleted!";
    public static final String ERROR_DELETE_MESSAGE = "Cannot delete the product!";
    
    public static final String SUCCESS_MESSAGE = "successMessage";
    public static final String ERROR_MESSAGE = "errorMessage";
    // End Messages
    
    // JSP route
    public static final String LOGIN_PAGE = "/pages/login.jsp";
    public static final String REGISTER_PAGE = "/pages/register.jsp";
    public static final String CUSTOMER_HOME_PAGE = "/pages/home.jsp";
    public static final String ADMIN_DASHBOARD_PAGE = "/pages/adminDashboard.jsp";
    public static final String ADD_PRODUCT_PAGE = "/pages/addProduct.jsp";
    
    public static final String HOME_LIST_PAGE = "/pages/home.jsp";
    public static final String PRODUCTS_PAGE = "/pages/products.jsp";
    public static final String SINGLE_PRODUCT_PAGE = "/pages/singleProductPage.jsp";
    public static final String ABOUT_US_PAGE = "/pages/aboutUs.jsp";
    public static final String CONTACT_US_PAGE = "/pages/contactUs.jsp";
    
    public static final String USER_PROFILE_PAGE = "/pages/userProfile.jsp";
    public static final String EDIT_USER_PROFILE_PAGE = "/pages/editProfile.jsp";
    public static final String CART_PAGE = "/pages/cart.jsp";
    public static final String HISTORY_PAGE = "/pages/history.jsp";
    
    public static final String UPDATE_PAGE = "/pages/updateProduct.jsp";
    
    public static final String URL_LOGIN = "/login.jsp";
    public static final String URL_REGISTER = "/register.jsp";
    
    public static final String URL_CUSTOMER_HOME = "/home.jsp";
    
    public static final String URL_ADMIN_DASHBOARD = "/adminDashboard.jsp";
    public static final String URL_ADD_PRODUCT = "/addProduct.jsp";
    public static final String URL_EDIT_USER_PROFILE = "/editProfile.jsp";

    
    // JSP route
    
    // Start Servlet Route
    public static final String SERVLET_URL_REGISTER = "/RegisterServlet";
    
    public static final String SERVLET_URL_LOGIN = "/LoginServlet";
 
    public static final String SERVLET_URL_LOGOUT = "/LogoutServlet";
    
    public static final String SERVLET_URL_ADDPRODUCT = "/AddProductServlet";
    
    public static final String SERVLET_URL_PRODUCTLIST = "/ProductListServlet";
    
    public static final String SERVLET_URL_SINGLE_PRODUCT = "/SingleProductServlet";
    
    public static final String SERVLET_URL_HOME_LIST_PAGE = "/HomeListServlet";
    
    public static final String SERVLET_URL_PRODUCTLISTCUSTOMER = "/ProductListCustomerServlet";
    
    public static final String SERVLET_URL_USERPROFILE= "/UserProfileServlet";

    public static final String SERVLET_URL_EDITPROFILE= "/EditProfileServlet";
    
    public static final String SERVLET_URL_UPDATE = "/ModifyServlet";
    
    public static final String SERVLET_URL_CARTDETAILS = "/CartDetailsServlet";
    
    public static final String SERVLET_URL_ORDERDETAILS = "/OrderDetailsServlet";
    
    public static final String SERVLET_URL_MODIFYCART = "/ModifyCartServlet";
    
    // End Servlet Route
    
	// Session and Cookies
	public static final String USER = "user";
	public static final String JSESSIONID = "JSESSIONID";
	public static final String LOGIN = "Login";
	public static final String LOGOUT = "Logout";
	
	public static final String UPDATE_ID= "updateID";
	public static final String DELETE_ID= "deleteID";
	public static final String REMOVE_ID= "removeID";
	// Session and Cookies
}
