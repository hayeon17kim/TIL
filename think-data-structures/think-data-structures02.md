# 알고리즘 분석

## 프로파일링

어떤 응용 프로그램에 어느 클래스가 좋을 지 결정하는 방법 중 하나로, 둘 다 시도해보고 얼마나 걸리는 지 알아보는 접근법

### 문제점

- 알고리즘을 비교하려면 사전에 그것을 모두 구현해봐야 한다.
- 결과는 사용하는 컴퓨터의 성능에 의존한다. 한 알고리즘이 어떤 컴퓨터에서는 더 좋을 수 있지만, 다른 알고리즘은 다른 컴퓨터에서 더 좋을 수 있다.
- 결과는 문제 크기나 입력으로 사용하는 데이터에 의존하기도 한다.

알고리즘 분석을 사용하여 이러한 문제점을 해결할 수 있다.



## 알고리즘 분석(analysis of algorithm)

알고리즘 분석은 클래스를 구현하지 않고도 알고리즘을 비교할 수 있게 한다.

### 가정

- 컴퓨터 하드웨어의 세부사항을 다루지 않기 위해 알고리즘을 이루는 더하기와 곱하기, 숫자 비교 등의 기본 연산을 식별한다. 그리고 각 알고리즘에 필요한 연산 수를 센다.
- 입력 데이터의 세부사항을 다루지 않으려면 기대하는 입력 데이터에 대한 평균 성능을 분석하는 것이 가장 좋다. 이것이 가능하지 않다면, 일반적으로 최악의 시나리오를 분석하기도 한다.
- 큰 문제에서는 다른 알고리즘이 더 좋을 수 있다는 가능성을 배제해서는 안 된다. 작은 문제에서는 알고리즘의 차이가 크지 않지만, 큰 문제에서는 그 차이가 훨씨 클 수 있기 때문에 보통 큰 문제에 초점을 맞춘다.

이런 종류의 분석은 간단한 알고리즘 분류에 적합하다.

### 상수 시간(constant time)

실행시간이 입력 크기에 의존하지 않으면 알고리즘은 상수 시간을 따른다. 

n개의 배열에서 브래킷 연산을 사용하여 요소 중 하나에 접근할 때 이 연산은 배열에 크기와 관계 없이 같은 수의 동작을 수행한다.

### 선형(linear)

실행시간이 입력 크기에 비례하면 알고리즘은 선형이라고 한다. 

배열에 있는 요소를 더한다면 n개의 요소에 접근하여 n-1번 더하기 연산을 해야 한다. 연산(요소 접근과 더하기)의 총 횟수는 2n-1이고, n에 비례한다.

### 이차(quadratic)

실행시간이 n^2에 비례하면 알고리즘은 이차라고 한다.

리스트에 있는 어떤 요소가 두 번 이상 나타나는 지 확인하는 알고리즘을 생각해보자. 각 요소를 다른 모든 요소와 비교하는 알고리즘이라면 n개의 요소가 있고 각각 n-1개의 다른 요소와 비교하면 총 비교 횟수는 n^2 - n이 되어 n이 커지면서 n^2에 비례하게 된다.



## 선택 정렬(selection sort)

```java
public class SelectionSort {
  // 1)
  // i와 j의 위치에 있는 값을 바꾼다.
  public static void swap(int[] array, int i, int j) {
    int temp = array[i];
    array[i] = array[j];
    array[j] = temp;
  }
  
  // 2)
  // start로부터 시작하는 최솟값의 위치를 찾고, (start 포함)
  // 배열의 마지막 위치로 간다.
  public static int indexLowest(int[] array, int start) {
    int lowIndex = start;
    for (int i = start; i < array.length; i++) {
      if (array[i] < array[lowIndex])
        lowIndex = i;
    }
    return lowIndex;
  }
  
  // 3)
  // 선택정렬을 사용해서 요소 정렬
  public static void main(String[] args) {
    for (int i = 0; i < array.length; i++) {
      int j = indexLowest(array, i);
      swapElements(array, i, j);
    }
  }
}
```

첫 번째 메서드 swapElements는 배열에 있는 두 요소의 값을 바꾼다. **요소를 읽고 쓰는 것은 상수시간 연산**이다. 요소의 크기와 위치를 알고 있다면 한 번의 곱셈과 덧셈으로 어떤 요소의 위치라도 알 수 있기 때문이다. swapElements 메서드의 모든 연산이 상수 시간이므로 전체 메서드는 상수시간이다. 

두 번째 메서드 indexLowest는 반복문을 돌 때마다 배열의 두 요소에 접근하고 한 번의 비교 연산을 한다. 이들은 모두 상수 시간 연산이기 때문에 어떤 것을 세든지 중요하지 않다.

- start 인자가 0이면 indexLowest 메서드는 전체 배열을 검색하고 전체 비교 횟수는 배열의 길이인 n이다.
- start 인자가 1이면 비교 횟수는 n-1이다.
- 일반적으로 비교 횟수는 n-start가 되어 indexLowest 메서드는 선형이다.

세 번째 메서드 selectionSort는 배열을 정렬한다. n번 반복 실행되고, 매번 indexLowest 메서드와 swapElements 메서드를 호출한다.  청므 실행되면 n번 비교 연산을 하고, 두번째는 n-1번 비교 연산을 한다. 총 비교 횟수는 n + (n - 1) + (n-2) + ... + 1 + 0이다. 이 수열의 합은 n(n+1) / 2이고 n^2에 비례한다. 즉 이 메서드는 이차이다. 다르게 생각하면 indexLowest 메서드를 호출할 때마다 연산 횟수는 n에 비례하는데, 이를 n번 호출하니 결국 총 연산 횟수는 n^2에 비례한다.



## 빅오 표기법

