package dataTypes;

public class ArithmaticOperators {

	public static void main(String[] args) {
		// Precedence
		// PUMA is a REBL TA
		// P - Pre/post Unary
		// U - Unary
		// M - Multiplicative
		// A- Additttive
		// R - Relational
		// E - Equaility
		// B - Bitwise
		// L - logical
		// T - Ternary
		// A - Assignement
		// Post Unary expr++ expr--
		// Pre-Unary ++expr --expr
		// other unary +expr -expr !expr
		// Multiplicative * / %
		// Additive + -
		// Relational < > <= >= instanceof
		// Equality == !=
		// Logical & ^ |
		// Logical(short-circuit) && ||
		// Ternary expr?expr:expr;
		// Assignement = += -+ *= /= %=

		// Any Byte or short variable used for arithmatic operation automatically
		// promoted to intss

		byte result = 5 + 3;// =8
		byte a = 5;
		byte b = 3;
		// result=a+b;//error compiler will return int => int(a)+int(b)
		int intResult = a + b;// need to store the value in int variable

		// Any primitive variables involved in an arithmatic operation always promoted
		// to the largetst type before the calculation is executed.
		short srtA = 3;
		int intA = 5;
		long longA = 9;

		// intResult=srtA+intA+longA;//error. compiler will return long (long)srtA +
		// (long)intA + longA;
		long longResult = srtA + intA + longA;

		// Pre and Post Unary Operators
		// Above is not the case for pre and post unary operation
		int xInt = 1;
		++xInt;// xInt=2
		--xInt;// xInt=1

		double xDouble = 3.14;
		++xDouble;// xDouble=4.14
		--xDouble;// xDouble=3.14

		int x = 4;
		int y = 7;
		int z = 3;

		int re = ++x + y + --z;
		// re = 5 + 7 + 2 =14
		System.out.println("re=" + re);
		x = 5;
		re = ++x + x + ++x;
		// re = 6 + 6 + 7 = 19
		x = 1;
		re = x++ + x + x++;
		// re = 1 + 2 + 2 = 5

		// Assignment Operators
		int ax = 5;
		int ay = 3;
		int az = ax + ay; // 8

		ax = 5;
		ay = 3;
		az = 5 + (ay = ax + ay); // 13,, ay=8

		boolean aflag = false;
		int fz = 10;
		if (aflag = true) {
			fz = 5;
		} else {
			fz = 2;
		}
		// Z = 5

		// COmpount Assignement Operators
		// += -= *= /= %=
		int ca = 5;
		int cb = 3;
		ca *= ca; // 15a

		byte bb = 5;
		double bd = 3.0;

		bb += bd;
		bb = (byte) (bb + bd);

		// component assignment wide or cast result to the assigned type.a

		// COmarision or Relational operators
		// == != < <= > >= instanceof . total 7
		// < <= > >= can be used only with numbers
		// == != can be used with Primitive numbers, Object reference and booleans
		// instanceof can be used with obect instance
		class ClasA {
		}
		;
		class ClasB extends ClasA {
		}
		;
		ClasA instanceA = new ClasA();
		ClasB instanceB = new ClasB();

		boolean isInstance;
		isInstance = (instanceA instanceof ClasA);// true
		isInstance = (instanceB instanceof ClasA);// true
		isInstance = (instanceA instanceof ClasB);// false

		// Logical Operators
		// & && | || ^ !
		// & -> Both side must be true. Both side should be tested.
		// && -> Both side must be true. if first one is false, it wont test the next
		// one.
		// | -> Atleast one side must be true. Both side should be tested.
		// || -> Atleaset side must be true. if first one is true, it wont test the next
		// one.

		x = 5;
		boolean xbool = false;
		boolean res = (x++ <= 5) || (xbool = true);
		// res=true, x=6,, xbool=false

		// ^ one side must be true. other side must be false.
		// ! reverse the boolean

		// Ternary operator expr ? trueExpr : falseExpr;

		// unary positive or negative operators

		int l = 5 * -3; // -15
		x = 5;
		y = -x * -3 + +x; // -5 * -3 + 5 = 20
		x = 5;
		y = --x * -3 + ++x;// 4 * -3 + 5 = -7

		// Bitwise operators --> not appplicable for ava exam.
		// &= ^= |= <<= >>= >>>=

		// Precedence
		// Post Unary expr++ expr--
		// Pre-Unary ++expr --expr
		// other unary +expr -expr !expr
		// Multiplicative * / %
		// Additive + -
		// Relational < > <= >= instanceof
		// Equality == !=
		// Logical & ^ |
		// Logical(short-circuit) && ||
		// Ternary expr?expr:expr;
		// Assignement = += -+ *= /= %=

		x = 3;
		y = 4;
		z = x++ + y + --y + x; // 3 + 4 + 3 + 4 =14

		System.out.println("z=" + z);

		x = 3;
		y = 4;
		z = --x + -x + +y--;// 2 + -2 + 4 = 4

		x = 3;
		y = 4;
		z = x * x++ + y - y / x;// 3 * 3 + 4 - 4 / 4 = 9 + 4 - 1 = 12

		x = 3;
		y = 4;
		boolean zBool;
		zBool = y + x * x > y & y != ++x;// 4 + 3 * 3> 4 & 4 != 4 = 13 > 4 != 4 =false

		boolean r = true;
		boolean m = false;

		zBool = r && m ^ r || m | r; // r && (m ^ r) || (m | r) true

		x = 3;
		y = 6;
		z = 2;

		z *= y / x - y - x > y ? 4 : 2; // 2 - 6 - 3 > 6 ? 4 :2 => z *=2 = 4

		x = 3;
		y = 4;
		z = --x * x + y + 8; // 2 * 2 + 4 + 8 = 16

		x = 3;
		y = 4;
		z = --x * (x + y + 8); // 2 * (2 + 4 + 8)=28

		x = 3;
		y = 4;
		z = 0;
		z = (--x * x + (y + x) - y--);
		System.out.println("-----------");
		System.out.println(z);// 6

		x = 3;
		y = 4;
		z = 0;
		z = --x * ((x + y) + x) - y--;
		System.out.println("-----------");
		System.out.println(z);// 12

		x = 3;
		y = 4;
		z = 0;
		z = (--x * x + y + (x - y--));
		System.out.println("-----------");
		System.out.println(z);// 6

	}

}
