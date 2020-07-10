package day04;

import java.util.ArrayList;

// �ڹٿ����� �迭�� Ŭ����ȭ ��Ų
// ArrayList��� Ŭ������ �ִ�.
// �츮�� ��Ÿ� ���� ���ϰ� �� �� �ִ�.
public class Ex03ArrayList {
	public static void main(String[] args) {
		ArrayList<Integer> list = new ArrayList<>();
		//���������� ��̸���Ʈ�� Ŭ���� ��ü�� �ٷ�� ������
		//�ش� ��̸���Ʈ�� �ٷ� ������Ÿ����
		//< >�� �־�� �Ѵ�.
		//�׷��ٸ� �츮�� �⺻�� ������Ÿ���� �ٷ� �� ������? -> x
		//�׷��� �ڹٴ� �׷��� �⺻�� ������Ÿ���� Ŭ����ȭ ��Ų
		//���� Ŭ����(wrapper class)�� �غ��س���!
		//int -> Integer, long -> Long, double -> Double ���
		
		ArrayList<Car> carList = new ArrayList<>();
		
		//List�� ��ü�� �߰��� ������ add(��ü) �޼ҵ带 �����ϸ� �ȴ�.
		Car c1 = new Car("00�� 0000", "������", "�ҳ�Ÿ", 2000, 2020);
		carList.add(c1);
		//List�� ���� �߰��Ǿ��ִ� ��ü�� ������ Ȯ���Ҷ�����
		//size() �޼ҵ带 �����ϸ� �ȴ�.
		System.out.println("carList�� ���� ũ��: "+carList.size());
		
		
		Car c2 = new Car("00�� 0001", "������", "�ҳ�Ÿ", 2000, 2020);
		carList.add(c2);
		System.out.println("carList�� ���� ũ��: "+carList.size());
		
		Car c3 = new Car("00�� 0002", "������", "�ҳ�Ÿ", 2000, 2020);
		carList.add(c3);
		System.out.println("carList�� ���� ũ��: "+carList.size());
		
		Car c4 = new Car("00�� 0003", "������", "�ҳ�Ÿ", 2000, 2020);
		carList.add(c4);
		System.out.println("carList�� ���� ũ��: "+carList.size());
		
		Car c5 = new Car("00�� 0004", "������", "�ҳ�Ÿ", 2000, 2020);
		carList.add(c5);
		System.out.println("carList�� ���� ũ��: "+carList.size());
		
		//���࿡ Ư�� ��ġ�� ��ü�� ����ְ� ������
		//list.add(��ġ, ��ü)�� �����ϸ� �ȴ�.
		Car c6 = new Car("00�� 0005", "������", "�ҳ�Ÿ", 2000, 2020);
		//�߰����� 2��°�� �ִ� ��ü�� ��:
		
		//Ư�� ��ġ�� ��ü�� ȣ���� ������
		//get(index)�� ȣ�� �����ϴ�.
		System.out.println(carList.get(2));
		
		//index 2 �� c6�� ����ֱ��� ��
		carList.add(2, c6);
		System.out.println(carList.get(2));
		
		//ArrayList�� Ŭ������ equals()�޼ҵ带 ���������� ���ο��� Ȱ���Ѵ�.
		//�����е��� ArrayList�� ��� �޼ҵ���� �� ����� Ȱ���ϰ� ������
		//�����е��� Ŭ������ equals()�޼ҵ带 ����� ���� �ſ� �߿��ϴ�!!!!
		
		//1. ����Ʈ�� �ش� ��ü�� ������ �ִ��� Ȯ���� ������
		//contains()��� �޼ҵ带 �����ϸ� �ȴ�.
		Car c7 = new Car("00�� 0007", "������", "�ҳ�Ÿ", 2000, 2020);
		Car c8 = new Car("00�� 0001", "������", "�ҳ�Ÿ", 2000, 2020);
		System.out.println("carList.contains(c8)"+carList.contains(c8)); 
		System.out.println("carList.contains(c7)"+carList.contains(c7)); 
		
		//2. ����Ʈ���� �ش� ��ü�� index ��ȣ�� ã�� ������ 
		//indexOf(��ü)�� �����ϸ� �ȴ�.
		//���� �ش� ��ü�� ����Ʈ�� �������� ���� ������
		//-1�� ������ �ȴ�.
		System.out.println("carList.indexOf(c8): "+carList.indexOf(c8));
		System.out.println("carList.indexOf(c7): "+carList.indexOf(c7));
		
		//3. ���࿡ ���� �������� �ִ� �ε����� ã�� ������
		//lastIndexOf(��ü)�� �����ϸ� �ȴ�.
		//indexOf�� ����������
		//�������� ���� �ÿ���
		//-1�� ������ �ȴ�.
		System.out.println("carList.lastIndexOf(c8): "+carList.lastIndexOf(c8));
		System.out.println("carList.lastIndexOf(c7): "+carList.lastIndexOf(c7));
		
		//4. ����Ʈ���� ��ü�� ������ ������
		//2���� ������� ���Ű� �����ϴ�.
		//A. �ε��� ��ȣ�� �����ϱ�
		System.out.println("carList.get(0): "+carList.get(0));
		//�ε����� ������ ������ remove(�ε���) �� �ϸ� �ȴ�.
		carList.remove(0);
		System.out.println("carList.get(0): "+carList.get(0));
		
		//B. �ش� ��ü�� equals�� true�� ������ ��ü�� 
		//remove�� �Ķ���ͷ� �Ѱ��ִ� ���
		System.out.println("carList.contains(c8): "+carList.contains(c8));
		carList.remove(c8);
		System.out.println("carList.contains(c8): "+carList.contains(c8));
	}
}









