package practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
 *Key Words
 *		- filter -> to eliminate elements based on a criteria. -> Predicate argument will be passed. -> filter(Predicate)
 *		- limit -> to reduce the size of the stream. -> int argument will be passed -> limit(3)
 *		- sorted -> to sort the stream. -> sorted()
 *		- findAny -> returns any element from a Stream
 *		- findFirst -> returns the first element in a Stream. 
 *		- range -> IntStream.range(1,5) generates a stream of ‘1,2,3,4’ of type int.
 *		- rangeClosed -> LongStream.rangeClosed(1,5) generates a stream of ‘1,2,3,4,5’ of type long.
 *		- IntStream, LongStream, DoubleStream, and Stream.
 *		- forEach-> iterating each element
 *		- allMatch -> If each and every element satisfies the given Predicate then it returns true otherwise false.
 *		- anyMatch -> If any element matches then it returns true otherwise false.
 *		- noneMatch -> if none of element of stream matches the given Predicate, then it returns true otherwise false. 
 *		- map -> to map each element to its corresponding result.-> Function argument will be passed -> map(Function)
 *		- mapToInt(num -> num) -> map int element
 *		- mapToDouble(emp -> emp.getSalary()) -> map double element			
 *		- SummaryStatistics() -> Always comes after mapToint/mapToDouble -> mapToInt(num -> num).summaryStatistics() -> IntSummaryStatistics/DoubleSummaryStatistics
 *		- reduce -> operation applies a binary operator to each element in the stream where the first argument to the operator is the return value of the previous application and second argument is the current stream element. -> reduce(0.0f, (sum, salary) -> sum + salary)
 *		- max -> max(Comparator.comparing(Employee::getSalary))
 *		- min -> min(Comparator.comparing(Employee::getSalary))  
 *		- collect -> to combine the result of processing on the elements of a stream. -> Collectors argument will be passed -> collect(Collectors.toList)
 *		- Collectors -> to specify the what kind of output we need.
 *				=> Collectors.toList() -> return list
 *				=> Collectors.toSet() -> return set
 *				=> Collectors.toMap(emp -> emp.id, emp -> emp.getName()) -> return map
 *				=> Collectors.joining(",") -> return comma separated 
 *				=> Collectors.groupingBy(Employee::getDeptId) -> return map<Object, List<Object>> -> Ex: Map<Integer, List<Employee>> 
 *				=> Collectors.groupingBy(Employee::getDeptId, Collectors.counting()) -> return map<Object, Long> -> Ex:Map<Integer, Long> 
 *				=> Collectors.groupingBy(Employee::getDeptId, Collectors.summingDouble(Employee::getSalary) -> return map<Object, Double> -> Ex: Map<Integer, Double> 
 *				=> Collectors.groupingBy(Employee::getDeptId, Collectors.maxBy(Comparator.comparing(Employee::getSalary))) -> return map<object, Optional<Object>> -> Ex: Map<Integer, Optional<Employee>> 
 *				=> Collectors.groupingBy(Employee::getDeptId, Collectors.minBy(Comparator.comparing(Employee::getSalary))) -> return map<object, Optional<object>> -> Ex: Map<Integer, Optional<Employee>>
 *				=> Collectors.groupingBy(Employee::getDeptId, Collectors.averagingDouble(Employee::getSalary)) ->map<object,Double> -> Ex: Map<Integer, Double>
 *				=> Partitioning is similar to grouping except it returns two Collections - one for elements matching the condition and one for elements not matching the condition.
 *				=> Collectors.partitioningBy(emp -> emp.salary > 8000) -> returns  The map containing the output.Keys are boolean values(true or false) and the corresponding values are lists containing elements of type T. -> Ex: Map<Boolean, List<Employee>>
 *				=> Collectors.partitioningBy(emp -> emp.salary > 8000, Collectors.counting()) ->  return Map<Boolean, Long> 
 *				
 *			
 */

class Employee {
	int id;
	String name;
	float salary;
	Integer deptId;

	public Employee(int id, String name, float salary, int deptId) {
		this.id = id;
		this.name = name;
		this.salary = salary;
		this.deptId = deptId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getSalary() {
		return salary;
	}

	public void setSalary(float salary) {
		this.salary = salary;
	}

	public int getDeptId() {
		return deptId;
	}

	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}

	public String toString() {
		return "Dept id:" + deptId + " Name:" + name + " Salary:" + salary;
	}

}

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
		// list => stream => filter => map => collect => Collectors.toList
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
		// list => stream => filter => count
		long count = namesList.stream().filter(name -> name.startsWith("M")).count();
		System.out.println("Count of names starts with M: " + count);
		/*
		 * output: Count of names starts with M: 2
		 */

		// -------------------------------------------------------------------------------------------------
		// limit -> to reduce the size of the stream.
		// list => stream => limit
		namesList.stream().limit(3).forEach(System.out::println);
		/*
		 * output: John Mathew Judas
		 */

		// -------------------------------------------------------------------------------------------------
		// sorted -> to sort the stream.
		// list => stream => sorted
		namesList.stream().sorted().forEach(System.out::println);
		/*
		 * output: John Judas Luke Mark Mathew Peter
		 */

		// -------------------------------------------------------------------------------------------------
		// statistics -> statistics collectors are introduced to calculate all
		// statistics when stream processing is being done.
		// list => stream => mapToInt => summaryStatistics
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

		// -------------------------------------------------------------------------------------------------
		// Using Employee Object
		List<Employee> employees = new ArrayList<Employee>();
		employees.add(new Employee(1, "David", 15000, 1));
		employees.add(new Employee(2, "Samuel", 4000, 1));
		employees.add(new Employee(3, "Abraham", 17000, 2));
		employees.add(new Employee(4, "Isac", 5000, 2));
		employees.add(new Employee(5, "Jacob", 20000, 3));

		// -------------------------------------------------------------------------------------------------
		// print name of employee who has greater than 5000 salary ->
		// stream-filter-map-collect-Collectors.joining
		// list => stream => filter => map => collect => Collectors.joining
		String fiveThousands = employees.stream().filter(emp -> emp.salary > 5000)
				.map(emp -> emp.name + " : " + emp.salary).collect(Collectors.joining(","));
		System.out.println("Employees with > 5000 salary: " + fiveThousands);
		/*
		 * output: Employees with > 5000 salary: David : 15000.0,Abraham : 17000.0,Jacob
		 * : 20000.0
		 */

		// -------------------------------------------------------------------------------------------------
		// print name of employee who has greater than 5000 salary ->
		// stream-filter-foreach
		// list => stream => filter => forEach
		employees.stream().filter(emp -> emp.salary > 5000)
				.forEach(emp -> System.out.println(emp.name + ": " + emp.salary));
		/*
		 * output: David: 15000.0 Abraham: 17000.0 Jacob: 20000.0
		 */

		// -------------------------------------------------------------------------------------------------
		// Find sum of salary of all employees
		// Many times, we need to perform operations where a stream reduces to single
		// resultant value, for example, maximum, minimum, sum, product, etc. Reducing
		// is the repeated process of combining all elements.
		// reduce operation applies a binary operator to each element in the stream
		// where the first argument to the operator is the return value of the previous
		// application and second argument is the current stream element.
		// list => stream => map => reduce
		Float sumOfSalary = employees.stream().map(emp -> emp.salary).reduce(0.0f, (sum, salary) -> sum + salary);
		System.out.println("Total Salary: " + sumOfSalary);
		/*
		 * output: Total Salary: 61000.0
		 */

		// -------------------------------------------------------------------------------------------------
		// Find Max salary of all employees
		// list => stream => map => reduce
		Float maxSalry = employees.stream().map(emp -> emp.salary).reduce(0.0f,
				(max, salary) -> max < salary ? salary : max);

		// list => stream => max => Comparator => Comparing
		Employee maxSalryEmp = employees.stream().max(Comparator.comparing(Employee::getSalary)).get();

		System.out.println("Max Salary: " + maxSalry);
		System.out.println("Max Salary Employee: " + maxSalryEmp.name + " - " + maxSalryEmp.salary);
		/*
		 * output: Max Salary: 20000.0 Max Salary Employee: Jacob - 20000.0
		 */

		// -------------------------------------------------------------------------------------------------
		// Find Min salary of all employees
		// list => stream => map => reduce
		Float minSalry = employees.stream().map(emp -> emp.salary).reduce(0.0f,
				(min, salary) -> min > salary ? salary : min);

		System.out.println("Min Salary: Always return Zero:" + minSalry);

		// list => stream => min => Comparator => comparing
		Employee minSalaryEmp = employees.stream().min(Comparator.comparing(Employee::getSalary)).get();
		System.out.println("Min Salary Employee: " + minSalaryEmp.name + " - " + minSalaryEmp.salary);
		/*
		 * output: Min Salary: Always return Zero:0.0 Min Salary Employee: Samuel -
		 * 4000.0
		 */

		// -------------------------------------------------------------------------------------------------
		// Find min, max, Avg,, sum salary of all employees using
		// DoubleSummaryStatistics
		// list => stream => mapToDouble => summaryStatistics
		DoubleSummaryStatistics doubleSummaryStatistics = employees.stream().mapToDouble(emp -> emp.getSalary())
				.summaryStatistics();
		System.out.println("Min Salary: " + doubleSummaryStatistics.getMin());
		System.out.println("Max Salary: " + doubleSummaryStatistics.getMax());
		System.out.println("Avg Salary: " + doubleSummaryStatistics.getAverage());
		System.out.println("Sum Salary: " + doubleSummaryStatistics.getSum());
		/*
		 * output: Min Salary: 4000.0 Max Salary: 20000.0 Avg Salary: 12200.0 Sum
		 * Salary: 61000.0
		 */

		// -------------------------------------------------------------------------------------------------
		// list of employee for each department
		// Collectors.groupingBy
		// list => stream => collect -> Collectors => groupingBy(variable)
		Map<Integer, List<Employee>> listOfEmployeePerDept = employees.stream()
				.collect(Collectors.groupingBy(Employee::getDeptId));
		System.out.println("Employee for each department: " + listOfEmployeePerDept.toString());

		/*
		 * output: Employee for each department: {1=[Dept id:1 Name:David
		 * Salary:15000.0, Dept id:1 Name:Samuel Salary:4000.0], 2=[Dept id:2
		 * Name:Abraham Salary:17000.0, Dept id:2 Name:Isac Salary:5000.0], 3=[Dept id:3
		 * Name:Jacob Salary:20000.0]}
		 */

		// -------------------------------------------------------------------------------------------------
		// Number of employee for each department
		// Collectors.groupingBy
		// list => stream => collect -> Collectors => groupingBy(variable,
		// Collectors.counting()))
		Map<Integer, Long> countPerDept = employees.stream()
				.collect(Collectors.groupingBy(Employee::getDeptId, Collectors.counting()));
		System.out.println("Number of Emp per department: " + countPerDept.toString());

		/*
		 * output:Number of Emp per department: {1=2, 2=2, 3=1}
		 */

		// -------------------------------------------------------------------------------------------------
		// Sum of salary for each department
		// Collectors.groupingBy
		// list => stream => collect -> Collectors => groupingBy(variable,
		// Collectors.summingDouble(variablename)))
		Map<Integer, Double> SumOfSalaryPerDept = employees.stream()
				.collect(Collectors.groupingBy(Employee::getDeptId, Collectors.summingDouble(Employee::getSalary)));
		System.out.println("Sum of salary for each department: " + SumOfSalaryPerDept.toString());

		/*
		 * output: Sum of salary for each department: {1=19000.0, 2=22000.0, 3=20000.0}
		 */

		// -------------------------------------------------------------------------------------------------
		// Max salary emp for each department
		// Collectors.groupingBy
		// list => stream => collect -> Collectors => groupingBy(variable,
		// Collectors.maxBy(Comparator.comparing)))
		Map<Integer, Optional<Employee>> maxSalaryEmpPerDept = employees.stream().collect(Collectors
				.groupingBy(Employee::getDeptId, Collectors.maxBy(Comparator.comparing(Employee::getSalary))));
		System.out.println("Max salary emp for each department: " + maxSalaryEmpPerDept.toString());

		/*
		 * output: Max salary emp for each department: {1=Optional[Dept id:1 Name:David
		 * Salary:15000.0], 2=Optional[Dept id:2 Name:Abraham Salary:17000.0],
		 * 3=Optional[Dept id:3 Name:Jacob Salary:20000.0]}
		 */

		// -------------------------------------------------------------------------------------------------
		// Min salary emp for each department
		// Collectors.groupingBy
		// list => stream => collect -> Collectors => groupingBy(variable,
		// Collectors.minBy(Comparator.comparing)))
		Map<Integer, Optional<Employee>> minSalaryEmpPerDept = employees.stream().collect(Collectors
				.groupingBy(Employee::getDeptId, Collectors.minBy(Comparator.comparing(Employee::getSalary))));
		System.out.println("Min salary emp for each department: " + minSalaryEmpPerDept.toString());

		/*
		 * output: Min salary emp for each department: {1=Optional[Dept id:1 Name:Samuel
		 * Salary:4000.0], 2=Optional[Dept id:2 Name:Isac Salary:5000.0],
		 * 3=Optional[Dept id:3 Name:Jacob Salary:20000.0]}
		 */

		// -------------------------------------------------------------------------------------------------
		// Avg salary emp for each department
		// Collectors.groupingBy
		// list => stream => collect -> Collectors => groupingBy(variable,
		// Collectors.averagingDouble(VariableName)))
		Map<Integer, Double> avgSalaryPerDept = employees.stream()
				.collect(Collectors.groupingBy(Employee::getDeptId, Collectors.averagingDouble(Employee::getSalary)));
		System.out.println("Avg salary emp for each department: " + avgSalaryPerDept.toString());

		/*
		 * output: Avg salary emp for each department: {1=9500.0, 2=11000.0, 3=20000.0}
		 */

		// -------------------------------------------------------------------------------------------------
		// divide the Employees into 2 groups. Group1:Emp with > 8K, Group2:Emp with <8K
		// Collectors.partitioningBy
		// list => stream => collect => Collectors => partitioningBy
		Map<Boolean, List<Employee>> partionByGTeightKSalary = employees.stream()
				.collect(Collectors.partitioningBy(emp -> emp.salary > 8000));
		System.out.println("partionByGTeightKSalary => " + partionByGTeightKSalary);
		/*
		 * output: partionByGTeightKSalary => {false=[Dept id:1 Name:Samuel
		 * Salary:4000.0, Dept id:2 Name:Isac Salary:5000.0], true=[Dept id:1 Name:David
		 * Salary:15000.0, Dept id:2 Name:Abraham Salary:17000.0, Dept id:3 Name:Jacob
		 * Salary:20000.0]}
		 */

		// -------------------------------------------------------------------------------------------------
		// divide the Employees into 2 groups. and return the count. Group1:Emp with >
		// 8K, Group2:Emp with <8K
		// Collectors.partitioningBy
		// list => stream => collect => Collectors => partitioningBy
		Map<Boolean, Long> partitionCount = employees.stream()
				.collect(Collectors.partitioningBy(emp -> emp.salary > 8000, Collectors.counting()));
		System.out.println("partitionCount => " + partitionCount);
		/*
		 * output: partitionCount => {false=2, true=3}
		 */

		// -------------------------------------------------------------------------------------------------
		// Convert list into set
		// list => stream -> collect -> Collectors.toSet
		List<Integer> intList = Arrays.asList(2, 4, 6, 5, 8, 8, 9, 9, 10);
		Set<Integer> intSet = intList.stream().collect(Collectors.toSet());
		System.out.println("intList -> " + intList.toString());
		System.out.println("intSet -> " + intSet.toString());
		/*
		 * output: intList -> [2, 4, 6, 5, 8, 8, 9, 9, 10] intSet -> [2, 4, 5, 6, 8, 9,
		 * 10]
		 */

		// -------------------------------------------------------------------------------------------------
		// Convert list into map
		// list => stream -> collect -> Collectors.toMap
		Map<Integer, String> empIdName = employees.stream()
				.collect(Collectors.toMap(emp -> emp.id, emp -> emp.getName()));
		System.out.println("Emp Id and Name: " + empIdName);
		/*
		 * output: Emp Id and Name: {1=David, 2=Samuel, 3=Abraham, 4=Isac, 5=Jacob}
		 */

		// -------------------------------------------------------------------------------------------------
		// findAny
		// list => stream => findany
		Optional<Employee> anyEmp = employees.stream().findAny();
		System.out.println(anyEmp);
		/*
		 * output: Optional[Dept id:1 Name:David Salary:15000.0]
		 */

		// -------------------------------------------------------------------------------------------------
		// findfirst
		// list => stream => findany
		Optional<Employee> firstEmp = employees.stream().findFirst();
		System.out.println(firstEmp);
		/*
		 * output: Optional[Dept id:1 Name:David Salary:15000.0]
		 */

		// -------------------------------------------------------------------------------------------------
		// allMatch -> If each and every element satisfies the given Predicate then it
		// returns true otherwise false.
		// list => stream => allMatch => Predicate
		boolean allMatch = employees.stream().allMatch(emp -> emp.getId() > 1);
		System.out.println("allMatch getId>1 : " + allMatch);
		allMatch = employees.stream().allMatch(emp -> emp.getId() > 0);
		System.out.println("allMatch getId>0 : " + allMatch);
		/*
		 * output: allMatch getId>1 : false allMatch getId>0 : true
		 */

		// -------------------------------------------------------------------------------------------------
		// anyMatch -> If any element matches then it returns true otherwise false.
		// list => stream => anyMatch => Predicate
		boolean anyMatch = employees.stream().anyMatch(emp -> emp.getId() > 1);
		System.out.println("anyMatch getId>1 : " + anyMatch);
		anyMatch = employees.stream().anyMatch(emp -> emp.getId() > 10);
		System.out.println("anyMatch getId>10 : " + anyMatch);
		/*
		 * output: anyMatch getId>1 : true anyMatch getId>10 : false
		 */

		// -------------------------------------------------------------------------------------------------
		// noneMatch -> if none of element of stream matches the given Predicate, then
		// it returns true otherwise false.
		// list => stream => noneMatch => Predicate
		boolean noneMatch = employees.stream().noneMatch(emp -> emp.getId() > 1);
		System.out.println("noneMatch getId>1 : " + noneMatch);
		noneMatch = employees.stream().noneMatch(emp -> emp.getId() > 10);
		System.out.println("noneMatch getId>10 : " + noneMatch);
		/*
		 * output: noneMatch getId>1 : false noneMatch getId>10 : true
		 */

		// -------------------------------------------------------------------------------------------------
		// range and rangeClosed
		System.out.println("Range");
		IntStream.range(1, 5).forEach(System.out::print);
		System.out.println("RangeClosed");
		IntStream.rangeClosed(1, 5).forEach(System.out::print);
		/*
		 * Range 1234 RangeClosed 12345
		 */

	}

}
