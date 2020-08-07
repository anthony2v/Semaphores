package comp346pa3s2020;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		int[] buffer = new int[10];
		for (int element: buffer) {
			element = 0;
		}
		Semaphore mutex = new Semaphore(1);
		Semaphore full = new Semaphore(0);
		Semaphore empty = new Semaphore(10);
		Scanner in = new Scanner(System.in);
		System.out.print("Please enter a value for q: ");
		double q = Double.parseDouble(in.next());
		in.close();
		if (q > 0) {
			Producer producer = new Producer(buffer, q, mutex, full, empty);
			producer.start();
		}
		if (q < 1) {
			Consumer consumer = new Consumer(buffer, 1 - q, mutex, full, empty);
			consumer.start();
		}
	}

}
