package day03;
// ����ڷΔ� ������ �Է¹޵�
// �ùٸ��� ���� ������ ���� ���
// �ùٸ� ������ �Է��� �޶�� �ݺ��ϴ� ���α׷�
import java.util.Scanner;
public class Ex04While {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("������ �Է��ϼ���: ");
		int score = scanner.nextInt();
		
		while(score < 0 || score > 100) {
			System.out.println("�߸� �Է��ϼ̽��ϴ�.");
			System.out.print("������ �Է��ϼ���: ");
			score = scanner.nextInt();
		}
		
		System.out.println("����ڰ� �Է��� ����: " + score);
		
		scanner.close();
	}
}
