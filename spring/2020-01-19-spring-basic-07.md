# Section 7. AOP

## 1강. AOP가 필요한 상황

- 모든 메서드의 호출 시간을 측정하고 싶다면?
- 공통 관심 사항(cross-cutting concern) vs 핵심 관심 사항(core concern)
- 회원 가입 시간, 회원 조회 시간을 측정하고 싶다면?

![image](https://user-images.githubusercontent.com/50407047/105120679-0cf93980-5b16-11eb-8ab3-0e2076e9bc8d.png)

위와 같이 시간 측정 로직을 각 메서드에 포함시켜서 작성할 수밖에 없다. 만약 초단위에서 밀리초단위로 변경한다고 한다면 모든 메서드의 시간 측정 로직을 다 변경해야 할 것이다. 

> 처음 메서드를 실행할 때는 로딩할 것(클래스 메타데이타 등등)이 있어서 오래 걸리지만, 그 다음부터는 빨라진다. 그래서 실무에서는 서버에 올리고 초반에 이것저것 호출해서 warm-up을 한번 해놓는다. 또한 DB에 데이터가 많으면 조회 시간이 더 걸린다.

**MemberService 회원 조회 시간 측정 추가**

```java
@Transactional
public class MemberService {

  private final MemberRepository memberRepository;

  @Autowired
  public MemberService(MemberRepository memberRepository) {
    this.memberRepository = memberRepository;
  }

  public Long join(Member member) {
    long start = System.currentTimeMillis();

    try {
      validateDuplicateMember(member);
      memberRepository.save(member);
      return member.getId();
    } finally {
      long finish = System.currentTimeMillis();
      long timeMs = finish - start;
      System.out.println("join " + timeMs + "ms");
    }
  }

  public List<Member> findMembers() {
    long start = System.currentTimeMillis();
    try {
      return memberRepository.findAll();
    } finally {
      long finish = System.currentTimeMillis();
      long timeMs = finish - start;
      System.out.println("findMembers " + timeMs + "ms");
    }
  }
```

**문제**

- 회원가입, 회원 조회에 **시간을 측정**하는 기능은 **핵심 관심 사항이 아니다.**
- 시간을 측정하는 로직은 **공통 관심 사항**이다.
- 시간을 측정하는 로직과 **핵심 비즈니스 로직**이 **섞여서** **유지보수가 어렵다.**
- 시간을 측정하는 로직을 **별도의 공통 로직으로 만들기 매우 어렵다.** 
- 시간을 측정하는 로직을 **변경**할 때 **모든 로직을 찾아가면서 변경해야 한다.** 



## 2강. AOP 적용

- AOP: Aspect Oriented Programming; 관점 지향 프로그램
- **공통 관심 사항(cross-cutting concern)** vs **핵심 관심 사항(core concern)** 분리

![image](https://user-images.githubusercontent.com/50407047/105124477-8f392c00-5b1d-11eb-8a0e-4fe18a7eb730.png)

**시간 측정 AOP 등록**

- `@Aspect`를 붙여야 AOP로 사용할 수 있다.

- `joinPoint.proceed()`: 다음 메서드로 진행될 수 있다.

- AOP를 스프링 빈으로 등록하기 위해서는 클래스에 `@Component` 애노테이션을 붙이는 방법(컴포넌트 스캔)과, SpringConfig에 빈으로 등록하는 방법이 있다. 서비스, 리포지토리보다는 AOP가 특별하니까 SpringConfig에 직접 등록하는 것이 좋다. 그래야 SpringConfig를 보고 AOP가 등록되어서 쓰인다는 것을 바로 알 수 있기 때문이다. 

  ```java
  @Configuration
  public class SpringConfig {
  
    private final MemberRepository memberRepository;
  
    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
      this.memberRepository = memberRepository;
    }
  
    @Bean
    public MemberService memberService() {
      return new MemberService(memberRepository);
    }
  
    @Bean
    public TimeTraceApp timeTraceApp() {
      return new TimeTraceApp();
    }
  ```

- `@Around("execution(* hello.hellospring..*(..))")`: 공통 관심사항 적용 범위를 타겟팅: 보통 패키지 단위로 타게팅한다.

```java
package hello.hellospring.aop;

/*@Component*/
@Aspect
public class TimeTraceApp {
  @Around("execution(* hello.hellospring..*(..))")
  public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {

    long start = System.currentTimeMillis();

    System.out.println("START: " + joinPoint.toString());

    try {
      return joinPoint.proceed();
    } finally {
      long finish = System.currentTimeMillis();
      long timeMs = finish - start;

      System.out.println("END: " + joinPoint.toString() + " " + timeMs + "ms");
    }
  }
}
```

> 이렇게 시간 측정하는 로직을 공통 로직으로 만들어 적용하면 어디에 병목이 생기는 지 바로 파악할 수 있다.

**해결**

- 회원가입, 회원 조회 등 **핵심 관심사항**과 시간을 측정하는 공**통 관심 사항**을 **분리**한다.
- 시간을 측정하는 로직을 **별도의 공통 로직**으로 만들었다.
- **핵심 관심 사항을 깔끔하게 유지**할 수 있다.
- **변경이 필요하면 이 로직만 변경**하면 된다.
- **원하는 적용 대상을 선택**할 수 있다. (`@Around` 속성값)



### 스프링의 AOP 동작 방식 설명

**AOP 적용 전 의존관계**

![image](https://user-images.githubusercontent.com/50407047/105131159-bc8cd680-5b2b-11eb-89d3-e4b48dd3cee8.png)


**AOP 적용 후 의존관계**

스프링은 AOP가 있으면 가짜(프록시) MemberService를 만들어낸다.  그러면 스프링이 올라올때, 컨테이너에 스프링 빈을 등록할 때 실제 memberService가 아니라 가짜 스프링 빈을 앞에 세워둔다. 가짜 스프링 빈이 끝나면(`joinProceed()` 호출을 하면 내부적으로 많은 일이 벌어진다.) 그때 실제 memberService를 호출해준다. 그래서 memberController가 호출하는 것은 실제 memberService가 아니라 프록시라는 기술로 발생하는 가짜 memberService이다. 

![image](https://user-images.githubusercontent.com/50407047/105131185-c6aed500-5b2b-11eb-893a-e6e34650bb56.png)

**AOP 적용 전 전체 그림**

![image](https://user-images.githubusercontent.com/50407047/105131210-d3cbc400-5b2b-11eb-8c48-b2a3720986fd.png)

**AOP 적용 후 전체 그림**

![image](https://user-images.githubusercontent.com/50407047/105131234-e0501c80-5b2b-11eb-9271-011898689389.png)

스프링 컨테이너에서 스프링 빈을 관리하기 때문에 프록시 객체를 만들어서 주입하도록 하는 게 가능하다. 개발자가 의존객체를 `new` 연산자로 생성한다면 이런 기술 자체가 불가능할 것이다. MemberController 입장에서는 뭐가 들어오든지 상관 없고 들어오는 거 쓸게! 하는데 프록시가 들어오는 것이다.

> AOP 말고 컴파일 타임에 코드를 generate해서 진짜 자바 코드를 박아서 넣어주는 기술도 있다.

**실제 프록시가 주입되는지 콘솔에서 확인하기**

MemberController의 생성자에서 memberService를 출력해 직접 확인해보자.

```java
@Controller
public class MemberController {
  private final MemberService memberService;

  @Autowired
  public MemberController(MemberService memberService) {

    this.memberService = memberService;
    System.out.println("memberService: " + memberService.getClass());
  }
```

출력결과

```
memberService: class hello.hellospring.service.MemberService$$EnhancerBySpringCGLIB$$a796821c
```

memberService를 가지고 복제를 해서 코드를 조작하는 기술이다. 스프링 컨테이너가 memberService에 AOP가 적용이 되면 프록시 memberService를 통해서 AOP가 다 실행이 되고 joinPoint.proceed하면 실제 memberService가 호출된다.



