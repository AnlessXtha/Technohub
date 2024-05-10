package controller.database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.CartModel;
import model.OrderModel;
import model.PasswordEncryptionWithAes;
import model.ProductModel;
import model.ProductModel;
//import model.PasswordEncryptionWithAes;
import model.UserModel;
import util.StringUtils;

public class DatabaseController {
	public Connection getConnection() throws SQLException, ClassNotFoundException {

		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/technohub";
		String user = "root";
		String pass = "";
		return DriverManager.getConnection(url, user, pass);
	}
	
	public int registerUser(UserModel userModel) {

		try (Connection con = getConnection()) {

			PreparedStatement checkedUsernameSt = con.prepareStatement(StringUtils.GET_USERNAME);
			checkedUsernameSt.setString(1, userModel.getUsername());
			ResultSet checkedUsernameRs = checkedUsernameSt.executeQuery();
			checkedUsernameRs.next();
			if (checkedUsernameRs.getInt(1) > 0) {
				return -2; 
			}


			PreparedStatement checkEmailSt = con.prepareStatement(StringUtils.GET_EMAIL);
			checkEmailSt.setString(1, userModel.getEmail());
			ResultSet checkEmailRs = checkEmailSt.executeQuery();
			checkEmailRs.next();
			if (checkEmailRs.getInt(1) > 0) {
				return -3;
			}


			PreparedStatement checkPhoneSt = con.prepareStatement(StringUtils.GET_CONTACT_NUMBER);
			checkPhoneSt.setString(1, userModel.getContactNumber());
			ResultSet checkPhoneRs = checkPhoneSt.executeQuery();
			checkPhoneRs.next();
			if (checkPhoneRs.getInt(1) > 0) {
				return -4; 
			}

			PreparedStatement st = con.prepareStatement(StringUtils.REGISTER_USER);

			st.setString(1, userModel.getUsername());
			st.setString(2, userModel.getFirstName());
			st.setString(3, userModel.getLastName());
			st.setString(4, "CUSTOMER");
			st.setString(5, userModel.getAddress());
			st.setString(6, userModel.getContactNumber());
			st.setString(7, userModel.getEmail());
			st.setString(8, PasswordEncryptionWithAes.encrypt(userModel.getUsername(), userModel.getPassword()));

			int result = st.executeUpdate();
			return result > 0 ? 1 : 0;

		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace(); 
			return -1;
		}
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
				} else {
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

	public UserModel getCurrentUserInfoByUserName(String username) {
		try (Connection con = getConnection()) {
			PreparedStatement stmt = con.prepareStatement(StringUtils.GET_SINGLE_USER_INFO);
			stmt.setString(1, username);
			ResultSet result = stmt.executeQuery();

			UserModel user = new UserModel();

			if (result.next()) {
				user.setFirstName(result.getString("firstName"));
				user.setLastName(result.getString("lastName"));
				user.setUserType(result.getString("userType"));
				user.setAddress(result.getString("address"));
				user.setContactNumber(result.getString("contactNumber"));
				user.setEmail(result.getString("email"));
				user.setUsername(result.getString("username"));
				user.setPassword(result.getString("password"));
			}
			return user;

		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public int checkUserType(String username) {
		try (Connection con = getConnection()) {
			PreparedStatement checkUserTypeUT = con.prepareStatement(StringUtils.CHECK_USERTYPE);
			checkUserTypeUT.setString(1, username);
//			checkUserTypeUT.setString(1, userModel.getUsername());
			ResultSet checkedUserTypeRs = checkUserTypeUT.executeQuery();

			if (checkedUserTypeRs.next()) {
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

		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace(); // Log the exception for debugging
			return -1;
		}
	}
	
	public int getCurrentCartID(String username) {
		try (Connection con = getConnection()) {
			PreparedStatement checkUserTypeUT = con.prepareStatement(StringUtils.CHECK_USERTYPE);
			checkUserTypeUT.setString(1, username);
			ResultSet checkedUserTypeRs = checkUserTypeUT.executeQuery();

			if (checkedUserTypeRs.next()) {
				String userDb = checkedUserTypeRs.getString("username");
				int userCartID = checkedUserTypeRs.getInt("cartID");
				System.out.println(userDb);
				System.out.println("userCartID" + userCartID);
				return userCartID;
			} else {
				// No user found with the given username
				return -2;
			}

		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace(); // Log the exception for debugging
			return -1;
		}
	}

	// Products

	public int addProduct(ProductModel productModel) {
		try (Connection con = getConnection()) {
			PreparedStatement st = con.prepareStatement(StringUtils.ADD_PRODUCT);

			st.setString(1, productModel.getProductName());
			st.setString(2, productModel.getProductDescription());
			st.setString(3, productModel.getProductCategory());
			st.setInt(4, productModel.getStock());
			st.setInt(5, productModel.getUnitPrice());
			st.setString(6, productModel.getProductImageUrlFromPart());

			int result = st.executeUpdate();
			return result > 0 ? 1 : 0;
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			return -1;
		}
	}

	public ArrayList<ProductModel> getAllProductsInfo() {
		try (Connection con = getConnection()) {
			PreparedStatement stmt = con.prepareStatement(StringUtils.GET_ALL_PRODUCTS);
			ResultSet result = stmt.executeQuery();

			ArrayList<ProductModel> products = new ArrayList<ProductModel>();

			while (result.next()) {
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

	public ProductModel getCurrentProductInfo(int productID) {
		try (Connection con = getConnection()) {
			PreparedStatement stmt = con.prepareStatement(StringUtils.GET_ID_PRODUCTS);
			stmt.setInt(1, productID);
			ResultSet result = stmt.executeQuery();

			if (result.next()) {
				ProductModel product = new ProductModel();
				product.setProductID(result.getInt("productID"));
				product.setProductName(result.getString("productName"));
				product.setProductDescription(result.getString("productDescription"));
				product.setProductCategory(result.getString("productCategory"));
				product.setStock(result.getInt("stock"));
				product.setUnitPrice(result.getInt("unitPrice"));
				product.setProductImageUrlFromPart(result.getString("productImage"));
				return product;
			} else {
				return null;
			}
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			return null;
		}
	}

	// updating product in admin dashboard
	public int updateProductInfo(ProductModel productModel, int productId) {
		System.out.println("put triggered database");
		try (Connection con = getConnection()) {
			PreparedStatement st = con.prepareStatement(StringUtils.QUERY_UPDATE_PRODUCT);
			st.setString(1, productModel.getProductName());
			st.setString(2, productModel.getProductDescription());
			st.setString(3, productModel.getProductCategory());
			st.setInt(4, productModel.getStock());
			st.setInt(5, productModel.getUnitPrice());
			st.setString(6, productModel.getProductImageUrlFromPart());
			st.setInt(7, productId);
			int result = st.executeUpdate();
			return result > 0 ? 1 : 0;
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace(); // Log the exception for debugging
			return -1;
		}
	}

	public int deleteProduct(String productName) {
		try (Connection con = getConnection()) {
			PreparedStatement st = con.prepareStatement(StringUtils.DELETE_PRODUCT);
			st.setString(1, productName);
			return st.executeUpdate();
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace(); // Log the exception for debugging
			return -1;
		}

	}
	
	public int addToCart(CartModel cartItem) {
		try (Connection con = getConnection()) {
			PreparedStatement st = con.prepareStatement("INSERT INTO cartproductdetails (cartID, productID, cartProductQuantity, cartLineTotal) VALUES (?, ?, ?, ?)");
			st.setInt(1, cartItem.getCartID());
	        st.setInt(2, cartItem.getProductID());
	        st.setInt(3, cartItem.getCartProductQuantity());
	        st.setDouble(4, cartItem.getCartLineTotal());
	        
	        int result = st.executeUpdate();
	        
			return result > 0 ? 1 : 0;
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace(); // Log the exception for debugging
			return -1;
		}
	}
	
    public void updateProductStock(ProductModel product) {
        try (Connection con = getConnection()) {
            PreparedStatement ps = con.prepareStatement("UPDATE products SET stock = ? WHERE productID = ?");
            ps.setInt(1, product.getStock());
            ps.setInt(2, product.getProductID());
            ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

	// Carts

	public ArrayList<CartModel> getAllProductsFromCart(int cartID) {
		try (Connection con = getConnection()) {
			PreparedStatement stmt = con.prepareStatement(StringUtils.GET_ALL_PRODUCTS_FROM_CART);
			stmt.setInt(1, cartID);
			ResultSet result = stmt.executeQuery();

			ArrayList<CartModel> carts = new ArrayList<CartModel>();

			while (result.next()) {
				CartModel cart = new CartModel();
				cart.setCartID(result.getInt("cartID"));
				cart.setProductID(result.getInt("productID"));
				cart.setCartProductQuantity(result.getInt("cartProductQuantity"));
				cart.setCartLineTotal(result.getDouble("cartLineTotal"));
				cart.setUnitPrice(result.getInt("unitPrice"));
				cart.setProductName(result.getString("productName"));
				cart.setProductImageCartUrlFromPart(result.getString("productImage"));

				carts.add(cart);
			}
			return carts;
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public int removeProductFromCart(int productID) {
		try (Connection con = getConnection()) {
			PreparedStatement st = con.prepareStatement(StringUtils.REMOVE_PRODUCT_FROM_CART);
			st.setInt(1, productID);
			return st.executeUpdate();
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace(); // Log the exception for debugging
			return -1;
		}

	}
	// Carts
	
	// Orders
	
	public ArrayList<OrderModel> getOrdersOfUser(String username) {
		try (Connection con = getConnection()) {
			PreparedStatement stmt = con.prepareStatement(StringUtils.GET_ALL_ORDERS_FOR_USER);
			stmt.setString(1, username);
			ResultSet result = stmt.executeQuery();

			ArrayList<OrderModel> orders = new ArrayList<OrderModel>();

			while (result.next()) {
				OrderModel order = new OrderModel();
				order.setOrderID(result.getInt("orderID"));
				order.setOrderDate(result.getDate("orderDate")); 
				order.setOrderStatus(result.getString("orderStatus"));
                order.setProductName(result.getString("productName"));
                order.setProductImageOrderUrlFromPart(result.getString("productImage"));
                order.setOrderQuantity(result.getInt("orderQuantity"));
                order.setLineTotal(result.getDouble("lineTotal"));
                order.setOrderTotal(result.getDouble("orderTotal"));

				orders.add(order);
			}
			return orders;
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	public int addOrder(OrderModel order) {
        try (Connection con = getConnection()) {
            PreparedStatement ps = con.prepareStatement("INSERT INTO orders (orderDate, orderTotal, username, orderStatus) VALUES (?, ?, ?, ?)");
            ps.setDate(1, new java.sql.Date(order.getOrderDate().getTime()));
            ps.setDouble(2, order.getOrderTotal());
            ps.setString(3, order.getUsername());
            ps.setString(4, order.getOrderStatus());
            
            int result = ps.executeUpdate();
            if (result > 0) {
                ResultSet generatedKeys = ps.getGeneratedKeys();
                if (generatedKeys.next()) {
                	System.out.println(generatedKeys.getInt(0));
                    return generatedKeys.getInt(0); // Return the generated orderID
                }
            }
            return 0;
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return -1; 
    }
	
    public void addOrderProductDetails(OrderModel orderProductDetails) {
        try (Connection con = getConnection()) {
            PreparedStatement ps = con.prepareStatement("INSERT INTO orderproductdetails (orderID, productID, orderQuantity, lineTotal) VALUES (?, ?, ?, ?)");
            ps.setInt(1, orderProductDetails.getOrderID());
            System.out.println(orderProductDetails.getOrderID());
            ps.setInt(2, orderProductDetails.getProductID());
            System.out.println(orderProductDetails.getProductID());
            ps.setInt(3, orderProductDetails.getOrderQuantity());
            ps.setDouble(4, orderProductDetails.getLineTotal());
            ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
	// Orders

}
