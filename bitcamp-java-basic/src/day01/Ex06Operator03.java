package day01;

// �񱳿�����
// > >= < <= == !=
// �񱳿����ڴ�
// �� ������ ���ο� ����� ���� ���ؼ�
// ������ true, Ʋ���� false�� ������ �ȴ�.

public class Ex06Operator03 {
	public static void main(String[] args) {
		int number1 = 20;
		int number2 = 15;
		System.out.print("number1 > number2: ");
		System.out.println(number1 > number2);
		
		System.out.print("number1 >= number2: ");
		System.out.println(number1 >= number2);
		
		System.out.print("number1 < number2: ");
		System.out.println(number1 < number2);
		
		System.out.print("number1 <= number2: ");
		System.out.println(number1 <= number2);
		
		System.out.print("number1 == number2: ");
		System.out.println(number1 == number2);
		
		System.out.print("number1 != number2: ");
		System.out.println(number1 != number2);
		
		// ������ �� �����ڸ� ����� ���� �� ���� ������ ���� �ִ�.
		// �⺻�� ���������� ��Ȯ�� ���� ���� �� �ִ�.
		// ������ ���������� Ʋ�� ���� ���� �� �ִ�.
		
		// String�� ���� ���ڸ� �ѹ��� �ٷ�� Ŭ�����̴�.
		// String ������ ���� �� ���� ���� ���غ���.
		String string1 = "abc";
		String string2 = new String("abc");
		String string3 = string1;
		
		System.out.println("string1�� ���� ��: " + string1);
		System.out.println("string2�� ���� ��: " + string2);
		System.out.println("string3�� ���� ��: " + string3);
		
		System.out.print("string1 == string2: ");
		System.out.println(string1 == string2);
		
		System.out.print("string1 == string3: ");
		System.out.println(string1 == string3);
		
		System.out.print("string2 == string3: ");
		System.out.println(string2 == string3);
		
		// ���� �ڵ忡���� �츮�� ����� �Ͱ� �ٸ���
		// false, true, false�� ������ �ȴ�.
		// �� �̷� ���� ��������?
		
		// String�� Ŭ�����̴�.
		// Ŭ���� ������ ������ �����̴�.
		
		// ������ ������ �� �������̶�� �̸��� ������?
		// �⺻�� ������ ��� �ش� ������ ���� ���� �� ������ 
		// ������ ������ �ش� ������ ���� �ƴ� �ּҰ��� �� �ְ�
		// �� �ּҰ��� "����"�ؼ� �ٸ� ������ Ȯ���ؾ�
		// ���� ���� Ȯ���� �� �ִ�.
		
		// ������ �񱳿����ڴ� �ּҰ��� Ÿ�� ���� ���� ���� Ȯ���ϴ� �� �ƴ϶�
		// �ּҰ� ��ü�� ���ع�����.
		
		// ���� �츮�� ������ ������ ���� ������ �񱳿����� ��� �ٸ� ����� ����ؾ� �Ѵ�.
		// �ٷ� �ش� ������ ������ ���ǵǾ� �ִ� �޼ҵ带 ȣ���ؼ� �����ؾ� �Ѵ�.
		
		// ������ ������ ���ǵǾ� �ִ� �޼ҵ带 ȣ���� ������
		// �����̸�.�޼ҵ�()�� ȣ���ϸ� �ȴ�.
		
		// �񱳸� ���ؼ��� �츮�� equals()��� �޼ҵ带 ȣ�����ָ� �ȴ�.
		System.out.print("string1.equals(string2): ");
		System.out.println(string1.equals(string2));
	}
}
