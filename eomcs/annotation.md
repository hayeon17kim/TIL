# 애노테이션

## 애노테이션이란?

**클래스 파일에 남길 수 있는 주석**이다. **형식을 갖춘** 주석을 남길 수 있다. 

- 소스 코드에서 특정 값을 쉽게 추출할 수 있다.

  소스 코드에서 이 주석을 읽어 다른 소스 파일을 생성할 때 사용한다.

- `.class` 파일에 주석을 남길 수 있다.

  실행 시에 추출하여 사용할 수 있다.

```java
public @interface MyAnnotation {
  String value();
}
```

- 주석의 프로퍼티를 정의할 수 있다.
  - 인터페이스에서 메서드를 정의하는 것과 유사하다.
  - 메서드 이름은 프로퍼티(변수)명처럼 작성한다.
- 즉 일반적인 메서드는 보통 동사로 이름을 시작하지만, 애노테이션은 명사(명사구)로 이름을 짓는다.

```java
@MyAnnotation(value = "값") // 클래스 정의 앞에 선언할 수 있다.
public class MyClass {
  @MyAnnotation(value = "값") // 변수 앞에 선언할 수 있다.
  int i;
  
  @MyAnnotation(value = "값") // 메서드 정의 앞에 선언할 수 있다.
  public void m(// 파라미터 앞에 선언할 수 있다.
    	@MyAnnotation(value = "값") String p) {
   
    @MyAnnotation(value = "값")
    int local;
    
    // 일반 문장 앞에 선언할 수 없다!
    // @MyAnnotation(value = "값")
    // if (true)
    // 	System.out.println("ok"); => 컴파일 오류!
  }
}
```



## 애노테이션 유지 정책

- `SOURCE`: 소스 파일에만 남긴다. 컴파일 후 제거된다.

  ```java
  @Retention(RetentionPolicy.SOURCE)
  public @interface MyAnnotation2 {
      String value();
  }
  ```

- `CLASS`: `.class` 파일에 남긴다. 그러나 실행 시에 추출할 순 없다. (기본)

  ```java
  // 애노테이션의 유지 정책을 지정하지 않으면 기본이 CLASS이다.
  @Retention(RetentionPolicy.CLASS)
  public @interface MyAnnotation {
      String value();
  }
  ```

- `RUNTIME`: `.class` 파일에 남긴다. 실행 시에 추출할 수 있다.

  ```java
  @Retention(RetentionPolicy.RUNTIME)
  public @interface MyAnnotation3 {
      String value();
  }
  ```

애노테이션 유지 정책을 지정하지 않으면 기본이 CLASS이다.

```java
@MyAnnotation(value="값") // 유지정책 => CLASS 
@MyAnnotation2(value="값") // 유지정책 => SOURCE 
@MyAnnotation3(value="값") // 유지정책 => RUNTIME 
public class MyClass {
}
```

애노테이션을 확인해볼 수도 있다.

```java
public class Exam01 {

  public static void main(String[] args) {
    // 클래스 정보 객체로부터 애노테이션 정보 추출
    Class<?> clazz = MyClass.class;

    // => 유지정책 : CLASS
    MyAnnotation obj = clazz.getAnnotation(MyAnnotation.class);
    if (obj == null) {
      System.out.println("MyAnnotation을 추출할 수 없습니다!");
    }

    // => 유지정책 : SOURCE
    MyAnnotation2 obj2 = clazz.getAnnotation(MyAnnotation2.class);
    if (obj2 == null) {
      System.out.println("MyAnnotation2를 추출할 수 없습니다!");
    }

    // => 유지정책 : RUNTIME
    MyAnnotation3 obj3 = clazz.getAnnotation(MyAnnotation3.class);
    if (obj3 == null) {
      System.out.println("MyAnnotation3를 추출할 수 없습니다!");
    } else {
      // 값을 꺼낼 때는 메서드 호출하듯이 꺼내면 된다.
      System.out.println("MyAnnotation3.value=" + obj3.value());
    }
  }
}
```

## 애노테이션 프로퍼티

**default 값을 지정하지 않으면 필수 프로퍼티**이다. 즉 애노테이션을 사용할 때 반드시 값을 지정해야 한다.

```java
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation {
    String value();
}
```

default 값이 있으면, 애노페이션 값을 사용할 때 값을 지정하지 않아도 된다.

