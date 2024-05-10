package model;

import java.io.File;
import java.sql.Date;

import javax.servlet.http.Part;

import util.StringUtils;

public class OrderModel {
	private int orderID;
	private int productID;
	private int orderQuantity;
	private double lineTotal;
	
	private Date orderDate;
	private double orderTotal;
	private String username;
	private String orderStatus;
	
	private String productName;
	private String productImageOrderUrlFromPart;
	
	public OrderModel(){
		
	}

	public OrderModel(int orderID, int productID, int orderQuantity, double lineTotal) {
		super();
		this.orderID = orderID;
		this.productID = productID;
		this.orderQuantity = orderQuantity;
		this.lineTotal = lineTotal;
	}


	public OrderModel (Date orderDate, double orderTotal, String username, String orderStatus) {
		super();
		this.orderDate = orderDate;
		this.orderTotal = orderTotal;
		this.username = username;
		this.orderStatus = orderStatus;
	}

	public OrderModel(int orderID, int productID, int orderQuantity, double lineTotal, Date orderDate,
			double orderTotal, String username, String orderStatus, String productName,
			String productImageOrderUrlFromPart) {
		super();
		this.orderID = orderID;
		this.productID = productID;
		this.orderQuantity = orderQuantity;
		this.lineTotal = lineTotal;
		this.orderDate = orderDate;
		this.orderTotal = orderTotal;
		this.username = username;
		this.orderStatus = orderStatus;
		this.productName = productName;
		this.productImageOrderUrlFromPart = productImageOrderUrlFromPart;
	}

	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	public int getProductID() {
		return productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}

	public int getOrderQuantity() {
		return orderQuantity;
	}

	public void setOrderQuantity(int orderQuantity) {
		this.orderQuantity = orderQuantity;
	}

	public double getLineTotal() {
		return lineTotal;
	}

	public void setLineTotal(double lineTotal) {
		this.lineTotal = lineTotal;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public double getOrderTotal() {
		return orderTotal;
	}

	public void setOrderTotal(double orderTotal) {
		this.orderTotal = orderTotal;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductImageOrderUrlFromPart() {
		return productImageOrderUrlFromPart;
	}

	public void setProductImageOrderUrlFromPart(String productImageOrderUrlFromPart) {
		this.productImageOrderUrlFromPart = productImageOrderUrlFromPart;
	}
	
	private String getProductImageOrderUrl(Part part) {
		String savePath = StringUtils.IMAGE_DIR_SAVE_PATH_PRODUCT;
		File fileSaveDir = new File(savePath);
		String productImageUrlOrderFromPart = null;
		if (!fileSaveDir.exists()) {
			fileSaveDir.mkdir();
		}
		String contentDisp = part.getHeader("content-disposition");
		String[] items = contentDisp.split(";");
		for (String s : items) {
			if (s.trim().startsWith("filename")) {
				productImageUrlOrderFromPart = s.substring(s.indexOf("=") + 2, s.length() - 1);
			}
		}
		if (productImageUrlOrderFromPart == null || productImageUrlOrderFromPart.isEmpty()) {
			productImageUrlOrderFromPart = "download.jpg";
		}
		return productImageUrlOrderFromPart;
	}

	
	

}
