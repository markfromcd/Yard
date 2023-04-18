public class RunYard {

	// Change this to change the number of threads in the system.
	private static final int numThr = 20;
	// Change this for the number of times you'd like to repeat the tests
	private static final int numRepeats = 1000;


	public static void main(String[] args) {
		Thread threads[] = new Thread[numThr];
		TestYard tester = new TestYard(numRepeats);


		for (int i = 0; i < numThr; i++) {
			threads[i] = new Thread(tester);
			threads[i].start();
		}

		try {
			for (int i = 0; i < numThr; i++) { threads[i].join(); }
		} catch (InterruptedException ex) {
			System.out.println("Something went wrong during joining.");
		}
	}

}