package dataTypes;

public class Underscores {

	public static void main(String[] args) {
		// instead of comma
		long amount=1_10_000L;// compile time: _ will be removed
		//cant be used at start and end of the value
		amount=_1_100;//error
		amount=1100_;//error
		//cant use before and after the range specific
		amount=1_100L_;///error
		amount=1_100_L;//error
		//cant use before and after decimal point
		float floatamount=1_.1000000f;//error
		floatamount=1._100f;//error
		//cant use before and after the minus sign
		amount=-_100;//error
		amount=_-100;//error
	}

}
