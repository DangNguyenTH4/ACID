package lock.listtask;
import java.util.ArrayList;
import java.util.List;

public class ManageTask {
	private List<Task> listTask;
	public ManageTask(int i) {
		listTask = new ArrayList<Task>();
		for(int k=0;k<i;k++) {
			Task t=new Task("Task"+k, k+i);
			listTask.add(t);
		}
	}
	synchronized public List<Task> getListTask() {
		return listTask;
	}

	synchronized public void setListTask(List<Task> listTask) {
		this.listTask = listTask;
	}
	//Vì một worker đã khóa cả list task 
	//=> Những worker khác phải đợi worker này tìm thấy task thì worker khác mới được vào tìm task.
	//=> Giải pháp: Không khóa listTask nữa, mà khóa Từng task
	synchronized public Task getATask(String worker) {
		for(Task t : listTask) {
			try {
				System.out.println(worker + " finding a task ......");
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(!t.isChecked()) {
				return t;
			}
		}
		return null;
	}
	
}
