package day05a;

import java.util.Scanner;

//화면에 출력을 담당하는 뷰어 클래스
//만약 데이터를 필요할 때에는 컨트롤러한테 부탁한다.
//입력도 받아야 하니깐 스캐너도 있어야 한다.
//따라서 요 2개 클래스 객체를 필드로 갖고 있는다.
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
	
	//메뉴 출력을 담당하는
	//showMenu() 메소드
	public void showMenu() {
		while(true) {
			System.out.println("===============");
			System.out.println("비트 주차관리 프로그램");
			System.out.println("===============");
			System.out.println("1. 입차 2. 출차 3. 종료");
			System.out.print("> ");
			int choice = scanner.nextInt();
			
			if(choice == 1) {
				//입차 메소드 호출
				insertPark();
				
			}else if(choice == 2) {
				//출차 메소드 호출
				deletePark();
				
			}else if(choice == 3) {
				System.out.println("사용해주셔서 감사합니다.");
				break;
			}else {
				System.out.println("잘못 입력하셨습니다.");
				
			}
		}
	}
	
	private void deletePark() {
		//차량 출차시에는
		//1. 들어와있는 차량이 존재하는지 체크
		//2. 들어와있는 차량이 존재하면
		//   차량번호를 입력 받는데
		//   해당 차량번호가 존재 하지 않으면
		//   다시 입력 받는다.
		//3. 출차 시간을 입력을 받는다.
		//   이때는 시간이 올바른지 뿐만이 아니라
		//   출차 시간은 반드시 입차 시간보다 늦어야 한다!
		//4. 해당 차량의 요금을 계산해서 보여주고
		//   그리고 리스트에서 삭제한다.
		
		//먼저 차량이 입고된 적 있는지 체크한다.
		//size가 0인지 체크해도 되지만 
		//isEmpty()라는 메소드를 통해서 확인해 보자
		if(controller.isEmpty()) {
			//주차된 차량이 없으므로 경고 메시지만 출력해 준다!
			System.out.println("입차된 차량이 없습니다.");
		}else {
			//차량 출차를 시작한다.
			
			//차량번호를 저장할 ParkVO 객체 p를 만들자
			ParkVO p = new ParkVO();
			
			System.out.print("출차할 차량의 번호를 입력해주세요: ");
			scanner.nextLine();
			p.setPlateNumber(scanner.nextLine());
			
			while(!controller.contains(p)) {
				// 컨트롤러 안의 필드인 리스트에 
				// p가 존재하지 않으므로
				// 다시 입력받는다.
				System.out.println("입차된 기록이 없는 번호입니다.");
				System.out.println("출차할 차량의 번호를 입력해주세요: ");
				p.setPlateNumber(scanner.nextLine());
			}
			
			// 입력된 plateNumber를 통해서
			// controller 안의 리스트에서
			// 원본을 찾아온다.
			p = controller.get(p);
			
			System.out.print("출차 시간을 입력해주세요: ");
			int outTime = scanner.nextInt();
			while(!controller.validateTime(outTime) 
					|| p.getInTime() > outTime) {
				System.out.println("잘못된 형식입니다.");
				System.out.println("출차 시간을 입력해주세요: ");
				outTime = scanner.nextInt();
			}
			// 이제 나갈 차량과 나가는 시간이 있으므로
			// 요금 계산해주고
			// 해당 차량을 컨트롤러의리스트에서 없애준다.
			calculateRate(p, outTime);
			controller.remove(p);
			
		}
	}
	
	private void calculateRate(ParkVO p, int outTime) {
		// 시간 차이를 계산한다.
		int hourDifference = outTime / 100 - p.getInTime() / 100;
		// 분 차이를 계산한다.
		int minuteDifference = outTime % 100 - p.getInTime() % 100;
		
		// 두 시간의 차이를총 분의 차이로 바꾼다.
		int totalDifference = hourDifference * 60 + minuteDifference;
		
		// 10분 당 1000원의 요금으로 계산해서 출력한다.
		int rate = totalDifference / UNIT_MINUTE * UNIT_PRICE;
		
		System.out.printf("총 요금은 %d원 입니다. \n", rate);
		
	}
	
	private void insertPark() {
		//차량 입고시에는
		//1. 빈칸이 있는지 체크
		// -> 컨트롤러 size 메소드를 실행해서
		// SIZE 상수보다 작은지 체크해서
		// 작으면 진행
		// 아니면 경고메시지 출력
		//2. 입력받은 차량 번호가 존재하는 차량인지 체크
		//3. 시간을 입력받아 유효한 시간인지 체크
		//4. 모두 유효하면 컨트롤러로 보내서 리스트에 입력
		
		//사이즈 체크 진행
		if(controller.size() < SIZE) {
			//주차장에 여유 공간 존재
			//차량 번호를 입력받는다.
			System.out.print("차량 번호를 입력해주세요: ");
			scanner.nextLine();
			ParkVO p = new ParkVO();
			p.setPlateNumber(scanner.nextLine());
			
			while(controller.contains(p)) {
				//이미 존재하는 차량 번호 이므로 다시 입력 받는다.
				System.out.println("이미 입차된 번호입니다.");
				System.out.print("차량 번호를 입력해주세요: ");
				p.setPlateNumber(scanner.nextLine());
			}
			
			//시간을 입력 받아서 올바른 시간인지 체크한다.
			System.out.print("입차 시간을 입력해주세요: ");
			p.setInTime(scanner.nextInt());
			
			while(!controller.validateTime(p.getInTime())) {
				System.out.println("잘못된 시간 형식입니다.");
				System.out.print("입차 시간을 입력해주세요: ");
				p.setInTime(scanner.nextInt());
			}
			
			//올바른 번호 + 시간이 p에 들어있으므로
			//controller를 통해서 list에 추가해준다.
			controller.add(p);
			
			
			
		}else {
			//주차장이 꽉 찼으므로 경고 메시지만 출력
			System.out.println("빈 칸이 없습니다.");
		}
	}
}
















