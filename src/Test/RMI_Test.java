package Test;

import rmi.server.*;

import static org.junit.Assert.*;

import java.nio.channels.AlreadyBoundException;
import java.rmi.RemoteException;

import org.junit.Test;

import rmi.client.Client;

/**
 * will test the rmi assuming connection to Server is already running when the
 * test is executed if it is not running this will automatically fail The test
 * does not need mySQL server
 * 
 * @author Harrys
 */
public class RMI_Test {

	
	/**
	 * Test if server and client can be initialized and if they communicate via rmi
	 * Database server not needed
	 * */
	@Test
	public void testCon() {
		try {
			Server server = new Server();

		}catch(RemoteException e){
		}
		catch (Exception e) {
			fail("Port already bound");
		}
		Client client = new Client();
		try {
			client.userLogin("User not", "Not User");
		} catch (NullPointerException e) {

		} catch (Exception e) {
			fail("Should be null pointer exception since data would be unavailable or no exception at all"
					+ e.toString());
		}
	}

}
