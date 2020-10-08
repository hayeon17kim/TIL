## 배열 

### 자료구조

**데이터 단위와 데이터 자체 사이의 물리적 또는 논리적 관계**이다. 자료구조는 **자료를 효율적으로 이용할 수 있도록 컴퓨터에 저장하는 방법**을 말한다.

> 데이터 단위: 데이터를 구성하는 한 덩어리



### 배열

각 변수 이름을 '몇 번째'라고 지정할 때 사용하는 가장 기본적이고 간단한 자료구조이다. 배열은 같은 자료형의 변수로 이루어진 구성 요소(component)가 모인 것이다. 

```java
int[] a; // 자료형이 int형인 배열
a = new int[5]; // a는 길이가 5인 배열을 참조한다.
```



### 구성 요소

배열 안의 **모든 구성 요소의 형은 같고** **직선 모양으로 줄지어** 있다. **이 배열의 개별 요소에 접근하기 위해 사용하는 것이 연산자 `[]` 안에 넣는 정수형 인덱스**이다.



### 배열의 자료형

```java
int[] a = new int[5];
```

배열 a의 각 요소의 자료형은 int형이고, **a의 자료형은 int[5]형**이다. 다시 말해 a[0]은 int형, a는 int[5]형이다. 배열 a는 a[0], a[1], a[2], a[3], a[4]로 총 5개의 int형 저장 공간을 차지한다.

구성요소의 자료형이 Type인 배열을 `Type형 배열`이라고 부른다.



### 배열 초기화(array initializer)

```java
int[] a = {1, 2, 3, 4};
int[] b = int[]{1, 2, 3, 4};
```



### 배열의 복제(클론)

```java
배열이름.clone();
```

이 수식은 배열을 복제하고 복제한 배열에 대한 참조를 생성한다.



### 배열 최댓값을 구하기

```java
static int maxOf(int[] a) {
  int max = int[0];
  for (int i = 1; i < a.length; i++) {
    if (max < a[i])
      max = a[i];
    return max;
  }
}
```

> 주사(traverse): 배열의 요소를 하나씩 차례로 살펴보는 과정. 데이터를 하나씩 지나면서 살피고, 조사하는 일을 말한다.



**연습문제Q1.**

```java
public class Exam0201 {
  
  static int maxOf(int[] a) {
    int max = a[0];
    for (int i = 1; i < a.length; i++) {
      if (a[i] > max)
        max = a[i];
    }
    return max;
   
  }
  
  public static void main(String[] args) {
    Random rand = new Random();
    Scanner sc = new Scanner(System.in);
    
    System.out.println("키의 최댓값을 구합니다.");
    int num = rand.nextInt(500);
    System.out.println("사람수: " + num);
    int[] height = new int[num];
    
    for (int i = 0; i < num; i++)
      height[i] = 100 + rand.nextInt(90);
    
    System.out.println("최댓값은 " + maxOf(height) + "입니다.");
    
  }
}
```

### 난수 생성

Random 클래스의 인스턴스는 일련의 의사 난수를 생성한다. 난수는 무에서 생성되는 것이 아니라 seed라는 수의 값을 바탕으로 여러 연산ㅇ르 수행하며 얻는다. Random 클래스에서는 48비트의 seed를 사용하고, 이 seed는 선형 합동법이라는 계산법에 의해 특정 수(난수)로 바뀐다.

```java
Random rand = new Random(); // 자동으로 시드 부여
Random rand = new Random(n); // 시드 설정
```

Random 클래스는 `nextBoolean()`, `nextInt()`, `nextInt(n)`, `nextLong()`, `nextDouble()`, `nextFloat()` 메서드를 가지고 있다.

> 의사난수: 실제와 비슷한 난수. 시드를 가지고 생성하는 난수는 엄밀한 의미에서 실제 난수가 아니다.

