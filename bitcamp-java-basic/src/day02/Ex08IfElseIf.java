package day02;
// ����ڷκ��� ������ �Է¹޾Ƽ�
// 90~100: A
// 80~89: B
// 70~79: C
// 60~69: D
// ~59: F
// �� ��µǴ� ���α׷� �ۼ�

import java.util.Scanner;

public class Ex08IfElseIf {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("������ �Է����ּ���: ");
		int score = scanner.nextInt();
		
		System.out.println("������ ������ ���� ���� ����");
		
		if(score >= 90) {
			System.out.println("A");
		} else if (score >= 80) {
			System.out.println("B");
		} else if (score >= 70) {
			System.out.println("C");
		} else if (score >= 60) {
			System.out.println("D");
		} else {
			System.out.println("E");
		}
		
		System.out.println("======================");
		// �� �ڵ�� �� ���� ������ �ִ�.
		// ����ڰ� �Է��� ������ 100���� �ʰ��ϰų� 0�� �̸��̴���
		// A Ȥ�� F�� ����Ѵ�.
		
		// �̷� ���� 2���� ����� ���ؼ� �ذ��� �����ϴ�.
		
		// 1. ���ǽ��� �����ϰ� ��´�. 
		
		System.out.println("���ǽ��� �����ϰ� ���� ����");
		
		if(score >= 90 && score <= 100) {
			System.out.println("A");
		} else if (score >= 80 && score <= 89) {
			System.out.println("B");
		} else if (score >= 70 && score <= 79) {
			System.out.println("C");
		} else if (score >= 60 && score <= 69) {
			System.out.println("D");
		} else if (score >= 50 && score <= 59) {
			System.out.println("E");
		} else {
			System.out.println("�߸� �Է��ϼ̽��ϴ�.");
		}
		
		System.out.println("======================");
		
		System.out.println("�Է°��� �� ���� �� �ùٸ� ������ �ڵ� ����");
		// �� �ڵ�� ��ø if��(Nested If)���ν�
		// if ������ ������ ������
		// �ٽ� �� ������ ����
		// �ٸ� if ���ǹ��� �����ϰ� �ȴ�.
		// 2. 
		if (score >= 0 && score <= 100) {
			// ���� ���ǽ��� true�� ���Դٴ� ���� 
			// ������ �ùٸ� �����̴ٴ� ���� ����
			// ���� ���ǽ��� ����������.
			if(score >= 90) {
				System.out.println("A");
			} else if (score >= 80) {
				System.out.println("B");
			} else if (score >= 70) {
				System.out.println("C");
			} else if (score >= 60) {
				System.out.println("D");
			} else {
				System.out.println("E");
			}
		} else {
			System.out.println("�߸� �Է��ϼ̽��ϴ�.");
		}
		
		scanner.close();
	}
	



}
