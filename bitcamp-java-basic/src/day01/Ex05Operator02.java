package day01;
// ����������
// ���������ڴ� ������ ����� ���� ��Ȯ�ϰ� 1���� �ٲ۴�.
// ++�� 1 ����
// --�� 1 ����
// �� ���������ڴ� ������ �տ� �ٳ� �ڿ� �ٳĿ� ����
// �ǹ̰� �޶����� �ȴ�!

public class Ex05Operator02 {
	public static void main(String[] args) {
		int number = 10;
		System.out.println("number�� ���簪: " + number);
		// ���������ڰ� ������ �տ� �ٰ� �Ǹ�
		// �ش� �ٿ��� �켱������ ���� ���� �ȴ�
		// �� ���� ���� ������ �ȴ�
		System.out.println("number�� ���簪: " + ++number);
		// ���� �ڵ�� ũ�� 3���� �ܰ�� ������.
		// 1. ++number -> number�� ���簪�� 1 �÷��� -> 11�� �ȴ�.
		// 2. "number�� ���簪: " + ++number
		//	-> number�� ���簪: �ڿ� number�� ���簪�� 1 �ø� ���� �ٿ���
		//	-> number�� ���簪: 11 
		// 3. System.out.println("number�� ���簪: " + ++number);
		//	-> ��ȣ ���� ������ ȭ�鿡 ����ض�

		System.out.println("number�� ���簪: " + number);
		
		// ������ ++�� ������ �ڿ� ������
		// �켱������ ���� ��������.
		// �� ���� �������� ����ȴ�.
		System.out.println("number�� ���簪: " + number++);
		
		// ���� �ڵ��
		// 1. "number�� ���簪: " �ڿ� number�� �ٿ��ش�.
		// 2. ȭ�鿡 ��ȣ ���� ������ ����Ѵ�.
		// 3. number�� ���簪�� 1 ������Ų��.
		System.out.println("number�� ���簪: " + number);
		
		// ++�� 1 ����
		// --�� 1 ����
		// **, //, %%�� �������� �ʴ´�.
	}
}
