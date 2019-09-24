package a2novice;

import java.util.Scanner;

public class A2Novice {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		picture(s);
	}

	public static void picture(Scanner s) {
		int hori = s.nextInt();
		int vert = s.nextInt();
		int pnum[][] = new int[hori][vert];
		for(int h = 0; h < hori; h++) {
			for(int v = 0; v < vert; v++) {
				pnum[h][v] = s.nextInt();
			}
		}
		for(int v = 0; v < vert; v++) {
			for(int h = 0; h < hori; h++) {
				System.out.print(Pixelize(pnum[h][v]));
			}
			System.out.println();
		}
		System.out.println("Min value = " + FindMin(pnum));
		System.out.println("Max value = " + FindMax(pnum));
		System.out.println("Average value = " + FindAvg(pnum));
	}
	
	public static String Pixelize(int a) {
		if(a >= 0 && a <=9) {
			return "#";
		}
		else if(a >= 10 && a <= 19) {
			return "M";
		}
		else if(a >= 20 && a <= 29) {
			return "X";
		}
		else if(a >= 30 && a <= 39) {
			return "D";
		}
		else if(a >= 40 && a <= 49) {
			return "<";
		}
		else if(a >= 50 && a <= 59) {
			return ">";
		}
		else if(a >= 60 && a <= 69) {
			return "s";
		}
		else if(a >= 70 && a <= 79) {
			return ":";
		}
		else if(a >= 80 && a <= 89) {
			return "-";
		}
		else{
			return " ";
		}
	}
	
	public static int FindMin(int[][] a) {
		int min = a[0][0];
		for(int h = 0; h < a.length ; h++) {
			for(int v = 0; v < a[h].length; v++) {
				if(a[h][v] < min) {
					min = a[h][v];
				}
			}
		}
		return min;
	}
	public static int FindMax(int[][] a) {
		int max = a[0][0];
		for(int h = 0; h < a.length ; h++) {
			for(int v = 0; v < a[h].length; v++) {
				if(a[h][v] > max) {
					max = a[h][v];
				}
			}
		}
		return max;
	}
	public static double FindAvg(int[][] a) {
		double sum = 0;
		for(int h = 0; h < a.length ; h++) {
			for(int v = 0; v < a[h].length; v++) {
				sum += a[h][v];
			}
		}
		double avg = sum/(a.length * a[0].length);
		return avg;
	}
}
