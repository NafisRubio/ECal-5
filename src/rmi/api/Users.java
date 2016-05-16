package rmi.api;

import java.io.Serializable;


/**
 * @author Harrys
 * Keep track of user information 
 * */
public class Users implements Serializable {

	
	private static final long serialVersionUID = 6925334332517105497L;
	public int userID = 0;
	public int phoneNumber = 0;
	public int officeNo = 0;
	
	public String name = "";
	public String middleName =  "";
	public String lastName ="";
	public String password = "";
	public String email = "";
	
	public boolean superUser = false;
	
}
