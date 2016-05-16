package rmi.client;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

import javax.management.Notification;

import rmi.api.ApiInterface;
import rmi.api.Events;
import rmi.api.Ivitation;
import rmi.api.Notifications;
import rmi.api.Reminders;
import rmi.api.Users;

/**
 * 
 * 
 * This will be the client implementation it will take information from the
 * server. with the aid of RMI it will pass the needed parameters using the API
 * interface to the server. Furthermore the GUI will use this in order to
 * communicate and connect with the server
 * 
 * @author Harrys
 */

public class Client {

	private static final String HOST = "localhost";
	private static final int PORT = 1099;
	private static Registry registry;
	private static ApiInterface api;

	public Client() {
		try {
			registry = LocateRegistry.getRegistry(HOST, PORT);
			api = (ApiInterface) registry.lookup(ApiInterface.class
					.getSimpleName());
		} catch (Exception e) {

			System.out.println("Client Connnection Exception " + e.toString());

		}
	}

	/**
	 * Will be used to confirm if user name and password inputed in login screen
	 * were correct will return an error of -1 if incorrect
	 * 
	 * @param userName
	 * @param password
	 * @return user id if success -1 if not
	 */
	public int userLogin(String userName, String password) {
		if (userName.equals(null) || password.equals(null)) {
			return -1;
		} else {
			try {
				return api.userLogOn(userName, password);
			} catch (Exception e) {
				System.out.println("Loign error at client level: "
						+ e.toString());
				return -1;
			}
		}
	}

	/**
	 * Get user notifications
	 * 
	 * @param userID
	 * @return All notifications under the userID null if client level error
	 */
	public ArrayList<Notifications> getNotifications(int userID) {
		try {
			return api.getNotify(userID);
		} catch (RemoteException e) {
			System.out
					.println("Client level error at getting notification list"
							+ e.toString());
			return null;
		}

	}

	/**
	 * Get all events registered under userID
	 * 
	 * @param userID
	 * @return Event List or null if error
	 */
	public ArrayList<Events> getEvents(int userID) {
		try {
			return api.getEvents(userID);
		} catch (RemoteException e) {
			System.out.println("Client level error at getting event list"
					+ e.toString());
			return null;

		}
	}

	/**
	 * Get all users.
	 * 
	 * @return returns all users null if failed
	 */
	public ArrayList<Users> getUser() {
		try {
			return api.getUsers();
		} catch (RemoteException e) {
			System.out.println("Client getUser exception: " + e.toString());
			return null;
		}
	}

	/**
	 * Returns all invites for a user based on user id
	 * 
	 * @param userID
	 * @return returns invites for a user null if error
	 */
	public ArrayList<Ivitation> getInv(int userID) {
		try {
			return api.getUserInvites(userID);
		} catch (RemoteException e) {
			System.out.println("Client level error at getting invite list"
					+ e.toString());
			return null;
		}
	}

	/**
	 * Get all reminders for a user based on user id
	 * 
	 * @param userID
	 * @return reminder list null if error
	 */
	private ArrayList<Reminders> getReminder(int userID) {
		try {
			return api.getReminders(userID);
		} catch (RemoteException e) {
			System.out.println("Client level error at getting reminder list"
					+ e.toString());
			return null;
		}

	}

	/**
	 * Used by the user to accept or decline an inviation to an event
	 * 
	 * @param userID
	 *            user who is accepting or declining
	 * @param eventID
	 * @param yesNo
	 * 
	 * */
	public void acceptOrDecline(int userID, int eventID, boolean yesNo) {
		try {
			api.acceptOrDecline(userID, eventID, yesNo);
		} catch (RemoteException e) {
			System.out
					.println("Client level error at accepting or declining invitation"
							+ e.toString());
		}
	}

	/**
	 * will invite a user to a specified event
	 * 
	 * @param userID
	 * @param eventID
	 * 
	 * */
	public void userInvite(int userID, int eventID) {
		try {
			api.userInvite(userID, eventID);
		} catch (RemoteException e) {
			System.out.println("Client level error at userInvite"
					+ e.toString());
		}
	}

	/**
	 * To cancel an already sent invite on a specified event
	 * 
	 * @param userID
	 * @param eventID
	 * */
	public void cancelInvite(int userID, int eventID) {
		try {
			api.cancelInvite(userID, eventID);
		} catch (RemoteException e) {
			System.out.println("Client level error at cancel invite"
					+ e.toString());
		}
	}

	/**
	 * Will be used to create an event
	 * 
	 * @param userID
	 * @param eventName
	 * @param description
	 * @param date
	 * @param eStartTime
	 * @param eEndTime
	 * @param loc
	 * */
	public void createEvent(int userID, String eventName, String description,
			String date, String eStartTime, String eEndTime, String loc, boolean alldayEvent) {
		try {
			api.createEvent(userID, eventName, description, date, eStartTime,
					eEndTime, loc, alldayEvent);
		} catch (RemoteException e) {
			System.out
					.println("Client level error at cancel createEvent invocation"
							+ e.toString());
		}
	}

	/**
	 * This will be used to update events
	 * 
	 * @param userID
	 * @param eventName
	 * @param description
	 * @param date
	 * @param eStartTime
	 * @param eEndTime
	 * @param loc
	 * */
	public void updateEvent(int userID, String eventName, String description,
			String date, String eStartTime, String eEndTime, String loc) {
		try {
			api.updateEvent(userID, eventName, description, date, eStartTime,
					eEndTime, loc);
		} catch (RemoteException e) {
			System.out
					.println("Client level error at cancel updateEvent invocation"
							+ e.toString());
		}
	}

	/**
	 * Will be used to delete event with given eventID created by user with
	 * userID
	 * 
	 * @param userID
	 * @param eventID
	 * */
	public void deleteEvent(int userID, int eventID) {
		try {
			api.deleteEvent(userID, eventID);
		} catch (RemoteException e) {
			System.out
					.println("Client level error at cancel deleteEvent invocation"
							+ e.toString());
		}
	}

	/**
	 * Set an event reminder
	 * 
	 * @param userID
	 * @param eventID
	 * @param reminder
	 * @throws RemoteException
	 */
	public void setReminder(int userID, int eventID, int reminder) {
		try {
			api.setReminder(userID, eventID, reminder);
		} catch (RemoteException e) {
			System.out
					.println("Client level error at cancel setReminder invocation"
							+ e.toString());
		}
	}

	/**
	 * Delete an event reminder
	 * 
	 * @param userID
	 * @param eventID
	 * @param reminder
	 * @throws RemoteException
	 */
	public void deleteRem(int userID, int eventID, int reminder) {
		try {
			api.deleteEvent(userID, eventID);
		} catch (RemoteException e) {
			System.out
					.println("Client level error at cancel deleteReminder invocation"
							+ e.toString());
		}
	}
}
