package Calendar;
import Calendar.*;
import DatabaseRun.Model;
import GeneralInfo.GlobalInfo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import rmi.api.Events;
import rmi.api.Notifications;



public class NotificationsView extends JFrame {
	static JTable tblnotificatiion;
	static JFrame frame = new JFrame();
	static Container pane;
	static DefaultTableModel defaultTableModel; // Model for table
	static JScrollPane jScrollPane; // scrollbane to be atached to notification will always show
	static JPanel pnlnotificatiion;
	DayController DC = new DayController();
	ArrayList<Notifications> Notification = DC
			.getNotifications(GlobalInfo.userID);
	
	JPanel panel = new JPanel();
	Model model = new Model();
	GlobalInfo global;

	public NotificationsView() {
		
		frame.setSize(220, 380);
		frame.setLocation(280, 200);
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);// Do not keep the windows open
		setResizable(false);

		JTable tablemain = NotificationTable();
		panel.setLayout(new BorderLayout());

		for (int i = 0; i < model.getUsers().size(); i++) {
			System.out.println(model.getUsers().get(i).userID);
			if ((model.getUsers().get(i).userID == global.userID)) {
				for (int k = 0; k < model.getEvents(
						model.getUsers().get(i).userID).size(); k++) {
					
					Events e = new Events();
					
					tablemain.setValueAt(
							model.getEvents(model.getUsers().get(i).userID)
									.get(k).eName, k, 0);
					
					

				}

			}
			tablemain.repaint();
		}

		panel.add(tablemain.getTableHeader(), BorderLayout.CENTER);

		panel.add(tablemain);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER));
		frame.add(panel);
		panel.setBorder(BorderFactory.createLineBorder(Color.black));
		for (int i = 0; i < tablemain.getColumnCount(); i++) {
			TableColumn column = tablemain.getColumnModel().getColumn(i);
			column.setPreferredWidth(200);

		}
		tablemain.repaint();
		frame.setVisible(true);
	}

	public JTable NotificationTable() {

		String[] tophead = { "Notification" };
		Object[][] data = new String[20][20];

		tblnotificatiion = new JTable(data, tophead);
		tblnotificatiion.setPreferredScrollableViewportSize(new Dimension(200,
				200));
		tblnotificatiion.setFillsViewportHeight(true);
		tblnotificatiion.setShowVerticalLines(true);
		// tblnotificatiion.setGridColor(Color.BLACK);
		tblnotificatiion.setForeground(Color.blue);
		tblnotificatiion.setBackground(Color.white);

		jScrollPane = new JScrollPane(tblnotificatiion);
		jScrollPane
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		jScrollPane.setVisible(true);

		return tblnotificatiion;
	}
}