컴퓨터 과학에서 난수는 보통 특정 입력값이나 특정 환경에 따라  무작위로 선택한 것처럼 보이는 난수를 생성한다. 그런데 srand 메서드에  전달한 시드값과 컴퓨터 환경이 같다면 그 결과값은 항상 같다. 결국 컴퓨터에 의해 생성되는 난수는 미리 컴퓨터가 계산해둔 의사난수이다. 컴퓨터는 계산된 결과만 가지고 난수를 생성하는데, 이 계산된 결과는 입력값에 의해 결정되므로 이 값으로 임의의 난수를 생성할 수 없다. (컴퓨터를 처음 키면 난수표를 생성해서 보관한다고 생각하면 된다)

따라서 프로그램에서 매번 같은 방법(같은 시드를 사용)으로 난수를 가져오면 처음 실행할 때 이외에는 난수라고 할 수 없다. 그래서 보통 seed이라 불리는 수를 srand 메서드에 매개변수로 매번 다르게 전달해 항상 다른 의사 난수를 생성해야 한다. 이때 seed값을 항상 다르게 주기 위해 현재 시간을 사용하는 것이 일반적이다. 



### 배열 요소를 역순으로 정렬

```java
for(int i = 0; i < n / 2; i++)
  //a[i]와 a[n - i - 1] 의 값을 교환
```

`n/2`에서 나머지는 버린다. 요소 개수가 홀수인 경우 가운데 요소는 교환할 필요가 없기 때문이다.



### 두 값의 교환

```java
static void swap(int a, int b) {
  int temp = a;
  a = b;
  b = temp;
}
```



**연습문제 Q2.**

```java
public class Exam0202 {

  static void swap(int[] a, int i1, int i2) {    
    System.out.printf("a[%d]과(와) a[%d]를 교환합니다.\n", i1, i2);
    int t = a[i1];
    a[i1] = a[i2];
    a[i2] = t;
  }

  static void reverse(int[] a) {
    printArr(a);
    for (int i = 0; i < a.length / 2; i++) {
      swap(a, i, a.length - i - 1);
      printArr(a);
    }
    System.out.println("역순 정렬을 마쳤습니다.");
  }
  
  static void printArr(int[] a) {
    for (int value : a) {
      System.out.printf("%d ", value);
    }
    System.out.println();
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    
    System.out.print("요솟수 : ");
    int num = sc.nextInt();
    
    int[] x = new int[num];
    
    for (int i = 0; i < num; i++) {
      System.out.print("x[" + i + "] : ");
      x[i] = sc.nextInt();
    }
    
    reverse(x);
  }
}
```



**연습문제 Q3.**

```java
static int sumOf(int[] a) {
  int result = 0; 
  for (int value : a)
    result += value;
  return result;
}
```



### 두 배열의 비교

하나의 배열이 아니라 여러 배열을 처리하는 알고리즘



**연습문제 Q4.**

```java
static void copy(int[] a, int[] b) {
  int len = a.length <= b.length ? a.length : b.length
  for (int i = 0; i < len; i++)
    a[i] = b[i];
}
```



**연습문제 Q5.**

```java
static void rcopy(int[] a, int[] b) {
  int len = a.length <= b.length ? a.length : b.length
  for (int i = 0; i < len; i++) {
    a[i] = b[b.length - i - 1];
  }
}
```



### 기수 변환 

10진수 정수를 n진수 정수로 변환하려면 정수를 n으로 나눈 나머지를 구하는 동시에 그 몫에 대해서 나눗셈을 반복해야 한다. 이 과정을 0이 될 때까지 반복하고, 이런 과정으로 구한 나머지를 거꾸로 늘어 놓은 숫자가 기수로 변환한 숫자이다. 

**기수 변환을 수행하는 프로그램**

