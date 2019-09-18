package si.matjazcerkvenik.test.hashcode.manager.model;

public class Result {
	
	private String status = "Finished";
	private String result = "result";
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	@Override
	public String toString() {
		return "Result [status=" + status + ", result=" + result + "]";
	}
	
	
	
}
