package lock.task.acid;
import java.util.ArrayList;
import java.util.List;

public class Task {
	private boolean checked;
	private String taskName;
	private String whoDo;
	private List<String> subtask;
	private List<Boolean> subtaskDone;
	public void rollBack() {
		System.out.println("Roll back ------------------"+taskName);
		whoDo=null;
		int size = subtaskDone.size();
		for(int i = 0 ; i<size;i++) {
			subtaskDone.set(i, false);
		}
		checked=false;
	}
	public Task(String taskName, int numberOfTask) {
		super();
		this.taskName = taskName;
		checked = false;
		subtask = new ArrayList<String>();
		subtaskDone = new ArrayList<>();
		generate(numberOfTask);
		
	}
	
	public String getWhoDo() {
		return whoDo;
	}

	public void setWhoDo(String whoDo) {
		this.whoDo = whoDo;
	}

	private void generate(int n) {
		for(int i =0;i<n;i++) {
			subtask.add(taskName+"___"+i);
			subtaskDone.add(false);
		}
	}
	public boolean isChecked() {
		return checked;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public List<String> getSubtask() {
		return subtask;
	}
	public void setSubtask(List<String> subtask) {
		this.subtask = subtask;
	}
	public List<Boolean> getSubtaskDone() {
		return subtaskDone;
	}
	public void setSubtaskDone(List<Boolean> subtaskDone) {
		this.subtaskDone = subtaskDone;
	}
	private int i =0;
	
	public Boolean setDoneASubTask() {
		if(i<subtaskDone.size()) {
			subtaskDone.set(i, true);
			System.out.println(whoDo + " Done sub task: "+subtask.get(i));
			i++;
			
			return true;
		}
		return false;
	}


	
	
}
