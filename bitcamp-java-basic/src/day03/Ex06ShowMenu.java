package day03;

import java.util.Scanner;

// ���ѷ����� �̿��ؼ� �޴��� ������. 
public class Ex06ShowMenu {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		while(true) {
			System.out.println("��Ʈ ����б� ���� ���� ���α׷�");
			System.out.println("1. �Է�  2. ����  3. ���  4. ����  5. ����");
			System.out.print("> ");
			int choice = scanner.nextInt();
			if (choice == 1) {
				// �л� ���� �Է��ϴ� �ڵ� ����
			} else if (choice == 2) {
				// �л� ���� �����ϴ� �ڵ� ����
			} else if (choice == 3) {
				// �л� ���� ����ϴ� �ڵ� ����
			} else if (choice == 4) {
				// �л� ���� �����ϴ� �ڵ� ����
			} else if (choice == 5) {
				System.out.println("������ּż� �����մϴ�.");
				break;
			} else {
				System.out.println("�߸� �Է��ϼ̽��ϴ�.");
			}
		}
		
		scanner.close();
	}
}
