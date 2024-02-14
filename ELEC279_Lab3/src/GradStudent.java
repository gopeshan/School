import java.util.Arrays;

public class GradStudent extends Student {

    private int numCommittee;
    private String[] gradExamNames;
    private String[] gradExamResults;
    private static final int MAX_EXAM_ATTEMPTS = 6;
    private static final int MAX_GRAD_EXAMS = 4;

    public GradStudent(String name, String address, int idNum) {
        super(name, address, idNum);
        numCommittee = 0;
        gradExamNames = new String[MAX_GRAD_EXAMS];
        gradExamResults = new String[MAX_GRAD_EXAMS];
        Arrays.fill(gradExamNames, "");
        Arrays.fill(gradExamResults, "");
    }

    public void setCommitteeNum(int numCommittee) {
        this.numCommittee = numCommittee;
    }

    public int getCommitteeNum() {
        return numCommittee;
    }

    @Override
    public String toString() {
        return "Grad " + super.toString();
    }

    public void addGrade(String examName, String examResult) {
        for (int i = 0; i < gradExamNames.length; i++) {
            if (gradExamNames[i].isEmpty()) {
                gradExamNames[i] = examName;
                gradExamResults[i] = examResult;
                break;
            }
        }
    }

    public void printExamGrades() {
        for (int i = 0; i < gradExamNames.length; i++) {
            System.out.println(gradExamNames[i] + ":" + gradExamResults[i]);
        }
    }
}
