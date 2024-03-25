import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class Lab5 {

    public static void main(String[] args) {
        // Create an ArrayList to store integers
        ArrayList<Integer> intArray = new ArrayList<>(5);
        Random random = new Random();

        // Ensure the capacity of the ArrayList is at least 10
        intArray.ensureCapacity(10);
        
        // Generate random integers and add them to the ArrayList
        for (int i = 0; i < 10; i++)
            intArray.add(random.nextInt(100));
        
        // Print the contents of the ArrayList
        System.out.print("[");
        intArray.forEach((n) -> System.out.print(n + ", "));
        System.out.print("]\n");

        // Create a new ArrayList to store integers in reverse order
        ArrayList<Integer> revArray = new ArrayList<>(10);
        
        // Add elements from intArray to revArray in reverse order
        intArray.forEach((n) -> revArray.add(0, n));
        
        // Print the contents of revArray
        System.out.print("[");
        revArray.forEach((n) -> System.out.print(n + ", "));
        System.out.print("]\n");

        // Create an ArrayList to store HourlyEmployee objects
        ArrayList<HourlyEmployee> hEmployee = new ArrayList<>(100);
        
        // Add HourlyEmployee objects to the ArrayList
        hEmployee.add(new HourlyEmployee("Joe", new Date("2020/10/1"), 5, 5));
        hEmployee.add(new HourlyEmployee("Moe", new Date("2000/7/10"), 5, 10));
        hEmployee.add(new HourlyEmployee("Bob", new Date("2010/11/12"), 3, 55));
        hEmployee.add(new HourlyEmployee("Mike", new Date("2022/1/3"), 7, 9));
        
        // Print information about each HourlyEmployee object
        hEmployee.forEach((n) -> System.out.println(n.toString()));
        
        // Print the size of the ArrayList
        System.out.println("Size: " + hEmployee.size());

        // Modify the ArrayList by removing and adding an element
        HourlyEmployee mile = new HourlyEmployee("Mile", new Date("2002/10/10"), 10, 90);
        hEmployee.remove(0);
        hEmployee.add(0, mile);
        
        // Print information about each HourlyEmployee object after modification
        hEmployee.forEach((n) -> System.out.println(n.toString()));

        // Remove an element from the ArrayList and trim its capacity
        hEmployee.remove(2);
        hEmployee.trimToSize();
        
        // Print the size of the ArrayList after modification
        System.out.println("Size: " + hEmployee.size());

        // Create instances of Pair and Triple classes
        Pair<String, String> jt = new Pair<>("Julia", "Tom");
        Pair<String, String> tj = new Pair<>("Julia", "Tom");
        Pair<String, String> judy = new Pair<>("Judy", "Tom");

        // Print information about the pairs and check for equality
        System.out.println("First pair ==> " + jt);
        System.out.println("Second pair ==> " + tj);
        System.out.println("Third pair ==> " + judy);
        System.out.println("First and second pair are equal? " + jt.equals(tj));
        System.out.println("First and third pair are equal? " + jt.equals(judy));

        // Create instances of Triple class
        Triple<String, Double, String> t1 = new Triple<>("Toronto", 547.0, "Montreal");
        Triple<String, Double, String> t2 = new Triple<>("Toronto", 547.0, "Montreal");
        Triple<String, Double, String> t3 = new Triple<>("Toronto", 264.5, "Kingston");

        // Print information about the triples and check for equality
        System.out.println("First triple ==> " + t1);
        System.out.println("Second triple ==> " + t2);
        System.out.println("Third triple ==> " + t3);
        System.out.println("First and second triple are equal? " + t1.equals(t2));
        System.out.println("First and third triple are equal? " + t1.equals(t3));
    }
}
