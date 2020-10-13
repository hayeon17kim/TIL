# 검색

## 검색 알고리즘

데이터 집합에서 원하는 값을 가진 요소를 찾아내는 알고리즘

### 검색과 키

- 어떤 검색을 하더라도 특정 항목에 주목한다는 점은 *검색하기*의 공통점이다. 여기서 **주목하는 항목**을 키(key)라고 한다.
- 대부분의 경우에서 키는 **데이터의 일부**이다.

- 키 값을 지정하는 방법
  - 키 값과 **일치**하도록 지정한다.
  - 키 값의 **구간**을 지정한다.
  - 키 값과 **비슷**하도록 지정한다.



### 배열에서 검색하기

- 검색 기법의 종류
  - 배열 검색
  - 선형 리스트 검색
  - 이진검색트리 검색
  - 일부 문자열을 검색
- 일부 검색 기법은 자료구조에 의존한다.

- 배열 검색 알고리즘
  - 선형 검색: 무작위로 늘어놓은 데이터 모임에서 검색을 수행한다.
  - 이진 검색: 일정한 규칙으로 늘어놓은 데이터 모임에서 아주 빠른 검색을 수핸한다.
  - 해시법: 추가, 삭제가 자주 일어나는 데이터 모임에서 아주 빠른 검색을 수핸한다.
    - 체인법: 같은 해시 값의 데이터를 선형 리스트로 연결하는 방법
    - 오픈 주소법: 데이터를 위한 해시 값이 충돌할 때 재해시하는 방법
- 검색에 사용할 알고리즘 선택 방법
  - 검색이 중요하다면 계산 시간이 짧은 것을 선택한다.
  - 검색뿐만 아니라 데이터의 추가, 삭제를 자주해야 한다면 검색 이외에 소요되는 비용을 종합적으로 평가해서 선택한다.
  - 즉, 용도나 목적, 실행속도, 자료구조 등을 고려해서 알고리즘을 선택해야 한다.
- 배열은 검색은 빠르지만 데이터를 추가하기 위한 비용은 많이 든다. 
  - 인덱스만 알면 바로 값을 알 수 있다.
  - 중간에 값을 기워넣으려면 이후의 값을 모두 뒤로 밀어 넣는 작업을 해야 한다.

## 선형 검색

### 선형 검색

요소가 직선 모양으로 늘어선 배열에서의 검색은 **원하는 키 값을 갖는 요소를 만날 때까지 맨 앞부터 순서대로 요소를 검색**한다. 이를 선형 검색(linear search) 또는 순차 검색(sequential search)라고 한다. **선형 검색은 배열에서 순서대로 검색하는 유일한 방법**이다.

- 배열 검색의 종료 조건
  - 검색 실패: 검색할 값을 발견하지 못하고 배열의 끝을 지나간 경우
  - 검색 성공: 검색할 값과 같은 요소를 발견할 경우

**실습 3-1.** while문으로 선형 검색 구현

```java
static int seqSearch(int[] a, int n, int key) {
  int i = 0;

  while (true) {
    if (i == n)
      return -1;
    if (a[i] == key)
      return i;
    i++
  }
}
```

- 값이 key인 요소가 여러 개 존재할 경우 반환값은 검색 과정에서 처음 발견한 요소의 인덱스가 된다. 값이 key인 요소가 존재하지 않으면 -1을 반환한다.

- 종료조건
  - 검색실패: i == n이 성립하는 경우 => -1 반환
  - 검색성공: a[i] == key가 성립하는 경우 => i 반환
- 조건 판단 횟수는 3회이다.
  - while(1)
  - if ( i == n )
  - a[i] == key

**실습 3-2. ** for문으로 선형 검색 구현

```java
static int seqSearch(int[] a, int n, int key) {
  for (int i = 0; i < n; i++)
    if (a[i] == key)
      return i; // 검색 성공!
  return -1; // 검색 실패!
}
```

