package controller;

import com.google.gson.JsonObject;

import dao.UserDao;
import dao.UserDaoImp;
import model.Details;
import model.UserDetails;
import service.CanService;

public class CanServiceController {
	CanService service = new CanService();
	UserDaoImp userdao = new UserDao();
	UserDetails user = new UserDetails();

	public String orderCan(String orderCan, String mobile) {
		String errorMessage = null;
		String message = null;
		UserDetails user = null;
		try {
			int canOrder = Integer.parseInt(orderCan);
			long number = Long.parseLong(mobile);
			user = userdao.findByMobileNumber(number);
			if (user == null) {
				throw new Exception("Please enter valid mobile number");
			} else {
				Details details = null;
				details = new Details();
				details.setQuantyList(canOrder);
				details.setNumber(number);
				service.orderCan(details);
				message = "Success";
			}
		} catch (Exception e) {
			e.printStackTrace();
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
		UserDetails user = null;
		try {
			int canReserve = Integer.parseInt(reserveCan);
			long number = Long.parseLong(mobile);
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
