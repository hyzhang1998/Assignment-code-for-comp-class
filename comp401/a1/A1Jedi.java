package a1jedi;

import java.util.Scanner;

public class A1Jedi {

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
		double mid_sum = 0;
		double fin_sum = 0;
		char F[] = new char[student_num];
		String Lname[] = new String[student_num];
		double Agrade[] = new double[student_num];
		double p_final_grade[] = new double[student_num];
		double mid[] = new double[student_num];
		double fin[] = new double[student_num];
		for(int i = 0; i < student_num; i++) {
			F[i] = s.next().charAt(0);
			Lname[i] = s.next();
			double Pgrade[] = new double[student_num];
			Pgrade[i] = s.nextDouble();
			double a[] = new double[assignment_num];
			double a_total_grade[] = new double[student_num];
			for(int k = 0; k < assignment_num; k++) {
				a[k] = s.nextDouble();
				a_total_grade[i] += a[k];
			}
			Agrade[i] = a_total_grade[i]/a_total_score*100;
			p_final_grade[i] = 100*(Pgrade[i]/(p_score*0.8));
			if(p_final_grade[i] > 100) {
				p_final_grade[i] = 100;
			}
			mid[i] = s.nextDouble();
			mid_sum += mid[i];
			fin[i] = s.nextDouble();
			fin_sum += fin[i];
		}
		double mid_avg = mid_sum/student_num;
		double fin_avg = fin_sum/student_num;
		double m = 0;
		double f = 0;
		for(int i = 0; i < student_num; i++) {
			m += (mid[i] - mid_avg)*(mid[i] - mid_avg);
			f += (fin[i] - fin_avg)*(fin[i] - fin_avg);
		}
		double mid_sigma = Math.sqrt(m/student_num);
		double fin_sigma = Math.sqrt(f/student_num);
		double mid_normalized[] = new double[student_num];
		double fin_normalized[] = new double[student_num];
		double mid_score[] = new double[student_num];
		double fin_score[] = new double[student_num];
		double w[] = new double[student_num];
		for (int i = 0; i < student_num; i++) {
			mid_normalized[i] = (mid[i] - mid_avg)/mid_sigma;
			fin_normalized[i] = (fin[i] - fin_avg)/fin_sigma;
			mid_score[i] = CurvedScore(mid_normalized[i]);
			fin_score[i] = CurvedScore(fin_normalized[i]);
			w[i] = Agrade[i]*0.4 + p_final_grade[i]*0.15 + mid_score[i]*0.2 + fin_score[i]*0.25;
		}
		int a = 0; int a_minus = 0; int b_plus = 0; int b = 0; int b_minus = 0; int c_plus = 0; int c = 0; int c_minus = 0; int d_plus = 0; int d = 0; int f_grade = 0;
		for(int i = 0; i < student_num; i++) {
			if(LetterGrade(w[i]).equals("A")) {
				a++;
			}
			else if(LetterGrade(w[i]).equals("A-")) {
				a_minus++;
			}
			else if(LetterGrade(w[i]).equals("B+")) {
				b_plus++;
			}
			else if(LetterGrade(w[i]).equals("B")) {
				b++;
			}
			else if(LetterGrade(w[i]).equals("B-")) {
				b_minus++;
			}
			else if(LetterGrade(w[i]).equals("C+")) {
				c_plus++;
			}
			else if(LetterGrade(w[i]).equals("C")) {
				c++;
			}
			else if(LetterGrade(w[i]).equals("C-")) {
				c_minus++;
			}
			else if(LetterGrade(w[i]).equals("D+")) {
				d_plus++;
			}
			else if(LetterGrade(w[i]).equals("D")) {
				d++;
			}
			else if(LetterGrade(w[i]).equals("F")) {
				f_grade++;
			}
		}
		System.out.println("A :" + a);
		System.out.println("A-:" + a_minus);
		System.out.println("B+:" + b_plus);
		System.out.println("B :" + b);
		System.out.println("B-:" + b_minus);
		System.out.println("C+:" + c_plus);
		System.out.println("C :" + c);
		System.out.println("C-:" + c_minus);
		System.out.println("D+:" + d_plus);
		System.out.println("D :" + d);
		System.out.println("F :" + f_grade);
		// Put your code here.
	}
	
	public static double CurvedScore(double n) {
		double low_normal;
		double high_normal;
		double low_curved;
		double high_curved;
		double curved;
		if(n >= 2) {
			return 100;
		}
		else if(n > 1) {
			low_normal = 1;
			high_normal = 2;
			low_curved = 94;
			high_curved = 100;
		}
		else if(n == 1) {
			return 94;
		}
		else if(n > 0) {
			low_normal = 0;
			high_normal = 1;
			low_curved = 85;
			high_curved = 94;
		}
		else if(n == 0) {
			return 85;
		}
		else if(n > -1) {
			low_normal = -1;
			high_normal = 0;
			low_curved = 75;
			high_curved = 85;
		}
		else if(n == -1) {
			return 75;
		}
		else if(n > -1.5) {
			low_normal = -1.5;
			high_normal = -1;
			low_curved = 65;
			high_curved = 75;
		}
		else if(n == -1.5) {
			return 65;
		}
		else if(n > -2) {
			low_normal = -2;
			high_normal = -1.5;
			low_curved = 55;
			high_curved = 65;
		}
		else if(n == 2) {
			return 55;
		}
		else if(n > -3) {
			low_normal = -3;
			high_normal = -2;
			low_curved = 30;
			high_curved = 55;
		}
		else if(n == -3) {
			return 30;
		}
		else if(n > -4) {
			low_normal = -4;
			high_normal = -3;
			low_curved = 0;
			high_curved = 30;
		}
		else {
			return 0;
		}
		curved = (((n - low_normal)/(high_normal - low_normal))*(high_curved - low_curved)) + low_curved;
		return curved;
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
