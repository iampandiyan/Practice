package practice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/*
 * Used To provide the implementation of Functional interface(Interface should have only one unimplemented method).
 * Functional Interface --> Lambda expression provides implementation of functional interface. An interface which has only one abstract method is called functional interface. Java provides an anotation @FunctionalInterface, which is used to declare an interface as functional interface.
 * Java lambda expression is treated as a function, so compiler does not create .class file.
 * 
 * Syntax
 * parameter -> expression body OR (argument-list) -> {body} 
 * 
 * - Argument-list: It can be empty or non-empty as well.
 * 		() -> {//Body of no parameter lambda}  OR (p1) -> {//Body of single parameter lambda} 
 * - Arrow-token: It is used to link arguments-list and body of expression.
 * - Body: It contains expressions and statements for lambda expression.
 * - Optional type declaration − No need to declare the type of a parameter. The compiler can inference the same from the value of the parameter.
 * 		(int a, int b) -> a + b; OR (a, b) -> a + b;
 * - Optional parenthesis around parameter − No need to declare a single parameter in parenthesis. For multiple parameters, parentheses are required.
 * 		a -> System.out.println("Hello " + a); AND (a, b) -> a + b;
 * - Optional curly braces − No need to use curly braces in expression body if the body contains a single statement.
 * 		(a, b) -> a + b;
 * - Optional return keyword − The compiler automatically returns the value if the body has a single expression to return the value. Curly braces are required to indicate that expression returns a value.
 * 		(a, b) -> a + b; AND 
 * 
 * No Parameter Syntax
 *     () -> {//Body of no parameter lambda} 
 * One Parameter Syntax
 *     (p1) -> {//Body of single parameter lambda} 
 * Two Parameter Syntax
 *     (p1,p2) -> {//Body of multiple parameter lambda}
 *  
 */

interface Drawable {
	public void draw();
}

interface NoParameterInterface {
	public String welcome();
}

interface SingleParameterInterface {
	public String welcome(String name);
}

interface MultipleParameterInterface {
	public String welcome(String name1, String name2);
}

class ComparatorExample {
	int id;

	public ComparatorExample(int id) {
		super();
		this.id = id;
	}
}

