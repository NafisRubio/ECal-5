package Calendar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Scrollbar;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Time;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ComboBoxEditor;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.xml.crypto.Data;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
/**
 * @author Harrys
 * --updated nafis
 * Daily view add/create/edit events in here via a different class
 * a new view is created for each
 * */
public class DayView extends JFrame {
	/**
	 * Clerify all the variables
	 */
	
	NewEvents newEvents = new NewEvents();
	JPanel p = new JPanel();
	JPanel topP = new JPanel();
	JPanel generalP = new JPanel();
	JButton zoomIn = new JButton("Z");
	JButton zoomOut = new JButton("z");
	JButton leftI = new JButton("<");
	JButton rightI = new JButton(">");
	static JButton eVent = new JButton("Event +");
	JComboBox admincmb = new JComboBox();
	JTextField t = new JTextField("Hello, how are you?", 30);
	JTextArea ta = new JTextArea("", 10, 10);
	JLabel labelW = new JLabel("Monday");
	JLabel labelspac = new JLabel(
			"                                                                       ");
	JLabel labelmonday = new JLabel("Monday    ");
	JLabel labeltuesday = new JLabel("Tuesday    ");
	JLabel labelwed = new JLabel("Wedsday   ");
	JLabel labelthur = new JLabel("Thurday   ");
	JLabel labelfrt = new JLabel("Friday   ");
	JLabel labelsat = new JLabel("Saturday   ");
	JLabel labelSunday = new JLabel("Sunday    ");
	JLabel labelspace = new JLabel("        ");
	JTable table;
	JButton refresh = new JButton("Refresh");
	JScrollPane scrolltable;
	boolean click = false;
	int number = 1;

	int counter = 0;
	int sumSizeH = 0;
	int sumSize = 0;
	
	JButton WeeklyV = new JButton("Weekly");
	JComboBox cmbView = new JComboBox();
	boolean changesize = false;
	
	int counterday;

	int eventID = 0;
	public int timesofclicks = 0;
	public int calPage = 0;
	ArrayList<String> returnlistOf_Records = new ArrayList<String>(20);
	JLabel daynumber = new JLabel();
	JLabel monthlyscreendata = new JLabel();


	static JFrame wind = new JFrame("Daily");

	JButton btnNextCal = new JButton("next page");
	JButton btnPrevCal = new JButton("prev page");

	public DayView() {

		super("Weekly View");

		wind.setSize(700, 360);
		wind.setLocation(400, 200);
		wind.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);// no
																		// keep
																		// all
																		// windows
																		// open
		wind.setResizable(false);

		try {
			URL url = new URL(
					"http://png-1.findicons.com/files/icons/1786/oxygen_refit/128/zoom_in.png");
			ImageIcon img = new ImageIcon("res/zoom_in.png");
			zoomIn = new JButton(new ImageIcon(
					((img).getImage()).getScaledInstance(40, 40,
							java.awt.Image.SCALE_SMOOTH)));

		

			p.add(zoomIn);

			zoomIn.setBounds(100, 100, 20, 20);

		
		} catch (IOException ex) {
		}

		try {
			URL url = new URL(
					"http://icons.iconarchive.com/icons/gakuseisean/ivista-2/256/Misc-Zoom-Out-icon.png");
			ImageIcon img = new ImageIcon(url);
			zoomOut = new JButton(new ImageIcon(
					((img).getImage()).getScaledInstance(40, 40,
							java.awt.Image.SCALE_SMOOTH)));

			p.add(zoomOut);

		} catch (IOException ex) {
		}

		zoomIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changesize = true;
				counter++;

