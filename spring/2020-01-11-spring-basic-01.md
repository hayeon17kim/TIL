# Section 1. 프로젝트 환경설정

## 1강. 프로젝트 생성

[스프링 부트 스타터 사이트](https://start.spring.io/)에서 스프링 프로젝트를 생성하였다. 

- 프로젝트 선택
  - Project: Gradle Project (요즘에는 Maven보다 Gradle을 더 많이 사용하는 추세이다.)
  - Spring Boot: 2.3.x
  - Language: Java
  - Packaging: Jar
  - Java: 11

-  Project Metadata
   -  groupId: hello
   -  artifactId: hello-spring
-  Dependencies: Spring Web, Thymeleaf

생성된 프로젝트의 소스폴더는 main과 test로 나뉘어져 있다.

```
src
  - main
  - test
```

이처럼 요즘은 테스트코드를 작성하는 것이 굉장히 중요하게 여겨진다.

**build.gradle**

```groovy
plugins {
	id 'org.springframework.boot' version '2.4.1'
	id 'io.spring.dependency-management' version '1.0.10.RELEASE'
	id 'java'
}

group = 'hello'
version = '0.0.1-SNAPSHOT'
sourceCompatibility = '11'

// 의존 라이브러리를 다운받을 사이트
repositories {
	mavenCentral()
}

// 의존 라이브러리 목록
dependencies {
	implementation 'org.springframework.boot:spring-boot-starter-thymeleaf'
	implementation 'org.springframework.boot:spring-boot-starter-web'
	testImplementation 'org.springframework.boot:spring-boot-starter-test'
}

test {
	useJUnitPlatform()
}

```

`dependencies`에는 의존 라이브러리가 기입된다.

- spring-boot-starter-thymeleaf: 스프링 부트 템플릿 엔진이다.
- spring-boot-starter-test: JUnit5가 디폴드로 들어간다.

위와 같은 라이브러리를 어디선가 다운받아야 할 것이다. 

```
repositories{ 
	mavenCentral()
}
```

위 코드는 `mavenCentral`이라는 공개된 사이트가 있는데, 그 사이트에서 `dependencies`에 적혀진 라이브러리를 다운받으라는 선언이다. 이곳에 mavenCentral이 아니라 개발자가 직접 URL을 적을 수도 있다.

`gitignore` 파일도 스프링부트에서 만들어준다.

`@SpringBootApplication` 애노테이션을 붙여주면 톰캣이라는 웹서버를 내장하고 있다. 톰캣 웹서버를 자체적으로 띄우면서 스프링도 같이 올라온다.

**IntelliJ Gradle 대신에 자바 직접 실행**

최근 IntelliJ 버전은 Gradle을 통해서 실행하는 것이 기본 설정이다. 이렇게 하면 실행속도가 느리기 때문에 자바로 바로 실행하도록 만들어주자.

- Preference > Build, Execution, Deployment > Build Tools > Gradle
  - Build and run using: Gradle -> IntelliJ IDEA
  - Run tests using: Gradle -> IntelliJ IDEA

**단축키 조회 방법**

- 윈도우: Ctrl + Alt + Shift + T
- File -> Settings -> Keymap

## 2강 라이브러리 살펴보기

> Gradle 은 의존관계가 있는 라이브러리를 함께 다운로드한다.

```groovy
dependencies {
	implementation 'org.springframework.boot:spring-boot-starter-thymeleaf'
	implementation 'org.springframework.boot:spring-boot-starter-web'
	testImplementation 'org.springframework.boot:spring-boot-starter-test'
}
```

`buid.gradle`의 `dependencies`에는 세 개의 라이브러리밖에 없는데 Project Explorer의 `External Libraries`에는 굉장히 많은 라이브러리가 있다. 이 라이브러리들은 실제 웹 애플리케이션을 만드는 데 거의 필수적이라고 한다. 실제로 빌드를 해보면 몇 십 기가바이트의 용량이 나올 정도다. 그런데 왜 이런 차이가 있을까?

우리는 의존 라이브러리 하나만 필요하다고 생각하지만, 해당 의존 라이브러리가 의존하고 있는 또다른 라이브러리도 있다. 또한 그 라이브러리는 다른 다른 라이브러리를 의존하고 있다. 

Gradle은 라이브러리를 다운로드할 때 의존 관계가 있는 라이브러리들을 함께 다운로드한다.

![image](https://user-images.githubusercontent.com/50407047/103901579-ab5fc500-513c-11eb-9b91-f5ac1eb0668a.png)

IntelliJ에서 라이브러리 의존 관계를 확인할 수 있다.

### 스프링 부트 라이브러리

- spring-boot-starter-web
  - spring-boot-starter-tomcat: 톰캣(웹서버)
  - spring-webmvc: 스프링 웹 MVC

> **spring-boot-starter-tomcat?** 과거에는 WAS(웹 서버 ex: 톰캣)를 직접 서버에 설치를 하고, 이 WAS에 자바 코드를 밀어넣는 식으로 **웹 서버와 개발 라이브러리가 완전히 분리**가 되어 있었다. 그래서 톰캣 서버에 들어가서 설치해야 하는 등 힘든 작업을 해야 했다. 그러나 요즘은 소스 라이브러리에서 이런 웹서버를 기본으로 임베디드(내장)하고 있다. 우리는 실행만 하는데도 웹 서버가 뜨고 포트번호 8080으로 접속할 수 있다. 이처럼 요즘은 라이브러리 하나를 빌드해서 웹 서버를 올리면 된다. 예전처럼 톰캣서버를 깔지 않는다. 

- spring-boot-starter-thymeleaf: 타임리프 템플릿 엔진(View)
- spring-boot-starter(공통): 스프링 부트 + 스프링 코어 + 로깅
  - spring-boot
    - spring-core
  - spring-boot-starter-logging
    - logback
    - slf4j

> **log?** 현업에서는 테스트를 할 때 `System.out.println`으로 출력을 하는 것이 아니라 `log` 파일로 남겨야 한다. 그래야 나중에 어떤 문제가 발생했는지 확인하고 공유할 수 있기 때문이다. **slf4j**는 인터페이스이고 실제 로그를 어떤 구현체로 출력할 것인지는 **logback**이 담당한다. 요즘에는 이 두 개의 조합을 많이 사용해서 (거의 표준에 가까움) 스프링 부트 starter-logging을 다운로드 받을 때 두 라이브러리도 자동으로 다운로드받는다.


### 테스트 라이브러리

- spring-boot-starter-test
  - junit: 테스트 프레임워크
  - mockito: 목 라이브러리
  - assertj: 테스트 코드를 좀 더 편하게 작성하게 도와주는 라이브러리
  - spring-test: 스프링 통합 테스트 지원. JUnit으로 테스트를 할 때 스프링과 통합해서 테스트를 할 수 있도록 지원해준다.

## 3강 View 환경설정

### Welcome Page 만들기

스프링 부트가 제공하는 Welcome Page 기능

- `static/index.html`을 올려두면 Welcome page 기능을 제공한다.

`resources/static/index.html`

```html
<!DOCTYPE HTML>
<html>
<head>
 <title>Hello</title>
 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>
<body>
Hello
<a href="/hello">hello</a>
</body>
</html>
```

> 스프링 부트는 스프링 생태계 자체를 감싸서 편리하게 사용할 수 있도록 도와준다. 스프링이 자바 엔터프라이즈 웹 개발과 관련된 어마어마한 기능을 제공한다. 다 머리속에 담을 수가 없기 때문에 필요한 것을 찾는 능력이 필요하다. [스프링 부트 공식문서](https://docs.spring.io/spring-boot/docs/current/reference/html/)에서 검색해서 확인하자.

### thymeleaf 템플릿 엔진

위의 html문서는 정적이다. 프로그램이 아니라 파일 자체를 웹 브라우저에 던져주는 것이다. 여기에 반복문, 조건문 등 프로그래밍적인 요소를 첨가하여 동적으로 만들고 싶다면 템플릿 엔진을 사용하면 된다. Spring Boot 공식문서에서 제안하고 있는 템플릿 엔진에는 `FreeMaker`, `Groovy`, `Thymeleaf`, `Mustache` 등이 있다. 

[thymeleaf 공식문서](https://www.thymeleaf.org/)

**hello.hellospring.controller.HelloController**

```java
@Controller
public class HelloController {
    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello");
        return "hello";
    }
}
```

**resources/templates/hello.html**

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Hello</title>
</head>
<body>
<p th:text="'안녕하세요. ' + ${data}">안녕하세요. 손님</p>
</body>
</html>
```

### 동작 환경

웹 브라우저에서 `localhost:8080/hello`라는 url로 요청을 보내면 내장 톰캣 서버가 요청을 받아서 스프링 컨테이너의 helloController의 `@GetMapping("hello")` 애노테이션이 붙어 있는 hello 메서드를 실행한다. 여기서 파라미터로 받은 모델 객체에 데이터를 담고, 문자열 `"hello"`를 리턴한다. 그러면 `viewResolver`가 앞뒤에 경로와 확장자를 붙여서 `resources/template/hello.html`라는 화면을 찾아서 처리한다. 템플릿에서는 모델에 담은 데이터를 꺼내쓸 수 있다.

- 스프링 부트 템플릿엔진 기본 `viewName` 매핑
- `resources:templates/` + `{ViewName}` + `.html`

> `spring-boot-devtools` 라이브러리를 추가하면, `html` 파일을 컴파일만 해주면 서버 재시작 없이 `View` 파일 변경이 가능하다.
>
> 인텔리J 컴파일 방버: 메뉴 build -> Recompile

## 4강. 빌드하고 실행하기

```console
gradlew build
cd build/libs
java -jar hello-spring-0.0.1-SNAPSHOT.jar
```

서버 배포할 때는 이 파일만 복사해서 서버에 넣고 `java -jar` 명령어를 쳐서 실행시키면 된다. 그러면 서버에서 스프링이 동작하게 된다. 과거에는 톰캣을 서버에 직접 설치하고 특정 폴더에 war를 만들어서 집어넣는 과정이 있어야 했다. 지금은 jar 파일 하나만 복사하고 이를 실행시키면 된다.

빌드가 잘 안된다면 `./gradlew clean`, `./gradlew.clean build` 명령어를 실행한다. 이 명령어는 빌드 폴더를 깔끔하게 비워서 다시 초기 상태에서 빌드할 수 있게 해준다. 