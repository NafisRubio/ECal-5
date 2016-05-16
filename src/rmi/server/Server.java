package rmi.server;

import java.nio.channels.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;

import rmi.api.ApiInterface;

/**
 * This is the server it is responsible for opening and registering the server
 * location in the rmi registry It will create a new class of the api
 * implementation and will attempt to connect it to the database
 * 
 * @author Harrys using,
 *         http://www.ejbtutorial.com/java-rmi/a-step-by-step-implementation-
 *         tutorial-for-java-rmi and
 *         http://mrbool.com/how-to-create-rmi-client-and-server-to-invoke-
 *         remove-method-of-rmi-server-in-java/28320
 */
public class Server {
	private static final int PORT = 1099;
	private static Registry registry;

	/**
	 * Allows the server to run directly
	 * 
	 * @throws java.rmi.AlreadyBoundException
	 * @throws RemoteException
	 * @throws AlreadyBoundException
	 */
	public static void main(String[] args)
			throws AlreadyBoundException, RemoteException, java.rmi.AlreadyBoundException {
		new Server();
	}

	/**
	 * Start the registry at port within this class and register the instance of
	 * api implementation in the rmi registry this will allow the corresponding
	 * method to be called when the port is accessed
	 * 
	 * @throws java.rmi.AlreadyBoundException
	 */
	public Server() throws AlreadyBoundException, RemoteException, java.rmi.AlreadyBoundException {
		startRegistry();
		registerObject(ApiInterface.class.getSimpleName(), new Implementation());
	}

	/**
	 * Start the rmi registry at port
	 * 
	 * @throws RemoteException
	 */
	public static void startRegistry() throws RemoteException {
		registry = java.rmi.registry.LocateRegistry.createRegistry(PORT);
	}

	private void registerObject(String simpleName, Remote implementation)
			throws RemoteException, AlreadyBoundException, java.rmi.AlreadyBoundException {
		registry.bind(simpleName, implementation);
		System.out.println("Registered: " + simpleName + " -> " + implementation.getClass().getName() + "["
				+ implementation + "]");
	}
}
