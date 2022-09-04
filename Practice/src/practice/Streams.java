package practice;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

/*
 * Using stream, you can process data in a declarative way similar to SQL statements. For example, consider the following SQL statement.
 * SELECT max(salary), employee_id, employee_name FROM Employee
 * The above SQL expression automatically returns the maximum salaried employee's details, without doing any computation on the developer's end. 
 * Using collections framework in Java, a developer has to use loops and make repeated checks. 
 * Another concern is efficiency; as multi-core processors are available at ease, a Java developer has to write parallel code processing that can be pretty error-prone.
 * To resolve such issues, Java 8 introduced the concept of stream that lets the developer to process data declaratively and leverage multicore architecture without the need to write any specific code for it.
 * 
 * What is Stream?
 * 		Stream represents a sequence of objects from a source, which supports aggregate operations. Following are the characteristics of a Stream 
 * 			- Sequence of elements − A stream provides a set of elements of specific type in a sequential manner. A stream gets/computes elements on demand. It never stores the elements.
 * 			- Source − Stream takes Collections, Arrays, or I/O resources as input source.
 * 			- Aggregate operations − Stream supports aggregate operations like filter, map, limit, reduce, find, match, and so on.
 * 			- Pipelining − Most of the stream operations return stream itself so that their result can be pipelined. These operations are called intermediate operations and their function is to take input, process them, and return output to the target. collect() method is a terminal operation which is normally present at the end of the pipelining operation to mark the end of the stream.
 * 			- Automatic iterations − Stream operations do the iterations internally over the source elements provided, in contrast to Collections where explicit iteration is required.
 * Generating Streams
 * 		- stream() − Returns a sequential stream considering collection as its source.
 * 		- parallelStream() − Returns a parallel Stream considering collection as its source.
 *
 */

public class Streams {

	public static void main(String[] args) {
		List<String> namesList = Arrays.asList("John", "Mathew", "Judas", "Mark", "Luke", "Peter");

		// -------------------------------------------------------------------------------------------------
		// foreach -> to iterate each element of the stream.
		namesList.stream().forEach(System.out::println);
		/*
		 * output: John Mathew Judas Mark Luke Peter
		 */

		// -------------------------------------------------------------------------------------------------
		// filter -> to eliminate elements based on a criteria.
		// map -> to map each element to its corresponding result.
		// Collectors are used to combine the result of processing on the elements of a
		// stream. Collectors can be used to return a list or a string.
		List<String> namesStartswithM = namesList.stream().filter(name -> name.startsWith("M")).map(name -> name)
				.collect(Collectors.toList());
		System.out.println("Names starts with M (List): " + namesStartswithM);
		String namesStartsWithMString = namesList.stream().filter(name -> name.startsWith("M"))
				.collect(Collectors.joining(","));
		System.out.println("Names starts with M (String): " + namesStartsWithMString);
		/*
		 * output: Names starts with M (List): [Mathew, Mark] Names starts with M
		 * (String): Mathew,Mark
		 */

		// -------------------------------------------------------------------------------------------------
		// filter and count -> to get the count of the elements
		long count = namesList.stream().filter(name -> name.startsWith("M")).count();
		System.out.println("Count of names starts with M: " + count);
		/*
		 * output: Count of names starts with M: 2
		 */

		// -------------------------------------------------------------------------------------------------
		// limit -> to reduce the size of the stream.
		namesList.stream().limit(3).forEach(System.out::println);
		/*
		 * output: John Mathew Judas
		 */

		// -------------------------------------------------------------------------------------------------
		// sorted -> to sort the stream.
		namesList.stream().sorted().forEach(System.out::println);
		/*
		 * output: John Judas Luke Mark Mathew Peter
		 */

		// -------------------------------------------------------------------------------------------------
		// statistics -> statistics collectors are introduced to calculate all
		// statistics when stream processing is being done.
		List<Integer> numbers = Arrays.asList(8, 4, 5, 3, 9, 21, 10);
		IntSummaryStatistics statics = numbers.stream().mapToInt(num -> num).summaryStatistics();
		System.out.println("Max Number: " + statics.getMax());
		System.out.println("Min Number: " + statics.getMin());
		System.out.println("Sum Number: " + statics.getSum());
		System.out.println("Average Number: " + statics.getAverage());
		/*
		 * output: Max Number: 21 Min Number: 3 Sum Number: 60 Average Number:
		 * 8.571428571428571
		 */

		//
	}

}
