package practice;
/*
 * 
 * Synchronization
 * 	 - the capability to control the access of multiple threads to any shared resource.
 * 	 - better option where we want to allow only one thread to access the shared resource.
 * 
 * Lock
 * 		- Every object has a lock associated with it. By convention, a thread that needs consistent access to an object's fields has to acquire the object's lock before accessing them, and then release the lock when it's done with them.
 * Thread Synchronization
 * 		- Mutual Exclusive
        	- Synchronized method.
        		If you declare any method as synchronized, it is known as synchronized method.
        		Synchronized method is used to lock an object for any shared resource.
        		When a thread invokes a synchronized method, it automatically acquires the lock for that object and releases it when the thread completes its task.
        	- Synchronized block.
        		Synchronized block can be used to perform synchronization on any specific resource of the method.
        		Suppose we have 50 lines of code in our method, but we want to synchronize only 5 lines, in such cases, we can use synchronized block.
        		
        - Static synchronization.
        	If you make any static method as synchronized, the lock will be on the class not on object.
    		Cooperation (Inter-thread communication in java)
		
		- Synchronization Vs Static Synchronization
			Synchronization -> locking on this object lock
			Static Synchronization -> locking on .class object lock
			
 */

class NotSyncObject {
	int n;

	public NotSyncObject(int n) {
		this.n = n;
	}

	void print(String str) {
		System.out.println("printing from " + str);
		for (int i = 0; i < n; i++) {
			System.out.println("printing:" + i);
			try {
				Thread.sleep(400);
			} catch (Exception e) {
				System.out.println("exception");
			}
		}
	}
}

class SyncMethodObj {
	int n;

	public SyncMethodObj(int n) {
		this.n = n;
	}

	synchronized void print(String str) {
		System.out.println("Printing from:" + str);
		for (int i = 0; i < n; i++) {
			System.out.println("printing:" + i);
		}
	}
}

class SyncBlockObj {
	int n;

	public SyncBlockObj(int n) {
		this.n = n;
	}

