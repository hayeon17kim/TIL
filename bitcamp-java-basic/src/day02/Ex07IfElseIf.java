package day02;
// if-else if ����
// if else if ������
// if ������ üũ�ؼ� false�̸�
// �� ������ �ִ� else if ������ üũ�Ѵ�.
// ���� �ش� else if ���ǵ� false�� ������
// �� ������ �ִ� else if�� ������ üũ�Ѵ�.
// �׷��ٰ� ��ü�� ���� ������ false�̸�
// else�� �����Ѵ�. 

public class Ex07IfElseIf {
	public static void main(String[] args) {
		int age = 3;
		// �̹����� �ܼ��� ���ΰ� �̼����ڷ� ������ ����
		// ���̿� ���� ����-���-û�ҳ�-���� 4�ܰ�� ���� ����.
		
		if(age >= 18) {
			System.out.println("�����Դϴ�.");
		} else if(age >= 14) {
			System.out.println("û�ҳ��Դϴ�.");
		} else if(age >= 6) {
			System.out.println("����Դϴ�.");
		} else {
			System.out.println("�����Դϴ�.");
		}
		
		System.out.println("���α׷� ����");
	}
}
