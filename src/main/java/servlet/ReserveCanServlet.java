package servlet;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controller.CanServiceController;


public class ReserveCanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String reserveCan = request.getParameter("reserveCans");
		String mobile = request.getParameter("number");
		 PrintWriter out = response.getWriter();
	       //out.println("Enter Number Of Cans to be ordered :" + canOrder);	
		 
		       CanServiceController service = new CanServiceController();
		       String json = service.reserveCan(reserveCan,mobile);
		       out.print(json);
			   out.flush();
}
}