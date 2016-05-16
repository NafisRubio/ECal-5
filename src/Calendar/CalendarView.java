package Calendar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.ProcessBuilder.Redirect;
import java.time.temporal.WeekFields;
import java.util.GregorianCalendar;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.MatteBorder;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
/**
 * @author Harrys
 * -- updated naffis
 * View created as soon as login happens 
 * this is montly view
 * 
 * */
public class CalendarView extends JFrame {
	NewEvents newEvents = new NewEvents();
	JPanel panel = new JPanel();
	JPanel top = new JPanel();
	JPanel gPanel = new JPanel();

	private final JButton notificationButton = new JButton("Notification");
	JButton incFontSize = new JButton("S");
	JButton decFontSize = new JButton("s");
	JButton incMonth = new JButton("+");
	JButton decMonth = new JButton("-");
	JButton addEvent = new JButton("Add event");
	JButton weekView = new JButton("Weekly");
	JButton nextMonth = new JButton("Next Month");
	JButton prevMonth = new JButton("Previous Month");
	JButton deleteEvent = new JButton("Delete Event");
	JButton editEvent = new JButton("Edit Event");
	JButton exit = new JButton("Log Out");

	JLabel monLabel = new JLabel("Monday		");
	JLabel tueLabel = new JLabel("Tuesday	");
	JLabel wedLabel = new JLabel("Wednesday		");
	JLabel thurLabel = new JLabel("Thursday		");
	JLabel friLabel = new JLabel("Friday	");
	JLabel satLabel = new JLabel("Saturday	");
	JLabel sunLabel = new JLabel("Sunday	");
	JLabel space = new JLabel("			");
	JLabel yearLabel;

	JTable table;

	JScrollPane scrollPane;

	private final JComboBox selectMonth = new JComboBox();

	JComboBox chooseView = new JComboBox();
	JComboBox selectTime = new JComboBox();

	boolean hasClicked = false;
	boolean supUser = false;
	boolean changeSize = false;

	int days = 0;

	public int year, month;
	public int selectedYear;
	public int previousYear, nextYear;
	
	//To keep track of how to update the calendar
	public int noOfClicks = 0;
	public int rClick = 0;
	public int lClick = 0;
	public int page = 0;
	public int gCounter = 0;

	static JFrame window = new JFrame("Month View");

	public CalendarView() {
		
		super("Monthly View");
		window.setSize(650, 400);
		window.setLocation(500, 200);
		window.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);// We
																			// do
																			// not
																			// want
																			// to
																			// keep
																			// all
																			// windwos
																			// open
		window.setResizable(false); // Dont resize

		GregorianCalendar eCal = new GregorianCalendar(); // To extract real
															// month, year
		year = eCal.get(GregorianCalendar.YEAR);
		month = eCal.get(GregorianCalendar.MONTH);

		selectedYear = year;

		panel.setLayout(null);

		incFontSize.setBounds(80, 5, 40, 25);
		panel.add(incFontSize);
		// add image later on to this

		decFontSize.setBounds(120, 5, 40, 25);
		// add image later on
		panel.add(decFontSize);

		addEvent.setBounds(165, 6, 75, 25);
		// add image
		panel.add(addEvent);

