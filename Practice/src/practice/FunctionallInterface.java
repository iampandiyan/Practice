package practice;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntConsumer;
import java.util.function.IntFunction;
import java.util.function.IntPredicate;
import java.util.function.IntSupplier;
import java.util.function.Predicate;
import java.util.function.Supplier;

/*
 *  Functional Interface
 *  	An Interface that contains exactly one abstract method is known as functional interface.
 *  	It can have any number of default, static methods but can contain only one abstract method.
 *  	It can also declare methods of object class.
 *  	It is also known as Single Abstract Method Interfaces or SAM Interfaces.
 *  	There is no such need for the @FunctionalInterface annotation as it is voluntary only. This is written because it helps in checking the compiler level. Besides this, it is optional.
 *  	A functional interface can extends another interface only when it does not have any abstract method.
 *  	
 *  	Java provides predefined functional interfaces to deal with functional programming by using lambda and method references.
 *  	List of functional interface which are placed in java.util.function package.
 *  		    Runnable –> This interface only contains the run() method.
    			Comparable –> This interface only contains the compareTo() method.
    			ActionListener –> This interface only contains the actionPerformed() method.
    			Callable –> This interface only contains the call() method.
    			Consumer -> Bi-Consumer -> contains accept() method -> No return value 
    			Predicate -> Bi-Predicate -> contains test() method -> takes one or two arguments, does some processing, and returns the boolean value.
    			Function -> Bi-Function, Unary Operator, Binary Operator -> contains apply() method -> receives one or two arguments and returns a value after the required processing.
    			Supplier -> BooleanSupplier, DoubleSupplier, LongSupplier, and IntSupplier. -> contains get() method -> it takes no arguments and returns a result. -> 
 *  	
 *  
 *  As per https://docs.oracle.com/javase/8/docs/api/java/util/function/package-summary.html
 *  BiConsumer<T,U> -> Represents an operation that accepts two input arguments and returns no result.
 *  BiFunction<T,U,R> -> Represents a function that accepts two arguments and produces a result.
 *  BinaryOperator<T> -> Represents an operation upon two operands of the same type, producing a result of the same type as the operands.
 *  BiPredicate<T,U> -> Represents a predicate (boolean-valued function) of two arguments.
 *  BooleanSupplier -> Represents a supplier of boolean-valued results.
 *  Consumer<T> -> Represents an operation that accepts a single input argument and returns no result.
 *  DoubleBinaryOperator -> Represents an operation upon two double-valued operands and producing a double-valued result.
 *  DoubleConsumer -> Represents an operation that accepts a single double-valued argument and returns no result.
 *  DoubleFunction<R> -> Represents a function that accepts a double-valued argument and produces a result.
 *  DoublePredicate -> Represents a predicate (boolean-valued function) of one double-valued argument.
 *  DoubleSupplier -> Represents a supplier of double-valued results.
 *  DoubleToIntFunction -> Represents a function that accepts a double-valued argument and produces an int-valued result.
 *  DoubleToLongFunction -> Represents a function that accepts a double-valued argument and produces a long-valued result.
 *  DoubleUnaryOperator -> Represents an operation on a single double-valued operand that produces a double-valued result.
 *  Function<T,R> -> Represents a function that accepts one argument and produces a result.
 *  IntBinaryOperator -> Represents an operation upon two int-valued operands and producing an int-valued result.
 *  IntConsumer -> Represents an operation that accepts a single int-valued argument and returns no result.
 *  IntFunction<R> -> Represents a function that accepts an int-valued argument and produces a result.
 *  IntPredicate -> Represents a predicate (boolean-valued function) of one int-valued argument.
 *  IntSupplier -> Represents a supplier of int-valued results.
 *  IntToDoubleFunction -> Represents a function that accepts an int-valued argument and produces a double-valued result.
 *  IntToLongFunction -> Represents a function that accepts an int-valued argument and produces a long-valued result.
 *  IntUnaryOperator -> Represents an operation on a single int-valued operand that produces an int-valued result.
 *  LongBinaryOperator -> Represents an operation upon two long-valued operands and producing a long-valued result.
 *  LongConsumer -> Represents an operation that accepts a single long-valued argument and returns no result.
 *  LongFunction<R> -> Represents a function that accepts a long-valued argument and produces a result.
 *  LongPredicate -> Represents a predicate (boolean-valued function) of one long-valued argument.
 *  LongSupplier -> Represents a supplier of long-valued results.
 *  LongToDoubleFunction -> Represents a function that accepts a long-valued argument and produces a double-valued result.
 *  LongToIntFunction -> Represents a function that accepts a long-valued argument and produces an int-valued result.
 *  LongUnaryOperator -> Represents an operation on a single long-valued operand that produces a long-valued result.
 *  ObjDoubleConsumer<T> -> Represents an operation that accepts an object-valued and a double-valued argument, and returns no result.
 *  ObjIntConsumer<T> -> Represents an operation that accepts an object-valued and a int-valued argument, and returns no result.
 *  ObjLongConsumer<T> -> Represents an operation that accepts an object-valued and a long-valued argument, and returns no result.
 *  Predicate<T> -> Represents a predicate (boolean-valued function) of one argument.
 *  Supplier<T> -> Represents a supplier of results.
 *  ToDoubleBiFunction<T,U> -> Represents a function that accepts two arguments and produces a double-valued result.
 *  ToDoubleFunction<T> -> Represents a function that produces a double-valued result.
 *  ToIntBiFunction<T,U> -> Represents a function that accepts two arguments and produces an int-valued result.
 *  ToIntFunction<T> -> Represents a function that produces an int-valued result.
 *  ToLongBiFunction<T,U> -> Represents a function that accepts two arguments and produces a long-valued result.
 *  ToLongFunction<T> -> Represents a function that produces a long-valued result.
 *  UnaryOperator<T> -> Represents an operation on a single operand that produces a result of the same type as its operand.
 *   */

