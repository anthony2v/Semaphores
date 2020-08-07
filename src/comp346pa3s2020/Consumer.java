package comp346pa3s2020;

public class Consumer extends Thread {
	private double C = 0;
	private int[] buffer;
	private int index = 0;
	private int maxIndex = 0;
	private double r = 0;
	private Semaphore mutex;
	private Semaphore full;
	private Semaphore empty;
	public Consumer(int[] buffer, double q, Semaphore mutex, Semaphore full, Semaphore empty) {
		this.buffer = buffer;
		this.r = 1 - q;
		this.mutex = mutex;
		this.full = full;
		this.empty = empty;
		this.maxIndex = buffer.length - 1;
	}
	private void consume() {
		if (Math.random() < r) {
			int next_consumed = -1;
			full.wait(full);
			mutex.wait(mutex);
			if (index > maxIndex && buffer[0] == -1)
				System.out.println("DEBUG ::: BUFFER EMPTY");
			else if (index > maxIndex) {
				index = 0;
				next_consumed = buffer[index];
				buffer[index] = -1;
				index++;
				System.out.println("DEBUG ::: CONSUMING " + next_consumed);
			}
			else {
				next_consumed = buffer[index];
				buffer[index] = -1;
				index++;
				System.out.println("DEBUG ::: CONSUMING " + next_consumed);
			}
			mutex.signal(mutex);
			empty.signal(empty);
		}
	}
	public void run() {
		while(true) {
			C = Math.random();
			if (C < r) 
				consume();
		}
	}
}
