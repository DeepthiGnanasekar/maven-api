package controller;

import com.google.gson.JsonObject;

import dao.AvailabilityDao;
import dao.UserDao;
import dao.UserDaoImp;
import model.Availability;
import model.Details;
import model.UserDetails;
import service.CanService;

public class CanServiceController {
	CanService service = new CanService();
	UserDaoImp userdao = new UserDao();
	UserDetails user = new UserDetails();
	AvailabilityDao dao = new AvailabilityDao();

	public String orderCan(String orderCan, String mobile) {
		String errorMessage = null;
		String message = null;
		UserDetails user = null;
		try {
			int canOrder = Integer.parseInt(orderCan);
			long number = Long.parseLong(mobile);
			Availability availableStock=dao.getStock();
			if (canOrder <= availableStock.getAvailability_List()) {
				user = userdao.findByMobileNumber(number);
				if (user == null) {
					errorMessage = "Please enter valid mobile number";
				} else {
					Details details = null;
					details = new Details();
					details.setQuantyList(canOrder);
					details.setNumber(number);
					service.orderCan(details);
					message = "Success";
				}
			} else {
				errorMessage = "Please enter valid number of cans to order based on availability";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
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
		UserDetails user = null;
		try {
			int canReserve = Integer.parseInt(reserveCan);
			long number = Long.parseLong(mobile);
			Availability availableStock=dao.getStock();
			if (canReserve <= availableStock.getAvailability_List()) 
			{
			user = userdao.findByMobileNumber(number);
			if (user == null) {
				throw new Exception("Please enter valid mobile number");
			} else {
				Details details = null;
				details = new Details();
				details.setReservedList(canReserve);
				details.setNumber(number);
				service.reserveCan(details);
				message = "Success";
			}
			} else {
				errorMessage = "Please enter valid number of cans to order based on availability";
			}
		} catch (Exception e) {
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
