import java.sql.*;  

public class DBConnection {
	
	Connection con;
	
	DBConnection(String databaseUsername, String databasePassword) throws  SQLException{
  
			//step1 load the driver class  
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
			  
			//step2 create  the connection object  
			con=DriverManager.getConnection( "jdbc:oracle:thin:@localhost:1521:orcl",databaseUsername,databasePassword);   
			  
	}
	
	public Connection getConnection() {
		return this.con;
	}
}
