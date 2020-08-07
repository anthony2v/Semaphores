package comp346pa3s2020;

public class Consumer extends Thread {
	private int[] buffer;
	private int index = 0;
	private int maxIndex = 0;
	private Semaphore mutex;
	private Semaphore full;
	private Semaphore empty;
	public Consumer(int[] buffer, Semaphore mutex, Semaphore full, Semaphore empty) {
		this.buffer = buffer;
		this.mutex = mutex;
		this.full = full;
		this.empty = empty;
		this.maxIndex = buffer.length - 1;
	}
	public void consume() {
		if (index > maxIndex)
			index = 0;
		int next_consumed = buffer[index];
		if (next_consumed == -1)
			System.out.println("DEBUG ::: BUFFER EMPTY");
		else {
			full.wait(full);
			mutex.wait(mutex);
			buffer[index] = -1;
			index++;
			System.out.println("DEBUG ::: CONSUMING " + next_consumed);
		}
		mutex.signal(mutex);
		empty.signal(empty);
	}
	public void run() {
		while(true) {
			
		}
	}
}