> 무한 루프의 구현: break나 return문으로 빠져나올 수 있다. 
>
> - `while(true)`, `for( ; ; )`, `do {} while(true);`
> - 끝까지 읽지 않으면 무한 루프인지 아닌지 알 수 없는 do 문에 의한 무한 루프 구현은 권장하지 않는다.



### 보초법 (sentinel metod)

- 선형검색은 반복할 때마다 종료조건 두가지를 모두 판단한다.
  - 종료조건1: 검색할 값을 발견하지 못하고 배열의 끝을 지나간 경우
  - 종료조건2: 검색할 값과 같은 요소를 발견한 경우
- 종료조건을 검사하는 비용을 반으로 줄이는 방법이 **보초법(sentinel method)**이다.
- 검색하기 전 검색하고자 하는 키 값을 맨 끝 요소 `a[검색하고자하는값들의개수]`에 저장한다.
- 저장하는 값이 **보초**이다.

**실습 3-3. ** 선형검색 + 보초법

```java
// 이때 넘겨주는 배열의 마지막 요소는 비어있다.
static int seqSearchSen(int[] a, int n, int key) {
  int i = 0;
  
  a[n] = key; // 보초 추가
  
  while (true) {
    if (a[i] == key) // 검색 성공!
      break;
    i++;
  }
  // 마지막에 원래 데이터인지 보초인지 판단
  return i == n? -1 : i;
}
```

- 앞선 while문에서는 종료조건이 `if(i==n)`, `if(a[i]==k)`로 두가지였다. 보초법을 사용할 경우 첫번째 종료조건은 필요 없다. 따라서 반복 종료에 대한 판단 횟수는 절반으로 줄어든다.
- while문에 의한 반복이 완료되면 찾은 값이 배열의 원래 데이터인지 아니면 보초인지 판단해야 한다.



## 이진 검색

### 이진 검색(binary search)

- 요소가 오름차순 또는 내림차순으로 정렬된 배열에서 검색하는 알고리즘이다.
- 전제조건: 데이터가 키 값으로 이미 정렬(sort) 되어 있다.
- 장점: 선형 검색보다 빠르게 검색할 수 있다.

#### 일반적인 방법

- n개의 요소가 오름차순으로 늘어선 배열 a에서 이진 검색으로 검색하는 과정
- 인덱스 설정
  - 맨 앞 인덱스: `p1 = 0`
  - 맨 끝 인덱스: `pr = n - 1`
  - 중앙 인덱스: `pc = (n - 1) / 2` (앞과 끝 인덱스의 거리 / 2)
- 이진 검색을 **한 단계씩 진행할 때마다 검색 범위가 거의 반으로 좁혀진다.**
- **검사한 요소를 하나씩 제외시키는 선형 검색**과는 다르게, 이진 검색은 **검색할 요소(중앙요소, pc)가 해당 단계에서 다음에 검색할 범위의 중간 지점으로 단숨에 이동**한다.
- 경우의 수
  - `a[pc] == key`: 검색 성공!
  - `a[pc] < key`
    - `a[pl] ~ a[pc]`는 key보다 작은 것이 분명하므로 검색 대상에서 제외한다.
    - 검색 범위는 중앙요소보다 뒤쪽의 `a[pc + 1] ~ a[pr]`로 좁힌다.
    - pl의 값을 pc + 1로 업데이트한다.
  - `a[pc] > key`
    - `a[pc + 1] ~ a[pr]`은 key보다 클 것이 분명하므로 검색 대상에서 제외한다.
    - 검색의 범위는 중앙 요소보다 앞쪽의 `a[pl] ~ a[pc - 1]`로 좁힌다.
    - pr의 값을 pc - 1로 업데이트한다.
- 종료 조건
  - `a[pc]`와 key가 일치하는 경우
  - 검색 범위가 더 이상 없는 경우

