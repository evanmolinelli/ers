package ers.revature.beans;

import org.mindrot.jbcrypt.BCrypt;

public class Password {
	//workload can be between 10-31
	public static int workload = 12;
	
	public static String hashPassword(String plainTextPassword){
		String salt = BCrypt.gensalt(workload);
		String hashed = BCrypt.hashpw(plainTextPassword, salt);
		return hashed;
	}
	
	public static boolean checkPw(Users usr, String pass){
		boolean result;
		
		if(BCrypt.checkpw(pass, usr.getPassword())){
			result=true;
		}
		else{
			System.out.println("getPass: " + usr.getPassword());
			System.out.println("getPass: " + Password.hashPassword(pass));
			result=false;
		}
		return result;
	}

}
