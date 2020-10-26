# 재귀 알고리즘

## 재귀의 기본

### 재귀란?

어떤 사건이 자기 자신을 포함하고 다시 자기 자신을 사용하여 정의될 때 **재귀적(recursive)**이라고 한다. 

예를 들어, 재귀적 정의(recursive definition)에 의해 무한으로 존재하는 자연수를 정의해보자.

- 1은 자연수이다.
- 자연수 n의 바로 다음 수도 자연수이다.

> 재귀는 병합 정렬과 퀵 정렬, 이진검색트리 등에 사용된다.

재귀 호출은 '메서드 자신'을 호출하는 것이라고 이해하기보다는 **자기 자신과 똑같은 메서드**를 호출한다고 이해하는 것이 자연스럽다. 만약 진짜 메서드 자신을 호출하면 끝없이 자기 자신을 호출하는 행위를 계속할테니 말이다. 

### 팩토리얼 구하기

음이 아닌 정수 n의 팩토리얼(n!)는 다음과 같이 재귀적으로 정의된다.

- 0! = 1
- n > 0 이면 `n! = n * (n - 1)`

**실습 5-1.**

```java
static int factorial(int n) {
  return (n > 0) ? n * factorial(n - 1) : 1;
}
```

매개변수 n에 전달받은 값이 0보다 크면 `n * factorial(n - 1)`을 반환하고, 그렇지 않으면 1을 반환한다.

**직접 재귀와 간접 재귀**

