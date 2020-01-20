package lock.listtask;

public class Worker implements Runnable {
	private String workerName;
	private ManageTask manageTask;
	public Worker(ManageTask manageTask,String workerName) {
		this.manageTask=manageTask;
		this.workerName=workerName;
	}
	@Override
	public void run() {
		boolean haveTask = true;
		while(haveTask) {
			try {
				Thread.sleep(2000);
				Task t = pickTask();
				if(t!=null) {
					System.out.println(workerName + " was pick a task: "+t.getTaskName());
					doTask(t);
				}else {
					System.out.println("ME(" +workerName+ ") Dont have a task!");
					haveTask=false;
				}
				
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	public void doTask(Task t) throws InterruptedException {
		boolean isDo = true;
		while(isDo) {
			System.out.println(workerName + " Doing a task..... " + t.getTaskName());
			Thread.sleep(1000);
			isDo = t.setDoneASubTask();
		}
		System.out.println(workerName + " done task "+t.getTaskName());
	}
	public Task pickTask() {
		Task t = manageTask.getATask(workerName);
		if(t!=null) {
			t.setChecked(true);
			t.setWhoDo(workerName);
		}
		return t;
	}

}
