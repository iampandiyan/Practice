package dataTypes;

public class NarrowingWideningCasting {

	public static void main(String[] args) {
		// Widening
		/*
		 * Smaller primitives can be automatically filled into large primitives.
		 */
		byte byteValue = 2;
		short shrtValue = byteValue;
		int intValue = shrtValue;
		long longValue = intValue;

		float floatValue = 33.3f;
		double doubleValue = floatValue;

		floatValue = intValue;
		doubleValue = longValue;

		// Narrowing
		/*
		 * Assign larger primitive to smaller primitive using typecase only if fall
		 * witin the range.
		 */

		byteValue = (byte) intValue;
		floatValue = (float) doubleValue;

		// overflow
		/*
		 * If number is too large, it will trim down to lower number. Assign larger
		 * primitive to smaller primitive if not within the range.
		 */

		//char and short
		/*
		 * both are having the same bit but range is different.
		 */
	}

}