![image](https://user-images.githubusercontent.com/50407047/97102850-2abc2b80-16ec-11eb-96c8-bdabfde23ff8.png)

- 직접(direct) 재귀: 자신과 같은 메서드를 호출
- 간접(indirect) 재귀: 메서드a가 메서드 b를 호출하고, 다시 메서드 b가 메서드 a를 호출하는 구조

### 유클리드 호제법

- 두 정수의 최대공약수(greatest common divisior)를 재귀적으로 구하는 방법
- 두 정수를 직사각형의 두 변의 길이라고 생각해보자.
- 직사각형을 정사각형으로 완전히 채운다. 이렇게 만들 수 있는 정사각형의 가장 긴 변의 길이를 구하라.
- 두 정수가 주어질 때 **큰 값을 작은 값으로 나누었을 때 나누어떨어지는 가장 작은 값**이 최대공약수이다. **나누어지지 않으면 작은 값(얻은 나머지)에 대해 나누어 떨어질 때까지 같은 과정을 재귀적으로 반복**한다.
- x, y의 최대공약수를 `gcd(x,y)`로 표기하자. `x=az`와 `y=bz`를 만족하는 정수 a,b와 최대의 정수 z가 존재할 때 최대의 정수를 `gcd(x,y)`라고 할 수 있다. 최대공약수는 y가 0이면 x이고, y가 0이 아니면 `gcd(y, x%y)`로 구한다.

**[유클리드 호제법의 원리](https://justicehui.github.io/easy-algorithm/2018/08/11/GCD/)**

- A, B의 최대공약수를 G라고 하자. 그러면 A = Ga, B = Gb로 나타낼 수 있다.
- G는 최대공약수이기 때문에 a와 b의 최대 공약수는 1이고, 이 관계를 서로소라고 한다.
- A ± B = G(a ± b)이기 때문에 G는 A와 B를 더하거나 뺀 값의 약수이다.
- **A ≥ B라고 가정**하고 A를 B로 나누어보자. 이때 몫을 Q, 나머지를 R이라고 치자.
- A = BQ + R, Ga = GbQ + R, Ga - GbQ = R, R = G(a - bQ)
- 즉, **G(최대공약수)는 A를 B로 나눈 나머지의 약수이다.** 

```java
public static int gcd(int x, int y) {
  if (y == 0)
    return x;
  else
    return gcd(y, x % y);
}

// gcd(22, 8)
// gcd(8, 6)
// gcd(6, 2)
// gcd(2, 0)
// 2

// gcd(8, 22)
// gcd(8, 6) => x의 크기가 y보다 작은 경우 첫 번째 호출 시 순서가 뒤바뀐다.
// gcd(6, 2)
// gcd(2, 0)
// 2
```

**연습문제 Q1.** 재귀 메서드 호출을 사용하지 않고 팩토리얼 메서드 작성

```java
public static int factorial(int n) {
  int result = 1;
  for (int i = n; i > 1; i--) {
    result *= i;
  }
  return result;
}
```

**연습문제 Q2.** 재귀 메서드 호출을 사용하지 않고 gcd 메서드 작성

```java
public static int gcd(int x, int y) {
  while (y != 0) {
    int temp = x;
    x = y;
    y = temp % y;
  }
  return x;
}
```

**연습문제 Q3**. 배열 a의 모든 요소의 최대공약수를 구하는 메서드를 작성하라.

```java
// 내가 쓴 코드
public static int gcd(int x, int y) {
  if (y == 0)
    return x;
  return gcd(y, x % y);
}

public static int gcdArr(int[] arr) {
  int result = arr[0];
  for (int i = 0; i < arr.length; i++)
    result = gcd(result, arr[i]);
  return result;
}
```

```java
// 답안지 제공 코드
static int gcd(int x, int y) {
  while (y != 0) {
    int temp = x;
    y = x % y;
    x = temp;
  }
  return x;
}
// 2 4 6 8 
// gcdArray(a[], 0, 4)
// => gcd(2, gcdArray(a[], 1, 3))
// => gcd(2, gcd(4, gcdArray(a[], 2, 2)))
// => gcd(2, gcd(4, gcd(6, gcdArray(a[], 3, 1))))
// => gcd(2, gcd(4, gcd(6, 8))
// => gcd(2, gcd(4, 2))
// => gcd(2, 2)
// => 2

static int gcdArray(int a[], int start, int no) {
  if (no == 1)
    return a[start];
  else if (no == 2)
    return gcd(a[start], a[start + 1]);
  else
    return gcd(a[start], gcdArray(a, start + 1, no -1));
}
```



## 재귀 알고리즘 분석

### 재귀 알고리즘의 분석

``` java
public class Recur {
	static void recur(int n) {
    if (n > 0) {
    	recur(n - 1);
    	System.out.println(n);
    	recur(n -2);
  	}
  }
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.print("정수를 입력하세요: ");
    int x = sc.nextInt();
    recur(x);
  }
}

```

**재귀호출을 여러 회 실행하는 메서드**를 **순수하게(genuinely) 재귀적**이라고 한다. 

#### 하향식 분석(top-down analysis)

- 가장 위쪽에 위치한 상자의 메서드 호출부터 시작해 계단식으로 자세히 조사하는 분석 기법을 말한다.

```java
// recur(4)
// => recur(3) sysout(4) recur(2)
// => recur(2) sysout(3) recur(1) sysout(4) recur(1) sysout(2)
// => recur(1) sysout(2) sysout(3) sysout(1) sysout(4) sysout(1) sysout(2)
// => sysout(1) sysout(2) sysout(3) sysout(1) sysout(4) sysout(1) sysout(2)
// 1 2 3 1 4 1 2 
```



#### 상향식 분석(bottom-up analysis)

- 아래쪽부터 쌓아 올리며 분석하는 방법

위의 재귀함수를 상향식으로 분석해보자.

- `recur` 메서드는 n이 양수일 때만 실행하므로 `recur(1)` 우선적으로 고려
- `recur(1)`
  - `recur(0)` => 출력 내용 X
  - `sysout(1)` => 1 출력
  -  `recur(-1)` => 출력내용 X
- `recur(2)`
  - `recur(1)` => 1 출력
  - `sysout(2)` => 2 출력
  - `recur(0)` => 출력 내용 X



**연습문제 Q4.** 아래 `recur2` 메서드를 보고 하향식 분석과 하향식 분석을 수행하라.

```java
static void recur2(int n) {
  if (n > 0) {
    recur2(n - 2);
    System.out.println(n);
    recur2(n - 1);
  }
}
```

**하향식 분석**

- recur2(4)
  - recur2(2)
    - recur2(0) => 아무것도 하지 않음!
    - **2 출력**
    - recur2(1)
      - recur2(-1) => 아무것도 하지 않음!
      - **1 출력**
      - recur2(0) => 아무것도 하지 않음!
  - **4 출력**
  - recur2(3)
    - recur2(1)
      - recur2(-1) => 아무것도 하지 않음!
      - **1 출력**
      - recur2(0) => 아무것도 하지 않음!
    - **3 출력**
    - recur2(2)
      - recur2(0) => 아무것도 하지 않음!
      - **2 출력**
      - recur2(1)
        - recur2(-1) => 아무것도 하지 않음!
        - **1 출력**
        - recur2(0) => 아무것도 하지 않음!

출력 결과: 2 1 4 1 3 2 1

**상향식 분석**

`recur2` 메서드는 n이 양수일 때만 실행하므로 `recur2(1)`를 우선적으로 고려한다.

- recur2(1)
  - recur2(-1)를 호출한다. => 아무것도 하지 않는다.
  - 1을 출력한다.
  - recur2(0)를 호출한다. => 아무것도 하지 않는다.
- recur2(2)
  - recur2(0)를 호출한다. => 아무것도 하지 않는다.
  - 2를 출력한다.
  - recur2(1)를 호출한다 => 1을 출력한다.
- recur2(3)
  - recur2(1)를 호출한다. => 1을 출력한다.
  - 3을 출력한다.
  - recur2(2)를 호출한다. => 2를 출력하고 1을 출력한다.
- recur2(4)
  - recur2(2)를 호출한다. -> 2를 출력하고 1을 출력한다.
  - 4를 출력한다.
  - recur2(3)을 호출한다 => 1, 3, 2, 1을 출력한다.

출력 결과 2 1 4 1 3 2 1

### 재귀 알고리즘의 비재귀적 표현

#### 꼬리 재귀(tail recursion)의 제거

- 메서드의 꼬리에서 재귀 호출하는 메서드 recur(n -2)라는 말은 '**인자로 n-2를 전달하여 recur메서드를 호출한다**'는 의미이다.
- **n의 값을 n-2로 업데이트**하고 **메서드의 시작 지점으로 돌아간다.**

```java
// 꼬리 재귀를 제거했다.
static void recur(int n) {
  while (n < 0) {
    recur(n - 1);
    System.out.println(n);
    n = n - 2;
  }
}
```

#### 재귀의 제거 

하나의 재귀함수를 제거하는 것은 어렵지 않다. 그러나 이는 tail recursion일 때만이다. head recursion은 말이 달라진다. n-1을 수행하는 동안 잠깐 n을 저장해야 할 필요가 있고, 또 n-1을 처리한 후 저장한 n을 다시 불러와야 하기 때문이다. 또한 재귀가 두 개 공존하고 있을 경우, 각각의 재귀함수의 처리 여부를 구분시켜주는 변수도 따로 필요하다.

```java
static void recur(int n) {
  IntStack s = new IntStack();
  while (true) {
    if (n > 0) {
      s.push(n);
      n = n - 1;
      continue;
    }
    
    if (!s.isEmpty()) {
      n = s.pop();
      System.out.println(n);
      n = n - 2;
      continue;
    }
    break;
  }
}

// recur(4)
// stack: 4 3 2 1 
// sysout(1) n = -1
// sysout(2) n = 0
// sysout(3) n = 1
// s.push(1) -> stack: 4 1
// sysout(4) n = 2
// s.push(2) -> stack 1 2
// sysout(1) n = -1 (stack 1)
// sysout(2) n = 0 (stack)
```



## 하노이의 탑

- 쌓아 놓은 원반을 최소의 횟수로 옮기기 위한 알고리즘
- 가장 큰 원반을 최소의 단계로 목표 기둥으로 옮기려면 가장 먼저 '그룹'을 중간 기둥으로 옮겨야 한다. 그러면 총 3단계로 완료된다.
- 원반 3개를 옮기는 과정 (1, 2가 하나의 그룹)
  - 그룹을 시작 기둥에서 중간 기둥으로
  - 원반 3을 시작 기둥에서 목표 기둥으로
  - 그룹을 중간 기둥에서 목표 기둥으로
- 원반 2개를 옮기는 과정 (1이 하나의 그룹)
  - 그룹(원반1)을 시작 기둥에서 중간 기둥으로
  - 원반 2를 시작 기둥에서 목표 기둥으로
  - 그룹(원반1)을 중간 기둥에서 목표 기둥으로
- 원반 4개를 옮기는 과정(1, 2, 3이 하나의 그룹)
  - 그룹(1, 2, 3)을 시작 기둥에서 중간 기둥으로
  - 원반 4를 시작 기둥에서 목표 기둥으로
  - 그룹을 중간 기둥에서 목표 기둥으로 

아래 프로그램은 기둥 번호를 정수 1, 2, 3으로 나타낸다. 기둥 번호의 합이 6이므로 시작 기둥, 목표 기둥이 몇 번이든 **중간 기둥**은 **6 - x - y로 구할 수 있다.** 원반은 no개이므로 move 메서드는

- 바닥 원반을 제외한 그룹(원반[1] ~ 원반[no -1])을 시작 기둥에서 중간 기둥으로 옮긴다.
- 바닥 원반 no를 시작 기둥에서 목표 기둥으로 옮겼음을 출력한다.
- 바닥 원반을 제외한 그룹(원반[1] ~ 원반[no-1])을 중간 기둥에서 목표 기둥으로 옮긴다.

```java
public class Hanoi {
  static void move(int no, int x, int y) {
    if (no > 1)
      move(no - 1, x, 6 - x - y);
    System.out.println("원반 [" + no + "]을 " + x + "기둥에서 " + y + "기둥으로 옮김");
    
    if (no > 1)
      move(no - 1, 6 - x - y, y);
  }
  
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    
    System.out.println("하노이의 탑");
    System.out.print("원반 개수: ");
    int n = sc.nextInt();
    
    move(n, 1, 3);
  }
}
```

실행 결과

```console
하노이의 탑
원반 개수: 3
원반 [1]을 1기둥에서 3기둥으로 옮김
원반 [2]을 1기둥에서 2기둥으로 옮김
원반 [1]을 3기둥에서 2기둥으로 옮김
원반 [3]을 1기둥에서 3기둥으로 옮김
원반 [1]을 2기둥에서 1기둥으로 옮김
원반 [2]을 2기둥에서 3기둥으로 옮김
원반 [1]을 1기둥에서 3기둥으로 옮김
```

- move(3, 1, 3): `{1, 2, 3}`을 기둥1에서 기둥3으로
  - move(2, 1, 2): `{1, 2}`을 기둥1에서 기둥2로
    - move(1, 1, 3): `{1}`을 기둥1에서 기둥3으로
    - 원반2를 기둥1에서 기둥2로
    - move(1, 3, 2): `{1}`을 기둥3에서 기둥2로
  - 원반3을 기둥1에서 기둥3으로
  - move(2, 2, 3): `{1, 2}`을 기둥2에서 기둥3으로
    - move(1, 2, 1): `{1}`을 기둥2에서 기둥1로
    - 원반2를 기둥2에서 기둥3으로
    - move(1, 1, 3): `{1}`을 기둥1에서 기둥3으로

**연습문제 Q5** 

```java
static void recur3(int n) {
  if (n > 0) {
    recur3(n - 1);
    recur3(n - 2);
    System.out.println(n);
  }
}
```

위 recur3 메서드를 비재귀적으로 구현하라.

```java
static void recur3(int n) {
  int[] nstack = new int[100];
  int[] sstack = new int[100];
  int ptr = -1;
  int sw = 0;

  while (true) {
    if (n > 0) {
      ptr++;
      nstk[ptr] = n;
      sstk[ptr] = sw;

      if (sw == 0)
        n = n - 1;
      else if (sw == 1) {
        n = n - 2;
        sw = 0;
      }
      continue;
    }
    do {
      n = nstk[ptr];
      sw = sstk[ptr--] + 1;

      if (sw == 2) {
        System.out.println(n);
        if (ptr < 0)
          return;
      }
    } while (sw == 2);
  }
}
```

위와 같이 head recursion의 경우 n-1을 수행하는 동안 잠깐 n을 저장해야 하고, n-1을 처리한 후 저장한 n을 다시 불러와야 하기 때문에 비재귀 처리가 까다롭다. 특히 여기서는 head recursion이 두 개 공존하고 있다. 따라서 n을 저장하는 스택뿐만 아니라 재귀함수의 처리 여부를 구분해주는 변수도 필요하다.

sw 변수로 재귀 반복 완료 상태를 구분한다. 0일 경우 어떠한 재귀문도 실행되지 않은 상태, 1일 경우 첫 번째 재귀문`recur3(n-1)`이 모두 처리된 상태, 2일 경우 두 번째 재귀문`recur3(n-2)`까지 모두 처리된 상태를 말한다.

**연습문제 Q6**. 하노이의 탑 문제에서 숫자가 아닌 문자열로 기둥 이름을 출력하도록 프로그램을 수정하라. 

```java
static void move(int no, int x, int y) {
  String[] pillarName = {"A기둥", "B기둥", "C기둥"};
  if (no > 1)
    move(no - 1, x, 6 - x - y);
  System.out.println("원반 [" + no + "]을 "  + pillarName[x - 1] + "에서 " + pillarName[y - 1] + "으로 옮긴다.");
  if (no > 1)
    move(no - 1, 6 - x - y, y);
}
```

> 처음에는 스위치문으로 풀려고 했는데 이 방법이 훨씬 편한 것 같다. 개인 프로젝트도 다 이런 식으로 바꿔야지.

**연습문제 Q7.** move 메서드를 비재귀적으로 수정하라.

```java
static void move(int no, int x, int y) {
  int[] xstk = new int[100];
  int[] ystk = new int[100];
  int[] sstk = new int[100];
  int ptr = 0;
  int sw = 0;
  
  while (true) {
    if (sw == 0 && no > 1) {
      xstk[ptr] = x;
      ystk[ptr] = y;
      sstk[ptr] = sw;
      ptr++;
      no = no - 1;
      y = 6 - x - y;
      continue;
    }
    
    System.out.printf("[%d]를 %d기둥에서 %d기둥으로 옮김\n", no, x, y);
    
    if (sw = 1 && no > 1) {
      xstk[ptr] = x;
      ystk[ptr] = y;
      sstk[ptr] = sw;
      ptr++;
      no = no - 1;
      x = 6 - x - y;
      if (++sw == 2)
        sw = 0;
      continue;
    }
    do {
      if (ptr-- == 0) // 
    }
  }
}
```



## 8퀸 문제

- 서로 공격하여 잡을 수 없도록 8개의 퀸을 8x8 체스판에 놓는 문제
- 한정 조작: 필요하지 않은 분기를 없애 불필요한 조합을 줄이는 방법
- 분기 한정법: 가지 뻗기와 한정 조작을 조합하여 풀어가는 방법

```java
public class QueenB {
  static int[] pos = new int[8];

  static void print() {
    for (int i = 0; i < 8; i++)
      System.out.printf("%2d", pos[i]);
    System.out.println();
  }

  static void set(int i) {
    for (int j = 0; j < 8; j++) {
      pos[i] = j;
      if (i == 7)
        print();
      else
        set(i + 1);
    }
  }

  public static void main(String[] args) {
    set(0);
  }
}
```



```java
public class QueenBB {
  static boolean[] flag = new boolean[8];
  static int[] pos = new int[8];

  static void print() {
    for (int i = 0; i < 8; i++)
      System.out.printf("%2d", pos[i]);
    System.out.println();
  }

  static void set(int i) {
    for (int j = 0; j < 8; j++) {
      if (flag[j] == false) {
        pos[i] = j;
        if (i == 7)
          print();
        else {
          flag[j] = true;
          set(i + 1);
          flag[j] = false;
        }
      }
    }
  }

  public static void main(String[] args) {
    set(0);
  }
}
```

```java
public class EightQueen {
  static boolean[] flag_a = new boolean[8];
  static boolean[] flag_b = new boolean[15];
  static boolean [] flag_c = new boolean[15];
  static int[] pos = new int[8];

  static void print() {
    for (int i = 0; i < 8; i++)
      System.out.printf("%2d", pos[i]);
    System.out.println();
  }

  static void set(int i) {
    for (int j = 0; j < 8; j++) {
      if (flag_a[j] == false &&
          flag_b[i + j] == false &&
          flag_c[i - j + 7] == false) {
        pos[i] = j;
        if (i == 7)
          print();
        else {
          flag_a[j] = flag_b[i + j] = flag_c[i - j + 7] = true;
          set(i + 1);
          flag_a[j] = flag_b[i + j] = flag_c[i - j + 7] = false;
        }
      }
    }
  }

  public static void main(String[] args) {
    set(0);
  }
}
```