package day02;
// ����ڷκ��� ���ڸ� �Է� �޾Ƽ�
// 1~�� �������� ���� ���ϴ� ���α׷��� �ۼ��Ͻÿ�.
// �� �� ���� 15���� �۾ƾ� �Ѵ�. 
import java.util.Scanner;
public class Ex16ForLoop {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("���ڸ� �Է����ּ���: ");
		int result = 1;
		int userNumber = scanner.nextInt();
		if (userNumber < 15) {
			for (int i=1; i <= userNumber; i++) {
				result *= i;
			}
			System.out.println("1���� " + userNumber + "������ ����: " + result);
		} else {
			System.out.print("15���� ũ�ų� ������ �۵����� �ʽ��ϴ�.");
		}
		scanner.close();
	}
}
