package ers.revature.servlets;

import ers.revature.*;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginController extends HttpServlet {

	private EmployeeController empController;

	@Override
	public void init() throws ServletException {
		empController = new EmployeeController();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doDispatch(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doDispatch(req, resp);
	}

	private void doDispatch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String requestURI = req.getRequestURI();
//		System.out.println(requestURI);
		switch (requestURI) {

		case "/ers1/login.do": {
			empController.login(req, resp);
			break;
		}
		case "/ers1/view.do": {
			empController.viewAllEmployees(req, resp);
			break;
		}
		case "/ers1/allReimb.do": {
			empController.viewAllReimbursements(req, resp);
			break;
		}
		case "/ers1/createReimb.do": {
			empController.createReimbursement(req, resp);
			break;
		}
		case "/ers1/manage.do":{
			empController.managerTable(req, resp);
			break;
		}
		case "/ers1/manageAccept.do":{
			empController.managerAccept(req, resp);
			break;
		}
		case "/ers1/managerDeny.do":{
			empController.managerDeny(req, resp);
			break;
		}
		default: {
			throw new IllegalArgumentException("Invalid URI");
		}
		}

	}

	/*
	 * @Override protected void doPost(HttpServletRequest req,
	 * HttpServletResponse resp) throws ServletException, IOException {
	 * 
	 * String username = req.getParameter("username"); String password =
	 * req.getParameter("password"); // UserBean userDetails =
	 * authenticate(username, password);
	 * 
	 * if(userDetails != null){ //successful login: store user in Session
	 * req.getSession().setAttribute("loggedInUser", userDetails);
	 * resp.sendRedirect("home.jsp"); //UserBean bean = (UserBean)
	 * req.getSession().getAttribute("loggedInUser", userDetails); } else{
	 * //failed login: send request-scope variable to login page
	 * req.setAttribute("loginFail", "Authentication Failure!");
	 * req.getRequestDispatcher("login.jsp").forward(req, resp); } }
	 * 
	 * private UserBean authenticate(String username, String password){
	 * if(username !=null){ if(username.equals("dan") &&
	 * password.equals("pickles")){ return new UserBean("dan", null,
	 * "Dan Pickles"); } else return null; } else{ return null; } }
	 */

}
