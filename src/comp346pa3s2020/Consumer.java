package comp346pa3s2020;

public class Consumer extends Thread {
	private double C = 0;
	private int[] buffer;
	private double r = 0;
	private Semaphore populated;
	private Semaphore empty;
	public Consumer(int[] buffer, double q, Semaphore populated, Semaphore empty) {
		this.buffer = buffer;
		this.r = 1 - q;
		this.populated = populated;
		this.empty = empty;
	}
	private void consume() {
		if (Math.random() < r && populated.getS() != 0 && buffer[populated.getS() - 1] > 0) {
			populated.wait(5);
			int product = buffer[populated.getS()];
			buffer[populated.getS()] = 0;
			populated.signal();
			System.out.println("DEBUG ::: CONSUMING " + product);
			empty.signal();
		}
	}
	public void run() {
		for (int i = 0; i < 200; i++) {
			C = Math.random();
			if (C < r) 
				consume();
		}
	}
}
