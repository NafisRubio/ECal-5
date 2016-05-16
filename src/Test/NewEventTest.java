package Test;
import rmi.client.*;
import static org.junit.Assert.*;

import org.junit.Test;

import Calendar.NewEvents;
import Calendar.NewEvents;

public class NewEventTest {
        NewEvents newevent = new NewEvents();
        Client client = new Client();
        @Test
        public void test() {
/*
 * Populate the event with data
 */
                newevent.txtNewEvent.setText("New Meeting");
                newevent.txtDate.setText("2016-05-18");
                newevent.txtFrom.setText("12:00");
                newevent.textTo.setText("14:30");
                newevent.textLoc.setText("Watts 201");
                newevent.chckbxAllDayEvent.setSelected(true);
                newevent.textComments.setText("Take all my Notes");
/*
 * Pass the JTextField to a String
 */
                String title =  newevent.txtNewEvent.getText();
                String date = newevent.txtDate.getText();
                String from = newevent.txtFrom.getText();
                String to = newevent.textTo.getText();
                String loc = newevent.textLoc.getText();
                String com = newevent.textComments.getText();
                boolean allday = newevent.chckbxAllDayEvent.isSelected();
/*
 * Call the Create Event method in the Client Class and add the Strings values from above into it
 */
                client.createEvent(1, title, com, date, from, to, loc, allday);
        }
}