package day02;

import java.util.Scanner;

// ����ڷκ���
// �̸�, �г�, ����, ����, ���� ������ �Է¹޾Ƽ�
// ����ϴ� ���α׷��� �ۼ��ϼ���.
// ����� ������ ����, ����, ���� ������ 3�ڸ��� ���߰� ���ʿ� �� ������ ���� �ÿ��� 0���� ä���
// ����, ����, ������ ���� �� ������ ����� ����ϵ�
// ������ 3�ڸ�, ���� ���鿡�� 0�� �ְ�
// ����� �Ҽ��� 2��° �ڸ����� ��µǰ� ���α׷��� �ۼ��ϼ���.
public class Ex04Scanner {
	public static void main(String[] args) {
		

		Scanner scanner = new Scanner(System.in);
		
		System.out.print("�̸�: ");
		String name = scanner.nextLine();
		
		System.out.print("�г�: ");
		int grade = scanner.nextInt();
		
		System.out.print("���� ����: ");
		int korean = scanner.nextInt();
		
		System.out.print("���� ����: ");
		int english = scanner.nextInt();
		
		System.out.print("���� ����: ");
		int math = scanner.nextInt();
		
		int sum = korean + english + math;
		
		System.out.println("�̸�: "+name+", �г�: " + grade+"�г�");
		System.out.printf("����: %d��, ����:%d��, ����:%d�� \n", korean, english, math);
		System.out.printf("����: %03d��, ���: %.2f��", sum, sum/3.0);
		
		scanner.close();
	}
	
	
}
