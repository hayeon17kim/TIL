package day02;
// ����ڷκ��� ���ڸ� �ϳ� �Է¹޾Ƽ�
// 1���� �� ���ڱ����� ���� �����ִ� ���α׷�

import java.util.Scanner;

public class Ex15ForLoop {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int sum = 0;
		System.out.print("���ڸ� �Է����ּ���: ");
		int userNumber = scanner.nextInt();
		for (int i= 1; i <= userNumber; i++) {
			// sum = sum + i;
			// ���� �ڵ�� �Ʒ� �ڵ�� ª�� �ٿ��� �� �� �ִ�.
			sum += i;
			// ���� -, *, / ��� �����ϴ�.
		}
		System.out.println("1���� " + userNumber + "������ ��: " + sum);
		scanner.close();
	}
}