```java
class CardConvRev {
  // 정숫값 x를 y진수로 변환하여 배열 d에 아랫자리부터 넣어두고 자릿수를 반환한다.
  static int cardConvR(int x, int y, char[] d) {
    int digits = 0; // 변환 후의 자릿수
    String dchar = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    do {
      d[digits] = dchar.charAt(x%y); //y로 나눈 나머지를 저장
      x /= y;
    } while (x == 0);
    return digits;
  }
  
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int no;
    int cd;
    int dno;
    int retry;
    char[] cno = new char[32];
    
    System.out.println("10진수를 기수 변환합니다.");
    do {
      do {
        System.out.print("변환하는 음이 아닌 정수: ");
        no = sc.nextInt();
      } while (no < 0);
      do {
        System.out.print("어떤 진수로 변환할까요? (2~36): ");
        cd = sc.nextInt();
      } while(cd < 2 || cd > 36);
      dno = cardConvR(no, cd, cno);
      
      System.out.print(cd + "진수로는 ");
      for (int i = dno - 1; i > 0; i--)
        System.out.print(cno[i]);
      System.out.println("입니다.");
      
      System.out.print("한 번 더 할까요? (1: 예 | 0: 아니오)");
      retry = sc.nextInt();
    } while (retry == 1);
  }
}
```

그러나 위 프로그램에서 cardConvR은 배열에 넣는 것을 역순으로 수행한다(배열의 앞쪽에 아랫자리게 들어가게 된다.) 따라서 변환 결과를 나타내는 갈색 박스 부분에서는 배열 cno를 뒤쪽에서 앞쪽으로 역순으로 스캔한다.



**연습문제 Q6.** 배열의 앞쪽에 아랫자리가 아니라 윗자리를 넣더두는 메서드를 작성하라.

**내가 푼 코드**

나는 임시 배열 temp를 생성하여 값을 저장하고, 그 값을 스캔하면서 역순으로 d 배열에 저장하였다.

```java
static int cardConv(int x, int y, char[] d) {
  int digits = 0;
  char[] temp = new char[32];

  String dchar = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
  do {
    temp[digits++] = dchar.charAt(x % y);
    x /= y;
  } while (x != 0);
  
  for (int i = 0; i < digits; i++)
    d[i] = temp[digits - i - 1];

  return digits;
}
```

**정답**

임시 배열을 따로 생성하지 않고 d배열에 넣은 후, d배열 안에서 역순으로 바꿔주었다. 내가 푼 코드는 반복문을 digits 만큼 도는 반면 정답에서는 digits/2만큼 돌기 때문에 더 효율적인 것 같다.

```java
static int cardConv(int x, int y, char[] d) {
  int digits = 0;

  String dchar = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
  do {
    d[digits++] = dchar.charAt(x % y);
    x /= y;
  } while (x != 0);

  for (int i = 0; i < digits / 2; i++) {
    char temp = d[i];
    d[i] = d[digits - i - 1];
    d[digits - i - 1] = temp;
  }

  return digits;
}
```



**연습문제 Q7.** 기수 변환 과정을 자세히 나타내는 프로그램을 작성하라.

```java
package algorithm.doit.problem;

import java.util.Scanner;

public class Exam0207 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int no;
    int cd;
    int dno;
    char[] cno = new char[32];
    
    System.out.println("10진수를 기수변환합니다.");
    System.out.print("변환하는 음이 아닌 정수: ");
    no = sc.nextInt();
    System.out.println("어떤 진수로 변환할까요?(2-36): ");
    cd = sc.nextInt();
    
    dno = CardConv(no, cd, cno);
    
    for (int i = 0; i < dno; i++) {
      System.out.print(cno[i]);
    }
  }
  
  static int CardConv (int x, int y, char[] d) {
    int digits = 0;
    String dchar = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    do {
      if (digits == 0) {
        System.out.printf("%d|%6d\n", y, x);
        System.out.println(" +------");        
      }
      else if (x / y != 0) {
        System.out.printf("%d|%6d  ...%d\n", y, x ,x % y);
        System.out.println(" +------");               
      }
      else
        System.out.printf("%8d  ...%d\n", y, x ,x % y);
      d[digits++] = dchar.charAt(x % y);
      
      x /= y;
      
    } while (x != 0);
    
    for (int i = 0; i < digits /2; i++) {
      char temp = d[i];
      d[i] = d[digits - i - 1];
      d[digits - i - 1] = temp;
    }
    return digits;
  }
}
```

