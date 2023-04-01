package dataTypes;

import java.util.Iterator;

public class MathClass {

	public static void main(String[] args) {
		// random()
		// round()
		// pow()
		// max()
		// min()
		// random() -> returns between 0 and 1
		for (int i = 1; i < 4; i++) {
			System.out.println(Math.random());
		}

		// round() -> rounds to the nearest value and returns the integer
		// if pass float return int
		// if pass double return long
		// looks the 1st digit after the decimal point
		double bi = 3.14;
		long longx = Math.round(bi);// 3

		float en = 2.71f;
		float floatx = Math.round(en);// 3

		// pow() calculate the exponential
		// only double. no other data type supported
		double num1 = 2;
		double num2 = 3;
		double exres = Math.pow(num1, num2);// 8 = 2 ^3 =2 x 2 x 2

		// min() returns smaller
		// int, long, double and float applicable
		int x = 3;
		int y = 4;
		int min = Math.min(x, y);// 3
		
		//max() return larger
		// int, long, double and float applicable
		int max = Math.max(x, y);//4

	}

}
