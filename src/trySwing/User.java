package trySwing;

public class User {
	
	private String name;
	private String password ;
	private int userId;
	
	public User(User user){
		if (user != null){
		name = user.getName();
		password = user.getPassword();
		userId = user.getUserId();
		}else {
			name = "";
			password = "";
			userId = 0;
		}
	}
	
	public User(String name, String password) {
		super();
		this.name = name;
		this.password = password;
	}
	
	public User(String name, String password, int id) {
		super();
		this.name = name;
		this.password = password;
		this.userId = id;
	}
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "User [name=" + name + ", password=" + password + "]";
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