```java
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation2 {
    String value() default "홍길동"; 
}
```

필수 프로퍼티 값을 지정하지 않으면 컴파일 오류가 발생한다. 선택 프로퍼티 값을 지정하지 않으면 default 값이 사용된다.

```java
// @MyAnnotation // 필수 프로퍼티 값을 지정하지 않으면 컴파일 오류!
// @MyAnnotation(value="값") // OK!
@MyAnnotation2 // 선택 프로퍼티 값을 지정하지 않으면 default 값이 사용된다.
public class MyClass {
}
```

### value 프로퍼티

```java
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation {
    String value();
}
```

value 프로퍼티는 **이름을 생략할 수 있다.**

```java
// @MyAnnotation(value="홍길동") // OK!
@MyAnnotation("홍길동") // OK! value 프로퍼티는 이름 생략 가능!
public class MyClass {
}
```

### 일반 프로퍼티

```java
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation2 {
    String tel(); 
}
```

value 속성이 아닌 경우 생략할 수 없다.

```java
@MyAnnotation2(tel = "222-2222") // OK!
// @MyAnnotation2("222-2222") // value 속성이 아닌 경우 생략 불가!
public class MyClass2 {
}
```

### 여러 프로퍼티

```java
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation3 {
  String value();

  String tel();
}
```

- 프로퍼티를 설정할 때 순서는 상관 없다.
- `value` 외 다른 프로퍼티 값도 지정할 경우, value 이름을 생략할 수 없다. value 값만 지정할 때 생략할 수 있다.

```java
@MyAnnotation3(value = "홍길동", tel = "222-2222") // OK!
// @MyAnnotation3(tel = "222-2222", value = "홍길동") // OK!
// @MyAnnotation3("홍길동",tel="222-2222")
public class MyClass3 {
}
```

## 애노테이션 프로퍼티 타입

```java
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation {
    String v1() default "가나다";
    int v2() default 100;
    float v3() default 3.14f;
}
```

### 배열

배열 프로퍼티의 기본 값을 지정할 때 중괄호를 사용한다.

```java
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation2 {
    String[] v1() default {"가나다","라마바"};
    int[] v2() default {100,200};
    float[] v3() default {3.14f,5.14f};
}
```

배열 값을 지정할 때도 중괄호를 사용한다.

```java
@MyAnnotation3(
    v1 = {"홍길동", "임꺽정", "유관순"}, //
    v2 = {1000, 2000, 3000, 4000, 5000}, //
    v3 = {1.12f, 2.23f, 3, 34f})
public class MyClass4 {
}
```

다음과 같이 배열 값을 추출할 수 있다.

```java
public class Exam04 {

  public static void main(String[] args) {
    Class<?> clazz = MyClass4.class;
    MyAnnotation3 obj = clazz.getAnnotation(MyAnnotation3.class);

    System.out.println(obj.v1()[2]); // "유관순"
    System.out.println(obj.v2()[3]); // 4000
    System.out.println(obj.v3()[2]); // 3

  }
}
```

**배열 값이 한 개일 경우 중괄호를 생략할 수 있다.**

```java
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation3 {
    String[] v1() default "가나다";
    int[] v2() default 100;
    float[] v3() default 3.14f;
}
```

```java
@MyAnnotation3(
        v1="임꺽정",
        v2=1111,
        v3=1.11f)
public class MyClass5 {
}
```

다음과 같이 배열 프로퍼티 값을 추출할 수 있다.

```java
public class Exam05 {

  public static void main(String[] args) {
    Class<?> clazz = MyClass5.class;
    MyAnnotation3 obj = clazz.getAnnotation(MyAnnotation3.class);

    System.out.println(obj.v1()[0]); // "임꺽정"
    System.out.println(obj.v2()[0]); // 1111
    System.out.println(obj.v3()[0]); // 1.11f

  }
}
```



## 애노테이션 적용 범위

`@Target`을 사용하여 **애노테이션을 붙일 수 있는 범위를 지정할 수 있다**.

```java
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation {
  String v1() default "가나다";
}
```

`MyAnnotation`은 메서드와 타입(인터페이스와 클래스)에만 붙일 수 있다. 

```java
@MyAnnotation // OK!
public class MyClass {

  // @MyAnnotation
  int i; // 컴파일 오류!

  @MyAnnotation
  public void m() {} // OK!
}
```

