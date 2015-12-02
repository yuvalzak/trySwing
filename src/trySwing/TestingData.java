package trySwing;


import java.awt.List;
import java.sql.*;


/**
 * 
 * @author www.luv2code.com
 *
 */
public   class TestingData {

	 
	public String getData() throws SQLException {

		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		 StringBuilder sb = new StringBuilder();
		 
		
		
		try {
			// 1. Get a connection to database
			myConn = DriverManager.getConnection ( "jdbc:mysql://localhost:3306/demo",  "student" , "student");
			
			System.out.println("Database connection successful!\n");
			
			// 2. Create a statement
			myStmt = myConn.createStatement();
			
			// 3. Execute SQL query
			myRs = myStmt.executeQuery("select * from employees");
			
			// 4. Process the result set
			while (myRs.next()) {
			//	System.out.println(myRs.getString("last_name") + ", " + myRs.getString("first_name"));
			  sb.append("\n"  + myRs.getString("last_name") + ", " + myRs.getString("first_name"));
			}
			
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
		finally {
			if (myRs != null) {
				myRs.close();
			}
			
			if (myStmt != null) {
				myStmt.close();
			}
			
			if (myConn != null) {
				myConn.close();
			}
			
			
		}
		return sb.toString();
	}

}
