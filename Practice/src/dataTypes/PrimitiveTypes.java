package dataTypes;

/*
 * boolean, byte, short, int, long, double, float, char => Total 8 Primitive  Data Type
 */
public class PrimitiveTypes {
	static int classVariab;

	public static void main(String[] args) {
		// Field Vs Local Variable
		/*
		 * Fields -> Class Level, Static(class) variable, instance variable, initialized
		 * by default value as below. boolean - 1 bit - False, char - 16bit - '\u0000',
		 * byte - 8bit - 0, short - 16bit - 0, int 32 bit -0, long - 64 bit - 0, float -
		 * 32 bit - 0.0,, double - 64 bit - 0.0
		 */

		/*
		 * Local Variable -> Methods/block level, never static, must be initialized
		 * before use. If we havent initialized, it will cause a compile time error.
		 */
		// Primitive Vs Objective/Reference type
		/*
		 * primitive fields and variables store the actual value whereas object fields
		 * and local variables store references/pointers to the values. reference type
		 * can be assigned with null whereas primitive field cant be assigned with null.
		 */
		int methodVariable;

		System.out.println("class variable=" + classVariab + "m ethod variable=");
		// byte 8bit
		byte maxByte = 127;
		byte minByte = -128;

		// short 16bit
		short mxShort = 32767;
		short mnShort = -32768;

		// int 32 bit
		int maxInt = 2147483647; // 2.1 Billion
		int minInt = -2147483648;

		// long 64 bit
		long maxLong = 9223372036854775807L; // 9.2 quintillion -> L is required at the end.
		long minLong = -9223372036854775808L; // used on milliseconds calculations.

		// float and double -> holds the decimal points.
		// float 32 bit
		float maxFloat = 1.40239846e-45f; // f is mandatory at the end. It will automatically round off the last digit
		float minFloat = 1.40239846e-45f; // 8 number after the decimal point.

		// double 64 bit
		double maxDouble = 1.79769313486231570e+308;
		double minDouble = 4.94065645841246544e-324;// 16 number after the decimal point.
		// integral values can be assigned to float/double but it will be truncated;
		float xf = 100;
		double xd = 100L;
		System.out.println(xf + " " + xd);

		// char 16 bit
		// used to store the unicode representation of character. Also can be used to
		// store integral in the range
		// UNicode character are made up of 16 bits

		char a = 'A';// character literal
		char unicodeA = '\u0041';// hexadecimal number for A. Unicaode literal
		char intA = 65; // char stored as a integral number.
		char minChar = 0;
		char maxchar = 65535;
		// cant assign a deciaml or double quote to char

		//Scientific Notation -> can be used only with decimal datatypes float and double.
		//float have only 8 digits and double have only 16 digits. 
		//Anything requires more digits converted into scientific notation
		double lightSpeed=5.88e12;//5,888,000,000,000
		float diameter=5.0e-8f;//0.00000005
	}

}
