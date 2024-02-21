import java.text.ParseException;
import java.util.Scanner;

public class Date {
    private String month;
    private int day;
    private int year;

    public Date() {
        setDefaultDate();
    }

    public Date(String dateStr) throws ParseException {
        try {
            parseDateInput(dateStr);
        } catch (Exception e) {
            throw new ParseException("Invalid date format", 0);
        }
    }

    public Date(int month, int day, int year) {
        setDate(month, day, year);
    }

    public Date(String month, int day, int year) {
        setDate(month, day, year);
    }

    public Date(int year) {
        setDefaultDate();
        setYear(year);
    }

    public Date(Date otherDate) {
        copyDate(otherDate);
    }

    public void setDate(int month, int day, int year) {
        if (isValidDate(month, day, year)) {
            setMonth(month);
            setDay(day);
            setYear(year);
        } else {
            handleInvalidDate();
        }
    }

    public void setDate(String month, int day, int year) {
        if (isValidDate(month, day, year)) {
            this.month = month;
            this.day = day;
            this.year = year;
        } else {
            handleInvalidDate();
        }
    }

    public void setYear(int year) {
        if (isValidYear(year)) {
            this.year = year;
        } else {
            handleInvalidYear();
        }
    }

    public void setMonth(int monthNumber) {
        if (isValidMonth(monthNumber)) {
            this.month = getMonthName(monthNumber);
        } else {
            handleInvalidMonth();
        }
    }

    public void setDay(int day) {
        if (isValidDay(day)) {
            this.day = day;
        } else {
            handleInvalidDay();
        }
    }

    public int getMonth() {
        return getMonthNumber(month);
    }

    public int getDay() {
        return day;
    }

    public int getYear() {
        return year;
    }

    public String toString() {
        return String.format("%s %d, %d", month, day, year);
    }

    public boolean equals(Date otherDate) {
        return otherDate != null && month.equals(otherDate.month) && day == otherDate.day && year == otherDate.year;
    }

    public boolean precedes(Date otherDate) {
        return year < otherDate.year ||
                (year == otherDate.year && getMonth() < otherDate.getMonth()) ||
                (year == otherDate.year && month.equals(otherDate.month) && day < otherDate.day);
    }

    public void readInput() {
        boolean tryAgain = true;
        Scanner keyboard = new Scanner(System.in);
        while (tryAgain) {
            System.out.println("Enter month, day, and year.");
            System.out.println("Do not use a comma.");
            String monthInput = keyboard.next();
            int dayInput = keyboard.nextInt();
            int yearInput = keyboard.nextInt();
            if (isValidDate(monthInput, dayInput, yearInput)) {
                setDate(monthInput, dayInput, yearInput);
                tryAgain = false;
            } else {
                System.out.println("Illegal date. Reenter input.");
            }
        }
    }

    private void setDefaultDate() {
        this.month = "January";
        this.day = 1;
        this.year = 1000;
    }

    private void parseDateInput(String dateStr) throws ParseException {
        String[] dateValues = dateStr.split("/");
        if (dateValues.length != 3) {
            throw new ParseException("Invalid date format", 0);
        }

        int monthInt = Integer.parseInt(dateValues[0]);
        int dayInt = Integer.parseInt(dateValues[1]);
        int yearInt = Integer.parseInt(dateValues[2]);

        if (isValidDate(monthInt, dayInt, yearInt)) {
            setMonth(monthInt);
            setDay(dayInt);
            setYear(yearInt);
        } else {
            throw new ParseException("Invalid date", 0);
        }
    }

    private void handleInvalidDateInput() {
        System.out.println("Invalid Date entered.");
        setDefaultDate();
    }

    private void handleInvalidDate() {
        System.out.println("Major Error");
        System.exit(0);
    }

    private void copyDate(Date otherDate) {
        if (otherDate != null) {
            this.month = otherDate.month;
            this.day = otherDate.day;
            this.year = otherDate.year;
        } else {
            handleInvalidDate();
        }
    }

    private boolean isValidDate(int month, int day, int year) {
        return isValidMonth(month) && isValidDay(day) && isValidYear(year);
    }

    private boolean isValidDate(String month, int day, int year) {
        return isValidMonth(month) && isValidDay(day) && isValidYear(year);
    }

    private boolean isValidMonth(int monthNumber) {
        return monthNumber >= 1 && monthNumber <= 12;
    }

    private boolean isValidMonth(String month) {
        return getMonthNumber(month) != 0;
    }

    private boolean isValidDay(int day) {
        return day >= 1 && day <= 31;
    }

    private boolean isValidYear(int year) {
        return year >= 1000 && year <= 9999;
    }

    private int getMonthNumber(String monthName) {
        switch (monthName.toLowerCase()) {
            case "january":
                return 1;
            case "february":
                return 2;
            case "march":
                return 3;
            case "april":
                return 4;
            case "may":
                return 5;
            case "june":
                return 6;
            case "july":
                return 7;
            case "august":
                return 8;
            case "september":
                return 9;
            case "october":
                return 10;
            case "november":
                return 11;
            case "december":
                return 12;
            default:
                return 0;
        }
    }

    private String getMonthName(int monthNumber) {
        switch (monthNumber) {
            case 1:
                return "January";
            case 2:
                return "February";
            case 3:
                return "March";
            case 4:
                return "April";
            case 5:
                return "May";
            case 6:
                return "June";
            case 7:
                return "July";
            case 8:
                return "August";
            case 9:
                return "September";
            case 10:
                return "October";
            case 11:
                return "November";
            case 12:
                return "December";
            default:
                handleInvalidMonth();
                return ""; // This line is unreachable but included to satisfy the compiler
        }
    }

    private void handleInvalidMonth() {
        System.out.println("Major Error");
        System.exit(0);
    }

    private void handleInvalidYear() {
        System.out.println("Major Error");
        System.exit(0);
    }

    private void handleInvalidDay() {
        System.out.println("Major Error");
        System.exit(0);
    }
}