- 이진 검색은 검색을 반복할 때마다 검색 범위가 절반이 되므로 검색에 필요한 비교 횟수의 평균값은 **logn**이다.
  - 검색실패: `⌈log(n+1)⌉`회
  - 검색성공: `⌈logn - 1⌉`회 

> 천장 메서드(ceiling function): 올림 메서드라고도 한다. `⌈ x ⌉`는 x의 천장 메서드이며, x보다 크거나 같으면서 가장 작은 정수이다. 예를 들어 `⌈ 3.5 ⌉`는 4이다.



**실습 3-4.** 이진 검색

```java
static int binSearch(int[] a, int n, int key) {
  int pl = 0;
  int pr = n - 1;
  
  do {
    int pc = (pr - pl) / 2;
    if (a[pc] == key)
      return pc;
    else if (a[pc] < key)
      pl = pc + 1;
    else
      pr = pc - 1;
  } while (pl <= pr);
}
```



### 복잡도

- 프로그램의 실행 속도는 프로그램이 동작하는 하드웨어나 컴파일러 등의 조건에 따라 달라진다.
- **알고리즘 성능을 객관적으로 평가하는 기준**을 **복잡도(complexitiy)**라고 한다.
  - 시간 복잡도(time complexity): 실행에 필요한 시간을 평가한 것
  - 공간 복잡도(space complexity): 기억 영역과 파일 공간이 얼마나 필요한 가를 평가한 것

#### 선형 검색의 시간 복잡도

```java
static int seqSearch(int[] a, int n, int key) {
  int i = 0;         // 1회: O(1)
  while (i < n) {    // n/2회: O(n)
    if (a[i] == key) // n/2회: O(n)
      return i;      // 1회: O(1)
    i++;					  // n/2회: O(n)
  }
  return -1;         // 1회: O(1)
}
```

- **O(n)**: n에 비례하는 횟수만큼 실행하는 경우의 복잡도
- **O(1)**: n과 무관한 상수 횟수만큼 실행한는 경우의 복잡도
- n/2회 실행했을 때 복잡도를 O(n/2)r가 아닌 O(n)으로 표현하는 이유는 n의 값이 무한히 커진다고 가정했을 때, 그 차이가 무의미해지기 때문이다. 마찬가지로 100번만 실행하는 경우에도 O(100)이 아닌 O(1)로 표현한다.

> O는 Order에서 따온 것으로, O(n)은 'O-n', 'Order n', 'n의 Order'라고 읽는다.

- `O(f(n))`과 `O(g(n))`의 복잡도를 계산하는 방법

  - `O(f(n)) + O(g(n)) = O(max(f(n), g(n)))`

  - > max(a, b)는 a와 b 중 큰 쪽을 나타내는 메서드이다.

- 전체 복잡도는 차원이 가장 높은 복잡도를 선택한다.

- 선형 알고리즘의 복잡도

  -  `O(1) + O(n) + O(n) + O(1) + O(n) + O(1)` = `O(max(1, n, n, 1, n, 1))`= **O(n)**

**연습문제 Q1. ** 실습 3-3의 seqSearchSen 메서드를 while문이 아니라 for문을 사용하여 수정한 프로그램을 작성하시오.

```java
static int seqSearchSen(int[] a, int n, int key) {
  int i;
  a[n] = key;
  for (i = 0; a[i] == key; i++);
  return i == n ? -1 : i;
}
```



**연습문제 Q2.** 선형 검색의 스캐닝 과정을 상세하게 출력하는 프로그램을 작성하시오.

```java
static int seqSearch(int a[], int n, int key) {
  System.out.print("  | ");
  for (int k = 0; k < n; k++)
    System.out.printf("   %d", k);
  System.out.println();
  for (int k = 0; k < 4 * n + 2; k++)
    System.out.printf("-"); // 4*n + 2 번 출력
  int i;
  // n줄
  for (i = 0; i <= n; i++) {
    System.out.println();
    System.out.printf(String.format("   %%%ds*\n", (i * 4) + 3), "");
    System.out.printf("%2d|", i);
    for (int k = 0; k < n; k++)
      System.out.printf("%4d", a[k]);
    if (a[i] == key)
      break;
  }
  System.out.println();
  return i == n ? -1 : i;
}
```

