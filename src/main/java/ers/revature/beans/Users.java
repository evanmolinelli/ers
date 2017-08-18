package ers.revature.beans;

import java.util.List;

public class Users {
	
	private int user_id;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String email;
	private int userRoleId;
//	private List<Reimbursement> reimb;
	
	public Users() {
		super();
	}
	
	public Users(int user_id, String username, String password, String firstName, String lastName, String email,
			int userRoleId) {
		// List<Reimbursement> reimb
		super();
		this.user_id = user_id;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.userRoleId = userRoleId;
//		this.reimb = reimb;
	}



	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getUserRoleId() {
		return userRoleId;
	}
	public void setUserRoleId(int userRoleId) {
		this.userRoleId = userRoleId;
	}
	
//	public List<Reimbursement> getReimb() {
//		return reimb;
//	}
//
//	public void setReimb(List<Reimbursement> reimb) {
//		this.reimb = reimb;
//	}

	@Override
	public String toString() {
		return "Users [user_id=" + user_id + ", username=" + username + ", password=" + password + ", firstName="
				+ firstName + ", lastName=" + lastName + ", email=" + email + ", userRoleId = " + userRoleId + "]";
	}



}

