package comp346pa3s2020;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		double P, C;
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
		double r = 1 - q;
		in.close();
		Producer producer = new Producer(buffer, mutex, full, empty);
		producer.start();
		Consumer consumer = new Consumer(buffer, mutex, full, empty);
		consumer.start();
		while(true) {
			P = Math.random();
			//System.out.println("P = " + P + " q = " + q);
			if (P < q)
				producer.produce();
			C = Math.random();
			//System.out.println("C = " + C + " r = " + r);
			if (C < r) 
				consumer.consume();
			System.out.print("DEBUG ::: BUFFER: [");
			for (int element: buffer)
				System.out.print(element + " ");
			System.out.println("]");
			System.out.println("mutex " + mutex + ", full " + full + ", empty " + empty);
		}
	}

}
