package day03;

import java.util.Scanner;

public class Ex11PrintStars09 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("=====����� 9��=====");
		System.out.print("����� �� ���� �Է��ϼ���: ");
		int userNumber = scanner.nextInt();
		
		for(int i = 1; i <= 2 * userNumber - 1; i++){
			String stars = "";
			if(i < userNumber) {
				// ���κ�
				for(int j = 1; j <= userNumber - i; j++) {
					stars += " ";
				}
				for(int j = 1; j <= 2 * i - 1; j++) {
					stars += "*";
				}
			} else {
				// �Ʒ��κ�
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
