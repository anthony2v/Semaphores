package comp346pa3s2020;

public class Producer extends Thread {
	private double P = 0;
	private int[] buffer;
	private double q = 0;
	private Semaphore populated;
	private Semaphore empty;
	public Producer(int[] buffer, double q, Semaphore populated, Semaphore empty) {
		this.buffer = buffer;
		this.q = q;
		this.populated = populated;
		this.empty = empty;
	}
	private void produce() {
		if (Math.random() < q && populated.getS() < buffer.length - 1) {
			int product = (int)(Math.random() * 100);
			empty.wait(5);
			System.out.println("DEBUG ::: PRODUCING " + product);
			buffer[populated.getS()] = product;
			empty.signal();
			populated.signal();
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
