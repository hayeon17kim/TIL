package day03;

import java.util.Scanner;

public class PrintStars10 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("=====����� 10��=====");
		System.out.print("����� �� ���� �Է��ϼ���: ");
		int userNumber = scanner.nextInt();
		
		for(int i = 1; i <= 2 * userNumber - 1; i++){
			String stars = "";
			if(i == 1 || i == 2 * userNumber - 1) {
				// ���� ���� or ���� ������ ���̹Ƿ�
				// ���� 2 * userNumber - 1 ����ŭ stars�� �߰��Ѵ�.
				for(int j = 1; j <= 2 * userNumber - 1; j++) {
					stars += "*";
				}
			} else if(i < userNumber) {
					// ���κ�
					// 9 7 5 3 1
					for(int j = i; j <= userNumber; j++) {
						//���� ��
						stars += "*";
					}
					int upperI = i - 1;
					
					for(int j = 1; j <= 2 * upperI - 1; j++) {
						//��� ����
						stars += " ";
					}
					for(int j = i; j <= userNumber; j++) {
						//������ ��
						stars += "*";
					}
					
			} else {
				// �Ʒ��κ�
				int lowerI = i - userNumber + 1;
				for(int j = 1; j <= lowerI; j++) {
					//���� ��
					stars += "*";
				}
				// ������ ������ �� ���� ĭ �� - ���� ������ �ȴ�.
				// �ش� ���� ���� ������ ���� �� + ������ ���̰�
				// ���� ���� ������ lowerI�� �̴�.
				// �ᱹ ���� ���� ������ 2*lowerI�� �ȴ�.
				// ���� ������ ������
				// 2*userNumber-1-2*lowerI���� �ȴ�.
				// ���� ���� �� �� ������ ����
				// 2*(userNumber - lowerI) - 1�� �ȴ�. 
				for(int j = 1; j <= 2*(userNumber - lowerI) - 1; j++) {
					//��� ����
					stars += " ";
				}
				for(int j = 1; j <= lowerI; j++) {
					//������ ��
					stars += "*";
				}
			}

			System.out.println(stars);
		}
		scanner.close();
	}
}
