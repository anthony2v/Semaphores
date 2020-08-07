package comp346pa3s2020;

public class Consumer extends Thread {
	private int[] buffer;
	private int index = 0;
	private double r = 0;
	private Semaphore mutex;
	private Semaphore full;
	private Semaphore empty;
	public Consumer(int[] buffer, double r, Semaphore mutex, Semaphore full, Semaphore empty) {
		this.buffer = buffer;
		this.r = r;
		this.mutex = mutex;
		this.full = full;
		this.empty = empty;
	}
	public void consume() {
		while(true) {
			int next_consumed;
			double C = Math.random();
			//System.out.println("C = " + C + " r = " + r);
			if (C < r) {
				if (full.getS() == 0)
					System.out.println("DEBUG ::: BUFFER EMPTY, BUSY WAITING");
				full.wait(full);
				if (mutex.getS() == buffer.length)
					System.out.println("DEBUG ::: MUTEX LOCKED, BUSY WAITING");
				mutex.wait(mutex);
				// Remove an item from the buffer to next_consumed
				next_consumed = buffer[index];
				buffer[index] = 0;
				index++;
				if (index == buffer.length)
					index = 0;
				mutex.signal(mutex);
				empty.signal(empty);
				System.out.println("DEBUG ::: CONSUMING " + next_consumed);
			}
//			System.out.print("DEBUG ::: BUFFER: [");
//			for (int element: buffer)
//				System.out.print(element + " ");
//			System.out.println("]");
			System.out.println("DEBUG ::: MUTEX " + mutex + ", FULL " + full + ", EMPTY " + empty);
		}
	}
	public void run() {
		consume();
	}
}
