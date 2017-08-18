package ers.revature.java;

import java.sql.Blob;
import java.sql.CallableStatement;
import org.mindrot.jbcrypt.BCrypt;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ers.revature.data.*;
import ers.revature.beans.*;

public class SampleTests {

	public static void main(String[] args) throws Exception {
		Connection conn = ConnectionManager.getConnection();
//		String dateString = "14-JAN-10 12:10:29.0";
//		Date date = new SimpleDateFormat("dd-MMM-yy HH:mm:ss.ss").parse(dateString);
//		String formattedDate = new SimpleDateFormat("dd MMM YYY, HH:mm").format(date);
//		Timestamp timestamp = new Timestamp(new SimpleDateFormat("ddMMMyy").parse(formattedDate).getTime());
		java.util.Date utilDate = new java.util.Date();
		java.sql.Timestamp sq = new java.sql.Timestamp(utilDate.getTime());  

		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
//		System.out.println(sdf.format(sq));
//		Timestamp time = new Timestamp(800543);
//		System.out.println(getTime.getTimestamp());
//		UsersDAO ud = new UsersDAO(conn);
//		Users usr1 = ud.findByUsername("BOSSMAN4");
//		Users usr2 = ud.findByUserId(1);
//		Users usr = new Users(5, "picklerick1", "morty", "Tiny", "Rick", "rick@morty.com", 2);
//		Reimbursement rmb = new Reimbursement(5, 500, getTime.getTimestamp(), getTime.getTimestamp(), "GBED", null, usr2, usr1, "PENDING", "TRAVEL");

		
//		UserDataService uds = new UserDataService();
//		System.out.println(uds.getAllEmployees().toString());
//		ArrayList<Reimbursement>num = new ArrayList<Reimbursement>();
		ReimburseDAO rd = new ReimburseDAO(conn);
		System.out.println("Hello? "+rd.viewUserReimb("KINGSHEIST").toString());
//		rd.viewAllReimbursement();
//		System.out.println(rd.viewUserReimb("patsMuldy").toString());
//		List<Reimbursement> lr = new ArrayList<Reimbursement>();
//		Reimbursement reb = new Reimbursement();
//		reb = rd.viewUserReimb(usr.getUsername());
//		reb.setReimbAuthor(usr1);
//		reb.setReimbResolver(usr2);
//		lr.add(reb);
//		System.out.println("Done reimb" + usr1.getPassword());
//		usr.setReimb(lr);
		
//		if(usr1.getPassword() == Password.hashPassword("dabguy"))
//		if(BCrypt.checkpw("dabguy", usr1.getPassword())){
//			System.out.println("Pats a guy.");
//		}
//		else{
//			System.out.println("I'm a guy.");
//			System.out.println("getPass: " + usr1.getPassword());
//			System.out.println("getPass: " + Password.hashPassword("dabguy"));
//		}
		
//		usr.viewReimb("WENTZ1");
//		UserDataService uds = new UserDataService();
////		
//		uds.update(usr);
//		ds.findByUsername("kjhgkjhg");
//		System.out.println("Done.");
//		CallableStatement stmt = conn.prepareCall(sql);
//		stmt.setInt(1, 60); 
//		ReimburseDAO rDao = new ReimburseDAO(conn);
//		rDao.viewAllReimbursement();
		
//		stmt.registerOutParameter(2, Types.VARCHAR);
//		stmt.registerOutParameter(3, Types.VARCHAR);
//		stmt.execute(); // send the SQL call (precompiled, safe against injection)
//		
//		String name = stmt.getString(2);
//		String email = stmt.getString(3);
//		System.out.println("Hello " + name + "! Your email is " + email);
		conn.close();
//		ud.close();
	}
}

class getTime {
	public static Timestamp getTimestamp() {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		return timestamp;
	}
}