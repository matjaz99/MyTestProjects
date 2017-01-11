package si.matjazcerkvenik.test.mysql.example2;

public class User {

	private String id;
	private String username;
	private boolean locked;
	private long lastLogin;

	public User(String id, String username, boolean locked, long lastLogin) {
		this.id = id;
		this.username = username;
		this.locked = locked;
		this.lastLogin = lastLogin;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	public long getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(long lastLogin) {
		this.lastLogin = lastLogin;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", locked=" + locked + ", lastLogin=" + lastLogin + "]";
	}

}
