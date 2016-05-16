package rmi.api;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.sql.*;

/**
 * 
 *   This interface will be available for implementation on both the client and
 * the server. According to the oracle any interaction that must be done via
 * this abstraction has to allow the client to call on this methods regardless
 * of how they are implemented on the server side, thus making the program
 * multi-platform usable in the network.
 * 
 * Any functionality that the server will have and will be publicly available to
 * use in the program has to be first implemented here.
 * 
 * @author Harrys, with the help from,
 *         https://docs.oracle.com/javase/8/docs/technotes/guides/rmi/hello/
 *         hello-world.html Taken from the oracle tutorials
 */
public interface ApiInterface extends Remote {

	/**
	 * Get information about the user on log in
	 * 
	 * @param name
	 *            The user name
	 * @param password
	 *            password inputed
	 * @return Returns an int value indicating success or failure
	 * @throws RemoteException
	 */
	public int userLogOn(String name, String password) throws RemoteException;

	/**
	 * Get all user notifications and store them in an array to update the
	 * notification object
	 * 
	 * @param userID
	 * @return an array with the notifications
	 * @throws RemoteException
	 */
	public ArrayList<Notifications> getNotify(int userID) throws RemoteException;

	/**
	 * Get all the invited users into an event based on userID
	 * 
	 * @param userID
	 * @return ArrayList All invitations
	 * @throws RemoteException
	 */
	public ArrayList<Ivitation> getUserInvites(int userID) throws RemoteException;

	/**
	 * Get Events based on userID
	 * 
	 * @param userID
	 * @return ArrayList Returns an array holding events
	 * @throws RemoteException
	 */
	public ArrayList<Events> getEvents(int userID) throws RemoteException;

	/**
	 * Returns all the users
	 * 
	 * @return ArrayList users
	 * @throws RemoteException
	 */
	public ArrayList<Users> getUsers() throws RemoteException;

	/**
	 * Get a list of the Reminders the user has set
	 * 
	 * @param userID
	 * @return Reminders as list
	 * @throws RemoteException
	 */
	public ArrayList<Reminders> getReminders(int userID) throws RemoteException;

	/**
	 * Invite a user to an event
	 * 
	 * @param userID
	 * @param eventID
	 * @throws RemoteException
	 */
	public void userInvite(int userID, int eventID) throws RemoteException;

	/**
	 * Cancel an invite
	 * 
	 * @param userID
	 * @param eventID
	 * @throws RemoteException
	 */
	public void cancelInvite(int userID, int eventID) throws RemoteException;

	/**
	 * Accept or decline invite to an event
	 * 
	 * @param userID
	 * @param eventID
	 * @param YesNo
	 *            if attending or not
	 * @throws RemoteException
	 */
	public void acceptOrDecline(int userID, int eventID, boolean yesNo) throws RemoteException;

	/**
	 * Creates Event
	 * 
	 * @param userID
	 * @param eventName
	 * @param description
	 * @param date
	 * @param eStartTime
	 * @param eEndTime
	 * @param loc
	 * @throws RemoteException
	 */
	public void createEvent(int userID, String eventName, String description, String date, String eStartTime,
			String eEndTime, String loc, boolean alldayEvent) throws RemoteException;

	/**
	 * Update Event
	 * 
	 * @param userID
	 * @param eventName
	 * @param description
	 * @param date
	 * @param eStartTime
	 * @param eEndTime
	 * @param loc
	 * @throws RemoteException
	 */
	public void updateEvent(int userID, String eventName, String description, String date, String eStartTime,
			String eEndTime, String loc) throws RemoteException;

	/**
	 * Deletes an event
	 * 
	 * @param userID
	 * @param eventID
	 * @throws RemoteException
	 */
	public void deleteEvent(int userID, int eventID) throws RemoteException;

	/**
	 * Set an event reminder
	 * 
	 * @param userID
	 * @param eventID
	 * @param reminder
	 * @throws RemoteException
	 */
	public void setReminder(int userID, int eventID, int reminder) throws RemoteException;
	
	/**
	 * Delete an event reminder
	 * 
	 * @param userID
	 * @param eventID
	 * @param reminder
	 * @throws RemoteException
	 */
	public void deleteRem(int userID, int eventID, int reminder) throws RemoteException;
}
