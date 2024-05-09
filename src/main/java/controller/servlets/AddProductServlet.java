package controller.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import controller.database.DatabaseController;
import model.ProductModel;
import util.StringUtils;

/**
 * Servlet implementation class AddProductServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { StringUtils.SERVLET_URL_ADDPRODUCT })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, 
maxFileSize = 1024 * 1024 * 10, 
maxRequestSize = 1024 * 1024 * 50) 
public class AddProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	DatabaseController dbController = new DatabaseController();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddProductServlet() {
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
		String productName = request.getParameter("productName");
		String productDescription = request.getParameter("productDescription");
		String productCategory = request.getParameter("productCategory");
		int stock = Integer.parseInt(request.getParameter("stock"));
		int unitPrice = Integer.parseInt(request.getParameter("unitPrice"));
		Part productImagePart = request.getPart("productImage");
		
		System.out.println("productName value:" + productName);
		System.out.println("productDescription value:" + productDescription);
		
		ProductModel productModel = new ProductModel (productName, productDescription, productCategory,
				stock, unitPrice, productImagePart);
		
		String savePath = StringUtils.IMAGE_DIR_SAVE_PATH_PRODUCT;
	    String fileName = productModel.getProductImageUrlFromPart();
	    if(!fileName.isEmpty() && fileName != null)
	    	productImagePart.write(savePath + fileName);
	    
	    int result = dbController.addProduct(productModel);
		System.out.println("register result value:" +result);
		
		switch(result) {
		case 1 -> {
			request.setAttribute(StringUtils.SUCCESS_MESSAGE, "Added Product");
			response.sendRedirect(request.getContextPath() + StringUtils.SERVLET_URL_PRODUCTLIST);
		}
		case 0 -> {
			request.setAttribute(StringUtils.ERROR_MESSAGE, "Incorrect format of data provided.");
			request.getRequestDispatcher(StringUtils.ADD_PRODUCT_PAGE).forward(request, response);
		}
		case -1 -> {
			request.setAttribute(StringUtils.ERROR_MESSAGE, StringUtils.SERVER_ERROR_MESSAGE);
			request.getRequestDispatcher(StringUtils.ADD_PRODUCT_PAGE).forward(request, response);
		}
		}
	}

}