- 모든 상수시간 알고리즘은 O(1) 집합에 속한다.
- 모든 선형 알고리즘은 O(n)에 속한다.
- 모든 이차 알고리즘은 O(n^2)에 속한다.

이렇게 알고리즘을 분류하는 방식을 빅오 표기법(big O notation)이라고 한다. 이 표기법은 알고리즘이 어떻게 동작하는지에 관한 일반적인 법칙을 표현하는 간편한 방법을 제공한다. 

- 상수 시간 알고리즘에 이어 선형 시간 알고리즘 수행
  - f ∈ O(n)이고 g ∈ O(1)면 f + g ∈ O(n)
- 두 개의 선형 연산 수행: 합은 여전히 선행
  - f ∈ O(n)고 g ∈ O(n) 면 f + g ∈ O(n)
- 선형 연산을 k번 수행 (단 k는 n에 의존하지 않는 상수)
  - f ∈ O(n)이면 kf ∈ O(n)
- 선형 연산을 n번 수행
  - f ∈ O(n)이면 nf ∈ O(n^2)

n의 가장 큰 지수만 신경쓴다.

증가 차수(order of growth)는 같은 개념의 다른 이름이다. 증가 차수는 실행시간이 같은 빅오 범주에 해당하는 알고리즘 집합이다. 예를 들어, 모든 선형 알고리즘은 실행시간이 O(n)에 있으므로 같은 증가 차수에 속한다. 여기서 차수(order)는 집단의 의미로, 순서를 의미하는 것이 아니다.



## 실습

### 필드와 생성자

```java
public class MyArrayList<T> implements List<T> {
  int size;                    // keeps track of the number of elements
  private T[] array;           // stores the elements
  	@SuppressWarnings("unchecked")
	
  public MyArrayList() {
		// You can't instantiate an array of T[], but you can instantiate an
		// array of Object and then typecast it.
		array = (T[]) new Object[10];
		size = 0;
	}
}
```

size 변수는 MyArrayList 클래스의 요소 개수를 추적하고 array 변수는 실제로 그 요소들을 저장하는 배열을 의미한다. 생성자는 초기값이 null이고 10개의 요소를 갖는 배열을 생성하며. size 변수는 0으로 설정한다. 대부분 시간 동안 배열의 크기는 size 변수보다 크기 때문에 배열에는 사용하지 않는 슬롯이 있다. 

자바는 타입 파라미터로 배열을 초기화할 수 없다. `new T[10]`는 작동하지 않는다. 이러한 제약을 해결하기 위해서 Object의 배열로 초기화하고 형변환을 해야 한다.

### boolean add(T element)

```java
// 리스트에 요소를 추가하는 메서드
public boolean add(T element) {
  // TODO: FILL THIS IN!
  if (size >= array.length) {
    // 큰 배열을 만들고 요소들을 복사
    T[] bigger = (T[]) new Object[array.length * 2];
    System.arraycopy(array, 0, bigger, 0, array.length);
    array = bigger;
  }
  array[size] = element;
  size++;
  return true;
}
```

이 메서드는 일반적으로 상수시간이지만, 배열 크기를 변경한다면 선형 시간이 된다.

### T get(int index)

```java
public T get(int index) {
  if (index < 0 || index >= size)
    throw new IndexOutOfBoundsException();
  return array[index]
}
```

여기서 인덱스는 array.length보다 작은지가 아니라 size보다 작은지를 검사하므로 배열의 사용하지 않는 요소에는 접근할 수 없다.



여기부터는 내가 자바 공식 문서를 읽으면서 쓴 코드이다. 테스트는 모두 통과하였다.

### T set(int index, T element)

```java
public T set(int index, T element) {
  if (index < 0 || index >= size)
    throw new IndexOutOfBoundsException();
  T old = array[index];
  array[index] = element;
  return old;
}
```

배열의 index번째 요소를 old 변수가 참조하도록 하고 array[index]자리에는 파라미터로 준 element 값을 넣어준다. 마지막으로 old가 참조하는 원래 요소는 리턴하도록 만들었다.

### indexOf(Object target)

```java
public int indexOf(Object target) {
  if (target == null) {
    for (int i = 0; i < size; i++) {
      if (array[i] == null)
        return i;
    }
  } else {
    for (int i = 0; i < size; i++) {
    	if (target.equals(array[i]))
      	return i;
  	} 
  }
  return -1;
}
```

배열의 요소를 탐색하면서 target과 요소를 비교하고, 찾았을 경우 바로 그 인덱스 값을 리턴한다. 비교할 때 `==` 연산자가 아니라 equals 메서드로 비교한다. 각 클래스에 따라 어떤 것을 같은 값으로 비교할 것인지(인스턴스가 같으면 같다고 간주할 것인지, 내용만 같아도 같다고 간주할 것인지)를 정의한 것이 `equals()` 메서드이기 때문이다. ArrayList는 어떤 타입의 객체도 들어올 수 있다. 그 타입(클래스)에서 애초에 정의한 것을 존중하기 위해 `==`가 아니라 equals를 사용한다. 



### add(int index, T element)

```java
public void add(int index, T element) {
  if (index < 0 || index > size)
    throw new IndexOutOfBoundsException();
  add(element);
  for (int i = size - 1; i > index; i--) {
    array[i] = array[i-1];
  }
  array[index] = element;
}
```



### remove(int index)

인덱스를 인자로 받아 새로운 값을 저장한다. 필요하다면 다른 인자들을 시프트하여 공간을 만든다.

```java
@Override
public T remove(int index) {
  if (index < 0 || index >= size)
    throw new IndexOutOfBoundsException();
  T old = array[index];
  for (int i = index; i < size - 1; i++) {
    array[index] = array[index + 1];
  }
  array[size--] = null;
  return old;
}
```