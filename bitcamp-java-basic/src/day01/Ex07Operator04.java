package day01;

// ��������
// �������ڴ� 2������ boolean ���� ���� ������ �Ѵ�.
// AND OR ���� 3������ �ִ�

// AND: shift+7 �� ������ && �� �����ش�.
// 		AND ������ 2���� boolean�� ���� ��� true�� ����
//		true�� ���´�.

// OR:	shift+\\ �� ������ || �� �����ش�.
//		\�� �ѱ� Ű���忡�� ��ȭǥ���̴�.
//		OR ������ 2���� boolean �� �ϳ��� true�� true�� ���´�.

// ����:	! �� �����ش�.
//		���������� true�� false��, false�� true�� �ٲ��ش�.

public class Ex07Operator04 {
	public static void main(String[] args) {
		
		boolean boolean1 = true;
		boolean boolean2 = false;
		
		System.out.print("boolean1 && boolean2: ");
		System.out.println(boolean1 && boolean2);
		
		System.out.print("boolean1 || boolean2: ");
		System.out.println(boolean1 || boolean2);
		
		System.out.print("!boolean1: ");
		System.out.println(!boolean1);
		
	}
}

// 