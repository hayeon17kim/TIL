package day05;
// 컨트롤러 클래스는
// 웹 MVC 패턴에서는
// 웹에서 들어온 요청에 따라 데이터를 데이터베이스에 넣거나 빼거나 하고
// 그리고 그 결과값을 다시 웹으로 보내주는 역할을 한다.
// 그러나 우리는 웹이 없으니까
// 여기서 ArrayList를 하나의 필드로 가지고
// 일종의 데이터베이스 역할을 맡긴다.

import java.util.ArrayList;

// 그리고 뷰에서 요청할 때마다 데이터를 보내주거나
// 데이터를 받아서 해당 값이 존재하는 지를 체크한다든지의 기능을
// 여기에 넣어주게 된다. 
public class ParkController {
	private ArrayList<ParkVO> list;
	
	// 생성자를 호출하면 이 list도 초기화되게 만들어주자
	public ParkController() {
		list = new ArrayList<ParkVO>();
		
	}
	
	// 먼저 현재 list에 저장되어 있는 객체의 크기를 반환하는
	// size() 메소드를 만들자.
	public int size() {
		return list.size();
	}
	
	// 파라미터로 넘어온 ParkVO 객체가 존재하는지 체크하는
	// contains 메소드
	public boolean contains(ParkVO p) {
		return list.contains(p);
	}
	
	// 파라미터로 넘어온 ParkVO 객체를 list에 입력하는
	// add 메소드
	public void add(ParkVO p) {
		list.add(p);
	}
	
	public void remove(ParkVO p) {
		list.remove(p);
	}
	
	
	// 입력된 시간이 올바르면 true, 올바르지 않으면 false를
	// 리턴하는 validateTime 메소드
	// 단 시간은 int로 입력된다.
	public boolean validateTime(int time) {
		// 입력된 시간을
		// 어떻게 해야 
		// 앞에 2자리 뒤의 2자리 나눌 수 있을까?
		// 앞에 2자리는 / 100 하면 된다
		// 뒤에 2자리는 % 100 하면 된다.
		int hour = time / 100;
		int minute = time % 100;
		
		if(hour >= 0 && hour <= 23 && minute >= 0 && minute <= 59) {
			return true;
		}
		
		return false;
	}
	
	public void take(ParkVO p) {
		int index = list.indexOf(p);
		p.setInTime(list.get(index).getInTime());
	}



	
	
}
