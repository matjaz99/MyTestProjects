package si.matjazcerkvenik.test.hashcode.manager.model;

public class Result {
	
	private int taskId = 0;
	private int workerId = 0;
	private String status = "Finished";
	private String result = "result";
	private long duration = 0;
	private long count = 0;
	
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
	
	public long getDuration() {
		return duration;
	}
	public void setDuration(long duration) {
		this.duration = duration;
	}
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}
	@Override
	public String toString() {
		return "Result [taskId=" + taskId + ", workerId=" + workerId + ", status=" + status + ", result=" + result
				+ ", duration=" + duration + ", count=" + count + "]";
	}
	
	
	
}
