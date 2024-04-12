package util;

public class StringUtils {
	
	// SQL Queries
	public static final String REGISTER_USER = "INSERT INTO user"
			+ "(username, firstName, lastName, userType, address, contactNumber, email, password)" 
			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	public static final String GET_ALL_USER = "SELECT * FROM user";
    public static final String GET_LOGIN_USER  = "SELECT * FROM user WHERE username = ? AND password = ?";
    
	public static final String GET_USERNAME = "SELECT COUNT(*) FROM user WHERE username = ?";
	public static final String GET_CONTACT_NUMBER = "SELECT COUNT(*) FROM user WHERE contactNumber = ?";
	public static final String GET_EMAIL = "SELECT COUNT(*) FROM user WHERE email = ?";
	
	// SQL Queries
	
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
	
	// enum reserach
	
	// Start Message
    public static final String SUCCESS_REGISTER_MESSAGE = "Sucessfully Registered";
    public static final String LOGIN_REGISTER_MESSAGE = "Sucessfully Logged in";
    public static final String ERROR_REGISTER_MESSAGE = "Please correct the form data.";
    public static final String SERVER_ERROR_MESSAGE = "An unexpected server error occurred.";
    public static final String USERNAME_ERROR_MESSAGE = "Username is already registered.";
    public static final String EMAIL_ERROR_MESSAGE = "Email is already registered.";
    public static final String PHONE_NUMBER_ERROR_MESSAGE = "Phone number is already registered.";
    public static final String PASSWORD_UMNATCHED_ERROR_MESSAGE = "Password is not matched.";
    
    public static final String SUCCESS_MESSAGE = "successMessage";
    public static final String ERROR_MESSAGE = "errorMessage";
    // End Messages
    
    // JSP route
    public static final String LOGIN_PAGE = "/pages/login.jsp";
    public static final String REGISTER_PAGE = "/pages/register.jsp";
    public static final String CUSTOMER_HOME_PAGE = "/pages/home.jsp";
    // JSP route
}
