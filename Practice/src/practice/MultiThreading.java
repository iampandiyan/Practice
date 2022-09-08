package practice;

/*
 * The significant differences between extending Thread class and implementing Runnable interface:

    When we extend Thread class, we can’t extend any other class even we require and When we implement Runnable, we can save a space for our class to extend any other class in future or now.
    When we extend Thread class, each of our thread creates unique object and associate with it. When we implements Runnable, it shares the same object to multiple threads.
    
 * Thread Scheduler -> decides which thread to run or execute and which thread to wait.
 * There are two factors for scheduling a thread i.e. Priority and Time of arrival.
 * Priority -> Priority of each thread lies between 1 to 10. If a thread has a higher priority, it means that thread has got a better chance of getting picked up by the thread scheduler.
 * Time of Arrival -> Suppose two threads of the same priority enter the runnable state, then priority cannot be the factor to pick a thread from these two threads. In such a case, arrival time of thread is considered by the thread scheduler. A thread that arrived first gets the preference over the other threads.
 * 
 * - Thread Scheduler Algorithms->
 * 		First Come First Serve Scheduling:
 * 		Time-slicing scheduling:
 * 		Preemptive-Priority Scheduling:
 * 
 * - Thread.sleep(-100); -> it throws the exception IllegalArgumentException as the time is -ive which is -100
 * 
 * -  After starting a thread, it can never be started again. If you does so, an IllegalThreadStateException is thrown.
 *   
 * -  call Java run() method directly instead start() method ->
 *   	Each thread starts in a separate call stack. Invoking the run() method from the main thread, the run() method goes onto the current call stack rather than at the beginning of a new call stack.
 * 
 * - The join() method in Java is provided by the java.lang.Thread class that permits one thread to wait until the other thread to finish its execution
 * 
 * - An InterruptedException is thrown when a thread is interrupted while it's waiting, sleeping, or otherwise occupied. In other words, some code has called the interrupt() method on our thread. It's a checked exception, and many blocking operations in Java can throw it.
 * - Thread Name
 * - Thread.currentThread().getName()
 * - Daemon thread in Java is a service provider thread that provides services to the user thread. Its life depend on the mercy of user threads i.e. when all the user threads dies, JVM terminates this thread automatically.
 * 		There are many java daemon threads running automatically e.g. gc, finalizer etc.  
 */

class ExtendsThread extends Thread {
	public void run() {
		System.out.println("Extends Thread");
	};
}

class ImplementRunnable implements Runnable {
	public void run() {
		System.out.println("Implements Runnable");
	};
}

public class MultiThreading {

	public static void main(String[] args) {
		// -------------------------------------------------------------------------------------------------
		// 1. Extends Thread
		ExtendsThread extendsThread = new ExtendsThread();
		extendsThread.start();
		/*
		 * output: Extends Thread
		 */

		// -------------------------------------------------------------------------------------------------
		// 2. Implements runnable interface
		ImplementRunnable implementRunnable = new ImplementRunnable();
		Thread implementRunnableThread = new Thread(implementRunnable);
		implementRunnableThread.start();
		/*
		 * output: Implements Runnable
		 */

		// -------------------------------------------------------------------------------------------------
		// 3. Using Thread class
		Thread thread = new Thread();
		thread.start();// print nothing

		// -------------------------------------------------------------------------------------------------
		// 4. thread with name and runnable interface
		Thread t = new Thread(() -> System.out.println("Thread with name started"), "Lambda Thread");
		t.start();
		System.out.println("Thread Name:" + t.getName());
		/*
		 * output: Thread Name:Lambda Thread, Thread with name started
		 */

		// -------------------------------------------------------------------------------------------------
		// Threading with sleep
		Runnable r1 = () -> {
			try {
				for (int i = 0; i < 5; i++) {
					Thread.sleep(500);
					System.out.println(i);
				}

			} catch (Exception e) {
				System.out.println(e);
			}
		};
		Thread th1 = new Thread(r1);
		Thread th2 = new Thread(r1);
		th1.start();
		th2.start();
		try {
			th1.join();
			th2.join();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		/*
		 * output: 0 0 1 1 2 2 3 3 4 4
		 */

		// -------------------------------------------------------------------------------------------------
		// sleep with -ve argument
		try {
			Thread.sleep(-100);
		} catch (Exception e2) {
			System.out.println(e2);
		}
		/*
		 * output: java.lang.IllegalArgumentException: timeout value is negative
		 */

		// -------------------------------------------------------------------------------------------------
		System.out.println("-------start th1 runnable 2nd time-------");

		try {
			th1.start();
		} catch (Exception e) {
			System.out.println(e);
		}
		System.out.println("--------------------------------");
		/*
		 * output: -------start th1 runnable 2nd time------- Exception in thread "main"
		 * java.lang.IllegalThreadStateException
		 */

		// -------------------------------------------------------------------------------------------------
		System.out.println("-------start extendsThread 2nd time-------");
		try {
			extendsThread.start();
		} catch (Exception e) {
			System.out.println(e);
		}

		System.out.println("--------------------------------");
		/*
		 * output: -------start extendsThread 2nd time-------
		 * java.lang.IllegalThreadStateException --------------------------------
		 */

		// -------------------------------------------------------------------------------------------------
		System.out.println("-------start call run method----------------");
		// it will not run parallely.
		// run() method goes onto the current call stack rather than at the beginning of
		// a new call stack.
		Thread threadCallRun1 = new Thread(r1);
		Thread threadCallRun2 = new Thread(r1);
		threadCallRun1.run();
		threadCallRun2.run();
		System.out.println("-----------------------");
		/*
		 * output: -------start call run method---------------- 0 1 2 3 4 0 1 2 3 4
		 * -----------------------
		 */

		// -------------------------------------------------------------------------------------------------
		System.out.println("-------start InterruptedException---------------");
		Thread threadCallRun3 = new Thread(r1);
		threadCallRun3.start();
		try {
			threadCallRun3.interrupt();
		} catch (Exception e) {
			e.printStackTrace();
		}

		/*
		 * output: -------start InterruptedException---------------
		 * java.lang.InterruptedException: sleep interrupteda
		 */
		// -------------------------------------------------------------------------------------------------
		// Default name of a thread
		System.out.println("threadCallRun3 Name:" + threadCallRun3.getName());
		System.out.println("Current Thread: " + Thread.currentThread().getName());
		/*
		 * output:threadCallRun3 Name:Thread-7 Current Thread: main
		 */

		// -------------------------------------------------------------------------------------------------
		// Deamon thread
		Runnable deamonThread = () -> {
			if (Thread.currentThread().isDaemon()) {
				System.out.println("Deamon Thread");
			} else {
				System.out.println("User Thread");
			}
		};

		Thread userThread = new Thread(deamonThread);
		Thread daemonThread = new Thread(deamonThread);
		daemonThread.setDaemon(true);
		System.out.println("--------Starts user Thread----------");
		userThread.start();
		System.out.println("--------Starts daemon Thread----------");
		daemonThread.start();
		/*
		 * output: --------Starts user Thread---------- --------Starts daemon
		 * Thread---------- User Thread Deamon Thread
		 */

	}

}
