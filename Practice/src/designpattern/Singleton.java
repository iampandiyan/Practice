package designpattern;

/*
 * Only one object creation per JVM
 * If object has some specific properties, we should not use singleton. Ex:Student
 * If object has common properties , we can use singleton
 * Cant create a object using new keyword.
 * Used in logging, cache, session, drivers
 * 
 * Advantage:
 * Saves Memory
 * Performance improvement
 * 
 * Implementation Rule:
 * Private constructor
 * Public getInstance method
 * Private static instance type 
 * 
 * Initialization Type:
 * Eager initiation() - create object private static Singleton singleton= new Singleton(); return singleton.
 * 
 * Lazy Initialization - create object whenever need it as this class. 
 * 
 * Thread Safe Method Initialization
 * SWhen 2 threads trying to access the class at the same time, it may create a 2 object because obj is null. To avoid this,add synchronized getinstance method. 
 *  
 * Thread safe block initialization
 * Instead of adding synchronized to method, we can add synchronized to the bloc where we are creating the new object. synchronized(Singleton.class)
 * Java inbuild Singleton class: Runtime
 * 
 * 
 */
public class Singleton {

	// Create a static 
	private static Singleton singleton;
	
	private String url="iampandiyan.blogspot.com";

	//Create a private constructor
	private Singleton() {
		 
	}
	
	public static Singleton getInstance() {
		if(singleton==null) {
			singleton=new Singleton();
		}
		return singleton;		
	}
	
	public void displayUrl() {
		System.out.println(url);
	}
}
