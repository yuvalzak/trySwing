package trySwing;

public class UserAndMsg    {
  private String msg;
  private User user;
    

public String getMsg() {
	return msg;
}

public void setMsg(String msg) {
	this.msg = msg;
}
  
  public UserAndMsg(String msg, User user){
	  
	  this.user = user;
	  this.msg = msg;
  }
 
  public User getUser(){
	  return user;
	  
  }
}
