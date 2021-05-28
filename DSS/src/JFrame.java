import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLayeredPane;
import java.awt.CardLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JPasswordField;

public class JFrame extends javax.swing.JFrame {

	private JPanel contentPane;
	private JLayeredPane layeredPane;
	private JLayeredPane layeredPaneInserts;
	private JTextField textFieldLocationName;
	private JTextField textFieldLocationPopulation;
	private JTextField textFieldLocationAverageAge;
	private JTextPane textPaneInserts;
	private JTextField textFieldDateLocationSituation;
	private JTextField textFieldSickPatients;
	private JTextField textFieldRecoveredPatients;
	private JTextField textFieldDeaths;
	private JTextField textFieldAverageSickAge;
	private JTextField textFieldLockdownLevel;
	private JTextField textFieldWeather;
	private JTextField textFieldTransportLevel;
	private JTextField textFieldVitalCompanies;
	private JTextField textFieldLocation;
	private JTextField textFieldHospitalName;
	private JTextField textFieldShiftSize;
	private JTextField textFieldICUSize;
	private JTextField textFieldHospitalSize;
	private JTextField textFieldEquipmentLimit;
	private JTextField textFieldLocationHospital;
	private JTextField textFieldDateHospitalSituation;
	private JTextField textFieldICUCOVIDPatients;
	private JTextField textFieldICUTotalPatients;
	private JTextField textFieldHospitalizedCOVIDPatients;
	private JTextField textFieldHospitalizedTotalPatients;
	private JTextField textFieldActualEquipment;
	private JTextField textFieldHospital;
	
	static Processor p;
	private JTextField textFieldDatabaseUsername;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					p = new Processor();
					JFrame frame = new JFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void switchPanels(JPanel panel) {
		layeredPane.removeAll();
		layeredPane.add(panel);
		layeredPane.repaint();
		layeredPane.revalidate();
	}
	
	public void switchInsertPanels(JPanel panel) {
		layeredPaneInserts.removeAll();
		layeredPaneInserts.add(panel);
		layeredPaneInserts.add(textPaneInserts);
		layeredPaneInserts.repaint();
		layeredPaneInserts.revalidate();
	}

