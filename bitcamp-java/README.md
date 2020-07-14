# Java

비트 캠프 자바 과정 강의노트입니다.

-----



Day1: 2020-07-13

1. S/W Overview

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
      - 네트워크 연결이 되어야 작동됨
      - ex) 네트워크 게임, 메신저, 메일, 게시판, 카페, 쇼핑몰 등
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



Java 개발 환경 셋팅

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



- 체크섬 및 해시값 확인 (`checksum`, `hash value`)
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
  - 굳이 공식 사이트 가서 다운 받아서 설치하고 환경변수 등록할 필요 X 



#### Java Environmnet 설정

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

[scoop 패키지 매니저 설치]
- scoop.sh 사이트 방문
- 안내에 따라 설치한다.

[git 형상관리도구 설치]
- scoop install git 실행

[gradle 빌드도구 설치]

- scoop install gradle 실행