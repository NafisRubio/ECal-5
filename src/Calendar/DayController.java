package Calendar;

import java.util.ArrayList;

import rmi.api.Events;
import rmi.api.Notifications;
import rmi.api.Users;
import rmi.client.*;

public class DayController {

	private Client client = null;

	public DayController() {
		client = new Client();
	}

	public ArrayList<Events> getEvent(int userid) {

		return client.getEvents(userid);
	}

	public void createEvent(int userID, String eventName, String eventDescription, String eventDate, String startTime,
			String endTime, String location, boolean allDayEvent) {

		client.createEvent(userID, eventName, eventDescription, eventDate, startTime, endTime, location, allDayEvent);
	}

	public void cancelEvent(int eventID, int userID) {
		client.deleteEvent(userID, eventID);
	}

	public ArrayList<Users> getUserList() {
		return client.getUser();

	}

	public ArrayList<Notifications> getNotifications(int userID) {
		return client.getNotifications(userID);

	}

	public void undoInvite(int userID, int eventID) {
		client.cancelInvite(userID, eventID);
	}

	public void userInvite(int userID, int eventID) {
		client.userInvite(userID, eventID);
	}
}
