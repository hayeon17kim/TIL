package day04;
// �ζǹ�ȣ���۱�
// ����: ���� arrayList ���ô�.

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Ex04LottoArrayList {
	private static final int SIZE = 6;
	private static final int MAX = 45;
	
	public static void main(String[] args) {
		Random random = new Random();
		ArrayList<Integer> list = new ArrayList<>();
		// ����Ʈ�� ���� �߰��غ���
		// �� contains�� false�� ���� ���� �� �߰�
		// list�� ����� 6���� ���� ���� �� �߰��� �ϸ� �ȴ�.
		
		while(list.size() < SIZE) {
			int number = random.nextInt(MAX) + 1;
			if(!list.contains(number)) {
				list.add(number);
			}
		}
		
		// ������ �غ���.
		// �ڹٿ��� ���Ѻ���..
		
		//Collections��� �ܺ� Ŭ������ sort��� �޼ҵ尡 ���ǵǾ� �ִ�.
		//ctrl + space�� ����Ʈ�ؼ� �������
		Collections.sort(list);
		
		for(int i = 0; i < list.size(); i++) {
			System.out.printf("%d��° ����: %d\n", i, list.get(i));
		}
	}
}
