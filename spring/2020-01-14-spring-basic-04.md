# Section 4. 스프링 빈과 의존 관계

## 1강. 컴포넌트 스캔과 자동 의존관계 설정

화면을 만들기 위해서는 컨트롤러와 뷰 템플릿이 필요하다. 이제 MemberController가 MemberService를 통해 회원가입을 하고 데이터를 조회해야 한다. 이것을 MemberController가 MemberService에 의존한다고 이야기한다. 이 작업을 스프링 스럽게 해보겠다.

회원 컨트롤러가 회원서비스와 회원 리포지토리를 사용할 수 있게 의존관계를 준비하자.

**MemberController**

`@Controller` 애노테이션을 붙이면 어떻게 될까? 스프링을 띄울 때 처음에 스프링 컨테이너라는 상자가 생긴다. Controller 애노테이션이 붙어 있으면 해당 객체를 생성하여 스프링 컨테이너에 넣어 놓고 스프링이 이를 관리하게 된다. 이를 스프링 컨테이너에서 스프링 빈이 관리된다고 말한다. 

- `private final MemberService memberService = new MemberService();`: 스프링이 관리를 하게 되면 스프링 컨테이너에 다 등록을 하고 클래스는 스프링 컨테이너로부터 받아서 사용하도록 바꿔야 한다. `new` 연산자로 객체를 직접 생성하면, MemberController뿐만 아니라 다른 여러 컨트롤러들이 MemberService를 가져다 쓸 수 있기 때문이다. 예를 들어 주문 컨트롤러에서 회원 서비스를 사용할 수 있다. 그러나 MemberService는 유용한 기능들이 들어 있는 클래스이기 때문에 굳이 객체를 여러개 생성할 필요 없이 한 번 생성해서 공유하면 된다. 그래서 스프링 컨테이너에 등록을 해서 사용하는 것이 좋다. 등록은 한 번만 하면 된다. 그 외에 여러가지 효과를 많이 볼 수 있는데 뒤에서 설명할 것이다. 
- 연결은 생성자로 만들고 `@Autowired`를 붙인다. `@Controller`가 붙은 `MemberController`는 스프링이 뜰 때 생성이 될 것이다. 그때 생성자를 호출하는데 생성자에 `@Autowired`가 붙어 있으면 스프링이 스프링 컨테이너에 들어 있는 MemberService를 가져와 연결을 시켜준다. 

```java
@Controller
public class MemberController {
  //private final MemberService memberService = new MemberService();
  private final MemberService memberService;

  @Autowired
  public MemberController(MemberService memberService) {
    this.memberService = memberService;
  }
}
```

- **생성자에 `@Autowired`**가 있으면 **스프링이 연관된 객체를 스프링 컨테이너에서 찾아서 넣어준다**. 이렇게 **객체 의존관계를 외부에서 넣어주는 것**을 **DI (Dependency Injection), 의존성 주입**이라고 한다. 
- 이전 테스트에서는 개발자가 직접 주입했고, 여기서는 `@Autowired`에 의해 스프링이 주입해준다.

이대로 실행하면 다음과 같은 오류가 발생한다.

```
Consider defining a bean of type 'hello.hellospring.service.MemberService' in
your configuration.
```

이 오류는 **memberService**가 스프링 빈으로 등록되어 있지 않아서 생긴 문제이다. 현재 MemberService는 순수한 자바 클래스이다. 스프링이 MemberService를 알 수 있는 방법이 없다. 

그럼 어떻게 해야 할까?  스프링 빈으로 등록해야 한다. 이에는 2가지 방법이 있다.

- **컴포넌트 스캔**과 **자동 의존관계 설정**
  - `@Controller`, `@Service`, `@Repository`
- 자바 코드로 직접 스프링 빈 등록하기

**컴포넌트 스캔 원리**

- `@Component` 애노테이션이 있으면 스프링 빈으로 자동 등록된다. 
- `@Controller` 컨트롤러가 스프링 빈으로 자동 등록된 이유도 컴포넌트 스캔 때문이다.

**`@Component`를 포함**하는 다음 애노테이션도 스프링 빈으로 자동 등록된다.

- `@Controller`
- `@Service`
- `@Repository`

포함한다는 것이 어떤 뜻일까? `Service` 안에 들어가보면 다음과 같이 `Component`라는 애노테이션이 등록되어 있다. `Service`, `Repository`, `Controller` 애노테이션은 `@Component`에 특수화된 케이스이다. 

```java
This annotation serves as a specialization of @Component, allowing for implementation classes to be autodetected through classpath scanning.
Since:
2.5
See Also:
Component, Repository
Author:
Juergen Hoeller

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface Service {
```

스프링이 올라올 때 `@Component` 관련된 애노테이션이 있으면 그 객체들을 생성을 해서 스프링 컨테이너에 등록한다. `@Autowired`는 연관관계를 만들어준다. 따라서 `helloController`가 `memberService`를, `memberService`가 `memberRepository`를 사용할 수 있게 만들어준다. 

`main` 메서드가 존재하는 클래스(실제 실행되는 클래스)가 위치하는 패키지와 그 하위 패키지를 뒤져서 스프링 빈으로 등록한다. 만약 다른 패키지에 `@Component`가 붙은 클래스가 있다 하더라도 이는 기본적으로 컴포넌트 스캔의 대상이 아니다. 

**회원 서비스 스프링 빈 등록**

