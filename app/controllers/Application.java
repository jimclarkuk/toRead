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

}