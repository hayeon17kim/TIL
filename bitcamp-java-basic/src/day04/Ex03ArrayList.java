package day04;

import java.util.ArrayList;

// 자바에서는 배열을 클래스화 시킨
// ArrayList라는 클래스가 있다.
// 우리는 요거를 쓰면 편하게 할 수 있다.
public class Ex03ArrayList {
	public static void main(String[] args) {
		ArrayList<Integer> list = new ArrayList<>();
		//내부적으로 어레이리스트는 클래스 객체를 다루기 때문에
		//해당 어레이리스트가 다룰 데이터타입을
		//< >에 넣어야 한다.
		//그렇다면 우리는 기본형 데이터타입을 다룰 수 있을까? -> x
		//그래서 자바는 그러한 기본형 데이터타입을 클래스화 시킨
		//포장 클래스(wrapper class)를 준비해놨다!
		//int -> Integer, long -> Long, double -> Double 등등
		
		ArrayList<Car> carList = new ArrayList<>();
		
		//List에 객체를 추가할 때에는 add(객체) 메소드를 실행하면 된다.
		Car c1 = new Car("00가 0000", "빨강색", "소나타", 2000, 2020);
		carList.add(c1);
		//List에 현재 추가되어있는 객체의 갯수를 확인할때에는
		//size() 메소드를 실행하면 된다.
		System.out.println("carList의 현재 크기: "+carList.size());
		
		
		Car c2 = new Car("00가 0001", "빨강색", "소나타", 2000, 2020);
		carList.add(c2);
		System.out.println("carList의 현재 크기: "+carList.size());
		
		Car c3 = new Car("00가 0002", "빨강색", "소나타", 2000, 2020);
		carList.add(c3);
		System.out.println("carList의 현재 크기: "+carList.size());
		
		Car c4 = new Car("00가 0003", "빨강색", "소나타", 2000, 2020);
		carList.add(c4);
		System.out.println("carList의 현재 크기: "+carList.size());
		
		Car c5 = new Car("00가 0004", "빨강색", "소나타", 2000, 2020);
		carList.add(c5);
		System.out.println("carList의 현재 크기: "+carList.size());
		
		//만약에 특정 위치에 객체를 끼어넣고 싶으면
		//list.add(위치, 객체)를 실행하면 된다.
		Car c6 = new Car("00가 0005", "빨강색", "소나타", 2000, 2020);
		//추가전에 2번째에 있는 객체의 값:
		
		//특정 위치의 객체를 호출할 때에는
		//get(index)로 호출 가능하다.
		System.out.println(carList.get(2));
		
		//index 2 에 c6을 끼어넣기한 후
		carList.add(2, c6);
		System.out.println(carList.get(2));
		
		//ArrayList는 클래스의 equals()메소드를 적극적으로 내부에서 활용한다.
		//여러분들이 ArrayList의 모든 메소드들을 다 제대로 활용하고 싶으면
		//여러분들의 클래스에 equals()메소드를 만드는 것이 매우 중요하다!!!!
		
		//1. 리스트가 해당 객체를 가지고 있는지 확인할 때에는
		//contains()라는 메소드를 실행하면 된다.
		Car c7 = new Car("00가 0007", "빨강색", "소나타", 2000, 2020);
		Car c8 = new Car("00가 0001", "빨강색", "소나타", 2000, 2020);
		System.out.println("carList.contains(c8)"+carList.contains(c8)); 
		System.out.println("carList.contains(c7)"+carList.contains(c7)); 
		
		//2. 리스트에서 해당 객체의 index 번호를 찾을 때에는 
		//indexOf(객체)를 실행하면 된다.
		//만약 해당 객체가 리스트에 존재하지 않을 때에는
		//-1이 나오게 된다.
		System.out.println("carList.indexOf(c8): "+carList.indexOf(c8));
		System.out.println("carList.indexOf(c7): "+carList.indexOf(c7));
		
		//3. 만약에 가장 마지막에 있는 인덱스를 찾을 때에는
		//lastIndexOf(객체)를 실행하면 된다.
		//indexOf와 마찬가지로
		//존재하지 않을 시에는
		//-1이 나오게 된다.
		System.out.println("carList.lastIndexOf(c8): "+carList.lastIndexOf(c8));
		System.out.println("carList.lastIndexOf(c7): "+carList.lastIndexOf(c7));
		
		//4. 리스트에서 객체를 제거할 때에는
		//2가지 방법으로 제거가 가능하다.
		//A. 인덱스 번호로 삭제하기
		System.out.println("carList.get(0): "+carList.get(0));
		//인덱스로 제거할 때에는 remove(인덱스) 로 하면 된다.
		carList.remove(0);
		System.out.println("carList.get(0): "+carList.get(0));
		
		//B. 해당 객체와 equals가 true가 나오는 객체를 
		//remove의 파라미터로 넘겨주는 방법
		System.out.println("carList.contains(c8): "+carList.contains(c8));
		carList.remove(c8);
		System.out.println("carList.contains(c8): "+carList.contains(c8));
	}
}









