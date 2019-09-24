package a2adept;

import java.util.Scanner;

public class A2Adept {
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
		lbound = 0;
		for(int i = 0; i < 99/step+1; i++) {
			if(lbound < 10) {
				System.out.print(" " + lbound + ":");
			}
			else {
				System.out.print(lbound + ":");
			}
			for(int j = 0; j < range[i]; j++) {
				System.out.print("*");
			}
			System.out.println();
			lbound += step;
		}
	}

}
