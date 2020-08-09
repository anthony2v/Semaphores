package comp346pa3s2020;

public class Producer extends Thread {
	private int[] buffer;
	private int index = 0;
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
	}
	public void produce() {
		while(true) {
			// --- Production block ---
			int next_produced;
			double P = Math.random();
			//System.out.println("P = " + P + " q = " + q);
			if (P < q) {
				next_produced = (int)((Math.random() * 100 + 1));
				if (empty.getS() == 0)
					System.out.println("DEBUG ::: BUFFER FULL, BUSY WAITING");
				empty.wait(empty);
				if (mutex.getS() == buffer.length)
					System.out.println("DEBUG ::: MUTEX LOCKED, BUSY WAITING");
				mutex.wait(mutex);
				// Add next produced to the buffer
				System.out.println("DEBUG ::: PRODUCING " + next_produced);
				buffer[index] = next_produced;
				index++;
				if (index == buffer.length)
					index = 0;
				System.out.println("DEBUG ::: BUFFER: [" + buffer[0] + ", " + buffer[1] + ", " + buffer[2] + ", "
						+ buffer[3] + ", " + buffer[4] + ", " + buffer[5] + ", " 
						+ buffer[6] + ", " + buffer[7] + ", " + buffer[8] + ", "
						+ buffer[9] + "]");
				mutex.signal(mutex);
				full.signal(full);
			}
			System.out.println("DEBUG ::: MUTEX " + mutex + ", FULL " + full + ", EMPTY " + empty);
		}
	}
	public void run() {
		produce();
	}
}
