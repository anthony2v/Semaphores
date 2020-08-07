package comp346pa3s2020;

public class Producer extends Thread {
	private double P = 0;
	private int[] buffer;
	private int index = 0;
	private int maxIndex = 0;
	private double q = 0;
	private Semaphore mutex;
	private Semaphore full;
	private Semaphore empty;
	public Producer(int[] buffer, double q, Semaphore mutex, Semaphore full, Semaphore empty) {
		this.buffer = buffer;
		this.q = q;
		this.mutex = mutex;
		this.full = full;
		this.empty = empty;
		this.maxIndex = buffer.length - 1;
	}
	private void produce() {
		if (Math.random() < q) {
			int next_produced = (int)(Math.random() * 100);
			empty.wait(empty);
			mutex.wait(mutex);
			if (index > maxIndex && buffer[0] != -1)
				System.out.println("DEBUG ::: BUFFER FULL");
			else if (index > maxIndex){
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
			System.out.print("DEBUG ::: BUFFER: [");
			for (int element: buffer)
				System.out.print(element + " ");
			System.out.println("]");
			mutex.signal(mutex);
			full.signal(full);
		}
	}
	public void run() {
		while(true) {
			P = Math.random();
			if (P < q) 
				produce();
		}
	}
}
