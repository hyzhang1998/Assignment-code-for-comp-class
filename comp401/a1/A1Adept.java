package a1adept;

import java.util.Scanner;

public class A1Adept {
	
	// Do not change the main method.	
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		
		process(s);
		
	}
	
	public static void process(Scanner s) {
		int assignment_num = s.nextInt();
		int assignment_score[] = new int[assignment_num];
		int a_total_score = 0;
		for(int i = 0; i < assignment_num; i++) {
			assignment_score[i] = s.nextInt();
			a_total_score += assignment_score[i];
		}
		int p_score = s.nextInt();
		int student_num = s.nextInt();
		for(int i = 0; i < student_num; i++) {
			char F = s.next().charAt(0);;
			String Lname = s.next();
			double Pgrade = s.nextDouble();
			double a[] = new double[assignment_num];
			double a_total_grade = 0;
			for(int k = 0; k < assignment_num; k++) {
				a[k] = s.nextDouble();
				a_total_grade += a[k];
			}
			double Agrade = a_total_grade/a_total_score*100;
			double p_final_grade = 100*(Pgrade/(p_score*0.8));
			if(p_final_grade > 100) {
				p_final_grade = 100;
			}
			double mid = s.nextDouble();
			double fin = s.nextDouble();
			double w = Agrade*0.4 + p_final_grade*0.15 + mid*0.2 + fin*0.25;
			System.out.println(F + ". " + Lname + " " + LetterGrade(w));
		}
		// Put your code here.
	}
	public static String LetterGrade(double w) {
		String grade;
		if(w >= 94) {
			grade = "A";
		}
		else if(w >= 90) {
			grade = "A-";
		}
		else if(w >= 86) {
			grade = "B+";
		}
		else if(w >= 83) {
			grade = "B";
		}
		else if(w >= 80) {
			grade = "B-";
		}
		else if(w >= 76) {
			grade = "C+";
		}
		else if(w >= 73) {
			grade = "C";
		}
		else if(w >= 70) {
			grade = "C-";
		}
		else if(w >= 65) {
			grade = "D+";
		}
		else if(w >= 60) {
			grade = "D";
		}
		else{
			grade = "F";
		}
		return grade;
	}
}
