package day02;
// �ǵ������� break�� �����ؼ�
// �ڵ带 �����ϰ� ����� ����

// ����ڷκ��� ���� �Է¹޾Ƽ�
// �ش� ���� �� �ϱ��� �ִ��� �����ִ� ���α׷�

import java.util.Scanner;
public class Ex11Switch {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("���� �Է����ּ���: ");
		int month = scanner.nextInt();
		if(month >= 1 && month <= 12) {
			switch(month) {
			// �ǵ������� break�� �����ؼ�
			// �ش� ���� ������ ���� ���� �޳��� ��Ƴ���
			// ���� ������ ���̽����� �� �ϱ��������� ����ϰ�
			// �ٸ� �޵��� �ƹ� �͵� ���� �ʴ´�.
			case 2:
				System.out.println("28�ϱ��� �Դϴ�.");
				break;
			case 4:
			case 6:
			case 9:
			case 11:
				System.out.println("30�ϱ����Դϴ�.");
				break;
			default:
				System.out.println("31�ϱ����Դϴ�.");
				break;
			}
		} else {
			System.out.println("�߸� �Է��ϼ̽��ϴ�.");
		}

		scanner.close();
	}
}
// swtich�� ���̽��� ���� �� ��ȿ����