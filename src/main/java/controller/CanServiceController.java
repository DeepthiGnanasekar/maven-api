package controller;

import com.google.gson.JsonObject;

import model.Details;
import service.CanService;

public class CanServiceController {
       CanService service = new CanService();

	public String orderCan(String orderCan, String mobile) {
		 String errorMessage = null;
	   		String message = null;
	   		try{
	   			int canOrder = Integer.parseInt(orderCan);
	   			long number = Long.parseLong(mobile);
	   			Details details = null;
	   			details = new Details();
	   			details.setQuantyList(canOrder);
	   			details.setNumber(number);
	   			service.orderCan(details);
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
	
	public String reserveCan(String reserveCan, String mobile) {
		 String errorMessage = null;
	   		String message = null;
	   		try{
	   			int canReserve = Integer.parseInt(reserveCan);
	   			long number = Long.parseLong(mobile);
	   			Details details = null;
	   			details = new Details();
	   			details.setReservedList(canReserve);
	   			details.setNumber(number);
	   			service.reserveCan(details);
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
