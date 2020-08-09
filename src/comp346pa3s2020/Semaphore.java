package comp346pa3s2020;

public class Semaphore {
	private volatile int s = 0;
	public Semaphore(int S) {
		s = S;
	}
	public int getS() {
		return s;
	}
	public void wait(Semaphore S) {
		while (s <= 0) {
			;
		}
		s--;
	}
	public void signal(Semaphore S) {
		s++;
	}
	@Override
	public String toString() {
		return "[s=" + s + "]";
	}
}