- `System.out.printf(String.format("   %%%ds*\n", (i * 4) + 3), "");`
  - `%d`에 `i * 4 + 3`이 대입된다.
  - `"   (4*i + 3의 결과값)%s*\n"` 문자열을 생성한다.
  - 생성된 문자열은 `""` 빈 문자열과 함께 `printf`의 파라미터로 넣어준다.
  - 따라서 4*i + 3의 결과값만큼 공백이 생기고, 별표와 줄바꿈기호가 뒤에 붙는다.
- 실행 결과는 다음과 같다.

```console
  |   0   1   2   3
------------------
      *
 0|   1   2   3   4
          *
 1|   1   2   3   4
              *
 2|   1   2   3   4
                  *
 3|   1   2   3   4
4는 x[3]에 있습니다.
```



#### 이진 검색의 시간 복잡도

```java
static int binSearch(int[] a, int n, int key) {
  int pl = 0;			//1회 => O(1)
  int pr = n -1;	//1회 => O(1)
  
  do {
    int pc = (pl + pr) / 2; // O(log n)
    if (a[pc] == key)			 // O(log n)
      return pc;					 // O(1)
    else if (a[pc] < key)		// O(log n)
      pl = pc + 1;				 // O(log n)
    else 									
      pr = pc - 1; 				 // O(log n)
  } while (pl <= pr);			 // O(log n)
  return -1;							// O(1)
}
```

- 이진 검색 알고리즘의 복잡도:  **O(log n)**



- 복잡도의 대소 관계
  - 1 < log n < n < n log n < n^2^ < n^3^ < n^k^ < 2^n^



**연습문제 Q3.** 요솟수가 n인 배열 a에서 key와 일치하는 모든 요소의 인덱스를 배열 idx의 맨 앞부터 순서대로 저장하고, 일치한 요솟수를 반환하는 메서드를 작성하세요.

```java
public static int searchIdx(int[] a, int n, int key, int[] idx) {
  int size = 0;
  for (int i = 0; i < a.length; i++) {
    if (a[i] == key)
      idx[size++] = i;
  }
  return size;
}
```



**연습문제 Q4.** 이진 검색 과정을 자세히 출력하는 프로그램을 작성하세요.

```java
  static int binSearchEx(int[] a, int n, int key) {
    System.out.print("   |");
    for (int k = 0; k < n; k++)
      System.out.printf("%4d", k);
    System.out.println();

    System.out.print("---+");
    for (int k = 0; k < 4 * n + 2; k++)
      System.out.print("-");
    System.out.println();

    int pl = 0; // 검색범위 맨 앞의 index
    int pr = n - 1; // 검색범위 맨 뒤의 index

    do {
      int pc = (pl + pr) / 2; // 중앙요소의 index
      System.out.print("   |");
      if (pl != pc)
        System.out.printf(String.format("%%%ds<-%%%ds+", (pl * 4) + 1, (pc - pl) * 4), "", "");
      else
        System.out.printf(String.format("%%%ds<-+", pc * 4 + 1), "");
      if (pc != pr)
        System.out.printf(String.format("%%%ds->\n", (pr - pc) * 4 - 2), "");
      else
        System.out.println("->");
      System.out.printf("%3d|", pc);
      for (int k = 0; k < n; k++)
        System.out.printf("%4d", a[k]);
      System.out.println("\n   |");
      if (a[pc] == key)
        return pc; // 검색성공
      else if (a[pc] < key)
        pl = pc + 1; // 검색범위를 뒤쪽 절반으로 좁힘
      else
        pr = pc - 1; // 검색범위를 앞쪽 절반으로 좁힘
    } while (pl <= pr);
    return -1; // 검색실패
  }
```