		panel.add(exit);
		// add image
		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				window.dispose();

			}
		});
		decMonth.setBounds(250, 5, 40, 25);
		yearLabel = new JLabel(Integer.toString(year));
		yearLabel.setBounds(290, 10, 45, 15);

		panel.add(yearLabel);
		panel.add(decMonth);

		incMonth.setBounds(340, 6, 40, 25);
		panel.add(incMonth);

		selectTime.setBounds(390, 8, 65, 22);
		selectTime.setSelectedItem(String.valueOf(year)); // Sets the correct
															// year
		for (int i = year - 100; i <= year + 100; i++) {
			selectTime.addItem(String.valueOf(i));			//Get year values
		}
		panel.add(selectTime);
		panel.add(selectMonth);
		
		
		selectMonth.addItem("Junuary");
		selectMonth.addItem("February");
		selectMonth.addItem("March");
		selectMonth.addItem("April");
		selectMonth.addItem("May");
		selectMonth.addItem("June");
		selectMonth.addItem("July ");
		selectMonth.addItem("August");
		selectMonth.addItem("Semptember");
		selectMonth.addItem("Octomber");
		selectMonth.addItem("November");
		selectMonth.addItem("December");
	
		panel.add(notificationButton);
		
		chooseView.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(chooseView.getSelectedItem().equals("Weekly")) {
					new WeekView();
				} else if (chooseView.getSelectedItem().equals("Daily")) {
					new DayView();
				}
				window.dispose();
			}
		});
		chooseView.addItem("Select");	// set a defualt 
		chooseView.addItem("Weekly");
		chooseView.addItem("Daily");
		panel.add(chooseView);
		
		table = makeTable();
		
		
		
		panel.add(table.getTableHeader(), BorderLayout.PAGE_START);
		panel.add(table, BorderLayout.CENTER);
		panel.add(table);
		
		
		for(int i = 0; i < 5 ; i++) {
			table.getColumnModel().getColumn(i).setResizable(false);
		}
		for(int i = 0; i <= table.getColumnCount() - 1; i++) {
			TableColumn c = table.getColumnModel().getColumn(i);
			c.setPreferredWidth(90);
		}
		
		table.setRowHeight(40);
		table.setFont(new Font("Serif", Font.BOLD, 15));
		
		panel.setLayout(new FlowLayout(FlowLayout.CENTER));
		panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		window.getContentPane().add(panel);
		
		prevMonth.setBounds(215, 155, 90, 25);
		nextMonth.setBounds(125, 155, 90, 25);
		
		nextMonth.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(page < 1 ) {
					page = page ++;
					
					header();
				}
				
			}
		});
		
		weekView.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new WeekView(); 
				
			}
		});
		
		
		editEvent.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				//new EditEvent(); -- add Later way to modify events view
				
			}
		});
		
		incFontSize.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				changeSize = true;
				gCounter++;
				
				if (gCounter <= 3) {
					
					table.setFont(new Font("Serif", Font.BOLD, (16+ gCounter)));
					table.getTableHeader().setFont(
						new Font("Dialog", Font.CENTER_BASELINE, 10+gCounter));
					
				}
				
			}
		});
		
		decFontSize.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (changeSize == true) {

					if (gCounter >= 0) {
						if (gCounter == 3) {
							table.setFont(new Font("Serif", Font.BOLD, (19 - 1)));

							table.getTableHeader().setFont(
									new Font("Dialog", Font.CENTER_BASELINE,
											13 - 1));

						} else if (gCounter == 2) {
							table.setFont(new Font("Serif", Font.BOLD, (18 - 1)));
							table.getTableHeader().setFont(
									new Font("Dialog", Font.CENTER_BASELINE,
											12 - 1));

						} else if (gCounter == 1) {
							table.setFont(new Font("Serif", Font.BOLD, (17 - 1)));
							table.getTableHeader().setFont(
									new Font("Dialog", Font.CENTER_BASELINE,
											11 - 1));

						}
						gCounter--;
					}

				}
			}
		});
		
		addEvent.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				window.setLocation(570,200);
				
				newEvents.main(null);
				newEvents.createEvent();
				newEvents.initialize();
				
			}
		});;
		
		prevMonth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (page >= 0) {
					page = page - 1;
					header();
				}
			}
		});
		
		notificationButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				 new NotificationsView();
				
			}
		});
		
		decMonth.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				lClick = lClick++;

				if (lClick == 0) {
					selectedYear = selectedYear - 1;
					yearLabel.setText(Integer.toString(selectedYear));

				}

				if (lClick > 0) {
					for (int i = 0; i < lClick; i++) {

						selectedYear = selectedYear - i;
						yearLabel.setText(Integer.toString(selectedYear));
					}
				}

			}

		});
		
		incMonth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rClick = rClick++;

				if (rClick == 0) {
					selectedYear = selectedYear + 1;
					yearLabel.setText(Integer.toString(selectedYear));

				}

				if (rClick > 0) {
					for (int i = 0; i < rClick; i++) {

						selectedYear = selectedYear + i;
						yearLabel.setText(Integer.toString(selectedYear));
					}
				}

			}

		});
		
		selectMonth.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				

				for (int i = 0; i < table.getRowCount(); i++) {
					for (int k = 0; k < table.getColumnCount(); k++) {
						table.setValueAt("", i, k);
					}
				}

				if (selectMonth.getSelectedItem().equals("Junuary")) {
					days = 30;
				} else if (selectMonth.getSelectedItem().equals("February")) {
					days = 28;

				} else if (selectMonth.getSelectedItem().equals("March")) {
					days = 31;
				} else if (selectMonth.getSelectedItem().equals("April")) {
					days = 30;
				} else if (selectMonth.getSelectedItem().equals("May")) {
					days = 31;
				} else if (selectMonth.getSelectedItem().equals("June")) {
					days = 30;
				} else if (selectMonth.getSelectedItem().equals("July")) {
					days = 31;
				} else if (selectMonth.getSelectedItem().equals("August")) {
					days = 31;
				} else if (selectMonth.getSelectedItem().equals("Semptember")) {
					days = 30;
				}

				else if (selectMonth.getSelectedItem().equals("October")) {
					days = 31;
				} else if (selectMonth.getSelectedItem().equals("November")) {
					days = 30;
				} else if (selectMonth.getSelectedItem().equals("December")) {
					days = 31;
				}

				int r = 1;
				for (int i = 0; i < table.getRowCount(); i++) {
					for (int k = 0; k < table.getColumnCount(); k++) {

						if (selectMonth.getSelectedItem().equals("Junuary")) {

							if (days > 0) {
								table.setValueAt(r, i, k);
								days--;

							}
						}

						else if (selectMonth.getSelectedItem().equals(
								"February")) {

							if (days > 0) {
								table.setValueAt(r, i, k);
								days--;

							}
						} else if (selectMonth.getSelectedItem().equals(
								"March")) {
							if (days > 0) {
								table.setValueAt(r, i, k);
								days--;

							}
						} else if (selectMonth.getSelectedItem().equals(
								"April")) {
							if (days > 0) {
								table.setValueAt(r, i, k);
								days--;

							}
						} else if (selectMonth.getSelectedItem()
								.equals("May")) {

							if (days > 0) {
								table.setValueAt(r, i, k);
								days--;

							}
						} else if (selectMonth.getSelectedItem().equals(
								"June")) {

							if (days > 0) {
								table.setValueAt(r, i, k);
								days--;

							}
						}

						else if (selectMonth.getSelectedItem().equals("July")) {
							if (days > 0) {
								table.setValueAt(r, i, k);
								days--;

							}
						} else if (selectMonth.getSelectedItem().equals(
								"August")) {
							if (days > 0) {
								table.setValueAt(r, i, k);
								days--;

							}
						} else if (selectMonth.getSelectedItem().equals(
								"September")) {
							if (days > 0) {
								table.setValueAt(r, i, k);
								days--;

							}
						} else if (selectMonth.getSelectedItem().equals(
								"October")) {
							if (days > 0) {
								table.setValueAt(r, i, k);
								days--;

							}
						} else if (selectMonth.getSelectedItem().equals(
								"November")) {
							if (days > 0) {
								table.setValueAt(r, i, k);
								days--;

							}
						} else if (selectMonth.getSelectedItem().equals(
								"December")) {
							if (days > 0) {
								table.setValueAt(r, i, k);
								days--;

							}
						}
						r++;
					}

				}
			}

		});
		table.setBackground(Color.WHITE);
		
		window.setVisible(true);
	}
	public JTable makeTable() {
		
		String columnName[] = { "Monday", "Tuesday", "Wednesday", "Thursday",
				"Friday", "Saturday", "Sunday" };

		Object[][] data = { { "1", "2", "3", "4", "5", "6", "7" },
				{ "8", "9", "10", "11", "12", "13", "14" },
				{ "15", "16", "17", "18", "19", "20", "21", },
				{ "22", "23", "24", "25", "26", "27", "28" },
				{ "29", "30", "31", "", "", "", "" },

		};
		
		table = new JTable(data, columnName);
		table.setModel(new DefaultTableModel(data, columnName) {
			boolean[] columnEditables = new boolean[] {false, false, false,
					false,false,false,false};
			
			public boolean isCellEditable(int row, int column){
				return columnEditables[column];
			}
		}) ;
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent clicked) {
				
				noOfClicks++;
				
				int row = table.getSelectedRow();
				int col = table.getSelectedColumn();
				
				if(table.getValueAt(row, col)== null || table.getValueAt(row, col) == "") {
					if (noOfClicks == 2) {
						newEvents.main(null);
						newEvents.createEvent();
						newEvents.initialize();
					}
				} else if(noOfClicks == 2) {
					new DayView();
					noOfClicks = 0;
				}
			}
		});
		
		table.setBounds(40, 70, 450, 80);
		table.setPreferredScrollableViewportSize(new Dimension(1000, 1000));
		table.setFillsViewportHeight(true);
		table.setShowVerticalLines(true);
		table.setGridColor(Color.BLACK);
		
		JTableHeader header = table.getTableHeader(); // Get Head
		
		header.setFont(new Font("Arial", Font.CENTER_BASELINE, 10));
		header.setForeground(Color.BLUE); // Change Color
		header.setBorder(new CompoundBorder(new MatteBorder(1, 1, 0, 0, Color.BLACK),
				new MatteBorder(2, 1, 1, 1, Color.GRAY)));
		
		return table;
	}
	public JTableHeader header() {
		
		JTableHeader header = table.getTableHeader();
		
		if(page == 0) {
			
			header.setFont(new Font("Dialog", Font.CENTER_BASELINE,10));
			header.setForeground(Color.blue);
			
		} else if(page == 1) {
			header.setFont(new Font("Dialog", Font.CENTER_BASELINE, 10));
			header.setForeground(Color.blue);
		}
		
		table.getTableHeader().repaint();
		return header;
		
	}
	
}


