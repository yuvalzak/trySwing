package trySwing;

public class User {
	
	private String name;
	private String password ;
	private int userId;
	private Boolean isAdmin ;
	
	public Boolean getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Boolean isAdmin) {
		 
		this.isAdmin =isAdmin;
		 
	}

	public User(User user){
		if (user != null){
		name = user.getName();
		password = user.getPassword();
		userId = user.getUserId();
		isAdmin = user.getIsAdmin();
		}else {
			name = "";
			password = "";
			userId = 0;
			isAdmin = false;
		}
	}
	
	public User(String name, String password, Boolean isAdmin) {
		this.name = name;
		this.password = password;
		this.isAdmin = isAdmin;
	}
	
	public User(String name, String password, int id, Boolean isAdmin) {
		this.name = name;
		this.password = password;
		this.userId = id;
		this.isAdmin = isAdmin;
	}
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "User [name=" + name + ", password=" + password + ",  isAdmin=" + isAdmin + "]";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	

}
