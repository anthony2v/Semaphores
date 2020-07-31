package comp346pa3s2020;

public class Main {

	public static void main(String[] args) {
		int[] buffer = new int[10];
		Semaphore populated = new Semaphore(0);
		Semaphore empty = new Semaphore(10);
		double q = 0.33;
		Producer producer = new Producer(buffer, q, populated, empty);
		producer.start();
		Consumer consumer = new Consumer(buffer, q, populated, empty);
		consumer.start();
	}

}