	/**
	 * Create the frame.
	 */
	public JFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 520);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 634, 470);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Database Connection");
		lblNewLabel.setBounds(23, 29, 151, 14);
		panel_1.add(lblNewLabel);
		
		JLabel lblDatabaseUsername = new JLabel("Database Username: ");
		lblDatabaseUsername.setBounds(23, 75, 127, 14);
		panel_1.add(lblDatabaseUsername);
		
		textFieldDatabaseUsername = new JTextField();
		textFieldDatabaseUsername.setBounds(148, 72, 127, 20);
		panel_1.add(textFieldDatabaseUsername);
		textFieldDatabaseUsername.setColumns(10);
		
		JLabel lblDatabasePassword = new JLabel("Database Password:");
		lblDatabasePassword.setBounds(310, 75, 127, 14);
		panel_1.add(lblDatabasePassword);
		
		JButton btnConnect = new JButton("Connect");
		
		btnConnect.setBounds(249, 103, 89, 23);
		panel_1.add(btnConnect);
		
		JTextPane textPaneConnection = new JTextPane();
		textPaneConnection.setVisible(false);
		textPaneConnection.setEditable(false);
		textPaneConnection.setBounds(23, 142, 582, 84);
		panel_1.add(textPaneConnection);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(447, 72, 127, 17);
		panel_1.add(passwordField);
		
		layeredPane = new JLayeredPane();
		layeredPane.setBounds(10, 75, 614, 395);
		contentPane.add(layeredPane);
		layeredPane.setLayout(new CardLayout(0, 0));
		layeredPane.setVisible(false);
		
		JPanel exportReport = new JPanel();
		exportReport.setBorder(new LineBorder(new Color(0, 0, 0)));
		layeredPane.add(exportReport, "name_105208716851800");
		exportReport.setLayout(null);
		
		JTextPane txtReporte = new JTextPane();
		txtReporte.setEditable(false);
		txtReporte.setBounds(10, 11, 594, 373);
		exportReport.add(txtReporte);
		
		JButton button = new JButton("New button");
		button.setBounds(154, 77, 89, 23);
		exportReport.add(button);
		
		JPanel insertData = new JPanel();
		insertData.setBorder(new LineBorder(new Color(0, 0, 0)));
		layeredPane.add(insertData, "name_105227632185500");
		insertData.setLayout(null);
		
		layeredPaneInserts = new JLayeredPane();
		layeredPaneInserts.setBorder(new LineBorder(new Color(0, 0, 0)));
		layeredPaneInserts.setBounds(10, 73, 594, 311);
		insertData.add(layeredPaneInserts);
		layeredPaneInserts.setLayout(null);
		
		JPanel panelInsertHospitalSituation = new JPanel();
		panelInsertHospitalSituation.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelInsertHospitalSituation.setBounds(0, 0, 594, 230);
		layeredPaneInserts.add(panelInsertHospitalSituation);
		panelInsertHospitalSituation.setLayout(null);
		
		JLabel lblDateHospitalSituation = new JLabel("Date Hospital Situation*");
		lblDateHospitalSituation.setBounds(40, 17, 137, 14);
		panelInsertHospitalSituation.add(lblDateHospitalSituation);
		
		textFieldDateHospitalSituation = new JTextField();
		textFieldDateHospitalSituation.setText("yyyy-mm-dd");
		textFieldDateHospitalSituation.setBounds(203, 11, 86, 20);
		panelInsertHospitalSituation.add(textFieldDateHospitalSituation);
		textFieldDateHospitalSituation.setColumns(10);
		
		JLabel lblICUCOVIDPatients = new JLabel("ICU COVID Patients*");
		lblICUCOVIDPatients.setBounds(52, 57, 114, 14);
		panelInsertHospitalSituation.add(lblICUCOVIDPatients);
		
		textFieldICUCOVIDPatients = new JTextField();
		textFieldICUCOVIDPatients.setBounds(203, 54, 86, 20);
		panelInsertHospitalSituation.add(textFieldICUCOVIDPatients);
		textFieldICUCOVIDPatients.setColumns(10);
		
		JLabel lblICUTotalPatients = new JLabel("ICU Total Patients*");
		lblICUTotalPatients.setBounds(65, 94, 113, 14);
		panelInsertHospitalSituation.add(lblICUTotalPatients);
		
		textFieldICUTotalPatients = new JTextField();
		textFieldICUTotalPatients.setBounds(203, 91, 86, 20);
		panelInsertHospitalSituation.add(textFieldICUTotalPatients);
		textFieldICUTotalPatients.setColumns(10);
		
		JLabel lblHospitalizedCOVIDPatients = new JLabel("Hospitalized COVID Patients*");
		lblHospitalizedCOVIDPatients.setBounds(20, 131, 172, 14);
		panelInsertHospitalSituation.add(lblHospitalizedCOVIDPatients);
		
		textFieldHospitalizedCOVIDPatients = new JTextField();
		textFieldHospitalizedCOVIDPatients.setBounds(203, 128, 86, 20);
		panelInsertHospitalSituation.add(textFieldHospitalizedCOVIDPatients);
		textFieldHospitalizedCOVIDPatients.setColumns(10);
		
		JLabel lblHospitalizedTotalPatients = new JLabel("Hospitalized Total Patients*");
		lblHospitalizedTotalPatients.setBounds(329, 14, 172, 14);
		panelInsertHospitalSituation.add(lblHospitalizedTotalPatients);
		
		textFieldHospitalizedTotalPatients = new JTextField();
		textFieldHospitalizedTotalPatients.setBounds(498, 11, 86, 20);
		panelInsertHospitalSituation.add(textFieldHospitalizedTotalPatients);
		textFieldHospitalizedTotalPatients.setColumns(10);
		
		JLabel lblActualEquipment = new JLabel("Actual Equipment* ");
		lblActualEquipment.setBounds(370, 57, 107, 14);
		panelInsertHospitalSituation.add(lblActualEquipment);
		
		textFieldActualEquipment = new JTextField();
		textFieldActualEquipment.setBounds(498, 54, 86, 20);
		panelInsertHospitalSituation.add(textFieldActualEquipment);
		textFieldActualEquipment.setColumns(10);
		
		JLabel lblHospital = new JLabel("Hospital*");
		lblHospital.setBounds(409, 97, 68, 14);
		panelInsertHospitalSituation.add(lblHospital);
		
		textFieldHospital = new JTextField();
		textFieldHospital.setBounds(498, 94, 86, 20);
		panelInsertHospitalSituation.add(textFieldHospital);
		textFieldHospital.setColumns(10);
		
		JButton btnInsertHospitalSituationForm = new JButton("Insert");
		btnInsertHospitalSituationForm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] data = new String[7];
				data[0] = textFieldDateHospitalSituation.getText();
				data[1] = textFieldICUCOVIDPatients.getText();
				data[2] = textFieldICUTotalPatients.getText();
				data[3] = textFieldHospitalizedCOVIDPatients.getText();
				data[4] = textFieldHospitalizedTotalPatients.getText();
				data[5] = textFieldActualEquipment.getText();
				data[6] = textFieldHospital.getText();
				
				try {
					p.insertHospitalSituation(data);
					textPaneInserts.setText("Hospital Situation inserted!");
				} catch (SQLException e1) {
					textPaneInserts.setText("An error has ocurred...\n"+e1.getErrorCode());
					e1.printStackTrace();
					
				}
			}
		});
		btnInsertHospitalSituationForm.setBounds(255, 196, 89, 23);
		panelInsertHospitalSituation.add(btnInsertHospitalSituationForm);
		
		JPanel panelInsertHospital = new JPanel();
		panelInsertHospital.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelInsertHospital.setBounds(0, 0, 594, 230);
		layeredPaneInserts.add(panelInsertHospital);
		panelInsertHospital.setLayout(null);
		
		JLabel lblHospitalName = new JLabel("Hospital Name* ");
		lblHospitalName.setBounds(79, 11, 93, 14);
		panelInsertHospital.add(lblHospitalName);
		
		textFieldHospitalName = new JTextField();
		textFieldHospitalName.setBounds(203, 8, 86, 20);
		panelInsertHospital.add(textFieldHospitalName);
		textFieldHospitalName.setColumns(10);
		
		JLabel lblShiftSize = new JLabel("Shift Size*");
		lblShiftSize.setBounds(98, 53, 74, 14);
		panelInsertHospital.add(lblShiftSize);
		
		textFieldShiftSize = new JTextField();
		textFieldShiftSize.setBounds(203, 50, 86, 20);
		panelInsertHospital.add(textFieldShiftSize);
		textFieldShiftSize.setColumns(10);
		
		JLabel lblICUSize = new JLabel("   ICU Size*");
		lblICUSize.setBounds(94, 92, 119, 14);
		panelInsertHospital.add(lblICUSize);
		
		textFieldICUSize = new JTextField();
		textFieldICUSize.setBounds(203, 89, 86, 20);
		panelInsertHospital.add(textFieldICUSize);
		textFieldICUSize.setColumns(10);
		
		JLabel lblHospitalSize = new JLabel("Hospital Size*");
		lblHospitalSize.setBounds(395, 11, 93, 14);
		panelInsertHospital.add(lblHospitalSize);
		
		textFieldHospitalSize = new JTextField();
		textFieldHospitalSize.setBounds(498, 11, 86, 20);
		panelInsertHospital.add(textFieldHospitalSize);
		textFieldHospitalSize.setColumns(10);
		
		JLabel lblEquipmentLimit = new JLabel("Equipment Limit*");
		lblEquipmentLimit.setBounds(386, 53, 107, 14);
		panelInsertHospital.add(lblEquipmentLimit);
		
		textFieldEquipmentLimit = new JTextField();
		textFieldEquipmentLimit.setBounds(498, 50, 86, 20);
		panelInsertHospital.add(textFieldEquipmentLimit);
		textFieldEquipmentLimit.setColumns(10);
		
		JLabel lblLocationHospital = new JLabel("Location*");
		lblLocationHospital.setBounds(417, 92, 93, 14);
		panelInsertHospital.add(lblLocationHospital);
		
		textFieldLocationHospital = new JTextField();
		textFieldLocationHospital.setBounds(498, 89, 86, 20);
		panelInsertHospital.add(textFieldLocationHospital);
		textFieldLocationHospital.setColumns(10);
		
		JButton btnInsertHospitalForm = new JButton("Insert");
		btnInsertHospitalForm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] data = new String[6];
				data[0] = textFieldHospitalName.getText();
				data[1] = textFieldShiftSize.getText();
				data[2] = textFieldICUSize.getText();
				data[3] = textFieldHospitalSize.getText();
				data[4] = textFieldEquipmentLimit.getText();
				data[5] = textFieldLocationHospital.getText();
				
				try {
					p.insertHospital(data);
					textPaneInserts.setText("Hospital inserted!");
				} catch (SQLException e1) {
					textPaneInserts.setText("An error has ocurred...\n"+e1.getErrorCode());
					e1.printStackTrace();
					
				}
			}
		});
		btnInsertHospitalForm.setBounds(255, 196, 89, 23);
		panelInsertHospital.add(btnInsertHospitalForm);
		
		JPanel panelInsertLocationSituation = new JPanel();
		panelInsertLocationSituation.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelInsertLocationSituation.setBounds(0, 0, 594, 230);
		layeredPaneInserts.add(panelInsertLocationSituation);
		panelInsertLocationSituation.setLayout(null);
		
		JLabel lblDateLocationSituation = new JLabel("Date Location Situation*");
		lblDateLocationSituation.setBounds(38, 11, 155, 14);
		panelInsertLocationSituation.add(lblDateLocationSituation);
		
		textFieldDateLocationSituation = new JTextField();
		textFieldDateLocationSituation.setText("yyyy-mm-dd");
		textFieldDateLocationSituation.setBounds(203, 8, 86, 20);
		panelInsertLocationSituation.add(textFieldDateLocationSituation);
		textFieldDateLocationSituation.setColumns(10);
		
		JLabel lblSickPatients = new JLabel("Sick Patients*");
		lblSickPatients.setBounds(87, 42, 84, 14);
		panelInsertLocationSituation.add(lblSickPatients);
		
		textFieldSickPatients = new JTextField();
		textFieldSickPatients.setBounds(203, 39, 86, 20);
		panelInsertLocationSituation.add(textFieldSickPatients);
		textFieldSickPatients.setColumns(10);
		
		JLabel lblRecoveredPatients = new JLabel("Recovered Patients*");
		lblRecoveredPatients.setBounds(54, 73, 117, 14);
		panelInsertLocationSituation.add(lblRecoveredPatients);
		
		textFieldRecoveredPatients = new JTextField();
		textFieldRecoveredPatients.setBounds(203, 70, 86, 20);
		panelInsertLocationSituation.add(textFieldRecoveredPatients);
		textFieldRecoveredPatients.setColumns(10);
		
		JLabel lblDeaths = new JLabel("Deaths*");
		lblDeaths.setBounds(110, 104, 54, 14);
		panelInsertLocationSituation.add(lblDeaths);
		
		textFieldDeaths = new JTextField();
		textFieldDeaths.setBounds(203, 101, 86, 20);
		panelInsertLocationSituation.add(textFieldDeaths);
		textFieldDeaths.setColumns(10);
		
		JLabel lblAverageSickAge = new JLabel("Average Sick Age*");
		lblAverageSickAge.setBounds(62, 135, 109, 14);
		panelInsertLocationSituation.add(lblAverageSickAge);
		
		textFieldAverageSickAge = new JTextField();
		textFieldAverageSickAge.setBounds(203, 132, 86, 20);
		panelInsertLocationSituation.add(textFieldAverageSickAge);
		textFieldAverageSickAge.setColumns(10);
		
		JLabel lblLockDownLevel = new JLabel("Lockdown Level*");
		lblLockDownLevel.setBounds(389, 14, 99, 14);
		panelInsertLocationSituation.add(lblLockDownLevel);
		
		textFieldLockdownLevel = new JTextField();
		textFieldLockdownLevel.setBounds(498, 11, 86, 20);
		panelInsertLocationSituation.add(textFieldLockdownLevel);
		textFieldLockdownLevel.setColumns(10);
		
		JLabel lblWeather = new JLabel("Weather*");
		lblWeather.setBounds(417, 49, 63, 14);
		panelInsertLocationSituation.add(lblWeather);
		
		textFieldWeather = new JTextField();
		textFieldWeather.setBounds(498, 46, 86, 20);
		panelInsertLocationSituation.add(textFieldWeather);
		textFieldWeather.setColumns(10);
		
		JLabel lblTransportLevel = new JLabel("Transport level*");
		lblTransportLevel.setBounds(389, 80, 91, 14);
		panelInsertLocationSituation.add(lblTransportLevel);
		
		textFieldTransportLevel = new JTextField();
		textFieldTransportLevel.setBounds(498, 77, 86, 20);
		panelInsertLocationSituation.add(textFieldTransportLevel);
		textFieldTransportLevel.setColumns(10);
		
		JLabel lblVitalCompanies = new JLabel("Vital Companies* ");
		lblVitalCompanies.setBounds(389, 111, 107, 14);
		panelInsertLocationSituation.add(lblVitalCompanies);
		
		textFieldVitalCompanies = new JTextField();
		textFieldVitalCompanies.setBounds(498, 108, 86, 20);
		panelInsertLocationSituation.add(textFieldVitalCompanies);
		textFieldVitalCompanies.setColumns(10);
		
		JLabel lblLocation = new JLabel("Location* ");
		lblLocation.setBounds(421, 142, 75, 14);
		panelInsertLocationSituation.add(lblLocation);
		
		textFieldLocation = new JTextField();
		textFieldLocation.setBounds(498, 139, 86, 20);
		panelInsertLocationSituation.add(textFieldLocation);
		textFieldLocation.setColumns(10);
		
		JButton btnInsertLocationSituationForm = new JButton("Insert");
		btnInsertLocationSituationForm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String[] data = new String[10];
				data[0] = textFieldDateLocationSituation.getText();
				data[1] = textFieldSickPatients.getText();
				data[2] = textFieldRecoveredPatients.getText();
				data[3] = textFieldDeaths.getText();
				data[4] = textFieldAverageSickAge.getText();
				data[5] = textFieldLockdownLevel.getText();
				data[6] = textFieldWeather.getText();
				data[7] = textFieldTransportLevel.getText();
				data[8] = textFieldVitalCompanies.getText();
				data[9] = textFieldLocation.getText();
				
				try {
					p.insertLocationSituation(data);
					textPaneInserts.setText("Location Situation inserted!");
				} catch (SQLException e1) {
					textPaneInserts.setText("An error has ocurred...\n"+e1.getErrorCode());
					e1.printStackTrace();
					
				}
			}
		});
		btnInsertLocationSituationForm.setBounds(255, 196, 89, 23);
		panelInsertLocationSituation.add(btnInsertLocationSituationForm);
		
		JPanel panelInsertLocation = new JPanel();
		panelInsertLocation.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelInsertLocation.setBounds(0, 0, 594, 230);
		layeredPaneInserts.add(panelInsertLocation);
		panelInsertLocation.setLayout(null);
		
		JLabel lblNameLocation = new JLabel("Location name*");
		lblNameLocation.setBounds(52, 11, 95, 23);
		panelInsertLocation.add(lblNameLocation);
		
		textFieldLocationName = new JTextField();
		textFieldLocationName.setBounds(186, 12, 103, 20);
		panelInsertLocation.add(textFieldLocationName);
		textFieldLocationName.setColumns(10);
		
		JLabel lblLocationPopulation = new JLabel("Location Population*");
		lblLocationPopulation.setBounds(346, 15, 125, 14);
		panelInsertLocation.add(lblLocationPopulation);
		
		textFieldLocationPopulation = new JTextField();
		textFieldLocationPopulation.setBounds(481, 12, 103, 20);
		panelInsertLocation.add(textFieldLocationPopulation);
		textFieldLocationPopulation.setColumns(10);
		
		JLabel lblLocationAverageAge = new JLabel("Location Average Age*");
		lblLocationAverageAge.setBounds(10, 45, 143, 14);
		panelInsertLocation.add(lblLocationAverageAge);
		
		textFieldLocationAverageAge = new JTextField();
		textFieldLocationAverageAge.setBounds(186, 42, 103, 20);
		panelInsertLocation.add(textFieldLocationAverageAge);
		textFieldLocationAverageAge.setColumns(10);
		
		JButton btnInsertLocationForm = new JButton("Insert");
		btnInsertLocationForm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String[] data = new String[3];
				data[0] = textFieldLocationName.getText();
				data[1] = textFieldLocationPopulation.getText();
				data[2] = textFieldLocationAverageAge.getText();
				
				System.out.println(data[0]+" - "+data[1]+" - "+data[2]);
				
				try {
					p.insertLocation(data);
					textPaneInserts.setText("Location inserted!");
				} catch (SQLException e1) {
					textPaneInserts.setText("An error has ocurred...\n"+e1.getErrorCode());
					e1.printStackTrace();
					
				}
			}
		});
		btnInsertLocationForm.setBounds(255, 196, 89, 23);
		panelInsertLocation.add(btnInsertLocationForm);
		
		textPaneInserts = new JTextPane();
		textPaneInserts.setEditable(false);
		textPaneInserts.setBounds(10, 237, 574, 63);
		layeredPaneInserts.add(textPaneInserts);
		
		JButton btnInsertLocation = new JButton("Insert Location");
		btnInsertLocation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchInsertPanels(panelInsertLocation);
			}
		});
		btnInsertLocation.setBounds(38, 11, 185, 23);
		insertData.add(btnInsertLocation);
		
		JButton btnInsertLocationSituation = new JButton("Insert Location Situation");
		btnInsertLocationSituation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchInsertPanels(panelInsertLocationSituation);
			}
		});
		btnInsertLocationSituation.setBounds(38, 45, 185, 23);
		insertData.add(btnInsertLocationSituation);
		
		JButton btnInsertHospital = new JButton("Insert Hospital");
		btnInsertHospital.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchInsertPanels(panelInsertHospital);
			}
		});
		btnInsertHospital.setBounds(380, 11, 185, 23);
		insertData.add(btnInsertHospital);
		
		JButton btnInsertHospitalSituation = new JButton("Insert Hospital Situation");
		btnInsertHospitalSituation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchInsertPanels(panelInsertHospitalSituation);
			}
		});
		btnInsertHospitalSituation.setBounds(380, 45, 185, 23);
		insertData.add(btnInsertHospitalSituation);
		
		JButton btnNewButton = new JButton("Generate report");
		btnNewButton.setVisible(false);
		btnNewButton.setBounds(68, 23, 136, 23);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				switchPanels(exportReport);
				txtReporte.setText("Generating report...");
				try {
					p.execute(txtReporte);
				} catch (Exception e) {
					txtReporte.setText(txtReporte.getText()+"\nAn error has ocurred!");
					e.printStackTrace();
				}
			}
		});
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Insert data");
		btnNewButton_1.setVisible(false);
		btnNewButton_1.setBounds(423, 23, 123, 23);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanels(insertData);
				switchInsertPanels(panelInsertLocation);
			}
		});
		contentPane.add(btnNewButton_1);
		
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					p.connect(textFieldDatabaseUsername.getText(), String.valueOf(passwordField.getPassword()));
					panel_1.setVisible(false);
					btnNewButton_1.setVisible(true);
					btnNewButton.setVisible(true);
					layeredPane.setVisible(true);
				} catch (SQLException e1) {
					textPaneConnection.setText("Error: Invalid Username or Password");
					textPaneConnection.setVisible(true);
				}
			}
		});
	}
}
