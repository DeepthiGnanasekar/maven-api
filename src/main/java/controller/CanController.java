package controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import dao.AvailabilityDao;
import dao.UserDao;
import dao.UserDaoImp;
import model.Availability;
import model.OrderDetails;
import model.ReserveDetails;
import model.UserDetails;
import service.CanService;

public class CanController {
	CanService service = new CanService();
	UserDaoImp userdao = new UserDao();
	UserDetails user = new UserDetails();
	AvailabilityDao dao = new AvailabilityDao();

	public String orderCan(String orderCan, String mobile) {
		String errorMessage = null;
		UserDetails user = null;
		int id = 0;
		try {
			int canOrder = Integer.parseInt(orderCan);
			long number = Long.parseLong(mobile);
			Availability availableStock = dao.getStock();
			if (canOrder <= availableStock.getAvailability_List()) {
				user = userdao.findByMobileNumber(number);
				if (user == null) {
					errorMessage = "Please enter valid mobile number";
				} else {
					OrderDetails details = null;
					details = new OrderDetails();
					details.setQuantyList(canOrder);
					details.setNumber(number);
					id = service.orderCan(details);
				}
			} else {
				errorMessage = "Please enter valid number of cans to order based on availability";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		String json = null;
		 Gson gson = new Gson();
		if (id != 0) {
			json = gson.toJson(id);
		} else if (errorMessage != null) {
			JsonObject obj = new JsonObject();
			obj.addProperty("errorMessage", errorMessage);
			json = obj.toString();
		}
		return json;
	}

	public String reserveCan(String reserveCan, String mobile) {
		String errorMessage = null;
		UserDetails user = null;
		int id =0;
		try {
			int canReserve = Integer.parseInt(reserveCan);
			long number = Long.parseLong(mobile);
			Availability availableStock=dao.getStock();
			if (canReserve <= availableStock.getAvailability_List()) 
			{
			user = userdao.findByMobileNumber(number);
			if (user == null) {
				errorMessage= "Please enter valid mobile number";
			} else {
				ReserveDetails details = null;
				details = new ReserveDetails();
				details.setReservedList(canReserve);
				details.setNumber(number);
				id = service.reserveCan(details);
			}
		} else {
			errorMessage = "Please enter valid number of cans to order based on availability";
		}
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	String json = null;
	 Gson gson = new Gson();
	if (id != 0) {
		json = gson.toJson(id);
	} else if (errorMessage != null) {
		JsonObject obj = new JsonObject();
		obj.addProperty("errorMessage", errorMessage);
		json = obj.toString();
	}
	return json;
	}
	}