실행결과

```console
10진수를 기수변환합니다.
변환하는 음이 아닌 정수: 10
어떤 진수로 변환할까요?(2-36): 
2
2|    10
 +------
2|     5  ...1
 +------
2|     2  ...0
 +------
       2  ...1
1010
```



### String 클래스

- 문자열 리터럴은 단순히 문자가 늘어서 있는 것이 아니라 String형 인스턴스에 대한 참조이다. 
- String 클래스는 문자열을 넣어두기 위한 문자 배열, 문자 수를 나타내는 필드를 갖고 있는 클래스이다.



### 소수의 나열

- 소수: 2부터 n-1까지의 어떤 정수로도 나누어떨어지지 않는 정수
- 합성수: 나누어떨어지는 정수가 하나 이상 존재



#### 1단계: 2부터 n-1까지의 정수로 나누기

**실습2-9.**

```java
class PrimeNumber1 {
  public static void main(String[] args) {
    int counter = 0;
    
    for (int n = 2; n<= 1000; n++) {
      int i;
      for (i = 2; i < n; i++) {
        counter++;
        if (n % i == 0)
          break;
      }
      if (n == i)
        System.out.println(n);
    }
  }
}
```

- n이 소수인 경우: n과 같은 값 (for문이 끝까지 실행된다.)

- n이 합성수인 경우: n보다 작은 값 (for문이 중단된다.)



#### 2단계: 3부터 n까지의 소수로 나누기 + 짝수 제외

```javascript
class PrimeNumber2 {
  public static void main(String[] args) {
    int counter = 0; // 나눗셈의 횟수
    int ptr = 0; // 찾은 소수의 개수
    int[] prime = new int[500]; // 소수를 저장하는 배열
    
    prime[ptr++] = 2;
    
    for (int n = 3; n < 1000; n +=2) {
      int i;
      for (i = 1; i < ptr; i++) {
        counter++;
        if (n % prime[i] == 0)
          break;
      }
      if (ptr == i) // 마지막까지 나누어떨어지지 않음
        prime[ptr++] = n; // 소수라고 배열에 저장
    }
    
    for (int i = 0; i < ptr; i++) 
      System.out.println(prime[i]);
    
    System.out.println("나눗셈을 수행한 횟수: " + counter);
  }
}
```



- 같은 답을 얻는 알고리즘은 하나가 아니다.
- **빠른 알고리즘은 메모리를 많이 요구한다.**



#### 3단계: n의 제곱근 이하의 소수로 나누기

어떤 정수 n은 n 제곱근 이하의 어떤 소수로도 나누어떨어지지 않을 때 소수라고 판단할 수 있다.

> 넓이가 100이라는 것은 직사각형의 어느 한 변으로 나눌 수 있다는 의미이다. 이러한 성질을 이용하여 **제곱근을 한 변으로 하는 이후의 직사각형에 대한 계산량을 줄이는 것**이 이 단계의 핵심이다.

이때 n의 제곱근을 구하는 것보다 제곱을 구하는 것이 훨씬 간단하고 빠르다. 

```java
public class Exam0210 {
  public static void main(String[] args) {
    int counter = 0; // 곱셈과 나눗셈의 횟수
    int ptr = 0; // 찾은 소수의 개수
    int[] prime = new int[500]; // 소수를 저장하는 배열
    
    prime[ptr++] = 2; // 2는 소수
    prime[ptr++] = 3; // 3은 소수
    
    for (int n = 5; n < 1000; n+= 2) { // 대상은 홀수만
      boolean flag = false;
      for (int i = 1; prime[i] * prime[i] <= n; i++) {
        System.out.println(n);
        counter+=2; //곱셈 + 나눗셈 연산 수행 
        if (n % prime[i] == 0) { // 나누어 떨어지면 소수가 아님
          flag = true;
          break; // 더이상의 반복은 불필요
        }
      }
      if (!flag) { // 마지막까지 나누어 떨어지지 않음
        prime[ptr++] = n; // 소수라고 배열에 저장
        counter++; // 안쪽 for문 본문으로 들어가지 않으므로 곱셈이 수행횟수에 포함X
      }
    }
    
    for (int i = 0; i < ptr; i++) // 찾은 ptr개의 소수를 출력
      System.out.println(prime[i]);
    System.out.println("나눗셈을 수행한 횟수: " + counter);
  }
}

```