	public void print(String str) {
		System.out.println("Printing from:" + str);
		synchronized (this) {
			for (int i = 0; i < n; i++) {
				System.out.println("Printing:" + i);
				try {
					Thread.sleep(400);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}
	}
}

class SyncStaticClass {
	static int n;

	SyncStaticClass(int n) {
		this.n = n;
	}

	synchronized static public void printSyncMethod(String str) {
		for (int i = 0; i < n; i++) {
			System.out.println("Printing printSyncMethod From:" + str + "  " + i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void printNonSyncMethod(String str) {
		for (int i = 0; i < n; i++) {
			System.out.println("Printing printNonSyncMethod From:" + str + "  " + i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

public class Synchronization {

	public static void main(String[] args) {

		// there is no synchronization, so output is inconsistent.
		/*
		 * NotSyncObject notSyncObject = new NotSyncObject(5);
		 * 
		 * Runnable notSynchR1 = () -> notSyncObject.print("notSynchThread1"); Thread
		 * notSynchThread1 = new Thread(notSynchR1); notSynchThread1.start();
		 * 
		 * Runnable notSynchR2 = () -> notSyncObject.print("notSynchThread2"); Thread
		 * notSynchThread2 = new Thread(notSynchR2); notSynchThread2.start();
		 */
		/*
		 * Output printing from notSynchThread1 printing:0 printing from notSynchThread2
		 * printing:0 printing:1 printing:1 printing:2 printing:2 printing:3 printing:3
		 * printing:4 printing:4
		 */

		// method is synchronized. So output will be consistent
		SyncMethodObj syncMethodObj = new SyncMethodObj(5);

		Runnable syncMethodR1 = () -> syncMethodObj.print("syncMethodThread1");
		Thread syncMethodThread1 = new Thread(syncMethodR1);

		syncMethodThread1.start();

		Runnable syncMethodR2 = () -> syncMethodObj.print("syncMethodThread2");
		Thread syncMethodThread2 = new Thread(syncMethodR2);

		syncMethodThread2.start();
		/*
		 * output: Printing from:syncMethodThread1 printing:0 printing:1 printing:2
		 * printing:3 printing:4 Printing from:syncMethodThread2 printing:0 printing:1
		 * printing:2 printing:3 printing:4
		 */

		// synchronized block
		SyncBlockObj syncBlockObj = new SyncBlockObj(5);

		System.out.println("---------------Synchronozed Block---------------");

		Runnable syncBlockR1 = () -> syncBlockObj.print("syncBlockThread1");
		Thread syncBlockThread1 = new Thread(syncBlockR1);

		syncBlockThread1.start();

		Runnable syncBlockR2 = () -> syncBlockObj.print("syncBlockThread2");
		Thread syncBlockThread2 = new Thread(syncBlockR2);

		syncBlockThread2.start();

		/*
		 * output Printing from:syncBlockThread1 Printing:0 Printing
		 * from:syncBlockThread2 Printing:1 Printing:2 Printing:3 Printing:4 Printing:0
		 * Printing:1 Printing:2 Printing:3 Printing:4
		 */

		// Static synchronization 2 instance/obj of same class trying to access same
		// synchronized static method
		System.out.println("---------------Static Synchronozation---------------");
		SyncStaticClass syncStatiObj1 = new SyncStaticClass(5);
		Runnable syncStatiR1 = () -> syncStatiObj1.printSyncMethod("syncStatiObj1");
		Thread syncStatiThread1 = new Thread(syncStatiR1);

		SyncStaticClass syncStatiObj2 = new SyncStaticClass(5);
		Runnable syncStatiR2 = () -> syncStatiObj2.printSyncMethod("syncStatiObj2");
		Thread syncStatiThread2 = new Thread(syncStatiR2);

		syncStatiThread1.start();
		syncStatiThread2.start();

		/*
		 * output ---------------Static Synchronozation--------------- Printing
		 * printSyncMethod From:syncStatiObj1 0 Printing printSyncMethod
		 * From:syncStatiObj1 1 Printing printSyncMethod From:syncStatiObj1 2 Printing
		 * printSyncMethod From:syncStatiObj1 3 Printing printSyncMethod
		 * From:syncStatiObj1 4 Printing printSyncMethod From:syncStatiObj2 0 Printing
		 * printSyncMethod From:syncStatiObj2 1 Printing printSyncMethod
		 * From:syncStatiObj2 2 Printing printSyncMethod From:syncStatiObj2 3 Printing
		 * printSyncMethod From:syncStatiObj2 4
		 */

		// Static synchronization 1st instance/obj occurs the lock. 2nd instance/obj of
		// same class cant access the non static methods. it has to wait until the
		// previous instance release the lock.

		SyncStaticClass syncStatiObj3 = new SyncStaticClass(5);
		Runnable syncStatiR3 = () -> syncStatiObj3.printSyncMethod("syncStatiObj3");
		Thread syncStatiThread3 = new Thread(syncStatiR3);

		SyncStaticClass syncStatiObj4 = new SyncStaticClass(5);
		Runnable syncStatiR4 = () -> syncStatiObj2.printNonSyncMethod("syncStatiObj4");
		Thread syncStatiThread4 = new Thread(syncStatiR4);

		syncStatiThread3.start();
		syncStatiThread4.start();

		/*
		 * ---------------Static Synchronozation--------------- Printing printSyncMethod
		 * From:syncStatiObj3 0 Printing printNonSyncMethod From:syncStatiObj4 0
		 * Printing printSyncMethod From:syncStatiObj3 1 Printing printNonSyncMethod
		 * From:syncStatiObj4 1 Printing printSyncMethod From:syncStatiObj3 2 Printing
		 * printNonSyncMethod From:syncStatiObj4 2 Printing printSyncMethod
		 * From:syncStatiObj3 3 Printing printNonSyncMethod From:syncStatiObj4 3
		 * Printing printSyncMethod From:syncStatiObj3 4 Printing printNonSyncMethod
		 * From:syncStatiObj4 4
		 * 
		 */

	}

}
