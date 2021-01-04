include는 그냥 서비스를 호출하는 것이다. 요청하는 것이 아니다. `service()`를 호출해서 inlcude를 한다. dispatcher에서는 getPathInfo를 pathInfo를 해봐야 계속 `/project/detail`이겠지. `service`에서 `pathInfo`를 해봐야 `/task/list`가 아니라 `project/detail`일 것이다.

그럼 다시 pathInfo로 간다. pathInfo하니까 DisptacherServlet은 TaskListServlet으로 가는 게 아니라 ProjectDetailServlet으로 간다. 그리고 다시 `/project/detail.jsp`를 인클루드하고 다시 `/app/task/list`를 주면서 DispatcherServlet을 호출한다. 여기서 getPath를 하면 다시 `/project/detail`이어서 다시 ProjectDetailServlet. 그래서 무한루프에 빠지게 되는 것이다. 

**이를 해결하는 방법이 없다**. 이것은 FrontController도 Servlet이고 PageController도 Servlet이다. incl



include할 때 SerlvetURL을 `/app/project/detail` 이렇게 하지 말고 `/project/detail` 이런 식으로 하자.

### FrontController 기반 요청 처리 기본

Client 요청이 DispatcherServlet으로 오는데 DispatcherServlet이 서블릿으로 가고, 그리고 Servlet이 Service를 사용해서 Data를 준비한다. 이걸 리턴해서 JSP에게 보낸다. JSP가 응답을 하면서 JSP가 출력한다. 

- 다른 페이지 컨트롤러에서 등록한 프로퍼티 에디터는 사용할 수 없다. 각 페이지 컨트롤러마다 자신이 사용할 프로퍼티 에디터를 등록한다. 따라서 만약 여러 페이지 컨트롤러에서 공통으로 사용하는 프로퍼티 에디터는 글로벌 프로퍼티 에디터를 사용하는 것이 좋다. 





### 헤더 꺼내기: `@RequestHeader`

```java
// 요청 핸들러의 아규먼트 - @RequestHeader
@Controller
@RequestMapping("/c04_6")
public class Controller04_6 {

  // 클라이언트의 HTTP 요청 헤더를 받고 싶으면
  // request handler의 아규먼트 앞에 @RequestHeader(헤더명) 애노테이션을 붙여라!

  // 테스트:
  // http://.../c04_6/h1
  @GetMapping("h1")
  @ResponseBody
  public void handler1(//
      PrintWriter out, //
      @RequestHeader("Accept") String accept, //
      @RequestHeader("User-Agent") String userAgent) {

    out.printf("Accept=%s\n", accept);
    out.printf("User-Agent=%s\n", userAgent);

    if (userAgent.matches(".*Edg.*")) {
      out.println("Edge");
    } else if (userAgent.matches(".*Chrome.*")) {
      out.println("chrome");
    } else if (userAgent.matches(".*Safari.*")) {
      out.println("safari");
    } else if (userAgent.matches(".*Firefox.*")) {
      out.println("firefox");
    } else {
      out.println("etc");
    }
  }

  public static void main(String[] args) {
    String str =
        "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.103 Safari/537.36";
    // String str = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_6) AppleWebKit/605.1.15 (KHTML,
    // like Gecko) Version/12.1 Safari/605.1.15";
    // String str = "AA BB Aa Ba $12,000";

    // 정규 표현식으로 패턴을 정의한다.
    // String regex = "Chrome";
    // String regex = "Chrome.*Safari";
    String regex = "[^(Chrome.*)]Safari";
    Pattern pattern = Pattern.compile(regex);

    // 주어진 문자열에서 패턴과 일치하는 정보를 찾는다.
    Matcher matcher = pattern.matcher(str);

    // 일치 여부를 확인한다.
    if (matcher.find()) {
      System.out.println("OK!");
      // for (int i = 1; i < matcher.groupCount(); i++) {
      // System.out.println(matcher.group(1));
      // }
    } else {
      System.out.println("NO!");
    }

  }

}
```

- Chrome 브라우저는 사파리를 포함한다. 그래서  Chrome이 Safari 앞에 와야 한다.
- Edge 브라우저는 내부적으로 Chrome과 Safari 기술을 사용한다. 그래서 순서가 중요하다. 
- `@RequestHeader` 애노테이션을 사용하면 헤더를 꺼낼 수 있다.



### 쿠키값 꺼내기: `@CookieValue('쿠키이름')`

