package day02;

import java.util.Scanner;

// �ڹٿ��� �Է���
// �츮�� ���� ����� �� �ƴ϶�
// Scanner ��� Ŭ������ ����(=��ü)��
// �޼ҵ带 ����ؼ�
// �Է��� �ް� �ȴ�.
// �� Scanner �� �ܺ� Ŭ�����̱� ������
// �츮�� "����"�� �;� �Ѵ�.
public class Ex03Scanner {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		// ������ �Է¹��� ������
		// nextInt() �޼ҵ带 �������ָ� �ȴ�
		System.out.print("������ �Է��ϼ���: ");
		int myNumber = scanner.nextInt();
		System.out.println("myNumber�� ���簪: " + myNumber);
		
		// �Ǽ��� �Է¹��� ������
		// nextDouble() �޼ҵ带 �������ָ� �ȴ�.
		System.out.print("�Ǽ��� �Է��ϼ���: ");
		double myDouble = scanner.nextDouble();
		System.out.println("myDouble�� ���簪: " + myDouble);
		
		// String (=���� ���� ���ڰ� �� �ִ� ��)�� �Է� ���� ������
		// nextLine()�� �������ָ� �ȴ�.
		// ��!
		// �� ���� ������ ���� �ִµ�
		// nextInt()�� nextDouble()�� �����ϰ� ���� nextLine()�� �� ������
		// nextLine()�� �� �� �ܵ� �����Ű�� ���� �Է��� �޾ƾ� �Ѵٴ� ���̴�.
		// �ֳ��ϸ� �츮�� �Է��� ������ �� ���� ����Ű�� �޸𸮿� ���� �ֱ� �빮��
		// nextLine()�� ���װ� ����� �����̴�.
		
		// nextInt()�� �츮�� 4\n�� ������
		// \n�� ������ �ƴ϶� �����̱� ������
		// \n�� ���۸޸𸮿� ����ȴ�.
		// ���� nextDouble()�� 12.345\n�� ������
		// ���� ���۸޸𸮿� \n12.345\n�� �� �ִ� ��Ȳ�̴�.
		// ó�� \n�� �����ϰ�
		// 12.345�� nextDouble�� ��������.
		// �׷��ٸ� ���۸޸𸮿� ������ �����ִ°�?
		// \n 2���� �����ִ�.
		// ������ nextLine�� ���ڰ� ���� ���� �� ������ �´�.
		// �ٵ� \n�� ���� ����
		// nextLine
		
	
		// nextLine�� �ܵ� �����Ű�� ����� �ſ� �����ϴ�.
		// �׳� scanner.nextLine()�� �����ָ� �ȴ�.
		// ��!
		// ��Ŭ���� �ڵ��ϼ� ����� abc ���ĺ� ������ �ϼ���Ű�� ������
		// scanner.nextLine�� ������
		// �ڵ����� �ڱⰡ hasNextLine�̶�� �ٸ� �޼ҵ�� ���������.
		// scanner.nextLine�� �����ִ����� Ȯ���ϴ� ���� �߿��ϴ�!
		
		System.out.print("���ڵ��� �Է��ϼ���: ");
		String myString = scanner.nextLine();
		System.out.println("myString�� ���簪: " + myString);
		System.out.println("���α׷� ����! ");
		
		// ��ĳ�ʿ� ���� �޸𸮸� �����ϴ� Ŭ��������
		// close()��� �޼ҵ尡 �ִµ�
		// ����� ���� �Ŀ� �ش� close() �޼ҵ带 ȣ�����ִ� ����
		// ���� ����̴�.
		scanner.close();
	}
}
