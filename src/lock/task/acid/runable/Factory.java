package lock.task.acid.runable;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Factory {
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";
	
	
	public static void main(String[] args) {
		
		
		
		ManageTask mt = new ManageTask(10);
		System.out.println("Start game...");
		long t1 = System.currentTimeMillis();
		
		CompletableFuture<Void> a = CompletableFuture.runAsync(new Worker(mt,"worker1",ANSI_RED));
		CompletableFuture<Void> b = CompletableFuture.runAsync(new Worker(mt,"worker2",ANSI_GREEN));
		CompletableFuture<Void> c = CompletableFuture.runAsync(new Worker(mt,"worker3",ANSI_YELLOW));
		CompletableFuture<Void> d = CompletableFuture.runAsync(new Worker(mt,"worker4",ANSI_BLUE));
		CompletableFuture<Void> e = CompletableFuture.runAsync(new Worker(mt,"worker5",ANSI_PURPLE));
		CompletableFuture<Void> f = CompletableFuture.runAsync(new Worker(mt,"worker6",ANSI_CYAN));
		
		CompletableFuture<Void> all = CompletableFuture.allOf(a,b,c,d,e,f);
		try {
			all.get();
		} catch (InterruptedException | ExecutionException e1) {
			e1.printStackTrace();
		}
		
		long t2=System.currentTimeMillis();
		System.out.println("End game!");
		System.out.println(t2-t1);
	}
	

}
