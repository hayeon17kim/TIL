package day03;

import java.util.Scanner;

public class PrintStars04 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("=====����� 4��=====");
		System.out.print("����� �� ���� �Է��ϼ���: ");
		int userNumber = scanner.nextInt();
		
		for(int i = 1; i <= userNumber; i++) {
			String stars = "";
			
			//����
			for(int j = 1; j <= i-1; j++) {
				stars += " ";
			}
			
			//��
			for(int j = i; j <= userNumber; j++) {
				stars += "*";
			}
			
			System.out.println(stars);
		}
		
		scanner.close();
	}

}
