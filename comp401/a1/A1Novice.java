package a1novice;

import java.util.Scanner;

public class A1Novice {

	// Do not change the main method.	
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		
		process(s);
	}
	
	public static void process(Scanner s) {
		int student_num = s.nextInt();
		for(int i = 0; i < student_num; i++) {
			char F = s.next().charAt(0);;
			String Lname = s.next();
			double Agrade = s.nextDouble();
			double Pgrade = s.nextDouble();
			double mid = s.nextDouble();
			double fin = s.nextDouble();
			double w = Agrade*0.4 + Pgrade*0.15 + mid*0.2 + fin*0.25;
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
