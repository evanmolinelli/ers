package ers.revature.servlets;

import java.sql.Timestamp;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mindrot.jbcrypt.BCrypt;

import com.fasterxml.jackson.databind.ObjectMapper;

import ers.revature.beans.*;
import ers.revature.data.*;

import ers.revature.data.*;

public class EmployeeController {

	public void login(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		UserDataService u = new UserDataService();
		Users bean = u.findByUsername(username); // model data
		// glue model to the view
		
		String salt = BCrypt.gensalt(12);
		String hashed = BCrypt.hashpw(password, salt);
		
		if (username != "" && BCrypt.checkpw(password, hashed)) {
			req.getSession().setAttribute("userLoggedIn", bean);
			req.getRequestDispatcher("index.html").forward(req, resp);
		} else {
			req.getSession().setAttribute("userLoggedIn", null);
			resp.sendRedirect("login.html");
		}

		// req.getRequestDispatcher("login.html").forward(req, resp);
	}

	public void viewAllEmployees(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		if (req.getSession().getAttribute("userLoggedIn") == null) {
			System.out.println("not working guy 3.");
			resp.sendRedirect("login.html");
		} else {
			ArrayList<Users> users = new UserDataService().getAllEmployees();
			// create an array of objects
			// [{"username":"dan"...}, {}, {}, {}]
			// write the JSON data to the HTTP response
			new ObjectMapper().writeValue(resp.getWriter(), users);
		}
	}

	public void viewAllReimbursements(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		if (req.getSession().getAttribute("userLoggedIn") == null) {
			System.out.println("not working guy 2.");
			resp.sendRedirect("login.html");
		} else {
			ArrayList<Reimbursement> reimb = new ReimburseDataService().viewAllReimbursement();

//			UserDataService u = new UserDataService();
//			for(int i=reimb.size()-1;i>0;i++){
//				Users bean = u.findByUsername(reimb.get(i).getReimbAuthor().getUsername());
//				reimb.get(i).setFullName(bean.getFirstName() + " " + bean.getLastName());
//			}
			// write the JSON data to the HTTP response
			System.out.print(reimb.toString());
			new ObjectMapper().writeValue(resp.getWriter(), reimb);
			
		}
	}

	public void createReimbursement(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		// get JSON (JavaScript Object Notation) resemble the object
		// { "username" : "value", "dan" : "pickles", "array": [], "object":{ }
		// }
		// get JSON from the UI
		if (req.getSession().getAttribute("userLoggedIn") == null) {
			resp.sendRedirect("login.html");
		} else {
			Users user = (Users) req.getSession().getAttribute("userLoggedIn");
			InputStream httpRequestBody = req.getInputStream();
//			System.out.println(httpRequestBody.toString());
			// map JSON property to the JavaBean property
			ObjectMapper mapper = new ObjectMapper();
			// convert JSON to a Java object
			Reimbursement bean = mapper.readValue(httpRequestBody, Reimbursement.class);
			// do whatever with it
			bean.setReimbId(new ReimburseDataService().viewAllReimbursement().size()+1);
			bean.setFullName(user.getFirstName() +" "+user.getLastName());
			bean.setReimbStatus("PENDING");
			bean.setReimbSubmitted(getTime.getTimestamp());
			bean.setReimbAuthor(user);
			bean.setReimbResolver(new UserDataService().findByUserId(3));
			
			System.out.println("user = "+bean.toString());
			System.out.println(bean.getFullName());
			new ReimburseDataService().create(bean);
		}
	}
	
	public void managerTable(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		Users user = (Users) req.getSession().getAttribute("userLoggedIn");
		System.out.print(user.toString());
		if (req.getSession().getAttribute("userLoggedIn") == null 
				|| user.getUserRoleId()==1 ) {
			resp.sendRedirect("reimburseImage.html");
		} else {
			ArrayList<Reimbursement> reimb = new ReimburseDataService().viewReimbursementByStatus("PENDING");

			// write the JSON data to the HTTP response
			new ObjectMapper().writeValue(resp.getWriter(), reimb);
		}
	}
	
	public void managerAccept(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		Users user = (Users) req.getSession().getAttribute("userLoggedIn");
		if (req.getSession().getAttribute("userLoggedIn") == null 
				|| user.getUserRoleId()==1 ) {
			resp.sendRedirect("reimburseImage.html");
		} else {
			InputStream httpRequestBody = req.getInputStream();

			ObjectMapper mapper = new ObjectMapper();
			// convert JSON to a Java object
			Reimbursement bean = mapper.readValue(httpRequestBody, Reimbursement.class);
//			System.out.println(bean.toString());
			bean.setReimbStatus("ACCEPTED");
			new ReimburseDataService().update(bean);
		}
	}
	
	public void managerDeny(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		Users user = (Users) req.getSession().getAttribute("userLoggedIn");
		if (req.getSession().getAttribute("userLoggedIn") == null 
				|| user.getUserRoleId()==1 ) {
			resp.sendRedirect("reimburseImage.html");
		} else {
			InputStream httpRequestBody = req.getInputStream();
//			System.out.println(httpRequestBody.toString());
			ObjectMapper mapper = new ObjectMapper();
			// convert JSON to a Java object
			Reimbursement bean = mapper.readValue(httpRequestBody, Reimbursement.class);
			bean.setReimbStatus("DENIED");
			new ReimburseDataService().update(bean);
		}
	}
}
class getTime {
	public static Timestamp getTimestamp() {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		return timestamp;
	}
}