### 다차원배열

Java는 엄밀한 의미에서 다차원 배열이 없다. 2차원 배열을 '배열의 배열'로 생각하고 3차원 배열을 '배열의 배열의 배열'로 생각하기 때문이다.

`int[][]x = new int[2][4]`에서 배열 x는 "int형을 구성 자료형으로 하는 배열"을 구성 자료형으로 하는 배열이다. 이걸 더 이상 나눌 수 없는 요소(배열이 아닌)까지 분해하면 배열 x는 int형이다. 이런 형을 자료형(element type)이라고 부르고 자료형 레벨의 구성요소를 요소(element)라고 한다.



### 한 해의 경과 일 수를 계산하는 프로그램

**실습 2-13.**

```java
public class DayOfYear {
  static int[][] mdays = {
      {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31},
      {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31}
  };
  
  // 서기 year년은 윤년인가?
  static int isLeap(int year) {
    return (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) ? 1 : 0;
  }
  
  static int dayOfYear(int y, int m, int d) {
    int days = d;
    for (int i = 0; i < m; i++) 
      days += mdays[isLeap()][i - 1];
    return days;
  }
  
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int retry;
    
    System.out.println("그 해의 경과 일수를 구합니다.");
    
    do {
      System.out.print("년? ");
      int year = sc.nextInt();
      System.out.print("월? ");
      int month = sc.nextInt();
      System.out.print("일? ");
      int day = sc.nextInt();
      
      System.out.printf("%s일이 경과되었습니다.", dayOfYear(year, month, day));
      System.out.println("다시 하시겠습니까? (예: 1 | 아니오: 0");
      retry = sc.nextInt();
    } while (retry == 1);
  }

}
```



**연습문제 Q8.** 메서드 dayOfYear를 변수 i와 days없이 구현하세요. while문을 사용하세요.

```java
static int dayOfYear(int y, int m, int d) {
  while (--m != 0) 
    d += mdays[isLeap(y)][m - 1];
  return d;
}
```

처음에는 count 변수를 따로 선언했는데, 그럴 필요가 없었다. 0번 인덱스에 있는 값부터 더하는 것이 아니라 m-1번 인덱스에 들어있는 값부터 더하면 된다. 또한 기본 자료형이기 때문에 메서드 안에서 값을 마음대로 변형하여도 원본값에는 형향을 미치지 않는다. 따라서 굳이 days 변수에 다시 담을 필요가 없다.



**연습문제 Q9.** y년 m월 d일 의 그해 남은 일 수를 구하는 메서드를 작성하세요.

```java
static int leftDayOfYear(int y, int m, int d) {
  while (--m != 0)
    d += mdays[isLeap(y)][m - 1];
  return 365 + isLeap(y) - d;
  //return isLeap(y) == 0 ? 365 - d : 366 - d; 
  //=> 굳이 이럴 필요가 없다. 
}
```



### 배열에 관한 보충

- 배열의 접근은 모두 런타임(실행 시)에 검사된다. 
- 배열 요솟수는 0이어도 된다. 빈 배열이라고 부른다.
- 배열 초기화는 마지막 요소에 대한 초기화 뒤에 "추가로" 쉼표를 넣을 수 있따.
  - `int[] a = {1, 2, 3, 4,}`

