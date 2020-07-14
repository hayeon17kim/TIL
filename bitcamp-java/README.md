# Java

비트 캠프 자바 과정 강의노트입니다.

-----



> Day1: 2020-07-13



## S/W Overview

- 소프트웨어: 컴퓨터(pc, atm, smartphone 에어콘, 저울 등)를 **제어하는 명령어**
  - 시스템 소프트웨어 (H/W 제어)
    - **운영체제** OS(Operating System)
      - Mac, Windows, Linux, Android...
    - **드라이버**: 프린터, 스캐너 등 제어(control), 조종(drive)
    - **임베디드**: 내장형 시스템
      - 반도체 ROM (칩) (하드웨어) 안 메모리 속에 개발자가 작성한 명령어가 있음
  - 응용 소프트웨어(Application Software): 하드웨어 위에서 실행됨
    - 데스크톱 애플리케이션 (Standalone Application): 홀로
      - ex) 포토샵, 메모장, 앱, 게임, 엑셀, 워드 등
      - C/C++, C# 언어 사용
      
    - **C/S (클라이언트/서버) 애플리케이션**
      
      ![CSapp](https://user-images.githubusercontent.com/50407047/87386177-db3d6e00-c5da-11ea-86a2-f228e6b9776e.jpg)
      
      - 네트워크 연결이 되어야 작동됨
      - ex) 네트워크 게임, 메신저, 메일, 게시판, 카페, 쇼핑몰 등
      - ex) (App2) 게임 유저(클라이언트) = 요청 => <=응답= (App1) 게임 서버
      - **전용**
      - C/C++, C#, PHP, ASP, Java, Python 등
      - 종류
        - TCP/UDP(Transmission Control Protocol/User Datagram Protocol) 애플리케이션
          - TCP: 인터넷상에서 데이터를 메세지의 형태로 보내기 위해 IP와 함께 사용하는 프로토콜
          - UDP: 데이터를 데이터그램 단위로 처리하는 프로토콜 
          - ex) 네트워크 게임, 메신저, 채팅, FTP 등
        - **HTTP 애플리케이션** (웹 애플리케이션)
          - 개발자 수요 70% 이상
          - 웹 브라우저를 사용해서 실행하는 프로그램
          - ex) 웹 메신저, 웹 메일, 쇼핑몰, 업무 시스템
          - HTML, CSS, JavaScript, Java, SQL, XML 등