```java
@Service
public class MemberService {

  private final MemberRepository memberRepository;

  @Autowired
  public MemberService(MemberRepository memberRepository) {
    this.memberRepository = memberRepository;
  }
}
```

> 생성자에 `@Autowired`를 사용하면 객체 생성 시점에 스프링 컨테이너에서 해당 스프링 빈을 찾아서 주입한다. **생성자가 1개만 있으면 `@Autowired`는 생략할 수 있다.** 

**회원 리포지토리 스프링 빈 등록**

```java
@Repository
public class MemoryMemberRepository implements MemberRepository {
```

컨트롤러 - 서비스 - 리포지토리 구조 자체가 굉장히 정형화된 패턴이다. 컨트롤러가 외부 요청을 받고, 서비스에서 비즈니스 로직을 만들고, 리포지토리에서 데이터를 저장한다. 이렇게 해 놓으면 스프링이 컨트롤러, 서비스, 리포지토리를 생성해 컨테이너에 등록한다. 

**스프링 빈 등록**

![image](https://user-images.githubusercontent.com/50407047/104813933-890e2b80-584f-11eb-852a-96f69e5e234e.png)

`memberService`와 `memberRepository`가 스프링 컨테이너의 스프링 빈으로 등록되었다.

스프링은 스프링 컨테이너에 스프링 빈을 등록할 때, 기본으로 **싱글톤으로 등록한다**(유일하게 하나만 등록해서 공유한다.) 따라서 **같은 스프링 빈이면 모두 같은 인스턴스**이다. 설정으로 싱글톤이 아니게 설정할 수 있지만, 특별한 경우를 제외하면 대부분 싱글톤을 사용한다.

## 2강. 자바 코드로 직접 스프링 빈 등록하기

회원 서비스와 회원 리포지토리의 `@Service`, `@Repository`, `@Autowired` 애노테이션을 제거하고 진행한다. 

- `@Bean`: 스프링 빈을 만든다는 의미이다. 
- 스프링이 뜰 때 SpringConfig를 읽고 `@Bean` 애노테이션이 달린 메서드를 호출해서 스프링 빈에 등록한다. 그러면서 MemberService는 memberRepository를 사용하도록 만들었기 때문에 스프링 빈에 등록된 memberRepository를 넣어준다. 

```java
package hello.hellospring;

@Configuration
public class SpringConfig {
  @Bean
  public MemberService memberService() {
    return new MemberService(memberRepository());
  }

  @Bean
  public MemberRepository memberRepository() {
    return new MemoryMemberRepository();
  }
}
```

- **여기서는 향후 메모리 리포지토리를 다른 리포지토리로 변경할 예정이므로, 컴포넌트 스캔 방식 대신에 자바 코드로 스프링 빈을 설정한다.** 자바 코드로 스프링 빈을 설정하면 MemoryMemberRepository를 다른 구현체로 교체할 때 기존 코드의 변경 없이 교체할 수 있다. 그처 SpringConfig의 MEmberRepository 생성자에서 `new DBMemberRepository`로 변경하기만 하면 된다. **컴포넌트 스캔을 사용하면 구현체 교체 시 기존 코드를 변경해야 한다.**

```java
@Controller
public class MemberController {
  private final MemberService memberService;

  @Autowired
  public MemberController(MemberService memberService) {
    this.memberService = memberService;
  }
}
```

- Controller는 어쩔 수 없다. Controller는 스프링이 어차피 관리하는 것이기 때문에 `@Controller` 애노테이션을 붙이면 컴포넌트 스캔에 걸리기 때문에 `@Autowired`를 생성자에 붙인다.

- XML로 설정하는 방식도 있지만 최근에는 잘 사용하지 않는다.

- DI에는 **필드 주입**, **setter 주입**, **생성자 주입**의 3 가지 방법이 있다. 의존관계가 실행 중에 동적으로 변하는 경우는 거의 없으므로 **생성자 주입을 권장한다.**

  **필드 주입**: 중간에 바꿀 수 있는 방법이 없다.

  ```java
  @Autowired private MemberService memberService;
  ```

  **setter 주입**: setter를 만들 경우 의존객체 setter가 public하게 노출이 되기 때문에 누군가가 setter를 사용해 의존객체를 변경하도록 열려 있다. 사실 실제로 의존객체를 변경하는 일은 거의 일어나지 않는다. 호출하면 안되는 메서드는 호출할 수 없게 만드는 것이 개발 시에 중요하기 때문에 setter 주입은 지양된다.

  ```java
  @Autowired
  public void setMemberService(MemberService memberSerivce) {
    this.memberService = memberService;
  }
  ```

  **생성자 주입**: 처음에 조립될 때(스프링 컨테이너에 올라가고 셋팅이 되는 시점) 의존객체가 한 번 주입되고 끝난다. 그 다음에는 변경을 하지 못하도록 막아버릴 수 있다. 

- 실무에서는 주로 **정형화된 컨트롤러, 서비스, 리포지토리 같은 코드는 컴포넌트 스캔을 사용**한다. 그리고 **정형화되지 않거나, 상황에 따라 구현 클래스를 변경해야 하면 설정을 통해 스프링 빈으로 등록**한다.

- `@Autowired`를 통한 DI는 `helloController`, `memberService` 등과 같이 스프링이 관리하는 객체에서만 동작한다. 스프링 빈으로 등록하지 않고 내가 직접 생성한 객체에서는 동작하지 않는다.