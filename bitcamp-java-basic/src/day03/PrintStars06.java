package day03;

import java.util.Scanner;

public class PrintStars06 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("=====����� 6��=====");
		System.out.print("����� �� ���� �Է��ϼ���: ");
		int userNumber = scanner.nextInt();
		
		for(int i = userNumber; i >= 1; i--){
			String stars = "";
			
			for(int j = 1; j <= userNumber - i; j++) {
				stars += " ";
			}
			
			for(int j = 1; j <= 2 * i - 1; j++) {
				stars += "*";
			}

			System.out.println(stars);
		}
		
		scanner.close();
	}
}
