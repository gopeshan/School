package package1;
import java.util.Scanner;

public class WorkHourApp {
	int hoursperday = 0, numdays = 0;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int toHours = 0;
		
		Scanner input = new Scanner(System.in);
		WorkHourApp obj = new WorkHourApp();
		
		System.out.println("Input how many days you worked and how long");
		obj.hoursperday = input.nextInt();
		obj.numdays = input.nextInt();
		
		for (int i = 1; i <= obj.numdays; i++) {
			toHours += obj.hoursperday;
			System.out.println("Day " + i + " Hours worked " + toHours);
		
		
		}
		
	}

}
