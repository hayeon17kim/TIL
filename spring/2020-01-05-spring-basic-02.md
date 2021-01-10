# Section 2: 스프링 웹 개발 기초

웹을 개발한다는 것은 크게 세 가지 방법이 있다.

- **정적 컨텐츠**: 서버에서 하는 일 없이 서버에서 파일을 그대로 내려준다.
- **MVC와 템플릿 엔진**: 가장 많이 하는 방식이다. JSP, PHP 등이 템플릿 엔진이다. HTML을 그냥 주는 것이 아니라 서버에서 프로그래밍해서 HTML을 동적으로 바꿔서 내리는 것이다. 이를 하기 위해서 Controller, Model, View 세가지를 MVC 라고 한다. 정적 컨텐츠는 그냥 파일을 그대로 웹브라우저에게 전달한다면, MVC와 템플릿 엔진은 서버에서 HTML을 변형해서 내려주는 방식이다.
- **API**: 만약 안드로이드나 아이폰 클라이언트와 개발을 한다고 한다면,, 요즘은 JSON이라는 데이터 구조 포맷으로 내려준다. 클라이언트에게 데이터를 전달한다. ViewJS, React 를 사용할 때도 사용한다. 그리고 서버끼리 통신할 때도 사용한다. 어떤 데이터가 왔다갔다 하는지가 중요하지 화면은 중요하지 않기 때문이다. 



## 1강. 정적 컨텐츠

기본적으로 스프링 부트는 `/public`이나 `/static`에서 정적 컨텐츠를 찾아서 클라이언트에게 보낸다. 접근할 때는 확장자까지 파일명 그대로 주소창에 쳐야 한다.

웹브라우저에서  `localhost:8080/hello-static.html`를 치니까 제일 처음에 내장 톰캣 서버가 요청을 받고, 스프링에게 이 주소를 넘긴다. 그럼 스프링은 일단 컨트롤러쪽에서 hello-static이 있는지 찾아본다. 즉 **컨트롤러가 우선순위를 가진다**. 그런데 hello-static 관련(매핑이 된) 컨트롤러가 없다. 그럼 resources/static에서 hello-static을 찾는다. 정적 컨텐츠는 이런식으로 동작한다.



## 2강. MVC와 템플릿 엔진

MVC: Model, View, Controller

과거에는 Controller와 View가 분리되지 않았다. 예전에는 JSP로 처리했는데 이걸 Model1 방식이라고 한다. 지금은 MVC 스타일로 많이 한다. 왜냐하면 개발을 할 때 관심사를 분리해야 한다. 역할과 책임. View는 화면을 그리는 데 모든 역량을 집중해야 한다.  그리고 Controller와 Model 같은 경우에는 비즈니스 로직을 처리하거나 내부적인것을 처리하는 데 집중해야 하다. 그래서 Model, View, Controller를 쪼개놨다. 과거에는 컨트롤러에서 모든 것을 처리했다.

**hello.hellospring.controller.HelloController**

```java
@GetMapping("hello-mvc")
public String helloMvc(@RequestParam("name") String name, Model model) {
  model.addAttribute("name", name);
  return "hello-template";
}
```

> RequestParam 에서 required 속성은 값이 필수값인지 아닌지를 

```html
<p th:text="'hello' + ${name}">hello! empty</p>
```

타임리프 템플릿의 장점은 html 파일을 그대로 쓰기 때문에 파일을 서버 없이 바로 열어봐도 껍데기를 볼 수 있다. `hello empty`. 그런데 이게 템플릿 엔진으로 동작을 하면(실제 서버를 타서 돌면) `hello name`이 나온다. 서버 없이 html을 마크업하는 분들이 볼 때 값을 적어놓고 볼 수 있도록 할 수 있다. 

### 동작 방식

웹 브라우저에서 `localhost:8080/hello-mvc`로 요청을 보내면 스프링 부트를 띄울 때 같이 띄우는 내장 톰캣서버를 거친다. 그러면 톰캣 서버는 `hello-mvc`라는 게 왔어! 라고 스프링 컨테이너에게 전달한다. 그럼 스프링 컨테이너는 `helloController`의 메서드에 매핑이 되어 있는 것을 확인하고 이를 호출한다. 리턴해줄 때 `hello-template`, 모델에는 `spring`이라는 문자열을 `name`이라는 이름으로 담아 전달한다. 그러면 뷰 리졸버가 `template/hello-template.html`을 찾아서 `Thymeleaf` 템플릿 엔진에 처리해달라고 넘긴다. 그러면 Thymeleaf가 렌더링해서 HTML로 변환한다. 그러면 스프링 컨테이너에서 톰캣서버로, 그리고 웹 브라우저로 변환된 HTML을 보내준다. 



## 3강. API

정적 컨텐츠 방식을 제외하면 View를 찾아서 템플릿 엔진을 통해서 HTML을 웹 브라우저에게 넘겨주는 방법이 있고. 그리고 API를 쓰는 방식을 한다. 그러니까 서버가 HTML을 내리는지, 데이터를 내리는지의 문제이다.

```java
@GetMapping("hello-string")
@ResponseBody
public String helloString(@RequestParam("name") String name) {
  return "hello " + name;
}
```

이전에 템플릿 엔진은 화면을 가지고 View 라는 템플릿에서 이를 조작하는 방식이라면 `@ResponseBody` 애노테이션을 쓰면 문자 **그대로** 내려간다. 이 방식은 거의 사용하지 않는다.

```java
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
```



