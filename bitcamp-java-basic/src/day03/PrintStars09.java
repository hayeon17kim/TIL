package day03;

import java.util.Scanner;

public class Ex11PrintStars09 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("=====별찍기 9번=====");
		System.out.print("출력할 줄 수를 입력하세요: ");
		int userNumber = scanner.nextInt();
		
		for(int i = 1; i <= 2 * userNumber - 1; i++){
			String stars = "";
			if(i < userNumber) {
				// 윗부분
				for(int j = 1; j <= userNumber - i; j++) {
					stars += " ";
				}
				for(int j = 1; j <= 2 * i - 1; j++) {
					stars += "*";
				}
			} else {
				// 아랫부분
				// i: 5 6 7 8 9
				int lowerI = 2 * userNumber - i;
				for(int j = 1; j <= userNumber - lowerI; j++) {
					stars += " ";
				}
				for(int j = 1; j <= 2 * lowerI - 1; j++) {
					stars += "*";
				}
			}
			System.out.println(stars);
		}
		scanner.close();

	}

}
