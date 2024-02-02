package package1;
import java.util.Date;
import java.util.Scanner;


class DateApptask {
public static void main(String[]args) { 
	Date todaysDate = new Date();
	System.out.println(todaysDate);
	int min = 10, max = 20, average = 5;
	String myRole = "Driver";
	byte myFirstByte = 22;
	double pi = 3.14159;
	char myChar = 'N';
	int count = 1;
	
	System.out.println("I am now the '' +myRole");
	System.out.println("Our minimum score is '' +min");
	System.out.println("We have a byte '' +myFirstByte");
	System.out.println("And double type is '' +pi");
	System.out.println("A char looks like '' +myChar");

	while(count <= 10) {
		System.out.println(todaysDate);
		count = count+1;
	}
	
	for (int row = 1; row <= 5; row++) {
		for(int column = 1; column <= 10; column++) {
			System.out.print(row*column+"\t");
		}
		System.out.println();
	}
}
}

