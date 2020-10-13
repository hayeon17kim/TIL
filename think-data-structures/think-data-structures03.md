# ArrayList 클래스

분할상환분석(amortized analysis) 알고리즘 분류법

## 3.1 MyArrayList 메서드 분류하기

**get(int index)**

```java
public E get(int index) {
  if (index < 0 || index >= size)
    throw new IndexOutOfBoundsException();
  return array[index];
}
```

get 메서드에 있는 모든 것이 상수시간이므로 get 메서드는 상수 시간이다.



**set(int index, E element)**

```java
public E set(int index, E element) {
  // 인덱스 유효범위를 검사하는 get 메서드를 호출하기 때문에 
  // 배열의 범위를 검사할 필요가 없다.
  E old = get(index);
  array[index] = element;
  return old;
}
```

set 메서드에 있는 모든 것이 상수 시간이므로 set 메서드는 상수 시간이다.

**equals(Object target, Object element)**

```java
public boolean equals(Object target, Object element) {
  if (target == null)
    return element == null;
  else
    return target.equals(element);
}
```

indexOf에서 호출하는 **equals 메서드의 실행시간은 target 또는 element의 크기에 의존한**다. 그러나 **배열의 크기에는 의존하지 않으므로** indexOf의 실행시간을 분석하기 위해 equals 메서드를 **상수 시간**으로 생각한다. 

**indexOf(Object target)**

```java
public int indexOf(Object target) {
  for (int i = 0; i < size; i++) {
    if (equals(target, array[i]))
      return i;
  }
  return -1;
}
```

반복문 안에 있는 모든 것은 상수 시간이므로 반복문이 몇번 실행되는지 생각해야 한다. **운이 좋다면 대상 객체를 바로 찾아 리턴**하고, **운이 없다면 모든 요소를 테스트**해야 한다. 따라서 **평균적으로 요소 개수의 절반을 테스트**하기를 기대한다. 따라서 indexOf 메서드는 **선형**이다.

**remove(int index)**

```java
public E remove(int index) {
  E element = get(index);
  for (int i = index; i < size - 1; i++)
    array[i] = array[i+1];
  size--;
  return element;
}
```

리스트의 **마지막 요소를 제거**하면 반복문은 실행되지 않고 **상수 시간**이 된다. **첫번째 요소를 제거**하면 **나머지 모든 요소에 접근하여 선형**이 된다. 따라서 remove 메서드는 **선형으로 간주**한다.



## add 메서드 분류

**add(E element)**

```java
public boolean add(E element) {
  if (size >= array.length) {
    E[] bigger = (E[]) new Object[array.length * 2];
    System.arraycopy(array, 0, bigger, 0, array.length);
    array = bigger;
  }
  array[size] = element;
  size++;
  return true;
}
```

배열에 **미사용 공간**이 있다면 add 메서드는 **상수 시간**이다. 그러나 **배열의 크기를 변경**하면 System.arraycopy 메서드 호출 시 **실행시간이 배열의 크기에 비례**하기 때문에 add 메서드는 **선형**이다.

두 요소만큼 공간이 있는 배열에서 시작하면

- 4번의 add 메서드 호출 후에는 요소 4개를 저장하고 2번 복사한다.
- 8번의 add 메서드 호출 후에는 요소 8개를 저장하고 6번 복사한다.
- 16번의 add 메서드 호출 후에는 요소 16개를 저장하고 14번 복사한다.

즉 n번 추가하면 n개를 저장하고 n-2개를 복사해야 한다. 따라서 총 연산 횟수는 n + n - 2, 즉 2n-2이다. add 메서드의 평균 연산 횟수를 구하려면 합을 n으로 나눠야 한다. 따라서 결과는 (2n-2)/n이다. n이 커지면 두 번째 항인 2/n은 작아진다. n의 가장 큰 지수에만 관심 있다는 원칙을 적용하면 add 메서드는 상수시간으로 간주한다. 이처럼 때때로 선형인 알고리즘도 평균적으로 상수 시간이 될 수 있다. 이는 배열의 크기를 조정할 때마다 배열의 길이가 2배로 늘어 각 요소를 복사하는 횟수를 제한하기 때문이다.