- 확장된 for문은 배열의 스캔을 매우 간단하게 구현할 수 있다.
  - `for (double i : a) {}`
  - `콜론(:)`d은 "~의 안에 있는"이라는 뜻이다. 
  - 그래서 확장 for문을 for-in문/for-each문이라고도 부른다.
  - 여기서 변수 i는 인덱스를 나타내는 것이 아니라 double 형 실수의 값인 **스캔할 때 주목하고 있는 요소**를 나타낸다.
  - 배열의 길이를 조사하는 수고를 덜 수 있다.
  - iterator와 같은 방법으로 스캔할 수 있다.
  - 배열의 모든 요소를 스캔하는 과정에서 인덱스 자체의 값이 필요하지 않으면 그 스캔은 확장 for문에 의해 구현하는 것이 좋다.
- 다차원 배열의 복제는 최상위 1레벨만 수행한다. 



## 클래스

- **임의의 데이터형을 자유로이 조합하여 만들 수 있는 자료구조**이다.

- 신체검사 데이터를 처리할 때 클래스를 사용하지 않는다면 어떻게 될까? name, vision, height 배열을 만들어서 다룰 것이다. name[0]의 "김하연" 키는 height[0]에 저장되고 시력은 vision[0]에 저장될 것이다. 그러나 각 개인의 데이터가 같은 인덱스에 저장되는 **관계가 프로그램에 직접 나타나지는 않을 것**이다. 



### 클래스의 배열

신체검사 데이터를 따로따로인 배열의 모임이 아닌 클래스의 배열로 구현한 프로그램이다. 

```java
// 신체검사 데이터용 클래스 배열에서 평균 키와 시력의 분포를 구함 
public class PhysicalExamination {
  
  // 시력 분포(0.0에서 0.1단위로 21개)
  static final int VMAX = 21;
  
  static class PhyscData {
    String name;
    int height;
    double vision;
    
    //생성자
    PhyscData(String name, int height, double vision) {
      this.name = name;
      this.height = height;
      this.vision = vision;
    }
    
  }
  
  // 키의 평균값
  static double aveHeight(PhyscData[] dat) {
    double sum = 0;
    for (int i = 0; i < dat.length; i++) {
      sum += dat[i].height;
    }
    return sum/dat.length;
   }
  
  // *시력 분포 
  static void distVision(PhyscData[] dat, int[] dist) {
    int i = 0;
    dist[i] = 0;
    for (i = 0; i < dat.length; i++) {
      if (dat[i].vision >= 0.0 && dat[i].vision <= VMAX / 10.0)
        dist[(int)dat[i].vision*10]++;
    }
  }
  
  public static void main(String[] args)  {
    Scanner sc = new Scanner(System.in);
    PhyscData[] x = { 
        new PhyscData("박현규", 162, 0.3),
        new PhyscData("함진아", 173, 0.7),
        new PhyscData("최윤미", 175, 2.0),
        new PhyscData("홍연의", 171, 1.5),
        new PhyscData("이수진", 168, 0.4),
        new PhyscData("김용준", 174, 1.2),
        new PhyscData("박용규", 169, 0.8),
    };
    int[] vdist = new int[VMAX]; // 시력 분포
    
    System.out.println("신체검사 리스트");
    System.out.println("이름   키    시력");
    System.out.println("----------------");
    for (int i = 0; i < x.length; i++)
      System.out.printf("%-8s%3d%5.1f\n",
         x[i].name, x[i].height, x[i].vision);
    System.out.printf("\n평균키: %5.1fcm\n", aveHeight(x));
    
    distVision(x, vdist); // 시력 분포를 구함
    
    System.out.println("\n시력분포");
    for (int i = 0; i < VMAX; i++)
      System.out.printf("%3.1f ~ : %2d명\n", i/10.0, vdist[i]);
    
  }
  
}
```



**연습문제 Q10.** 시력분포를 그래프로 출력하도록 바꾸어 프로그램 작성 (기호문자 *를 사람 수만큼 반복)

