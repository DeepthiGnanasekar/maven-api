package controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import model.UserDetails;
import service.UserService;

public class UserController {
	public String register(String name, String mobile, String password)  {
		String errorMessage = null;
		String message = null;
		
		try {
			UserDetails user = null;
			user = new UserDetails();
			user.setName(name);
			user.setMobileNumber(mobile);
			user.setSetPassword(password);
			
			UserService service = new UserService();
			service.register(user);
			message = "Success";
			
		} catch (Exception e) {
			errorMessage = e.getMessage();
		}
		
		JsonObject obj = new JsonObject();
		if (message != null) {
			obj.addProperty("message", message);
		} else if (errorMessage != null) {
			obj.addProperty("errorMessage", errorMessage);
		}
		return obj.toString();
	}
	
	public String login(String phone_number,String password)
	{
		String errorMessage = null;
		UserDetails user = null;
		try {
			UserService project = new UserService();
            user=project.login(phone_number, password);
            if(user==null) {
            	throw new Exception("Invalid Crendentials...!!!");
            }
        } catch (Exception e) {
            errorMessage = e.getMessage();
        }       
         
        String json = null;
        Gson gson = new Gson();
        if( user != null) {
             json = gson.toJson(user);
             
        }
        else if ( user == null ) {
            JsonObject obj = new JsonObject();
            obj.addProperty("errorMessage", errorMessage);
            json = obj.toString();
        }
        return json;
	}

	
	
}
