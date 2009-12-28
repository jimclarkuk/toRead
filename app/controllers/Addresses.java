package controllers;
 
import models.Address;
import play.mvc.With;
 
@CRUD.For(Address.class)
@With(Secure.class)
public class Addresses extends CRUD {    
}
