package a2jedi;

import java.util.Scanner;

public class A2Jedi {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		picture(s);
	}

	public static void picture(Scanner s) {
		int step = s.nextInt();
		int width = s.nextInt();
		int height = s.nextInt();
		int range[] = new int[99/step+1];
		int lbound = 0;
		int ubound = step - 1;
		int index = 0;
		for(int i = 0; i < width*height; i++) {
			int num = s.nextInt();
			if(num >= lbound && num <= ubound) {
				range[index]++;
			}
			else {
				while(num < lbound || num > ubound) {
					if(num > ubound){
						lbound += step;
						ubound += step;
						index++;
					}
					else {
						lbound -= step;
						ubound -= step;
						index--;
					}
				}
				range[index]++;
			}
		}
		for(int i = 0; i < 99/step+1; i++){
			double temp = (double)range[i];
			range[i] = (int)((temp/(width*height))*100+0.5);
		}
		int maxindex[] = FindMaxIndex(range);
		int print[][] = new int[range.length][range[maxindex[0]]];
		for(int i = 0; i < range.length; i++) {
			for(int k = 0; k < maxindex.length; k++) {
				for(int l = 0; l < range[maxindex[0]]; l++) {
					print[maxindex[k]][l] = 1;
				}
			}
			for(int k = 0; k < maxindex.length; k++) {
				if(i != maxindex[k]) {
					for(int j = 0; j < range[maxindex[0]] - range[i]; j++) {
						print[i][j] = 0;
					}
					for(int j = 0; j < range[i]; j++) {
						print[i][range[maxindex[0]] - range[i] + j] = 1;
					}
				}
			}
		}
		for(int i = 0; i < print[0].length; i++) {
			for(int j = 0; j < print.length; j++) {
				if(print[j][i] == 0) {
					System.out.print(" ");
				}
				if(print[j][i] == 1) {
					System.out.print("*");
				}
			}
			System.out.println();
		}
		for(int i = 0; i < range.length; i++) {
			System.out.print("-");
		}
		System.out.println();
		lbound = 0;
		for(int i = 0; i < range.length; i++) {
			int fdigit = lbound/10;
			if(fdigit == 0) {
				System.out.print(" ");
			}
			else {
				System.out.print(fdigit);
			}
			lbound += step;
		}
		System.out.println();
		lbound = 0;
		for(int i = 0; i < range.length; i++) {
			int sdigit = lbound % 10;
			System.out.print(sdigit);
			lbound += step;
		}
	}

	public static int[] FindMaxIndex(int[] range) {
		int max = range[0];
		for(int i = 0; i < range.length; i++) {
			if(range[i] > max) {
				max = range[i];
			}
		}
		int maxnum = 0;
		for(int i = 0; i < range.length; i++) {
			if(range[i] == max) {
				maxnum++;
			}
		}
		int index[] = new int[maxnum];
		int count = 0;
		for(int i = 0; i < range.length; i++) {
			if(range[i] == max) {
				index[count] = i;
				count++;
			}
		}
		return index;
	}
}
