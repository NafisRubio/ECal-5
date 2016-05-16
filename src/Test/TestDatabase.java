package Test;
import static org.junit.Assert.*;
import rmi.api.*;

import org.junit.Test;

import DatabaseRun.Model;

import java.sql.*;
import java.util.ArrayList;


/**
 * Check the model can communicate via the rmi and model to the database 
 * Testing along side Test and get Event methods from the database
 * 
 * */
public class TestDatabase {

	
	@Test
	public void testGetEvents() {
		Model model = new Model();
		
		int size = model.getEvents(1).size();
		System.out.println(size);
		int oldSize = model.getEvents(1).size();
		//Excpected Columns 5
		assertEquals(oldSize, size);
	}
	
	@Test
	public void testSetAndGetEvents() {
		Model model = new Model();
		model.getEvents(1);
		int oldSize = model.getEvents(1).size();
		model.createEvent(1, "Testing", "Testing Event", "2015-06-06", "12:00", "13:00", "Test");
		System.out.println(model.getEvents(1).size());
		assertEquals(oldSize+1, model.getEvents(1).size());
		
	}
}

