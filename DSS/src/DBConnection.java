import java.sql.*;  

public class DBConnection {
	
	Connection con;
	
	DBConnection(){
		try{  
			//step1 load the driver class  
			Class.forName("oracle.jdbc.driver.OracleDriver");  
			  
			//step2 create  the connection object  
			con=DriverManager.getConnection( "jdbc:oracle:thin:@localhost:1521:orcl","DSS","DSS1234");   
			  
			}catch(Exception e){ System.out.println(e);}  
	}
	
	public Connection getConnection() {
		return this.con;
	}
}
