package lock.listtask;

public class Factory {

	public static void main(String[] args) {
		
		ManageTask mt = new ManageTask(10);
		System.out.println("Start game...");
		long t1 = System.currentTimeMillis();
		new Thread(new Worker(mt, "worker1")).start();;
		new Thread(new Worker(mt, "worker2")).start();;
		new Thread(new Worker(mt, "worker3")).start();;
		new Thread(new Worker(mt, "worker4")).start();;
		new Worker(mt, "worker5").run();
		long t2 = System.currentTimeMillis();
		System.out.println("End game!");
		
		System.out.println(t2-t1);
		
	}

}
