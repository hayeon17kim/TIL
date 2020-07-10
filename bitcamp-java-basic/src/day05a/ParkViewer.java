package day05a;

import java.util.Scanner;

//ȭ�鿡 ����� ����ϴ� ��� Ŭ����
//���� �����͸� �ʿ��� ������ ��Ʈ�ѷ����� ��Ź�Ѵ�.
//�Էµ� �޾ƾ� �ϴϱ� ��ĳ�ʵ� �־�� �Ѵ�.
//���� �� 2�� Ŭ���� ��ü�� �ʵ�� ���� �ִ´�.
public class ParkViewer {
	private final int SIZE = 5;
	private final int UNIT_MINUTE = 10;
	private final int UNIT_PRICE = 1000;
	private Scanner scanner;
	private ParkController controller;
	public ParkViewer() {
		scanner = new Scanner(System.in);
		controller = new ParkController();
	}
	
	//�޴� ����� ����ϴ�
	//showMenu() �޼ҵ�
	public void showMenu() {
		while(true) {
			System.out.println("===============");
			System.out.println("��Ʈ �������� ���α׷�");
			System.out.println("===============");
			System.out.println("1. ���� 2. ���� 3. ����");
			System.out.print("> ");
			int choice = scanner.nextInt();
			
			if(choice == 1) {
				//���� �޼ҵ� ȣ��
				insertPark();
				
			}else if(choice == 2) {
				//���� �޼ҵ� ȣ��
				deletePark();
				
			}else if(choice == 3) {
				System.out.println("������ּż� �����մϴ�.");
				break;
			}else {
				System.out.println("�߸� �Է��ϼ̽��ϴ�.");
				
			}
		}
	}
	
	private void deletePark() {
		//���� �����ÿ���
		//1. �����ִ� ������ �����ϴ��� üũ
		//2. �����ִ� ������ �����ϸ�
		//   ������ȣ�� �Է� �޴µ�
		//   �ش� ������ȣ�� ���� ���� ������
		//   �ٽ� �Է� �޴´�.
		//3. ���� �ð��� �Է��� �޴´�.
		//   �̶��� �ð��� �ùٸ��� �Ӹ��� �ƴ϶�
		//   ���� �ð��� �ݵ�� ���� �ð����� �ʾ�� �Ѵ�!
		//4. �ش� ������ ����� ����ؼ� �����ְ�
		//   �׸��� ����Ʈ���� �����Ѵ�.
		
		//���� ������ �԰�� �� �ִ��� üũ�Ѵ�.
		//size�� 0���� üũ�ص� ������ 
		//isEmpty()��� �޼ҵ带 ���ؼ� Ȯ���� ����
		if(controller.isEmpty()) {
			//������ ������ �����Ƿ� ��� �޽����� ����� �ش�!
			System.out.println("������ ������ �����ϴ�.");
		}else {
			//���� ������ �����Ѵ�.
			
			//������ȣ�� ������ ParkVO ��ü p�� ������
			ParkVO p = new ParkVO();
			
			System.out.print("������ ������ ��ȣ�� �Է����ּ���: ");
			scanner.nextLine();
			p.setPlateNumber(scanner.nextLine());
			
			while(!controller.contains(p)) {
				// ��Ʈ�ѷ� ���� �ʵ��� ����Ʈ�� 
				// p�� �������� �����Ƿ�
				// �ٽ� �Է¹޴´�.
				System.out.println("������ ����� ���� ��ȣ�Դϴ�.");
				System.out.println("������ ������ ��ȣ�� �Է����ּ���: ");
				p.setPlateNumber(scanner.nextLine());
			}
			
			// �Էµ� plateNumber�� ���ؼ�
			// controller ���� ����Ʈ����
			// ������ ã�ƿ´�.
			p = controller.get(p);
			
			System.out.print("���� �ð��� �Է����ּ���: ");
			int outTime = scanner.nextInt();
			while(!controller.validateTime(outTime) 
					|| p.getInTime() > outTime) {
				System.out.println("�߸��� �����Դϴ�.");
				System.out.println("���� �ð��� �Է����ּ���: ");
				outTime = scanner.nextInt();
			}
			// ���� ���� ������ ������ �ð��� �����Ƿ�
			// ��� ������ְ�
			// �ش� ������ ��Ʈ�ѷ��Ǹ���Ʈ���� �����ش�.
			calculateRate(p, outTime);
			controller.remove(p);
			
		}
	}
	
	private void calculateRate(ParkVO p, int outTime) {
		// �ð� ���̸� ����Ѵ�.
		int hourDifference = outTime / 100 - p.getInTime() / 100;
		// �� ���̸� ����Ѵ�.
		int minuteDifference = outTime % 100 - p.getInTime() % 100;
		
		// �� �ð��� ���̸��� ���� ���̷� �ٲ۴�.
		int totalDifference = hourDifference * 60 + minuteDifference;
		
		// 10�� �� 1000���� ������� ����ؼ� ����Ѵ�.
		int rate = totalDifference / UNIT_MINUTE * UNIT_PRICE;
		
		System.out.printf("�� ����� %d�� �Դϴ�. \n", rate);
		
	}
	
	private void insertPark() {
		//���� �԰�ÿ���
		//1. ��ĭ�� �ִ��� üũ
		// -> ��Ʈ�ѷ� size �޼ҵ带 �����ؼ�
		// SIZE ������� ������ üũ�ؼ�
		// ������ ����
		// �ƴϸ� ���޽��� ���
		//2. �Է¹��� ���� ��ȣ�� �����ϴ� �������� üũ
		//3. �ð��� �Է¹޾� ��ȿ�� �ð����� üũ
		//4. ��� ��ȿ�ϸ� ��Ʈ�ѷ��� ������ ����Ʈ�� �Է�
		
		//������ üũ ����
		if(controller.size() < SIZE) {
			//�����忡 ���� ���� ����
			//���� ��ȣ�� �Է¹޴´�.
			System.out.print("���� ��ȣ�� �Է����ּ���: ");
			scanner.nextLine();
			ParkVO p = new ParkVO();
			p.setPlateNumber(scanner.nextLine());
			
			while(controller.contains(p)) {
				//�̹� �����ϴ� ���� ��ȣ �̹Ƿ� �ٽ� �Է� �޴´�.
				System.out.println("�̹� ������ ��ȣ�Դϴ�.");
				System.out.print("���� ��ȣ�� �Է����ּ���: ");
				p.setPlateNumber(scanner.nextLine());
			}
			
			//�ð��� �Է� �޾Ƽ� �ùٸ� �ð����� üũ�Ѵ�.
			System.out.print("���� �ð��� �Է����ּ���: ");
			p.setInTime(scanner.nextInt());
			
			while(!controller.validateTime(p.getInTime())) {
				System.out.println("�߸��� �ð� �����Դϴ�.");
				System.out.print("���� �ð��� �Է����ּ���: ");
				p.setInTime(scanner.nextInt());
			}
			
			//�ùٸ� ��ȣ + �ð��� p�� ��������Ƿ�
			//controller�� ���ؼ� list�� �߰����ش�.
			controller.add(p);
			
			
			
		}else {
			//�������� �� á���Ƿ� ��� �޽����� ���
			System.out.println("�� ĭ�� �����ϴ�.");
		}
	}
}
















