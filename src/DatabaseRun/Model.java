package DatabaseRun;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import rmi.api.*;
import java.sql.*;

/**
 * Methods for connections to be used with client and server
 * 
 * @author Harrys
 */
public class Model {

	private final String USER_NAME = "root";
	private final String PASSWORD = "root";
	private final String SERVER_NAME = "localhost";
	private final int PORT_NUMBER = 3306;
	private final String DB_NAME = "eCal";

	public Model() {
		try {
			getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Get a new database connection
	 * 
	 * @return a new connection
	 * @throws SQL
	 *             exception
	 **/

	public Connection getConnection() throws SQLException {

		java.sql.Connection conn = null;
		Properties conProps = new Properties();

		conProps.put("user", this.USER_NAME);
		conProps.put("password", PASSWORD);

		conn = DriverManager.getConnection("jdbc:mysql://" + this.SERVER_NAME
				+ ":" + this.PORT_NUMBER + "/" + this.DB_NAME, conProps);

		return conn;

	}

	public boolean excecuteUpdate(Connection conn, String statement)
			throws SQLException {
		Statement stm = null;
		try {
			stm = (Statement) conn.createStatement();
			stm.executeUpdate(statement);
			return true;

		} finally {
			if (stm != null) {
				stm.close();
			}
		}

	}

	/**
	 * To be changed this is put here so you understand what the method does
	 * this will show you what needs to be done when hovering above the method
	 * so you dont need to change windows all the time
	 * 
	 * @throws SQLException
	 * 
	 * @see rmi.ApiInterface // to be changed to your comments
	 * 
	 *      This method get the statement that I created in the getStatement()
	 *      method above.
	 * 
	 *      This selects all of the data from the users table in the database
	 *      and then goes through a result set and checks for the correct
	 *      username and password.
	 * @author Dan Sparks
	 */
	public int userLogOn(String name, String password2) {
		int id = -1;
		Connection conn = null;
		try {
			conn = getConnection();
			System.out.println("Connected to the Database succesfully");
		} catch (SQLException e) {
			System.out.println("Connection unsucessful");
		}
		try {
			Statement stm = (Statement) conn.createStatement();
			String sql = "SELECT * FROM eCal.Users";
			ResultSet res = stm.executeQuery(sql);

			while (res.next()) {
				if (res.getString("username").equals(name)
						&& (res.getString("user_password").equals(password2))) {
					id = res.getInt("user_ID");
					System.out.println("Welcome");
					break;

				}
			}
			System.out.println("Not Welcome");
		} catch (Exception e) {

			System.out.println("Error");
		}
		closeConn(conn);

		return id;
	}

	/**
	 * @see rmi.ApiInterface
	 * 
	 * @Need to converse with Harrys before completion - to do on Monday
	 * @author Dan Sparks
	 * */
	public ArrayList<Notifications> getNotify(int userID) {
		ArrayList<Attend> attending = new ArrayList<Attend>();
		ArrayList<Notifications> notifications = new ArrayList<Notifications>();
		Connection conn = null;
		try {
			conn = getConnection();
			System.out.println("Connected to the Database succesfully");
		} catch (SQLException e) {
			System.out.println("Connection unsucessful");
		}
		try {
			Statement stm = (Statement) conn.createStatement();
			String sql = "SELECT * FROM eCal.Notifications INNER JOIN eCal.Users ON eCal.Users.user_ID = eCal.Notifications.user_ID"
					+ "WHERE eCal.Notifications.user_ID =" + userID + ";";
			ResultSet res = stm.executeQuery(sql);
			while (res.next()) {
				Attend x = new Attend();
				x.eventID = res.getInt("event_ID");
				attending.add(x);
				System.out.println("I acessed the notification my event ID is "
						+ x.eventID);

			}
			for (int i = 0; i < attending.size() - 1; i++) {

				int value = attending.get(i).eventID;
				String sql1 = ("SELECT * FROM eCal.Notifications WHERE event_ID = "
						+ value + ");");
				ResultSet rs1 = stm.executeQuery(sql1);

				while (rs1.next()) {
					Notifications not = new Notifications();
					not.userID = rs1.getInt("User_ID");
					not.eventID = value;
					notifications.add(not);
					break;
				}
			}

		} catch (SQLException e) {
			System.out.println("ERROR");

		}
		closeConn(conn);
		return notifications;
	}

	/**
	 * @see rmi.ApiInterface
	 * */
	public ArrayList<Ivitation> getUserInvites(int userID) {

		Connection conn = null;
		ArrayList<Ivitation> inviter = new ArrayList<Ivitation>();
		try {
			conn = getConnection();
			System.out.println("Connected to the Database succesfully");
		} catch (SQLException e) {
			System.out.println("Connection unsucessful");
		}
		try {
			Statement stm = (Statement) conn.createStatement();
			String sql = "SELECT * FROM eCal.Invites INNER JOIN eCal.Users ON eCal.Users.user_ID = eCal.Invites.user_ID"
					+ "INNER JOIN eCal.Events ON eCal.Events.event_ID = eCal.Invites.event_ID WHERE eCal.Invites.user_ID="
					+ userID;
			ResultSet res = stm.executeQuery(sql);
			while (res.next()) {
				Ivitation invite = new Ivitation();
				invite.userID0 = res.getShort("user_ID");
				invite.eventID0 = res.getInt("event_ID");
				invite.attending0 = res.getBoolean("attending"); // To create in
																	// database.
				inviter.add(invite);
			}

		} catch (SQLException e) {
			System.out.println("Error");
			e.printStackTrace();

		}
		closeConn(conn);
		return inviter;
	}

	/**
	 * @see rmi.ApiInterface
	 * */
	public ArrayList<Events> getEvents(int userID) {

		ArrayList<Events> events = new ArrayList<Events>();
		Connection conn = null;
		try {
			conn = getConnection();
			System.out.println("Connected to the Database succesfully");
		} catch (SQLException e) {
			System.out.println("Connection unsucessful");
		}
		try {
			Statement stm = (Statement) conn.createStatement();
			String sql = "SELECT * FROM events WHERE user_ID =" + userID;
			ResultSet res = stm.executeQuery(sql);

			while (res.next()) {
				Events eventer = new Events();
				eventer.userID = res.getInt("user_ID");
				eventer.eventID = res.getInt("event_ID");
				eventer.eName = res.getString("event_name");
				eventer.eDescription = res.getString("comments");
				eventer.date = res.getString("event_date");
				eventer.startTime = res.getString("event_start");
				eventer.endTime = res.getString("event_end");
				eventer.loc = res.getString("location");
				events.add(eventer);

			}
		} catch (SQLException e) {
			System.out.println(e.toString());
			System.out.println("Error");
		}
		closeConn(conn);
		return events;
	}

	public ArrayList<Users> getUsers() {
		ArrayList<Users> users = new ArrayList<Users>();
		Connection conn = null;
		try {
			conn = getConnection();
			System.out.println("Connected to the Database succesfully");
		} catch (SQLException e) {
			System.out.println("Connection unsucessful");
		}
		try {
			Statement stm = (Statement) conn.createStatement();
			String sql = "SELECT * FROM eCal.Users";
			ResultSet res = stm.executeQuery(sql);

			while (res.next()) {
				Users user = new Users();
				user.name = res.getString("user_fullname");
				user.email = res.getString("user_email");
				user.userID = res.getInt("user_ID");
				users.add(user);

			}

		} catch (Exception e) {

			e.printStackTrace();
			System.out.println("Error");
		}
		closeConn(conn);
		return users;
	}

	public ArrayList<Reminders> getReminders(int userID) {
		ArrayList<Reminders> reminders = new ArrayList<Reminders>();
		Connection conn = null;
		try {
			conn = getConnection();
			System.out.println("Connected to the Database succesfully");
		} catch (SQLException e) {
			System.out.println("Connection unsucessful");
		}
		try {
			Statement stm = (Statement) conn.createStatement();
			String sql = "SELECT * FROM eCal.Reminders INNER JOIN eCal.Users ON eCal.Users.user_ID = eCal.Remembers.user_ID"
					+ "INNER JOIN eCal.Events ON eCal.Events.event_ID = eCal.Reminders.event_ID WHERE eCal.reminders.user_ID="
					+ userID;

			ResultSet res = stm.executeQuery(sql);

			while (res.next()) {
				Reminders remind = new Reminders();
				remind.userID0 = res.getInt("user_ID");
				remind.eventID0 = res.getInt("event_ID");
				remind.reminderTime0 = res.getByte("reminder_time"); // to add
																		// to
																		// the
																		// database.

			}

		} catch (SQLException e) {

			System.out.println("Error");

		}
		closeConn(conn);
		return reminders;

	}

	public void getUserInvite(int userID, int eventID) {
		Connection conn = null;
		try {
			conn = getConnection();
			System.out.println("Connected to the Database succesfully");
		} catch (SQLException e) {
			System.out.println("Connection unsucessful");
		}
		try {
			Statement stm = (Statement) conn.createStatement();
			String sql = "INSERT INTO eCal.Invites(user_ID, event_ID) VALUES ("
					+ userID + "," + eventID + ");";
			stm.executeQuery(sql);
			System.out.println("Success");

		} catch (SQLException e) {
			System.out.println("Error");
			System.out.println(e.toString());
		}

		closeConn(conn);
	}

	public void cancelInvite(int userID, int eventID) {
		Connection conn = null;
		try {
			conn = getConnection();
			System.out.println("Connected to the Database succesfully");
		} catch (SQLException e) {
			System.out.println("Connection unsucessful");
		}

		try {
			Statement stm = (Statement) conn.createStatement();
			String sql = "DELETE FROM eCal.Invites WHERE eCal.Invites.user_ID ="
					+ userID + "AND eCal.Invites.event_ID =" + eventID;
			stm.executeQuery(sql);
			System.out.println("Success");
		} catch (SQLException e) {

			System.out.println("Error");
			System.out.println(e.toString());

		}

		closeConn(conn);
	}

	public void acceptOrDecline(int userID, int eventID, boolean yesNo) {
		Connection conn = null;
		try {
			conn = getConnection();
			System.out.println("Connected to the Database succesfully");
		} catch (SQLException e) {
			System.out.println("Connection unsucessful");
		}
		try {
			Statement stm = (Statement) conn.createStatement();
			String sql = "UPDATE TABLE eCal.Invites SET eCal.Invites.event_agree ="
					+ yesNo
					+ "WHERE eCal.Invites.user_ID ="
					+ userID
					+ "AND eCal.Invites.event_ID = " + eventID;
			stm.executeQuery(sql);
			System.out.println("Success");

		} catch (SQLException e) {
			System.out.println("Error");
			System.out.println(e.toString());
		}
		closeConn(conn);
	}

	public void createEvent(int userID, String eventName, String description,
			String date, String eStartTime, String eEndTime, String loc) {
		Connection conn = null;
		try {
			conn = getConnection();
			System.out.println("Connected to the Database succesfully");
		} catch (SQLException e) {
			System.out.println("Connection unsucessful");
		}
		try {
			Statement stm = (Statement) conn.createStatement();
			String sql = "INSERT INTO eCal.Events (event_name, comments, event_date, event_start, event_end, location, user_ID)"
					+ "VALUES('"
					+ eventName
					+ "','"
					+ description
					+ "','"
					+ date
					+ "','"
					+ eStartTime
					+ "','"
					+ eEndTime
					+ "','"
					+ loc + "','" + userID + "');";
			stm.executeUpdate(sql);
			System.out.println("Successful");
		} catch (SQLException e) {
			System.out.println("Error");
			System.out.println(e.toString());
		}
		closeConn(conn);
	}

	public void updateEvent(int userID, String eventName, String description,
			String date, String eStartTime, String eEndTime, String loc) {
		Connection conn = null;
		try {
			conn = getConnection();
			System.out.println("Connected to the Database succesfully");
		} catch (SQLException e) {
			System.out.println("Connection unsucessful");
		}
		try {
			Statement stm = (Statement) conn.createStatement();
			String sql = "UPDATE eCal.Events SET (event_name=" + eventName
					+ ",event_comments=" + description + ",event_date=" + date
					+ ",event_start=" + eStartTime + ",event_end=" + eEndTime
					+ ",location=" + loc + ") WHERE user_ID = " + userID;

			stm.executeQuery(sql);
		} catch (SQLException e) {
			System.out.println("Error");
			System.out.println(e.toString());
		}
		closeConn(conn);
	}

	public void deleteEvent(int userID, int eventID) {
		Connection conn = null;
		try {
			conn = getConnection();
			System.out.println("Connected to the Database succesfully");
		} catch (SQLException e) {
			System.out.println("Connection unsucessful");
		}
		try {
			Statement stm = (Statement) conn.createStatement();
			String sql = "DELETE FROM eCal.Events WHERE user_ID =" + userID
					+ "AND event_ID =" + eventID;
			stm.executeQuery(sql);

		} catch (SQLException e) {
			System.out.println("Error");
			System.out.println(e.toString());
		}
		closeConn(conn);
	}

	// Not sure what to pass here because of reminder being int. @dan
	public void setReminder(int userID, int eventID, int reminder) {
		Connection conn = null;
		try {
			conn = getConnection();
			System.out.println("Connected to the Database succesfully");
		} catch (SQLException e) {
			System.out.println("Connection unsucessful");
		}
		try {
			Statement stm = (Statement) conn.createStatement();
			String sql = "INSERT INTO eCal.Reminders (reminder_name, user_ID, event_ID)"
					+ "VALUES (" + reminder + userID + eventID + ");";
			stm.executeQuery(sql);

		} catch (SQLException e) {
			System.out.println("Error");
			System.out.println(e.toString());
		}
		closeConn(conn);
	}

	public void deleteRem(int userID, int eventID, int reminder) {
		Connection conn = null;
		try {
			conn = getConnection();
			System.out.println("Connected to the Database succesfully");
		} catch (SQLException e) {
			System.out.println("Connection unsucessful");
		}
		try {
			Statement stm = (Statement) conn.createStatement();
			String sql = "DELETE FROM eCal.Reminders WHERE user_ID =" + userID
					+ "AND event_ID =" + eventID;
			stm.executeQuery(sql);

		} catch (SQLException e) {
			System.out.println("Error");
			System.out.println(e.toString());

		}
		closeConn(conn);
	}

	public void closeConn(Connection c) {
		try {
			c.close();
		} catch (SQLException e) {
			System.out.println("Connection not closed");
		}
	}
}
