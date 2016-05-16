package rmi.api;

import java.io.Serializable;

/**
 * This is to keep track of attendance has to be distributed both the client and
 * server
 * @author Harrys
 */
public class Attend implements Serializable {

	private static final long serialVersionUID = 6563632100323267084L;
	public int eventID = 0;
	public int userID = 0;
	public boolean yesOrNo = false;

}
