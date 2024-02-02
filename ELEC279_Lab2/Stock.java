package package1;

public class Stock {
    private int day;
    private int month;
    private int year;
    private String name;
    private int[] values = new int[7];

    public Stock() {
        this.day = 1;
        this.month = 1;
        this.year = 1;
        this.name = "";
        for (int i = 0; i < values.length; i++) {
            values[i] = 0;
        }
    }

    public Stock(int dayIn, int monthIn, int yearIn, String nameIn, int[] valIn) {
        if (isValidDate(dayIn, monthIn, yearIn) && isValidValues(valIn)) {
            this.day = dayIn;
            this.month = monthIn;
            this.year = yearIn;
            this.name = nameIn;
            for (int i = 0; i < values.length; i++) {
                values[i] = valIn[i];
            }
        } else {
            System.out.println("Invalid arguments provided. Using default values.");
            this.day = 1;
            this.month = 1;
            this.year = 1;
            this.name = "";
            for (int i = 0; i < values.length; i++) {
                values[i] = 0;
            }
        }
    }

    public String getName() {
        return this.name;
    }

    public int getValue(int ind) {
        if (ind >= 0 && ind < values.length) {
            return values[ind];
        } else {
            System.out.println("Index out of bounds. Returning -1.");
            return -1;
        }
    }

    public void setName(String nameIn) {
        this.name = nameIn;
    }

    public void setValue(int val, int ind) {
        if (val >= 0 && ind >= 0 && ind < values.length) {
            values[ind] = val;
        } else {
            System.out.println("Invalid value or index provided. Value not updated.");
        }
    }

    public void printStock() {
        System.out.println("Date: " + day + "." + month + "." + year);
        System.out.println("Name: " + name);
        System.out.print("Values: ");
        for (int value : values) {
            System.out.print(value + " ");
        }
        System.out.println();
    }

    public int getMean() {
        int sum = 0;
        for (int value : values) {
            sum += value;
        }
        return values.length > 0 ? sum / values.length : 0;
    }

    private boolean isValidDate(int day, int month, int year) {
        //hold
        return true;
    }

    private boolean isValidValues(int[] val) {
        if (val.length == 7) {
            for (int value : val) {
                if (value < 0) {
                    System.out.println("Invalid values provided. Values must not be negative.");
                    return false;
                }
            }
            return true;
        } else {
            System.out.println("Invalid values provided. Values must be of length 7.");
            return false;
        }
    }
}
