package model;

import java.io.File;

import javax.servlet.http.Part;

import util.StringUtils;

public class CartModel {
	private int cartID;
	private int productID;
	private int cartProductQuantity;
	private double cartLineTotal;
	
	private int unitPrice;
	private String productName;
	private String productImageCartUrlFromPart;
	
	public CartModel() {
		
	}

	public CartModel(int cartID, int productID, int cartProductQuantity, double cartLineTotal) {
		super();
		this.cartID = cartID;
		this.productID = productID;
		this.cartProductQuantity = cartProductQuantity;
		this.cartLineTotal = cartLineTotal;
	}

	public CartModel(int cartID, int productID, int cartProductQuantity, double cartLineTotal, int unitPrice,
			String productName, Part productImageCartPart) {
		super();
		this.cartID = cartID;
		this.productID = productID;
		this.cartProductQuantity = cartProductQuantity;
		this.cartLineTotal = cartLineTotal;
		this.unitPrice = unitPrice;
		this.productName = productName;
		this.productImageCartUrlFromPart = getProductImageCartUrl(productImageCartPart);
	}

	public int getCartID() {
		return cartID;
	}

	public void setCartID(int cartID) {
		this.cartID = cartID;
	}

	public int getProductID() {
		return productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}

	public int getCartProductQuantity() {
		return cartProductQuantity;
	}

	public void setCartProductQuantity(int cartProductQuantity) {
		this.cartProductQuantity = cartProductQuantity;
	}

	public double getCartLineTotal() {
		return cartLineTotal;
	}

	public void setCartLineTotal(double cartLineTotal) {
		this.cartLineTotal = cartLineTotal;
	}

	public int getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(int unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductImageCartUrlFromPart() {
		return productImageCartUrlFromPart;
	}

	public void setProductImageCartUrlFromPart(String productImageCartUrlFromPart) {
		this.productImageCartUrlFromPart = productImageCartUrlFromPart;
	}

	private String getProductImageCartUrl(Part part) {
		String savePath = StringUtils.IMAGE_DIR_SAVE_PATH_PRODUCT;
		File fileSaveDir = new File(savePath);
		String productImageUrlCartFromPart = null;
		if (!fileSaveDir.exists()) {
			fileSaveDir.mkdir();
		}
		String contentDisp = part.getHeader("content-disposition");
		String[] items = contentDisp.split(";");
		for (String s : items) {
			if (s.trim().startsWith("filename")) {
				productImageUrlCartFromPart = s.substring(s.indexOf("=") + 2, s.length() - 1);
			}
		}
		if (productImageUrlCartFromPart == null || productImageUrlCartFromPart.isEmpty()) {
			productImageUrlCartFromPart = "download.jpg";
		}
		return productImageUrlCartFromPart;
	}

	
	

}
