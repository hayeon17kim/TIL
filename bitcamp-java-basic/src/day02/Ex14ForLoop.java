package day02;
//for �ݺ����� ���� ����
//1. i�� ������ �ʿ�� ������
//	  ���������� i > j > k ������ ����.

// 2. ��Ʈ���ϴ� ������ ���� �ٸ� ������ �ʱ�ȭ�ϰų�
//	    Ȥ�� ���ǽĿ� �ٸ� ������ �� �� �ִ�.
public class Ex14ForLoop {
	public static void main(String[] args) {
		for (int number = 1; number <= 3; number ++) {
			System.out.println("number�� �ݺ�!!!");
		}
		
		int start = 30;
		int max = 33;
		for (int i = start; i <= max; i++) {
			System.out.println("������ �ʱ�ȭ�ϰ� ������ ���ǽ� ����!");
		}
	}
}
