package trySwing;

import java.sql.Date;

public class LogFile {
	
	private int Id;
	private int LoggedUserId ;
	private String ActionTaken;
	private String theDate ;
	
	
	public LogFile() {
		Id = 0;
		LoggedUserId = 0;
		ActionTaken = "";
		this.theDate = "";
		
	}
	public LogFile(int id, int loggedUserId, String actionTaken, String theDate) {
		super();
		Id = id;
		LoggedUserId = loggedUserId;
		ActionTaken = actionTaken;
		this.theDate = theDate;
	}
	@Override
	public String toString() {
		return "LogFile [Id=" + Id + ", LoggedUserId=" + LoggedUserId + ", ActionTaken=" + ActionTaken + ", theDate="
				+ theDate + "]";
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public int getLoggedUserId() {
		return LoggedUserId;
	}
	public void setLoggedUserId(int loggedUserId) {
		LoggedUserId = loggedUserId;
	}
	public String getActionTaken() {
		return ActionTaken;
	}
	public void setActionTaken(String actionTaken) {
		ActionTaken = actionTaken;
	}
	public String getTheDate() {
		return theDate;
	}
	public void setTheDate(String theDate) {
		this.theDate = theDate;
	}
	

}
