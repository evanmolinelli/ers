package ers.revature.data;

import java.sql.Timestamp;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import ers.revature.beans.*;

public class ReimburseDAO {

	private Connection conn;

	public ReimburseDAO(Connection conn) {
		super();
		this.conn = conn;
	}

	public void create(Reimbursement reimb) throws SQLException {
		String sql = "INSERT INTO REIMBURSEMENT(REIMB_ID, REIMB_AMOUNT, REIMB_SUBMITTED, "
				+ "REIMB_RESOLVED, REIMB_DESCRIPTION, REIMB_RECEIPT, REIMB_AUTHOR, "
				+ "REIMB_RESOLVER, REIMB_STATUS_ID, REIMB_TYPE_ID) " + "VALUES (?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, reimb.getReimbId());
		pstmt.setDouble(2, reimb.getReimbAmt());
		pstmt.setTimestamp(3, getTime.getTimestamp());
		pstmt.setTimestamp(4, getTime.getTimestamp());
		pstmt.setString(5, reimb.getReimbDescr());
		pstmt.setBlob(6, reimb.getReimbReciept());
		pstmt.setInt(7, reimb.getReimbAuthor().getUser_id());
		pstmt.setInt(8, reimb.getReimbResolver().getUser_id());
		int i =0;
		if(reimb.getReimbStatus().equals("ACCEPTED"))
		{
			i=1;
		}
		else if(reimb.getReimbStatus().equals("DENIED")){
			i=2;
		}
		else{
			i=3;
		}
		pstmt.setInt(9, i);
		int f=0;
		if(reimb.getReimbType().equals("LODGING"))
		{
			f=1;
		}
		else if(reimb.getReimbType().equals("TRAVEL")){
			f=2;
		}
		else if(reimb.getReimbType().equals("FOOD")){
			f=3;
		}else{
			f=4;
		}
		pstmt.setInt(10, f);
//		System.out.println("Done inserting into sql...");

		pstmt.executeUpdate();
//		System.out.println("Finished insert...");

	}

	public void update(Reimbursement reimb) throws SQLException {

		
		String sql = "UPDATE REIMBURSEMENT SET REIMB_AMOUNT=?, REIMB_SUBMITTED=?, "
				+" REIMB_RESOLVED=?, REIMB_DESCRIPTION=?, REIMB_RECEIPT=?, REIMB_AUTHOR=?, "
				+"REIMB_RESOLVER=?, REIMB_STATUS_ID=?, REIMB_TYPE_ID=? WHERE REIMB_ID= ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setDouble(1, reimb.getReimbAmt());
		pstmt.setTimestamp(2, reimb.getReimbSubmitted());
		pstmt.setTimestamp(3, reimb.getReimbResolved());
		pstmt.setString(4, reimb.getReimbDescr());
		pstmt.setBlob(5, reimb.getReimbReciept());
		pstmt.setInt(6, reimb.getReimbAuthor().getUser_id());
		pstmt.setInt(7, reimb.getReimbResolver().getUser_id());
		int i =0;
		if(reimb.getReimbStatus().equals("ACCEPTED"))
		{
			i=1;
		}
		else if(reimb.getReimbStatus().equals("DENIED")){
			i=2;
		}
		else{
			i=3;
		}
		pstmt.setInt(8, i);
		int f=0;
		if(reimb.getReimbType().equals("LODGING"))
		{
			f=1;
		}
		else if(reimb.getReimbType().equals("TRAVEL")){
			f=2;
		}
		else if(reimb.getReimbType().equals("FOOD")){
			f=3;
		}else{
			f=4;
		}
		pstmt.setInt(9, f);
		pstmt.setInt(10, reimb.getReimbId());
		int row = pstmt.executeUpdate();

//		 System.out.println("Row(s) updated: " + row);
	}

