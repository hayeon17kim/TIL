package day04;

public class CarEx {
	public static void main(String[] args) {
		Car c = new Car();
		// ���� Car ��ü c�� ������ �־��.
		// ��ü ���� �޼ҵ� Ȥ�� �ʵ带 ������ ������
		// ���� ������ . �� ����Ѵ�.
		
		// null�� ��� �ƹ��͵� �� �� ����.
		// null�� �ʵ峪 ��ü�� �޼ҵ峪 �ʵ带 ȣ���ϴ� �� ��ü��
		// �Ұ����ϴ�.
		// System.out.println(c.plateNumber.equals("abc"));
		
		// ���� �Ķ���Ͱ� ���� �����ڸ� ����� �� ���
		// ������ �����Ͱ��� ���� �ʵ��
		// ���ο��� �ش� ������Ÿ���� �����ڸ� ȣ�����ִ� ����� ���� ����̴�. 
		c.setColor("�Ķ���");
		c.setPlateNumber("00�� 0000");
		c.setPrice(20000000);
		c.setType("�ҳ�Ÿ");
		c.setYear(2020);
		
		//System.out.println("������ȣ�� "+ c.plateNumber);
		System.out.println("���� ��ȣ�� " + c.getPlateNumber());
		
		// �޼ҵ带 ȣ���غ���
		// 1. �Ķ���Ͱ� ���� ��� ���� Ÿ���� ��򰡿� �� ����� �ʿ���� ����.
		c.getPlateNumber();
		String string1 = c.getPlateNumber();
		System.out.println("���� ��ȣ: " + string1);
		
		// 2. �Ķ���Ͱ� �ִ� ��� �� ���� �ݵ�� �Ķ���͸� ��ӵȴ�� �Ѱ��־�� �Ѵ�.
		// �� �Ķ���͸� �Ѱ��� ������ �̸��� �޶� �ȴ�.
		String a = "00�� 0000";
		String b = "11�� 1111";
		
		System.out.println(c.checkPlateNumber(a));
		System.out.println(c.checkPlateNumber(b));
		
		Car c2 = new Car();
		c2.setPlateNumber("99�� 9999");
		c2.setColor("������");
		c2.setPrice(10000000);
		c2.setType("���");
		c2.setYear(2020);
		
		System.out.println(c.equals(c2));		//false
		
		Car c3 = new Car();
		c2.setPlateNumber("99�� 9999");
		c2.setColor("������");
		c2.setPrice(10000000);
		c2.setType("���");
		c2.setYear(2020);
		
		System.out.println(c2.equals(c3));		//false

		// System.out.println()�� �Ķ���ͷ�
		// ��ü�� �Ѱ��ָ� System.out.println ��
		// �ش� ��ü�� toString() �޼ҵ��� �������
		// ȭ�鿡 ����ϰ� �ȴ�.
		
		System.out.println(c2);					//day04.Car@15db9742
		// day04 Ŭ���� �ȿ� Car��� ��ü�� @15db9742 (at)�޸���ġ
		// toString()�� �������غ���.

	}
}
