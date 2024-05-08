package designpattern;

public class DesignPatternMain {
public static void main(String[] args) {
	//Singleton
	Singleton s=Singleton.getInstance();
	s.displayUrl();
	System.out.println("s="+s);
	Singleton s1=Singleton.getInstance();
	System.out.println("s1="+s1);
}
}
