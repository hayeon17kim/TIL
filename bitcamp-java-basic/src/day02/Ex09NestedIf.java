package day02;
// ��ø if���� ����ؾ�
// ������� ���Ǽ��� �츮�� ������ �� �ִ�.

// �̹� ����������
// ����ڷκ��� ���� �Է¹޾Ƽ�
// ����, ����, �����ǹ�x 3������ ����ϴ� ���α׷��� ���� ���̴�.

import java.util.Scanner;
public class Ex09NestedIf {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("������ �Է����ּ���(1-> ����   2-> ����): ");
		int choice = scanner.nextInt();
		if (choice == 1) {
			// �����̹Ƿ� ���̸� �Է¹޴´�.
			System.out.print("���̸� �Է����ּ���: ");
			int age = scanner.nextInt();
			if (age > 18) {
				//����+�����̹Ƿ� ��ü ����� �Է� �޴´�.
				System.out.print("��ü ����� �Է����ּ���: ");
				int category = scanner.nextInt();
				if (category <= 3 && category >=1) {
					System.out.println("���� �Դϴ�.");
				} else if (category == 4){
					System.out.println("���� �Դϴ�.");
				} else {
					System.out.println("�����Դϴ�.");
				}
			} else {
				System.out.println("�̼������̹Ƿ� ���� ������ �ǹ��� �����ϴ�.");
			}
		} else {
			if (choice == 2) {
				System.out.println("�����̹Ƿ� ������ �ǹ��� �����ϴ�.");
			} else {
				System.out.println("�߸� �Է��ϼ̽��ϴ�.");
			}
		}
		System.out.println("���α׷��� ����˴ϴ�.");
		
		scanner.close();
	}
}
