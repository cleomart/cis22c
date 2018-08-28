
public class QueueArrayTester {

	public static void main(String[] args) {
		ArrayQueue<Integer> mine = new ArrayQueue<>();
		mine.enqueue(12);
		System.out.println(mine.first() + " Is the leader");
		mine.enqueue(14);
		System.out.println(mine.first() + " Is the leader");
		System.out.println(mine.dequeue() + " Is removed");
		System.out.println(mine.first() + " Is the leader");
	}
}
