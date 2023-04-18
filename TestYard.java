import java.util.Random;

public class TestYard implements Runnable {

	private final Yard yard;
	private final Random rand;
	private final int numRepeats;

	TestYard (int repeats) {
		yard = new Yard();
		rand = new Random();
		numRepeats = repeats;
	}

	// public void run() {

	// 	// Repeatedly+randomly act as one of Alice's or Bob's pets
	// 	// Feel free to modify this to whatever will make your tests easier
	// 	for (int i=0; i < numRepeats; i++) {
	// 		boolean isAlicePet = rand.nextBoolean();

	// 		if (isAlicePet) {
	// 			yard.enterAlicePet(); yard.leaveAlicePet();
	// 		} else {
	// 			yard.enterBobPet(); yard.leaveBobPet();
	// 		}
	// 	}

	// }

	public void run() {
		// Run your tests here!
		try {
			// Repeatedly+randomly act as one of Alice's or Bob's pets
			// Feel free to modify this to whatever will make your tests easier
			for (int i = 0; i < numRepeats; i++) {
				yard.enterAlicePet();
				assert (yard.bpets == 0); // Assert there are no Bob pets
				yard.enterAlicePet();
				assert (yard.bpets == 0);
				yard.leaveAlicePet();
				assert (yard.bpets == 0);
				yard.leaveAlicePet();
				yard.enterBobPet();
				assert (yard.apets == 0);
				yard.enterBobPet();
				assert (yard.apets == 0); // Assert there are no Alice pets
				yard.leaveBobPet();
				assert (yard.apets == 0);
				yard.leaveBobPet();
			}
			System.out.println(Thread.currentThread().getName() + " is done");
		} catch (Exception e) {
			System.out.println(e);
			System.out.println(":" + Thread.currentThread().getName() + e.getMessage());
		}
	}
}



