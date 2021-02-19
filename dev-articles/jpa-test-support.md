https://cheese10yun.github.io/jpa-test-support/

# JPA 기반 테스트 코드 작성 팁

## JPA 기반 테스트 불편한 점

**과도한 Dependency**

`Given` 절을 작성할 때 많은 디펜던시가 필요한 부분의 테스트 코드를 작성할 때 많은 Repository를 주입받아야 해서 코드 양이 많아지는 문제가 있다.

**검증할 때 조회용 코드를 따로 작성해야 하는 문제**

해당 **서비스의 코드가 여러 entity의 여러 row의 변경을 가할 때** `Then` 절에서 검증을 진행할 때 **오직 테스트를 위해서만 조회용 코드를 일반 코드에 작성을 해야 하는 문제**가 있다. 그렇지 않으면 Test Scope에서 사용할 Repository를 따로 만들어야 한다.

```kotlin
paymentRepository.save(Payment(BigDecimal.TEN))
memberRepository.save(Member("username", 10, Team("team-name")))
orderRepository.save(Order(BigDecimal.TEN))

// 특정 서비스가 여러 entity rows를 변경을 가할 때 아래와 같은 조회로 then 절을 이어가야 한다.
// paymentRepository.findBy...epository 메서드는 없다
// memberRepository.findBy...
// orderRepository.findBy...
```



## 해결 방법

테스트 코드를 위해 필요한 기능을 제공해주는 `SpringBootTestSupport`를 사용한다.

### 과도한 Dependency 해결 방법

```kotlin
@SpringBootTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@ActiveProfiles("test")
abstract class SpringBootTestSupport {

    @Autowired
    protected lateinit var entityManagerFactory: EntityManagerFactory

    @Autowired
    protected lateinit var query: JPAQueryFactory

    protected val entityManager: EntityManager by lazy {
        entityManagerFactory.createEntityManager()
    }

    protected val transaction: EntityTransaction by lazy {
        entityManager.transaction
    }

    protected fun <T> save(entity: T): T {
        transaction.begin()

        try {
            entityManager.persist(entity)
            entityManager.flush() // transaction commit시 자동으로 flush 발생시키나 명시적으로 선언
            transaction.commit()
            entityManager.clear()

        } catch (e: Exception) {
            transaction.rollback()
        }

        return entity
    }


    protected fun <T> saveAll(entities: Iterable<T>): Iterable<T> {
        transaction.begin()

        for (entity in entities) {
            try {
                entityManager.persist(entity)
                entityManager.flush() // transaction commit시 자동으로 flush 발생시키나 명시적으로 선언
                transaction.commit()
                entityManager.clear()

            } catch (e: Exception) {
                transaction.rollback()
            }
        }
        return entities
    }
}
```

`Given` 절에서 작성하는 데이터 Set up이기 때문에 트랜잭션을 완전히 분리하기 위해서 transaction을 commit을 직접 수행시킨다. 

주의해야 할 점은 EntityManager를 통해 clear()를 강제로 발생시키는 점이다.

**영속성 컨텍스트는 1차 캐시를 우선시**한다. 추가적인 JPQL 쿼리로 조회한 값이 영속성 컨텍스트에 존재하는 경우(식별자로 동일성을 판단함) JPQL로 조회한 데이터를 버린다. 따라서 EntityManager를 이용해 **영속성 컨텍스트를 초기화하는 것이다.**

그리고 `Given` 절에 작성하는 데이터는 이미 데이터베이스에 영속화되어 있다는 개념을 테스트한다. Entity의 영속성을 EntityManager를 통해 진행하기 때문에 단순 save를 위해서 DI 받는 Repository가 없어졌다. 

### 검증시 조회용 코드를 일반 코드에 작성하는 문제 해결방법

```kotlin
@Configuration
class Configuration {

    @Bean
    fun query(entityManager: EntityManager): JPAQueryFactory {
        return JPAQueryFactory(entityManager)
    }
}

...
abstract class SpringBootTestSupport {
    ...
    @Autowired
    protected lateinit var query: JPAQueryFactory
}
```

`JPAQueryFactory`을 `query`으로 Bean 등록을 한다. Test Scope에서만 사용하길 원한다면 Test directory에 `@TestConfiguration`으로 지정해도 된다.

`query`를 이용해 테스트 코드 검증을 할 수 있다.

```kotlin
@Transactional
internal class JpaTestSupport : SpringBootTestSupport() {

    @Test
    internal fun `entityManager를 이용해서 dependency가 최소화 `() {
        // 특정 테스트를 하기 위해서 많은 디펜던시가 필요하다.
        save(Payment(BigDecimal.TEN))
        save(Member("username", 10, save(Team("team-ename"))))
        save(Coupon(BigDecimal.TEN))

        // 특정 서비스가 여러 entity rows를 변경할때 아래와 같은 조회로 Then 이어가야 합니다.
        // paymentRepository.findBy... epository 메서드는 없는데??...
        // memberRepository.findBy...
        // couponRepository.findBy...

        val payments = query.selectFrom(QPayment.payment)
            .where(QPayment.payment.amount.gt(BigDecimal.TEN))
            .fetch()

        val members = query.select(QMember.member.age)
            .from(QMember.member)
            .where(QMember.member.age.gt(20))
            .fetch()

        val coupons = query.selectFrom(QCoupon.coupon)
            .where(QCoupon.coupon.amount.eq(123.toBigDecimal()))
            .fetch()
    }
}
```

조회용 메서드를 단순히 테스트 코드에서만 사용하기 위해 작성하거나 Test Scope에 별도에 Repository를 구성하는 것이 아니라, `query`를 이용해 비즈니스에 맞는 쿼리를 작성할 수 있따.

```kotlin
internal class PaymentServiceTest(
    private val paymentService: PaymentService
) : SpringBootTestSupport() {

    @Test
    internal fun `paymentZero test`() {
        //given
        val targetAmount = 105.toBigDecimal()
        saveAll((1..100).map {
            Payment(it.toBigDecimal().plus(BigDecimal.TEN).setScale(0))
        })

        //when
        paymentService.paymentZero(targetAmount)

        //then
        val count = query
            .selectFrom(qPayment)
            .where(qPayment.amount.gt(targetAmount))
            .fetchCount()

        then(count).isEqualTo(0)
    }
```





