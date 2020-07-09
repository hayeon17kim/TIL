package day04;

import java.util.Random;

// �迭�� �̿��� �ζ� ��ȣ ������
// ����: �迭�� �� ���� ����
public class Ex02LottoArray {
	private static final int SIZE = 6;
	private static final int MAX = 45;
	public static void main(String[] args) {
		// �ζ� ��ȣ�� ���� int �迭�� �����.
		int[] array = new int[SIZE];
		
		// ��Ŭ�������� �ڵ��ϼ���
		// ctrl �� space�� ���ÿ� ������ �ȴ�.
		// ���� + �ڵ��ϼ� �ؼ� java.util.Random�� import ����
		Random random = new Random();
		// random�� ������ ����� �� ���̴� Random Ŭ������ ��ü�̴�.
		
		// for���� �̿��ؼ�
		// array�� ���ڸ� �־��
		for(int i = 0; i < array.length; i++) {
			array[i] = random.nextInt(MAX) + 1;
			// random�� nextInt �޼ҵ��
			// �Ķ���ͷ� ���ڰ� ����
			// 0~����-1 ������ ���� 
			// �����ϰ� ������ �ȴ�.
			// ���� �� ����� + 1 �� �ؼ�
			// 1~���� ���� ������ ������ش�.
		}
		
		System.out.println("======��ȣ ���� ����=======");
		for(int i = 0; i < array.length; i++) {
			System.out.printf("array[%d]: %d\n", i, array[i]);
		}
		System.out.println("=======================");
		
		// �ߺ� ���Ÿ� �ϴ� �ڵ�
		// �ߺ��̶�?
		// �ε����� �ٸ����� �迭�� ���� ���� ���
		// ��ø for���� ����ؼ�
		// i�� j�� �ٸ����� array[i]�� array[j]�� ���� ���
		// i�� ���� �ٽ� �ְ�
		// �׸��� ó������ ���ư��� �ٽ� �˻��ϴ� �ڵ带 ������
		
		for(int i = 0; i < array.length; i++) {
			for(int j = 0; j < array.length; j++) {
				if(i != j && array[i]==array[j]) {
					array[i] = random.nextInt(MAX) + 1;
					j = -1;
				}
			}
		}
		
		System.out.println("======�ߺ� ���� ����=======");
		for(int i = 0; i < array.length; i++) {
			System.out.printf("array[%d]: %d\n", i, array[i]);
		}
		System.out.println("=======================");
		
		// ������ �غ���
		// �������� ���� ��
		// ���� i��°�� ���� i+1��°���� ũ��
		// 2���� ������ �ٲ��ְ� i�� �� ó������ ������ �ȴ�.
		
		for(int i = 0; i< array.length - 1; i++) {
			if(array[i] > array[i+1]) {
				// 2���� ������ �ٲ��ָ� �ȴ�.
				int temp = array[i];
				array[i] = array[i+1];
				
				array[i+1] = temp;
				i = -1;
			}
		}
		
		System.out.println("========���� ����========");
		for(int i = 0; i < array.length; i++) {
			System.out.printf("array[%d]: %d\n", i, array[i]);
		}
		System.out.println("=======================");
	}
}
