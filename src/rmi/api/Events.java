package rmi.api;

import java.io.Serializable;

/**
 * Event data type Holds all the information about an event
 * @author Harrys
 */
public class Events implements Serializable {

	private static final long serialVersionUID = -6884902378926859262L;
	public int eventID = 0;
	public int userID = 0; // Who created event
	public String eName = "";
	public String eDescription = "";
	public String date = null;
	public String startTime = null;
	public String endTime = null;
	public String loc = null; // Describe location i.e room location building
	public boolean allDay = false; // Is the event all day long?
	public int monthDate;
	public int dayDate;
	
}
