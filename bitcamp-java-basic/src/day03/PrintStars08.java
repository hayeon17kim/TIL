package day03;

import java.util.Scanner;

public class PrintStars08 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("=====����� 8��=====");
		System.out.print("����� �� ���� �Է��ϼ���: ");
		int userNumber = scanner.nextInt();
		// ���κ�. => 1 ~ userNumber - 1 ����
		for(int i = 1; i <= userNumber; i++) {
			String stars = "";
			// ������ ����ϴ� for�� 
			for(int j = 1; j <= userNumber - i; j++) {
				stars += " ";
			}
			
			// ���� ����ϴ� for��
			for(int j = 1; j <= i; j++) {
				stars += "*";
			}
			System.out.println(stars);
		}
		
		// �Ʒ��κ�
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
		
	}
}
