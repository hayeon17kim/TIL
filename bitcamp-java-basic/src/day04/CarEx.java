package day04;

public class CarEx {
	public static void main(String[] args) {
		Car c = new Car();
		// 먼저 Car 객체 c에 정보를 넣어보자.
		// 객체 안의 메소드 혹은 필드를 접근할 때에는
		// 접근 연산자 . 을 사용한다.
		
		// null의 경우 아무것도 할 수 없다.
		// null인 필드나 객체에 메소드나 필드를 호출하는 것 자체가
		// 불가능하다.
		// System.out.println(c.plateNumber.equals("abc"));
		
		// 따라서 파라미터가 없는 생성자를 만들어 줄 경우
		// 참조형 데이터값을 가진 필드는
		// 내부에서 해당 데이터타입의 생성자를 호출해주는 방법이 좋은 방법이다. 
		c.setColor("파란색");
		c.setPlateNumber("00가 0000");
		c.setPrice(20000000);
		c.setType("소나타");
		c.setYear(2020);
		
		//System.out.println("차량번호는 "+ c.plateNumber);
		System.out.println("차량 번호는 " + c.getPlateNumber());
		
		// 메소드를 호출해보자
		// 1. 파라미터가 없는 경우 리턴 타입을 어딘가에 꼭 담아줄 필요까진 없다.
		c.getPlateNumber();
		String string1 = c.getPlateNumber();
		System.out.println("차량 번호: " + string1);
		
		// 2. 파라미터가 있는 경우 그 때는 반드시 파라미터를 약속된대로 넘겨주어야 한다.
		// 단 파라미터를 넘겨줄 때에는 이름과 달라도 된다.
		String a = "00가 0000";
		String b = "11하 1111";
		
		System.out.println(c.checkPlateNumber(a));
		System.out.println(c.checkPlateNumber(b));
		
		Car c2 = new Car();
		c2.setPlateNumber("99가 9999");
		c2.setColor("빨간색");
		c2.setPrice(10000000);
		c2.setType("모닝");
		c2.setYear(2020);
		
		System.out.println(c.equals(c2));		//false
		
		Car c3 = new Car();
		c2.setPlateNumber("99가 9999");
		c2.setColor("빨간색");
		c2.setPrice(10000000);
		c2.setType("모닝");
		c2.setYear(2020);
		
		System.out.println(c2.equals(c3));		//false

		// System.out.println()에 파라미터로
		// 객체를 넘겨주면 System.out.println 이
		// 해당 객체의 toString() 메소드의 결과값을
		// 화면에 출력하게 된다.
		
		System.out.println(c2);					//day04.Car@15db9742
		// day04 클래스 안에 Car라는 객체가 @15db9742 (at)메모리위치
		// toString()을 재정의해보자.

	}
}
