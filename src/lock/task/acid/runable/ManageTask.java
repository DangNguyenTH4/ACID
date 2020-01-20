package lock.task.acid.runable;

import java.util.ArrayList;
import java.util.List;

public class ManageTask {
	private List<Task> listTask;

	public ManageTask(int i) {
		listTask = new ArrayList<Task>();
		for (int k = 0; k < i; k++) {
			Task t = new Task("Task" + k, k + i);
			listTask.add(t);
		}
	}

	public List<Task> getListTask() {
		return listTask;
	}

	public void setListTask(List<Task> listTask) {
		this.listTask = listTask;
	}

	// Lock trên từng task -> Nhanh hơn nhiều (Tùy vào số lượng task)
	public Task getATask(String worker) {
		System.out.println(worker + " finding a task ......");
		for (Task t : listTask) {
			try {
				System.out.println(worker + " is checking "+ t.getTaskName()+"...");
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			synchronized (t) {
				if (!t.isChecked()) {
					t.setChecked(true);
					t.setWhoDo(worker);
					return t;
				}
			}

		}
		return null;
	}

}
