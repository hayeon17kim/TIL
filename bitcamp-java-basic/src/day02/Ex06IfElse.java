package day02;

// if - else ������
// ���� if���� ���ǽ��� false�� ������
// ������ else {	}�� ���� �ִ� �ڵ���� ������ �ȴ�!

// �ݴ�� if���� ���ǽ��� true�� ������
// else {	} �ȿ� �ִ� �ڵ���� ���ð� �ȴ�.

public class Ex06IfElse {
	public static void main(String[] args) {
		int age = 10;
		if(age >= 18) {
			System.out.println("����: " + age);
			System.out.println("�����Դϴ�.");
		} else {
			System.out.println("����: " + age);
			System.out.println("�̼������Դϴ�.");
		}
		System.out.println("���α׷� ����");
	}
}
