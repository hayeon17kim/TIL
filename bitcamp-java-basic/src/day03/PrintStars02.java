package day03;

import java.util.Scanner;

public class PrintStars02 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("=====����� 2��=====");
		System.out.print("����� �� ���� �Է��ϼ���: ");
		int userNumber = scanner.nextInt();

		// i for loop�� ���� �� ���� ����Ѵ�.
		// �׷��ٸ� i for ���� ���� ���ǽ��� ��� �ɱ�?
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
