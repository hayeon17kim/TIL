package day02;
// �ڹ� �ֿܼ��� ���ڳ� ������ ����ϴ� ����� ũ�� 3������ �ִ�.
// 1. print()
//	  print()�� ��ȣ ���� ������ ����ϰ� ���� ��� ��ġ�� �ٲ��� �ʴ´�.
// 2. println()
// 	  println() print a line�� ���Ӹ��ν�, ��ȣ ���� ������ ����ϰ�
//	   ���� ��� ��ġ�� ���� �ٷ� �ٲ۴�.
// 3. printf()
//	  printf()�� print in format�� ���Ӹ��ν�, ��ȣ ���� ������ ����ϵ�
//	  % ���ڸ� �̿��ؼ� ������ �������� �� �ִ�. �� ��� ��ġ�� �ٲ��� �ʴ´�.

// 4. ���鹮��
// \n, \t ���� Ư���� ���ڸ� �̿��ؼ�
// ������ ������ �־��� �� �ִ�.
// \n�� ������ ���� �ٲ��ش�.
// \t�� �� ����(�����̽� 4�� �з�)�� �־��ش�.
public class Ex01Print {
	public static void main(String[] args) {
		
		// print
		System.out.print("A ��ų�λ�츣��\t");
		System.out.print("B ���Ű����츣��\t");
		System.out.print("C �����ұ׷糪����");
		
		// println
		System.out.println("D ���̳����");
		System.out.println("E ���󽺸��츣��");
		System.out.println("F �ĸ�����츣��");
		
		// printf
		// ���� ���� print�� ��������� ������ ������ ������ �������� �� �ִ�
		System.out.printf("G %s\n", "�����̹���");
		System.out.printf("H %20s\n", "�ϵ�λ�츣��");
		System.out.printf("%S �̱��Ƴ뵷", "i");
	}
}