```java
@Controller
@RequestMapping("/c04_7")
public class Controller04_7 {

  // 클라이언트가 보낸 쿠키 꺼내기
  // => @CookieValue(쿠키명) 애노테이션을 request handler의 아규먼트 앞에 붙인다.

  // 테스트:
  // http://.../c04_7/h1
  @GetMapping("h1")
  @ResponseBody
  public void handler1(//
      PrintWriter out, //
      HttpServletResponse response//
  ) {
    // 이 메서드에서 쿠키를 클라이언트로 보낸다.
    try {
      // 쿠키의 값이 ASCII가 아니라면 URL 인코딩 해야만 데이터가 깨지지 않는다.
      // URL 인코딩을 하지 않으면 ? 문자로 변환된다.
      response.addCookie(new Cookie("name1", "AB가각"));
      response.addCookie(new Cookie("name2", URLEncoder.encode("AB가각", "UTF-8")));
      response.addCookie(new Cookie("name3", "HongKildong"));
      response.addCookie(new Cookie("age", "30"));

    } catch (Exception e) {
      e.printStackTrace();
    }

    out.println("send cookie2!");
  }

  // 테스트:
  // http://.../c04_7/h2
  @GetMapping(value = "h2", produces = "text/plain;charset=UTF-8")
  @ResponseBody
  public String handler2(//
      @CookieValue(value = "name1", required = false) String name1, //
      @CookieValue(value = "name2", defaultValue = "") String name2, //
      @CookieValue(value = "name3", defaultValue = "") String name3, //
      @CookieValue(value = "age", defaultValue = "0") int age // String ===> int 자동 변환
  ) throws Exception {

    //
    // 1) URLEncoder.encode("AB가각", "UTF-8")
    // ==> JVM 문자열은 UTF-16 바이트 배열이다.
    // 0041 0042 ac00 ac01
    // ==> UTF-8 바이트로 변환한다.
    // 41 42 ea b0 80 ea b0 81
    // ==> 8비트 데이터가 짤리지 않도록 URL 인코딩으로 7비트화 시킨다.
    // "AB%EA%B0%80%EA%B0%81"
    // 41 42 25 45 41 25 42 30 25 38 30 25 45 41 25 42 30 25 38 31
    // ==> 웹 브라우저에서는 받은 값을 그대로 저장
    //
    // 2) 쿠키를 다시 서버로 보내기
    // ==> 웹 브라우저는 저장된 값을 그대로 전송
    // "AB%EA%B0%80%EA%B0%81"
    // 41 42 25 45 41 25 42 30 25 38 30 25 45 41 25 42 30 25 38 31
    // ==> 프론트 컨트롤러가 쿠키 값을 꺼낼 때 자동으로 URL 디코딩을 수행한다.
    // 즉 7비트 문자화된 코드를 값을 원래의 8비트 코드로 복원한다.
    // 41 42 ea b0 80 ea b0 81
    // ==> 디코딩 하여 나온 바이트 배열을 UTF-16으로 만든다.
    // 문제는 바이트 배열을 ISO-8859-1로 간주한다는 것이다.
    // 그래서 UTF-16으로 만들 때 무조건 앞에 00 1바이트를 붙인다.
    // 0041 0042 00ea 00b0 0080 00ea 00b0 0081
    // 그래서 한글이 깨진 것이다.
    //
    // 해결책:
    // => UTF-16을 ISO-8859-1 바이트 배열로 변경한다.
    // 41 42 ea b0 80 ea b0 81
    byte[] originBytes = name2.getBytes("ISO-8859-1");

    // => 다시 바이트 배열을 UTF-16으로 바꾼다.
    // 이때 바이트 배열이 UTF-8로 인코딩된 값임을 알려줘야 한다.
    // 0041 0042 ac00 ac01
    String namex = new String(originBytes, "UTF-8");

    return String.format(//
        "name1=%s\n name2=%s\n name2=%s\n name3=%s\n age=%d\n", //
        name1, name2, namex, name3, age);
  }
}
```

- 쿠키가 없을 때 에러가 나는 것을 방지하기 위한 방법

  - `required=false`로 설정한다.

    `defaultValue="~~"`를 설정한다. 그럼 값이 없을 때 이 값을 사용한다.

- int인 경우는 required=false로 할 수 없다. null을 int에 넣을 수 없기 때문이다. 그래서 `defaultValue="0"` 이렇게 설정해야 한다. 그래야 숫자 0으로 바꿀 수 잇기 때문이다. 