	public ArrayList<Reimbursement> viewAllReimbursement() throws SQLException {
		UsersDAO udao = new UsersDAO(conn);
//		Statement pstmt = conn.createStatement();
		ArrayList<Reimbursement> result = new ArrayList<Reimbursement>();
		// this.conn = ConnectionManager.getConnection();
		String sql = "SELECT r.REIMB_ID, r.REIMB_AMOUNT, r.REIMB_SUBMITTED, r.REIMB_RESOLVED,"
				+ " r.REIMB_DESCRIPTION, r.REIMB_AUTHOR, r.REIMB_RESOLVER, s.REIMB_STATUS, t.REIMB_TYPE FROM REIMBURSEMENT r"
				+ " join users u on r.REIMB_AUTHOR = u.ERS_USERS_ID "
				+ "join REIMBURSEMENT_STATUS s on r.REIMB_STATUS_ID=s.REIMB_STATUS_ID "
				+ " join REIMBURSEMENT_TYPE t on r.REIMB_TYPE_ID= t.REIMB_TYPE_ID";
		
//		SELECT r.REIMB_ID, r.REIMB_AMOUNT, r.REIMB_SUBMITTED, r.REIMB_RESOLVED,
//		r.REIMB_DESCRIPTION, r.REIMB_RECEIPT, r.REIMB_AUTHOR, r.REIMB_RESOLVER,
//		s.REIMB_STATUS, t.REIMB_TYPE FROM REIMBURSEMENT r
//		join users u on r.REIMB_AUTHOR = u.ERS_USERS_ID
//		join REIMBURSEMENT_STATUS s on r.REIMB_STATUS_ID=s.REIMB_STATUS_ID
//		join REIMBURSEMENT_TYPE t on r.REIMB_TYPE_ID= t.REIMB_TYPE_ID;
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			Reimbursement reimb = new Reimbursement();
			Users usrAuth = new Users();
			Users usrReslv = new Users();

			reimb.setReimbId(rs.getInt(1));
			reimb.setReimbAmt(rs.getDouble(2));
			reimb.setReimbSubmitted(rs.getTimestamp(3));
			reimb.setReimbResolved(rs.getTimestamp(4));
			reimb.setReimbDescr(rs.getString(5));
			reimb.setReimbReciept(null);
			int i = rs.getInt(6);
			usrAuth = udao.findByUserId(i);
			reimb.setReimbAuthor(usrAuth);
			int r = rs.getInt(7);
			usrReslv = udao.findByUserId(r);
			reimb.setReimbResolver(usrReslv);
			reimb.setReimbStatus(rs.getString(8));
			reimb.setReimbType(rs.getString(9));
			reimb.setFullName(usrAuth.getFirstName() +" "+usrAuth.getLastName());
//			 System.out.println("reimb = "+ reimb.toString());
			result.add(reimb);
		}
		conn.close();
		return result;
	}

	public ArrayList<Reimbursement> viewUserReimb(String username) throws SQLException {

		ArrayList<Reimbursement> result = new ArrayList<Reimbursement>();
		String sql = "SELECT R.REIMB_ID, R.REIMB_AMOUNT, R.REIMB_SUBMITTED, R.REIMB_RESOLVED,"
				+ " R.REIMB_DESCRIPTION, R.REIMB_AUTHOR, R.REIMB_RESOLVER, s.REIMB_STATUS, t.REIMB_TYPE FROM USERS u JOIN REIMBURSEMENT R"
				+ " ON R.REIMB_AUTHOR = u.ERS_USERS_ID JOIN REIMBURSEMENT_STATUS s "
				+ "ON s.REIMB_STATUS_ID = R.REIMB_STATUS_ID join REIMBURSEMENT_TYPE t"
				+ " on t.REIMB_TYPE_ID = R.REIMB_TYPE_ID WHERE u.ERS_USERNAME = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, username);
		ResultSet rs = pstmt.executeQuery();
		UsersDAO udao = new UsersDAO(conn);
		while (rs.next()) {
			Reimbursement reimb = new Reimbursement();
			Users usrAuth = new Users();
			Users usrReslv = new Users();
			
			reimb.setReimbId(rs.getInt(1));
			reimb.setReimbAmt(rs.getDouble(2));
			reimb.setReimbSubmitted(rs.getTimestamp(3));
			reimb.setReimbResolved(rs.getTimestamp(4));
			reimb.setReimbDescr(rs.getString(5));
			reimb.setReimbReciept(null);
			int i = rs.getInt(6);
			usrAuth = udao.findByUserId(i);
			reimb.setReimbAuthor(usrAuth);
			int r = rs.getInt(7);
			usrReslv = udao.findByUserId(r);
			reimb.setReimbResolver(usrReslv);
			reimb.setReimbStatus(rs.getString(8));
			reimb.setReimbType(rs.getString(9));
			reimb.setFullName(usrAuth.getFirstName() +" "+usrAuth.getLastName());
			
			result.add(reimb);
		}
		return result;
	}

	public ArrayList<Reimbursement> viewReimbursementByStatus(String status) throws SQLException {

		ArrayList<Reimbursement> result = new ArrayList<Reimbursement>();
		String sql = "SELECT R.REIMB_ID, R.REIMB_AMOUNT, R.REIMB_SUBMITTED, R.REIMB_RESOLVED,"
				+ " R.REIMB_DESCRIPTION, R.REIMB_AUTHOR, R.REIMB_RESOLVER, s.REIMB_STATUS, t.REIMB_TYPE FROM USERS u JOIN REIMBURSEMENT R"
				+ " ON R.REIMB_AUTHOR = u.ERS_USERS_ID JOIN REIMBURSEMENT_STATUS s "
				+ "ON s.REIMB_STATUS_ID = R.REIMB_STATUS_ID join REIMBURSEMENT_TYPE t"
				+ " on t.REIMB_TYPE_ID = R.REIMB_TYPE_ID WHERE s.REIMB_STATUS = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, status);
		ResultSet rs = pstmt.executeQuery();
		UsersDAO udao = new UsersDAO(conn);
		while (rs.next()) {
			Reimbursement reimb = new Reimbursement();
			Users usrAuth = new Users();
			Users usrReslv = new Users();
			
			reimb.setReimbId(rs.getInt(1));
			reimb.setReimbAmt(rs.getDouble(2));
			reimb.setReimbSubmitted(rs.getTimestamp(3));
			reimb.setReimbResolved(rs.getTimestamp(4));
			reimb.setReimbDescr(rs.getString(5));
			reimb.setReimbReciept(null);
			int i = rs.getInt(6);
			usrAuth = udao.findByUserId(i);
			reimb.setReimbAuthor(usrAuth);
			int r = rs.getInt(7);
			usrReslv = udao.findByUserId(r);
			reimb.setReimbResolver(usrReslv);
			reimb.setReimbStatus(rs.getString(8));
			reimb.setReimbType(rs.getString(9));
			reimb.setFullName(usrAuth.getFirstName() +" "+usrAuth.getLastName());
			
			result.add(reimb);
		}
		return result;
	}
}

class getTime {
	public static Timestamp getTimestamp() {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		return timestamp;
	}
}
