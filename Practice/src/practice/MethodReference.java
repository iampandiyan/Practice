package practice;
/*
 * Method reference is used to refer method of functional interface(Interface should have only one unimplemented method).
 * Instead of creating a new method for interface using lambda, we can refer the existing method of the any class
 * Each time when you are using lambda expression to just referring a method, you can replace your lambda expression with method reference.
 * 
 * Syntax:
 * 1. Reference to a Static Method -> ContainingClass::staticMethodName  
 * 		Note:Here we are not creating the object using new keyword because static method can be accessed directly from class.
 * 2. Reference to an Instance Method -> ContainingObject::instanceMethodName  ==> new ContainingClass()::instanceMethodName
 * 		Note:Here we need to create the object for the class. 
 * 3. Reference to a Constructor ->     ClassName::new  
 * 		Note: Constructor method will be accessed using the new keyword
 */

interface LambdaVsMethodReferenceInterface {
	public String welcome();
}

class MethodReferenceClass {

	public MethodReferenceClass() {

	}

	public String welcomeFromMethodReference() {
		return "Welcome All of you from Method Reference implementation";
	}

	public String welcomeFromInstanceMethod() {
		return "Welcome All of you from Method Reference: Instance Method";
	}

	public static String welcomeFromStaticMethod() {
		return "Welcome All of you from Method Reference: Static Method";
	}
}

interface ConstructorMethodInterface {
	ConstructorMethodClass welcome();
}

class ConstructorMethodClass {
	ConstructorMethodClass() {
		System.out.println("Welcome All of you from Method Reference: Constructor Method");
	}
}

public class MethodReference {

	public static void main(String[] args) {

		// -------------------------------------------------------------------------------------------------
		// Lambda Vs Method Reference Example -> Lambda implementation
		LambdaVsMethodReferenceInterface lambdaImplementation = () -> "Welcome All of you from lambda implementation";
		System.out.println(lambdaImplementation.welcome());
		/*
		 * output: Welcome All of you from lambda implementation
		 */

		// -------------------------------------------------------------------------------------------------
		// Lambda Vs Method Reference Example -> Method Reference implementation.
		LambdaVsMethodReferenceInterface methodReferenceImplementation = new MethodReferenceClass()::welcomeFromMethodReference;
		System.out.println(methodReferenceImplementation.welcome());
		/*
		 * output: Welcome All of you from Method Reference implementation
		 */

		// -------------------------------------------------------------------------------------------------
		// Accessing the static method. Here we are not creating the object for class
		// using new keyword because static method can be accessed directly from class.
		LambdaVsMethodReferenceInterface staticMethodReference = MethodReferenceClass::welcomeFromStaticMethod;
		System.out.println(staticMethodReference.welcome());
		/*
		 * output: Welcome All of you from Method Reference: Static Method
		 */

		// -------------------------------------------------------------------------------------------------
		// Accessing the instance method. Here we are creating the object for class
		// using new keyword.
		LambdaVsMethodReferenceInterface instanceMethodReference = new MethodReferenceClass()::welcomeFromInstanceMethod;
		System.out.println(instanceMethodReference.welcome());
		/*
		 * output: Welcome All of you from Method Reference: Instance Method
		 */

		// -------------------------------------------------------------------------------------------------
		// Accessing the Constructor method.
		ConstructorMethodInterface constructorMethodReference = ConstructorMethodClass::new;
		constructorMethodReference.welcome();
		/*
		 * output: Welcome All of you from Method Reference: Constructor Method
		 */

	}

}