이처럼 **일련의 호출에서 평균 시간을 계산**하는 알고리즘 분류 방법을 **[분할 상환 분석(amortized analysis)](https://en.wikipedia.org/wiki/Amortized_analysis)**라고 한다. 일련의 호출을 하는 동안 배열을 복사하는 추가 비용이 **분산되거나 분할 상환**되었다는 것이 중요하다.

**add(int index, E element)**

```java
public void add (int index, E element) {
  if (index < 0 || index > size)
    throw new IndexOutOfBoundsException();
  add(element);
  for (int i = size -1; i > index; i--) 
    array[i] = array[i-1];
  array[index] = element;
}
```

add(E) 메서드를 호출한 후에 배열 일부에 반복문을 실행하고 요소를 시프트한다. 이 반복문은 리스트의 끝에 요소를 추가하는 특별한 경우만 제외하면 선형이다. 따라서 add(int, E)는 **선형**이다.



## 문제 크기

**removeAll(Collection<?> collection)**

```java
public boolean removeAll(Collection<?> collection) {
  boolean flag = false;
  for (Object obj : collection)
    flag != remove(obj);
  return flag;
}
```

- 반복문을 돌 때마다 선형인 remove 메서드를 호출하기 때문에 이차로 생각하기 쉽다는 것에 주의하자.
- collection의 요소가 m개고 제거할 리스트에 요소가 n개 있다면 메서드는 **O(nm)**이다.
- 하지만 **colleciton의 크기가 n에 비례한다면 이차**이다.

> 만약 반복 횟수가 모든 반복문에서 n에 비례한다면 반복문만 세면 끝이지만, 위의 예제에서 알 수 있듯 반복 횟수가 항상 n에 비례하지 않는다면 좀 더 고민해봐야 한다.



## 연결 자료구조

자료구조가 연결되었다는 것은 **노드(node)라는 객체들이 다른 노드에 대한 참조를 포함한 형태로 저장되었다**는 것을 의미한다. 연결 리스트에서 **각 노드는 리스트의 다음 요소에 대한 참조를 포함**한다. 연결 구조의 다른 예로는 **트리**와 **그래프**가 있다. 

```java
public class ListNode {
  public Object data; // 어떤 Object에 대한 참조
  public ListNode next; // 리스트에서 다음 노드에 대한 참조
  
  public ListNode() {
    this.data = null;
    this.next = null;
  }
  
  public ListNode(Object data) {
    this.data = data;
    this.next = null;
  }
  
  public ListNode(Obejct data, Listnode next) {
    this.data = data;
    this.next = next;
  }
  
  public String toString() {
    return "ListNode(" + data.toString() + ")"; 
  }
}
```

리스트의 마지막 노드에서 관례상 next  변수는 null이다.

ListNode 객체는 단일 요소를 가진 리스트로 생각할 수 있지만, 좀 더 일반적으로 리스트는 다수의 노드를 포함할 수 있다. 

```java
ListNode node1 = new ListNode(1);
ListNode node2 = new ListNode(2);
ListNode node3 = new ListNode(3);
```

다음과 같이 연결한다.

```java
node1.next = node2;
node2.next = node3;
node3.next = null;
```

노드와 링크를 동시에 생성할 수도 있다. 

```java
// 리스트 시작에 새로운 노드를 추가하는 코드
ListNode node0 = new ListNode(0, node1);
```



## 실습 5

```java
public class MyLinkedList<E> implements List<E> {
  private int size;  // 요소의 개수를 추적한다.
  private Node head; // 첫번째 노드에 대한 참조이다.
  
  public MyLinkedList() {
    head = null; // 빈 리스트임을 알려준다.
    size = 0;
  }
}
```

**요소 개수**를 꼭 저장할 필요는 없다. 일반적으로 **중복 정보를 유지하는 것은 위험**한데, **정보를 올바르게 갱신하지 않으면 오류가 생길 수 있기 때문**이다. 또한 약간의 추가 공간을 차지하게 된다. 그러나 size 변수를 명시적으로 저장하면 **상수 시간으로 size 메서드를 구현할 수 있다**. 그렇지 않으면 리스트를 **순회하여 요소 개수를 세는 선형 시간이 필요**하다. size 변수를 명시적으로 저장하므로 요소를 더하거나 제거할 때마다 갱신해야 해서 메서드가 느려지지만, 증가 차수를 변경하지 않으므로 할만한 가치가 있다.

```java
//MyLinkedList에 중첩된 Node 클래스
private class Node {
  public E data;
  public Node next;
  
  public Node(E data, Node next) {
    this.data = data;
    this.next = next;
  }
}
```

Node. 클래스는 연결 자료구조의 ListNode 클래스와 비슷하다.

```java
public boolean add (E element) {
  if (head == null)
    head = new Node(element);
  else {
    Node node = head;
    // 마지막 노드까지 반복한다.
    for (; node.next != null; node = node.next)
      node.next = new Node(element);
  }
  size++;
}
```

- 많은 메서드에서 리스트의 첫 번째 요소를 특별한 경우로 처리해야 한다. 이 예제에서는 리스트에 첫 번째 요소를 추가하면 head 변수를 변경해야 한다. 그렇지 않으면 리스트를 순회하여 끝을 찾아 새로운 노드를 추가해야 한다.
- add 메서드는 for문으로 리스트에 있는 노드를 순회하는 방법을 보여준다.

**indexOf(Object target)**

```java
public int indexOf(Object target) {
  Node node = head;
  for (int i = 0; i < size; i++) {
    if (equals(target, node.data))
      return i;
    node = node.next;
  }
  return -1;
}
```

**add(int index, E element)**

```java
public void add(int index, E element) {
  if (index == 0)
    head = new Node(element, head);
  else {
    Node node = getNode(index - 1);
    node.next = new Node(element, node.next);
  }
}
```

**remove(int index)**

```java
public E remove(int index) {
  E old = get(index);
  if (index == 0)
    head = head.next;
  else {
    Node node = getNode(index - 1);
    node.next = node.next.next;
  }
  size--;
  return element;
}
```



## 가비지 컬렉션

ArrayList 클래스는 **필요하면 배열이 늘어나지만 결코 줄어들지는 않는다**. **배열은 가비지 컬렉션을 하지 않고 그 요소도 리스트 자체가 파괴될 때까지 가비지 컬렉션 대상이 아니다**.

반면 연결리스트는 **요소를 제거하면 리스트 크기가 줄어들고**, **사용하지 않는 노드는 즉시 가비지 컬렉션이 될 수 있다**는 장점이 있다.

```java
public void clear() {
  head = null;
  size = 0;
}
```

head 변수를 null로 만들면 첫 번째 Node에 대한 참조를 제거한다. 해당 Node에 대한 다른 참조가 없다면(당연히 없어야 하지만) 가비지 컬렉션을 한다. 그러면 두 번째 Node에 대한 참조가 제거되고, 이에 대한 가비지 컬렉션을 한다. 이 절차는 모든 노드를 가비지 컬렉션 할 때까지 계속된다.

clear 메서드 자체는 두 개의 상수 시간 연산을 포함하므로 상수시가능로 보인다. 그러나 이를 호출할 때는 **요소의 개수에 비례하여 가비지 컬렉터가 동작**하기에 **선형으로 간주**되어야 한다. 이것이 **성능 버그(performance bug**라고 불리는 예이다. 기능은 기대한 대로지만 **증가 차수는 기대한 만큼 나오지 않기 때문**이다. **자바와 같은 언어는 가비지 컬렉션이 뒷단에서 많은 일을 하기 때문에 이런 종류의 버그를 찾기 어렵다.**