				if (counter <= 3) {
					table.setFont(new Font("Serif", Font.BOLD, (16 + counter)));

				}
			}

		});

		p.add(zoomOut);

		zoomOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (changesize == true) {

					if (counter >= 0) {
						if (counter == 3) {
							table.setFont(new Font("Serif", Font.BOLD, (19 - 1)));
							table.getTableHeader().setFont(
									new Font("Dialog", Font.CENTER_BASELINE,
											13 - 1));

						} else if (counter == 2) {
							table.setFont(new Font("Serif", Font.BOLD, (18 - 1)));
							table.getTableHeader().setFont(
									new Font("Dialog", Font.CENTER_BASELINE,
											12 - 1));

						} else if (counter == 1) {
							table.setFont(new Font("Serif", Font.BOLD, (17 - 1)));
							table.getTableHeader().setFont(
									new Font("Dialog", Font.CENTER_BASELINE,
											11 - 1));

						}
						counter--;
					}

				}
			}
		});

		try {
			URL url = new URL(
					"http://png-5.findicons.com/files/icons/99/office/128/add2.png");
			ImageIcon img = new ImageIcon("res/add2.png");
			eVent = new JButton(new ImageIcon(
					((img).getImage()).getScaledInstance(40, 40,
							java.awt.Image.SCALE_SMOOTH)));

			
			p.add(eVent);

		
		} catch (IOException ex) {
		}

		try {
			URL url = new URL(
					"http://www.adweek.com/socialtimes/files/2013/04/delete-300x300.jpg");
			ImageIcon img = new ImageIcon("res/refresh-browser.jpg");
			refresh = new JButton(new ImageIcon(
					((img).getImage()).getScaledInstance(40, 40,
							java.awt.Image.SCALE_SMOOTH)));

			
			p.add(refresh);

			
		} catch (IOException ex) {
		}

		p.add(leftI);
		p.add(labelW);
		p.add(rightI);

		p.add(daynumber);
		p.add(monthlyscreendata);

	
		

		eVent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				wind.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				//new CreatEvent();
			}
		});

		cmbView.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (cmbView.getSelectedItem().equals("weekly")) {
					new WeekView();

				} else if (cmbView.getSelectedItem().equals("monthly")) {
					new CalendarView();
				}
				wind.dispose();
			}
		});
		p.add(cmbView);
		p.add(btnPrevCal);

		p.add(btnNextCal);

		cmbView.addItem("View");
		cmbView.addItem("weekly");
		cmbView.addItem("monthly");
	
		JTable tablemain = table();
		p.setLayout(new BorderLayout());

		p.add(tablemain.getTableHeader(), BorderLayout.PAGE_START);
		p.add(tablemain, BorderLayout.CENTER);
		p.add(tablemain);

		p.setLayout(new FlowLayout(FlowLayout.CENTER));
		wind.getContentPane().add(p);
		p.setBorder(BorderFactory.createLineBorder(Color.black));



		if (labelW.getText().equals("Monday")) {
			counterday = 1;
		} else if ((labelW.getText().equals("Tuesday"))) {
			counterday = 2;
		} else if ((labelW.getText().equals("Wednesday"))) {
			counterday = 3;
		} else if ((labelW.getText().equals("Thursday"))) {
			counterday = 4;
		} else if ((labelW.getText().equals("Friday"))) {
			counterday = 5;
		}

		else if ((labelW.getText().equals("Saturday"))) {
			counterday = 6;
		} else if ((labelW.getText().equals("Sunday"))) {
			counterday = 7;
		}

		leftI.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				counterday--;

				if (counterday >= 1 && counterday <= 7) {
					if (counterday <= 1) {
						labelW.setText("Monday");
					} else if (counterday == 2) {
						labelW.setText("Tuesday");
					} else if (counterday == 3) {
						labelW.setText("Wednesday");
					} else if (counterday == 4) {
						labelW.setText("Thurday");
					} else if (counterday == 5) {
						labelW.setText("Friday");
					} else if (counterday == 6) {
						labelW.setText("Saturday");
					} else if (counterday == 7) {
						labelW.setText("Sunday");
					}

				} else {
					counterday = 7;
				}

				int k = Integer.parseInt(daynumber.getText());
				if (k >= 1 && k <= 31) {
					daynumber.setText("" + (k - 1));
				} else {
					k = 31;
				}

			}
		});

		rightI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				counterday++;

				if (counterday >= 1 && counterday <= 7) {
					if (counterday <= 1) {
						labelW.setText("Monday");
					} else if (counterday == 2) {
						labelW.setText("Tuesday");
					} else if (counterday == 3) {
						labelW.setText("Wednesday");
					} else if (counterday == 4) {
						labelW.setText("Thurday");
					} else if (counterday == 5) {
						labelW.setText("Friday");
					} else if (counterday == 6) {
						labelW.setText("Saturday");
					} else if (counterday == 7) {
						labelW.setText("Sunday");
					}

				} else {
					counterday = 0;
				}

				int k = Integer.parseInt(daynumber.getText());
				if (k >= 1 && k <= 31) {
					daynumber.setText("" + (k + 1));
				} else {
					k = 1;
				}

			}
		});

		refresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				wind.setSize(670, 400);
				wind.setLocation(400, 200);
				wind.repaint();

			}
		});

		WeeklyV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				wind.dispose();

			}
		});

		btnNextCal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (calPage < 3) {

					calPage = calPage + 1;

					header();
					table.repaint();

				}

			}
		});

		btnPrevCal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (calPage >= 0) {
					calPage = calPage - 1;
					header();
				}
			}
		});

		
		for (int q = 0; q <= table.getColumnCount() - 1; q++) {
			TableColumn column = table.getColumnModel().getColumn(q);
			column.setPreferredWidth(110);

		}
		table.setRowHeight(40);
		table.setFont(new Font("Serif", Font.BOLD, 15));
		table.setBackground(Color.WHITE);

		wind.setVisible(true);

	}

	public void deletestff(String j) {

		for (int i = 0; i <= table.getModel().getColumnCount() - 1; i++) {
			for (int k = 0; k <= table.getModel().getRowCount() - 1; k++) {
				if (j.equals(table.getModel().getValueAt(i, k))) {
					table.setValueAt("", i, k);
					System.out.println("Success");

					break;
				}

			}

		}

		table.repaint();
	}

	public JTable table() {

	

		String columnName[] = { "01:00", "02:00", "03:00", "04:00", "05:00",
				"06:00" };

		Object[][] data = { { "", "", "", "", "", "" },
				{ "", "", "", "", "", "" }, { "", "", "", "", "", "", "" },
				{ "", "", "", "", "", "" }, { "", "", "", "", "", "" },

		};

		table = new JTable(data, columnName);
		table.setModel(new DefaultTableModel(data, columnName) {
			boolean[] columnEditables = new boolean[] { false, false, false,
					false, false, false, false };
		
		});

		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(4).setResizable(false);
		table.getColumnModel().getColumn(5).setResizable(false);
		table.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent arg0) {
				int row = table.getSelectedRow();
				int col = table.getSelectedColumn();

				if (table.getValueAt(row, col) == ""
						|| table.getValueAt(row, col) == null) {

					newEvents.main(null);
					newEvents.createEvent();
					newEvents.initialize();

				} else {
					//get events from sql
					System.out.println("Getting events...");
				}
		
			}
		});
		

		table.setPreferredScrollableViewportSize(new Dimension(100, 100));
		table.setFillsViewportHeight(true);
		table.setShowVerticalLines(true);
		table.setGridColor(Color.BLACK);
		JTableHeader header = table.getTableHeader();// get the head and then
														// change color

		header.setFont(new Font("Dialog", Font.CENTER_BASELINE, 10));
		header.setForeground(Color.blue);

		header.setBorder(new CompoundBorder(new MatteBorder(1, 1, 0, 0,
				Color.BLACK), new MatteBorder(2, 1, 1, 1, Color.GRAY)));

		return table;
	}

	public JTableHeader header() {
		JTableHeader header = table.getTableHeader();// get the head and then
														// change color
		if (calPage == 0) {
			for (int a = 0; a < table.getColumnCount(); a++) {
				for (int q = 0; q < table.getRowCount(); q++) {
					table.setValueAt("", q, a);
				}
			

				header.setFont(new Font("Dialog", Font.CENTER_BASELINE, 10));
				header.setForeground(Color.blue);

			}
		} else if (calPage == 1) {
			for (int a = 0; a < table.getColumnCount(); a++) {
				for (int q = 0; q < table.getRowCount(); q++) {
					table.setValueAt("", q, a);

				}
			}
			int counterC = 0;
			for (int i = 0; i <= 5; i++) {
				TableColumnModel tcm = header.getColumnModel();
				javax.swing.table.TableColumn tc = tcm.getColumn(i);
				int h = i + 7;
				String head = Integer.toString(h);//change the String to Integer
				if (counterC < 3) {
					tc.setHeaderValue("0" + head + ":00");
					counterC++;
				} else {
					tc.setHeaderValue(head + ":00");
				}

				int counterChangeline = 0;
				

				header.setFont(new Font("Dialog", Font.CENTER_BASELINE, 10));
				header.setForeground(Color.blue);

			}
		} else if (calPage == 2) {
			for (int a = 0; a < table.getColumnCount(); a++) {
				for (int q = 0; q < table.getRowCount(); q++) {
					table.setValueAt("", q, a);
				}
			}
			
			

			{

				header.setFont(new Font("Dialog", Font.CENTER_BASELINE, 10));
				header.setForeground(Color.blue);

			}

		} else if (calPage == 3) {
			for (int a = 0; a < table.getColumnCount(); a++) {
				for (int q = 0; q < table.getRowCount(); q++) {
					table.setValueAt("", q, a);
				}
			}

			for (int i = 0; i <= 5; i++) {
				TableColumnModel tcm = header.getColumnModel();
				javax.swing.table.TableColumn tc = tcm.getColumn(i);
				int h = i + 19;
				String head = Integer.toString(h);
				tc.setHeaderValue(head + ":00");

				int u = 0;
				

				}

				header.setFont(new Font("Dialog", Font.CENTER_BASELINE, 10));
				header.setForeground(Color.blue);

			}

		

		for (int t = 0; t < returnlistOf_Records.size(); t++) {
			System.out.println(returnlistOf_Records.get(t).toString());
		}

		table.getTableHeader().repaint();
		return header;
	}
/**
 * Getting the table details and pushing the variables to the new JLables or JTextfield on EditEvent screen
 */
	
/**
 * 
 * @return returnlistOf_Records
 */
	public ArrayList getArray() {

		return returnlistOf_Records;
	}

}