**연습문제 Q5.** 맨 앞의 요소를 찾는 이진 알고리즘 binSearchX 메서드를 작성해보세요.

- 이진 검색 알고리즘에 의해 검색에 성공했을 때 그 위치로부터 앞쪽으로 하나씩 검사하면 여러 요소가 일치하는 경우에도 가장 앞쪽에 위치하는 요소의 인덱스를 찾아낸다.
- 배열의 맨 앞을 넘지 않는 범위에서 같은 값의 요소가 계속되는 한 앞쪽으로 스캔한다.

```java
static int binSearchX(int[] a, int n, int key) {
  int pl = 0;
  int pr = n - 1;
  do {
    int pc = (p1 + pr) / 2;
    // 앞쪽으로 스캔
    if (a[pc] == key) {
      while (pc != 0 && a[pc - 1] == a[pc]) {
        pc = pc - 1;
      }
      return pc;
    } else if (a[pc] > key) {
      pr = pc - 1;
    } else
      pl = pc + 1;
  } while (pl <= pr);
  return -1;
}
```

답안에서는 다음과 같이 구현하였다.

```java
// 배열 a의 앞쪽 n개 요소에서 key와 같은 요소를 이진검색
static int binSearchX(int[] a, int n, int key) {
  int pl = 0; // 검색범위 맨 앞의 index
  int pr = n - 1; // 검색범위 맨 뒤의 index

  do {
    int pc = (pl + pr) / 2; // 중앙요소의 index
    if (a[pc] == key) {
      for (; pc > pl; pc--) // key와 같은 맨 앞의 요소를 찾습니다
        if (a[pc - 1] < key)
          break;
      return pc; // 검색성공
    } else if (a[pc] < key)
      pl = pc + 1; // 검색범위를 앞쪽 절반으로 좁힘
    else
      pr = pc - 1; // 검색범위를 뒤쪽 절반으로 좁힘
  } while (pl <= pr);

  return -1; // 검색실패
}
```



### Arrays.binarySearch에 의한 이진 검색

- Java는 이진 검색을 하는 메서드인 Arrays.binarySearch를 표준 라이브러리로 제공한다.
- binarySearch 메서드의 장점
  - 이진 검색 메서드를 직접 코딩할 필요가 없다.
  - 모든 자료형 배열에서 검색할 수 있다.

> API 문서란? 라이브러리를 사용하는 방법을 작성해놓은 것이다. 

- 오름차순으로 정렬된 배열 a를 가정하고, 키 값이 key인 요소를 이진 검색한다.
- 자료형에 따라 9가지 방법으로 오버로딩(overloading)되어 있다.
  - 원시타입
  - `static int binarySearch(Object[] a, Object key)`
  - `static <T> int binarySearch(T[] a, T key, Comparator <? super T> c)`
- 검색에 성공한 경우
  - key와 일치하는 요소의 인덱스를 반환
  - **일치하는 요소가 여러 개** 있다면 **무작위 인덱스**를 반환
- 검색에 실패하는 경우
  - 반환값은 삽입 포인트를 x라 할 때 -x-1을 반환
  - **삽입 포인트**: 검색하기 위해 지정한 **key**보다 큰 요소 중 첫 번째 요소의 인덱스
  - 배열의 **모든 요소가 key보다 작다면**? **배열의 길이**를 삽입 포인트로 정한다.


#### 기본 자료형 배열에서 binarySearch 메서드로 검색하기

**실습 3-5. ** 