- 이때 url 인코드ㅎ했다면 url 디코딩해야 한다.



`@Bean`을 붙이지 않으면 프론트 컨트롤러가 객체를 생성하지 않고 무시한다.

```java

```

DispatcherServlet에 multipart를 설정해주면 Spring의 MultipartResolver가 작동ㅈ되지 않는다. 만약 Spring 방식으로 파일 업로드를 처리하고 싶다면, AppConfig.java의 MultipartResolver를 추가해야 한다. 

스프링 방식으로 적용했으면 서블릿 3.0 표준에서 제안한 Part photo 이건 안먹는다. MultipartFile photo를 사용해야 한다. 

스프링

```java
// 요청 핸들러의 리턴 값 - 콘텐트를 직접 리턴하기
@Controller
@RequestMapping("/c05_1")
public class Controller05_1 {

  // 테스트:
  // http://localhost:8080/java-spring-webmvc/app1/c05_1/h1
  @GetMapping("h1")
  @ResponseBody
  public String handler1() {
    // 리턴 값이 클라이언트에게 보내는 콘텐트라면
    // 메서드 선언부에 @ResponseBody를 붙인다.
    // => 붙이지 않으면 프론트 컨트롤러는 view URL로 인식한다.
    // => 출력 콘텐트는 브라우저에서 기본으로 HTML로 간주한다.
    // 단 한글은 ISO-8859-1 문자표에 정의된 코드가 아니기 때문에
    // 클라이언트로 보낼 때 '?' 문자로 바꿔 보낸다.
    return "<html><body><h1>abc가각간</h1></body></html>";
  }

  // 테스트:
  // http://localhost:8080/java-spring-webmvc/app1/c05_1/h2
  // => 리턴되는 콘텐트의 MIME 타입과 charset을 지정하고 싶다면
  // 애노테이션의 produces 프로퍼티에 설정하라.
  @GetMapping(value = "h2", produces = "text/html;charset=UTF-8")
  @ResponseBody
  public String handler2() {
    return "<html><body><h1>abc가각간<h1></body></html>";
  }

  // 테스트:
  // http://localhost:8080/java-spring-webmvc/app1/c05_1/h3
  @GetMapping("h3")
  @ResponseBody
  public String handler3(HttpServletResponse response) {

    // HttpServletResponse에 대해 다음과 같이 콘텐트 타입을 설정해봐야 소용없다.
    response.setContentType("text/html;charset=UTF-8");

    return "<html><body><h1>abc가각간<h1></body></html>";
  }

  // 테스트:
  // http://localhost:8080/java-spring-webmvc/app1/c05_1/h4
  @GetMapping("h4")
  public HttpEntity<String> handler4(HttpServletResponse response) {
    // HttpEntity 객체에 콘텐트를 담아 리턴할 수 있다.
    // 이 경우에는 리턴 타입으로 콘텐트임을 알 수 있기 때문에
    // @ResponseBody 애노테이션을 붙이지 않아도 된다.

    HttpEntity<String> entity = new HttpEntity<>(//
        "<html><body><h1>abc가각간<h1></body></html>");

    // 이 경우에는 출력할 때 ISO-8859-1 문자표의 코드로 변환하여 출력한다.
    // 그래서 한글은 ? 문자로 변환된다.

    return entity;
  }

  // 테스트:
  // http://localhost:8080/java-spring-webmvc/app1/c05_1/h5
  @GetMapping(value = "h5", produces = "text/html;charset=UTF-8")
  public HttpEntity<String> handler5(HttpServletResponse response) {
    // 한글을 제대로 출력하고 싶으면 위 애노테이션의 produces 속성에 콘텐트 타입을 지정한다.
    HttpEntity<String> entity = new HttpEntity<>(//
        "<html><body><h1>abc가각간<h1></body></html>");

    return entity;
  }

  // 테스트:
  // http://localhost:8080/java-spring-webmvc/app1/c05_1/h6
  @GetMapping("h6")
  public HttpEntity<String> handler6(HttpServletResponse response) {
    // 한글을 제대로 출력하고 싶으면,
    // 응답 헤더에 직접 Content-Type을 설정할 수 있다.

    HttpHeaders headers = new HttpHeaders();
    headers.add("Content-Type", "text/html;charset=UTF-8");

    HttpEntity<String> entity = new HttpEntity<>(//
        "<html><body><h1>abc가각간<h1></body></html>", //
        headers);

    return entity;
  }

  // 테스트:
  // http://localhost:8080/java-spring-webmvc/app1/c05_1/h7
  @GetMapping("h7")
  public ResponseEntity<String> handler7(HttpServletResponse response) {
    // HttpEntity 대신에 ResponseEntity 객체를 리턴 할 수 있다.
    // 이 클래스의 경우 응답 상태 코드를 추가하기 편하다.

    HttpHeaders headers = new HttpHeaders();
    headers.add("Content-Type", "text/html;charset=UTF-8");

    // 이렇게 응답 헤더를 따로 설정하는 방법이 존재하는 이유는
    // 다음과 같이 임의의 응답 헤더를 추가하는 경우가 있기 때문이다.
    headers.add("BIT-OK", "ohora");

    ResponseEntity<String> entity = new ResponseEntity<>(//
        "<html><body><h1>abc가각간<h1></body></html>", //
        headers, //
        HttpStatus.OK // 응답 상태 코드를 설정할 수 있다.
    );

    return entity;
  }
}
```



