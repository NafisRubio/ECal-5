package Calendar;

import java.lang.reflect.*;
import rmi.client.Client;
import DatabaseRun.Model;
import java.awt.EventQueue;
import GeneralInfo.GlobalInfo;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JToggleButton;
import java.awt.TextArea;
import java.awt.Button;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class NewEvents {

	private JFrame frame;
	public JTextField txtNewEvent;
	public JTextField txtDate;
	public JTextField txtFrom;
	private JButton btnOK;
	private JButton btnCancel;

	Client client = new Client();
	public JTextField textLoc;
	public JTextField textTo;
	public JTextField textComments;
	public JCheckBox chckbxAllDayEvent;

	GlobalInfo id = new GlobalInfo();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewEvents window = new NewEvents();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		});

	}

	/**
	 * Create the application.
	 */
	public NewEvents() {
		initialize();
		createEvent();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 372, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel = new JLabel("Event");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lblNewLabel, BorderLayout.NORTH);

		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		txtNewEvent = new JTextField();
		txtNewEvent.setBounds(66, 25, 136, 23);
		panel.add(txtNewEvent);
		txtNewEvent.setColumns(10);

		chckbxAllDayEvent = new JCheckBox("All Day Event");
		chckbxAllDayEvent.setBounds(115, 65, 128, 23);
		chckbxAllDayEvent.enable(false);
		panel.add(chckbxAllDayEvent);

		btnOK = new JButton("OK");

		btnOK.setBounds(225, 383, 117, 29);
		panel.add(btnOK);

		btnCancel = new JButton("Cancel");

		btnCancel.setBounds(26, 383, 117, 29);
		panel.add(btnCancel);

		txtDate = new JTextField();
		txtDate.setBounds(66, 103, 136, 26);
		panel.add(txtDate);
		txtDate.setColumns(10);

		txtFrom = new JTextField();
		txtFrom.setBounds(66, 182, 67, 26);
		panel.add(txtFrom);
		txtFrom.setColumns(10);

		textTo = new JTextField();
		textTo.setColumns(10);
		textTo.setBounds(174, 182, 67, 26);
		panel.add(textTo);

		textLoc = new JTextField();
		textLoc.setBounds(66, 219, 136, 23);
		panel.add(textLoc);
		textLoc.setColumns(10);

		JLabel lblDate = new JLabel("Date");
		lblDate.setBounds(214, 108, 61, 16);
		panel.add(lblDate);

		JLabel lblTo = new JLabel("To");
		lblTo.setBounds(243, 187, 23, 16);
		panel.add(lblTo);

		JLabel lblFrom = new JLabel("From");
		lblFrom.setBounds(134, 187, 43, 16);
		panel.add(lblFrom);

		JLabel lblNewLabel_1 = new JLabel("Event Title");
		lblNewLabel_1.setBounds(207, 28, 88, 16);
		panel.add(lblNewLabel_1);

		JLabel lblLocation = new JLabel("Location");
		lblLocation.setBounds(214, 223, 81, 14);
		panel.add(lblLocation);

		JLabel lblTime = new JLabel("Time");
		lblTime.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTime.setBounds(131, 148, 46, 23);
		panel.add(lblTime);

		textComments = new JTextField();
		textComments.setBounds(26, 268, 308, 95);
		panel.add(textComments);
		textComments.setColumns(10);

	}

	/*
	 * This Part control the Events Behaviour
	 */
	public void createEvent() {

		if (chckbxAllDayEvent.isSelected() == true) {
			txtFrom.disable();
			textTo.disable();
		}

		// ////////////////////////////////////////
		// // Add the new Event into the DB ///
		// ////////////////////////////////////////
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int usrID = id.userID;
				String title = txtNewEvent.getText();
				String date = txtDate.getText();
				String from = txtFrom.getText();
				String to = textTo.getText();
				String loc = textLoc.getText();
				String com = textComments.getText();
				boolean allday = chckbxAllDayEvent.isSelected();

				client.createEvent(usrID, title, com, date, from, to, loc, true);
				System.out.println(usrID + " " + title + " " + com + " " + date
						+ " " + from + " " + to + " " + loc + " " + allday);

			}
		});
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ImageIcon icon = new ImageIcon("res/thumbsUp.png");
				JOptionPane.showMessageDialog(frame, "Done!", "\tEvent",
						JOptionPane.INFORMATION_MESSAGE, icon);
				frame.dispose();
			}
		});
		if (chckbxAllDayEvent.isSelected() == true) {
			txtFrom.setVisible(false);
			textTo.disable();
		}
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
	}
}
