package ers.revature.data;

import ers.revature.beans.*;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;

public class UsersDAO {
	
	private Connection conn;

	public UsersDAO(Connection conn) {
		super();
		this.conn = conn;
	}
	
//	USER DAO - create(User), Update(User), setById(int), findByUsername(str)
	public void create(Users user) throws SQLException{
		
//		System.out.println("Starting insert...");
		String sql = "INSERT INTO USERS(ERS_USERS_ID, ERS_USERNAME, ERS_PASSWORD, USER_FIRST_NAME,"+ 
				" USER_LAST_NAME, USER_EMAIL, USER_ROLE_ID) " + "VALUES (?,?,?,?,?,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, user.getUser_id());
		pstmt.setString(2, user.getUsername());
		pstmt.setString(3, user.getPassword());
		pstmt.setString(4, user.getFirstName());
		pstmt.setString(5, user.getLastName());
		pstmt.setString(6, user.getEmail());
		pstmt.setInt(7, user.getUserRoleId());
//		System.out.println("Done inserting into sql...");
		
		pstmt.executeUpdate();
//		System.out.println("Finished insert...");
	}
	
	public void update(Users user) throws SQLException{
		
		String sql = "UPDATE USERS SET ERS_USERNAME= ?, ERS_PASSWORD=?, USER_FIRST_NAME= ?, " 
					+ "USER_LAST_NAME= ?, USER_EMAIL= ? WHERE ERS_USERS_ID= ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, user.getUsername());
		pstmt.setString(2, Password.hashPassword(user.getPassword()));
		pstmt.setString(3, user.getFirstName());
		pstmt.setString(4, user.getLastName());
		pstmt.setString(5, user.getEmail());
		pstmt.setInt(6, user.getUser_id());
		int row = pstmt.executeUpdate();
		
//		System.out.println("Row(s) updated: " + row);
//		System.out.println(user.toString());
	}
	
	public ArrayList<Users> getAllEmployees() throws SQLException{

		ArrayList<Users>result = new ArrayList<Users>();
		String sql = "SELECT u.ERS_USERS_ID, u.ERS_USERNAME, u.ERS_PASSWORD, u.USER_FIRST_NAME, u.USER_LAST_NAME, u.USER_EMAIL, u.USER_ROLE_ID "
				+ "FROM USERS u join USER_ROLES r on u.USER_ROLE_ID = r.ERS_USER_ROLE_ID";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		
		while(rs.next())
		{
			Users usr = new Users();
//			System.out.println("Employees: ");
//			System.out.print("Username: " + rs.getString(1));
//			System.out.print("\tFull Name: " + rs.getString(2) + " " + rs.getString(3));
//			System.out.print("\tEmail: " + rs.getString(4));
			usr.setUser_id(rs.getInt(1));
			usr.setUsername(rs.getString(2));
			usr.setPassword(rs.getString(3));
			usr.setFirstName(rs.getString(4));
			usr.setLastName(rs.getString(5));
			usr.setEmail(rs.getString(6));
			usr.setUserRoleId(rs.getInt(7));
			
			result.add(usr);
		}
		
		return result;
	}
	
	
	public Users findByUsername(String username ) throws SQLException{
//		if(username==null || username=="")
//		{
//			throw new IllegalArgumentException("Not a valid username.");
//		}
		Users user = new Users();
		String sql = "SELECT ERS_USERS_ID, ERS_USERNAME,  ERS_PASSWORD, USER_FIRST_NAME," +
				"USER_LAST_NAME, USER_EMAIL, USER_ROLE_ID FROM USERS WHERE ERS_USERNAME = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, username);
		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next())
		{
//			System.out.println("Hello " + rs.getString("USER_FIRST_NAME") + " " 
//					+ rs.getString("USER_EMAIL"));
			user.setUser_id(rs.getInt("ERS_USERS_ID"));
			user.setUsername(rs.getString("ERS_USERNAME"));
			user.setPassword( rs.getString("ERS_PASSWORD"));
			user.setFirstName(rs.getString("USER_FIRST_NAME"));
			user.setLastName(rs.getString("USER_LAST_NAME"));
			user.setEmail(rs.getString("USER_EMAIL"));
			user.setUserRoleId(rs.getInt("USER_ROLE_ID"));
			
		}
		
//		if(user.getUsername()==null || user.getUsername() =="")
//		{
//			user = new Users();
//			throw new IllegalArgumentException("Username was not found in database.");
//		}
		
//		System.out.println(user.toString());
		return user;
	}
	
	public Users findByUserEmail(String email ) throws SQLException{
		if(email==null || email=="")
		{
			throw new IllegalArgumentException("Not a valid email.");
		}
		Users user = new Users();
		String sql = "SELECT ERS_USERS_ID, ERS_USERNAME, USER_FIRST_NAME," +
				"USER_LAST_NAME, USER_EMAIL, USER_ROLE_ID FROM USERS WHERE USER_EMAIL = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, email);
		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next())
		{
//			System.out.println("Hello " + rs.getString("USER_FIRST_NAME") + " " 
//					+ rs.getString("USER_EMAIL"));
			user.setUser_id(rs.getInt("ERS_USERS_ID"));
			user.setUsername(rs.getString("ERS_USERNAME"));
			user.setFirstName(rs.getString("USER_FIRST_NAME"));
			user.setLastName(rs.getString("USER_LAST_NAME"));
			user.setEmail(rs.getString("USER_EMAIL"));
			user.setUserRoleId(rs.getInt("USER_ROLE_ID"));
			
		}
		if(user.getEmail()==null || user.getEmail()=="")
		{
			user = new Users();
			throw new IllegalArgumentException("Email was not found in the database.");
		}
//		System.out.println(user.toString());
		return user;
	}
	
	public Users findByUserId(int id ) throws SQLException{
		String sql1 = "CALL checkMaxId(?)";
		CallableStatement stmt = conn.prepareCall(sql1);
		stmt.registerOutParameter(1, Types.INTEGER);
		stmt.execute(); // send the SQL call (precompiled, safe against injection)
		
		int maxId = stmt.getInt(1);
		
		if(maxId < id)
		{
			throw new IllegalArgumentException("Not a valid id.");
		}
		Users user = new Users();
		String sql = "SELECT ERS_USERS_ID, ERS_USERNAME, ERS_PASSWORD, USER_FIRST_NAME," +
				"USER_LAST_NAME, USER_EMAIL, USER_ROLE_ID FROM USERS WHERE ERS_USERS_ID = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, id);
		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next())
		{
//			System.out.println("Hello " + rs.getString("USER_FIRST_NAME") + " " 
//					+ rs.getString("USER_EMAIL"));
			user.setUser_id(rs.getInt("ERS_USERS_ID"));
			user.setUsername(rs.getString("ERS_USERNAME"));
			user.setPassword(rs.getString("ERS_PASSWORD"));
			user.setFirstName(rs.getString("USER_FIRST_NAME"));
			user.setLastName(rs.getString("USER_LAST_NAME"));
			user.setEmail(rs.getString("USER_EMAIL"));
			user.setUserRoleId(rs.getInt("USER_ROLE_ID"));
			
		}
//		System.out.println(user.toString());
		return user;
	}
	
}
