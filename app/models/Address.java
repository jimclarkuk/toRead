package models;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import play.db.jpa.Model;

@Entity
public class Address extends Model {
	@Lob
	public String url;
	
	@ManyToOne
	public User user;
	
	public Address(String url, User user) {
		this.url = url;
		this.user = user;
	}
	
	public Address(String url) {
		this.url = url;
		this.user = User.getDefault();
	}
	
	@Override
	public String toString() {
		return "Address [" +
				"URL: "+url+
				"User: "+user+
				"]";
	}
}
