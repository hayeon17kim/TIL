# Section 2: 스프링 웹 개발 기초

웹 개발에는 크게 정적 컨텐츠, MVC와 템플릿 엔진, API의 세 가지 방법이 있다.

- **정적 컨텐츠**: 서버에서 하는 일 없이 서버에서 파일을 그대로 내려준다.
- **MVC와 템플릿 엔진**: 가장 많이 사용하는 방식이다. JSP, PHP 등이 템플릿 엔진이다. 정적 컨텐츠는 그냥 파일을 그대로 웹브라우저에게 전달한다면, MVC와 템플릿 엔진은 서버에서 HTML을 변형해서 내려주는 방식이다. 이를 위해 앱을 Controller, Model, View 세 가지 역할로 분리한다. 이를 MVC 패턴이라고 한다. 
- **API**: 안드로이드나 아이폰 클라이언트와 개발을 한다면 요즘은 서버에서 데이터를 JSON이라는 데이터 구조 포맷으로 내려서 클라이언트에 전달한다. 이 방식은 ViewJS, React 를 사용하거나 서버끼리 통신할 때도 사용한다. 특히 서버끼리 통신할 때는 데이터가 왔다갔다 하는지가 중요하지 화면은 중요하지 않기 때문이다. 

## 1강. 정적 컨텐츠