@FunctionalInterface
interface FunctionalInInterface {
	void welcome(); // only one abstract method
	// It can contain any number of Object class methods.

	int hashCode();

	String toString();

	boolean equals(Object obj);
}

interface NormalInterfaceWithAbstractMethod {
	void welcome();
}

@FunctionalInterface
interface FunctionalInterfaceExtendsNormalInterfaceWithAbstractMethod extends NormalInterfaceWithAbstractMethod {
	// Invalid Functional Interface
	// Note: A functional interface can extends another interface only when it does
	// not have any abstract method.
	// If we uncomment below, we will get the compilation error
	// void welcomeAgain();
}

interface NormalInterfaceWithoutAbstractMethod {
	default void welcome() {
		System.out.println("Welcome all of you from NormalInterfaceWithoutAbstractMethod");
	}
}

@FunctionalInterface
interface FunctionalInterfaceExtendsNormalInterfaceWithoutAbstractMethod extends NormalInterfaceWithoutAbstractMethod {
	// Note: A functional interface can extends another interface only when it does
	// not have any abstract method.
	void welcomeAgain();
}

class FunctionalInterfaceClass implements FunctionalInInterface {

	@Override
	public void welcome() {
		System.out.println("Welcome all of you from Functional Interface");
	}

}

public class FunctionallInterface {

