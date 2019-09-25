package controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import model.AdminDetails;
import service.AdminService;

public class AdminController {
	public String adminLogin(String name,String password)
	{
		String errorMessage = null;
       AdminDetails admin  = null ;
        try {
        	admin=new AdminDetails();
			AdminService services = new AdminService();
			admin=services.adminLogin(name, password); 
			if(admin==null) {
            	throw new Exception("Invalid Crendentials...!!!");
            }
        } catch (Exception e) {
            errorMessage = e.getMessage();
        }       
        String json = null;
        Gson gson = new Gson();
        if( admin != null) {
             json = gson.toJson(admin);
             
        }
        else if ( admin == null ) {
            JsonObject obj = new JsonObject();
            obj.addProperty("errorMessage", errorMessage);
            json = obj.toString();
        }
        return json;
	}
}