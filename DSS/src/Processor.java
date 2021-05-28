import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.sql.*;

import javax.swing.JTextPane;


public class Processor {
	private Connection con;
	
	
	private String[] names;
	private String[][] values;
	private String[][] scores;
	private String[][] total;
	private String[][] colors;
	private String[] criteria = new String[29];
	private int[] weighting = new int[]{5, 5, 5, 5, 5, 4, 4, 4, 4, 4, 4, 4, 1, 1, 1, 1, 1, 3, 1, 4, 3, 3, 4, 4, 3, 2, 1, 2};
	
	private String[][] insert;
	
	private LinearRegression lr;
	
	Processor(){
		
	}
	
	public void connect(String databaseUsername, String databasePassword) throws SQLException {
		DBConnection dbc = new DBConnection(databaseUsername, databasePassword);
		this.con = dbc.getConnection();
	}
	
	public void execute(JTextPane text) throws SQLException {
		
		
		
		
			 setMatrix();
			 setLocation();
			 setICUTotalPatients();
			 setExpectedICUTotalPatients();
			 setICUCovidPatients();
			 setExpectedICUCovidPatients();
			 setRatioUCIPatients();
			 setHospitalizedTotalPatients();
			 setExpectedHospitalizedTotalPatients();
			 setHospitalizedCovidPatients();
			 setExpectedHospitalizedCovidPatients();
			 setRatioHospitalizedPatients();
			 setAmountCOVIDpatients();
			 setExpectedAmountCOVIDpatients();
			 setAmountRecoveredCOVIDpatients();
			 setExpectedRecoveredCOVIDpatients();
			 setRatioRecoveredPatients();
			 setAmountDeadCOVIDpatients();
			 setExpectedDeadCOVIDpatients();
			 setRatioRecoveredPatientsDeadPatients();
			 setAverageAge();
			 setAverageAgeSickPatients();
			 setMedicalPersonnel();
			 setExpectedMedicalPersonnel();
			 setReserveOfMedicalSupplies();
			 setDailyMaterialConsumption();
			 setLockDownLevel();
			 setTransportLevel();
			 setWeatherType();
			 setVitalCompanies();
			 setSumOfImportance();
			 
			 insertPrediction();
			   
			//con.close(); 
		
		
		HTMLGenerator htmlG = new HTMLGenerator();
		htmlG.createHTML(names, values, scores, total, criteria, weighting, colors);
		
		text.setText(text.getText()+"\n"+"Report generated");
		
		File htmlFile = new File("DSSReport/report.html");
		try {
			Desktop.getDesktop().browse(htmlFile.toURI());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void setMatrix() throws SQLException {
		Statement stmt=this.con.createStatement();  
		ResultSet rs=stmt.executeQuery("select count(location_id) from location");  
		int c=0;
		while(rs.next()) {
			c = rs.getInt(1);
		}
		names = new String[c];
		values = new String[c][28];
		scores = new String[c][28];
		total = new String[c][29];
		colors = new String[c][29];
		
		insert = new String[c*3][12];
	}
	
	public void setLocation() throws SQLException {
		Statement stmt=this.con.createStatement();  
		ResultSet rs=stmt.executeQuery("SELECT * FROM location");  
		int c=0;
		while(rs.next()) {
			names[c] = rs.getString("NAME");
			c++;
		}
	}
	
	public void setICUTotalPatients() throws SQLException {
		criteria[0] = "ICU total occupancy percentage";
		Statement stmt = this.con.createStatement();
		Statement stmt2 = this.con.createStatement();

		for (int i = 0; i < names.length; i++) {
			ResultSet rs = stmt.executeQuery("select sum(hs.icu_total_patients) from hospital_situation hs "
					+ "inner join hospital h on h.hospital_id = hs.hospital_id "
					+ "inner join location l on l.location_id = h.location_id " + "where l.name = '" + names[i]
					+ "' and " + "hs.date_hosp_situation = (select max(hs2.date_hosp_situation)  "
					+ "                            from hospital_situation  hs2 "
					+ "                            where hs.hospital_id = hs2.hospital_id "
					+ "                            group by hs2.hospital_id)");

			ResultSet rs2 = stmt2.executeQuery("select sum(h.icu_size) from hospital h "
					+ "inner join location l on l.location_id = h.location_id " + "where l.name = '" + names[i] + "'");

			if (rs.next() && rs2.next()) {
				float op1 = Integer.parseInt(rs.getString(1)) * 100;
				float op2 = Integer.parseInt(rs2.getString(1));
				float aux = (float) (Math.round(op1 / op2 * 100.0) / 100.0);
				values[i][0] = String.valueOf(aux) + "%";

				if (aux <= 20.0)
					scores[i][0] = "1";
				else if (aux > 20.0 && aux <= 40.0)
					scores[i][0] = "2";
				else if (aux > 40.0 && aux <= 60.0)
					scores[i][0] = "3";
				else if (aux > 60.0 && aux <= 80.0)
					scores[i][0] = "4";
				else if (aux > 80.0 && aux <= 100.0)
					scores[i][0] = "5";

				colors[i][0] = "color" + scores[i][0];
				total[i][0] = String.valueOf(weighting[0] * Integer.parseInt(scores[i][0]));
			}
		}
	}
	
	public void setExpectedICUTotalPatients() throws SQLException {
		criteria[1] = "Expected ICU total occupancy percentage 1, 3 and 7 days later";
		Statement stmt=this.con.createStatement();
		Statement stmt2=this.con.createStatement();
		
		for(int i = 0, j=0; i<names.length;i++) {
			ResultSet rs=stmt.executeQuery("select s from( " + 
										"    select hs.date_hosp_situation, sum(hs.icu_total_patients) s from hospital_situation hs " + 
										"    inner join hospital h on h.hospital_id = hs.hospital_id " + 
										"    inner join location l on l.location_id = h.location_id " + 
										"    where l.name = '"+names[i]+"' " + 
										"    group by hs.date_hosp_situation " + 
										"    order by hs.date_hosp_situation DESC) " + 
										"where rownum<=15");  
			
			ResultSet rs2=stmt2.executeQuery("select sum(h.icu_size) from hospital h " + 
											"inner join location l on l.location_id = h.location_id " + 
											"where l.name = '"+names[i]+"'");  
			
			if(rs2.next()) {
				double[] y = new double[15];
				int c = 14;
				while(rs.next()) {
					y[c] = Double.parseDouble(rs.getString(1));
					c--;
				}	
				
				lr = new LinearRegression(y);
				double dayLater1 = lr.getPredictY(15)*100;
				double dayLater3 = lr.getPredictY(17)*100;
				double dayLater7 = lr.getPredictY(21)*100;
				
				insert[i+j][2] = String.valueOf((int)dayLater1/100);
				j++;
				insert[i+j][2] = String.valueOf((int)dayLater3/100);
				j++;
				insert[i+j][2] = String.valueOf((int)dayLater7/100);
				j=2*(i+1);
				
				float op2 = Integer.parseInt(rs2.getString(1));
				float aux1 = (float) (Math.round(dayLater1/op2*100.0)/100.0);
				float aux3 = (float) (Math.round(dayLater3/op2*100.0)/100.0);
				float aux7 = (float) (Math.round(dayLater7/op2*100.0)/100.0);
				
				values[i][1] = "1 Day later: "+String.valueOf(aux1)+"%<br>"
								+"3 Days later: "+String.valueOf(aux3)+"%<br> "
								+"7 Days later: "+String.valueOf(aux7)+"%";
				
				if(aux7 <= 20.0)
					scores[i][1] = "1";
				else if(aux7 > 20.0 && aux7 <= 40.0)
					scores[i][1] = "2";
				else if(aux7 > 40.0 && aux7 <= 60.0)
					scores[i][1] = "3";
				else if(aux7 > 60.0 && aux7 <= 80.0)
					scores[i][1] = "4";
				else if(aux7 > 80.0 && aux7 <= 100.0)
					scores[i][1] = "5";
				
				colors[i][1] = "color"+scores[i][1];
				total[i][1] = String.valueOf(weighting[1]*Integer.parseInt(scores[i][1]));
				
			}	
		}
	}
	
	public void setICUCovidPatients() throws SQLException {
		criteria[2] = "ICU COVID-19 occupancy percentage";
		Statement stmt = this.con.createStatement();
		Statement stmt2 = this.con.createStatement();

		for (int i = 0; i < names.length; i++) {
			ResultSet rs = stmt.executeQuery("select sum(hs.icu_covid_patients) from hospital_situation hs "
					+ "inner join hospital h on h.hospital_id = hs.hospital_id "
					+ "inner join location l on l.location_id = h.location_id " + "where l.name = '" + names[i]
					+ "' and " + "hs.date_hosp_situation = (select max(hs2.date_hosp_situation)  "
					+ "                            from hospital_situation  hs2 "
					+ "                            where hs.hospital_id = hs2.hospital_id "
					+ "                            group by hs2.hospital_id)");

			ResultSet rs2 = stmt2.executeQuery("select sum(h.icu_size) from hospital h "
					+ "inner join location l on l.location_id = h.location_id " + "where l.name = '" + names[i] + "'");

			if (rs.next() && rs2.next()) {
				float op1 = Integer.parseInt(rs.getString(1)) * 100;
				float op2 = Integer.parseInt(rs2.getString(1));
				float aux = (float) (Math.round(op1 / op2 * 100.0) / 100.0);
				values[i][2] = String.valueOf(aux) + "%";

				if (aux <= 15.0)
					scores[i][2] = "1";
				else if (aux > 15.0 && aux <= 30.0)
					scores[i][2] = "2";
				else if (aux > 30.0 && aux <= 45.0)
					scores[i][2] = "3";
				else if (aux > 45.0 && aux <= 60.0)
					scores[i][2] = "4";
				else if (aux > 60.0)
					scores[i][2] = "5";

				colors[i][2] = "color" + scores[i][2];
				total[i][2] = String.valueOf(weighting[2] * Integer.parseInt(scores[i][2]));
			}
		}
	}
	
	public void setExpectedICUCovidPatients() throws SQLException {
		criteria[3] = "Expected ICU COVID-19 occupancy percentage 1, 3 and 7 days later";
		Statement stmt=this.con.createStatement();
		Statement stmt2=this.con.createStatement();
		
		for(int i = 0, j=0; i<names.length;i++) {
			ResultSet rs=stmt.executeQuery("select s from( " + 
										"    select hs.date_hosp_situation, sum(hs.icu_covid_patients) s from hospital_situation hs " + 
										"    inner join hospital h on h.hospital_id = hs.hospital_id " + 
										"    inner join location l on l.location_id = h.location_id " + 
										"    where l.name = '"+names[i]+"' " + 
										"    group by hs.date_hosp_situation " + 
										"    order by hs.date_hosp_situation DESC) " + 
										"where rownum<=15");  
			
			ResultSet rs2=stmt2.executeQuery("select sum(h.icu_size) from hospital h " + 
											"inner join location l on l.location_id = h.location_id " + 
											"where l.name = '"+names[i]+"'");  
			
			if(rs2.next()) {
				double[] y = new double[15];
				int c = 14;
				while(rs.next()) {
					y[c] = Double.parseDouble(rs.getString(1));
					c--;
				}	
				
				lr = new LinearRegression(y);
				double dayLater1 = lr.getPredictY(15)*100;
				double dayLater3 = lr.getPredictY(17)*100;
				double dayLater7 = lr.getPredictY(21)*100;
				
				insert[i+j][3] = String.valueOf((int)dayLater1/100);
				j++;
				insert[i+j][3] = String.valueOf((int)dayLater3/100);
				j++;
				insert[i+j][3] = String.valueOf((int)dayLater7/100);
				j=2*(i+1);
				
				float op2 = Integer.parseInt(rs2.getString(1));
				float aux1 = (float) (Math.round(dayLater1/op2*100.0)/100.0);
				float aux3 = (float) (Math.round(dayLater3/op2*100.0)/100.0);
				float aux7 = (float) (Math.round(dayLater7/op2*100.0)/100.0);
				
				values[i][3] = "1 Day later: "+String.valueOf(aux1)+"%<br>"
								+"3 Days later: "+String.valueOf(aux3)+"%<br> "
								+"7 Days later: "+String.valueOf(aux7)+"%";
				
				if (aux7 <= 15.0)
					scores[i][3] = "1";
				else if (aux7 > 15.0 && aux7 <= 30.0)
					scores[i][3] = "2";
				else if (aux7 > 30.0 && aux7 <= 45.0)
					scores[i][3] = "3";
				else if (aux7 > 45.0 && aux7 <= 60.0)
					scores[i][3] = "4";
				else if (aux7 > 60.0)
					scores[i][3] = "5";
				
				colors[i][3] = "color"+scores[i][3];
				total[i][3] = String.valueOf(weighting[3]*Integer.parseInt(scores[i][3]));
				
			}	
		}
	}
	
	public void setRatioUCIPatients() throws SQLException {
		criteria[4] = "Ratio ICU patients with COVID-19 / total ICU patients";
		Statement stmt=this.con.createStatement();
		Statement stmt2=this.con.createStatement();
		
		for(int i = 0; i<names.length;i++) {
			ResultSet rs = stmt.executeQuery("select sum(hs.icu_covid_patients) from hospital_situation hs "
					+ "inner join hospital h on h.hospital_id = hs.hospital_id "
					+ "inner join location l on l.location_id = h.location_id " + "where l.name = '" + names[i]
					+ "' and " + "hs.date_hosp_situation = (select max(hs2.date_hosp_situation)  "
					+ "                            from hospital_situation  hs2 "
					+ "                            where hs.hospital_id = hs2.hospital_id "
					+ "                            group by hs2.hospital_id)"); 
			
			ResultSet rs2=stmt2.executeQuery("select sum(hs.icu_total_patients) from hospital_situation hs "
					+ "inner join hospital h on h.hospital_id = hs.hospital_id "
					+ "inner join location l on l.location_id = h.location_id " + "where l.name = '" + names[i]
					+ "' and " + "hs.date_hosp_situation = (select max(hs2.date_hosp_situation)  "
					+ "                            from hospital_situation  hs2 "
					+ "                            where hs.hospital_id = hs2.hospital_id "
					+ "                            group by hs2.hospital_id)");  
			
			if(rs.next() && rs2.next()) {
				float op1 = Integer.parseInt(rs.getString(1))*100;
				float op2 = Integer.parseInt(rs2.getString(1));
				float aux = (float) (Math.round(op1/op2*100.0)/100.0);
				values[i][4] = String.valueOf(aux)+"%";
				
				if(aux <= 20.0)
					scores[i][4] = "1";
				else if(aux > 20.0 && aux <= 40.0)
					scores[i][4] = "2";
				else if(aux > 40.0 && aux <= 60.0)
					scores[i][4] = "3";
				else if(aux > 60.0 && aux <= 80.0)
					scores[i][4] = "4";
				else if(aux > 80.0)
					scores[i][4] = "5";
				
				colors[i][4] = "color"+scores[i][4];
				total[i][4] = String.valueOf(weighting[4]*Integer.parseInt(scores[i][4]));
			}
		}
	}
	
	public void setHospitalizedTotalPatients() throws SQLException {
		criteria[5] = "Hospitalized total occupancy percentage";
		Statement stmt=this.con.createStatement();
		Statement stmt2=this.con.createStatement();
		
		for(int i = 0; i<names.length;i++) {
			ResultSet rs=stmt.executeQuery("select sum(hs.hosp_total_patients) from hospital_situation hs " + 
					"inner join hospital h on h.hospital_id = hs.hospital_id " + 
					"inner join location l on l.location_id = h.location_id " + 
					"where l.name = '"+names[i]+"' and " + 
					"hs.date_hosp_situation = (select max(hs2.date_hosp_situation)  " + 
					"                            from hospital_situation  hs2 " + 
					"                            where hs.hospital_id = hs2.hospital_id " + 
					"                            group by hs2.hospital_id)");  
			
			ResultSet rs2=stmt2.executeQuery("select sum(h.hosp_size) from hospital h " + 
											"inner join location l on l.location_id = h.location_id " + 
											"where l.name = '"+names[i]+"'");  
			
			if(rs.next() && rs2.next()) {
				float op1 = Integer.parseInt(rs.getString(1))*100;
				float op2 = Integer.parseInt(rs2.getString(1));
				float aux = (float) (Math.round(op1/op2*100.0)/100.0);
				values[i][5] = String.valueOf(aux)+"%";
				
				if(aux <= 20.0)
					scores[i][5] = "1";
				else if(aux > 20.0 && aux <= 40.0)
					scores[i][5] = "2";
				else if(aux > 40.0 && aux <= 60.0)
					scores[i][5] = "3";
				else if(aux > 60.0 && aux <= 80.0)
					scores[i][5] = "4";
				else if(aux > 80.0 && aux <= 100.0)
					scores[i][5] = "5";
				
				colors[i][5] = "color"+scores[i][5];
				total[i][5] = String.valueOf(weighting[5]*Integer.parseInt(scores[i][5]));
			}	
		}
	}
	
	public void setExpectedHospitalizedTotalPatients() throws SQLException {
		criteria[6] = "Expected hospitalized total occupancy percentage 1, 3 and 7 days later";
		Statement stmt=this.con.createStatement();
		Statement stmt2=this.con.createStatement();
		
		for(int i = 0, j=0; i<names.length;i++) {
			ResultSet rs=stmt.executeQuery("select s from( " + 
										"    select hs.date_hosp_situation, sum(hs.hosp_total_patients) s from hospital_situation hs " + 
										"    inner join hospital h on h.hospital_id = hs.hospital_id " + 
										"    inner join location l on l.location_id = h.location_id " + 
										"    where l.name = '"+names[i]+"' " + 
										"    group by hs.date_hosp_situation " + 
										"    order by hs.date_hosp_situation DESC) " + 
										"where rownum<=15");  
			
			ResultSet rs2=stmt2.executeQuery("select sum(h.hosp_size) from hospital h " + 
											"inner join location l on l.location_id = h.location_id " + 
											"where l.name = '"+names[i]+"'");  
			
			if(rs2.next()) {
				double[] y = new double[15];
				int c = 14;
				while(rs.next()) {
					y[c] = Double.parseDouble(rs.getString(1));
					c--;
				}	
				
				lr = new LinearRegression(y);
				
				double dayLater1 = lr.getPredictY(15)*100;
				double dayLater3 = lr.getPredictY(17)*100;
				double dayLater7 = lr.getPredictY(21)*100;
				
				insert[i+j][4] = String.valueOf((int)dayLater1/100);
				j++;
				insert[i+j][4] = String.valueOf((int)dayLater3/100);
				j++;
				insert[i+j][4] = String.valueOf((int)dayLater7/100);
				j=2*(i+1);
				
				float op2 = Integer.parseInt(rs2.getString(1));
				float aux1 = (float) (Math.round(dayLater1/op2*100.0)/100.0);
				float aux3 = (float) (Math.round(dayLater3/op2*100.0)/100.0);
				float aux7 = (float) (Math.round(dayLater7/op2*100.0)/100.0);
				
				values[i][6] = "1 Day later: "+String.valueOf(aux1)+"%<br>"
								+"3 Days later: "+String.valueOf(aux3)+"%<br> "
								+"7 Days later: "+String.valueOf(aux7)+"%";
				
				if(aux7 <= 20.0)
					scores[i][6] = "1";
				else if(aux7 > 20.0 && aux7 <= 40.0)
					scores[i][6] = "2";
				else if(aux7 > 40.0 && aux7 <= 60.0)
					scores[i][6] = "3";
				else if(aux7 > 60.0 && aux7 <= 80.0)
					scores[i][6] = "4";
				else if(aux7 > 80.0 && aux7 <= 100.0)
					scores[i][6] = "5";
				
				colors[i][6] = "color"+scores[i][6];
				total[i][6] = String.valueOf(weighting[6]*Integer.parseInt(scores[i][6]));
				
			}	
		}
	}
	
	public void setHospitalizedCovidPatients() throws SQLException {
		criteria[7] = "Hospitalized COVID-19 occupancy percentage";
		Statement stmt=this.con.createStatement();
		Statement stmt2=this.con.createStatement();
		
		for(int i = 0; i<names.length;i++) {
			ResultSet rs=stmt.executeQuery("select sum(hs.hosp_covid_patients) from hospital_situation hs " + 
					"inner join hospital h on h.hospital_id = hs.hospital_id " + 
					"inner join location l on l.location_id = h.location_id " + 
					"where l.name = '"+names[i]+"' and " + 
					"hs.date_hosp_situation = (select max(hs2.date_hosp_situation)  " + 
					"                            from hospital_situation  hs2 " + 
					"                            where hs.hospital_id = hs2.hospital_id " + 
					"                            group by hs2.hospital_id)");  
			
			ResultSet rs2=stmt2.executeQuery("select sum(h.hosp_size) from hospital h " + 
											"inner join location l on l.location_id = h.location_id " + 
											"where l.name = '"+names[i]+"'");  
			
			if(rs.next() && rs2.next()) {
				float op1 = Integer.parseInt(rs.getString(1))*100;
				float op2 = Integer.parseInt(rs2.getString(1));
				float aux = (float) (Math.round(op1/op2*100.0)/100.0);
				values[i][7] = String.valueOf(aux)+"%";
				
				if(aux <= 15.0)
					scores[i][7] = "1";
				else if(aux > 15.0 && aux <= 30.0)
					scores[i][7] = "2";
				else if(aux > 30.0 && aux <= 45.0)
					scores[i][7] = "3";
				else if(aux > 45.0 && aux <= 60.0)
					scores[i][7] = "4";
				else if(aux > 60.0)
					scores[i][7] = "5";
				
				colors[i][7] = "color"+scores[i][7];
				total[i][7] = String.valueOf(weighting[7]*Integer.parseInt(scores[i][7]));
			}	
		}
	}
	
	public void setExpectedHospitalizedCovidPatients() throws SQLException {
		criteria[8] = "Expected hospitalized COVID-19 occupancy percentage 1, 3 and 7 days later";
		Statement stmt=this.con.createStatement();
		Statement stmt2=this.con.createStatement();
		
		for(int i = 0, j=0; i<names.length;i++) {
			ResultSet rs=stmt.executeQuery("select s from( " + 
										"    select hs.date_hosp_situation, sum(hs.hosp_covid_patients) s from hospital_situation hs " + 
										"    inner join hospital h on h.hospital_id = hs.hospital_id " + 
										"    inner join location l on l.location_id = h.location_id " + 
										"    where l.name = '"+names[i]+"' " + 
										"    group by hs.date_hosp_situation " + 
										"    order by hs.date_hosp_situation DESC) " + 
										"where rownum<=15");  
			
			ResultSet rs2=stmt2.executeQuery("select sum(h.hosp_size) from hospital h " + 
											"inner join location l on l.location_id = h.location_id " + 
											"where l.name = '"+names[i]+"'");  
			
			if(rs2.next()) {
				double[] y = new double[15];
				int c = 14;
				while(rs.next()) {
					y[c] = Double.parseDouble(rs.getString(1));
					c--;
				}	
				
				lr = new LinearRegression(y);
				
				double dayLater1 = lr.getPredictY(15)*100;
				double dayLater3 = lr.getPredictY(17)*100;
				double dayLater7 = lr.getPredictY(21)*100;
				
				insert[i+j][5] = String.valueOf((int)dayLater1/100);
				j++;
				insert[i+j][5] = String.valueOf((int)dayLater3/100);
				j++;
				insert[i+j][5] = String.valueOf((int)dayLater7/100);
				j=2*(i+1);
				
				float op2 = Integer.parseInt(rs2.getString(1));
				float aux1 = (float) (Math.round(dayLater1/op2*100.0)/100.0);
				float aux3 = (float) (Math.round(dayLater3/op2*100.0)/100.0);
				float aux7 = (float) (Math.round(dayLater7/op2*100.0)/100.0);
				
				values[i][8] = "1 Day later: "+String.valueOf(aux1)+"%<br>"
								+"3 Days later: "+String.valueOf(aux3)+"%<br> "
								+"7 Days later: "+String.valueOf(aux7)+"%";
				
				if(aux7 <= 15.0)
					scores[i][8] = "1";
				else if(aux7 > 15.0 && aux7 <= 30.0)
					scores[i][8] = "2";
				else if(aux7 > 30.0 && aux7 <= 45.0)
					scores[i][8] = "3";
				else if(aux7 > 45.0 && aux7 <= 60.0)
					scores[i][8] = "4";
				else if(aux7 > 60.0)
					scores[i][8] = "5";
				
				colors[i][8] = "color"+scores[i][8];
				total[i][8] = String.valueOf(weighting[8]*Integer.parseInt(scores[i][8]));
				
			}	
		}
	}
	
	public void setRatioHospitalizedPatients() throws SQLException {
		criteria[9] = "Ratio hospitalized patients with COVID-19 / total hospitalized patients";
		Statement stmt=this.con.createStatement();
		Statement stmt2=this.con.createStatement();
		
		for(int i = 0; i<names.length;i++) {
			ResultSet rs = stmt.executeQuery("select sum(hs.hosp_covid_patients) from hospital_situation hs "
					+ "inner join hospital h on h.hospital_id = hs.hospital_id "
					+ "inner join location l on l.location_id = h.location_id " + "where l.name = '" + names[i]
					+ "' and " + "hs.date_hosp_situation = (select max(hs2.date_hosp_situation)  "
					+ "                            from hospital_situation  hs2 "
					+ "                            where hs.hospital_id = hs2.hospital_id "
					+ "                            group by hs2.hospital_id)"); 
			
			ResultSet rs2=stmt2.executeQuery("select sum(hs.hosp_total_patients) from hospital_situation hs "
					+ "inner join hospital h on h.hospital_id = hs.hospital_id "
					+ "inner join location l on l.location_id = h.location_id " + "where l.name = '" + names[i]
					+ "' and " + "hs.date_hosp_situation = (select max(hs2.date_hosp_situation)  "
					+ "                            from hospital_situation  hs2 "
					+ "                            where hs.hospital_id = hs2.hospital_id "
					+ "                            group by hs2.hospital_id)");  
			
			if(rs.next() && rs2.next()) {
				float op1 = Integer.parseInt(rs.getString(1))*100;
				float op2 = Integer.parseInt(rs2.getString(1));
				float aux = (float) (Math.round(op1/op2*100.0)/100.0);
				values[i][9] = String.valueOf(aux)+"%";
				
				if(aux <= 20.0)
					scores[i][9] = "1";
				else if(aux > 20.0 && aux <= 40.0)
					scores[i][9] = "2";
				else if(aux > 40.0 && aux <= 60.0)
					scores[i][9] = "3";
				else if(aux > 60.0 && aux <= 80.0)
					scores[i][9] = "4";
				else if(aux > 80.0)
					scores[i][9] = "5";
				
				colors[i][9] = "color"+scores[i][9];
				total[i][9] = String.valueOf(weighting[9]*Integer.parseInt(scores[i][9]));
			}
		}
	}
	
	public void setAmountCOVIDpatients() throws SQLException {
		criteria[10] = "Amount of COVID-19 patients/100.000";
		Statement stmt=this.con.createStatement();
		Statement stmt2=this.con.createStatement();
		
		for(int i = 0; i<names.length;i++) {
			ResultSet rs=stmt.executeQuery("select ls.sick_patients from location_situation ls  " + 
											"inner join location l on l.location_id = ls.location_id  " + 
											"where l.name = '"+names[i]+"'  " + 
											"and ls.date_loc_situation = (select max(ls2.date_loc_situation)   " + 
											"from location_situation  ls2 " + 
											"where ls2.location_id = l.location_id)");  
			
			ResultSet rs2=stmt2.executeQuery("select population from location " + 
											"where name = '"+names[i]+"'");  
			
			
			if(rs.next() && rs2.next()) {
				float op1 = Integer.parseInt(rs.getString(1));
				float op2 = Integer.parseInt(rs2.getString(1));
				
				
				float aux = (float) (Math.round(op1*100000/op2));
				float aux2 = (float) (Math.round(op1*100/op2));
				
				values[i][10] = String.valueOf(aux);
				
				if(aux2 <= 5.0)
					scores[i][10] = "1";
				else if(aux2 > 5.0 && aux2 <= 10.0)
					scores[i][10] = "2";
				else if(aux2 > 10.0 && aux2 <= 15.0)
					scores[i][10] = "3";
				else if(aux2 > 15.0 && aux2 <= 20.0)
					scores[i][10] = "4";
				else if(aux2 > 20.0)
					scores[i][10] = "5";
			
				
				colors[i][10] = "color"+scores[i][10];
				total[i][10] = String.valueOf(weighting[10]*Integer.parseInt(scores[i][10]));
			}	
		}
	}
	
	public void setExpectedAmountCOVIDpatients() throws SQLException {
		criteria[11] = "Expected amount of COVID-19 patients 1, 3 and 7 days later";
		Statement stmt=this.con.createStatement();
		Statement stmt2=this.con.createStatement();
		
		for(int i = 0, j=0; i<names.length;i++) {
			ResultSet rs=stmt.executeQuery("select s from( " + 
										"    select ls.date_loc_situation, ls.sick_patients s from location_situation ls " + 
										"    inner join location l on l.location_id = ls.location_id " + 
										"    where l.name = '"+names[i]+"' " + 
										"    order by ls.date_loc_situation DESC) " + 
										"where rownum<=15");  
			
			ResultSet rs2=stmt2.executeQuery("select population from location " + 
												"where name = '"+names[i]+"'");   
			
			if(rs2.next()) {
				double[] y = new double[15];
				int c = 14;
				while(rs.next()) {
					y[c] = Double.parseDouble(rs.getString(1));
					c--;
				}	
				
				lr = new LinearRegression(y);
				double slope = lr.getSlope();
				double dayLater1 = y[14]+slope*1;
				double dayLater3 = y[14]+slope*3;
				double dayLater7 = y[14]+slope*7;
				
				insert[i+j][6] = String.valueOf((int)dayLater1);
				j++;
				insert[i+j][6] = String.valueOf((int)dayLater3);
				j++;
				insert[i+j][6] = String.valueOf((int)dayLater7);
				j=2*(i+1);
				
				float op2 = Integer.parseInt(rs2.getString(1));
				float aux1 = (float) (Math.round(dayLater1*100000/op2));
				float aux3 = (float) (Math.round(dayLater3*100000/op2));
				float aux7 = (float) (Math.round(dayLater7*100000/op2));
				
				float percentage = (float) (Math.round(dayLater7*100/op2));
				
				values[i][11] = "1 Day later: "+String.valueOf(aux1)+"<br>"
								+"3 Days later: "+String.valueOf(aux3)+"<br> "
								+"7 Days later: "+String.valueOf(aux7)+"";
				
				if(percentage <= 5.0)
					scores[i][11] = "1";
				else if(percentage > 5.0 && percentage <= 10.0)
					scores[i][11] = "2";
				else if(percentage > 10.0 && percentage <= 15.0)
					scores[i][11] = "3";
				else if(percentage > 15.0 && percentage <= 20.0)
					scores[i][11] = "4";
				else if(percentage > 20.0)
					scores[i][11] = "5";
				
				colors[i][11] = "color"+scores[i][11];
				total[i][11] = String.valueOf(weighting[11]*Integer.parseInt(scores[i][11]));
				
			}	
		}
	}
	
	
	public void setAmountRecoveredCOVIDpatients() throws SQLException {
		criteria[12] = "Amount of recovered COVID-19 patients/100.000";
		Statement stmt=this.con.createStatement();
		Statement stmt2=this.con.createStatement();
		
		for(int i = 0; i<names.length;i++) {
			ResultSet rs=stmt.executeQuery("select ls.recovered_patients from location_situation ls " + 
										"    inner join location l on l.location_id = ls.location_id " + 
										"    where l.name = '"+names[i]+"' " + 
										"    and ls.date_loc_situation = (select max(ls2.date_loc_situation)  " + 
										"                                from location_situation  ls2 " + 
										"											where ls2.location_id = l.location_id)");  
			
			ResultSet rs2=stmt2.executeQuery("select population from location " + 
												"where name = '"+names[i]+"'");  
			
			if(rs.next() && rs2.next()) {
				float op1 = Integer.parseInt(rs.getString(1));
				float op2 = Integer.parseInt(rs2.getString(1));
				float aux = (float) (Math.round(op1*100000/op2));
				values[i][12] = String.valueOf(aux);
				
				float percentage = (float) (Math.round(op1*100/op2));
				
				if(percentage <= 80.0)
					scores[i][12] = "1";
				else if(percentage > 80.0 && percentage <= 85.0)
					scores[i][12] = "2";
				else if(percentage > 85.0 && percentage <= 90.0)
					scores[i][12] = "3";
				else if(percentage > 90.0 && percentage <= 95.0)
					scores[i][12] = "4";
				else if(percentage > 95.0)
					scores[i][12] = "5";
				
				colors[i][12] = "color"+scores[i][12];
				total[i][12] = String.valueOf(weighting[12]*Integer.parseInt(scores[i][12]));
			}	
		}
	}
	
	public void setExpectedRecoveredCOVIDpatients() throws SQLException {
		criteria[13] = "Expected recovered COVID-19 patients 1, 3 and 7 days later";
		Statement stmt=this.con.createStatement();
		Statement stmt2=this.con.createStatement();
		
		for(int i = 0, j=0; i<names.length;i++) {
			ResultSet rs=stmt.executeQuery("select s from(\r\n" + 
											"    select ls.date_loc_situation, ls.recovered_patients s from location_situation ls " + 
											"    inner join location l on l.location_id = ls.location_id " + 
											"    where l.name = '"+names[i]+"' " + 
											"    order by ls.date_loc_situation DESC) " + 
											"where rownum<=15");  
			
			ResultSet rs2=stmt2.executeQuery("select population from location " + 
												"where name = '"+names[i]+"'");   
			
			if(rs2.next()) {
				double[] y = new double[15];
				int c = 14;
				while(rs.next()) {
					y[c] = Double.parseDouble(rs.getString(1));
					c--;
				}	
				
				lr = new LinearRegression(y);
				double slope = lr.getSlope();
				double dayLater1 = y[14]+slope*1;
				double dayLater3 = y[14]+slope*3;
				double dayLater7 = y[14]+slope*7;
				
				insert[i+j][7] = String.valueOf((int)dayLater1);
				j++;
				insert[i+j][7] = String.valueOf((int)dayLater3);
				j++;
				insert[i+j][7] = String.valueOf((int)dayLater7);
				j=2*(i+1);
				
				float op2 = Integer.parseInt(rs2.getString(1));
				float aux1 = (float) (Math.round(dayLater1*100000/op2));
				float aux3 = (float) (Math.round(dayLater3*100000/op2));
				float aux7 = (float) (Math.round(dayLater7*100000/op2));
				
				float percentage = (float) (Math.round(dayLater7*100/op2));
				
				values[i][13] = "1 Day later: "+String.valueOf(aux1)+"<br>"
								+"3 Days later: "+String.valueOf(aux3)+"<br> "
								+"7 Days later: "+String.valueOf(aux7)+"";
				
				if(percentage <= 80.0)
					scores[i][13] = "1";
				else if(percentage > 80.0 && percentage <= 85.0)
					scores[i][13] = "2";
				else if(percentage > 85.0 && percentage <= 90.0)
					scores[i][13] = "3";
				else if(percentage > 90.0 && percentage <= 95.0)
					scores[i][13] = "4";
				else if(percentage > 95.0)
					scores[i][13] = "5";
				
				colors[i][13] = "color"+scores[i][13];
				total[i][13] = String.valueOf(weighting[13]*Integer.parseInt(scores[i][13]));
				
			}	
		}
	}
	
	public void setRatioRecoveredPatients() throws SQLException {
		criteria[14] = "Ratio of recovered COVID-19 patients / overall COVID-19 patients";
		Statement stmt=this.con.createStatement();
		Statement stmt2=this.con.createStatement();
		
		for(int i = 0; i<names.length;i++) {
			ResultSet rs=stmt.executeQuery("select ls.recovered_patients from location_situation ls " + 
										"    inner join location l on l.location_id = ls.location_id " + 
										"    where l.name = '"+names[i]+"' " + 
										"    and ls.date_loc_situation = (select max(ls2.date_loc_situation)  " + 
										"									from location_situation  ls2 " + 
										"									where ls2.location_id = l.location_id)");   
			
			ResultSet rs2=stmt2.executeQuery("select ls.sick_patients from location_situation ls " + 
										"    inner join location l on l.location_id = ls.location_id " + 
										"    where l.name = '"+names[i]+"' " + 
										"    and ls.date_loc_situation = (select max(ls2.date_loc_situation)  " + 
										"								from location_situation  ls2 " + 
										"								where ls2.location_id = l.location_id)");   
			
			if(rs.next() && rs2.next()) {
				float op1 = Integer.parseInt(rs.getString(1));
				float op2 = Integer.parseInt(rs2.getString(1));
				float aux = (float) (Math.round(op1*100/op2));
				values[i][14] = String.valueOf((int)aux+"%");
				
				
				if(aux <= 80.0)
					scores[i][14] = "5";
				else if(aux > 80.0 && aux <= 85.0)
					scores[i][14] = "4";
				else if(aux > 85.0 && aux <= 90.0)
					scores[i][14] = "3";
				else if(aux > 90.0 && aux <= 95.0)
					scores[i][14] = "2";
				else if(aux > 95.0)
					scores[i][14] = "1";
				
				colors[i][14] = "color"+scores[i][14];
				total[i][14] = String.valueOf(weighting[14]*Integer.parseInt(scores[i][14]));
			}	
		}
	}
	
	public void setAmountDeadCOVIDpatients() throws SQLException {
		criteria[15] = "Amount of dead COVID-19 patients/100.000";
		Statement stmt=this.con.createStatement();
		Statement stmt2=this.con.createStatement();
		
		for(int i = 0; i<names.length;i++) {
			ResultSet rs=stmt.executeQuery("select ls.deaths from location_situation ls " + 
										"    inner join location l on l.location_id = ls.location_id " + 
										"    where l.name = '"+names[i]+"' " + 
										"    and ls.date_loc_situation = (select max(ls2.date_loc_situation) " + 
										"									from location_situation  ls2 " + 
										"									where ls2.location_id = l.location_id)");   
			
			ResultSet rs2=stmt2.executeQuery("select population from location " + 
												"where name = '"+names[i]+"'");  
			
			if(rs.next() && rs2.next()) {
				float op1 = Integer.parseInt(rs.getString(1));
				float op2 = Integer.parseInt(rs2.getString(1));
				float aux = (float) (Math.round(op1*100000/op2));
				values[i][15] = String.valueOf(aux);
				
				float percentage = (float) (Math.round(op1*100/op2));
				
				if(percentage <= 0.1)
					scores[i][15] = "1";
				else if(percentage > 0.1  && percentage <= 0.5)
					scores[i][15] = "2";
				else if(percentage > 0.5 && percentage <= 1.0)
					scores[i][15] = "3";
				else if(percentage > 1.5 && percentage <= 2.0)
					scores[i][15] = "4";
				else if(percentage > 2.0)
					scores[i][15] = "5";
				
				colors[i][15] = "color"+scores[i][15];
				total[i][15] = String.valueOf(weighting[15]*Integer.parseInt(scores[i][15]));
			}	
		}
	}
	
	public void setExpectedDeadCOVIDpatients() throws SQLException {
		criteria[16] = "Expected dead COVID-19 patients 1, 3 and 7 days later";
		Statement stmt=this.con.createStatement();
		Statement stmt2=this.con.createStatement();
		
		for(int i = 0, j=0; i<names.length;i++) {
			ResultSet rs=stmt.executeQuery("select s from( " + 
										"    select ls.date_loc_situation, ls.deaths s from location_situation ls " + 
										"    inner join location l on l.location_id = ls.location_id " + 
										"    where l.name = '"+names[i]+"' " + 
										"    order by ls.date_loc_situation DESC) " + 
										"where rownum<=15");  
			
			ResultSet rs2=stmt2.executeQuery("select population from location " + 
												"where name = '"+names[i]+"'");   
			
			if(rs2.next()) {
				double[] y = new double[15];
				int c = 14;
				while(rs.next()) {
					y[c] = Double.parseDouble(rs.getString(1));
					c--;
				}	
				
				lr = new LinearRegression(y);
				double slope = lr.getSlope();
				double dayLater1 = y[14]+slope*1;
				double dayLater3 = y[14]+slope*3;
				double dayLater7 = y[14]+slope*7;
				
				insert[i+j][8] = String.valueOf((int)dayLater1);
				j++;
				insert[i+j][8] = String.valueOf((int)dayLater3);
				j++;
				insert[i+j][8] = String.valueOf((int)dayLater7);
				j=2*(i+1);
				
				float op2 = Integer.parseInt(rs2.getString(1));
				float aux1 = (float) (Math.round(dayLater1*100000/op2));
				float aux3 = (float) (Math.round(dayLater3*100000/op2));
				float aux7 = (float) (Math.round(dayLater7*100000/op2));
				
				float percentage = (float) (Math.round(dayLater7*100/op2));
				
				values[i][16] = "1 Day later: "+String.valueOf(aux1)+"<br>"
								+"3 Days later: "+String.valueOf(aux3)+"<br> "
								+"7 Days later: "+String.valueOf(aux7)+"";
				
				if(percentage <= 0.1)
					scores[i][16] = "1";
				else if(percentage > 0.1  && percentage <= 0.5)
					scores[i][16] = "2";
				else if(percentage > 0.5 && percentage <= 1.0)
					scores[i][16] = "3";
				else if(percentage > 1.5 && percentage <= 2.0)
					scores[i][16] = "4";
				else if(percentage > 2.0)
					scores[i][16] = "5";
				
				colors[i][16] = "color"+scores[i][16];
				total[i][16] = String.valueOf(weighting[16]*Integer.parseInt(scores[i][16]));
				
			}	
		}
	}
	
	public void setRatioRecoveredPatientsDeadPatients() throws SQLException {
		criteria[17] = "Ratio of dead patients / recovered COVID-19 patients";
		Statement stmt=this.con.createStatement();
		Statement stmt2=this.con.createStatement();
		
		for(int i = 0; i<names.length;i++) {
			ResultSet rs=stmt.executeQuery("select ls.deaths from location_situation ls " + 
										"    inner join location l on l.location_id = ls.location_id " + 
										"    where l.name = '"+names[i]+"' " + 
										"    and ls.date_loc_situation = (select max(ls2.date_loc_situation) " + 
										"								from location_situation  ls2 " + 
										"								where ls2.location_id = l.location_id)");   
			
			ResultSet rs2=stmt2.executeQuery("select ls.recovered_patients from location_situation ls " + 
										"    inner join location l on l.location_id = ls.location_id " + 
										"    where l.name = '"+names[i]+"' " + 
										"    and ls.date_loc_situation = (select max(ls2.date_loc_situation)  " + 
										"								from location_situation  ls2 " + 
										"								where ls2.location_id = l.location_id)");     
			
			if(rs.next() && rs2.next()) {
				float op1 = Integer.parseInt(rs.getString(1));
				float op2 = Integer.parseInt(rs2.getString(1));
				float aux = (float) (Math.round(op1*100/op2));
				values[i][17] = String.valueOf(aux+"%");
				
				
				if(aux <= 1.0)
					scores[i][17] = "1";
				else if(aux > 1.0 && aux <= 2.0)
					scores[i][17] = "2";
				else if(aux > 2.0 && aux <= 3.0)
					scores[i][17] = "3";
				else if(aux > 3.0 && aux <= 4.0)
					scores[i][17] = "4";
				else if(aux > 4.0)
					scores[i][17] = "5";
				
				
				colors[i][17] = "color"+scores[i][17];
				total[i][17] = String.valueOf(weighting[17]*Integer.parseInt(scores[i][17]));
			}	
		}
	}
	
	public void setAverageAge() throws SQLException {
		criteria[18] = "Average age";
		Statement stmt=this.con.createStatement();
		
		for(int i = 0; i<names.length;i++) {
			ResultSet rs=stmt.executeQuery("select l.average_age from location l " + 
											"where l.name = '"+names[i]+"'");  
			
			
			if(rs.next()) {
				float op1 = Float.parseFloat(rs.getString(1));
				
				values[i][18] = String.valueOf(op1);
				
				if(op1 <= 40.0)
					scores[i][18] = "1";
				else if(op1 > 40.0 && op1 <= 50.0)
					scores[i][18] = "2";
				else if(op1 > 50.0 && op1 <= 60.0)
					scores[i][18] = "3";
				else if(op1 > 60.0 && op1 <= 70.0)
					scores[i][18] = "4";
				else if(op1 > 70.0)
					scores[i][18] = "5";
			
				
				colors[i][18] = "color"+scores[i][18];
				total[i][18] = String.valueOf(weighting[18]*Integer.parseInt(scores[i][18]));
			}	
		}
	}
	
	public void setAverageAgeSickPatients() throws SQLException {
		criteria[19] = "Average age sick patients";
		Statement stmt=this.con.createStatement();
		
		for(int i = 0; i<names.length;i++) {
			ResultSet rs=stmt.executeQuery("select ls.average_sick_age from location_situation ls " + 
											"    inner join location l on l.location_id = ls.location_id " + 
											"    where l.name = '"+names[i]+"' " + 
											"    and ls.date_loc_situation = (select max(ls2.date_loc_situation) " + 
											"								from location_situation  ls2 " + 
											"								where ls2.location_id = l.location_id)");    
			
			
			if(rs.next()) {
				float op1 = Float.parseFloat(rs.getString(1));
				
				values[i][19] = String.valueOf(op1);
				
				if(op1 <= 40.0)
					scores[i][19] = "1";
				else if(op1 > 40.0 && op1 <= 50.0)
					scores[i][19] = "2";
				else if(op1 > 50.0 && op1 <= 60.0)
					scores[i][19] = "3";
				else if(op1 > 60.0 && op1 <= 70.0)
					scores[i][19] = "4";
				else if(op1 > 70.0)
					scores[i][19] = "5";
			
				
				colors[i][19] = "color"+scores[i][19];
				total[i][19] = String.valueOf(weighting[19]*Integer.parseInt(scores[i][19]));
			}	
		}
	}
	
	
	public void setMedicalPersonnel() throws SQLException {
		criteria[20] = "Actual medical personnel / 100 patients";
		Statement stmt=this.con.createStatement();
		Statement stmt2=this.con.createStatement();
		
		for(int i = 0; i<names.length;i++) {
			ResultSet rs=stmt.executeQuery("select sum(h.size_shift) from hospital h " + 
										"    inner join location l on l.location_id = h.location_id " + 
										"    where l.name = '"+names[i]+"'");  
			
			ResultSet rs2=stmt2.executeQuery("select sum(hs.hosp_total_patients) from hospital_situation hs " + 
											"inner join hospital h on h.hospital_id = hs.hospital_id " + 
											"inner join location l on l.location_id = h.location_id " + 
											"where l.name = '"+names[i]+"' and " + 
											"hs.date_hosp_situation = (select max(hs2.date_hosp_situation)  " + 
											"                            from hospital_situation  hs2 " + 
											"                            where hs.hospital_id = hs2.hospital_id " + 
											"                            group by hs2.hospital_id)");  
			
			if(rs.next() && rs2.next()) {
				float op1 = Integer.parseInt(rs.getString(1));
				float op2 = Integer.parseInt(rs2.getString(1));
				float aux = (float) (Math.round(op1*100/op2));
				values[i][20] = String.valueOf(aux);
				
				if(aux <= 20)
					scores[i][20] = "5";
				else if(aux > 20  && aux <= 40)
					scores[i][20] = "4";
				else if(aux > 40 && aux <= 60)
					scores[i][20] = "3";
				else if(aux > 60 && aux <= 80)
					scores[i][20] = "2";
				else if(aux > 80)
					scores[i][20] = "1";
				
				colors[i][20] = "color"+scores[i][20];
				total[i][20] = String.valueOf(weighting[20]*Integer.parseInt(scores[i][20]));
			}	
		}
	}
	
	public void setExpectedMedicalPersonnel() throws SQLException {
		criteria[21] = "Expected medical personnel needed 1, 3 and 7 days later";
		Statement stmt=this.con.createStatement();
		Statement stmt2=this.con.createStatement();
		Statement stmt3=this.con.createStatement();
		
		for(int i = 0, j=0; i<names.length;i++) {
			ResultSet rs=stmt.executeQuery("select s from( " + 
										"    select hs.date_hosp_situation, sum(hs.hosp_total_patients) s from hospital_situation hs " + 
										"    inner join hospital h on h.hospital_id = hs.hospital_id " + 
										"    inner join location l on l.location_id = h.location_id " + 
										"    where l.name = '"+names[i]+"' " + 
										"    group by hs.date_hosp_situation " + 
										"    order by hs.date_hosp_situation DESC) " + 
										"where rownum<=15");  
			
			ResultSet rs2=stmt2.executeQuery("select sum(h.hosp_size) from hospital h " + 
											"inner join location l on l.location_id = h.location_id " + 
											"where l.name = '"+names[i]+"'");  
			
			ResultSet rs3=stmt3.executeQuery("select sum(h.size_shift) from hospital h " + 
					"    inner join location l on l.location_id = h.location_id " + 
					"    where l.name = '"+names[i]+"'");
			
			if(rs2.next() && rs3.next()) {
				double[] y = new double[15];
				int c = 14;
				while(rs.next()) {
					y[c] = Double.parseDouble(rs.getString(1));
					c--;
				}	
				
				lr = new LinearRegression(y);
				
				double dayLater1 = lr.getPredictY(15);
				double dayLater3 = lr.getPredictY(17);
				double dayLater7 = lr.getPredictY(21);
				
				insert[i+j][9] = String.valueOf((int)dayLater1);
				j++;
				insert[i+j][9] = String.valueOf((int)dayLater3);
				j++;
				insert[i+j][9] = String.valueOf((int)dayLater7);
				j=2*(i+1);
				
				float op2 = Integer.parseInt(rs2.getString(1));
				float aux1 = (float) (Math.round(dayLater1/op2*100.0)/100.0);
				float aux3 = (float) (Math.round(dayLater3/op2*100.0)/100.0);
				float aux7 = (float) (Math.round(dayLater7/op2*100.0)/100.0);
				
				int op3 = Integer.parseInt(rs3.getString(1));
				float p1 = (float) (Math.round(op3*100/dayLater1));
				float p3 = (float) (Math.round(op3*100/dayLater3));
				float p7 = (float) (Math.round(op3*100/dayLater7));

				if(aux1 > 75.0) {
					p1 += (int)(aux1 - 75)/5;
				}
				if(aux3 > 75.0) {
					p3 += (int)(aux3 - 75)/5;
				}
				if(aux7 > 75.0) {
					p7 += (int)(aux7 - 75)/5;
				}
				
				values[i][21] = "1 Day later: "+String.valueOf(p1)+"<br>"
								+"3 Days later: "+String.valueOf(p3)+"<br> "
								+"7 Days later: "+String.valueOf(p7)+"";
				
				if((aux7 - 75)/5 <= 2.5)
					scores[i][21] = "1";
				else if((aux7 - 75)/5 > 2.5 && (aux7 - 75)/5 <= 5.0)
					scores[i][21] = "2";
				else if((aux7 - 75)/5 > 5.0 && (aux7 - 75)/5 <= 7.5)
					scores[i][21] = "3";
				else if((aux7 - 75)/5 > 7.5 && (aux7 - 75)/5 <= 10.0)
					scores[i][21] = "4";
				else if((aux7 - 75)/5 > 10.0)
					scores[i][21] = "5";
				
				colors[i][21] = "color"+scores[i][21];
				total[i][21] = String.valueOf(weighting[21]*Integer.parseInt(scores[i][21]));
				
			}	
		}
	}
	
	public void setReserveOfMedicalSupplies() throws SQLException{
		criteria[22] = "Reserve of medical supplies";
		Statement stmt=this.con.createStatement();
		Statement stmt2=this.con.createStatement();
		
		for(int i = 0; i<names.length;i++) {
			ResultSet rs = stmt.executeQuery("select sum(hs.actual_equipment) from hospital_situation hs "
					+ "inner join hospital h on h.hospital_id = hs.hospital_id "
					+ "inner join location l on l.location_id = h.location_id " + "where l.name = '" + names[i]
					+ "' and " + "hs.date_hosp_situation = (select max(hs2.date_hosp_situation)  "
					+ "                            from hospital_situation  hs2 "
					+ "                            where hs.hospital_id = hs2.hospital_id "
					+ "                            group by hs2.hospital_id)"); 
			
			ResultSet rs2=stmt2.executeQuery("select sum(h.equipment_limit) from hospital h "
					+ "inner join location l on l.location_id = h.location_id "
					+ "where l.name = '" + names[i] + "'");
				
			
			if(rs.next() && rs2.next()) {
				float op1 = Integer.parseInt(rs.getString(1));
				float op2 = Integer.parseInt(rs2.getString(1));
				float aux = (float) (Math.round(op1*100.0/op2));
				values[i][22] = String.valueOf(aux)+"%";
				
				if(aux <= 20.0)
					scores[i][22] = "5";
				else if(aux > 20.0 && aux <= 40.0)
					scores[i][22] = "4";
				else if(aux > 40.0 && aux <= 60.0)
					scores[i][22] = "3";
				else if(aux > 60.0 && aux <= 80.0)
					scores[i][22] = "2";
				else if(aux > 80.0)
					scores[i][22] = "1";
				
				colors[i][22] = "color"+scores[i][22];
				total[i][22] = String.valueOf(weighting[22]*Integer.parseInt(scores[i][22]));
			}
		}
	}
	
	public void setDailyMaterialConsumption() throws SQLException{
		criteria[23] = "Daily material consumption";
		Statement stmt=this.con.createStatement();
		Statement stmt2=this.con.createStatement();
		
		for(int i = 0, j=0; i<names.length;i++) {
			ResultSet rs=stmt.executeQuery("select s from( " + 
					"    select hs.date_hosp_situation, sum(hs.actual_equipment) s from hospital_situation hs " + 
					"    inner join hospital h on h.hospital_id = hs.hospital_id " + 
					"    inner join location l on l.location_id = h.location_id " + 
					"    where l.name = '"+names[i]+"' " + 
					"    group by hs.date_hosp_situation " + 
					"    order by hs.date_hosp_situation DESC) " + 
					"where rownum<=15");  
			
			ResultSet rs2=stmt2.executeQuery("select sum(h.equipment_limit) from hospital h "
					+ "inner join location l on l.location_id = h.location_id "
					+ "where l.name = '" + names[i] + "'");   
			
			if(rs2.next()) {
				double[] y = new double[15];
				float avg = 0;
				int c = 14;
				while(rs.next()) {
					y[c] = Double.parseDouble(rs.getString(1));
					c--;
				}	

				int count=0;
				for(int z=y.length-1;z>0;z--) {
					if(y[z]<y[z-1]) {
						avg = avg + (int)(y[z-1]-y[z]);
						count++;
					}
				}
				avg = avg/count;
				
				insert[i+j][10] = String.valueOf((int)avg);
				j++;
				insert[i+j][10] = String.valueOf((int)avg);
				j++;
				insert[i+j][10] = String.valueOf((int)avg);
				j=2*(i+1);
				
				float op2 = Integer.parseInt(rs2.getString(1));
				
				float percentage = (float) (Math.round(avg*100.0/op2));
				
				values[i][23] = String.valueOf((int)avg);
				
				if(percentage <= 5.0)
					scores[i][23] = "1";
				else if(percentage > 5.0 && percentage <= 10.0)
					scores[i][23] = "2";
				else if(percentage > 10.0 && percentage <= 15.0)
					scores[i][23] = "3";
				else if(percentage > 15.0 && percentage <= 20.0)
					scores[i][23] = "4";
				else if(percentage > 20.0)
					scores[i][23] = "5";
				
				colors[i][23] = "color"+scores[i][23];
				total[i][23] = String.valueOf(weighting[23]*Integer.parseInt(scores[i][23]));
				
			}	
		}
	}
	
	public void setLockDownLevel() throws SQLException {
		criteria[24] = "Lockdown Level";
		Statement stmt=this.con.createStatement();
		
		for(int i = 0; i<names.length;i++) {
			ResultSet rs=stmt.executeQuery("select ls.lockdown_level from location_situation ls " + 
											"    inner join location l on l.location_id = ls.location_id " + 
											"    where l.name = '"+names[i]+"' " + 
											"    and ls.date_loc_situation = (select max(ls2.date_loc_situation) " + 
											"								from location_situation  ls2 " + 
											"								where ls2.location_id = l.location_id)");   
			
			
			if(rs.next()) {
				float op1 = Integer.parseInt(rs.getString(1));
				values[i][24] = String.valueOf(op1);
				
				if(op1 <= 2)
					scores[i][24] = "1";
				else if(op1 > 2  && op1 <= 4)
					scores[i][24] = "2";
				else if(op1 > 4 && op1 <= 6)
					scores[i][24] = "3";
				else if(op1 > 6 && op1 <= 8)
					scores[i][24] = "4";
				else if(op1 > 8)
					scores[i][24] = "5";
				
				colors[i][24] = "color"+scores[i][24];
				total[i][24] = String.valueOf(weighting[24]*Integer.parseInt(scores[i][24]));
			}	
		}
	}
	
	public void setTransportLevel() throws SQLException {
		criteria[25] = "Transport Level";
		Statement stmt=this.con.createStatement();
		
		for(int i = 0; i<names.length;i++) {
			ResultSet rs=stmt.executeQuery("select ls.transport_level from location_situation ls " + 
										"    inner join location l on l.location_id = ls.location_id " + 
										"    where l.name = '"+names[i]+"' " + 
										"    and ls.date_loc_situation = (select max(ls2.date_loc_situation)  " + 
										"									from location_situation  ls2 " + 
										"									where ls2.location_id = l.location_id)");  
			
			
			if(rs.next()) {
				float op1 = Integer.parseInt(rs.getString(1));
				values[i][25] = String.valueOf(op1);
				
				if(op1 <= 2)
					scores[i][25] = "5";
				else if(op1 > 2  && op1 <= 4)
					scores[i][25] = "4";
				else if(op1 > 4 && op1 <= 6)
					scores[i][25] = "3";
				else if(op1 > 6 && op1 <= 8)
					scores[i][25] = "2";
				else if(op1 > 8)
					scores[i][25] = "1";
				
				colors[i][25] = "color"+scores[i][25];
				total[i][25] = String.valueOf(weighting[23]*Integer.parseInt(scores[i][25]));
			}	
		}
	}
	
	public void setWeatherType() throws SQLException {
		criteria[26] = "Weather Level";
		Statement stmt=this.con.createStatement();
		
		for(int i = 0; i<names.length;i++) {
			ResultSet rs=stmt.executeQuery("select ls.weather from location_situation ls " + 
										"    inner join location l on l.location_id = ls.location_id " + 
										"    where l.name = '"+names[i]+"' " + 
										"    and ls.date_loc_situation = (select max(ls2.date_loc_situation) " + 
										"								from location_situation  ls2 " + 
										"								where ls2.location_id = l.location_id)");   
			
			
			if(rs.next()) {
				float op1 = Integer.parseInt(rs.getString(1));
				values[i][26] = String.valueOf(op1);
				
				if(op1 <= 2)
					scores[i][26] = "5";
				else if(op1 > 2  && op1 <= 4)
					scores[i][26] = "4";
				else if(op1 > 4 && op1 <= 6)
					scores[i][26] = "3";
				else if(op1 > 6 && op1 <= 8)
					scores[i][26] = "2";
				else if(op1 > 8)
					scores[i][26] = "1";
				
				colors[i][26] = "color"+scores[i][26];
				total[i][26] = String.valueOf(weighting[26]*Integer.parseInt(scores[i][26]));
			}	
		}
	}
	
	public void setVitalCompanies() throws SQLException {
		criteria[27] = "Vital companies / 1.000 hospitalized patients";
		Statement stmt=this.con.createStatement();
		Statement stmt2=this.con.createStatement();
		
		for(int i = 0; i<names.length;i++) {
			ResultSet rs=stmt.executeQuery("select ls.vital_companies from location_situation ls " + 
										"    inner join location l on l.location_id = ls.location_id " + 
										"    where l.name = '"+names[i]+"' " + 
										"    and ls.date_loc_situation = (select max(ls2.date_loc_situation) " + 
										"									from location_situation  ls2 " + 
										"									where ls2.location_id = l.location_id)");    
			ResultSet rs2=stmt2.executeQuery("select sum(hs.hosp_total_patients) from hospital_situation hs " + 
					"inner join hospital h on h.hospital_id = hs.hospital_id " + 
					"inner join location l on l.location_id = h.location_id " + 
					"where l.name = '"+names[i]+"' and " + 
					"hs.date_hosp_situation = (select max(hs2.date_hosp_situation)  " + 
					"                            from hospital_situation  hs2 " + 
					"                            where hs.hospital_id = hs2.hospital_id " + 
					"                            group by hs2.hospital_id)");
			
			if(rs.next() && rs2.next()) {
				float op1 = Integer.parseInt(rs.getString(1));
				float op2 = Integer.parseInt(rs2.getString(1));
				float aux = Math.round(op1*100000/op2)/(float)100.0;
				values[i][27] = String.valueOf(aux);
				
				if(aux <= 2)
					scores[i][27] = "5";
				else if(aux > 2  && aux <= 4)
					scores[i][27] = "4";
				else if(aux > 4 && aux <= 6)
					scores[i][27] = "3";
				else if(aux > 6 && aux <= 8)
					scores[i][27] = "2";
				else if(aux > 8)
					scores[i][27] = "1";
				
				colors[i][27] = "color"+scores[i][27];
				total[i][27] = String.valueOf(weighting[27]*Integer.parseInt(scores[i][27]));
			}	
		}
	}
	
	public void setSumOfImportance() throws SQLException{
		criteria[28] = "Total importance";
		
		for(int i = 0, sum =0; i<names.length;i++) {  
			  for(int j=0; j<criteria.length-1 ;j++ ) {
				  sum += Integer.parseInt(total[i][j]);
			  }
			  
			  total[i][28] = String.valueOf(sum);
			  if(sum<88)
				  colors[i][28] = "color1";
			  else if(sum>=88 && sum<176)
				  colors[i][28] = "color2";
			  else if(sum>=176 && sum<264)
				  colors[i][28] = "color3";
			  else if(sum>=264 && sum<352)
				  colors[i][28] = "color4";
			  else if(sum>=352 && sum<440)
				  colors[i][28] = "color5";
			  
			  sum=0;
		  }
	}
	
	public void insertPrediction() throws SQLException{
		Statement stmt=this.con.createStatement();
		
		for(int i=0, j=0; i<insert.length; i++) {
			insert[i][0] = String.valueOf(i+1);
			if(i%3==0) {
				insert[i][1] = "date '2021-04-28'";
				j++;
			}
			else if(i%3==1) {
				insert[i][1] = "date '2021-04-30'";
			}
			else if(i%3==2) {
				insert[i][1] = "date '2021-05-04'";
			}
			insert[i][11] = String.valueOf(j);
		}
		
		try {
		
			String[] in = new String[insert.length];
			for(int i = 0; i<insert.length;i++) {
				in[i] = "insert into prediction (prediction_id, date_prediction, e_icu_total, e_icu_covid, e_hosp_total"
						+ ", e_hosp_covid, e_new_sick, e_recovered, e_deaths, personnel_needed, equipment_needed, location_id)"
						+ " values ('"+insert[i][0]+"', "+insert[i][1]+", '"+insert[i][2]+"', '"+insert[i][3]+"', '"+insert[i][4]+"', '"
						+insert[i][5]+"', '"+insert[i][6]+"', '"+insert[i][7]+"', '"+insert[i][8]+"', '"+insert[i][9]+"', '"
						+insert[i][10]+"', '"+insert[i][11]+"')";
				
				stmt.executeQuery(in[i]);
			}
		}catch(Exception e){
			
		}
		
		
	}
	
	public void insertLocation(String[] data) throws SQLException{
		Statement stmt=this.con.createStatement();
		Statement stmt2=this.con.createStatement();
		
		ResultSet rs2=stmt2.executeQuery("select location_id from location " + 
										"where rownum = 1 " + 
										"order by location_id desc");
		int id=0;
		if(rs2.next()) {
			id = rs2.getInt(1)+1;
		}
		
		
		String in = "INSERT INTO location ( location_id, name, population, average_age) VALUES ("+id+",'"+data[0]+"',"+data[1]+","+data[2]+")";
		stmt.executeQuery(in);
		
	}
	
	public void insertLocationSituation(String[] data) throws SQLException{
		Statement stmt=this.con.createStatement();
		Statement stmt2=this.con.createStatement();
		Statement stmt3=this.con.createStatement();
		
		ResultSet rs2=stmt2.executeQuery("select loc_sit_id from location_situation " + 
										"where rownum = 1 " + 
										"order by loc_sit_id desc");
		ResultSet rs3=stmt3.executeQuery("select location_id from location l " + 
										"where l.name = '"+data[9]+"'");
		
		int id=0;
		int location = 0;
		if(rs2.next() && rs3.next()) {
			id = rs2.getInt(1)+1;
			location = rs3.getInt(1);
		}
		
		
		String in = "INSERT INTO location_situation ( loc_sit_id, date_loc_situation, sick_patients, recovered_patients, deaths, average_sick_age, lockdown_level, weather, transport_level, vital_companies, location_id) " + 
					"VALUES ( "+id+", date '"+data[0]+"', '"+data[1]+"', '"+data[2]+"', '"+data[3]+"', '"+data[4]+"', '"+data[5]+"', '"+data[6]+"', '"+data[7]+"', '"+data[8]+"', '"+location+"')";
		stmt.executeQuery(in);
		
	}
	
	public void insertHospital(String[] data) throws SQLException{
		Statement stmt=this.con.createStatement();
		Statement stmt2=this.con.createStatement();
		Statement stmt3=this.con.createStatement();
		
		ResultSet rs2=stmt2.executeQuery("select hospital_id from hospital " + 
										"where rownum = 1 " + 
										"order by hospital_id desc ");
		ResultSet rs3=stmt3.executeQuery("select location_id from location l " + 
										"where l.name = '"+data[5]+"'");
		
		int id=0;
		int location = 0;
		if(rs2.next() && rs3.next()) {
			id = rs2.getInt(1)+1;
			location = rs3.getInt(1);
		}
		
		
		String in = "INSERT INTO hospital ( hospital_id, name, size_shift, icu_size, hosp_size, equipment_limit, location_id) " + 
					"VALUES ( "+id+", '"+data[0]+"', '"+data[1]+"', '"+data[2]+"', '"+data[3]+"', '"+data[4]+"', '"+location+"')";
		stmt.executeQuery(in);
		
	}
	
	public void insertHospitalSituation(String[] data) throws SQLException{
		Statement stmt=this.con.createStatement();
		Statement stmt2=this.con.createStatement();
		Statement stmt3=this.con.createStatement();
		
		ResultSet rs2=stmt2.executeQuery("select hosp_sit_id from hospital_situation " + 
											"where rownum = 1 " + 
											"order by hosp_sit_id desc");
		ResultSet rs3=stmt3.executeQuery("select hospital_id from hospital " + 
											"where name = '"+data[6]+"'");
		
		int id=0;
		int location = 0;
		if(rs2.next() && rs3.next()) {
			id = rs2.getInt(1)+1;
			location = rs3.getInt(1);
		}
		
		
		String in = "INSERT INTO hospital_situation ( hosp_sit_id, date_hosp_situation, icu_covid_patients, icu_total_patients, hosp_covid_patients, hosp_total_patients, actual_equipment, hospital_id)\r\n" + 
					"VALUES ( "+id+", date '"+data[0]+"', '"+data[1]+"', '"+data[2]+"', '"+data[3]+"', '"+data[4]+"', '"+data[5]+"', '"+location+"')";
		stmt.executeQuery(in);
		
	}
	
}
