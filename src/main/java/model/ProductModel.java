package model;

import java.io.File;
import java.io.Serializable;
import java.time.LocalDate;

import javax.servlet.http.Part;

import util.StringUtils;

public class ProductModel implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int productID;
	private String productName;
	private String productDescription;
	private String productCategory;
	private int stock;
	private int unitPrice;
	private String productImageUrlFromPart;

//	private String imageUrlFromPart;
	
	public ProductModel() {
		
	}

	public ProductModel(String productName, String productDescription, String productCategory,
			int stock, int unitPrice, Part productImagePart) {
		super();
//		this.productID = productID;
		this.productName = productName;
		this.productDescription = productDescription;
		this.productCategory = productCategory;
		this.stock = stock;
		this.unitPrice = unitPrice;
		this.productImageUrlFromPart = getProductImageUrl(productImagePart);
	}

	public int getProductID() {
		return productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public String getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public int getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(int unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getProductImageUrlFromPart() {
		return productImageUrlFromPart;
	}

	public void setProductImageUrlFromPart(String productImageUrlFromPart) {
		this.productImageUrlFromPart = productImageUrlFromPart;
	}
	
	private String getProductImageUrl(Part part) {
		String savePath = StringUtils.IMAGE_DIR_SAVE_PATH_PRODUCT;
		File fileSaveDir = new File(savePath);
		String productImageUrlFromPart = null;
		if (!fileSaveDir.exists()) {
			fileSaveDir.mkdir();
		}
		String contentDisp = part.getHeader("content-disposition");
		String[] items = contentDisp.split(";");
		for (String s : items) {
			if (s.trim().startsWith("filename")) {
				productImageUrlFromPart = s.substring(s.indexOf("=") + 2, s.length() - 1);
			}
		}
		if (productImageUrlFromPart == null || productImageUrlFromPart.isEmpty()) {
			productImageUrlFromPart = "download.jpg";
		}
		return productImageUrlFromPart;
	}

}
