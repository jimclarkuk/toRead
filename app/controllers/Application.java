package controllers;

import java.util.List;

import models.Address;
import models.User;
import play.data.validation.Required;
import play.mvc.*;

@With(Secure.class)
public class Application extends Controller {

	public static void index() {
		User user = null;
		if (Security.isConnected()) {
			user = User.find("byEmail", Security.connected()).first();
			if(user.isAdmin){
				List<Address> addresses = Address.findAll();
				render(addresses);
				return;
			}
			List<Address> addresses = Address.find("byUser", user).fetch();
			render(addresses, user);
			return;
		}
		List<Address> addresses = Address.findAll();
		render(addresses);
	}

	public static void addUrl(@Required String url) {
		add(url);
		show(null);
	}

	public static void submit(@Required String url) {
		add(url);
		show(url);
	}

	public static void show(String url) {
		render(url);
	}

	private static void add(String url) {
		if (!url.toLowerCase().startsWith("http://")) {
			url = "http://" + url;
		}
		if (Security.isConnected()) {
			User user = User.find("byEmail", Security.connected()).first();
			new Address(url, user).save();
		}
		else {
			new Address(url).save();
		}
	}
	
	public static void delete(String url, User user){
		System.out.println(url);
		System.out.println(user);
	}
	
	public static void markAsRead(String[] readaddress){
		if (Security.isConnected()) {
			User user = User.find("byEmail", Security.connected()).first();
			for (String add : readaddress) {
				List<Address> adds = Address.find("byUserAndUrl", user, add).fetch();
				for (Address address : adds) {
					address.delete();
				}
			}
		}
		index();
	}
}