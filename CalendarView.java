package Calendar;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

public class CalendarView {

	private JFrame frame;
	private JButton redMonth;
	private JButton incMonth;
	private JButton incFontSize;
	private JButton decFontSize;
	private JTextField mSelect;
	private JTable table;
	private JLabel mondayLabel;
	private JLabel tuesdayLabel;
	private JLabel wedLabel;
	private JLabel thurLabel;
	private JLabel friLabel;
	private JLabel satLabel;
	private JLabel sunLabel;
	private JComboBox comboBox;

	/**
	 * Runs application
	 **/

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				try {
					CalendarView w = new CalendarView();
					w.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace(); // To be changed to radical
				}

			}
		});
	}

	/**
	 * Initialises the UI
	 */
	public CalendarView() {
		start();
	}

	/**
	 * Creates the Content of UI
	 */
	private void start() {

		frame = new JFrame();
		frame.setBounds(100, 100, 550, 450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		incFontSize = new JButton("S");
		incFontSize.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				CalData.fontSize++;
				changeFont();
			}
		});
		incFontSize.setBounds(60, 5, 60, 30);
		frame.add(incFontSize);

		decFontSize = new JButton("s");
		decFontSize.setBounds(5, 5, 60, 30);
		decFontSize.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				CalData.fontSize--;
				changeFont();

			}
		});

		mondayLabel = new JLabel("Monday");
		mondayLabel.setBounds(15, 50, 50, 15);
		frame.add(mondayLabel);

		tuesdayLabel = new JLabel("Tuesday");
		tuesdayLabel.setBounds(80, 50, 55, 15);
		frame.add(tuesdayLabel);

		wedLabel = new JLabel("Wednesday");
		wedLabel.setBounds(150, 55, 70, 15);
		frame.add(wedLabel);

		thurLabel = new JLabel("Thursday");
		thurLabel.setBounds(230, 55, 70, 15);
		frame.add(thurLabel);

		friLabel = new JLabel("Friday");
		friLabel.setBounds(305, 55, 50, 15);
		frame.add(friLabel);

		satLabel = new JLabel("Staturday");
		satLabel.setBounds(450, 55, 45, 15);
		frame.add(satLabel);

		sunLabel = new JLabel("Sunday");
		sunLabel.setBounds(455, 55, 45, 15);
		frame.add(sunLabel);
		
		comboBox = new JComboBox();
		comboBox.setBounds(120, 7, 110, 25);
		comboBox.setModel(new DefaultComboBoxModel(new String[]{
			"Monthly", "Weekly", "Daily"
		}));
		comboBox.setMaximumRowCount(3);

		table = new JTable();
		table.setFillsViewportHeight(true);
		table.setColumnSelectionAllowed(true);
		table.setCellSelectionEnabled(true);
		table.setBorder(new LineBorder(new Color(1, 1, 1), 2));

		table.setModel(new DefaultTableModel(new Object[][] { 
			    { null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null }, 
				{ null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null }, 
				{ null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null },

		}, new String[] { "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday" }

		));
		table.setBounds(5, 80, 525, 350);
		frame.getContentPane().add(table);

		redMonth = new JButton("-");
		redMonth.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				CalData.month--;
				mSelect.setText(CalData.day + "/" + CalData.month + "/" + CalData.year);

			}
		});
		insertValuesTable();
	}

	public void changeFont() {
		table.setFont(new Font("Arila", Font.BOLD, CalData.fontSize));
	}
	public void insertValuesTable() {
		int days = getDays();
		int c = 0;
		for(int row = 0; row < CalData.tableRows; row++) {
			for (int column = 0; column < 7; column++) {
				if(c >= days) {
					table.setValueAt(null, row, column);
					continue;
				}
				table.setValueAt(c % days + 1, row, column);
				c++;
				System.out.println(c);
			}
		}
	}

	private int getDays() {
		int days = 0;
		Calendar eCal = new GregorianCalendar(CalData.year, CalData.month-1, CalData.day);
		return days = eCal.getActualMaximum(Calendar.DAY_OF_MONTH);
	}
}
