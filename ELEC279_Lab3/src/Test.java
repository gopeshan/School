
public class Test {

	public static void main(String[] args) {
		Person p1 = new Person("Jane", "181 University Ave.", 123456);
		System.out.println(p1.toString());
		
		Student s1 = new Student("John", "89 University Ave.", 234567);
		s1.addGrade("ELEC 279", 97);
		s1.addGrade("ELEC 221", 68);
		System.out.println(s1.toString());
		s1.printGrades();
		
		GradStudent s2 = new GradStudent("Tom", "92 Reg. St.", 345678);
		System.out.println(s2.toString());
		s2.addGrade("ELEC 279", 97);
		s2.addGrade("ELEC 271", 88);
		s2.setCommitteeNum(6);
		s2.addGrade("COMPI", "PASS");
		s2.addGrade("COMPII", "PASS");
		s2.addGrade("Thesis Defense first trial", "FAIL");
		s2.addGrade("Thesis Defense second trial", "FAIL");
		s2.printExamGrades();
		s2.printGrades();
	}
	
}
