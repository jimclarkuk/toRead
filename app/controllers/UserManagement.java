package controllers;

import models.User;
import play.data.validation.Required;
import play.mvc.Controller;

public class UserManagement extends Controller {

	public static void index(){
		render();
	}
	
	public static void addNewUser(@Required String email, @Required String pass, @Required String fullname){
		boolean success = false;
		User user = User.find("byEmail", email).first(); 
		if( user == null){
			user = new User(email,pass, fullname).save();
			success = true;
		}
		
		render(success, user);
	}
}
