package day03;

import java.util.Scanner;

public class PrintStars05 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("=====����� 5��=====");
		System.out.print("����� �� ���� �Է��ϼ���: ");
		int userNumber = scanner.nextInt();
		
		for(int i = 1; i <= userNumber; i++){
			String stars = "";
			
			for(int j = 1; j <= userNumber - i; j++) {
				stars += " ";
			}
			
			// ���� ����ϴ� for���� ���
			// ���� ������ 1 3 5 7 9 ..... 2*1-1 ���� �ȴ�.
			
			for(int j = 1; j <= 2 * i - 1; j++) {
				stars += "*";
			}

			System.out.println(stars);
		}
		
		scanner.close();
	}
}