![Web Development Overview](https://user-images.githubusercontent.com/50407047/87268231-defec100-c504-11ea-93c7-a922c8a7ecfd.jpg)





## Java 개발 환경 셋팅

![JDK](https://user-images.githubusercontent.com/50407047/87276251-ec26aa80-c51a-11ea-8733-4785e9dab565.jpg)

- Sun Enterprise
  - Standard Edition(SE)
    - 컴파일러, JVM, 디버거 등
    - Java App. 개발
    - 종류
      - `JRE`(Java Runtime Environment)
        - Java App. 실행시키는 S/W (사용자 입장)
        - **runtime** = Viewer, Playout, Engine, interpreter, Parser
        - JVM
      - `JDK`(Java Development Kit)
        - Java App. 만드는 S/W
        - Compiler, Debugger
        - `JRE` 포함: JDK 설치하면 JRE 자동 설치(for test)
  - Enterprise Edition (EE)
    - 서버 App. 개발에 필요한 도구
  - Micro Edition(ME)
- Open JDK
  - GraalVM: Open JDK 포함하고 있음



- Java는 Python과 달리 상위버전은 하위버전을 100% 포함



- **Package Manager**
  - Mac의 `brew`나 , Linux의 `apt-get`. Window에는 공식 Package Manager 없음
  - `scoop`, `chocholatey`사용 가능
  - 공식 Package Manaer `windget` 개발 중 
  - 설치파일 찾으러 다니거나 업데이트 일일이 할 것 없이 명령어 한 줄로 설치, 업데이트 삭제까지 가능



### 체크섬 및 해시값 확인 (`checksum`, `hash value`)

- 프로그램을 다운로드 받아서 설치하기 전에 해당 프로그램이 제대로 설치되어 있는지 확인하는 방법
  - 해당 파일이 변조되어 있지 않은지 확인: 누군가 해킹코드를 심어 넣은 프로그램을 다운받았을 수 있음
- 체크섬 할 수 있는 해쉬 값은 공식 사이트에 올려져 있거나 최초 배포값
- md5, sha1, sha256, sha512.. 
- 다운로드 한 파일이 원본과 같은지 비교
- xcode(도구)를 외부 텐센트에서 다운로드 받아 app을 빌드했음. 근데 그 도구(소프트웨어) 자체에 해킹코드가 삽입되어 있었음. 그래서 사용자가 앱을 사용할 때 그 해킹코드가 있는 앱을 사용하면서 데이터를 해커 서버에 전송(upload)된 경우
- 다운로드 받은 파일이 서버에 있는 파일과 같은 지 방법
  - Windows 10 의 Powershell에서 이 chcksum 확인하는 기능을 제공한다.
    1. Windows Powershell을 실행한다.
    2. 다운로드한 파일이 있는 폴더로 이동한다.
    3. `get-filehash -algorithm {공식명} '파일명.확장자명' | Format-List` 
    4. 공식사이트의 `checksum`과 비교하여 확인한다.

```powershell
PS C:\Users\bitcamp\Downloads> get-filehash -algorithm sha512 'mariadb-10.5.4-winx64.msi' | Format-List

Algorithm : SHA512
Hash      : 00043DAC2BA0659DF43A07BD4D9ED06C3F82CA143AC57A9C0AD5970B294766523AA66A3F280FE77CC30FD5490A504ECE1152D83C07087EB766AB1280A26901A1
Path      : C:\Users\bitcamp\Downloads\mariadb-10.5.4-winx64.msi
```

- graalVM 속 폴더들
  - `bin`(binary)
    - 실행할 수 있는 명령어가 들어있는 폴더
    - 들어 있는 파일들
      - java.exe: 자바 프로그램 플레이어
      - javac.exe: 자바 프로그램 만들어줌
  - conf(config)
    - 프로그램을 실행함에 있어서 **설정 정보**가 들어있는 폴더
  - include
    - 실행 파일에서 필요한 라이브러리 들어있음
  - lib
    - 자바 관련되어 필요한 모든 명령어들
- 환경변수 설정
  - 사용자변수: bitcamp 사용자만 사용 가능
  - 시스템변수: 어떤 사용자를 만들어도 사용 가능

```powershell
PS C:\Users\bitcamp> java.exe -version
openjdk version "11.0.7" 2020-04-14
OpenJDK Runtime Environment GraalVM CE 20.1.0 (build 11.0.7+10-jvmci-20.1-b02)
OpenJDK 64-Bit Server VM GraalVM CE 20.1.0 (build 11.0.7+10-jvmci-20.1-b02, mixed mode, sharing)

PS C:\Users\bitcamp> echo $env:JAVA_HOME
C:\devtools\graalvm-ce-java11-20.1.0\bin;C:\WINDOWS\system32;C:\WINDOWS;C:\WINDOWS\System32\Wbem;C:\WINDOWS\System32\WindowsPowerShell\v1.0\;C:\WINDOWS\System32\OpenSSH\;C:\Program Files\nodejs\;C:\Program Files\Git\cmd;C:\Users\bitcamp\AppData\Local\Microsoft\WindowsApps;;C:\Users\bitcamp\AppData\Local\Programs\Microsoft VS Code\bin;C:\Users\bitcamp\AppData\Roaming\npm
PS C:\Users\bitcamp> echo $env:PATH

```

- Mac 자체가 Linux
  - 패키지 매니저 프로그램이 있음(home brew.. )
  - 윈도우즈에서 위와 같은 패키지 매니저 사용 하려면 `scoop`이나 `chocolate`써라: `winget` 개발 중
  - 굳이 공식 사이트 가서 다운 받아서 설치하고 *환경변수* 등록할 필요 X 







>  Day2: 2020-07-14

### Project Overview



![process](https://user-images.githubusercontent.com/50407047/87386180-dd073180-c5da-11ea-9942-0d4a6e58dc3c.jpg)

### Java Environmnet 설정

[Visual Studio Code 설치]

- 다운로드 후 더블 클릭하여 설치

[JDK 설치]

- graalvm 다운로드 및 압축 해제
- OS JDK 설치 경로를 등록
  - OS 환경변수 설정창 열기
  - JAVA_HOME이라는 이름으로 JDK 설치 경로 등록
- OS에 JDK 도구 폴더 등록
  - PATH 라는 환경변수에 java.exe, javac.exe 등이 있는 폴더를 등록해두면 아무 곳(디렉토리)에서나 실행할 수 있다.

[Eclipse IDE(Integrated Development Environmnet) 설치]

- 다운로드 및 설치
- 이클립스 실행
- 워크스페이스 설정
  - 워크스페이스 디렉토리 설정
  - C:\Users\bitcamp\eclipse-workspace
- 이클립스 IDE 설정
    - Window > Preference 메뉴 선택
    - General > Editers 선택
        - 탭 크기: 2
        - 탭 대신 공백 삽입
        - 한 줄 100칸
        - 공백 문자를 특수 기호로 표시
    - General > Workspace 선택
        - Text file encoding: UTF-8
    - Java > Installed JREs
        - graalvm-ce-java11 확인
    - Java > Code Style > Formatter
        - eclipse-java-google-style.xml 파일 다운로드
        - 이 파일을 import 한다.

[scoop **패키지 매니저** 설치]

- scoop.sh 사이트 방문
- 안내에 따라 설치한다.

[git 형상관리도구 설치]
- scoop install git 실행

[gradle 빌드도구 설치]

- scoop install gradle 실행

[Tomcat 설치]

- c:\devtools 폴더에 압축을 푼다.
- PATH 환경변수에 bin 폴더를 추가한다.



[git]

- 형상관리 도구
  - CVS
  - Subversion
  - Git
    - Git을 전문적으로 서비스해주는 사이트가 `github.com` (MS 인수)



[gradle]

- `.war`: web archive(보관): 웹 프로젝트를 하나의 파일로 패킹
  - .zip으로 변경 후 압축 풀어도 됨
  - 제품 한 상자로 패킹한 게 .war
- `.jar` 
- `.tar` : 백업하기 위해 한 파일로 만든 것 => 풀으면 디렉토리와 파일이 생김
- `.tar.gz`: tar로 묶은 파일을 압축



- `gradle build`
- `gradle clean` : 빌드한 것 삭제
- `라이브러리`: 다른 개발자들이 미리 작성한 명령어들의 모음
  - 뭘 다운받아야 하는지는 `package.json`에 적혀 있음
    - `npm install` 를 실행하면 현재 폴더에서 package.json 파일에 따라 라이브러리들을 설치함
    - node_modules 폴더가 생성되고 그 안에 라이브러리들이 생성됨
  - JS는 npm이라는 패키지 매니저가 있음
    - npm: node package manager
    - node: interpreter, compiler와 vm 비교를 위해 nodejs 사용해 볼 것임
- npm install => gradle build



1. 프로젝트 다운 (git clone)
2. npm install (라이브러리 설치)
3. gradle build (war 생성)



build 과정(gradle은 이걸 순차적으로 실행시켜줄 뿐, 과정에서 필요한 jdk 꼭 필요)

- compile (=> javac.exe)
- test (=> java.exe)
- upload 
- ... 



[Tomcat]

Web Server: Tomcat

- http://localhost:번호
  - 아이피를 친 것과 같음
- Tomcat의 내선번호는 8080



웹서버가 war 파일을 실행할 수 있도록 app을 웹서버에 **배치(Deploy)**한다



[MariaDB]

- 과정
  - 다운로드 및 설치
  - 사용자 생성
  - 데이터베이스 생성
  - 사용자가 사용할 데이터베이스의 권한 설정
  - 테이블 생성
  - 예제 및 마스터 데이터 입력

- 특징
  - 실무에서 많이 씀
  - google, facebook 에서 사용
  - My-SQL과 99퍼센트 호환
  - 포트번호: 3306 => 다른 곳과 통신하기 위해 고유번호
    - 서버쪽 포트번호는 중복되면 안됨
  - 서버 설치하면 클라이언트도 함께 설치 mysql client application software



### App 실행 방식 (Interpreter 방식 VS Compiler 방식)

![app 실행 방식 비교](https://user-images.githubusercontent.com/50407047/87401971-d76b1500-c5f5-11ea-8b33-86d91e202ca1.jpg)

- **Interpreter 방식**

  - **매번** interpreter가 **실행할 부분**만 문법 검사 후 실행: 속도 느림

  - 소스코드 노출

  - 실행할 때마다 Interpreter 필요

  - CPU와 OS에 비종속적

    - 원본 명령어를 해석기로 실행

  - ex) js(js의 해석기는 NodeJS), py, php

  - 장단점

    - 장점: 소스파일과 Interpreter만 있다면 바로 실행 가능
- 단점: 오류가 있음에도 불구하고 실행할 때만, 실행하는 부분만 문법검사하여 오류가 있는지 모름
      - 작은 프로젝트는 interpreter 방식 좋은데, 큰 프로젝트에서는 좋지 않음

```javascript
var lang = "english";

if (lang == "english")
    console.log("Hello,world!");	//바뀔 부분
else
    console.log('안녕하세요!');
```

위의 코드에서

```javascript
var lang = "english";

if (lang == "english")
    console.leg("Hello,world!");	//바뀐 부분
else
    console.log('안녕하세요!');
```

위와 같이 `console.leg`로 바뀐다면 문법에 맞지 않지만, 해당 부분은 **실행되지 않는 부분이기 때문에 interpreter가 문법검사를 하지 않는다.** 



- **Compiler 방식**

![컴파일러 방식의 교차 불가능성](https://user-images.githubusercontent.com/50407047/87406725-1603ce00-c5fc-11ea-8692-9c1b60a4441d.jpg)

  - 한번 compiler가 기계어로 번역하고 그 변환된 기계어로 실행: 속도 빠름
  - 소스코드 보호
  - 실행할 때마다 Interpreter 불필요
  - CPU와 OS에 종속적
    - CPU와 OS에 맞춰서 기계어로 번역함
    - CPU가 바뀌면 이 CPU에 맞춰서 다시 Compile 해야 함
  - ex) C
  - 기계어는 CPU에 따라 달라짐
  - Intel CPU의 기계어는 같지만 Windows와 Linux는 기계어를 배치하는 구조(포맷)가 다름. so CPU가 같아 기계어가 같다고 해도 운영체제가 다르면 실행 못함

  

###### Windows에 Compiler가 없는 이유

  - UNIX 
      
      - 컴파일러 포함 O
  - 전문가용
    
    - a.c (source) = Compile => a.exe (실행 파일)
- 옛날에는 하드웨어마다 CPU가 달랐음
    - 컴파일을 하면 각 CPU에서만 실행할 수 있는 파일이 만들어지기 때문에, 원본 source code 자체를 공유하는 것이 당연시되었음
  - Windows 
      - 컴파일러 포함 X
      - 일반인용: PC(Personal Computer) 보급 이후 사용된 운영체제 (예전에는 DOS)
      - Intel CPU => 컴파일한 exe 파일만을 복사해서 사용 가능
        - 소스코드 공유X
        - ex파일 공유O
        - so, 소수의 전문가를 위한 Visual Studio 유료로 판매
      - but 무료 컴파일러도 있음 (gcc)

  

#### 직접 컴파일 방식을 체험해보자.

  .c 파일을 만들어 주자.

  ```powershell
  #include <stdio.h>
  
  int main() {
      printf("Hello, world");
      printf("안녕하세요!");
      return 0;
  }
  ```

  

  gcc를 설치하고, 

  powershell에 다음과 같이 명령어를 친다.

  ```powershell
  PS C:\Users\bitcamp\bitcamp-workspace> gcc -o hello.exe hello.c
  ```

  `-o`: 만들고자 하는 목적 파일

  ```powershell
  PS C:\Users\bitcamp\bitcamp-workspace> dir
  
  
      디렉터리: C:\Users\bitcamp\bitcamp-workspace
  
  
  Mode                LastWriteTime         Length Name
  ----                -------------         ------ ----
  -a----     2020-07-14   오후 4:13          48434 a.exe
  -a----     2020-07-14   오후 4:12            114 hello.c
  -a----     2020-07-14   오후 4:14          48434 hello.exe	// 컴파일로 생성된 파일
  -a----     2020-07-14   오후 3:13             63 hello.js
  -a----     2020-07-14   오후 3:18            125 hello2.js
  ```

  다음과 같이 컴파일된 hello.exe가 생성되었다. (기계어로 변환됨)

  이 과정에서 문법 검사를 한다.

```powershell
PS C:\Users\bitcamp\bitcamp-workspace> ./hello                     
Hello, world?덈뀞?섏꽭??
```

컴파일된 파일을 실행하면 다음과 같이 나온다 (한글은 깨진다..)

그러나, 이 exe 파일은 컴파일한 환경인 Intel CPU와 Windows 10에 **맞춰 컴파일되었기 때문에**, Mac이나 Linux에서는 실행되지 않는다.

수정사항이 있으면 다시 컴파일해야 하는데, 수정사항을 저장만 하면 되는 interpreter 방식과 다르다.



##### 안드로이드 os 는?

- Playstore에 **중간언어**로 컴파일한 파일(이미 문법검사 끝난 App)을 올림
- 핸드폰에 다운받을 때 **최종번역**된 App 다운받는다.
- 따라서, CPU가 다른 핸드폰에서 다 실행이 되는 것.



#### Hybrid 방식: Java

>  Java: "Write Once, Run Anywhere"



![Java의 Hybrid 실행 방식](https://user-images.githubusercontent.com/50407047/87406765-29169e00-c5fc-11ea-9136-0a9b65f2ff7f.jpg)

명령어를 관리하기 쉽게 분류

- classfication => 독립적 단위: 객체
  - Object Oriented Program(OOP)
  - C => C++
- JVM
  - App -> JVM -> OS
  - Just In Time (JIT): 예전 안드로이드 방식
    - 실행할 때 시간 걸림
  - Ahead of Time(AOT): 실행 앞서 미리 컴파일 다 함 (지금 안드로이드 방식). so 설치할 때 시간이 걸림
