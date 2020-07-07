package day01;

// 논리연산자
// 논리연산자는 2가지의 boolean 값에 대한 연산을 한다.
// AND OR 부정 3가지가 있다

// AND: shift+7 을 눌러서 && 을 적어준다.
// 		AND 연산은 2개의 boolean의 값이 모두 true일 때만
//		true가 나온다.

// OR:	shift+\\ 을 눌러서 || 을 적어준다.
//		\는 한글 키보드에서 원화표시이다.
//		OR 연산은 2개의 boolean 중 하나라도 true면 true가 나온다.

// 부정:	! 를 적어준다.
//		부정연산은 true는 false로, false는 true로 바꿔준다.

public class Ex07Operator04 {
	public static void main(String[] args) {
		
		boolean boolean1 = true;
		boolean boolean2 = false;
		
		System.out.print("boolean1 && boolean2: ");
		System.out.println(boolean1 && boolean2);
		
		System.out.print("boolean1 || boolean2: ");
		System.out.println(boolean1 || boolean2);
		
		System.out.print("!boolean1: ");
		System.out.println(!boolean1);
		
	}
}

// 