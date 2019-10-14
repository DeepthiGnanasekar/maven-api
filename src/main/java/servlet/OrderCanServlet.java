package servlet;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.CanController;

public class OrderCanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String orderCan = request.getParameter("orderCans");
		String mobile = request.getParameter("number");
		 PrintWriter out = response.getWriter();
		       CanController service = new CanController();
		       String json = service.orderCan(orderCan,mobile);
		       out.print(json);
			   out.flush();
	    }
}
