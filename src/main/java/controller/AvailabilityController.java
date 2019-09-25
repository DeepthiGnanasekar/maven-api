package controller;
import java.util.List;
import com.google.gson.JsonObject;
import model.Availability;
import service.AvailabilityService;

public class AvailabilityController {
    AvailabilityService service = new AvailabilityService();
    
	public List<Availability> viewStock()
	{
	    List<Availability> list = null;
	    try {
			list=service.stockView();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public String setCan(String can) {
		String errorMessage = null;
		String message = null;
		try{
			int cans = Integer.parseInt(can);
		 service.setCan(cans);
		message = "Success";
		}
		catch(Exception e)
		{
			errorMessage = e.getMessage();
		}
		JsonObject json = new JsonObject();
		if (message != null) {

			json.addProperty("message", message);
		} else if (errorMessage != null) {
			json.addProperty("errorMessage", errorMessage);
		}
		return json.toString();
	}
}
