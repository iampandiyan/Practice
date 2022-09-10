package practice;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

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
 * 
 *   - Thread Pool
 *   	- ExecutorService
 *   		The Concurrency API introduces the concept of an ExecutorService as a higher level replacement for working with threads directly. Executors are capable of running asynchronous tasks and typically manage a pool of threads, so we don’t have to create new threads manually. All threads of the internal pool will be reused under the hood for revenant tasks, so we can run as many concurrent tasks as we want throughout the life-cycle of our application with a single executor service.
 *   		The class Executors provides convenient factory methods for creating different kinds of executor services. 
 *   		- Executors.newSingleThreadExecutor() - n executor with a thread pool of size one.
 *  		 An ExecutorService provides two methods for that purpose: shutdown() waits for currently running tasks to finish while shutdownNow() interrupts all running tasks and shut the executor down immediately.
 *  
 *  		executor.shutdown(), executor.shutdownNow()
 *  		executor.isTerminated() -> Returns true if all tasks have completed following shut down. Note that isTerminated is never true unless either shutdown or shutdownNow was called first.
 *  		executor.awaitTermination(5, TimeUnit.SECONDS);  
 *  		executor.isShutdown(); - > Returns true if this executor has been shut down.
 *  		Callables -> In addition to Runnable executors support another kind of task named Callable. Callables are functional interfaces just like runnables but instead of being void they return a value.  	
 *  		Futures -> Since submit() doesn’t wait until the task completes, the executor service cannot return the result of the callable directly. Instead the executor returns a special result of type Future which can be used to retrieve the actual result at a later point in time.
 *  		Calling the method get() blocks the current thread and waits until the callable completes.
 *  		Timeouts ->  Any call to future.get() will block and wait until the underlying callable has been terminated. In the worst case a callable runs forever - thus making your application unresponsive. You can simply counteract those scenarios by passing a timeout:
 *  		TimeUnit.SECONDS.sleep(2);future.get(1, TimeUnit.SECONDS);
 *  		InvokeAll -> Executors support batch submitting of multiple callables at once via invokeAll(). This method accepts a collection of callables and returns a list of futures.
 *  		InvokeAny -> Another way of batch-submitting callables is the method invokeAny() which works slightly different to invokeAll(). Instead of returning future objects this method blocks until the first callable terminates and returns the result of that callable.
 *			Scheduled Executors -> A ScheduledExecutorService is capable of scheduling tasks to run either periodically or once after a certain amount of time has elapsed.
 *			ScheduledFuture -> Scheduling a task produces a specialized future of type ScheduledFuture which - in addition to Future - provides the method getDelay() to retrieve the remaining delay. After this delay has elapsed the task will be executed concurrently.
 *			scheduleAtFixedRate() -> capable of executing tasks with a fixed time rate, e.g. once every second.
 *			scheduleWithFixedDelay() -> works just like the counterpart described above. The difference is that the wait time period applies between the end of a task and the start of the next task.
 *		- ThreadPoolExecutor
 *			The ThreadPoolExecutor is an extensible thread pool implementation with lots of parameters and hooks for fine-tuning.
 *			The corePoolSize parameter is the number of core threads that will be instantiated and kept in the pool. When a new task comes in, if all core threads are busy and the internal queue is full, the pool is allowed to grow up to maximumPoolSize.
 *			  
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

		// -------------------------------------------------------------------------------------------------
		// Thread Pool // Executors
		System.out.println("--------------Executors.newSingleThreadExecutor-----------------");
		ExecutorService executor = Executors.newSingleThreadExecutor();
		executor.submit(() -> System.out.println("Thread Name:" + Thread.currentThread().getName()));

		// output:

		// -------------------------------------------------------------------------------------------------
		// Thread Pool executor.submit(() -> {
		System.out.println("Thread Started before sleepp 500");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) { // TODO Auto-generated
			e.printStackTrace();
		}
		System.out.println("Thread end after sleep 500");
		System.out.println("--------Attempt to shutdown executor-----------"); //
		executor.shutdown(); // Below will give the java.lang.InterruptedException:
		// sleep interrupted // executor.shutdownNow();
		try {
			executor.shutdown();
		} catch (Exception e2) {
			System.out.println("Shutdown interrupted");
		} finally {
			if (!executor.isTerminated()) {
				System.out.println("Try Shutdown again");
				executor.shutdown();
			}
			System.out.println("Finally");
		}

		// -------------------------------------------------------------------------------------------------
		// Callables and Futures
		// Callables are functional interfaces just like runnables but instead of being
		// void they return a value.
		// Since submit() doesn’t wait until the task completes, the executor service
		// cannot return the result of the callable directly. Instead the executor
		// returns a special result of type Future which can be used to retrieve the
		// actual result at a later point in time.
		Callable<String> task = () -> {
			System.out.println("Callable");
			return "Hello from Callable";
		};
		ExecutorService executor2 = Executors.newSingleThreadExecutor();
		Future<String> future = executor2.submit(task);
		System.out.println("Future completed:" + future.isDone());
		try {
			System.out.println("Future Value:" + future.get());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Future completed:" + future.isDone());
		/*
		 * Future completed:false Callable Future Value:Hello from Callable Future
		 * completed:truea
		 */

		// -------------------------------------------------------------------------------------------------
		// InvokeAll
		ExecutorService multicallableExecutor = Executors.newWorkStealingPool();
		List<Callable<String>> listOfCallable = Arrays.asList(() -> "Pandiyan", () -> "Rajan", () -> "Lion");
		try {
			multicallableExecutor.invokeAll(listOfCallable).stream().map(futures -> {
				try {
					return futures.get();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null;
			}).forEach(System.out::println);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		 * output: Pandiyan Rajan Lion
		 */

		// -------------------------------------------------------------------------------------------------
		// InvokeAny
		try {
			System.out.println(multicallableExecutor.invokeAny(listOfCallable));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// -------------------------------------------------------------------------------------------------
		// ScheduledExecutorService -> scheduleAtFixedRate
		ScheduledExecutorService shExecutorService = Executors.newScheduledThreadPool(1);
		Runnable runnableTask = () -> System.out.println("Scheduling:" + System.nanoTime());
		// shExecutorService.scheduleAtFixedRate(runnableTask, 0, 3, TimeUnit.SECONDS);
		/*
		 * output: Run continuous ->Scheduling:9944031281795 Scheduling:9947030913477
		 * Scheduling:9950031053167 Scheduling:9953030964201 Scheduling:9956030906659
		 * Scheduling:9959030957838 Scheduling:9962030915655 Scheduling:9965030989333
		 * Scheduling:9968030949792
		 */

		// -------------------------------------------------------------------------------------------------
		// ScheduledExecutorService -> scheduleWithFixedDelay
		shExecutorService.scheduleWithFixedDelay(runnableTask, 0, 1, TimeUnit.SECONDS);
		shExecutorService.shutdown();
		/*
		 * output: Scheduling:10261171697134 Scheduling:10262172397516
		 * Scheduling:10263172855592 Scheduling:10264173373362 Scheduling:10265173877917
		 * Scheduling:10266174337932 Scheduling:1026717487966
		 */

		// -------------------------------------------------------------------------------------------------
		// ThreadPoolExecutor

	}

}
