package package1;

public class Activity {
    private int day;
    private int month;
    private int year;

    public Activity() {
        this.day = 1;
        this.month = 1;
        this.year = 1;
    }

    public Activity(int dayIn, int monthIn, int yearIn) {
        if (isValidDate(dayIn, monthIn, yearIn)) {
            this.year = yearIn;
            this.month = monthIn;
            this.day = dayIn;
        } else {
            System.out.println("Invalid date provided. Using default values (1.1.1).");
            this.day = 1;
            this.month = 1;
            this.year = 1;
        }
    }

    public int getDay() {
        return this.day;
    }

    public int getMonth() {
        return this.month;
    }

    public int getYear() {
        return this.year;
    }

    public void setDate(int dayIn, int monthIn, int yearIn) {
        if (isValidDate(dayIn, monthIn, yearIn)) {
            this.year = yearIn;
            this.month = monthIn;
            this.day = dayIn;
        } else {
            System.out.println("Invalid date provided. Date not updated.");
        }
    }

    public void printDate() {
        System.out.println(this.day + "." + this.month + "." + this.year);
    }

    private boolean isValidDate(int day, int month, int year) {
        // hold
        return true;
    }
}
