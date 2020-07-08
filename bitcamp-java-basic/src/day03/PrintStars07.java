package day03;

import java.util.Scanner;

public class PrintStars07 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("=====별찍기 7번=====");
		System.out.print("출력할 줄 수를 입력하세요: ");
		int userNumber = scanner.nextInt();
		
		for(int i = 1; i <= 2*userNumber-1; i++) {
			String stars = "";
			if(i < userNumber) {
				// i가 1~4까지는 여기로 온다.
				for(int j = 1; j <=i; j++) {
					stars += "*";
				}
			} else {
				// i가 5~9까지는 여기로 온다.
				int lowerI = i - userNumber + 1;
				for (int j = lowerI; j <= userNumber; j++) {
					stars += "*";
				}
				
			}
			System.out.println(stars);
		}
		
		scanner.close();
	}
}
