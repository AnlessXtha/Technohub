package controller.database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
			st.setString(8, userModel.getPassword());
			// st.setString(9, PasswordEncryptionWithAes.encrypt(userModel.getUsername(), userModel.getPassword()));
			
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
		 *  return List<student>
		 */
	}
	
	public int getUserInfo(String username, String password) {
        try (Connection con = getConnection()) {
            PreparedStatement st = con.prepareStatement(StringUtils.GET_LOGIN_USER);
            st.setString(1, username);
            st.setString(2, password);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                // User name and password match in the database
                return 1;
            } else {
                // No matching record found
                return 0;
            }
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace(); // Log the exception for debugging
            return -1;
        }
    }

}
