package models;

import javax.persistence.Entity;
import javax.persistence.Lob;

import play.db.jpa.Model;

@Entity
public class Address extends Model {
	@Lob
	public String url;
	
	public Address(String url) {
		this.url = url;
	}
}
