package day04;

import java.util.Scanner;
public class Ex01Array {
	public static void main(String[] args) {	// �޼ҵ�� + ctrl + space + enter
		// �迭�̶� �Ȱ��� ������Ÿ����
		// �Ѱ��� �̸����� ������ �����ϴ� ������Ÿ���̴�.
		// ������Ÿ��[] �迭�̸� = new ������Ÿ��[ũ��]
		int[] arr = new int[5];
		System.out.println(arr.length);
		// ���� �ڵ�� 
		// int ���� 5���� ���� �� �ִ� �迭 arr �� ����� �ڵ��̴�. 
		
		// �迭�� �� ��ġ�� index ��� ��ġ��ȣ�� ���ؼ� ���� �����ϴ�.
		// index�� 0���� (�迭�� ũ�� - 1)�����̴�.
		
		// �迭�� �� ��ġ�� �츮�� ��ȣ�� �����ָ� �ϳ��� ����ó�� ����� �����ϴ�.
		arr[0] = 30;
		System.out.println("0��° ��: " + arr[0]);
		
		// ������ �迭�� ������ 2���� �ִ�.
		// 1. ũ�Ⱑ �����Ǿ� ����
		for(int i = 0; i < arr.length; i++) {
			System.out.println(arr[i]);
		}
		
		// ���� �츮�� �迭�� �������� �ʴ� ��ȣ�� ���� �Ϸ��� �Ѵٸ�?
		//System.out.println(arr[30]);
		// ���� �ڵ�� ������ ����. �ֳ��ϸ� �迭�� ũ�Ⱑ 5�ε�
		// index 30�̶�� ������ ��� ��ġ�� �����Ϸ��� �߱� �����̴�.
		// �� �츮�� ���ڸ� �Է¹޾Ƽ� �����ϴ� ���α׷��� �ۼ��Ϸ��� �Ѵٸ�
		// �迭�� ����� Ƚ���� ���ѵȴ�.
		
		// [����] �����Ҵ�� �����Ҵ��� ���� ����
		
		Scanner scanner = new Scanner(System.in);
		// 2. Ŭ�������� �ռ� ���Ա� ������ Ŭ������ ��ģȭ����
		Car[] carArray = new Car[300];
		for(int i = 0; i < carArray.length; i++) {
			System.out.print("ù��° ���� ������ȣ�� �Է��ϼ���: ");
			carArray[i].setPlateNumber(scanner.nextLine());
		}
		// ���� �ڵ忡�� null pointer exception�� �߻��Ѵ�.
		// ��ü�� �迭�� ��� �ش� ��ġ�� �ٽ� ������ ȣ���� �ؼ�
		// ����� �غ� �ؾ� �Ѵ�.
		
		scanner.close();
	}
}