	public static void main(String[] args) {

		// -------------------------------------------------------------------------------------------------
		// FunctionalInterfaceClass
		FunctionalInterfaceClass functionalInterfaceClass = new FunctionalInterfaceClass();
		functionalInterfaceClass.welcome();
		/*
		 * output: Welcome all of you from Functional Interface
		 */

		// -------------------------------------------------------------------------------------------------
		// Consumer -> Accepts single argument -> No return value -> has accept() method
		Consumer<String> consumer = name -> System.out.println("Hello " + name);
		consumer.accept("Pandiyan");
		/*
		 * output: Hello Pandiyan
		 */

		// -------------------------------------------------------------------------------------------------
		// IntConsumer -> Accepts single int argument -> No return value -> has accept()
		// method
		IntConsumer intConsumer = age -> System.out.println("Age is: " + age);
		intConsumer.accept(33);
		/*
		 * output: Age is: 33
		 */

		// -------------------------------------------------------------------------------------------------
		// Bi-Consumer -> Accepts two argument -> No return value -> has accept() method
		BiConsumer<String, Integer> biConsumer = (name, age) -> System.out.println("Name: " + name + " Age:" + age);
		biConsumer.accept("Pandiyan", 33);
		/*
		 * output: Name: Pandiyan Age:33
		 */

		// -------------------------------------------------------------------------------------------------
		// Predicate -> Accepts single argument -> return boolean -> has test() method
		Predicate<String> predicate = name -> name.startsWith("P");
		System.out.println("Pandiyan starts with P ? " + predicate.test("Pandiyan"));
		System.out.println("Rajan starts with P ? " + predicate.test("Rajan"));
		/*
		 * output: Pandiyan starts with P ? true Rajan starts with P ? false
		 */

		// -------------------------------------------------------------------------------------------------
		// IntPredicate -> Accepts single int argument -> return boolean -> has test()
		// method
		IntPredicate intPredicate = num -> num > 10;
		System.out.println("11 > 10 ? " + intPredicate.test(11));
		System.out.println("9 > 10 ? " + intPredicate.test(9));
		/*
		 * output: 11 > 10 ? true 9 > 10 ? false
		 */

		// -------------------------------------------------------------------------------------------------
		// Bi-Predicate -> Accepts two argument -> return boolean -> has test()
		// method
		BiPredicate<String, Integer> biPredicate = (name, age) -> name.startsWith("P") && age > 10;
		System.out.println("Pandiyan/33 starts with P and age > 10 ? " + biPredicate.test("Pandiyan", 33));
		System.out.println("Pandiyan/9 starts with P and age > 10 ? " + biPredicate.test("Pandiyan", 9));
		System.out.println("Rajan/33 starts with P and age > 10 ? " + biPredicate.test("Rajan", 33));
		/*
		 * output: Pandiyan/33 starts with P and age > 10 ? true Pandiyan/9 starts with
		 * P and age > 10 ? false Rajan/33 starts with P and age > 10 ? false
		 */

		// -------------------------------------------------------------------------------------------------
		// Function -> Accepts single argument -> return value -> has apply() method
		Function<String, String> function = name -> "Hello " + name;
		System.out.println(function.apply("Pandiyan"));
		/*
		 * output: Hello Pandiyan
		 */

		// -------------------------------------------------------------------------------------------------
		// Function -> Accepts single int argument -> return value -> has apply() method
		IntFunction<String> intFunction = age -> "Age is: " + age;
		System.out.println(intFunction.apply(33));
		/*
		 * output: Age is: 33
		 */

		// -------------------------------------------------------------------------------------------------
		// BiFunction -> Accepts two argument -> return value -> has apply() method
		BiFunction<String, Integer, String> biFunction = (name, age) -> name + " is " + age + " years old.";
		System.out.println(biFunction.apply("Pandiyan", 33));
		/*
		 * output: Pandiyan is 33 years old.
		 */

		// -------------------------------------------------------------------------------------------------
		// BinaryOperator -> operation upon two operands of the same data type. It
		// returns a result of the same type as the operands. -> has apply() method
		BinaryOperator<Integer> binaryOperator = (a, b) -> a + b;
		System.out.println("Addition 5+11= " + binaryOperator.apply(5, 11));
		/*
		 * output: Addition 5+11= 16
		 */

		// -------------------------------------------------------------------------------------------------
		// Supplier -> Accepts no argument -> return value -> has get() method
		Supplier<String> supplier = () -> "Welcome all of you";
		System.out.println(supplier.get());
		/*
		 * output: Welcome all of you
		 */

		// -------------------------------------------------------------------------------------------------
		// Supplier -> Accepts no argument -> return int value -> has getAsInt() method
		IntSupplier intSupplier = () -> 10;
		System.out.println("Always return : " + intSupplier.getAsInt());
		/*
		 * output: Always return : 10
		 */
	}

}
