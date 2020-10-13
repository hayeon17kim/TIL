# 04. 테스트 작성

- 리팩토링을 실시하기 위한 필수 전제조건은 견고한 테스트를 해야 한다는 것이다.
- 적절한 테스트를 작성하면 리팩토링 하지 않더라도 프로그래밍 속도가 월등히 빨라진다.

## 자가 테스트 코드의 가치

- 테스트 코드를 작성하지 않으면 디버깅하는 시간에 더 많은 시간을 뺏길 수 있다.
- 버그 수정은 금방 끝나지만 버그를 찾는 데 시간이 오래 걸리기 때문이다.



- 모든 테스트를 완전히 자동화하고 **각 클래스가 결과를 자체적으로 검사**하게 하자.  
- 테스트 코드 안에 예상 출력 결과를 넣고 비교하게 만들면 된다.
- 그렇게 하면 각 클래스에 넣은 테스트 메서드가 실행되어 문제가 없을 경우 OK를 출력한다.
- 예전 테스트에서 잡현던 버그를 추가하면 예전의 테스트를 실행하자마자 발견하게 된다.
- 테스트 스위트는 버그 찾는 시간을 획기적으로 줄여주는 강력한 버그 감지 도구다. 
- 테스트를 작성하기 가장 적합한 시점 중 하나는 프로그래밍을 시작할 대다.ㅣ;
  - 기능을 추가할 때는 우선 테스트부터 작성하자.
  - 테스트를 작성하면 그 기능을 추가하려고 **해야 할 작업이 무엇인지 자문**하게 된다.
  - 테스트를 작성하면 구현부가 아니라 **인터페이스에 집중**하게 된다.
    - 인터페이스에 집중하는 것은 **언제나 바람직하다!**
- 익스트림 프로그래머는 열정적인 테스터다. 
  - 테스트를 통해 소프트웨어를 가능한 빨리 개발할 수 있다.
- 테스트 방법
  - **테스트 메인**
    - 자바에서는 **관습**상 테스트 코드를 테스트 메인 메서드로 작성한다.
    - 모든 클래스에 그 클래스를 자가 테스트하는 메인 함수가 있어야 한다.
    - 단점: 수많은 테스트를 쉽게 실행할 수 없다.
  - **프레임워크** 안에서 테스트 작업을 수월하게 해주는 **별도의 테스트 클래스**를 몇 개 작성한다.



## JUnit 테스트 프레임워크

- 테스트가 든 모든 클래스 안에는 JUnit의 TestCase 클래스가 반드시 들어 있어야 한다.
- JUnit은 콤퍼지트 패턴을 사용하므로 개발자가 각종 테스트를 하나의 스위트로 묶어 실행할 수 있다.
- 이렇게 묶은 스위트에는 테스트 케이스나 다른 테스트 스위트를 넣을 수 있다.

