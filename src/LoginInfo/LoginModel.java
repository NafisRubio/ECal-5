package LoginInfo;

import GeneralInfo.*;
import rmi.client.Client;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

import Calendar.CalendarView;

/**
 * @author Harrys Login GUI this will communicate with the database to extract
 *         information if the inputed information exist in the database and
 *         allow access if so
 */
public class LoginModel {

	private Client client;
	private JFrame loginFrame;
	private JPasswordField passwordField;
	private JTextField userNameField;
	private JLabel userNameLabel;
	private JLabel passwordLabel;
	private JButton loginButton;
	private JButton cancelButton;
	private JButton forgotenPassword;

	private static final String CONFIRM_TEXT = "Log In";
	private static final String CANCEL_TEXT = "Exit";
	private static final String TITLE = "Welcome to ECalendar";
	private static final String USER_NAME = "Username";
	private static final String PASSWORD = "Password";
	private static final String F_PASSWORD = "Forgoten Password";

	private boolean login = false; // set true for testing

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					LoginModel lm = new LoginModel();
					lm.loginFrame.setVisible(true);
				} catch (Exception e) {
					// To be changed
					e.printStackTrace();
				}

			}
		});
	}

	/**
	 * No MVC no need to update anything Creates the Login View
	 **/
	private void createView() {

		loginFrame = new JFrame(TITLE);
		loginFrame.setBounds(100, 100, 500, 500);
		loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		loginFrame.getContentPane().setLayout(null);

		userNameLabel = new JLabel(USER_NAME);
		userNameLabel.setBounds(100, 110, 90, 40);
		userNameLabel.setFont(new Font("Arial", Font.BOLD, 15));
		loginFrame.getContentPane().add(userNameLabel);

		userNameField = new JTextField();
		userNameField.setBounds(180, 120, 150, 20);
		userNameField.setColumns(10);
		loginFrame.getContentPane().add(userNameField);

		passwordField = new JPasswordField();
		passwordField.setBounds(180, 170, 140, 25);
		loginFrame.getContentPane().add(passwordField);

		passwordLabel = new JLabel(PASSWORD);
		passwordLabel.setBounds(95, 175, 150, 20);
		passwordLabel.setFont(new Font("Arial", Font.BOLD, 16));
		loginFrame.getContentPane().add(passwordLabel);

		cancelButton = new JButton(CANCEL_TEXT);
		cancelButton.setBounds(250, 230, 70, 25);
		cancelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);

			}
		});
		loginFrame.getContentPane().add(cancelButton);

		loginButton = new JButton(CONFIRM_TEXT);
		loginButton.setBounds(180, 230, 65, 25);
		// Add verification code here later on
		loginButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				GlobalInfo.userID = client.userLogin(getUserName(), getPassword());
				System.out.println(GlobalInfo.userID); // Debug purposes

				if (GlobalInfo.userID != -1) {

					
					GlobalInfo.user = getUserName();
					JOptionPane.showMessageDialog(null, "Greetings Citizen " + GlobalInfo.user + " You have loged in");
					login = true;
					CalendarView calendarView = new CalendarView();
					loginFrame.dispose();
				} else {
					System.out.println(GlobalInfo.user);
					JOptionPane.showMessageDialog(null, "Wrong password or username", null, JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		loginFrame.getContentPane().add(loginButton);

		forgotenPassword = new JButton(F_PASSWORD);
		forgotenPassword.setBounds(180, 285, 150, 25);
		forgotenPassword.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Please contact the administrator", null, JOptionPane.ERROR_MESSAGE,
						null);

			}
		});
		loginFrame.add(forgotenPassword);
		// layout.addLayoutComponent(TITLE, loginFrame);

	}

	/**
	 * Run the application
	 **/
	public LoginModel() {
		client = new Client();
		createView();
	}

	public String getUserName() {
		String userName = userNameField.getText();
		return userName;
	}

	public String getPassword() {
		String password = new String(passwordField.getPassword());
		return password;
	}

	public void setUserName(String string) {
		userNameField.setText(string);

	}

	public void setPassword(String string) {
		passwordField.setText(string);

	}

}