```java
public class Exam0210 {
  static final int VMAX = 21;
  
  static class PhyscData {
    String name;
    int height;
    double vision;
    PhyscData(String name, int height, double vision) {
      this.name = name;
      this.height = height;
      this.vision = vision;
    }
    
  }
  
  static double aveHeight(PhyscData[] dat) {
    double sum = 0;
    for (int i = 0; i < dat.length; i++) {
      sum += dat[i].height;
    }
    return sum/dat.length;
   }
  
  static void distVision(PhyscData[] dat, int[] dist) {
    int i = 0;
    dist[i] = 0;
    for (i = 0; i < dat.length; i++) {
      if (dat[i].vision >= 0.0 && dat[i].vision <= VMAX / 10.0)
        dist[(int) (dat[i].vision * 10)]++;
    }
  }
  
  public static void main(String[] args)  {
    Scanner sc = new Scanner(System.in);
    PhyscData[] x = { 
        new PhyscData("박현규", 162, 0.3),
        new PhyscData("함진아", 173, 0.7),
        new PhyscData("최윤미", 175, 2.0),
        new PhyscData("홍연의", 171, 1.5),
        new PhyscData("이수진", 168, 0.4),
        new PhyscData("김용준", 174, 1.2),
        new PhyscData("박용규", 169, 0.8),
    };
    int[] vdist = new int[VMAX];
    
    System.out.println("신체검사 리스트");
    System.out.println("이름   키    시력");
    System.out.println("----------------");
    for (int i = 0; i < x.length; i++)
      System.out.printf("%-8s%3d%5.1f\n",
         x[i].name, x[i].height, x[i].vision);
    System.out.printf("\n평균키: %5.1fcm\n", aveHeight(x));
    distVision(x, vdist);
    
    System.out.println("\n시력분포");
    for (int i = 0; i < VMAX; i++) {
      System.out.printf("%3.1f ~ :", i/10.0);
      // vdist[i]개의 별을 출력하도록 메서드 호출
      printStars(vdist[i]);
      System.out.println();
    }
    
  }
  
  // 파라미터로 받은 수만큼 별을 출력하는 메서드
  public static void printStars(int n) {
    for (int i = 0; i < n; i++) 
      System.out.print('*');
  }
  
}
```



**연습문제 Q11.** 서기 년월일을 필드로 갖는 클래스 만들기

```java
public class Exam0211 {
  public static void main(String[] args) {
    YMD obj = new YMD(2020, 1, 1);
    System.out.println(obj.after(365));
    System.out.println(obj.before(365));
  }
}

class YMD {
  @Override
  public String toString() {
    return String.format("%d-%d-%d", y, m, d);
  }
  
  static int[][] mdays= {
      {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31},
      {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31}
  };
  
  int y;
  int m;
  int d;
  
  // 생성자 
  public YMD(int y, int m, int d) {
    this.y = y;
    this.m = m;
    this.d = d;
  }
  
  // n일 앞의 날짜를 반환
  public YMD before(int n) {
    if (n < 0)
      return after(-n);
    
    YMD temp = new YMD(y, m, d);
    temp.d -= n;
    while (temp.d < 1) {
      temp.d += mdays[isLeap(temp.y)][temp.m -1];
      if (--temp.m == 0) {
        temp.y--;
        temp.m = 12;
      }
    }
    return temp;
  }
  
  // n일 뒤의 날짜를 반환
  public YMD after(int n) {
    
    if (n < 0)
      return before(-n);
    
    YMD temp = new YMD(y, m, d);
    temp.d += n;
    while (temp.d > mdays[isLeap(y)][temp.m - 1]) {
      temp.d -= mdays[isLeap(y)][temp.m-1];
      if (++temp.m == 13) {
        temp.y++;
        temp.m = 1;
      }
    }
    return temp;
  }
  public int isLeap(int y) {   
    return (y % 4 == 0 && y % 100 != 0) || y % 400 == 0 ? 1 : 0; 
  }
}
```