```java
public class BinarySearchTester {
  public static void main(String[] args) {
    
    String str = new String();
    Scanner sc = new Scanner(System.in);
    System.out.print("요솟수");
    int num = sc.nextInt();
    int[] x = new int[num];
    
    System.out.println("오름차순으로 입력하세요.");
    
    System.out.print("x[0]: ");
    x[0] = sc.nextInt();
    
    for (int i = 1; i < num; i++) {
      do {
        System.out.print("x[" + i + "]: ");
        x[i] = sc.nextInt();
      } while (x[i] < x[i - 1]);
    }
    
    System.out.print("검색할 값: ");
    int key = sc.nextInt();
    
    int idx = Arrays.binarySearch(x, key);
    
    if (idx < 0)
      System.out.println("그 값의 요소가 없습니다.");
    else
      System.out.println(key + "는 x[" + idx + "]에 있습니다.");
  }
}
```



**연습문제 Q6.** 

```java
public class Q06 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.print("요솟수");
    int num = sc.nextInt();
    int[] x = new int[num];
    
    System.out.println("오름차순으로 입력하세요.");
    
    System.out.print("x[0]: ");
    x[0] = sc.nextInt();
    
    for (int i = 1; i < num; i++) {
      do {
        System.out.print("x[" + i + "]: ");
        x[i] = sc.nextInt();
      } while (x[i] < x[i - 1]);
    }
    
    System.out.print("검색할 값: ");
    int key = sc.nextInt();
    
    int idx = Arrays.binarySearch(x, key);
    
    if (idx < 0)
      System.out.println("삽입 포인트의 값은 " + (-idx - 1) + "입니다.");
    else
      System.out.println(key + "는 x[" + idx + "]에 있습니다.");
  }
}
```



#### 클래스 메서드와 인스턴스 메서드

두 메서드의 차이점은 **메서드가 인스턴스에 포함되는지의 여부**에 있다. 그래서 클래스 메서드는 **클래스 전체에 대한 처리**를 담당하며 인스턴스 메서드와 **처리 영역을 구분**하기 위해 주로 사용한다. 



**실습 3C-1**

```java
class Id {
  // static 변수
  private static int counter = 0; // 아이디를 몇 개 부여했는지 저장
  // non-static 변수
  private int id;
  
  public Id() {
    id = ++counter;
  }
  
  public int getId() {
    return id;
  }
  
  public static int getCounter() {
    return counter;
  }
  
}
public class IdTester {
  public static void main(String[] args) {
    Id a = new Id(); // 아이디 1
    Id b = new Id(); // 아이디 2
    
    System.out.println("a의 아이디: " + a.getId());
    System.out.println("b의 아이디: " + b.getId());
    
    System.out.println("부여한 아이디의 개수: " + Id.getCounter());
  }
}
```

- 클래스 메서드와 마찬가지로 클래스 변수도 **인스턴스에 포함되지 않는 변수**이다.
- 인스턴스의 개수와 관계 없이 1개만 만들어진다. 즉, 클래스 변수 counter를 통해 몇 개의 아이디를 만들었는지 알 수 있으며 아이디로 사용할 수 있다.
- 인스턴스 getId는 개별 인스턴스의 아이디를 반환한다.
- 클래스 메서드 getCoutner는 마지막으로 부여한 아이디를 반환한다.



#### 객체의 배열에서 검색하기

- `static int binarySearch(Object[] a, Object key)`
  - **자연 정렬**이라는 방법으로 요소의 **대소 관계를 판단**한다.
  - 정수 배열, 문자열 배열에서 검색할 때 적당하다.
- `static <T> int binarySearch(T[] a, T key, Comparator<? super T> c)`
  - **자연 순서가 아닌 순서**로 줄지어 있는 배열에서 검색하거나, **자연 순서를 논리적으로 갖지 않는** 클래스 배열에서 검색할 때 알맞다.



#### 자연 정렬로 정렬된 배열에서 검색하기

**실습 3-6**