[스프링 부트 정적 컨텐츠 기능]([Spring Boot Features](https://docs.spring.io/spring-boot/docs/2.3.1.RELEASE/reference/html/spring-boot-features.html#boot-features-spring-mvc-static-content))

기본적으로 스프링 부트는 `/public`이나 `/static`에서 정적 컨텐츠를 찾아서 클라이언트에게 보낸다. 접근할 때는 확장자까지 파일명 그대로 주소창에 쳐야 한다.

```html
<!DOCTYPE HTML>
<html>
<head>
 <title>static content</title>
 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>
<body>
정적 컨텐츠 입니다.
</body>
</html>
```

웹브라우저에서  `localhost:8080/hello-static.html` 요청을 보내면 우선 내장 톰캣 서버가 요청을 받고, 스프링 컨테이너에게 이 주소를 넘긴다. 그럼 스프링은 우선 컨트롤러쪽에서 hello-static이 있는지 찾아본다. 즉 **컨트롤러가 우선순위를 가진다**. hello-static 관련(매핑이 된) 컨트롤러가 없다면 resources/static에서 hello-static을 찾는다. 정적 컨텐츠는 이런식으로 동작한다.

## 2강. MVC와 템플릿 엔진

MVC: Model, View, Controller

과거에는 Controller와 View가 분리되지 않았다. 예전에는 JSP로 처리했는데 이걸 Model1 방식이라고 한다. 지금은 MVC 스타일로 많이 한다. 왜냐하면 관심사(역할과 책임)를 분리해야 유지보수하기에 좋기 때문이다. View는 화면을 그리는 데 모든 역량을 집중해야 한다. 그리고 Controller와 Model 같은 경우에는 비즈니스 로직을 처리하거나 내부 로직을 처리하는 데 집중해야 하다. 그래서 컨트롤러에서 모든 것을 처리하다가 이를 Model, View, Controller로 분리하게 되었다.

**hello.hellospring.controller.HelloController**

```java
@Controller
public class HelloController {
 @GetMapping("hello-mvc")
 public String helloMvc(@RequestParam("name") String name, Model model) {
 model.addAttribute("name", name);
 return "hello-template";
 }
}
```

> RequestParam 에서 required 속성은 값이 필수값인지 아닌지를 정한다.

```html
<p th:text="'hello' + ${name}">hello! empty</p>
```

타임리프 템플릿은 html 파일을 그대로 사용하기 때문에 파일을 서버 없이 바로 열어봐도 껍데기(hello! empty)를 볼 수 있다는 장점이 있다. 그런데 이게 템플릿 엔진으로 동작을 하면(실제 서버를 타서 돌면) hello name이 나온다. 서버 없이 html을 마크업하는 분들이 볼 때 값을 적어놓고 볼 수 있도록 할 수 있다.

### 동작 방식

웹 브라우저에서 보낸 `localhost:8080/hello-mvc` 요청은 스프링 부트를 띄울 때 같이 띄우는 내장 톰캣서버를 거친다. 그러면 톰캣 서버는 `hello-mvc` 요청이 왔음을 스프링 컨테이너에게 전달한다. 그럼 스프링 컨테이너는 `helloController`의 메서드에 `hello-mvc`가 매핑이 되어 있는 것을 확인하고 이 메서드를 호출한다. 이 메서드는 모델에 `spring`이라는 문자열을 `name`이라는 이름으로 담고, `hello-template` 문자열을 리턴한다. 그러면 뷰 리졸버가 `template/hello-template.html`을 찾아서 `Thymeleaf` 템플릿 엔진에 처리해달라고 넘긴다. 그러면 Thymeleaf가 모델에 담긴 데이터(name)를 사용하여 렌더링해서 HTML로 변환한다. 그러면 변환된 HTML은 스프링 컨테이너에서 톰캣서버로, 그리고 웹 브라우저로 전달된다. 

## 3강. API

서버 응답 방식에는 정적 컨텐츠 방식을 제외하면 View를 찾아 템플릿 엔진을 통해 변환된 HTML을 웹 브라우저에게 넘겨주는 방식이 있고, API를 사용하는 방식이 있다. 즉 서버가 직접 HTML 문서를 만들어 클라이언트에게 넘겨주든지, 데이터 자체를 넘겨주든지의 문제이다.

### @ResponseBody 문자 반환

```java
@Controller
public class HelloController {
 @GetMapping("hello-string")
 @ResponseBody
 public String helloString(@RequestParam("name") String name) {
 return "hello " + name;
 }
}
```

이전에 템플릿 엔진은 화면을 가지고 View 라는 템플릿에서 이를 조작하는 방식이었다. `@ResponseBody` 애노테이션을 사용하면 viewResolver를 사용하지 않는다. 대신에 HTTP의 Body에 문자 내용을 직접 반환한다. 실무에서 이 방식은 거의 사용하지 않는다.

### @ResponseBody 객체 반환

```java
@Controller
public class HelloController {
 @GetMapping("hello-api")
 @ResponseBody
 public Hello helloApi(@RequestParam("name") String name) {
 Hello hello = new Hello();
 hello.setName(name);
 return hello;
 }
 static class Hello {
 private String name;
 public String getName() {
 return name;
 }
 public void setName(String name) {
 this.name = name;
 }
 }
}
```

`@ResponseBody`를 사용하고, 객체를 반환하면 객체가 JSON을 ㅗ변환된다. 

> IntelliJ에서 Ctrl+Enter 키를 누르면 `getter`와 `setter`를 자동생성한다. 

> 필드를 외부에서 접근하지 못하게 private으로 막고 `getter`와 `setter`를 통해 접근하도록 만드는 것을 프로퍼티 접근 방식, JavaBean 표준 방식이라고 한다. 

`localhost:8080/hello-api?name=spring!!!` 요청하면 다음과 같은 json 데이터가 출력된다.

```json
{
  name: "spring!!!";
}
```

과거에는 JSON이 아니라 XML방식을 많이 사용했다. XML 방식은 무거울 뿐만 아니라 태그를 열고 닫는 과정이 번거롭다. 그런데 JSON은 XML에 비해 간단하게 표기할 수 있다. 과거에는 데이터를 전달할 때 두 방식 모두 많이 사용했지만, 최근에는 JSON 방식으로 통일이 되었다. 따라서 스프링부트에서도 객체를 리턴하면 JSON으로 반환하는 것이 디폴트로 셋팅되어 있다. 물론 원하면 XML 방식으로 바꿀 수 있다. 

### @ResponseBody 사용 원리

웹 브라우저가 hello-api로 요청을 보내면 내장 톰캣서버에서 hello-api 가 왔다고 스프링 컨테이너에게 던진다. 스프링 컨테이너는 helloController에 hello-api가 매핑되어 있는 메서드가 있는 것을 확인한다. 이 메서드에 `@ResponseBody`가 붙어 있으면 HTTP BODY에 문자 내용을 직접 반환한다. 이때 `viewResolver` 대신 `HttpMessageConverter`가 동작한다. 기본 문자처리는 `StringHttpMessageConverter`가, 기본 객체처리는 `MappingJackson2HttpMessageConverter` (JsonConverter)가 한다. byte 처리 등등 기타 여러 HttpMessageConverter가 기본으로 등록되어 있다. 

> 클라이언트의 HTTP Accept 헤더와 서버의 컨트롤러 반환 타입 정보 둘을 조합해서 `HttpMessageConverter`가 선택된다.