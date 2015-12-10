package trySwing;

import javax.swing.table.AbstractTableModel;
import trySwing.LogFile;
import java.util.List ;

public class LogFileTableModel extends AbstractTableModel {

	private static final int ID_COL = 0;
	private static final int LOGGED_USER_ID_COL = 1;
	private static final int ACTION_TAKEN_COL = 2;
	private static final int ADATE_COL = 3;
	 
	
	private String [] columNames = {"ID", "LOGGED ID", "ACTION TAKEN", "DATE" };
	
	public LogFileTableModel(List<LogFile> arrLogFiles) {
		this.arrLogFiles = arrLogFiles;
	}

	private List<LogFile> arrLogFiles  ;
    
	@Override
	public String getColumnName(int columnIndex) {
	    
	    return columNames[columnIndex];
	}
	
	@Override
	public int getRowCount() {
        return arrLogFiles.size();
	}

	@Override
	public int getColumnCount() {
		 return columNames.length ;
	}

	@Override
	public Object getValueAt(int row , int col  ) {
		LogFile aLogFile = arrLogFiles.get(row );
		switch (col) {
		case ID_COL:  return aLogFile.getId();
		case LOGGED_USER_ID_COL: return aLogFile.getLoggedUserId();
		case ACTION_TAKEN_COL: return aLogFile.getActionTaken();
		case ADATE_COL: return aLogFile.getTheDate();
		
	 	default :  return aLogFile.getActionTaken();
		
		}
	}

}

