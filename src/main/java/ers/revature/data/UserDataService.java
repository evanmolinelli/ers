package ers.revature.data;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import ers.revature.beans.Users;

public class UserDataService {

	private Connection conn;
	private UsersDAO usersDAO;

	public UserDataService() {
		super();
		this.conn = ConnectionManager.getConnection();
		this.usersDAO = new UsersDAO(conn);
	}

	public void close() throws Exception {
		if (conn != null && !conn.isClosed()) {
			conn.close();
		}
	}

	public boolean create(Users user) {
		boolean result = false;
		try {
			usersDAO.create(user);
			// 2+ DAO calls within a transaction
			conn.commit();
			result = true;
		} catch (SQLException e) {
			try {
				conn.rollback();
				result = false;
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return result;
	}

	public boolean update(Users user) {

		boolean result = false;
		try {
			usersDAO.update(user);
			// 2+ DAO calls within a transaction
			conn.commit();
			result = true;
		} catch (SQLException e) {
			try {
				conn.rollback();
				result = false;
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return result;
	}

	public Users findByUsername(String username) {

		Users user = new Users();
		try {
			user = usersDAO.findByUsername(username);
			return user;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Didn't work bro.");
			return new Users();
		}

	}

	public Users findByUserEmail(String email) {

		Users user = new Users();
		try {
			user = usersDAO.findByUserEmail(email);
			return user;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Didn't work bro.");
			return new Users();
		}

	}

	public Users findByUserId(int id) {

		Users user = new Users();
		try {
			user = usersDAO.findByUserId(id);
			return user;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Didn't work bro.");
			return new Users();
		}

	}
	
	public ArrayList<Users> getAllEmployees(){
		ArrayList<Users> result = new ArrayList<Users>();
		try {
				result = usersDAO.getAllEmployees();
				return result;
			} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Didn't work bro.");
			result = new ArrayList<Users>();
		}
		return result;
	}

}
