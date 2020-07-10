package day05;

import java.util.Scanner;

// 화면에 출력을 담당하는 뷰어 클래스
// 만약 데이터를 필요로 할 때는 컨트롤러에게 부탁한다.
// 입력도 받아야 하니까 스캐너도 있어야 한다.
// 따라서 이 2개 클래스 객체를 필드로 갖고 있다.
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
	
	// 메뉴 출력을 담당하는
	// showMenu() 메소드
	public void showMenu() {
		while(true) {
			System.out.println("============");
			System.out.println("주차관리 프로그램");
			System.out.println("============");
			System.out.println("1. 입차  2. 출차  3. 종료");
			System.out.println("> ");
			int choice = scanner.nextInt();
			
			if(choice == 1) {
				// 입차 메소드 호출
				insertPark();
			} else if(choice == 2) {
				// 출차 메소드 호출
				extractPark();
			} else if(choice == 3) {
				System.out.println("사용해주셔서 감사합니다.");
				break;
			} else {
				System.out.println("잘못 입력하셨습니다.");
			}
		}
	}
	private void insertPark() {
		// 차량 입고시에는
		// 1. 빈 칸이 있는지 체크
		// -> 컨트롤러 size 메소드를 실행해서
		// SIZE 실수보다 작은지 체크해서
		// 작으면 실행
		// 아니면 경고 메시지 출력
		// 2. 입력 받은 차량 번호가 존재하는 차량인지 체크
		// 3. 시간을 입력 받아 유효한 시간인지 체크
		// 4. 모두 유효하면 컨트롤러로 보내서 리스트에 입력
		if(controller.size() < SIZE) {
			// 주차장에 여유공간 존재
			System.out.print("차량 번호를 입력해주세요: ");
			scanner.nextLine();		// 스캐너 버그 방지
			ParkVO p = new ParkVO();
			p.setPlateNumber(scanner.nextLine());
			
			while(controller.contains(p)) {
				System.out.println("이미 입차된 번호입니다.");
				System.out.print("차량 번호를 입력해주세요: ");
				p.setPlateNumber(scanner.nextLine());
			}
			
			// 시간을 입력 받아서 올바른 시간인지 체크한다.
			
			System.out.print("입차 시간을 입력해주세요: ");
			p.setInTime(scanner.nextInt());
			
			while(!controller.validateTime(p.getInTime())) {
				System.out.println("잘못된 시간 형식입니다.");
				System.out.print("입차 시간을 입력해주세요: ");
				p.setInTime(scanner.nextInt());
			}
			
			// 올바른 번호 + 시간이 p에 들어있으므로
			// controller를 통해서 list에 추가한다. 
			controller.add(p);
		} else {
			System.out.println("빈 칸이 없습니다.");
		}
	}
	
	private void extractPark() {
		// 차량 입고 시에는 
		// 1. 주차된 게 있는지 체크
		// -> 컨트롤러 size 메소드를 불러와 실행
		// 0보다 크면 차량번호 입력받기
		// 그렇지 않으면 주차된 차량이 없습니다 말하기
		if(controller.size() > 0) {
			System.out.print("차량 번호를 입력해주세요: ");
			scanner.nextLine();		// 스캐너 버그 방지
			ParkVO p = new ParkVO();
			p.setPlateNumber(scanner.nextLine());
			

			while(!controller.contains(p)) {
				System.out.println("해당 차량이 없습니다.");
				System.out.print("차량 번호를 입력해주세요: ");
				p.setPlateNumber(scanner.nextLine());
			}
			
			controller.take(p);
			
			System.out.println("입차 시간은: " + p.getInTime());
			
			System.out.print("출차 시간을 입력해주세요: ");
			p.setOutTime(scanner.nextInt());
			
			System.out.println("요금: " + charge(p.getInTime(), p.getOutTime()) + "원");
			
			controller.remove(p);
			
		} else {
			System.out.println("주차된 차량이 없습니다.");
		}
	}
	
	public int charge(int inTime, int outTime) {
		int subTime = outTime - inTime; 
		int subHour = subTime / 100;
		int subMinute = subTime % 100;
		
		return (subHour * 60 + subMinute) / UNITTIME * PRICE ;
	}
	

}
