package codepractise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class JavaStreamPratise {
    /*
Lambda Expressions

parameter -> expression body

Following are the important characteristics of a lambda expression.

1. Optional type declaration − No need to declare the type of a parameter. 
 The compiler can inference the same from the value of the parameter.

2. Optional parentheses around parameter − No need to declare a single parameter in parentheses. 
For multiple parameters, parentheses are required.

3. Optional curly braces − No need to use curly braces in expression body if the body contains a single statement.

4. Optional return keyword − The compiler automatically returns the 
value if the body has a single expression to return the value. 
Curly braces are required to indicate that expression returns a value.


Method References


Method references help to point to methods by their names. A method reference is described using "::" symbol. 
A method reference can be used to point the following types of methods −

Static methods
Instance methods
Constructors using new operator (TreeSet::new)


Functional interfaces

Functional interfaces have a single functionality to exhibit. For example, 
a Comparable interface with a single method ‘compareTo’ is used for comparison purpose. 
Java 8 has defined a lot of functional interfaces to be used extensively in lambda expressions. 
Following is the list of functional interfaces defined in java.util.Function package.


Default Method

Java 8 introduces a new concept of default method implementation in interfaces. 
This capability is added for backward compatibility so that old interfaces can be used to leverage the 
lambda expression capability of Java 8.
For example, ‘List’ or ‘Collection’ interfaces do not have ‘forEach’ method declaration. Thus, 
adding such method will simply break the collection framework implementations. 
Java 8 introduces default method so that List/Collection interface can have a default implementation of forEach method, 
and the class implementing these interfaces need not implement the same.

Stream:

Stream is a new abstract layer introduced in Java 8. Using stream, you can process data in a declarative way similar to SQL statements. For example, consider the following SQL statement.

SELECT max(salary), employee_id, employee_name FROM Employee
The above SQL expression automatically returns the maximum salaried employee's details, without doing any computation on the developer's end. Using collections framework in Java, a developer has to use loops and make repeated checks. Another concern is efficiency; as multi-core processors are available at ease, a Java developer has to write parallel code processing that can be pretty error-prone.

To resolve such issues, Java 8 introduced the concept of stream that lets the developer to process data declaratively and leverage multicore architecture without the need to write any specific code for it.

What is Stream?
Stream represents a sequence of objects from a source, which supports aggregate operations. Following are the characteristics of a Stream −

Sequence of elements − A stream provides a set of elements of specific type in a sequential manner. A stream gets/computes elements on demand. It never stores the elements.

Source − Stream takes Collections, Arrays, or I/O resources as input source.

Aggregate operations − Stream supports aggregate operations like filter, map, limit, reduce, find, match, and so on.

Pipelining − Most of the stream operations return stream itself so that their result can be pipelined. These operations are called intermediate operations and their function is to take input, process them, and return output to the target. collect() method is a terminal operation which is normally present at the end of the pipelining operation to mark the end of the stream.

Automatic iterations − Stream operations do the iterations internally over the source elements provided, in contrast to Collections where explicit iteration is required.

Generating Streams
With Java 8, Collection interface has two methods to generate a Stream.

stream() − Returns a sequential stream considering collection as its source.

parallelStream() − Returns a parallel Stream considering collection as its source.

List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
List<String> filtered = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
forEach
Stream has provided a new method ‘forEach’ to iterate each element of the stream. The following code segment shows how to print 10 random numbers using forEach.

Random random = new Random();
random.ints().limit(10).forEach(System.out::println);
map
The ‘map’ method is used to map each element to its corresponding result. The following code segment prints unique squares of numbers using map.

List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);

//get list of unique squares
List<Integer> squaresList = numbers.stream().map( i -> i*i).distinct().collect(Collectors.toList());
filter
The ‘filter’ method is used to eliminate elements based on a criteria. The following code segment prints a count of empty strings using filter.

List<String>strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");

//get count of empty string
int count = strings.stream().filter(string -> string.isEmpty()).count();
limit
The ‘limit’ method is used to reduce the size of the stream. The following code segment shows how to print 10 random numbers using limit.

Random random = new Random();
random.ints().limit(10).forEach(System.out::println);
sorted
The ‘sorted’ method is used to sort the stream. The following code segment shows how to print 10 random numbers in a sorted order.

Random random = new Random();
random.ints().limit(10).sorted().forEach(System.out::println);
Parallel Processing
parallelStream is the alternative of stream for parallel processing. Take a look at the following code segment that prints a count of empty strings using parallelStream.

List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");

//get count of empty string
int count = strings.parallelStream().filter(string -> string.isEmpty()).count();
It is very easy to switch between sequential and parallel streams.

Collectors
Collectors are used to combine the result of processing on the elements of a stream. Collectors can be used to return a list or a string.

List<String>strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
List<String> filtered = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());

System.out.println("Filtered List: " + filtered);
String mergedString = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.joining(", "));
System.out.println("Merged String: " + mergedString);
Statistics
With Java 8, statistics collectors are introduced to calculate all statistics when stream processing is being done.

List numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);

IntSummaryStatistics stats = integers.stream().mapToInt((x) -> x).summaryStatistics();

System.out.println("Highest number in List : " + stats.getMax());
System.out.println("Lowest number in List : " + stats.getMin());
System.out.println("Sum of all numbers : " + stats.getSum());
System.out.println("Average of all numbers : " + stats.getAverage());



     */
    interface MathOperation {
        int operation(int a, int b);
     }
      
     interface GreetingService {
        void sayMessage(String message);
     }
      
     private int operate(int a, int b, MathOperation mathOperation) {
        return mathOperation.operation(a, b);
     }
    public static void main(String[] args) {
        
        
        JavaStreamPratise tester = new JavaStreamPratise();
        
        //with type declaration
        MathOperation addition = (int a, int b) -> a + b;
          
        //with out type declaration
        MathOperation subtraction = (a, b) -> a - b;
          
        //with return statement along with curly braces
        MathOperation multiplication = (int a, int b) -> { return a * b; };
          
        //without return statement and without curly braces
        MathOperation division = (int a, int b) -> a / b;
          
        System.out.println("10 + 5 = " + tester.operate(10, 5, addition));
        System.out.println("10 - 5 = " + tester.operate(10, 5, subtraction));
        System.out.println("10 x 5 = " + tester.operate(10, 5, multiplication));
        System.out.println("10 / 5 = " + tester.operate(10, 5, division));
          
        //without parentheses
        GreetingService greetService1 = message ->
        System.out.println("Hello " + message);
          
        //with parentheses
        GreetingService greetService2 = (message) ->
        System.out.println("Hello " + message);
          
        greetService1.sayMessage("Mahesh");
        greetService2.sayMessage("Suresh");
        
        
        
        
        //Method reference
        List names = new ArrayList();
        
        names.add("Mahesh");
        names.add("Suresh");
        names.add("Ramesh");
        names.add("Naresh");
        names.add("Kalpesh");
          
        names.forEach(System.out::println);
        
        //Function Interface
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        
        // Predicate<Integer> predicate = n -> true
        // n is passed as parameter to test method of Predicate interface
        // test method will always return true no matter what value n has.
          
        System.out.println("Print all numbers:");
          
        //pass n as parameter
        eval(list, n->true);
          
        // Predicate<Integer> predicate1 = n -> n%2 == 0
        // n is passed as parameter to test method of Predicate interface
        // test method will return true if n%2 comes to be zero
          
        System.out.println("Print even numbers:");
        eval(list, n-> n%2 == 0 );
          
        // Predicate<Integer> predicate2 = n -> n > 3
        // n is passed as parameter to test method of Predicate interface
        // test method will return true if n is greater than 3.
          
        System.out.println("Print numbers greater than 3:");
        eval(list, n-> n > 3 );
        
        
        //Default Method
        Vehicle vehicle = new Car();
        vehicle.print();
        
        
        
        
        // TODO Auto-generated method stub
        List<String> strings = Arrays.asList("John", "", "Marry", "Jim", "","", "Andrew");
        //Print the names from the above list that are not empty.
        strings.stream()
        .filter(e -> !e.equals(""))
        .forEach(System.out::println);
        
        //Print the names from the above list that are not empty.
        strings = Arrays.asList("John", "", "Marry", "Jim", "","", "Andrew");
        
    }
    
    public static void eval(List<Integer> list, Predicate<Integer> predicate) {

        for(Integer n: list) {

           if(predicate.test(n)) {
              System.out.println(n + " ");
           }
        }
     }
    

    
}

interface Vehicle {

    default void print() {
        System.out.println("I am a vehicle!");
    }

    static void blowHorn() {
        System.out.println("Blowing horn!!!");
    }
}

interface FourWheeler {

    default void print() {
        System.out.println("I am a four wheeler!");
    }
}

class Car implements Vehicle, FourWheeler {

    public void print() {
        Vehicle.super.print();
        FourWheeler.super.print();
        Vehicle.blowHorn();
        System.out.println("I am a car!");
    }
}
