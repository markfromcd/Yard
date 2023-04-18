import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;
//import java.util.concurrent.locks.Condition;

public class Yard {
	// The following two counters are for testing your implementatons
	// Note that you CANNOT use AtomicIntegers and the like in
	// your solution-- only Locks and Conditions (and, of course, 
	// non-concurrent data types) are allowed

	AtomicInteger ac = new AtomicInteger(0);
	AtomicInteger bc = new AtomicInteger(0);
	public Yard() {
		
	}
	
	private ReentrantLock lock = new ReentrantLock();
	Condition condition = lock.newCondition();

	// by using a queue, to implement FIFS, which x starvation
	Queue<Long> queue = new LinkedList<>();

	Map<Long, Integer> map = new HashMap<>();

	public int apets = 0;
	public int bpets = 0;

	public void enterAlicePet() {
		//ac.getAndIncrement(); // (Testing)
		//assert(bc.get() == 0); // (Testing) Assert there are no Bob pets around

		// Fill out your implementation for this method below

		lock.lock();
		try {
			Long id = Thread.currentThread().getId();
			int value = map.getOrDefault(id, 0);//new
			if(value == 0) {
				queue.add(id);
				while (bpets != 0 || queue.peek() != id) {					
					condition.await(); //Tricky part, to implement the program, you need to run condition.wait()
				}
				queue.remove();
			}
			map.put(id, map.getOrDefault(id, 0) + 1);
			apets += 1;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			lock.unlock();
		}

		// Best to put this last
		//ac.getAndDecrement(); // (Testing)

	}

	// }
	public void leaveAlicePet() {
		// Fill out your implementation for this method below
		lock.lock();
		try {
			apets -= 1;
			Long id = Thread.currentThread().getId();
			int value = map.get(id);//new
			map.put(id, value - 1);//new
			condition.signalAll();
		} finally {
			lock.unlock();
		}
	}

	public void enterBobPet() {

		lock.lock();
		try {
			Long id = Thread.currentThread().getId();
			int value = map.getOrDefault(id, 0);//new
			if(value !=0){
				map.put(id, value + 1);// new
				bpets += 1; //new
			}
			else{
				queue.add(id);
				while (queue.peek() != id || apets != 0) {	//alice in or it is not the first one in queue			
					condition.await(); //Tricky part, to implement the program, you need to run condition.wait()
				}
				queue.remove();
				map.put(id, value + 1);// new
				bpets += 1; //new
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			lock.unlock();
		}

		// Best to put this last
		//bc.getAndDecrement(); // (Testing)
	}


	public void leaveBobPet() {
		lock.lock();
		try {
			bpets -= 1;
			Long id = Thread.currentThread().getId();
			int value = map.get(id);//new
			map.put(id, value - 1);//new
			condition.signalAll();
		} finally {
			lock.unlock();
		}
	}

}
