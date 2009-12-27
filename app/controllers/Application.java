package controllers;

import java.util.List;

import models.Address;
import play.data.validation.Required;
import play.mvc.*;

public class Application extends Controller {

    public static void index() {
    	List<Address> addresses = Address.findAll();
        render(addresses);
    }
    
    public static void addUrl(@Required String url){
    	add(url);
    	show(null);
    }
    public static void submit(@Required String url){
    	add(url);
    	show(url);
    }
    public static void show(String url){
    	render(url);
    }

	private static void add(String url) {
		if(!url.toLowerCase().startsWith("http://")){
    		url = "http://"+url;
    	}
    	new Address(url).save();
	}

}