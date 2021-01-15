## 급하게 알아보는 스프링 기반 기술

### Servlet

```java
public abstract class HttpServlet extends GenericServlet { //... 
  protected void doGet(HttpServletRequest req, HttpServletResponse resp){...} 
  protected void doPost(HttpServletRequest req, HttpServletResponse resp){...} //... 
}
```

**Servlet**은 자바를 사용하여 **웹 페이지를 동적으로 생성하는** 서버측 프로그램 혹은 그 사양이다. **웹 서버 프로그래밍을 하기 위한 사양을 갖춘 자바 코드**이다.  `HttpServlet` 클래스를 상속한 클래스이고, 서블릿은 `Servlet Container`에 의해 관리, 실행된다. **HTTP Server + Servlet Container**가 웹 서버 역할에 필요한 대부분을 구현해두었고, 개발자는 HTTP 요청을 받아 처리하는 부분을 구현한다. Http 웹 서버 기능 동작(요청과 응답)이 가능하다.

### 톰캣

**웹 애플리케이션 서버(WAS)** 중 하나로 **`Servlet Container`, `Servlet Engine`**이라고 표현할 수 있다. 자바 웹 프로그래머가 작성한 Servlet을 관리한다. Servlet을 관리한다는 것은 **클라이언트가 어떤 요청을 했을 때 어떤 Servlet을 실행할 것인지 제어해준다**는 것이다. **톰캣**은 `Servlet`을 관리하는 주체이기 때문에 **Servlet**(HttpServlet 클래스를 상속한 클래스)이어야 한다.

### web.xml

WAS는 Servlet을 **생성**하고 **어떤 Servlet이 어떤 요청을 담당할 것인지(mapping)**, **어떤 요청이 인증과정을 거칠 것인지** 등의 **제어** 기능을 지원해준다. 그러기 위해서는 **WAS**에게 **Servlet에 대한 정보를 전달**해야하는데 이때 쓰이는 파일이 `web.xml(Deployment Descriptor)`이다. Servlet 3.0부터는 web.xml에서만 Servlet에 대해 정의하지 않고, Java Config(자바 소스 설정)으로도 가능하다. (요즘은 학습할 때 빼고는 거의 Java Config으로 설정한다고 한다)

### DispatcherServlet

**Servlet Container(ex: tomcat)으로부터 들어오는 요청을 관제하는 컨트롤러이다.** 즉 Spring MVC에서 요청을 받는 부분이다. `Servlet Container`에 **여러 매핑 정보를 가진 여러 Servlet을 생성하고 관리할 수도 있지만**, **일반적으로 Servlet Container에는 `DispatcherServlet`만 등록**해놓고 **DispatcherServlet이 HandlerMapping을 통해 적절한 Controller로 매핑**하도록 한다.

### Servlet Filter

**Servlet 실행 전, 후**에 **어떤 작업**을 할 때 사용한다. **`Interceptor`**와는 실행시점(**handler 전, 후**)에 차이가 있다. **`Filter`는 Servlet Container에 등록**하고 **`Interceptor`는 스프링 컨테이너에 등록**한다.

|              | **Filter**        | **Interceptor** |
| ------------ | ----------------- | --------------- |
| **실행시점** | Servlet 실행 전후 | Handler 전후    |
| **등록위치** | Servlet Container | 스프링 컨테이너 |

### Servlet Context

Servlet 단위로 생성되는 Context이다. Servlet Container에 DispatcherServlet과 같은 Servlet을 등록하면 해당 Servlet이 갖는 하나의 컨테이너 역할을 하는 객체이다. 스프링을 이용하는 경우, 스프링 컨테이너(Application Context)를 부모 Context로 사용한다. 따라서 Application Context와 Servlet Context에 같은 id로 된 Bean이 있으면 ServletContext에 있는 Bean을 우선 사용한다. 

### Application Context

Root Context이자 스프링에 의해 생성되는 Bean에 대한 **Spring IoC Container**이다. BeanFactory를 상속받는 Context이다. **여러 Servlet에서 공통으로 사용할 Bean을 등록하는 Context이다.** `@Transactional`으로 트랜잭션을 이용해야 할 때 **ApplicationContext에 있는 Service에서만 트랜잭션이 정상 작동**한다. 

> 그럼 ApplicationContext에 있지 않는 Service에는 트랜잭션을 어떻게 사용해야 하는가? 그리고 ApplicationContext에 두지 않고 다른 곳? (Servlet Context?)에 두는 서비스 객체가 있는가? 그 이유는 무엇인가?

### WebApplicationInitializer

`Servlet Context`를 프로그래밍적으로 설정하기 위한 인터페이스이다(web.xml을 대체하기 위함). 스프링에 `ServletContainerInitializer`를 구현한 클래스(SpringServletContainerInitializer)가 있고, 그 클래스가 **`WebApplicationInitializer` 인터페이스를 구현한 클래스를** 찾아 **초기화 작업을 위임**하도록 구현해놨다. 

### ContextLoaderListener

**Servlet Container**에 **루트 애플리케이션 컨텍스트(`Application Context`)를 등록하는 방법을 제공**한다. Servlet Cotainer의 시작과 종료 시에 발생하는 이벤트를 처리하는 리스너를 등록하기 위해 `ServletContextListener` 인터페이스를 구현한 리스너를 사용하는데 그 구현체가 `ContextLoaderListener`이다.

Application Context에 대한 실제 초기화 작업을 진행한다.

이 리스너만 등록하면 자동으로 디폴트 루트 애플리케이션 컨텍스트(XmlWebApplicationContext)를 생성해준다.

### RequestContextListener

현재 스레드에 요청을 노출하는 Servlet Listener이다.

### AnnotationConfigWebApplicationContext

Component 클래스를 입력값으로 받는 `WebApplicationContext` 인터페이스의 구현체이다. 패키지 경로를 스캔하여 컴포넌트를 Context에 등록한다. `AnnotationConfigWebApplication` 인스턴스 스프링에서 DispatcherServlet이나 ContextLoaderListener에 주입되는 경우에 많이 사용한다. 

