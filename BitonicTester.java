import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.CyclicBarrier;

/**
 * This tester class should contain your tests. The TAs will replace this with
 * our own tester class, but be sure to write tests here to check the
 * correctness of your program.
 **/
class BitonicTester implements Runnable {
	final int numThr;

	int width;
	Bitonic bitonic;
	AtomicInteger[] counterArray;
    CyclicBarrier testBarr;

	BitonicTester(int myNumThr) {
		this.numThr = myNumThr;
		this.width = 4;
		this.bitonic = new Bitonic(width);
        this.counterArray = new AtomicInteger[width];
		for (int i=0;i<width;i++) { 
			counterArray[i] = new AtomicInteger(0); 
		}

        Runnable testRunnable = new Runnable() {
			public void run() {
				printResults("Test");
			}
		};
		this.testBarr = new CyclicBarrier(numThr, testRunnable);
	}

	/*
	 * If you want to print out the results of a test, use this method. You can use
	 * barriers to ensure all threads finish before calling this function. Feel free
	 * to add additional functionality to see what goes on.
	 */

	void printResults(String testName) {
		System.out.println("------------- " + testName + " --------------");
		int colNum = 5;
		for (int i = 0; i < width; i += colNum) {
			for (int j = 0; j < colNum && (i + j < width); j++) {
				System.out.print("[" + (i + j) + "]=" + counterArray[i + j] + "; ");
			}
			System.out.println();
		}
		System.out.println("-------------------------------------");
	}

    private void testCounting() {
        ThreadLocal<Integer> outwire = new ThreadLocal<Integer>();
        // Add Three Times	
        outwire.set(bitonic.traverse((int) Math.random() % width));
        counterArray[outwire.get()].getAndIncrement();
        outwire.set(bitonic.traverse((int) Math.random() % width));
        counterArray[outwire.get()].getAndIncrement();
        outwire.set(bitonic.traverse((int) Math.random() % width));
        counterArray[outwire.get()].getAndIncrement();
        // Minus One Time
        outwire.set(bitonic.antiTraverse((int) Math.random() % width));
        counterArray[outwire.get()].getAndDecrement();
    }

	public void run() {
		// Begin writing your tests here
        try {
	        testCounting();
	        testBarr.await();
		} catch (Exception e) {
			System.out.println("$$--- " + e);
		}
	}
}
