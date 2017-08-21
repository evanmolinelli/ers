package ers.revature.data;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import ers.revature.beans.Reimbursement;

public class ReimburseDataService {

	private Connection conn;
	private ReimburseDAO reimbDao;
	
	public ReimburseDataService() {
		super();
		this.conn = ConnectionManager.getConnection();
		this.reimbDao = new ReimburseDAO(conn);
	}
	
	public void close() throws Exception {
		if (conn != null && !conn.isClosed()) {
			conn.close();
		}
	}
	
	public void create(Reimbursement reimb){
		boolean result;
		try {
			reimbDao.create(reimb);
			conn.commit();
			System.out.print("create/commit went thru.");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Create didn't work bro/didn't commit.");
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	public void update(Reimbursement reimb){
		boolean result;
		try {
			reimbDao.update(reimb);
			conn.commit();
			System.out.print("updated/commit went thru.");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Update didn't work bro/didn't commit.");
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
			
			
	public ArrayList<Reimbursement> viewAllReimbursement(){
		ArrayList<Reimbursement> result = new ArrayList<Reimbursement>();
		try {
			result = reimbDao.viewAllReimbursement();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Didn't work bro.");
			result = new ArrayList<Reimbursement>();
		}
		return result;
	}
	
	public ArrayList<Reimbursement> viewUserReimb(String username){
		
		ArrayList<Reimbursement> result = new ArrayList<Reimbursement>();
		try {
			result = reimbDao.viewUserReimb(username);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Didn't work bro.");
			result = new ArrayList<Reimbursement>();
		}
		return result;
	}
	
	public ArrayList<Reimbursement> viewReimbursementByStatus(String status){
		ArrayList<Reimbursement> result = new ArrayList<Reimbursement>();
		try {
			result = reimbDao.viewReimbursementByStatus(status);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Didn't work bro.");
			result = new ArrayList<Reimbursement>();
		}
		return result;
	}
	
	public static void main(String[] args) throws SQLException {
		Connection conn = new ConnectionManager().getConnection();
		ReimburseDAO rds = new ReimburseDAO(conn);
		for (Reimbursement reimb : rds.viewAllReimbursement()){
			System.out.println(reimb.toString());
		}
	}
}


