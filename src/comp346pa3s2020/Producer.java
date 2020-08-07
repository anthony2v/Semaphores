package comp346pa3s2020;

public class Producer extends Thread {
	private int[] buffer;
	private int index = 0;
	private int maxIndex = 0;
	private Semaphore mutex;
	private Semaphore full;
	private Semaphore empty;
	public Producer(int[] buffer, Semaphore mutex, Semaphore full, Semaphore empty) {
		this.buffer = buffer;
		this.mutex = mutex;
		this.full = full;
		this.empty = empty;
		this.maxIndex = buffer.length - 1;
	}
	public void produce() {
		if (buffer[index] <= 0)
			System.out.println("DEBUG ::: BUFFER FULL");
		else {
			int next_produced = (int)(Math.random() * 100);
			empty.wait(empty);
			mutex.wait(mutex);
			if (index > maxIndex){
				index = 0;
				System.out.println("DEBUG ::: PRODUCING " + next_produced);
				buffer[index] = next_produced;
				index++;
			}
			else {
				System.out.println("DEBUG ::: PRODUCING " + next_produced);
				buffer[index] = next_produced;
				index++;
			}
			mutex.signal(mutex);
			full.signal(full);
		}
	}
	public void run() {
		while(true) {
			
		}
	}
}
