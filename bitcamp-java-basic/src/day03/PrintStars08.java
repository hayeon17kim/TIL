package day03;

import java.util.Scanner;

public class PrintStars08 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("=====별찍기 8번=====");
		System.out.print("출력할 줄 수를 입력하세요: ");
		int userNumber = scanner.nextInt();
		// 윗부분. => 1 ~ userNumber - 1 까지
		for(int i = 1; i <= userNumber; i++) {
			String stars = "";
			// 공백을 담당하는 for문 
			for(int j = 1; j <= userNumber - i; j++) {
				stars += " ";
			}
			
			// 별을 담당하는 for문
			for(int j = 1; j <= i; j++) {
				stars += "*";
			}
			System.out.println(stars);
		}
		
		// 아랫부분
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
		
	}
}
