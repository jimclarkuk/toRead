package controllers;

import models.User;
import play.cache.Cache;
import play.data.validation.Required;
import play.libs.Codec;
import play.libs.Images;
import play.mvc.Controller;
import play.mvc.Router;

public class UserManagement extends Controller {

	public static void index(){
		String randomID = Codec.UUID();
		render(randomID);
	}
	
	public static void addNewUser(@Required(message="Please enter your email address") String email, 
			@Required(message="Please enter a password") String pass, 
			@Required(message="Please enter your username") String fullname, 
			@Required(message="Please enter the captcha code") String code,
			String randomID){
		
		validation.equals(
		        code, Cache.get(randomID)
		    ).message("Invalid code. Please type it again");
		if(validation.hasErrors()) {
			String url = Router.reverse("UserManagement.index").url;
			render(url);
		}
		User user = User.find("byEmail", email).first(); 
		if( user == null){
			user = new User(email,pass, fullname).save();
			flash.success("New User %s created!", user.email);
		} else {
			flash.success("New User %s already exists please login.", user.email);
		}
		String url = Router.reverse("Secure.logout").url;
		render(user, url);
	}
	
	public static void captcha(String id) {
		Images.Captcha captcha = Images.captcha();
	    String code = captcha.getText("#000000");
	    Cache.set(id, code, "10mn");
	    renderBinary(captcha);
	}
}
