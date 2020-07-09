package day04;

import java.util.Scanner;
public class Ex01Array {
	public static void main(String[] args) {	// 메소드명 + ctrl + space + enter
		// 배열이란 똑같은 데이터타입을
		// 한개의 이름으로 여러개 관리하는 데이터타입이다.
		// 데이터타입[] 배열이름 = new 데이터타입[크기]
		int[] arr = new int[5];
		System.out.println(arr.length);
		// 위의 코드는 
		// int 변수 5개를 가질 수 있는 배열 arr 을 만드는 코드이다. 
		
		// 배열의 각 위치는 index 라는 위치번호를 통해서 접근 가능하다.
		// index는 0부터 (배열의 크기 - 1)까지이다.
		
		// 배열의 각 위치는 우리가 번호를 적어주면 하나의 변수처럼 사용이 가능하다.
		arr[0] = 30;
		System.out.println("0번째 값: " + arr[0]);
		
		// 하지만 배열은 단점이 2가지 있다.
		// 1. 크기가 고정되어 있음
		for(int i = 0; i < arr.length; i++) {
			System.out.println(arr[i]);
		}
		
		// 만약 우리가 배열에 존재하지 않는 번호를 접근 하려고 한다면?
		//System.out.println(arr[30]);
		// 위의 코드는 에러가 난다. 왜냐하면 배열의 크기가 5인데
		// index 30이라는 범위를 벗어난 위치에 접근하려고 했기 때문이다.
		// 즉 우리가 숫자를 입력받아서 저장하는 프로그램을 작성하려고 한다면
		// 배열로 만들면 횟수가 제한된다.
		
		// [과제] 동적할당과 정적할당의 차이 조사
		
		Scanner scanner = new Scanner(System.in);
		// 2. 클래스보다 앞서 나왔기 때문에 클래스에 비친화적임
		Car[] carArray = new Car[300];
		for(int i = 0; i < carArray.length; i++) {
			System.out.print("첫번째 차의 차량번호를 입력하세요: ");
			carArray[i].setPlateNumber(scanner.nextLine());
		}
		// 위의 코드에서 null pointer exception이 발생한다.
		// 객체의 배열인 경우 해당 위치를 다시 생성자 호출을 해서
		// 사용할 준비를 해야 한다.
		
		scanner.close();
	}
}
