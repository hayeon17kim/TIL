package day05;

import java.util.Scanner;

// ȭ�鿡 ����� ����ϴ� ��� Ŭ����
// ���� �����͸� �ʿ�� �� ���� ��Ʈ�ѷ����� ��Ź�Ѵ�.
// �Էµ� �޾ƾ� �ϴϱ� ��ĳ�ʵ� �־�� �Ѵ�.
// ���� �� 2�� Ŭ���� ��ü�� �ʵ�� ���� �ִ�.
public class ParkViewer_mine {
	private final int SIZE = 5;
	private final int UNITTIME = 10;
	private final int PRICE = 1000;
	private Scanner scanner;
	private ParkController controller;
	public ParkViewer_mine() {
		scanner = new Scanner(System.in);
		controller = new ParkController();
	}
	
	// �޴� ����� ����ϴ�
	// showMenu() �޼ҵ�
	public void showMenu() {
		while(true) {
			System.out.println("============");
			System.out.println("�������� ���α׷�");
			System.out.println("============");
			System.out.println("1. ����  2. ����  3. ����");
			System.out.println("> ");
			int choice = scanner.nextInt();
			
			if(choice == 1) {
				// ���� �޼ҵ� ȣ��
				insertPark();
			} else if(choice == 2) {
				// ���� �޼ҵ� ȣ��
				extractPark();
			} else if(choice == 3) {
				System.out.println("������ּż� �����մϴ�.");
				break;
			} else {
				System.out.println("�߸� �Է��ϼ̽��ϴ�.");
			}
		}
	}
	private void insertPark() {
		// ���� �԰�ÿ���
		// 1. �� ĭ�� �ִ��� üũ
		// -> ��Ʈ�ѷ� size �޼ҵ带 �����ؼ�
		// SIZE �Ǽ����� ������ üũ�ؼ�
		// ������ ����
		// �ƴϸ� ��� �޽��� ���
		// 2. �Է� ���� ���� ��ȣ�� �����ϴ� �������� üũ
		// 3. �ð��� �Է� �޾� ��ȿ�� �ð����� üũ
		// 4. ��� ��ȿ�ϸ� ��Ʈ�ѷ��� ������ ����Ʈ�� �Է�
		if(controller.size() < SIZE) {
			// �����忡 �������� ����
			System.out.print("���� ��ȣ�� �Է����ּ���: ");
			scanner.nextLine();		// ��ĳ�� ���� ����
			ParkVO p = new ParkVO();
			p.setPlateNumber(scanner.nextLine());
			
			while(controller.contains(p)) {
				System.out.println("�̹� ������ ��ȣ�Դϴ�.");
				System.out.print("���� ��ȣ�� �Է����ּ���: ");
				p.setPlateNumber(scanner.nextLine());
			}
			
			// �ð��� �Է� �޾Ƽ� �ùٸ� �ð����� üũ�Ѵ�.
			
			System.out.print("���� �ð��� �Է����ּ���: ");
			p.setInTime(scanner.nextInt());
			
			while(!controller.validateTime(p.getInTime())) {
				System.out.println("�߸��� �ð� �����Դϴ�.");
				System.out.print("���� �ð��� �Է����ּ���: ");
				p.setInTime(scanner.nextInt());
			}
			
			// �ùٸ� ��ȣ + �ð��� p�� ��������Ƿ�
			// controller�� ���ؼ� list�� �߰��Ѵ�. 
			controller.add(p);
		} else {
			System.out.println("�� ĭ�� �����ϴ�.");
		}
	}
	
	private void extractPark() {
		// ���� �԰� �ÿ��� 
		// 1. ������ �� �ִ��� üũ
		// -> ��Ʈ�ѷ� size �޼ҵ带 �ҷ��� ����
		// 0���� ũ�� ������ȣ �Է¹ޱ�
		// �׷��� ������ ������ ������ �����ϴ� ���ϱ�
		if(controller.size() > 0) {
			System.out.print("���� ��ȣ�� �Է����ּ���: ");
			scanner.nextLine();		// ��ĳ�� ���� ����
			ParkVO p = new ParkVO();
			p.setPlateNumber(scanner.nextLine());
			

			while(!controller.contains(p)) {
				System.out.println("�ش� ������ �����ϴ�.");
				System.out.print("���� ��ȣ�� �Է����ּ���: ");
				p.setPlateNumber(scanner.nextLine());
			}
			
			controller.take(p);
			
			System.out.println("���� �ð���: " + p.getInTime());
			
			System.out.print("���� �ð��� �Է����ּ���: ");
			p.setOutTime(scanner.nextInt());
			
			System.out.println("���: " + charge(p.getInTime(), p.getOutTime()) + "��");
			
			controller.remove(p);
			
		} else {
			System.out.println("������ ������ �����ϴ�.");
		}
	}
	
	public int charge(int inTime, int outTime) {
		int subTime = outTime - inTime; 
		int subHour = subTime / 100;
		int subMinute = subTime % 100;
		
		return (subHour * 60 + subMinute) / UNITTIME * PRICE ;
	}
	

}
