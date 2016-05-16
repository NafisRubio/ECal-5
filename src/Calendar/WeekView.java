package Calendar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.net.URL;

import javax.imageio.ImageIO;
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
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.MatteBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.FontUIResource;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.xml.crypto.Data;
import javax.swing.table.DefaultTableModel;
/**
 * @author Harrys
 * -- Updated by Naffis
 * Gui to display the view per week
 * */
public class WeekView extends JFrame {
	NewEvents newEvents = new NewEvents();
	JPanel panel = new JPanel();
	JPanel topPanel = new JPanel();
	JPanel midPanel = new JPanel();
	JButton zoomIn = new JButton("S");
	JButton zoomOut = new JButton("s");
	JButton incMonth = new JButton("+");
	JButton decMonth = new JButton("-");
	static JButton newEvent = new JButton();
	JLabel weekLabel = new JLabel("Week 1");
	JLabel labelspac = new JLabel("                                                                       ");
	JLabel monLabel = new JLabel("Monday    ");
	JLabel tueLabel = new JLabel("Tuesday    ");
	JLabel webLabel = new JLabel("Wedsday   ");
	JLabel thuLabel = new JLabel("Thurday   ");
	JLabel friLabel = new JLabel("Friday   ");
	JLabel satLabel = new JLabel("Saturday   ");
	JLabel sunLabel = new JLabel("Sunday    ");
	JLabel labelspace = new JLabel("        ");
	JTable table;
	JButton deleteEvent = new JButton("Delete Event");
	boolean changesize = false;

	JButton logOut = new JButton("Exit");

	JComboBox changeView = new JComboBox();
	int counter = 0;
	int sumSizeH = 0;
	int sumSize = 0;
	int counterC = 0;
	int row = 1;
	int column = 8;

	public BufferedWriter checkFile = creatingFile();// Log Changes
	public StringWriter logWriter = null;

	public int noOfClicks = 0;

	static JFrame window = new JFrame("Weekly");

	public WeekView() {

		super("Weekly View");

		window.setSize(650, 430);
		window.setLocation(400, 200);

		window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);// Do we
																		// wish
																		// to
																		// keep
																		// all
																		// windows
																		// open?
		window.setResizable(false);
		try {
			URL url = new URL("http://png-1.findicons.com/files/icons/1786/oxygen_refit/128/zoom_in.png");// where
			// pictures
			ImageIcon img = new ImageIcon("res/zoom_in.png");
			zoomIn = new JButton(
					new ImageIcon(((img).getImage()).getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH)));

