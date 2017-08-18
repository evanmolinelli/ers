package ers.revature.java;

import static org.junit.Assert.*;

import org.junit.Before;
import org.mindrot.jbcrypt.BCrypt;

import org.junit.Test;
import ers.revature.beans.Password;
import ers.revature.beans.Users;
import ers.revature.data.*;

public class PasswordTest {

	@Before			// before EACH test
	public void setup(){
		UserDataService ubs = new UserDataService();
		Users usr = ubs.findByUsername("KINGSHEIST");
		String pass = "GUYGUY1";
		test(usr, pass);
	}
	
	@Test
	public void test(Users usr, String pass) {
		
		
			if(BCrypt.checkpw(pass, usr.getPassword())){
				System.out.println("Good.");
			}
			else{
				System.out.println("getPass: " + usr.getPassword());
				System.out.println("getPass: " + Password.hashPassword(pass));
			}
	}
	
	@Test
	public void testReimbursement(){
		ReimburseDataService rbs = new ReimburseDataService();
		System.out.print(rbs.viewAllReimbursement().toString());
	}

}
