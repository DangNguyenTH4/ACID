package lock.task.acid;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Factory {

	public static void main(String[] args) {
		ManageTask mt = new ManageTask(10);
		System.out.println("Start game...");
		long t1 = System.currentTimeMillis();
		
		CompletableFuture<Void> a = CompletableFuture.runAsync(new Worker(mt,"worker1"));
		CompletableFuture<Void> b = CompletableFuture.runAsync(new Worker(mt,"worker2"));
		CompletableFuture<Void> c = CompletableFuture.runAsync(new Worker(mt,"worker3"));
		CompletableFuture<Void> d = CompletableFuture.runAsync(new Worker(mt,"worker4"));
		CompletableFuture<Void> e = CompletableFuture.runAsync(new Worker(mt,"worker5"));
		CompletableFuture<Void> f = CompletableFuture.runAsync(new Worker(mt,"worker6"));
		
		CompletableFuture<Void> all = CompletableFuture.allOf(a,b,c,d,e,f);
		try {
			all.get();
		} catch (InterruptedException | ExecutionException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		long t2=System.currentTimeMillis();
		System.out.println("End game!");
		System.out.println(t2-t1);
	}
	

}