![image](https://user-images.githubusercontent.com/50407047/95669186-8f3d9d80-0bb8-11eb-996a-9c095a9d0e3c.png)

- 테스트에 사용할 파일

  ```
  Bradman 99.94 52 80 10 6996 334 29
  Pollock 60.97 23 41 4 2256 274 7
  Headley 60.83 22 40 4 2256 270* 10
  Sutcliffe 60.73 54 84 9 4555 194 16
  ```

**파일을 읽는 코드**

```java
public class FileReaderTester extends TestCase {
  public FileReaderTester (String name) {
    super(name);
  }

  FileReader _input;

  protected void setUp() {
    try {
      _input = new FileReader("data.txt");
    } catch (FileNotFoundException e) {
      throw new RuntimeException ("테스트 파일을 열 수 없음!");
    }
  }

  protected void tearDown() {
    try {      
      _input.close();
    } catch (IOException e) {
      throw new RuntimeException ("테스트 파일을 닫는 중 오류 발생!");
    }
  }
}
```

- test fixture(텍스트 픽스처): 테스트의 샘플 역할을 하는 객체
- `setUp()`: 픽스처 객체 생성
- `tearDown()`: 픽스처 객체 제거
  - 개발자는 사용할 일이 거의 없고, 가비지 컬렉터가 다룰 수 있다.
- 두 세개 정도의 문자를 읽은 후 읽은 문자가 맞는지 검사

```java
public void testRead() throws IOException {
  char ch = '&';
  for (int i = 0; i < 4; i++)
    ch = (char) _input.read();
  assert('d' == ch);
}
```

- `assert()`: 자동 테스트 담당
  - 리턴값이 true이면 테스트 성공, false이면 에러

**테스트 스위트 작성**

```java
public static Test suite() {
  TestSuite suite = new TestSuite();
  // 생성자 메서드명을 문자열 인자로 넘긴다.
  // 테스트를 하면 이 하나의 메서드를 테스트하는 하나의 객체가 생성된다.
  // 이 테스트는 자바의 리플렉션 기능에 의해 객체에 연결된다. 
  suite.addTest(new FileReaderTester("testRead"));
  return suite;
}
```

- 생성자 메서드명을 문자열 인자로 넘긴다. 
- 테스트를 하면 그 하나의 메서드를 테스트하는 하나의 객체가 생성된다.
- 이 테스트는 자바의 리플렉션 기능에 의해 객체에 연결된다.

**main 메서드 안에서 TestRunner 클래스 textui 버전 호출**

```java
public static void main(String[] args) {
  junit.textui.TestRunner.run(suite());
}
```

- TestRunner 클래스는 결과를 GUI나 텍스트로 보게 해준다.
- 위 코드는 TestRunner 인스턴스를 생성한 후 그 인스턴스에 FileReaderTester 클래스를 테스트하라고 명령한다. 실행 결과는 다음과 같다.

**테스트 성공 결과**

```console
.
Time: 0.003 // 테스트에 걸린 시간

OK (1 test) // 모두 에러가 없으면 OK (테스트 개수)
```

- JUnit은 실행되는 각 테스트마다 걸린 시간과 모든 테스트 실행에 걸린 시간을 출력한다.
- 다수의 테스트 중 하나도 에러가 없으면 OK 메시지를 출력한다.
- 피드백이 간결해 수많은 테스트를 무더기로 실행하고 나중에 결과를 확인하기 좋다.
- **테스트를 자주 실행**하자. 적어도 하루에 한 번은 테스트를 실시하고, **컴파일할 때마다 테스트를 국소화**하자.

**테스트 실패 결과**

- assert 메서드를 assertEquals 메서드로 대체하면 더 개선된 에러 문구가 출력된다.
- 주로 쓰이는 assert 메서드는 두 값을 비교하여 서로 같은지 검사하는 방식이다. 따라서 JUnit은 assertEquals 메서드를 제공한다. 

```console
Time: 0.009
There was 1 failure:
1) testRead(chap04.FileReaderTester)junit.framework.AssertionFailedError: expected:<m> but was:<d>
```

- JUnit 프레임워크는 실패만잡아내는 게 아니라 에러도 잡아낸다. 실패와 에러는 다르다. 실패는 어설션 결과가 실패(failure)로 출력되고, 에러는 예기치 못한 예외(unexpected exceptions)가 출력된다. 
- 실패와 에러는 발생 원인도 다르고 디버깅 절차도 다르므로 구별하는 것이 좋다.

**예기치 못한 에러 발생**

```console
There was 1 error:
1) testRead(chap04.FileReaderTester)java.io.IOException: Stream closed
```

### 단위 테스트와 기능 테스트

#### 단위 테스트

- JUnit 프레임워크는 단위 테스트용이다. 
- 목적: 프로그래밍 생산성 향상이다.
- 단위  테스트는 매우 국소적이어서, 각 테스트 클래스는 하나의 패키지 안에서만 효력이 있다.
- 다른 패키지의 **인터페이스**를 테스트하지만, 그 외에 나머지 코드는 잘 돌아간다고 가정한다.



#### 기능 테스트

- 목적: 소프트웨어 전반이 제대로 돌아가는지 확인하는 것이다.
- 고객에게 품질 보증만 할 분 프로그래머의 생산성과는 무관하다.
- 따라서 기능 테스트 코드는 별도 버그 발견 전문 팀이 개발해야 한다.

- 대체로 시스템 전반을 최대한 블랙박스처럼 취급한다.
- GUI 기반 시스템에선 GUI를 통해 이루어진다. 파일이나 데이터베이스 업데이트 프로그램에선 데이터가 특정 입력에 대해 어떻게 변하는지를 관찰하기만 한다.
- 버그를 수정하기 위한 두 가지 조치
  - 제품 코드를 수정해서 버그를 없앤다.
  - 버그를 출력하는 단위 테스트도 추가해야 한다.
- 기능 테스트를 작성하기 위해서는 GUI 기반의 다른 테스트 도구를 사용해야 한다
  - 그러나 보통 개발자가 직접 자신의 프로그램에 특화한 전용 테스트 도구를 작성한다. 이렇게 하면 GUI 스크립트를 단독 실행하는 것보다 테스트 케이스 관리가 더 편하다. 
  - JUnit으로도 작성할 수 있지만 효율이 떨어진다.



## 테스트 추가

- 테스트는 위험을 위주로 작성해야 한다.
- 잘못될까봐 가장 걱정되는 부분들만 테스트해야 한다.
- 완벽한 테스트를 작성하려다 아예 테스트를 포기하느니, 차라리 불완전한 테스트를 작성해 실행하는 편이 낫다.

**테스트 메서드 추가**

```java
public void testReadAtEnd() throws IOException {
  int ch = -1234;
  for (int i = 0; i < 141; i++) // 파일에 있는 문자 개수는 141개 
    ch = _input.read();
  assertEquals("read at end", -1, _input.read());
}
```

**테스트 스위트에 테스트 케이스 추가**

```java
public static Test suite() {
  TestSuite suite = new TestSuite();
  suite.addTest(new FileReaderTester("testRead"));
  suite.addTest(new FileReadertester("testReadAtEnd")); // 추가
  return suite;
}
```

테스트 스위트를 실행하면 그 안의 두 테스트 케이스가 실행된다. 각 테스트 케이스는 **setUp(), 테스트 코드 안의 테스트 코드, tearDown()를 차례로 실행**한다.

그러나 테스트를 suite 메서드에 넣는 작업은 잊기 쉬운데, 한 가지 방법이 있다. **다음 생성자가 test로 시작하는 이름의 모든 메서드의 테스트 케이스가 든 테스트 스위트를 생성한다**. 이 방식을 사용하려면 main 메서드를 수정해야 한다.

```java
public static void main(String[] args) {
  // 이러면 suite() 메서드가 필요 없다!
  junit.textui.TestRunner.run(new TestSuite(FileReaderTester.class));
}
```

**경계 조건 찾기**

```java
public void testReadBoundaries() throws IOException {
  assertEquals("read first char", 'B', _input.read());
  int ch;
  for (int i = 1; i < 140; i++)
    ch = _input.read();
  assertEquals("read last char", '6', _input.read());
  assertEquals("read at end", -1, _input.read());
}
```

**특수 조건 찾기**: 경계 찾기에는 테스트를 실패할 가능성이 있는 특수 조건을 찾는 작업도 포함된다.

파일이 비어있을 경우를 특수조건을 사용한다.

```java
public void testEmptyRead() throws IOException {
  File empty = new File("empty.txt");
  FileOutputStream out = new FileOutputStream(empty);
  out.close();
  FileReader in = new FileReader(empty);
  assertEquals(-1, in.read());
}
```

이 테스트 전용 픽스처를 추가로 작성한다. 나중에 빈 파일이 필요해지면 그에 대한 코드를 setUp 메서드로 옮겨서 빈 파일 픽스처를 일반 픽스처 안으로 옮긴다.

```java
public void testEmptyRead() throws IOException {
  assertEquals(-1, _empty.read());
}

private FileReader newEmptyFile() throws IOException {
  File empty = new File("empty.txt");
  FileOutputStream out = new FileOutputStream(empty);
  out.close();
  return new FileReader(empty);
}
```

```java
protected void setUp() {
  try {
    _input = new FileReader("data.txt");
    _empty = newEmptyFile();
  } catch (IOException e) {
    throw new RuntimeException ("테스트 파일을 열 수 없음!");
  }
}
```

파일 끝보다 더 읽었을 때에도 -1이 반환될 것이니 이를 검사하는 코드도 추가하자.

```java
public void testReadBoundaries() throws IOException {
  assertEquals("read first char", 'B', _input.read());
  int ch;
  for (int i = 1; i < 140; i++)
    ch = _input.read();
  assertEquals("read last char", '6', _input.read());
  assertEquals("read at end", -1, _input.read());
  assertEquals("read past end", -1, _input.read()); // 추가
}
```

뭔가 에러가 있으리라 예상될 땐 그 예외가 정말로 발생하는지 꼭 테스트하자.

```java
public void testReadAfterClose() throws IOException {
  _input.close();
  try {
    _input.read();
    fail ("read past end에 예외가 발생하지 않음!");
  } catch (IOException io) {}
}
```

> 이렇게 테스트를 추가하면, 작업 과정에서 해당 클래스의 인터페이스를 깊이 있게 이해할 수 있다. 특히 에러 조건과 경계 조건에 관해 생각하는 일이 쉬워진다. 

**여러 클래스의 스위트를 연결하는 다른 테스트 클래스를 작성**할 수도 있다. 테스트 스위트에는 다른 테스트 스위트를 넣을 수 있으므로 이 작업은 간단하다.

 ```java
public class MasterTester extends TestCase {
  public static void main(String[] args) {
    junit.textui.TestRunner.run(suite());
  }
  
  public static Test suite() {
    TestSuite result = new TestSuite();
    result.addTest(new TestSuite(FileReaderTester.class));
    result.addTest(new TestSuite(FileWriterTester.class));
    
    return result;
  }
}
 ```

- 테스트는 위험이 있는 곳에만 집중시켜야 한다.
- 코드에서 복잡해지는 부분이 어딘지 파악하고, 함수를 살펴보면서 에러 가능성이 높은 부분들을 생각해보자. 테스트로 모든 버그를 찾을 수는 없을지라도 리팩토링을 통해 프로그램을 더 제대로 이해하게 되어 좀 더 많은 버그를 발견하게 된다.
- 리팩토링을 시작할 때도 테스트 스위트를 작성하고, 리팩토링 중에도 테스트 스위트를 작성한다.
- 객체의 한 가지 단점은 상속과 재정의로 인해 테스트할 조합이 너무 많아져서 테스트가 어려워질 수 잇따.
- 기능이 연동되는 추상 클래스가 3개 있고, 각각의 하위 클래스가 3개씩이라면?
  - 조합될 후보 클래스(9개)만 테스트를 실시한다.
  - 이러헤 하면 전체 조합 경우의 수에서 위험 조합만이 추려진다.
  - 조합 후보들이 서로 꽤 독립적인 편이라면 조합마다 테스트를 실시할 일도 없다.

- 테스트 코드와 완제품 코드의 차이점
  - 테스트 코드는 복사해서 편집해도 괜찮다.
  - 정기적인 작업에 대한 테스트 => 오래된 작업에 대한 테스트 => 나중에 필요한 작업에 대한 테스트 작성