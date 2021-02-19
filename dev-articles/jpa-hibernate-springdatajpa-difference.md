# JPA, Hibernate, 그리고 Spring Data JPA의 차이점

## JPA는 기술 명세

- **자바 어플리케이션에서 관계형 데이터베이스를 사용하는 방식을 정의**한 **인터페이스**이다.

- JPA는 특정 기능을 하는 라이브러리가 아니다. 

- JPA는 단순 명세이기 때문에 구현이 없다.

  JPA를 정의한 `javax.persistance` 패키지의 대부분은 interface, enum, Exception, annotation으로 이루어져 있다.

  ```java
  package javax.persistence;
  
  import ...
  
  // JPA의 핵심
  public interface EntityManager {
  
      public void persist(Object entity);
  
      public <T> T merge(T entity);
  
      public void remove(Object entity);
  
      public <T> T find(Class<T> entityClass, Object primaryKey);
  
      // More interface methods...
  }
  ```

## Hibernate는 JPA의 구현체

- Hibernate는 JPA라는 명세의 구현체이다.
- JPA의 핵심인 `EntityManagerFactory`, `EntityManager`, `EntityTransaction`을 Hibernate에서는 각각 `SessionFacotry`, `Session`, `Transaction`으로 상속받고 각각 `Impl`로 구현하고 있다.

## Spring Data JPA는 JPA를 편하게 만들어놓은 모듈이다

- 실무에서는 보통 `EntityManager`를 직접 다루는 것이 아니라 DB에 접근할 필요가 있는 대부분의 상황에서는 `Repository`를 정의하여 사용한다.

- `Repository`가 Spring Data JPA의 핵심이다. 

- JPA를 한 단계 추상화시킨 `Repository`라는 인터페이스를 제공함으로써 개발자가 JPA를 더 쉽고 편하게 사용할 수 있도록 만들어준다. 

  사용자가 **`Repository` 인터페이스에 정해진 규칙대로 메서드를 입력**하면, **Spring이 알아서 해당 메서드 이름에 적합한 쿼리를 날리는 구현체를 만들어 Bean으로 등록**해준다. 

- Spring Data JPA가 JPA를 추상화했다는 말은, Spring Data JPA의 `Repository`의 구현에서 JPA를 사용하고 있다는 것이다. 

```java
package org.springframework.data.jpa.repository.support;

import ...

// Repository 인터페이스의 기본 구현체
public class SimpleJpaRepository<T, ID> implements JpaRepositoryImplementation<T, ID> {

    // 내부적으로 EntityMananger를 사용한다.
    private final EntityManager em;

    public Optional<T> findById(ID id) {

        Assert.notNull(id, ID_MUST_NOT_BE_NULL);

        Class<T> domainType = getDomainClass();

        if (metadata == null) {
            return Optional.ofNullable(em.find(domainType, id));
        }

        LockModeType type = metadata.getLockModeType();

        Map<String, Object> hints = getQueryHints().withFetchGraphs(em).asMap();

        return Optional.ofNullable(type == null ? em.find(domainType, id, hints) : em.find(domainType, id, type, hints));
    }

    // Other methods...
}
```

![JPA, Hibernate, Spring Data JPA의 전반적인 그림](https://suhwan.dev/images/jpa_hibernate_repository/overall_design.png)