```java
class StringBinarySearch {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    
    String[] x = {
      "apple", "basic", "cat", "day", "element", "fade", "gain", "height", "icecream"
    };
    
    System.out.print("원하는 키워드를 입력하세요. : "); // 키 값을 입력
    
    String ky = sc.next();
    
    int idx = Arrays.binarySearch(x, ky); // 배열 x에서 값이 ky인 요소를 입력
    
    if (idx < 0)
      System.out.println("해당 키워드가 없습니다.");
    else
      System.out.println("해당 키워드는 x[" + idx + "]에 있습니다.");
  }
}
```

```console
원하는 키워드를 입력하세요. : fade
해당 키워드는 x[5]에 있습니다.
```



#### 자연정렬

binarySearch 메서드에 배열과 키 값을 전달하는 간단한 방법으로 검색할 수 있는 이유는 **String 클래스가 Comparable<T> 인터페이스와 compateTo 메서드를 구현**하고 있기 때문이다.

```java
public int compareTo(String anotherString) {
  int len1 = value.length;
  int len2 = anotherString.value.length;
  int lim = Math.min(len1, len2);
  char v1[] = value;
  char v2[] = anotherString.value;

  int k = 0;
  while (k < lim) {
    char c1 = v1[k];
    char c2 = v2[k];
    if (c1 != c2) {
      return c1 - c2;
    }
    k++;
  }
  return len1 - len2;
}
```



| 문자열 정렬 | 자연 정렬 |
| ----------- | --------- |
| 텍스트1     | 텍스트1   |
| 텍스트10    | 텍스트2   |
| 텍스트100   | 텍스트10  |
| 텍스트2     | 텍스트21  |
| 텍스트21    | 텍스트100 |

둘 다 정렬이 되었다는 사실에는 문제가 없지만, 문자열 정렬은 자연스럽지 않다. 컴퓨터의 문자열 정렬은 동일한 위치에 있는 문자의 대소 비교를 통해 정렬하기 때문이다. 하지만 사람에게는 오른쪽이 더 자연스럽다. 

자신이 직접 만들 클래스 A에 대해서도 자연스러운 순서로 정렬할 필요가 있겠다는 생각이 든다면 다음과 같이 클래스를 정의할 수 있다.

```java
class A implements Comparable<A> {
  public int compareTo(A c) {
    // this가 c보다 크면 양의 값 반환
    // this가 c보다 작으면 음의 값 반환
    // this가 c와 같으면 0 반환
  }
  
  public boolean equals(Object c) {
    // this와 c가 같으면 true 반환
    // this가 c와 같지 않으면 false 반환
  }
}
```



#### 자연 정렬로 정렬되지 않은 배열에서 검색하기

- `static <T> int binarySearch(T[] a, T key, Comparator<? super T> c)`
  - T[] a: 검색 대상
  - T key: 키값
  - Comparator<? super T>
    - 배열의 요소가 **어떤 순서**로 줄지어 있는지, 각 요소의 **대소 관계는 어떻게 판단**할 것이지에 대한 **정보**
    - 클래스 T(또는 클래스 T의 수퍼 클래스)로 생성한 두 객체의 대소를 판단하기 위한 comparator이다.  안에는 cmpare 메서드가 있다.
- 제네릭 메서드는 자료형에 구애받지 않는다.

**java.util.Comparator**

```java
package java.util;

public interface Comparator<T> {
  int compare(T o1, T o2);
  boolean equals(Object obj);
}
```

**실습 3-7.** 클래스 X에 대한 comparator 정의하기

```java
class X {
  public static final Comparator<T> COMPARATOR = new Comp();
  
  // Comparator 인터페이스와 compare 메서드 구현한 클래스 작성
  private static class Comp implements Comparator<T> {
    public int compare(T d1, T d2) {
      // d1이 d2보다 크면 양의 값 반환
      // 작으면 음의 값 반환
      // 같으면 0 반환
    }
  }
}
```

