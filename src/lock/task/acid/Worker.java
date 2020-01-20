package lock.task.acid;

import java.util.Random;

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
		boolean justWakeUp = false;
		while(haveTask) {
			try {
				Thread.sleep(2000);
				Task t = pickTask();
				if(t!=null) {
					System.out.println(workerName + " was pick a task: "+t.getTaskName());
					try {
						justWakeUp=false;
						doTask(t);
					}
					catch(RuntimeException r) {
						t.rollBack();
						manageTask.notifyAll();
					}
					
				}
				else {
					haveTask=false;
					System.out.println("ME(" +workerName+ ") Dont have a task!");
					if(!justWakeUp) {
						System.out.println("Wait taskk");
						manageTask.wait();
						System.out.println("Wake up");
						justWakeUp = true;
						haveTask = true;
					}
					
				}
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	public void doTask(Task t) throws InterruptedException {
		boolean isDo = true;
		Random r = new Random();
		System.out.println(workerName + " Doing a task..... " + t.getTaskName());
		while(isDo) {
			
			Thread.sleep(1000);
			isDo = t.setDoneASubTask();
			if(r.nextInt(10)==5) {
				throw new DoTaskException("Do task unsuccessful");
			}
			
		}
		System.out.println(workerName + " done task --------------------- "+t.getTaskName());
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
