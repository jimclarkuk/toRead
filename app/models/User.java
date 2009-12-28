package models;
 
import java.util.*;
import javax.persistence.*;
 
import play.data.validation.Required;
import play.db.jpa.*;
 
@Entity
public class User extends Model {
 
    public String email;
    public String password;
    public String fullname;
    public boolean isAdmin;
    
    public User(String email, String password, String fullname) {
        this.email = email;
        this.password = password;
        this.fullname = fullname;
    }
 
    public static User connect(@Required String email, @Required String password){
    	return find("byEmailAndPassword", email, password).first();
    }

	public static User getDefault() {
		return find("byEmailAndPassword", "1@1.com", "pass").first();
	}
	
}