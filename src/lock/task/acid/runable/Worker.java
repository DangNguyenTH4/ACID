package lock.task.acid.runable;

import java.util.Random;

public class Worker implements Runnable {
	public String ANSI_RESET = "\u001B[0m";
	
	
	private String colorWorker;
	
	
	private String workerName;
	private ManageTask manageTask;
	public Worker(ManageTask manageTask,String workerName) {
		this.manageTask=manageTask;
		this.workerName=workerName;
	}
	public Worker(ManageTask manageTask,String workerName,String colorWorker) {
		this.manageTask=manageTask;
		this.workerName=workerName;
		this.colorWorker=colorWorker;
	}
	@Override
	public void run() {
		boolean haveTask = true;
		while(haveTask) {
			try {
				Thread.sleep(2000);
				Task t = pickTask();
				if(t!=null) {
					System.out.println(colorWorker + workerName + " was pick a task: "+t.getTaskName()+ ANSI_RESET);
					try {
						doTask(t);
					}
					catch(RuntimeException r) {
						t.rollBack();
					}
				}
				else {
					haveTask=false;
					System.out.println(colorWorker + "ME(" +workerName+ ") Dont have a task!"+ANSI_RESET);
				}
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(colorWorker + workerName + " out!!"+ANSI_RESET);
		
	}
	public void doTask(Task t) throws InterruptedException {
		boolean isDo = true;
		Random r = new Random();
		System.out.println(colorWorker+workerName + " Doing a task..... " + t.getTaskName()+ANSI_RESET);
		while(isDo) {
			
			Thread.sleep(1000);
			isDo = t.setDoneASubTask();
			if(r.nextInt(10)==5) {
				throw new DoTaskException("Do task unsuccessful");
			}
			
		}
		System.out.println(colorWorker + workerName + " done task --------------------- "+t.getTaskName()+ANSI_RESET);
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
