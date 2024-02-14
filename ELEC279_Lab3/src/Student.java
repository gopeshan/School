import java.util.Arrays;

public class Student extends Person {

    private int numCoursesTaken;
    private String[] courseCodes;
    private int[] grades;
    private static final int MAX_COURSES = 20;

    public Student(String name, String address, int idNum) {
        super(name, address, idNum);
        numCoursesTaken = 0;
        courseCodes = new String[MAX_COURSES];
        grades = new int[MAX_COURSES];
        Arrays.fill(courseCodes, "");
        Arrays.fill(grades, 0);
    }

    @Override
    public String toString() {
        return "Student: " + super.toString();
    }

    public void addGrade(String courseCode, int grade) {
        if (numCoursesTaken < MAX_COURSES) {
            courseCodes[numCoursesTaken] = courseCode;
            grades[numCoursesTaken] = grade;
            numCoursesTaken++;
        }
    }

    public void printGrades() {
        for (int i = 0; i < numCoursesTaken; i++) {
            System.out.println(courseCodes[i] + ":" + grades[i]);
        }
    }
}
