# 스프링 빈과 의존 관계

## 컴포넌트 스캔과 자동 의존관계 설정

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

- 컴포넌트 스캔과 자동 의존관계 설정
- 자바 코드로 직접 스프링 빈 등록하기

**컴포넌트 스캔 원리**

- `@Component` 애노테이션이 있으면 스프링 빈으로 자동 등록된다. 
- `@Controller` 컨트롤러가 스프링 빈으로 자동 등록된 이유도 컴포넌트 스캔 때문이다.

`@Component`를 포함하는 다음 애노테이션도 스프링 빈으로 자동 등록된다.

- `@Controller`
- `@Service`
- `@Repository`

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

**스프링 빈 등록 이미지**



