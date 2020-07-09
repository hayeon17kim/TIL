package day04;

public class Car {
	// 필드를 만들어보자
	// 필드는 변수를 선언하듯 만들어 준다.
	private String plateNumber;
	private String color;
	private String type;
	private int price;
	private int year;
	
	// 메소드를 만들어보자
	// 1. 리턴 타입, 파라미터가 없는 메소드
	void startEngine() {
		System.out.println("시동을 겁니다.");
	}
	// 2. 리턴 타입은 있지만 파라미터가 없는 메소드
	public String getPlateNumber() {
		return plateNumber;
	}
	
	// 3. 리턴 타입은 없지만 파라미터가 있는 메소드
	public void setPlateNumber(String plateNumber) {
		// 파라미터는 외부에서 보내주는 값을
		// 메소드 내부에서 일종의 변수처럼 다룰 수 있게 된다.
		// 즉 위의 userNumber는 외부에서 값을 넘겨줄 때에는
		// 다른 이름의 String 변수이거나 ""로 만들어진 임시 String 변수일 수 있지만
		// setPlateNumber라는 메소드 내부에서는 무조건
		// userNumber로 불리게 된다!
		
		// 그래서 파라미터를 예전에는 "매개 변수"라고 부르기도 했다.
		this.plateNumber = plateNumber;
	}
	
	// 4. 리턴 타입과 파라미터가 있는 메소드
	boolean checkPlateNumber(String userNumber) {
		if(plateNumber.equals(userNumber)) {
			return true;
		} else {
			return false;
		}
	}
	
	// 파라미터 있는 생성자를 만들어봅시다.
	public Car(String plateNumber, String color, String type, int price, int year){
		this.plateNumber = plateNumber;
		this.color = color;
		this.type = type;
		this.price = price;
		this.year = year;
	}
	
	// 파라미터가 있는 생성자를 만든다면 파라미터가 없는 생성자를
	// 호출할 수 없기 때문에
	// 파라미터가 없는 생성자도 만들어주어야 한다.
	Car(){
		plateNumber = new String();
		color = new String();
		type = new String();
		price = 0;
		year = 0;
	}
	
	//equals 메소드를 오버라이드 해보기
	public boolean equals(Object o) {
		// instanceof 라는 명령어를 통해서
		// 파라미터로 넘어온 o가 Car 클래스의 객체인지 확인한다.
		if(o instanceof Car) {
			// 위의 o instanceof Car의 의미는
			// o가 Car 클래스 객체이면 true, 아니면 false가 리턴되는 키워드를
			// 사용한 것이다.

			// 이 if에 들어왔던 것은 o가 Car 클래스 객체이다 라는 의미이므로
			// o를 명시적 형변환을 통해서 Car 클래스 객체에 덮어 씌어질 수 있다라는 의미가 된다.
			Car c = (Car)o;
			// 형변환된 c의 필드값들과 이 메소드를 호출하는 객체의 필드값들을
			// 비교해서 "주요 정보"가 같으면 return true 해준다.
			if(plateNumber.contentEquals(c.plateNumber)
					&&
					type.equals(c.type)) {
				return true;
			}
		}
		return false;
	}
	
	public String toString() {
		// return의 경우
		// 굳이 변수가 아니라
		// 값을 return해 줄 수도 있다.
		return "차량 번호: " + plateNumber
				+ ", 차량 종류: " + type
				+ ", 차량 색상: " + color
				+ ", 차량 연식: " + year
				+ ", 차량 가격: " + price;
	}
	
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	
	
}
