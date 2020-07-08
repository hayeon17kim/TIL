package day03;

import java.util.Scanner;

public class PrintStars04 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("=====별찍기 4번=====");
		System.out.print("출력할 줄 수를 입력하세요: ");
		int userNumber = scanner.nextInt();
		
		for(int i = 1; i <= userNumber; i++) {
			String stars = "";
			
			//공백
			for(int j = 1; j <= i-1; j++) {
				stars += " ";
			}
			
			//별
			for(int j = i; j <= userNumber; j++) {
				stars += "*";
			}
			
			System.out.println(stars);
		}
		
		scanner.close();
	}

}
