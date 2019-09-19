package si.matjazcerkvenik.test.hashcode.worker.model;

public class Task {
	
	private int taskId = 0;
	private int workerId = 0;
	private String algorithm = "MD5";
	private String chars = "abcdefghijklmnopqrstuvzxywABCDEFGHIJKLMNOPQRSTUVZXYW1234567890";
	private String search = "MatjazCerkvenik";
	
	public int getTaskId() {
		return taskId;
	}
	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}
	public int getWorkerId() {
		return workerId;
	}
	public void setWorkerId(int workerId) {
		this.workerId = workerId;
	}
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	public String getAlgorithm() {
		return algorithm;
	}
	public void setAlgorithm(String algorithm) {
		this.algorithm = algorithm;
	}
	public String getChars() {
		return chars;
	}
	public void setChars(String chars) {
		this.chars = chars;
	}
	@Override
	public String toString() {
		return "Task [taskId=" + taskId + ", workerId=" + workerId + ", algorithm=" + algorithm + ", chars=" + chars
				+ ", search=" + search + "]";
	}

}
