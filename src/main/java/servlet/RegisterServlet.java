package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.UserController;

public class RegisterServlet extends HttpServlet {
	   private static final long serialVersionUID = 1L;
	   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		   String name= request.getParameter("name");
		   String mobileNumber= request.getParameter("mobile");
	       String setPassword = request.getParameter("password");
	  
	       UserController user = new UserController();
	    	String json =   user.register(name,mobileNumber,setPassword);
	    	   PrintWriter out = response.getWriter();
	    	   out.write(json);
	    	   out.flush();
}
}
