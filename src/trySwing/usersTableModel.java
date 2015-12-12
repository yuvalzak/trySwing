package trySwing;

import javax.swing.table.AbstractTableModel;
import trySwing.User;
import java.util.List ;
  
 

public class usersTableModel extends AbstractTableModel {

	private static final int NAME_COL = 0;
	private static final int USER_ID_COL = 1;
	private static final int PASSWORD_COL = 2;
	private static final int IS_ADMIN_COL = 3;
	
	private String [] columNames = {"name", "user Id", "password", "is Admin" };
	
	public usersTableModel(List<User> arrUsers) {
		this.arrUsers = arrUsers;
	}

	private List<User> arrUsers  ;
	
	public String getColumnName(int columnIndex) {
	    
	    return columNames[columnIndex];
	}

	@Override
	public int getRowCount() {
        return arrUsers.size();
	}

	@Override
	public int getColumnCount() {
		 return columNames.length ;
	}

	@Override
	public Object getValueAt(int row , int col  ) {
		User aUser = arrUsers.get(row );
		switch (col) {
		case NAME_COL:  return aUser.getName();
	 	case USER_ID_COL:  return aUser.getUserId();	
	 	case PASSWORD_COL : return aUser.getPassword();
	 	case IS_ADMIN_COL : return aUser.getIsAdmin();
	 	default :  return aUser.getName();
		
		}
	}

}
