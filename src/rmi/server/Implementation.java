package rmi.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import rmi.api.ApiInterface;
import rmi.api.Events;
import rmi.api.Ivitation;
import rmi.api.Notifications;
import rmi.api.Reminders;
import rmi.api.Users;

/**
 * In this class happens the server side implementation of our remote interface,
 * this will be a sort of MVC for example when the client invoces an rmi method
 * the coresponding model method will be executed on the server and if there is
 * a value to return will do so, this value will be returned by the use of rmi
 * back to the client
 * @author Harrys
 */
public class Implementation extends UnicastRemoteObject implements ApiInterface{
	private static final long serialVersionUID = 1L;
	
	private DatabaseRun.Model model;

	
	protected Implementation() throws RemoteException {
		super();
		model = new DatabaseRun.Model();
	}
	/**
	 * Will call method from model
	 * @see rmi.api.ApiInterface 		
	 * */
	@Override
	public int userLogOn(String name, String password) throws RemoteException {
		return	model.userLogOn(name, password);
		
	}
	/**
	 * Will call method form model
	 * */
	@Override
	public ArrayList<Notifications> getNotify(int userID) throws RemoteException {
		return model.getNotify(userID);
	}

	@Override
	public ArrayList<Ivitation> getUserInvites(int userID) throws RemoteException {
		return model.getUserInvites(userID);
	}

	@Override
	public ArrayList<Events> getEvents(int userID) throws RemoteException {
		return model.getEvents(userID);
	}

	@Override
	public ArrayList<Users> getUsers() throws RemoteException {
		return model.getUsers();
	}

	@Override
	public ArrayList<Reminders> getReminders(int userID) throws RemoteException {
		return model.getReminders(userID);
	}

	@Override
	public void userInvite(int userID, int eventID) throws RemoteException {
		model.getUserInvite(userID, eventID);
		
	}

	@Override
	public void cancelInvite(int userID, int eventID) throws RemoteException {
		model.cancelInvite(userID, eventID);
		
	}

	@Override
	public void acceptOrDecline(int userID, int eventID, boolean yesNo) throws RemoteException {
		model.acceptOrDecline(userID, eventID, yesNo);
		
	}

	@Override
	public void createEvent(int userID, String eventName, String description, String date, String eStartTime,
			String eEndTime, String loc,boolean alldayEvent) throws RemoteException {
		model.createEvent(userID, eventName, description, date, eStartTime, eEndTime, loc);
		//int userIdInt ,String eventName,String eventDescription,String eventDate,String startTime,String endTime,String location,boolean allDayEvent
	}

	@Override
	public void updateEvent(int userID, String eventName, String description, String date, String eStartTime,
			String eEndTime, String loc) throws RemoteException {
		model.updateEvent(userID, eventName, description, date, eStartTime, eEndTime,loc);
		
	}

	@Override
	public void deleteEvent(int userID, int eventID) throws RemoteException {
		model.deleteEvent(eventID, userID);
		
	}

	@Override
	public void setReminder(int userID, int eventID, int reminder) throws RemoteException {
		model.setReminder(userID, eventID, reminder);
		
	}

	@Override
	public void deleteRem(int userID, int eventID, int reminder) throws RemoteException {
		model.deleteRem(userID, eventID, reminder);
		
	}
	

}