데이터 저장할 일이 없으면 그냥 URL 문자열을 리턴한다. 

- `RequestMappingHandlerMapping`





Map도 넣을 수 있고 Model 객체도 넣을 수 있다. 





**App2Config**

```java
@Bean
public ViewResolver viewResolver() {
  InternalResourceViewResolver vr = new InternalResourceViewResolver("/WEB-INF/jsp2/", ".jsp");
  return vr;
}
```



리턴값이 없으면 요청 URL(`/c01_1/h2`)을 리턴값으로 사용한다. 따라서 ViewResolver가 계산한 최종 URL은 `/WEB-INF/jsp2/c01_1/h2.jsp`이다. 



### ViewResolver

- 실행할 뷰를 찾는 일을 한다.
- 페이지 컨트롤러가 리턴한 뷰 이름에 해당하는 뷰 컴포넌트를 찾는 역할
- ResourceBundleViewResolver
  - `.properties`에서 뷰 이름에 해당하는 콤포넌트의 URL을 찾는다.
- InternalResourceViewResolver
  - 뷰를 실행하는 일을 한다.
  - 템플릿 엔진을 





값을 넘기는 방식을 쿼리스트링에서 바꾸기 

- `news/17592`: 값을 넘기는 요새 트랜드
- `news?no=1293`: 값을 넘기는 전통적인 방식



## 인터셉터 만들기

ServletContainer와 DispatcherServlet 사이에 꼽는 것이 필터이다. DispatcherServlet과 Service, ViewResolver 사이에 꼽는 것이 인터셉터이다. 



AOP 는 모든 객체에 대해서 다 꼽을 수 있다. 메서드 호출 앞뒤로 다 꼽을 수 있다. 주로 서비스 DAO에 넣을 때 많이 쓴다. 



- 프론트 컨트롤러와 뷰 컴포넌트 사이에
- 프론트 컨트롤러와 페이지 컨트롤러 사이에 코드를 삽입하는 기술

언제든지 기능을 삽입했다가 필요 없으면 빼면 된다. 필요할 때 기능을 넣고, 



인터셉터를 배치하기

컨트롤러의 메서드를 호출하기 전에 prehandle 그리고 리턴하기 전에 posthandle . 그리고 



이것도 배치해야 한다.

- `InterceptorRegistry`: 인터셉터 등록기
- 등록기.추가(new Controller04_1_Interceptor1())



디스패처 서블릿이 addInterceptors 메서드를 호출한다. 이 때 인터셉터 등록기를 디스패처 서블릿으로부터 받는다. 그리고 메서드 안에서 등록기에 Interceptor 객체를 생성하여 등록한다. 

이제부터 모든 요청이 올 때마다 interceptor postHandler, preHandler, 

아무것도 지정하지 않으면





**RootConfig**

``` java
@ComponentScan(
    value="com.eomcs.pms", 
    excludeFilters= {@Filter(type= FilterType.REGEX, pattern="com.eomcs.pms.web.*")
    })
public class RootConfig {

}
```

Spring WebMVC 프레임워크에서 ContextLoaderListener가 사용할 Java Config이다. 웹 컴포넌트가 공유할 객체를 선언한다. 예를 들어 DAO, Service 등이 속한다.

**AppWebApplicationInitializer**

DispatcherServlet은 페이지 컨트롤러나 인터셉터를 만든다. 

DispatcherServlet의 IoC Container가 사용하는 설정 파일인 AppWebConfig에 웹 관련 설정을 모두 넣는다.



resolver: 해결사 