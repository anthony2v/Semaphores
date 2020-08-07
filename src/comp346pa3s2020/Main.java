package comp346pa3s2020;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		int[] buffer = new int[10];
		for (int element: buffer) {
			element = -1;
		}
		Semaphore mutex = new Semaphore(1);
		Semaphore full = new Semaphore(0);
		Semaphore empty = new Semaphore(10);
		Scanner in = new Scanner(System.in);
		System.out.print("Please enter a value for q: ");
		double q = Double.parseDouble(in.next());
		in.close();
		Producer producer = new Producer(buffer, q, mutex, full, empty);
		producer.start();
		Consumer consumer = new Consumer(buffer, q, mutex, full, empty);
		consumer.start();
		while(true) {
			
		}
	}

}
