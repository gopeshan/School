
package package1;
import java.util.Scanner;

public class TaskInputOutput{
	
	public int int1, int2, sum = 0, product = 0;
	
	public static void main(String[]args) { 
		Scanner input = new Scanner(System.in);
		TaskInputOutput obj = new TaskInputOutput();
		
		System.out.println("Input two intergers: ");
		obj.int1 = input.nextInt();
		obj.int2 = input.nextInt();
		
		obj.sum = obj.int1 + obj.int2;
		obj.product = obj.int1 * obj.int2;
		
		System.out.println("The sum is: " + obj.sum + " and the product is: " + obj.product);

	}
}
