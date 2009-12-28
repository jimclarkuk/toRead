import java.util.List;

import play.*;
import play.jobs.*;
import play.test.*;
 
import models.*;
 
@OnApplicationStart
public class Bootstrap extends Job {
 
    public void doJob() {
        // Check if the database is empty
        if(User.count() == 0) {
            new User("1@1.com", "pass", "me").save();
            new User("2@2.com", "pass", "you").save();
        }
        new User("2@2.com", "pass", "you").save();
        User defaultUser = User.getDefault();
        List<Address> a = Address.findAll();
        for(Address address: a){
        	if(address.user == null){
        		address.user=defaultUser;
        		address.save();
        	}
        }
        
    }
 
}