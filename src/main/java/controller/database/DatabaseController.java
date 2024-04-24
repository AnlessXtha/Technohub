package controller.database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.PasswordEncryptionWithAes;
import model.ProductModel;
import model.ProductModel;
//import model.PasswordEncryptionWithAes;
import model.UserModel;
import util.StringUtils;

public class DatabaseController {
	public Connection getConnection () throws SQLException, ClassNotFoundException{
		
		Class.forName("com.mysql.jdbc.Driver");
		String url= "jdbc:mysql://localhost:3306/technohub";
		String user = "root";
		String pass = "";
		return DriverManager.getConnection (url, user, pass);
	}
	
	public int registerUser(UserModel userModel) {
		
		try (Connection con = getConnection()) {
			
			// Validation
			PreparedStatement checkedUsernameSt= con.prepareStatement (StringUtils.GET_USERNAME);
			checkedUsernameSt.setString(1, userModel.getUsername());
			ResultSet checkedUsernameRs = checkedUsernameSt.executeQuery();
			checkedUsernameRs.next();
			if (checkedUsernameRs.getInt(1) > 0 ) {
				return -2; // Username already exists
			}
			
			// checking for existing email
			PreparedStatement checkEmailSt= con.prepareStatement (StringUtils.GET_EMAIL);
			checkEmailSt.setString(1, userModel.getEmail());
			ResultSet checkEmailRs = checkEmailSt.executeQuery();
			checkEmailRs.next();
			if (checkEmailRs.getInt(1) > 0 ) {
				return -3; // Email already exists
			}
						
			// checking for existing phone number
			PreparedStatement checkPhoneSt= con.prepareStatement (StringUtils.GET_CONTACT_NUMBER);
			checkPhoneSt.setString(1, userModel.getContactNumber());
			ResultSet checkPhoneRs = checkPhoneSt.executeQuery();
			checkPhoneRs.next();
			if (checkPhoneRs.getInt(1) > 0 ) {
				return -4; // Phone number already exists
			}
			
			PreparedStatement st = con.prepareStatement (StringUtils.REGISTER_USER);
			
			
			st.setString(1, userModel.getUsername());
			st.setString(2, userModel.getFirstName());
			st.setString(3, userModel.getLastName());
			st.setString(4, "CUSTOMER");
			st.setString(5, userModel.getAddress ());
			st.setString(6, userModel.getContactNumber());
			st.setString(7, userModel.getEmail());
//			st.setString(8, userModel.getPassword());
			 st.setString(8, PasswordEncryptionWithAes.encrypt(userModel.getUsername(), userModel.getPassword()));
			
			int result = st.executeUpdate();
			return result > 0 ? 1: 0;
			
		}catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace (); // Log the exception for debugging
			return -1;
		}
		
		/**
		 * - connection
		 * ---> Prepared Statement
		 *  query
		 *  execute
		 *  return List<product>
		 */
	}
	
	public int getUserInfo(String username, String password) {
        try (Connection con = getConnection()) {
            PreparedStatement st = con.prepareStatement(StringUtils.GET_LOGIN_USER);
            st.setString(1, username);
//            st.setString(2, password);
            ResultSet result = st.executeQuery();

            if (result.next()) {
                // User name and password match in the database
            	String userDb = result.getString("username");
				String passwordDb = result.getString("password");
				String decryptedPwd = PasswordEncryptionWithAes.decrypt(passwordDb, username);
				
				if (decryptedPwd != null && userDb.equals(username) && decryptedPwd.equals(password)) {
					return 1;
				}else {
					return -2;
				}
            } else {
                // No matching record found
                return 0;
            }
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace(); // Log the exception for debugging
            return -1;
        }
    }
	
	public int checkUserType(String username) {
		try (Connection con = getConnection()) {
			PreparedStatement checkUserTypeUT = con.prepareStatement(StringUtils.CHECK_USERTYPE);
			checkUserTypeUT.setString(1,username);
//			checkUserTypeUT.setString(1, userModel.getUsername());
			ResultSet checkedUserTypeRs = checkUserTypeUT.executeQuery();
			
			if(checkedUserTypeRs.next()) {
				String userDb = checkedUserTypeRs.getString("username");
				String userTypeDb = checkedUserTypeRs.getString("userType");
				System.out.println(userDb);
				System.out.println(userTypeDb);
				if ("CUSTOMER".equals(userTypeDb)) {
					return 0; // Customer
				} else {
					return 1; // Admin
				}
			} else {
	            // No user found with the given username
	            return -2;
	        }
			
		}catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace (); // Log the exception for debugging
			return -1;
		}
	}
	
	// Products
	
	public int addProduct (ProductModel productModel) {
		try (Connection con = getConnection()) {
			PreparedStatement st = con.prepareStatement (StringUtils.ADD_PRODUCT);
			
			st.setString(1, productModel.getProductName());
			st.setString(2, productModel.getProductDescription());
			st.setString(3, productModel.getProductCategory());
			st.setInt(4, productModel.getStock());
			st.setInt(5, productModel.getUnitPrice());
			st.setString(6, productModel.getProductImageUrlFromPart());
			
			int result = st.executeUpdate();
			return result > 0 ? 1: 0;
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace (); 
			return -1;
		}
	}
	
	public ArrayList<ProductModel> getAllProductsInfo(){
		try (Connection con = getConnection()) {
			PreparedStatement stmt = con.prepareStatement(StringUtils.GET_ALL_PRODUCTS);
			ResultSet result = stmt.executeQuery();
			
			ArrayList<ProductModel> products = new ArrayList<ProductModel>();
			
			while(result.next()) {
				ProductModel product = new ProductModel();
				product.setProductID(result.getInt("productID"));
				product.setProductName(result.getString("productName"));
				product.setProductDescription(result.getString("productDescription"));
				product.setProductCategory(result.getString("productCategory"));
				product.setStock(result.getInt("stock"));
				product.setUnitPrice(result.getInt("unitPrice"));
				product.setProductImageUrlFromPart(result.getString("productImage"));
				
				
				products.add(product);
			}
			return products;
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			return null;
		}
	}
	 

}
