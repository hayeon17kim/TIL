# 섹션3. 회원관리 예제: 백엔드 개발

## 1강: 비즈니스 요구사항 정리

- 데이터: 회원ID, 이름

- 기능: 회원 등록, 조회

- 아직 데이터 저장소가 선정되지 않음(가상의 시나리오)

  따라서 우선 인터페이스(MemberRepository)로 구현 클래스(MemoryMemberRepository)를 변경할 수 있도록 설계한다. **개발을 진행하기 위해 초기 개발 단계에서는 구현체로 가벼운 메모리 기반의 데이터 저장소를 사용**한다. 향후에 RDB, NoSQL 등등 다양한 저장소를 데이터 저장소로 바꿔 끼울 것이다. 바꿔 끼우기 위해서는 인터페이스가 필요하다. 

![image](https://user-images.githubusercontent.com/50407047/104727549-d79cc680-5778-11eb-8673-d9b6fcbd43ac.png)

- 컨트롤러: **웹 MVC의 컨트롤러** 역할, 혹은 **API 만드는 역할**
- 서비스: 핵심 **비즈니스 로직** 구현
  예: 회원 중복 가입 불가 로직
- 리포지토리: **데이터베이스에 접근**, 도메인 객체를 DB에 저장하고 관리
- 도메인: **비즈니스 도메인 객체, 주로 데이터베이스에 저장하고 관리됨**
  예: 회원, 주문, 쿠폰 등등 

## 2강. 회원 도메인과 리포지토리 만들기

**회원 객체**

- 여기서 id는 임의의 값으로, 고객이 정하는 아이디가 아니라 시스템에 저장하는 아이디이다. 
- getter, setter가 좋냐 싫냐 여러가지 이야기가 나오고 있지만, 여기서는 단순한 예제이기 때문에  getter, setter를 만들었다.

```java
package hello.hellospring.domain;
public class Member {

 private Long id;
  
 private String name;
  
 public Long getId() {
 	return id;
 }
  
 public void setId(Long id) {
 	this.id = id;
 }
  
 public String getName() {
 	return name;
 }
  
 public void setName(String name) {
 	this.name = name;
 }
  
}
```

**회원 리포지토리 인터페이스**

```java
package hello.hellospring.repository;

public interface MemberRepository {
 Member save(Member member);
 Optional<Member> findById(Long id);
 Optional<Member> findByName(String name);
 List<Member> findAll();
}
```

**회원 리포지토리 메모리 구현체**

저장소(`store`)는 HashMap을 사용하고 있다. 실무에서는 동시성 문제가 있을 수 있어서 공유되는 변수일 때는 ConcurrentHashMap을 사용한다. 그리고 sequence를 Long타입으로 정하였다. 이것도 마찬가지로 실무에서는 동시성 문제를 고려해서 AtomicLong을 사용한다. 하지만 여기서는 단순한 예제이기 때문에 동시성 문제를 고려하지 않고, HashMap, Long을 사용하고 있다. 

`findById`, `findByName`의 리턴타입은 `Optional<Member>`이다. Optional이 무엇일까? 자바8에 새로 추가된 기능이다. `findById`나 `findByName` 메서드가 실행되었을 때 데이터가 없어서 null을 반환할 수 있다. 요즘에는 null을 처리하는 방법 중에 null을 그대로 반환하는 것이 아니라 Optional이라는 것으로 감싸서 반환하는 방법을 많이 선호한다. 이렇게 하면 클라이언트단에서 무언가를 할 수 있다. 

`findById`는 `Optional.ofNullable()` 메서드의 파라미터로 `store.get(id)`를 넘겨주어 데이터가 없을 때 null이 아니라 비어있는 Optional 객체를 반환하도록 만들어주었다. 

`store` 저장소에 있는 회원 전체 루프를 돌면서(`values().stream()`) member 이름이 파라미터로 넘어온 이름과 같은 경우(`filter`)를 하나라도 찾으면 그 값을 리턴하도록 만들어주었다. 

`findAll`은 List 객체를 리턴하도록 만들어주었다. (`ArrayList<>(store.values())`) 실무에서는 루프를 돌리기 쉬운 List 객체를 많이 사용한다. 

```java
package hello.hellospring.repository;

public class MemoryMemberRepository implements MemberRepository {
  
 private static Map<Long, Member> store = new HashMap<>();
  
 private static long sequence = 0L;
  
 @Override
 public Member save(Member member) {
 member.setId(++sequence);
 store.put(member.getId(), member);
	 return member;
 }
  
 @Override
 public Optional<Member> findById(Long id) {
 	return Optional.ofNullable(store.get(id));
 }
  
 @Override
 public List<Member> findAll() {
	 return new ArrayList<>(store.values());
 }
  
 @Override
 public Optional<Member> findByName(String name) {
 	return store.values().stream()
 .filter(member -> member.getName().equals(name))
 .findAny();
 }
  
 public void clearStore() {
 	store.clear();
 }
  
}
```



## 3강. 회원 리포지토리 테스트 케이스 작성

개발한 기능을 실행해서 테스트 할 때 자바의 **main 메서드**를 통해서 **실행**하거나, **웹 애플리케이션의 컨트롤러를 통해서 해당 기능을 실행**한다. 이러한 방법은 **준비하고 실행하는데 오래 걸리고**, **반복 실행하기 어렵고** **여러 테스트를 한 번에 실행하기 어렵다**는 단점이 있다. 자바는 JUnit이라는 프레임워크로 테스트를 실행해서 이러한 문제를 해결한다.

**회원 리포지토리 메모리 구현체 테스트**

`/src/test/java` 하위 폴더에 생성한다.

- `Test` 애노테이션을 붙이면 메서드가 각각의 테스트 케이스가 되어 각각 실행할 수 있게 된다. 테스트 케이스를 작성하는 것은 메인 메서드를 작성하는 것과 유사하다. 
- `save()`
  - ``System.out.println("result == " + (result == member));` 와 같이 출력해서 테스트를 할 수도 있다. 그러나 이렇게 계속 글자를 갖고 올 수 는 없다. 이를 위해서 JUnit이 제공하는 Assertions를 사용할 수 있다.
  - `Assertions.assertEquals(result, member)`: Junit에서 제공하는 Assertions이다.
  - `Assertions.assertThat(member).isEqualTo(result)`: AssertJ에서 제공하는 Assertions를 쓰면 더 편리하다. 더 직관적이다. (member가 result랑 똑같애!) 이 Assertions를 static import하면 `assertThat(member).isEqualTo(result)`로 메서드 이름만으로 사용할 수 있다. 

- `AfterEach`: 한 번에 여러 테스트를 실행하면 메모리 DB에 직전 테스트의 결과가 남을 수 있다. 이렇게 되면 다음 이전 테스트 때문에 다음 테스트가 실패할 가능성이 있다. `@AfterEach`를 사용하면 각 테스트가 종료될 때마다 이 기능을 실행한다(각 메서드가 호출될 때마다 호출되는 콜백 메서드라고 생각하면 된다). 여기서는 메모리 DB에 저장된 데이터를 삭제한다.

  **MemoryMemberRepository**

  ```java
   public void clearStore() {
   	store.clear();
   }
  ```

  **MemoryMemberRepositoryTest**

  ```java
    @AfterEach
    public void afterEach() {
      repository.clearStore();
    }
  
  ```

- 테스트는 각각 독립적으로 실행되어야 한다. 테스트 순서에 의존관계가 있는 것은 좋은 테스트가 아니다.

```java
package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MemoryMemberRepositoryTest {
  MemoryMemberRepository repository = new MemoryMemberRepository();

  @AfterEach
  public void afterEach() {
    repository.clearStore();
  }

  @Test
  public void save() {
    Member member = new Member();
    member.setName("spring");

    repository.save(member);
    Member result = repository.findById(member.getId()).get();
    assertThat(member).isEqualTo(result);
  }

  @Test
  public void findByName() {
    Member member1 = new Member();
    member1.setName("spring1");
    repository.save(member1);

    Member member2 = new Member();
    member2.setName("spring2");
    repository.save(member2);

    Member result = repository.findByName("spring1").get();

    assertThat(result).isEqualTo(member1);
  }

  @Test
  public void findAll() {
    Member member1 = new Member();
    member1.setName("spring1");
    repository.save(member1);

    Member member2 = new Member();
    member2.setName("spring1");
    repository.save(member2);

    List<Member> result = repository.findAll();

    assertThat(result.size()).isEqualTo(2);
  }
}
```



## 4강. 회원 서비스 개발

- `validateDuplicateMember()`: 회원가입 하기 전에 우선 회원 이름을 저장소에서 찾고(`findByName`) 값이 있으면 (`ifPresent()`) `IllegalStateException` 예외를 던진다. 이건 **`findByName`의 리턴값이 Optional이기 때문에 가능**한 것이다. 일단 Optional로 한 번 감싸면 Optional 객체 안에 Member 객체가 있는 것이다. Optional에는 유용한 메서드가 많고 이 중 하나가 `ifPresent`이다. 과거에는 조건문 `if (memberRepository.findByName(~) == null)` 이런 식으로 코딩을 했지만, 지금은 null일 가능성이 있을 경우에는 Optional로 감싸서 반환을 해주고, `ifPresent`를 사용한다. 그냥 꺼내고 싶으면 `get`으로 꺼내도 된다. 하지만 바로 꺼내는 것은 권장하지 않는다. `orElseGet(Supplier<? extends Member> supplier)`라는 메서드도 있다. 이 메서드는 값이 있으면 꺼내고, 값이 없으면 여기 있는 메서드를 실행해! 디폴트값을 꺼내! 라는 식으로 많이 사용한다. 
- **서비스 클래스**는 비즈니스에 가까운 이름을 사용해야 한다. 그래야 개발자든 기획자든 갑자기 기획자가 회원가입 로직이 이상해요! 하면 조인 쪽 살펴보자. 하고 매칭이 되기 때문이다. 서비스는 비즈니스에 의존적으로 설계를 하고 리포지토리는 단순히 기계적으로 개발자스럽게 이름을 붙인다. 

```java
package hello.hellospring.service;

public class MemberService {

  private final MemberRepository memberRepository = new MemoryMemberRepository();

  /**
   * 회원 가입
   */
  public Long join(Member member) {
    validateDuplicateMember(member); // 중복 회원 검증
    memberRepository.save(member);
    return member.getId();
  }

  private void validateDuplicateMember(Member member) {
    memberRepository.findByName(member.getName())
            .ifPresent(m -> {
              throw new IllegalStateException("이미 존재하는 회원입니다.");
            });
  }

  /**
   * 전체 회원 조회
   */ 
  public List<Member> findMembers() {
    return memberRepository.findAll();
  }

  public Optional<Member> findOne(Long memberId) {
    return memberRepository.findById(memberId);
  }
}

```

## 5강. 회원 서비스 테스트

>테스트 클래스 자동 생성 단축키: Ctrl + Shift + T

> 이전 실행한 코드 다시 실행: Shift + F10

테스트 케이스 메서드명은 한글로 작성하는 경우도 종종 있다. 직관적이고 빌드에 포함되지도 않기 때문이다. 

테스트를 작성할 때 given, when, then으로 나누어 작성하는 것이 좋다. 테스트는 대부분 무언가가 주어졌는데(given), 이것을 실행했을 때(when), 결과가 이게 나와야해(then) 라는 구조로 나뉘어진다. 다음과 같이 주석으로 given, when, then으로 적어두고 해당하는 코드를 작성하면 직관적이다. 상황에 따라 이에 맞지 않을 때도 있기 때문에 그럴 때는 변형하여 작성하면 된다. 

**기존에는 회원 서비스가 메모리 회원 리포지토리를 직접 생성하게 했다.**

```java
public class MemberService {
  private final MemberRepository memberRepository = new MemoryMemberRepository();
}
```

```java
public class MemberServiceTest {
  MemberService memberService = new MemberService;
  MemberRepository memberRepository = new MemoryMemberRepository();
  //.. 
}
```

**회원 리포지토리의 코드가 회원 서비스 코드를 DI 가능하게 변경한다.**

지금 문제는 MemberService에서 생성한 memberRepository와 테스트 케이스에서 만든 memberRepository가 서로 다른 인스턴스라는 것이다. 물론 지금 상황에서는 memberRepository의 store 객체가 static 필드이기 때문에 static 필드는 인스턴스에 상관 없이 클래스 레벨에 붙는 것이므로 문제가 되지 않는다. 그러나 만약 static이 아니면 DB가 다른 DB가 되면서 문제가 생길 것이다. 그뿐만 아니라 같은 리포지토리로 테스트하는 것이 맞는데 다른 리포지토리로 테스트되고 있다. 

그래서 같은 인스턴스를 사용하도록 바꿔야 한다. MemberRepository를 MemberService에서 직접 생성하는 것이 아니라 생성자 파라미터를 통해 외부로부터 주입받도록 바꿔준다. 이런 것을 Dependency Injection(DI)이라고 한다. 

```java
public class MemberService {

  private final MemberRepository memberRepository;

  public MemberService(MemberRepository memberRepository) {
    this.memberRepository = memberRepository;
  }
  ..
}
```

```java
public class MemberServiceTest {
  MemberService memberService;
  MemberRepository memberRepository;
  
  @BeforeEach
  public void beforeEach() {
    memberRepository = new MemoryMemberRepository();
    memberService = new MemberService(memberRepository);
  }
}
```

`@BeforeEach`: 각 테스트 실행 전에 호출된다. 테스트가 서로 영향이 없도록 항상 새로운 객체를 생성하고, 의존관계도 새로 맺어준다.

**회원 서비스 테스트**

```java
package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MemberServiceTest {

  MemberService memberService;
  MemoryMemberRepository memberRepository;

  @AfterEach
  public void afterEach() {
    memberRepository = new MemoryMemberRepository();
    memberService = new MemberService(memberRepository);
    memberRepository.clearStore();
  }

  @Test
  void 회원가입() {
    // given
    Member member = new Member();
    member.setName("spring");

    // when
    Long saveId = memberService.join(member);

    // then
    Member findMember = memberService.findOne(saveId).get();
    assertThat(member.getName()).isEqualTo(findMember.getName());
  }

  @Test
  public void 중복_회원_예외() {
    //given
    Member member1 = new Member();
    member1.setName("spring");

    Member member2 = new Member();
    member2.setName("spring");

    //when
    memberService.join(member1);
    IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
    assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        /*
        try {
            memberService.join(member2);
            // Exception이 발생되지 않고 넘어가면 실패한 것이기 때문에 fail()
            fail();
        } catch (IllegalStateException e) {
            assertThat(e.getMessage().isEqualTo("이미 존재하는 회원입니다."));
        }
         */
  }

  @Test
  void findMembers() {

  }

  @Test
  void findOne() {
  }
}
```

