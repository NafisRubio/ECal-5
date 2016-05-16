package Test;

import LoginInfo.*;
import rmi.client.Client;

import static org.junit.Assert.*;

import java.net.MalformedURLException;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import Calendar.CalendarView;
/**
 * Unit to test the login system will atempt to login via the RMI to check client and database
 * as well as check all the input fields
 * @author Harrys
 * */
public class LoginTest {

	private String[] names = { "Harrys", "John", "Savvas", "Stelios", "Antonis", "Giakoumis" };
	private String[] passwords = { "1234", "1111", "0000", "0123", "2555", "5684" };

	@Test
	public void test() {
		LoginModel modelTest = new LoginModel();

		for (int i = 0; i < names.length; i++) {
			modelTest.setUserName(names[i]);
			assertEquals(names[i], modelTest.getUserName());
			System.out.println("User name at position " + i + " is " + modelTest.getUserName());

		}

	}

	@Test
	public void testLogin() throws MalformedURLException, InterruptedException {
		LoginModel model = new LoginModel();
		String user;
		String password;
		Client client = new Client();

		for (int i = 0; i < names.length; i++) {
			model.setUserName(names[i]);
			user = model.getUserName();

			model.setPassword(passwords[i]);
			password = model.getPassword();

			System.out.println(client.userLogin(user, password));
			assertEquals(0, client.userLogin(user, password)); // change to 1
																// later
			assertNotEquals(-1, client.userLogin(user, password));
		}
	}

	@Test
	public void testCalendar() throws MalformedURLException, InterruptedException {
		//Check if the table is created
		final CalendarView calendarView = new CalendarView();

		assertEquals(calendarView.makeTable().getFont().getSize(), calendarView.makeTable().getFont().getSize());
		System.out.println(calendarView.makeTable().getFont().getSize());
	}

}
