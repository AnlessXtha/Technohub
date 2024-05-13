package controller.servlets;

import java.io.IOException;
import java.util.List;

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

@WebServlet(asyncSupported = true, urlPatterns = { StringUtils.SERVLET_URL_UPDATE})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, 
maxFileSize = 1024 * 1024 * 10, 
maxRequestSize = 1024 * 1024 * 50) 
public class ModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	DatabaseController databaseController= new DatabaseController(); 

    public ModifyServlet() {
        super();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	System.out.println("put triggered in do get modify");
    	String updateId = request.getParameter("updateID");
    	if (updateId != null && !updateId.isEmpty()) {
    	    int productID = Integer.parseInt(updateId);
    	    ProductModel productModel = databaseController.getCurrentProductInfo(productID);
    	    request.setAttribute("product", productModel);
    	    request.getRequestDispatcher(StringUtils.UPDATE_PAGE).forward(request, response);
    	    System.out.println(updateId);
    	} else {
    		System.out.println("updateID parameter is null or empty.");
    	}

    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
    	System.out.println("put triggered in do post modify");
    	
		String updateId = request.getParameter(StringUtils.UPDATE_ID);
		String deleteId = request.getParameter(StringUtils.DELETE_ID);
		
    	if (updateId != null && !updateId.isEmpty()) {
			doPut(request, response);
		}

		if (deleteId != null && !deleteId.isEmpty()) {
			doDelete(request, response);
		}

	}

	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("put triggered");
		String productID = request.getParameter("updateID");
		int productId = Integer.parseInt(productID);
		String productName = request.getParameter("productName");
		String productDescription = request.getParameter("productDescription");
		String productCategory = request.getParameter("productCategory");
		int stock = Integer.parseInt(request.getParameter("stock"));
		int unitPrice = Integer.parseInt(request.getParameter("unitPrice"));
		Part productImagePart = request.getPart("productImage");
		
		System.out.println("productName value:" + productName);
		System.out.println("productImagePart value:" + productImagePart);
		
		ProductModel productModel = new ProductModel(productName, productDescription, productCategory,
                stock, unitPrice, productImagePart);
		
		String savePath = StringUtils.IMAGE_DIR_SAVE_PATH_PRODUCT;
	    String fileName = productModel.getProductImageUrlFromPart();
	    if(!fileName.isEmpty() && fileName != null)
	    	productImagePart.write(savePath + fileName);
        // Updating product information in the database
        int result = databaseController.updateProductInfo(productModel,productId);
        System.out.println("register result value:" +result);
		switch(result) {
		case 1 -> {
			request.setAttribute(StringUtils.SUCCESS_MESSAGE, "Updated Product");
			response.sendRedirect(request.getContextPath() + StringUtils.SERVLET_URL_PRODUCTLIST);
		}
		case 0 -> {
			request.setAttribute(StringUtils.ERROR_MESSAGE, "Incorrect format of data provided.");
			request.getRequestDispatcher(StringUtils.UPDATE_PAGE).forward(request, response);
		}
		case -1 -> {
			request.setAttribute(StringUtils.ERROR_MESSAGE, StringUtils.SERVER_ERROR_MESSAGE);
			request.getRequestDispatcher(StringUtils.UPDATE_PAGE).forward(request, response);
		}
		}
	}
	
	@Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("delete triggered");
        if (databaseController.deleteProduct(req.getParameter("deleteId")) == 1) {
            req.setAttribute(StringUtils.SUCCESS_MESSAGE, StringUtils.SUCCESSFUL_DELETE_MESSAGE);
            resp.sendRedirect(req.getContextPath() + StringUtils.SERVLET_URL_PRODUCTLIST);
        } else {
            req.setAttribute(StringUtils.ERROR_MESSAGE, StringUtils.ERROR_DELETE_MESSAGE);
            resp.sendRedirect(req.getContextPath() + StringUtils.ADMIN_DASHBOARD_PAGE);
        }
    }
}
