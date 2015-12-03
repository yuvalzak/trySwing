package trySwing;

import java.sql.*;

//* inspired by : www.luv2code.com
public class TestingData {

	public int LoginUser(String name, String password) {
		Connection myConn = null;
		ResultSet myRs = null  ;
		
		int userId = -1;

		String sql;
		sql = "Select userId , password from demo.users  where `userName` = ?  ";

		try {
			myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "student", "student");

			PreparedStatement preparedStmt = myConn.prepareStatement(sql);
			preparedStmt.setString(1, name);
		//	preparedStmt.setString(2, password );
			 
			// preparedStmt.execute();
			  myRs = preparedStmt.executeQuery(sql);
			while (myRs.next()) {
				if (password.equals(myRs.getString("password"))) {
				userId = myRs.getInt("userId");
				
				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		finally {

			try {
				this.close(myConn, myRs);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return userId;
	}

	
	
	
	public Boolean makeNewUser(String name, String password) {
		Connection myConn = null;

		String sql;
		sql = "INSERT INTO `demo`.`users` ( `userName`, `password`)  VALUES (?,?)";

		try {
			myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "student", "student");

			PreparedStatement preparedStmt = myConn.prepareStatement(sql);
			preparedStmt.setString(1, name);
			preparedStmt.setString(2, password);
			preparedStmt.execute();

			// System.out.println("did insert of new user");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		finally {
			if (myConn != null) {
				try {
					myConn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return true;
	}

	public String getData(String sql, String[] show) throws SQLException {

		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;

		if (sql == "") {
			sql = "select * from employees";
		}

		if (show[0] == null) {
			show[0] = "last_name";
			show[1] = "first_name";
		}

		StringBuilder sb = new StringBuilder();

		try {
			myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "student", "student");
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery(sql);
			while (myRs.next()) {
				// System.out.println(myRs.getString("last_name") + ", " +
				// myRs.getString("first_name"));
				// sb.append("\n" + myRs.getString("last_name") + ", " +
				// myRs.getString("first_name"));
				sb.append("\n" + appendString(show, myRs));
			}

		} catch (Exception exc) {
			exc.printStackTrace();
		} finally {

			this.close(myConn, myStmt, myRs);

			/*
			 * if (myRs != null) { myRs.close(); }
			 * 
			 * if (myStmt != null) { myStmt.close(); }
			 * 
			 * if (myConn != null) { myConn.close(); }
			 */

		}
		return sb.toString();
	}

	private String appendString(String[] show, ResultSet rs) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < show.length; i++) {
			try {
				sb.append(" " + rs.getString(show[i]));
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		return sb.toString();
	}

	private static void close(Connection myConn, Statement myStmt, ResultSet myRs) throws SQLException {

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

	private void close(Statement myStmt, ResultSet myRs) throws SQLException {
		close(null, myStmt, myRs);
	}

	private void close(Connection myConn, ResultSet myRs) throws SQLException {
		close(myConn, null, myRs);
	}
}
