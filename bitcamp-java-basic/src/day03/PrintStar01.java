package day03;

// ����� ���� 1��
// \\192.168.0.98�� ���� �ڹٺ����� ���� ����
// ����� �ؽ�Ʈ ������ ���� �ɽô�.

import java.util.Scanner;

public class PrintStar01 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("=====����� 1��=====");
		System.out.print("����� �� ���� �Է��ϼ���: ");
		int userNumber = scanner.nextInt();
		
		// i for loop�� ���� �� ���� ����Ѵ�.
		// �׷��ٸ� i for ���� ���� ���ǽ��� ��� �ɱ�?
		for(int i = 1; i <= userNumber; i++) {
			String stars = "";
			for(int j = 1; j <= i; j++) {
				stars += "*";
			}
			System.out.println(stars);
		}
		
		scanner.close();
		
	}
}
