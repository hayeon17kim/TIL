package day03;

import java.util.Scanner;

public class PrintStars02 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("=====별찍기 2번=====");
		System.out.print("출력할 줄 수를 입력하세요: ");
		int userNumber = scanner.nextInt();

		// i for loop이 세로 줄 수를 담당한다.
		// 그렇다면 i for 문의 종료 조건식은 어떻게 될까?
		for(int i = 1; i <= userNumber; i++) {
			String stars = "";
			for(int j = i; j <= userNumber; j++) {
				stars += "*";
			}
			System.out.println(stars);
		}
		
		scanner.close();
	}
}
