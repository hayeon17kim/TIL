package day03;

import java.util.Scanner;

public class PrintStars05 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("=====별찍기 5번=====");
		System.out.print("출력할 줄 수를 입력하세요: ");
		int userNumber = scanner.nextInt();
		
		for(int i = 1; i <= userNumber; i++){
			String stars = "";
			
			for(int j = 1; j <= userNumber - i; j++) {
				stars += " ";
			}
			
			// 별을 담당하는 for문의 경우
			// 별의 갯수가 1 3 5 7 9 ..... 2*1-1 개가 된다.
			
			for(int j = 1; j <= 2 * i - 1; j++) {
				stars += "*";
			}

			System.out.println(stars);
		}
		
		scanner.close();
	}
}
