https://cheese10yun.github.io/jpa-preference/

## JPA 선호하는 패턴

### 컬럼 에노테이션 사용

컬럼과 필드명이 동일한 경우일지라도, 모든 멤버 필드에 컬럼 애노테이션을 작성하는 것이 좋다. 

이유:

- 컬럼 애노테이션을 통해 `nullable`, `unique`, `updatable` 등 메타 정보를 전달해줄 수 있다. 
- 멤버 필드 리네임 관련 리팩토링에 과감해질 수 있다.

### 엔티티에 과도한 애노테이션은 작성하지 않는다.

```java
@Entity
@Table(
    name = "member",
    indexes = [
        Index(columnList = "username"),
        Index(columnList = "age")
    ],
    uniqueConstraints = [
        UniqueConstraint(columnNames = ["username", "age"])
    ]
)
data class Member(
    ...
)
```

과도한 애노테이션은 엔티티 클래스의 비즈니스 코드의 집중도를 떨어뜨린다.  위 속성은 비교적 변경사항이 잦기 때문에 작성하지 않는 것이 좋다.

### 조인 컬럼을 사용한다

```java
data class Member(
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "team_id", nullable = false) // 생략 가능
    var team: Team
) : EntityAuditing()
```

`OneToOne`, `ManyToOne` 정보의 경우 연관관계의 주인에서 FK를 갖기 때문에 컬럼 애노테이션을 작성할 수 있다. 연관관계 애노테이션으로 작성하면 기본적으로 PK 기반으로 되기 때문에 생략 가능하다. 그러나 조인 컬럼 애노테이션을 작성하면 `nullable`, `unique`,`updatable` 등 정보를 많이 표현해준다. 모엇보다 `nullable` 설정에 따른 조인 전략이 달라질 수 있어 성능상 이점을 얻을 수 있다. 

### 양방향보다는 단방향으로 설정한다

양방향 연관관계를 작성하면 양방향 편의 메서드, 디펜던시 사이클 문제가 있다. 무엇보다 OneToMany, ManyToOne 관계설정은 선호하지 않는다고 한다. 

### 객체 그래프 탐색을 끊는다

관계형 데이터베이스는 FK를 통해 연관 탐색을 계속 진행할 수 있다. **JPA도 객체 그래프 탐색을 통해 연관 탐색을 계속 진행할 수 있다. 이것은 그래프 탐색의 오용이다.** 

### 쿼리 메서드를 지양한다

- 조건이 까다로운 조회용 코드는 쿼리 메서드로 표현하면 너무 장황해서 코드 가독성이 좋지 않다. 

- 엔티티 멤버 필드 변경 시 쿼리 메서드도 변경해야 한다.

- 비즈니스 로직의 컨텍스트를 메서드 명으로 표현하지 못한다.

  **예) `findByLastLoginAndStatusAndGrade`의 경우 쿼리 조건문을 뜻할 뿐 활동하지 않은 휴먼회원을 뜻하지 않는다. 차라리 Querydsl로 코드를 작성하고 `findDormancyMember`으로 메서드를 지정하는 것이 좋다.**

- 쿼리 메서드는 인터페이스를 기반으로 작성하기 때문에 테스트 코드를 작성하지 않는 경우도 많다. 반면 Query DSL은 세부 클래스를 작성하기 때문에 테스트를 더 작성하도록 만든다. 

쿼리 메서드보다는 Query DSL 기반으로 모두 작성하는 것을 선호한다고 한다. 

```java
public interface AccountRepository extends JpaRepository<Account, Long>, AccountCustomRepository {
}

public interface AccountCustomRepository {
    List<Account> findRecentlyRegistered(int limit);
}

@Transactional(readOnly = true)
public class AccountCustomRepositoryImpl extends QuerydslRepositorySupport implements AccountCustomRepository {

    public AccountCustomRepositoryImpl() {
        super(Account.class);
    }

    @Override
    // 최근 가입한 limit 갯수 만큼 유저 리스트를 가져온다
    public List<Account> findRecentlyRegistered(int limit) {
        final QAccount account = QAccount.account;
        return from(account)
                .limit(limit)
                .orderBy(account.createdAt.desc())
                .fetch();
    }
}
```

### Auditing 상속 구조로 사용한다

`@MappedSuperClass`, `@EntityListener`를 사용하면 반복적인 id, createdAt, udpatedAt을 코드를 상속받아 해결할 수 있다. 