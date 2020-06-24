### 1. Android Programming Outline

##### 주요 기능

- 코드를 재사용하여 효율적이고 빠른 애플리케이션 개발 가능
  - 애플리케이션 프레임워크를 통해서 제공되는 API 사용
- 모바일 기기에 최적화된 달빅 또는 아트런타임 제공
- 2D 그래픽 및 삼차원 그래픽을 최적화하여 표현
- 모바일용 데이터베이스인 SQLite 제공
- 오디오, 비디오 및 이미지 형식 지원
- 모바일 기기에 내장된 하드웨어 지원
  - ex) 블루투스, 카메라, 나침반, WiFi 등
- 이클립스 IDE 또는 Android Studio를 통해 개발 환경 제공

#### 특징

![image](https://user-images.githubusercontent.com/50407047/85373569-6c866b00-b56e-11ea-9d1f-3be2e5dc576e.png){align="center"}

#### 안드로이드 운영체제 및 애플리케이션

안드로이드의 구조

- 응용 프로그램 (Applications)
  - 안드로이드 스마트폰에서 사용할 수 있는 일반적인 응용 프로그램
  - => 사용자 입장에서 가장 많이 사용 (웹 브라우저, 달력, 구글맵, 연락처, 게임 등)
- 응용 프로그램 프레임워크 (Application Framework)
  - 안드로이드 API가 존재하는 곳
  - 안드로이드 폰 하드웨어에 접근할 때는 Java에 직접 접근하는 것이 아니라 API를 통해서 가능
- 안드로이드 런타임 (Android Runtime)
  - Java 코어 라이브러리
  - 달빅 가상머신 (Dalvik Virtual Machine)
  - 아트 런타임 (ART Runtime)
- 라이브러리 (Libraries)
  - 안드로이드에서 사용되는 여러 시스템 라이브러리
  - => 시스템 접근 때문에 Java가 아닌 C로 작성
- 리눅스 커널 (Linux Kernel)
  - : 하드웨어의 운영과 관련된 저수준의 관리 기능이 들어 있음
  - 메모리 관리
  - 디바이스 드라이버
  - 보안

### 2. Android Development Environment

![image](https://user-images.githubusercontent.com/50407047/85375025-a48ead80-b570-11ea-864f-da29ddad8d4b.png){: .center}

### 4-1. Java 개요 및 변수

#### Java의 개요

##### 역사

- 1991: 선마이크로시스템스(오라클에 인수되었음)에 제임스 고슬링이 **C언어**를 모델로 연구를 시작함
- 1995: **JDK(Java Development Kit)** 1.0을 발표함
- 1997: JDK1.1이 발표되면서 완전한 프로그래밍 언어의 모습을 갖춤

##### 특징

- 간결한 문구
- 명료한 객체지향 언어
- 이식성이 높고, 기계에 중립적인 성격
- 분산 처리 지원
- 멀티스레드(Multi-thread) 언어

##### Java 프로그램 작성법

1. 메모장에서 Java 코드를 작성한 후에 `.java`로 저장

2. 컴파일하면 `.class` 파일이 생성

3. 컴파일된 `.class` 파일을 실행

   => 이클립스 환경에서 Java 개발

[exercise] 변수 선언

##### Java 기본 문법

###### 1. 데이터형

![image](https://user-images.githubusercontent.com/50407047/85376092-3814ae00-b572-11ea-8944-cbed2db49c3b.png){: .center}

출처: geeksforgeeks.org

###### 2. 조건문

1. `if`

- 조건이 True, False인지에 따라서 어떤 작업을 할 것인지를 결정

```
if(조건식){
  //조건식이 true일 때 이 부분 실행
}
```

```
if(조건식){
  //조건식이 true일 때 이 부분 실행
} else {
  //조건식이 false일 때 이 부분 실행
}
```

2. `switch() ~ case`

- 여러 가지 경우에 따라 어떤 작업을 할 것인지를 결정 (case A, case B, case C ...)

```
switch(값) {
  case 값1:
    //값1이면 이 부분 실행
    break;
  case 값2:
    //값2이면 이 부분 실행
    break;

  default:
    //아무것도 해당하지 않으면 이 부분 실행
    break;
}
```

```
public class exam3 {

  public static void main(String[] args) {

    int count = 85;
    if (count >= 90) {
      System.out.println("if문: 합격 (장학생)");
    } else if (count >= 60) {
      System.out.prinln("if문: 합격");
    } else {
      System.out.println("if문: 불합격")
    }

    int jumsu = (count / 10) * 10;
    switch (jumsu) {
      case 100:
      case 90:
        System.out.println("switch문: 합격(장학생)");
        break;
      case 80:
      case 70:
      case 60:
        System.out.println("switch문: 합격");
        break;
      default:
        System.out.println("switch문: 불합격");
    }
  }
}
```

###### 3. 배열

여러 데이터를 한 변수에 저장하는 데 사용
![image](https://user-images.githubusercontent.com/50407047/85401022-5390b080-b594-11ea-81f0-a4e191347cf2.png){: .center}

```
int one = new int[4];
one[0] = 10;
one[3] = 20;
```

[참고] 배열을 선언하면서 바로 값을 대입할 수도 있음

```
int three[] = {1,2,3};
```

이차원 배열
![image](https://user-images.githubusercontent.com/50407047/85401302-c306a000-b594-11ea-945f-777b7d1882f9.png){: .center}

```
int two = new int[3][4]
two[0][0] = 100;
two[2][3] = 200;
```

###### 4. 반복문

1. `for`

```
for(초기식; 조건식; 증감식) {
  //이 부분을 반복 실행
}
```

```
for(변수형 변수: 배열명) {
  //이 부분에서 변수를 사용
}
```

배열의 값을 차례대로 가져와서 str에 넣어라

2. `while`

```
while(조건식){
  //조건식이 true인 동안 이 부분을 수행
}
```

```java
public class exam3 {
  public static void main(String[] args) {
    int one[] = new int[3];
    for (int i = 0; i < one.length; i++){
      one[i] = 10*i;
    }

    String two[] = {"하나", "둘", "셋"};
    for (String str : two){
      System.out.println(str);
    }

    while (j < one.length){
      System.out.println(one[j]);
      j++;
    }
  }
}

/*
0
10
20
하나둘셋
0
1
2
*/
```

###### 전역변수와 지역변수

```java
public class exam3 {
  static int var = 100;
  public static void main(String[] args){
    int var = 0;
    System.out.println(var);

    int sum = addFunction(10, 20);
    System.out.println(sum);
  }

  static int addFunction(int num1, int num2){
    int hap;
    hap = num1 + num2 + var;
    return hap;
  }
}
```

###### 예외처리

```java
public class exam3 {
  static int var = 100;
  public static void main(String[] args){
    int num1 = 100, num2 = 0;
    try {
      System.out.println(num1 / num2);
    } catch (java.lang.ArithmeticException e){
      System.out.println("계산에 문제가 있습니다");
    }
  }
}
```

### 5. Java의 기본 문법2

#### 5-1. 연산자

![image](https://user-images.githubusercontent.com/50407047/85405278-209deb00-b59b-11ea-86f1-573d7ae3ca2d.png)

#### 5-2. 클래스와 인스턴스

클래스(class)는 변수(필드)와 메소드로 구성
![image](https://user-images.githubusercontent.com/50407047/85405378-4cb96c00-b59b-11ea-8f60-00ab353f596f.png){: .center}

##### 메소드 오버로딩(Overloading) / 정적필드, 정적 메소드, 상수 필드

```java
public class Car {
  String color;
  int speed;
  static int carCount = 0;
  final static int MAXSPEED = 200;
  final static int MINSPEED = 0;

  static int currentCarCount() {
    return carCount;
  }

  Car(String color, int speed) {
    this.color = color;
    this.speed = speed;
    carCount++;
  }

  Car(int speed){
    this.speed = speed;
  }

  Car(){
  }
}
```

##### 생성자코드 + 정적 구성 요소

```java
public class exam3 {
  public static void main(string[] args) {
    Car myCar1 = new Car("빨강", 0);
    Car myCar2 = new Car("파랑", 0);
    Car myCar3 = new Car("초록", 0);

    myCar1.upSpeed(50);
    System.out.println("자동차1의 색상은 " + myCar1.getColor() + "이며, 속도는 " + myCar1.getSpeed() + " 입니다." );

    myCar2.downSpeed(20);
    System.out.println("자동차2의 색상은 " + myCar2.getColor() + "이며, 속도는 " + myCar2.getSpeed() + " 입니다.");

    myCar3.upSpeed(300);
    System.out.println("자동차3의 색상은 " + myCar3.getColor() + "이며, 속도는 " + myCar3.getSpeed() + " 입니다.");
  }
}
```

##### 클래스의 상속과 메소드 오버라이딩

1. 클래스의 상속(Inheritance)
   기존 클래스를 그대로 물려받으면서 필요한 필드나 메소드를 추가로 정의

### 8-1. CompoundButton

- public abstract class CompundButton extends `Button` implements Checkable
- subclass of `Button` class
- superclass of `CheckBox`, `RadioButton`, `Switch`, and `ToggleButton`

- Hierarchy

```
  java.lang.Object
    android.view.View
        android.widget.TextView
            android.widget.Button
                android.widget.CompoundButton
                    android.widget.CheckBox
                    android.widget.RadioButton
                    android.widget.Switch
                    android.widget.ToggleButton
```

#### 1. CheckBox

- A checkbox is a specific type of two-states button that can be either checked or unchecked

```
// 1. 체크박스 변수 선언
CheckBox mycheck

// 2. 변수에 체크박스 위젯 대입
mycheck = (CheckBox) findViewById (R.id.android);

// 3. 체크박스가 변경될 때 동작하는 클래스 정의
mycheck.setOnCheckedChangeListener(new CompoundButton.OncheckedChangeListener(){
	public void onCheckedChanged(CompoundButton buttonview, boolean isChecked){
		// 이 부분에 동작할 내용 코딩
	}
})
```

#### Switch / ToggleButton

\*`Switch`: a two-state toggle switch widget that can select between two options

- `ToggleButton`: Displays checked/unchecked states as a button with a "light"indicator and by default accompanied with the text "ON" or "OFF"
- The uses of`Switch` and `Toggle` are almost same except the shape.

#### RadioButton / RadioGroup

- `RadioButton` - contrary to a `CheckBox`, a radio button cannot be unchecked by the user once checked - use it when you have to choose one option among several options
- `RadioGroup` - Radio buttons are normally used together in a RadioGroup. When several radio buttons live inside a radio group, checking one radio button uncheckes all the others. - Listing multiple radio will select duplicates for each click so radio buttons should be used with `RadioGroup`

### 8-2. ImageView

#### Image View

- displays image resources, for example `Bitmap` or `Drawable` resources. \* `[res]-[drawable]`
- ImageView is also commonly used to apply tint to an image and handle image scaling
- Hierarchy

```
  java.lang.Object
    android.view.View
        android:widget.ImageView
            android.widget.ImageButton
```

- XML attributes of `ImageView` and `ImageButton`

* `src`: path of iamge
* `maxHeight`/`maxWidth`: Specify the size
* scaleType: Controls how the image should be resized or moved to match the size of this ImageView

- 실습4: 이미지 뷰와 이미지 버튼의 XML 속성

### 8-3. [exercise] Pet Pictures App

#### XML

[exercise - xml]("/exercise")

1. 프로젝트의 [res] - [drawable]에 강아지, 고양이, 토끼 그림 파일을 미리 복사
2. TextView, CheckBox, TextView, RadioGroup, RadioButton 각각 3개, Button, ImageView의 차례로 만듦 (실습5)

- 레이아웃에 Padding을 적절히 지정
- 맨 위의 TextView와 Checkbox 제외
- 나머지 위젯은 Visibility 속성을 Invisible로 지정
- 위젯의 Id는 위에서부터 Text1, ChkAgree, Text2, Rgroup1, RdoDog, RdoCat, RdoRabbit, BtnOk, ImgPet

#### Java

[exercise - java]

1.  activity_main.xml의 9개 위젯에 대응할 위젯 변수 9개
2.  각 위젯을 변수에 대입
3.  [시작함] 체크박스를 체크/언체크할 때 동작하는 리스너를 onCreate() 내부에 정의 (실습8)
4.  [선택 완료]를 클릭하면 동작하는 리스너를 onCreat() 메소드 내부에 정의(실습9)

### 8-4. Layout

- A layout defines the structure for a user interface in your app
- All elements in the layout are built using using hiearchy of `View` and `ViewGroup`
- A `View` usually draws something the user can see and interact with.
- Whearea a `ViewGroup` is an invisible container that defines the layout structure for `View` and other `ViewGroup` objects
- ![Illustration of a view hierarchym which defines UI layout](https://developer.android.com/images/viewgroup_2x.png)
- `ViewGroup` 클래스로부터 상속받으며, 내부에 무엇을 담는 용도로 사용
- `LinearLayout` is most used among layouts
- Hierarchy

```
  java.lang.Object
    android.view.View
        android.widget.ViewGroup
            android.widget.LinearLayout
                android.widget.TableLayout
            android.widget.RelativeLayout
            android.widget.FrameLayout
            android.widget.GridLayout
```

#### Layout Atrributes

- `Orientation`: 레이아웃 안에 배치될 위젯의 **수직 또는 수평 방향** 설정
- `Gravity`: 레이아웃 안에 배치할 위젯의 정렬 방향을 **좌측, 우측, 중앙**으로 설정
- `Padding`: 레이아웃 안에 배치할 위젯의 여백 설정
- `Layout_weight`: 레이아웃이 **전체 화면에서 차지하는 공간의 가중값** 설정- 여러 개의 레이아웃이 중복될 때 주로 사용
- `Baselinealigned`: 레이아웃 안에 배치할 위젯을 **보기 좋게 정렬**

#### Layout Type

- `LinearLayout`: 왼쪽 위부터 아래쪽 또는 오른쪽으로 차례로 배치
- `RalativeLayout`: 위젯 자신이 속한 레이아웃의 상하좌우의 위치를 지정하여 배치
- `TableLayout`: 위젯을 행과 열의 개수를 지정한 테이블 형태로 배열
- `GridLayout`: `TableLayout`과 비슷하지만, 행 또는 열을 확장하여 다양하게 배치할 때 더 편리
- `FrameLayout`: 위젯들을 위쪽에 일률적으로 겹쳐서 배치하여 중복해서 보이는 효과를 냄

### 9-1. LinearLayout

##### Attributes

1. `orientation`

- `Vertival`: 리니어 레이아웃 안에 포함될 위젯의 배치를 **수직 방향**으로 쌓겠다는 의미
- `Horizontal`: 리니어 레이아웃 안에 포함될 위젯의 배치를 **수평 방향**으로 쌓겠다는 의미
  Orientation 속성이 Vertical 값인 XML 코드 (실습10)

```
  <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android" xmls:android="http://schemas.android.com/tools">
      android:layout_width="match_parent"
      android:layout_height="match_parent"
      android:orientation="vertical">

      //여기에 위젯 배치

  </LinearLayout>
```

2. `gravity`
   레이아웃 안의 위젯을 어디에 배치할 것인지를 결정(실습12)

3. `layout_gravity`
   자신의 위치를 부모의 어디쯤에 위치시킬지를 결정

```XML
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="fill_parent"
    android:layout_height="fill_parent"
    android:gravity="right|bottom"
    android:orientation="vertical" >

    <Button
        android:id="@+id/button1"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Button" />

    <TextView
        android:id="@+id/textView1"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="TextView" />

    <CheckBox
        android:id="@+id/checkBox1"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="CheckBox" />

    <RadioButton
        android:id="@+id/radioButton1"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="RadioButton" />

    <Switch
        android:id="@+id/switch1"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Switch" />

</LinearLayout>
```

```xml
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="fill_parent"
    android:layout_height="fill_parent"
    android:orientation="vertical" >

    <Button
        android:id="@+id/button1"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="right"
        android:text="오른쪽" />

    <Button
        android:id="@+id/button2"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="center"
        android:text="중앙" />

    <Button
        android:id="@+id/button3"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="left"
        android:text="왼쪽" />

</LinearLayout>
```

4. `Baselinealigned` 속성

- 크기가 다른 위젯들을 보기 좋게 정렬함
- True와 False 값을 가질 수 있음

\*
중복 리니어 레이아웃 형태

```xml
<LinearLayout>

    <LinearLayout>
        위젯
    </LinearLayout>

    <LinearLayout>
        위젯
    </LinearLayout>

    <LinearLayout>
        위젯
    </LinearLayout>

    <LinearLayout>
        위젯
    </LinearLayout>

</LinearLayout>
```

\*

5. `Layout_weight` (실습1)

6. `Layout_height`를 `Wrap_content`로 변경 (실습2)
   ![image](https://user-images.githubusercontent.com/50407047/84607187-e079a100-aee6-11ea-9c3a-9ff46366b3f9.png){: .center}

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical" >

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:gravity="center"
        android:orientation="vertical" >

        <Button
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="버튼1" />

        <Button
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="버튼2" />
    </LinearLayout>

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:background="#00FF00"
        android:gravity="center"
        android:orientation="horizontal" >

        <Button
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="버튼3" />

        <Button
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="버튼4" />
    </LinearLayout>

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:background="#0000FF"
        android:gravity="center"
        android:orientation="vertical" >

        <Button
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="버튼5" />

        <Button
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="버튼6" />
    </LinearLayout>

</LinearLayout>
```

7. Layout_weight를 1로 지정 (실습3)

![image](https://user-images.githubusercontent.com/50407047/84607262-423a0b00-aee7-11ea-88af-a21bca121e3a.png){: .center}

```xml
    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:layout_weight="1"
        android:gravity="center"
        android:orientation="vertical" >

        <Button
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="버튼1" />

        <Button
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="버튼2" />
    </LinearLayout>
```

### 2. RelativeLayout

- 레이아웃 내부에 포함된 위젯을 상대적인 위치로 배치

- 렐러티브 레이아웃의 상하좌우에 배치

  ![image](https://user-images.githubusercontent.com/50407047/84607336-aa88ec80-aee7-11ea-8b8c-7f65775f6e5a.png){: .center}

* 예제 (실습4)

  ![image](https://user-images.githubusercontent.com/50407047/84607359-cb514200-aee7-11ea-9c75-5c160fc64b8c.png){: .center}

  ```xml
  <RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
      android:layout_width="match_parent"
      android:layout_height="match_parent" >

      <Button
          android:layout_width="wrap_content"
          android:layout_height="wrap_content"
          android:layout_alignParentTop="true"
          android:layout_centerHorizontal="true"
          android:text="위쪽" />

      <Button
          android:layout_width="wrap_content"
          android:layout_height="wrap_content"
          android:layout_alignParentLeft="true"
          android:layout_centerVertical="true"
          android:text="좌측" />

      <Button
          android:layout_width="wrap_content"
          android:layout_height="wrap_content"
          android:layout_centerInParent="true"
          android:text="중앙" />

      <Button
          android:layout_width="wrap_content"
          android:layout_height="wrap_content"
          android:layout_alignParentRight="true"
          android:layout_centerVertical="true"
          android:text="우측" />

      <Button
          android:layout_width="wrap_content"
          android:layout_height="wrap_content"
          android:layout_alignParentBottom="true"
          android:layout_centerHorizontal="true"
          android:text="아래" />

  </RelativeLayout>
  ```

- 다른 위젯의 상대 위치에 배치

  - 각 속성의 값은 다른 위젯의 Id를 지정

  - "@+id/기준 위젯의 아이디"와 같은 형식으로 사용

    ![image](https://user-images.githubusercontent.com/50407047/84607427-25ea9e00-aee8-11ea-8305-bae317606db3.png){: .center}

- 다른 위젯의 특정한 곳에 배치 (실습5)

  ![image](https://user-images.githubusercontent.com/50407047/84607452-4e729800-aee8-11ea-92b0-2222c22f3b58.png){: .center}

  ```xml
  <RelativeLayout xmls:android="http://schemas.android.com/apk/res/android"
                  android:layout_width="match_parent"
                  android:layout_height="match_parent">

      <Button
              android:id="@+id/baseBtn"
              android:layout_width="150dp"
              android:layout_height="150dp"
              android:layout_centerHorizontal="true"
              android:layout_centerVertical="true"
              android:text="기준위젯"/>

      <Button
              android:layout_width="wrap_content"
              android:layout_heihgt="wrap_content"
              android:alignTop="@+id/baseBtn"
              android:toLeftOf="@+id/baseBtn"
              android:text="1번"/>

      <Button
              android:layout_width="wrap_content"
              android:layout_height="wrap_content"
              android:layout_alignBaseline="@+id/baseBtn"
              android:layout_toLeftOf="@+id/baseBtn"
              android:text="2번"/>

      <Button
              android:layout_width="wrap_content"
              android:layout_heihgt="wrap_content"
              android:layout_alginBottom="@+id/baseBtn"
              android:layout_toLeftOf="@+id/baseBtn"
              android:text="3번"/>

      <Button
              android:id="@+id/button2"
              android:layout_width="wrap_content"
              android:layout_height="wrap_content"
              android:layout_above="@+id/baseBtn"
              android:layout_alignLeft="@+id/baseBtn"
              android:text="4번"/>

      <Button
              android:layout_width="wrap_content"
              android:layout_height="wrap_content"
              android:layout_alignRight="@+id/baseBtn"
              android:layout_below="@_id/baseBtn"
              android:text="5번"/>

      <Button
              android:layout_width="wrap_content"
              android:layout_height="wrap_content"
              android:layout_toRightOf="@+id/baseBtn"
              android:layout_alignTop="@+id/baseBtn"
              android:text="6번"/>

  </RelativeLayout>
  ```

* 여러 위젯에서 상대 위치를 지정 (실습6)

  ![image](https://user-images.githubusercontent.com/50407047/84607487-8d085280-aee8-11ea-964c-8ae13ce86f3f.png){: .center}

  ```xml
  <?xml version="1.0" encoding="utf-8"?>
  <RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
      android:layout_width="match_parent"
      android:layout_height="match_parent" >

      <Button
              android:id="@+id/baseBtn1"
              android:layout_width="wrap_content"
              android:layout_heihgt="wrap_content"
              android:layout_alignParentLeft="true"
              android:layout_alignParentTop="true"
              android:text="기준1"/>

      <Button
              android:id="@+id/baseBtn2"
              android:layout_width="wrap_content"
              android:layout_height="wrap_content"
              android:alignParentRight="true"
              android:centerVertical="true"
              android:text="기준2"/>

      <Button
              android:layout_width="wrap_content"
              android:layout_height="wrap_content"
              android:layout_above="@+id/baseBtn2"
              android:layout_toRightOf="@+id/baseBtn1"
              android:text="1번"/>

      <Button
              android:layout_width="wrap_content"
              android:layout_heihgt="wrap+content"
              android:layout_alignParentRight="true"
              android:layout_below"@+id/baseBtn1"
              android:text="2번"/>
  </RelativeLayout>
  ```

### 9-3. TableLayout

- 주로 위젯을 표 형태로 배치할 때 사용

  ![image](https://user-images.githubusercontent.com/50407047/84608733-c17f0d00-aeee-11ea-847b-e533bcfdb4a6.png){: .center}

- 테이블 레이아웃은 <TableRow>와 함께 사용

  - <TableRow>의 개수 => 행의 개수

- 열의 개수는 <TableRow> 안에 포함된 위젯의 수로 결정

  - 3행 4열의 테이블레이아웃

#### Attributes

- `layout_column`: 지정된 열에 현재 위젯을 표시하라는 의미

- `stretchColumns`: 지정된 열의 폭을 늘리라는 의미

- `stretchColumns="*"`: 각 셀을 같은 크기로 확장해 전체 화면이 꽉 차는 효과

- 예제

  ![image](https://user-images.githubusercontent.com/50407047/84608762-e1163580-aeee-11ea-8639-003e9d6633ab.png){: .center}

```xml
  <TableLayout xmlns:android="http://schemas.android.com/apk/res/android"
      android:id="@+id/tableLayout1"
      android:layout_width="match_parent"
      android:layout_height="match_parent">

      <TableRow>

          <Button
                  android:layout_width="60dp"
                  android:text="1"/>
          <Button
                  android:layout_widht="60dp"
                  android:layout_span="2"
                  android:text="2"/>
          <Button
                  android:layout_width="60dp"
                  android:text="3"/>
      </TableRow>

      <TableRow>

          <Button
                  android:layout_width="60dp"
                  android:layout_column="1"
                  android:text="4"/>
          <Button
                  android:layout_width="60dp"
                  android:text="5"/>
          <Button
                  android:layout_width="60dp"
                  android:text="6"/>

      </TableRow>
  </TableLayout>
```

### 9-4.GridLayout, FrameLayout

- 그리드레이아웃(GridLayout)

  - <GridLayout> 자체에 자주 사용되는 속성
    - `rowCount`: 행 개수
    - `columnCount`: 열 개수
    - `Orientation`: 그리드를 수평 방향을 우선으로 할지, 수직 방향을 우선으로 할지 결정
  - 그리드레이아웃 안에 포함될 위젯에서 자주 사용되는 속성
    - `Layout_row`: 자신이 위치할 행 번호 (0번부터 시작)
    - `Layout_column`: 자신이 위치할 열 번호 (0번부터 시작)
    - `Layout_rowSpan`: 행을 지정된 수만큼 확장
    - `Layout_columnSpan`: 열을 지정된 수만큼 확장
    - `Layout_gravity`
      - 주로 `Fill`, `Fill_vertical`, `Fill_horizontal` 등으로 지정
      - 행 또는 열 확장 시, 위젯을 확장된 셀에 꽉 채우는 효과

![image](https://user-images.githubusercontent.com/50407047/84612180-c5189100-aefa-11ea-8d0f-cdba2383ff1b.png){: .center}

```xml
<GridLayout
            android:layout_width="fill_parent"
            android:layout_height="wrap_content"
            android:columnCount="4"
            android:rowCount="2">

    <Button
    		android:layout_column="0"
            android:layout_gravity="fill_vertical"
            android:layout_row="0"
            android:layout_rowSpan="2"
            android:text="1"/>
    //(Q)
    <Button
            android:layout_column="0"
            android:layout_row=""


</GridLayout>
```

- 프레임레이아웃
- 단순히 레이아웃 내의 위젯을 왼쪽 상단부터 겹쳐서 출력
- 프레임레이아웃 자체로 사용하기보다는 탭 위젯 등과 혼용해서 사용할 때 유용
- 속성
  - `foreground`: 전경 이미지 지정
  - `foregroundGravity`: 전경 이미지의 위치 지정

![image](https://user-images.githubusercontent.com/50407047/84739448-97a91180-afe6-11ea-8208-af51a60b3265.png){: .center}

```xml
<FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
             android:layout_width="match_parent"
             android:layout_height="match_parent"
             android:forground="@drawable/dog"
             android:foregroundGravity="center|fill_horizontal">

    <RatingBar
               android:id="@+id/ratingBar"
               android:layout_width="wrap_content"
               android:layout_height="wrap_content"/>

    <ImageView
               android:id="@+id/imageView1"
               android:layout_width="wrap_content"
               android:layout_heihgt="wrap_content"
               android:src="@drawable/ic_launcher"/>

    <CheckBox
              android:id="@+id/checkBox1"
              android:layout_width="wrap_content"
              android:layout_heihgt="wrap_content"
              android:text="CheckBox"/>

</FrameLayout>
```

### 10주차 1강: 고급 위젯: 날짜/시간

1. 아날로그 시계, 디지털 시계

- 계층도

  ```
  java.lang.Object
    android.view.View
        android.widget.AnalogClock
        android.widget.TextView
            android.widget.DigitalClock
  ```

````

- 예제

![image](https://user-images.githubusercontent.com/50407047/84739792-3b92bd00-afe7-11ea-9cb9-9918942fcd2a.png)

​```xml
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
              android:layout_width="fill_parent"
              android:layout_height="fill_parent"
              android:orientation="vertical">

    <AnalogClock
                 android:layout_width="match_parent"
                 android:layout_height="wrap_content"/>

    <DigitalClock
                  android:layout_width="match_parent"
                  android:layout_height="wrap_content"
                  android:gravity="center"/>

</LinearLayout>
````

2. 크로노미터 (Chronometer)

타이머 형식의 위젯
일반적으로 시간을 측정할 때 많이 사용

```
java.lang.Object
    android.view.View
        andorid.widget.TextView
            android.widget.Chronometer
```

예제

![image](https://user-images.githubusercontent.com/50407047/84740424-531e7580-afe8-11ea-9135-127a36b338ea.png){: .center}

```xml
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
              android:layout_width="fill_parent"
              android:layout_height="fill_parent"
              android:orientation:"vertical">

    //(Q)
    <Chronometer
                 android:id="@+id/chronometer1"
                 android:layout_width="match_parent"
                 android:layout_height="wrap_content"
                 android:format="시간 측정: %s"
                 android:gravity="center"
                 android:textSize="30dp"/>

</LinearLayout>

```

3. 타임피커, 데이트피커, 캘린더뷰

타임피커(TimePicker)

- 시간을 표시, 조절

데이트피커(DatePicker)와 캘린더뷰(CalenderView)

- 날짜를 표시, 조절

* 계층도

```
java.lang.Object
    android.view.View
        android.widget.ViewGroup
            android.widget.FrameLayout
                android.widget.TimePicker
                android.widget.DatePicker
                android.widget.CalenderView
```

예제

![image](https://user-images.githubusercontent.com/50407047/84740788-fe2f2f00-afe8-11ea-88c0-d9bfcb3bbb3c.png){: .center}

```xml
<LinearLayout
              android:layout_width="fill_parent"
              android:layout_height="fill_parent"
              android:Orientation="horizontal">

    <TimePicker
                android:timePickerMode="spinner"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"/>

    <DatePicker
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"/>
    //(Q)


</LinearLayout>
```

### 10주차 2강: 고급위젯: 날짜/시간 예약 앱 만들기

- 날짜/시간 예약 앱 만들기
  라디오버튼 클릭 시 캘린더뷰와 타임피커 중 하나씩만 보이게 이벤트 리스너 작성

![image](https://user-images.githubusercontent.com/50407047/84742813-0046bd00-afec-11ea-9e0b-1510b6d74f6d.png){: .center}

```xml
<LinearLayout
              android:layout_width="fill_parent"
              android:layout_height="fill_parent"
              android:baselineAligned="false"
              android:orientation="vertical">
    //(Q)
    <LinearLayout
                  android:layout_width="match_parent"
                  android:layout_height="wrap_content"
                  android:orientation="vertical">
        <Chronometer
                     android:id="@+id/choronometer1"
                     android:layout_width="match_parent"
                     android:layout_height="wrap_content"
                     android:format="예약에 걸린 시간 %s"
                     android:gravity="center"
                     android:textSize="20dp"/>

        <Button
                android:id="@+id/btnStart"
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                android:text="예약 시작"/>

    </LinearLayout>
    <RadioGroup
                android:layout_width="wrap_content"
                android:layout_height="wrap_content">
        <RadioButton
                     android:id="@+id/rdoCal"
                     android:layout_width="wrap_content"
                     android:layout_height="wrap_content"
                     android:text="날짜 설정(캘린더뷰)"/>

        <RadioButton
                     adnroid:id="@+id/rdoTime"
                     android:layout_width="wrap_content"
                     android:layout_height="wrap_content"
                     android:text="시간 설정"/>
    </RadioGroup>

    <LinearLayout
                  android:layout_width="match_parent"
                  android:layout_height="match_parent"
                  android:gravity="center">
        <FrameLayout
                     android:layout_width="match_parent"
                     android:layout_height="match_parent"
                     android:gravity="center">
                <CalendarView
                      android:id="@+id/calendarView"
                      android:layout_width="match_parent"
                      android:layout_height="match_parent"
                      android:showWeekNumber="false"/>
                <TimePicker
                    android:timePickerMode="spinner"
                    android:id="@+id/timePicker1"
                    android:layout_width="match_parent"
                    android:layout_height="match_parent"
        </FrameLayout>


    </LinearLayout>


</LinearLayout>
```

### 10주차 3강: 기타 위젯과 고급 위젯

1. 기타위젯

- 자동완성텍스트뷰와 멀티자동완성텍스트뷰
  - 사용자가 단어의 일부만 입력해도 자동완성됨
    - 자동완성텍스트뷰: 1개
    - 멀티자동완성텍스트뷰: 쉼표로 구분
  - 계층도

```
    java.lang.Object
        android.view.View
            android.widget.TextView
                android.widget.EditText
                    android.widget.EditText.AutoCompleteTextView
                        android.widget.EditText.MultiAutoCompleteTextView
```

2. 고급 위젯 -프로그래스바, 시크바, 래이팅바 - 계층도

```
   java.lang.Objectandroid.view.View
       android.widget.ProgressBar
       android.widget.AbsSeekBar
           android.widget.RatingBar
           android.widget.SeekBar
```

- 프로그래스바(ProgressBar) - 작업의 진행 상황을 바(Bar)나 원 형태로 제공
- 시크바(SeekBar) - 프로그래스바와 대부분 비슷 - 사용자 터치로 임의 조절 가능
- 래이팅바(RatingBar) - 진행 상황을 별 모양으로 표시

### 10주차 4강: 뷰 컨테이너: 스크롤뷰, 슬라이딩드로어

1. 스크롤뷰(ScrollView)

   - 수직(위아래)로 스크롤하는 기능
   - 단 하나의 위젯만 넣을 수 있음
   - 수평(좌우)로 스크롤하는 수평스크롤뷰(HorizontalScrollView)는 따로 있음
   - 계층도

   ```
     java.lang.Object
        android.view.View
            android.widget.ViewGroup
                android.widget.FrameLayout
                    android.widget.ScrollView
   ```

   2. 슬라이딩드로어(SlidingDrawer)

   - 위젯들을 서랍처럼 열어서 보여주거나 닫아서 감춤
   - 계층도

   ```
    java.lang,Obejct
       android.view.View
           android.widget.ViewGroup
               android.widget.SlidingDrawer
   ```

   - 일반적인 형태

   ```
   <슬라이딩드로어 handle="핸들명" content="콘텐트명">
       <버튼 id="핸들명"/> //서랍 손잡이 역할
       <리니어레이아웃 id-"콘텐트명">

           // 이곳이 서랍 내부이며, 필요한 위젯을 넣으면 됨

       </리니어레이아웃>
   </슬라이딩드로어>

   ```

### 11주차 1강: 뷰 컨테이너: 뷰플리퍼, 탭호스트

1. 뷰플리퍼(ViewFlipper)
   안에 여러 개의 위젯을 배치한 후, 필요에 따라서 **화면을 왼쪽과 오른쪽으로 밀어서** 하나의 위젯씩 화면에 보여주는 방식의 뷰 컨테이너

   - 계층도

   ```
    java.lang.Object
        android.view.View
            android.widget.ViewGroup
                android.widget.FrameLayout
                    android.widget.ViewAnimator
                        android.widget.ViewFlipper
   ```

   - 일반적 형태

   ```
    <리니어레이아웃>
        <리니어레이아웃>
            //왼쪽/오른쪽으로 전환할 버튼 또는 이미지뷰
        </리니어레이아웃>
        <뷰플리퍼>
            //이곳에 한 번에 하나씩 보여줄 위젯을 넣음
        </뷰플리퍼>
    </리니어레이아웃>
   ```

2. 탭호스트(TabHost)
   여러 탭을 두고 **각 탭을 클릭**할 때마다 해당 화면이 나오도록 설정하는 뷰 컨테이너

   - 일반적인 형태

   ```
    <탭호스트 id="@android:id/tabhost">
        <리니어레이아웃>
          <탭위젯 id="@android:id/tabs" />
          <프레임레이아웃 id="android:id/tabcontent">
            //여기에 각 탭스펙에 대응할 탭 화면(레이아웃)을 3개 넣음
          </프레임레이아웃>
        </리니어레이아웃>
    </탭호스트>
   ```

   - 탭을 생성하고 탭화면을 연결하기 위한 Java 코드 형식

   ```
    TabHost tabHost=getTabHost();        //탭호스트 변수 생성
    //탭스펙 생성
    TabSpec tabSpec1 = tabHost.newTabSpec("TAG1").setIndicator("탭에 출력될 글자");
    tabSpec1.setContent(R.id.tab1);      //탭스펙을 탭과 연결
    tabHost.add(tabSpec1);               //탭을 탭호스트에 부착
   ```

   - 탭호스트(TabHost)

### 11주차 2강: 간단한 웹 브라우저 앱 만들기

1. 웹뷰(WebView)

- 사용자가 **웹브라우저 기능**을 앱 안에 직접 포함시킬 수 있는 위젯

- 계층도

  ```
  java.lang.Object
  android.view.View
   android.widget.ViewGroup
     android.widget.AbsoluteLayout
       android.widget.WebView
  ```

2. 간단한 웹 브라우저 만들기

- AndroidManifest.xml (매니패스트): 프로젝트의 전반적인 환경을 설정하는 파일

### 11주차 3강: 메뉴의 개요 및 구성

1. 메뉴

- 안드로이드의 메뉴는 **옵션 메뉴**와 컨텍스트 메뉴로 구분 (이미지)

2. 옵션 메뉴를 사용하는 방법

- 메뉴 XML 파일 생성 후 Java에서 호출하는 방법
- Java 코드만으로 메뉴를 생성하는 방법

3. 메뉴 XML 파일을 이용한 방식

- 1. 메뉴 코딩: 메뉴 폴더 생성 및 메뉴 XML 파일 생성/편집
- 2. 메뉴 파일 등록: Java 코딩: `onCreateOptionMenu()` 메소드 오버라이딩
- 3. 메뉴 선택 시 작동할 내용 코딩: Java 코딩: `onOptionsItemSelected()` 메소드 오버라이딩

메뉴 XML 파일 형식

```
<menu>
  <item
    android:id="@+id/항목1아이디"
    android:title="항목1 제목"/>
  <item
    android:id="@+id/항목2아이디"
    android:title="항목2 제목"/>
</menu>
```

`onCreateOptionMenu()` 메소드 기본 형식

```java
public boolean onCreateOptionsMenu(Menu menu) {
  super.onCreateOptionsMenu(menu);
  MenuInflater mInflater = getMenuINflater();
  mInflater.inflate(R.menu.메뉴XML아이디, menu);
  return true;
}
```

### 11주차 4강: 메뉴를 통한 배경색 변경 앱 만들기

- 화면 구성
  - 메뉴 추가
    - menu 폴더가 없으면 생성해야 함 (app-res-New-Android Resource Directory - Resource type "menu1")
  - 메뉴 XML 파일 생성
    - app-res-menu-New-Menu resource file-File name "menu 1.xml"

### 12주차 1강: 컨텍스트 메뉴: 배경색 및 버튼 변경 앱 만들기

- 콘텍스트 메뉴(Context Menu)

  - 레이아웃 또는 버튼, 에디트텍스트 등의 위젯을 롱클릭하면 나타남
  - Windows의 팝업창과 비슷

- 설정 순서
  - 1. 메뉴 코딩: 메뉴 폴더 생성 및 메뉴 XML 파일 생성/편집
  - 2. 메뉴를 사용할 위젯 등록: Java 코딩: onCreate()안에 registerForContextMenu()로 등록
  - 3. 메뉴 파일 등록: Java 코딩: onCreateContextMenu() 메소드 오버라이딩
  - 4. 메뉴 선택 시 동작할 내용 코딩: Java 코딩: onContextItemSelected() 메소드 오버라이딩

### 12주차 2강: 토스트와 대화상자: 화면에 잠깐 나타났다 사라지는 메시지

- 토스트(Toast)
  - 프로그래머가 디버깅 용도로 사용하기에도 적당함
    - `Toast.makeText(Context context, String message, int duration).show();`
  - `setGravity()` 메소드를 사용하면 위치를 변경할 수 있음
    - `Toast.setGravity(int gravity, int xOffset, int yOffset)`
- 대화상자(Dialog)
  - 목적: 사용자에게 중요한 사항을 알려준 후, 어떤 선택을 하게 하는 것
  - 1. 대화상자 생성
    - `AlertDialog.Builder` 클래스로 생성
  - 2. 용도에 따른 설정
    - `setTitle()`: 제목 설정
    - `setMessage()`: 내용 입력
    - `setIcon()`: 아이콘 설정
    - `setPositiveButton()`: <OK> 버튼
    - `setNegativeButton()`: <Cancel> 버튼
    - `setItems()`: 목록 출력
    - `setSingleChoiceItems`: 라디오버튼 목록 출력
    - `setMultiChoiceItems`: 체크박스 목록 출력
  - 3. 대화상자 화면 출력
    - `show()`

### 12주차 3강: 목록 대화상자

- 목록 대화상자
- 라디오 목록 대화상자
- 체크박스 목록 대화상자

### 12주차 4강: 사용자 정보 입력 앱 화면 구성하기

- 화면 디자인 및 편집
  - 생성 항목
    - 텍스트뷰 2개
    - 버튼 1개
  - 위젯의 id
    - tvName
    - tvEmail
    - button1
- 대화상자에서 사용할 레이아웃 XML 파일 생성
  - 대화상자용 dailog1.xml
    - 텍스트뷰, 에디트뷰, 텍스트뷰, 에디트테스트의 순서로 생성
    - 에디트텍스트만 위젯 id를 dlgEdt1, dlgEdt2로 지정
  - 대화상자용 toast1.xml
    - 이미지뷰, 텍스트뷰, 이미지뷰의 순서로 생성
    - 텍스트뷰의 id를 toastText1로 지정
    - 레이아웃의 배경을 빨간색으로 지정
    - drawable에 이미지 복사

### 13주차 1강: 사용자 정보 입력 앱 완성하기

- Java 코드 작성 및 수정

  - activity_main.xml의 텍스트뷰 2개, 버튼 1개에 대응할 전역변수 3개
  - dialog1.xml의 에디트텍스트에 대응할 전역변수 2개
  - toast.xml의 텍스트뷰 1개에 대응할 전역변수 1개
  - dialog1.xml과 toast1.xml을 인플레이트할 뷰 변수 2개
  - 메인 함수 onCreate() 안에서 activity_main.xml의 위젯 변수 3개

- button1 변수를 클릭했을 때 리스너 작성

  - onClick() 메소드 안에 대화상자를 만들고 <확인>과 <취소> 추가
  - <확인>, <취소> 버튼 클릭 시 실행할 내용은 일단 null로 입력
  - ic_menu_allfriends.png => drawable에 복사

- 사용자 이름과 이메일 입력 구성
  - 대화상자의 <확인>을 클릭하면 대화상자에서 입력한 **사용자 이름과 이메일**이 **메인 화면**(activity_main.xml)의 **텍스트뷰**에 쓰이도록 코딩
  - 참고) <취소>를 클릭했을 때 toast1.xml이 토스트 메시지로 나오도록 설정
