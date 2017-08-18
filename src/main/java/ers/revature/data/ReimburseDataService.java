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
	
	public boolean create(Reimbursement reimb){
		boolean result;
		try {
			reimbDao.create(reimb);
			conn.commit();
			result=true;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Didn't work bro.");
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			result=false;
		}
		return result;
	}
	
	public boolean update(Reimbursement reimb){
		boolean result;
		try {
			reimbDao.update(reimb);
			conn.commit();
			result=true;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Didn't work bro.");
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			result=false;
		}
		return result;
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
	
	public static void main(String[] args) {
		ReimburseDataService rds = new ReimburseDataService();
		
		System.out.println(rds.viewAllReimbursement().toString());
	}
}