- binarySearch 메서드의 세 번째 매개변수로 클래스 X의 클래스변수인 `X.COMPARATOR`를 전달한다.
- 호출된 binarySearch 메서드는 전달받은 comparator를 기준으로 배열 요소의 대소 관계를 판단하여 이진 검색을 수행한다.

```java

public class PhysExamSearch {
  static class PhyscData {
    private String name;
    private int height;
    private double vision;

    public PhyscData(String name, int height, double vision) {
      this.name = name;
      this.height = height;
      this.vision = vision;
    }

    public String toString() {
      return name + " " + height + " " + vision;
    }

    // 오름차순으로 정렬하기 위한 comparator
    public static final Comparator<PhyscData> HEIGHT_ORDER = new HeightOrderComparator();

    private static class HeightOrderComparator implements Comparator<PhyscData> {
      public int compare(PhyscData d1, PhyscData d2) {
        return (d1.height > d2.height) ? 1 : 
          (d1.height < d2.height) ? -1 : 0;
      }
    }
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    PhyscData[] x = { // 배열의 요소는 시력순이지 않으면 안됩니다.
        new PhyscData("이나령", 162, 0.3), new PhyscData("유지훈", 168, 0.4), new PhyscData("전서현", 173, 0.7),
        new PhyscData("김한결", 169, 0.8), new PhyscData("이호연", 174, 1.2), new PhyscData("홍준기", 171, 1.5),
        new PhyscData("이수민", 175, 2.0), };
    System.out.print("몇 cm인 사람을 찾고 있나요?");
    int height = sc.nextInt();
    int idx = Arrays.binarySearch(x, new PhyscData("", height, 0.0), PhyscData.HEIGHT_ORDER);
    if (idx < 0)
      System.out.println("요소가 없습니다.");
    else {
      System.out.println("x[" + idx + "]에 있습니다.");
      System.out.println("찾은 데이터: " + x[idx]);
    }
  }
}

```



**연습문제 Q7.** 시력에 대한 내림차순 정렬의 데이터에서 특정 시력을 가진 사람을 검색하는 프로그램을 작성하시오.

```java
public class Q07 {
  static class PhyscData {
    private String name;
    private int height;
    private double vision;

    public PhyscData(String name, int height, double vision) {
      this.name = name;
      this.height = height;
      this.vision = vision;
    }

    public String toString() {
      return name + " " + height + " " + vision;
    }

    public static final Comparator<PhyscData> HEIGHT_ORDER = new HeightOrderComparator();

    private static class HeightOrderComparator implements Comparator<PhyscData> {
      public int compare(PhyscData d1, PhyscData d2) {
        return (d1.height > d2.height) ? 1 : 
          (d1.height < d2.height) ? -1 : 0;
      }
    }
    
    public static final Comparator<PhyscData> VISION_ORDER = new VisionOrderComparator();
    
    public static class VisionOrderComparator implements Comparator<PhyscData> {
      public int compare(PhyscData d1, PhyscData d2) {
        return (d1.vision > d2.vision) ? 1 :
          (d1.vision < d2.vision) ? -1 : 0;
      }
    }
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    PhyscData[] x = { // 배열의 요소는 시력순이지 않으면 안됩니다.
        new PhyscData("이나령", 162, 0.3), new PhyscData("유지훈", 168, 0.4), new PhyscData("전서현", 173, 0.7),
        new PhyscData("김한결", 169, 0.8), new PhyscData("이호연", 174, 1.2), new PhyscData("홍준기", 171, 1.5),
        new PhyscData("이수민", 175, 2.0), };
    System.out.print("시력이 몇인 사람을 찾나요?");
    double vision = sc.nextDouble();
    int idx = Arrays.binarySearch(x, new PhyscData("", 0, vision), PhyscData.VISION_ORDER);
    if (idx < 0)
      System.out.println("요소가 없습니다.");
    else {
      System.out.println("x[" + idx + "]에 있습니다.");
      Sysem.out.println("찾은 데이터: " + x[idx]);
    }
  }
}
```

