package trySwing;

public class smallClass extends User {
  private String msg;
  private User user;
    

public String getMsg() {
	return msg;
}

public void setMsg(String msg) {
	this.msg = msg;
}
  
  public smallClass(String msg, User user){
	  super(user);
	  this.user = user;
	  this.msg = msg;
  }
 
  public User getUser(){
	  return user;
	  
  }
}
