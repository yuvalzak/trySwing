package trySwing;
 
import java.sql.*;
import com.mysql.jdbc.exceptions.*;
import javax.swing.JOptionPane;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

//* inspired by : www.luv2code.com
public class DAO {
static  public Connection myConn =   null;
 
	
public DAO() {
	     try {
			myConn = DriverManager.getConnection( "jdbc:mysql://localhost:3306/demo?" + 
			    "user=student&password=student&useUnicode=true&characterEncoding=UTF-8");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

public void CloseConnection(){
	if (myConn != null){
		myConn = null;
	}
}
	
	public String LoginUser(String name, String password) {
		ResultSet myRs = null  ;
		Statement stmt  ;
		
		String userName = "Guest";
		int userId = -1;

		PasswordHash pHash = new  PasswordHash();
		String passwordFromDB = "";
		 
		
		String sql;
	//	sql = "Select userId , password  from demo.users  where  userName  = '" + name  +  "' and  password = '" + password + "'";
		sql = "Select userId , password  from demo.users  where  userName    = '" + name  +  "'" ;

		
		try {
		 
			stmt = myConn.createStatement();
			myRs = stmt.executeQuery(sql);
			while (myRs.next()) {
				userId = myRs.getInt("userId");
				  passwordFromDB = myRs.getString("password");
			}
			if (   pHash.validatePassword(password , passwordFromDB)){ userName = name; }  
		//	if (userId > 0){ userName = name; }

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
		return userName;
	}

	
	
	
	public smallClass makeNewUser(String name, String password ) {
		
		String sql;
		smallClass sc = new smallClass("");
		
		PasswordHash pHash = new  PasswordHash();
		password = pHash.createHash(password);
		
		sql = "INSERT INTO `demo`.`users` ( `userName`, `password`)  VALUES (?,?)";
		try {
			PreparedStatement preparedStmt = myConn.prepareStatement(sql);
			preparedStmt.setString(1, name);
			preparedStmt.setString(2, password);
			preparedStmt.execute();

			 System.out.println("did insert of new user");
			 sc.setB(true);  
 
	 	} catch (    com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException e){
			
			 
			 sc.setMsg( "a user with this name allready exists !!" );
			sc.setB(false);  
		}
		
		catch (SQLException e) {
			e.printStackTrace();
			sc.setB(false); 
		}

		finally {
			if (myConn != null) {
				try {
					this.close(myConn, null);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return sc;
	}

	/**
	 * @param sql
	 * @param show
	 * @return
	 * @throws SQLException
	 */
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
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery(sql);
			while (myRs.next()) {
				sb.append("\n" + appendString(show, myRs));
			}

		} catch (Exception exc) {
			exc.printStackTrace();
		} finally {

			this.close(myConn, myStmt, myRs);

			 

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

	/*	if (myConn != null) {
			myConn.close();
		}*/
	}

	private void close(Statement myStmt, ResultSet myRs) throws SQLException {
		close(null, myStmt, myRs);
	}

	private void close(Connection myConn, ResultSet myRs) throws SQLException {
		close(myConn, null, myRs);
	}
}
