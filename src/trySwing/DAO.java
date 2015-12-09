package trySwing;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.exceptions.*;
import javax.swing.JOptionPane;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

//* inspired by : www.luv2code.com
public class DAO {
	static public Connection conn = null;

	public DAO() {
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo?"
					+ "user=student&password=student&useUnicode=true&characterEncoding=UTF-8");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public List<User> findUserData(String moreSql) throws SQLException {

		Statement stmt = null;
		ResultSet rs = null;
		List<User> lstUsers = new ArrayList<User>();

		String sql = "select userName, password, userId from users "  + moreSql ;

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				lstUsers.add(RS_to_User(rs));
			}

		} catch (Exception exc) {
			exc.printStackTrace();
		} finally {

			this.close(conn, stmt, rs);
		}
		return lstUsers;
	}

	private User RS_to_User(ResultSet rs) {
		User aUser = new User("", "");
		try {
			aUser.setName(rs.getString("userName"));
			aUser.setPassword(rs.getString("password"));
			aUser.setUserId(rs.getInt("userId"));
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return aUser;
	}

	public void CloseConnection() {
		if (conn != null) {
			conn = null;
		}
	}

	public String LoginUser(String name, String password) {
		ResultSet myRs = null;
		Statement stmt;

		String userName = "Guest";
		int userId = -1;

		PasswordHash pHash = new PasswordHash();
		String passwordFromDB = "";

		String sql;
		// sql = "Select userId , password from demo.users where userName = '" +
		// name + "' and password = '" + password + "'";
		sql = "Select userId , password  from demo.users  where  userName    = '" + name + "'";

		try {

			stmt = conn.createStatement();
			myRs = stmt.executeQuery(sql);
			while (myRs.next()) {
				userId = myRs.getInt("userId");
				passwordFromDB = myRs.getString("password");
			}
			if (pHash.validatePassword(password, passwordFromDB)) {
				userName = name;
			}
			// if (userId > 0){ userName = name; }

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		finally {

			try {
				this.close(conn, myRs);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return userName;
	}

	public smallClass makeNewUser(String name, String password) {

		String sql;
		smallClass sc = new smallClass("");

		PasswordHash pHash = new PasswordHash();
		password = pHash.createHash(password);

		sql = "INSERT INTO `demo`.`users` ( `userName`, `password`)  VALUES (?,?)";
		try {
			PreparedStatement preparedStmt = conn.prepareStatement(sql);
			preparedStmt.setString(1, name);
			preparedStmt.setString(2, password);
			preparedStmt.execute();

			System.out.println("did insert of new user");
			sc.setB(true);

		} catch (com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException e) {

			sc.setMsg("a user with this name allready exists !!");
			sc.setB(false);
		}

		catch (SQLException e) {
			e.printStackTrace();
			sc.setB(false);
		}

		finally {
			if (conn != null) {
				try {
					this.close(conn, null);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return sc;
	}

	public String getData(String sql, String[] show) throws SQLException {

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
			myStmt = conn.createStatement();
			myRs = myStmt.executeQuery(sql);
			while (myRs.next()) {
				sb.append("\n" + appendString(show, myRs));
			}

		} catch (Exception exc) {
			exc.printStackTrace();
		} finally {

			this.close(conn, myStmt, myRs);

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

		/*
		 * if (myConn != null) { myConn.close(); }
		 */
	}

	private void close(Statement myStmt, ResultSet myRs) throws SQLException {
		close(null, myStmt, myRs);
	}

	private void close(Connection myConn, ResultSet myRs) throws SQLException {
		close(myConn, null, myRs);
	}
	
	
	

	public Boolean DeleteUser(String name ) {

		String sql;
		sql = "Delete From `demo`.`users`  where  `userName` = '"  + name + "'";
		Boolean Return = false; 
		
		try {
			Statement stmt;
			stmt = conn.createStatement();
			int rt  = stmt.executeUpdate(sql);
			if (rt == 0) { Return = false ;}
			else { Return = true ;}
		}
		 
		catch (SQLException e) {
			e.printStackTrace();
		}

		 
		 
		return Return;
	}

	
	
	
	
	
}