public class LambdaExpression {
	public static void main(String[] args) {

		// -------------------------------------------------------------------------------------------------
		// without lambda, Drawable implementation using anonymous class
		Drawable withoutLamda = new Drawable() {

			@Override
			public void draw() {
				System.out.println("Drawing without Lamda. Created the Anonymous class");
			}
		};

		withoutLamda.draw();
		/*
		 * Output: Drawing without Lamda. Created the Anonymous class
		 */

		// -------------------------------------------------------------------------------------------------
		// With lamda, we dont need to create a anonymous class
		Drawable withLamda = () -> System.out.println("Drawing with Lamda.  No need to create Anonymous class");
		withLamda.draw();
		/*
		 * Output: Drawing with Lamda. No need to create Anonymous class
		 */

		// -------------------------------------------------------------------------------------------------
		// Implementing NoParameterInterface using Lamda no return keyword and no curly
		// braces on body.
		NoParameterInterface noParameterInterfacewithoutReturn = () -> "Welcome You All";
		System.out.println(noParameterInterfacewithoutReturn.welcome());
		/*
		 * Output: Welcome You All
		 */

		// -------------------------------------------------------------------------------------------------
		// Implementing NoParameterInterface using Lamda with return keyword. When we
		// have return keyword, we need curly braces on body.
		NoParameterInterface noParameterInterfaceWithReturn = () -> {
			return "Welcome You All";
		};
		System.out.println(noParameterInterfaceWithReturn.welcome());
		/*
		 * Output: Welcome You All
		 */

		// -------------------------------------------------------------------------------------------------
		// Implementing SingleParameterInterface using Lamda- no type declaration and no
		// paranthesis on parameter AND no return keyword and no
		// curly braces on body.
		SingleParameterInterface SingleParameterInterfacewithoutReturn = name -> "Welcome " + name;
		System.out.println(SingleParameterInterfacewithoutReturn.welcome("Pandiyan"));
		/*
		 * Output: Welcome Pandiyan
		 */

		// -------------------------------------------------------------------------------------------------
		// Implementing SingleParameterInterface using Lamda- with return keyword. When
		// we have return keyword, we need curly braces on body.
		SingleParameterInterface SingleParameterInterfacewithReturn = name -> {
			return "Welcome " + name;
		};
		System.out.println(SingleParameterInterfacewithReturn.welcome("Pandiyan"));
		/*
		 * Output: Welcome Pandiyan
		 */

		// -------------------------------------------------------------------------------------------------
		// Implementing MultipleParameterInterface using Lamda- no type declaration and
		// no
		// paranthesis on parameter AND no return keyword and no
		// curly braces on body.
		MultipleParameterInterface multipleParameterInterfaceWithoutReturn = (name1, name2) -> "Welcome " + name1
				+ " AND " + name2;
		System.out.println(multipleParameterInterfaceWithoutReturn.welcome("Pandiyan", "Rajan"));
		/*
		 * Output: Welcome Pandiyan AND Rajan
		 */

		// -------------------------------------------------------------------------------------------------
		// Implementing MultipleParameterInterface using Lamda- with return keyword.
		// When
		// we have return keyword, we need curly braces on body.
		MultipleParameterInterface multipleParameterInterfacewithReturn = (name1, name2) -> {
			return "Welcome " + name1 + " AND " + name2;
		};
		System.out.println(multipleParameterInterfacewithReturn.welcome("Pandiyan", "Rajan"));
		/*
		 * Output: Welcome Pandiyan AND Rajan
		 */

		// -------------------------------------------------------------------------------------------------
		// Implementing MultipleParameterInterface using Lamda- with multiple statement
		// on body
		// When
		// we have return keyword and multiple statement, we need curly braces on body.
		MultipleParameterInterface multipleParameterInterfaceWithMultStmtOnBody = (name1, name2) -> {
			String s1 = "Hello ";
			String s2 = " Welcome to our world";
			String s3 = s1 + name1 + " AND " + name2 + s2;
			return s3;
		};
		System.out.println(multipleParameterInterfaceWithMultStmtOnBody.welcome("Pandiyan", "Rajan"));
		/*
		 * Output: Hello Pandiyan AND Rajan Welcome to our world
		 */

		// -------------------------------------------------------------------------------------------------
		// implementing Thread without Lamda
		Runnable runnableWithoutLamda = new Runnable() {

			@Override
			public void run() {
				System.out.println("Thread started without the lamda");
			}
		};
		Thread threadWithoutLamda = new Thread(runnableWithoutLamda);
		threadWithoutLamda.start();
		/*
		 * Output: Thread started without the lamda
		 */

		// -------------------------------------------------------------------------------------------------
		// implementing Thread with Lamda
		Runnable runnableWithLamda = () -> System.out.println("Thread started with the lamda");

		Thread threadWithLamda = new Thread(runnableWithLamda);
		threadWithLamda.start();
		/*
		 * Output: Thread started with the lamda
		 */

		// -------------------------------------------------------------------------------------------------
		// Comparator example with Lamda
		List<ComparatorExample> listOfObjects = new ArrayList<ComparatorExample>();
		listOfObjects.add(new ComparatorExample(5));
		listOfObjects.add(new ComparatorExample(7));
		listOfObjects.add(new ComparatorExample(3));
		listOfObjects.add(new ComparatorExample(10));
		listOfObjects.add(new ComparatorExample(9));

		System.out.print("Before Sorting: ");
		for (ComparatorExample comparatorExample : listOfObjects) {
			System.out.print(comparatorExample.id + " ");
		}

		// implemented comparator method using lamda
		Collections.sort(listOfObjects, (obj1, obj2) -> obj1.id - obj2.id);
		System.out.print("After Sorting: ");
		for (ComparatorExample comparatorExample : listOfObjects) {
			System.out.print(comparatorExample.id + " ");
		}

		/*
		 * Output: Before Sorting: 5 7 3 10 9 After Sorting: 3 5 7 9 10
		 */

		// -------------------------------------------------------------------------------------------------
		// foreach with lamda
		listOfObjects.forEach(obj -> System.out.print(" Hello " + obj.id));
		/*
		 * Output: Hello 3 Hello 5 Hello 7 Hello 9 Hello 10
		 */

	}
}