			panel.add(zoomIn);

		} catch (IOException ex) {
		}

		try {
			URL url = new URL("http://icons.iconarchive.com/icons/gakuseisean/ivista-2/256/Misc-Zoom-Out-icon.png");
			ImageIcon img = new ImageIcon("res/Misc-Zoom-Out-icon.png");
			zoomOut = new JButton(
					new ImageIcon(((img).getImage()).getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH)));

			panel.add(zoomOut);

		} catch (IOException ex) {
		}

		zoomIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changesize = true;
				counter++;
				try {
					checkFile.append("  User Zoom In");
					checkFile.newLine();
				} catch (IOException e1) {

					e1.printStackTrace();
				}
				if (counter <= 3) {

					table.setFont(new Font("Serif", Font.BOLD, (16 + counter)));
					table.getTableHeader().setFont(new Font("Dialog", Font.CENTER_BASELINE, 10 + counter));

				}
			}

		});

		zoomOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (changesize == true) {

					try {
						checkFile.append("  User Zoom Out  ");
						checkFile.newLine();
					} catch (IOException e1) {

						e1.printStackTrace();
					}
					if (counter >= 0) {
						if (counter == 3) {
							table.setFont(new Font("Serif", Font.BOLD, (19 - 1)));

							table.getTableHeader().setFont(new Font("Dialog", Font.CENTER_BASELINE, 13 - 1));

						} else if (counter == 2) {
							table.setFont(new Font("Serif", Font.BOLD, (18 - 1)));
							table.getTableHeader().setFont(new Font("Dialog", Font.CENTER_BASELINE, 12 - 1));

						} else if (counter == 1) {
							table.setFont(new Font("Serif", Font.BOLD, (17 - 1)));
							table.getTableHeader().setFont(new Font("Dialog", Font.CENTER_BASELINE, 11 - 1));

						}
						counter--;
					}

				}
			}
		});

		try {
			URL url = new URL("http://png-5.findicons.com/files/icons/99/office/128/add2.png");
			ImageIcon img = new ImageIcon("res/add2.png");
			newEvent = new JButton(
					new ImageIcon(((img).getImage()).getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH)));

			panel.add(newEvent);

		} catch (IOException ex) {
		}

		try {
			URL url = new URL("http://www.adweek.com/socialtimes/files/2013/04/delete-300x300.jpg");
			ImageIcon img = new ImageIcon("res/delete-300x300.jpg");
			deleteEvent = new JButton(
					new ImageIcon(((img).getImage()).getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH)));

			panel.add(deleteEvent);

		} catch (IOException ex) {
		}

		try {
			URL url = new URL("http://dontgetstuck.net/wp-content/uploads/2012/06/Exit-Planning-300x300.png");
			ImageIcon img = new ImageIcon("res/Exit-Planning-300x300.png");
			logOut = new JButton(
					new ImageIcon(((img).getImage()).getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH)));

			panel.add(logOut);

		} catch (IOException ex) {
		}

		panel.add(incMonth);

		panel.add(weekLabel);
		panel.add(decMonth);

		String[] moh = { "January", "February", "March", "April", "May", "June", "July", "August", "Semptember",
				"Octomber", "November", "December" };
		final JComboBox chooseMonth = new JComboBox(moh);
		panel.add(chooseMonth);

		changeView.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (changeView.getSelectedItem().equals("daily")) {
					new DayView();

				} else if (changeView.getSelectedItem().equals("monthly")) {
					new CalendarView();
				}
				window.dispose();
			}
		});
		panel.add(changeView);

		changeView.addItem("View");
		changeView.addItem("daily");
		changeView.addItem("monthly");

		logOut.addActionListener(new ActionListener() {// close the file
			public void actionPerformed(ActionEvent e) {
				try {
					checkFile.close();
					window.dispose();
				} catch (IOException e1) {

					e1.printStackTrace();
				}
			}
		});

		chooseMonth.addActionListener(new ActionListener() {// delete everything
															// on the
			// Jtable
			public void actionPerformed(ActionEvent e) {
				String getMonth = (String) chooseMonth.getSelectedItem();
				if (!(getMonth.equals("January"))) {
					for (int i = 0; i < table.getColumnCount(); i++) {
						for (int k = 0; k < table.getRowCount(); k++) {
							table.setValueAt("", k, i); // Get Sql data if event
						}
					}
				}
			}
		});

		JTable tablemain = table();
		panel.setLayout(new BorderLayout());

		panel.add(tablemain.getTableHeader(), BorderLayout.PAGE_START);
		panel.add(tablemain, BorderLayout.CENTER);

		panel.add(tablemain);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER));
		window.getContentPane().add(panel);
		panel.setBorder(BorderFactory.createLineBorder(Color.black));

		incMonth.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				noOfClicks = noOfClicks - 1;
				if (noOfClicks == 0 || noOfClicks < 0) {
					if (noOfClicks == 0) {
						weekLabel.setText("Week1");
						noOfClicks = noOfClicks + 1;
					} else {
						weekLabel.setText("Week1");
					}
				} else if (noOfClicks == 0) {
					weekLabel.setText("Week1");

				} else if (noOfClicks == 1) {
					weekLabel.setText("Week2");

				} else if (noOfClicks == 2) {
					weekLabel.setText("Week3");

				} else if (noOfClicks == 3) {
					weekLabel.setText("Week4");

				}

				try {
					checkFile.append("  User click Left Button  ");
					checkFile.newLine();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}

		});

		decMonth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (noOfClicks <= 3) {

					noOfClicks = noOfClicks + 1;

				}
				if (noOfClicks == 1) {
					changeHeader();
					weekLabel.setText("Week2");

				} else if (noOfClicks == 2) {

					weekLabel.setText("Week3");

				} else if (noOfClicks == 3) {

					weekLabel.setText("Week4");

				} else {
					noOfClicks = noOfClicks - 1;

				}

				try {
					checkFile.append("  User click Right Button  ");
					checkFile.newLine();
				} catch (IOException e1) {

					e1.printStackTrace();
				}

			}

		});

		JPanel south = new JPanel();

		deleteEvent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				window.setLocation(570, 200);

			}
		});

		newEvent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				window.setLocation(570, 200);
				;
			}
		});

		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		table.setColumnSelectionAllowed(true);
		table.setRowSelectionAllowed(true);
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {

				MouseListener tableMouseListener = new MouseAdapter() {
					public void mouseCliked(MouseEvent e) {
						int row = table.rowAtPoint(e.getPoint());
						int col = table.columnAtPoint(e.getPoint());

						System.out.println(row + " " + col);
						System.out.println("hello");

					}

				};

			}

		});

		// Below the size of the table will be put in effect
		// it is not done at the same time as the table creation
		// because this needs for the table to already exist
		// in order to take effect
		for (int q = 0; q <= table.getColumnCount() - 1; q++) {
			TableColumn column = table.getColumnModel().getColumn(q);
			column.setPreferredWidth(90);

		}
		table.setRowHeight(40);
		table.setFont(new Font("Serif", Font.BOLD, 15));

		for (int i = 0; i < table.getColumnCount(); i++) {

			table.getTableHeader().getColumnModel().getColumn(i)
					.setHeaderValue(table.getTableHeader().getColumnModel().getColumn(i).getHeaderValue() + "  " + row);
			row++;

		}

		window.setVisible(true);

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

			table.repaint();
		}

	}

	public JTable table() {
		String[] columnName = { "Monday", "Tuesday", "Wednesday", "Thurday", "Friday", "Saturday", "Sunday" };
		Object[][] data = { { "", "", "", "", "", "", "" }, { "Hi", "", "", "", "", "", "" },
				{ "", "Look", "", "", "", "", "" }, { "Meeting 1", "", "", "Events", "", "", "" },
				{ "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "Are here!" },
				{ "", "", "", "", "", "", "" }, };

		table = new JTable(data, columnName);
		table.setModel(new DefaultTableModel(data, columnName) {
			boolean[] columnEditables = new boolean[] { false, false, false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		//
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(4).setResizable(false);
		table.getColumnModel().getColumn(5).setResizable(false);
		table.getColumnModel().getColumn(6).setResizable(false);

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int row = table.getSelectedRow();
				int col = table.getSelectedColumn();

				if (table.getValueAt(row, col) == "" || table.getValueAt(row, col) == null) {

					newEvents.main(null);
					newEvents.createEvent();
					newEvents.initialize();

				} else
				newEvents.main(null);
				newEvents.createEvent();
				newEvents.initialize();
			}
		});

		table.setPreferredScrollableViewportSize(new Dimension(1000, 1000));
		table.setFillsViewportHeight(true);
		table.setShowVerticalLines(true);
		table.setGridColor(Color.black);

		JTableHeader header = table.getTableHeader();// get the head and then
														// change color
		header.setFont(new Font("Dialog", Font.CENTER_BASELINE, 10));
		header.setForeground(Color.black);

		header.setBorder(
				new CompoundBorder(new MatteBorder(1, 1, 0, 0, Color.BLACK), new MatteBorder(2, 1, 1, 1, Color.GRAY)));
		return table;
	}

	WeekView weekView() {

		return this;
	}

	public BufferedWriter creatingFile() {

		try {
			BufferedWriter tofile = new BufferedWriter(new FileWriter("SaveInformation.txt"));

			return tofile;

		} catch (IOException e) {

			e.printStackTrace();
		}

		return null;
	}

	public void addstaffFile(BufferedWriter file, String detailsofproducts) {
		try {

			file.append("" + detailsofproducts);

		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	public void getClicked() {
		int row = table.getSelectedRow();
		int col = table.getSelectedColumn();
		String l = "                " + table.getValueAt(row, col).toString();
		String date = "    " + weekLabel.getText() + "         ";
		String startTime = (String) "      " + table.getTableHeader().getColumnModel().getColumn(col).getHeaderValue();

		String endTime = "";
		if (col == 6) {
			endTime = (String) table.getTableHeader().getColumnModel().getColumn(col).getHeaderValue() + "        ";
		} else {
			endTime = (String) table.getTableHeader().getColumnModel().getColumn(col + 1).getHeaderValue() + "        ";
		}

	}

	public void changeHeader() {
		for (int i = 0; i <= 6; i++) {

			table.getTableHeader().getColumnModel().getColumn(i).setHeaderValue("  " + row);
			row++;
			table.repaint();

		}
	}

}
