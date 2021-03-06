# JPA (Java Persistence API) 기본 개념

출처: https://tinkerbellbass.tistory.com/24 [Welcome to NeverLand]

## JPA(Java Persistence API)

- **RDBMS와 OOP 객체 사이의 불일치에서 오는 패러다임을 해결**하기 위해 **자바가 ORM 기술 만듦**
- ORM(Object-Relational Mapping): **객체와 RDBMS을 매핑**하는 역할

- **ORM 기술 구현하기 위해 만들어진 프레임워크가 Hibernate**이다. 이외에도 TopLink, CoCobase 구현 프레임워크가 등장했다. 
- 이런 **ORM 기술을 구현한 프레임워크에 대한 표준화** 결과: **JPA** 등장
- **JDBC API**가 앱과 DBMS 사이 **인터페이스** 역할을 하기 때문에 개발자는 **DB 드라이버만 변경하면 되는 것처럼**, 개발자는 ORM 기술을 사용할 때 **JPA 인터페이스**에 맞춰 구현되어 있는 **Hibernate, TopLink, CoCoBase 프레임워크** 중 골라서 사용하면 된다. 
- ORM 프레임워크를 사용하면 개발자가 **객체를 DB에 저장**할 때 **SQL을 직접 작성할 필요 없이** 자바 컬렉션에 저장하는 것처럼 **ORM 프레임워크에 저장하면 된다.** 



## 영속성(Persistance)

### Entity Class

**엔티티 클래스**는 **자바 클래스에 `@Entity`를 붙여 테이블과 매핑한다고 JPA에게 알려주는 클래스**다. 엔티티 클래스에서 **만들어진 객체를 엔티티**라고 한다. 

### 영속성 컨텍스트(Persistance Context)

영속성 컨텍스트는 엔티티 클래스에서 **만들어지는 엔티티를 영구 저장하고 관리하는 환경**이다.

엔티티 4가지 상태

- **비영속(new/transient)**: 엔티티 객체가 만들어져서 아직 저장되지 않은 상태로, 영속성 컨텍스트와 전혀 관계 없는 상태
- **영속(managed):** 엔티티가 영속성 컨텍스트에 저장되어, 영속성 컨텍스트가 관리할 수 있는 상태
- **준영속(detached)**: 엔티티가 영속성 컨텍스트에 **저장되어 있다가 분리된 상태**로, 영속성 컨텍스트가 **더 이상 관리하지 않는 상태**
- **삭제(removed)**: 엔티티를 **영속성 컨텍스트와 데이터베이스에서 삭제**

영속성 컨텍스트의 특징

- 영속성 컨텍스트는 **엔티티를 식별자 값(`@Id`로 테이블의 기본키와 매핑한 필드값)으로 구분**한다. 따라서 영속 상태는 **식별자 값이 반드시 있어야 한다**. 
- 영속성 컨텍스트에 엔티티를 저장하면 바로 DB에 저장되는 것이 아니라, **1차 캐시에 엔티티를 생성**하고, **쓰기 지연 SQL 저장소에 쿼리문을 생성해서 저장**한다. 

### 엔티티 생성

자바 앱에서 엔티티가 만들어져서 JPA에게 DB 저장을 부탁하면 **만들어진 엔티티는 1차적으로 영속성 컨텍스트에 저장**된다(**1차 캐시**). 그리고, **저장한 엔티티를 DB에 저장하기 위한 쿼리문을 생성시켜 쓰기 지연 SQL 저장소에 저장**한다. 계속해서 엔티티를 넘기면 엔티티와 쿼리문들은 **차곡차곡 영속성 컨텍스트에 저장**된다. 그러나 자바 앱에서 **커밋 명령**이 내려지면 **영속 컨텍스트에는 자동으로 `flush()`가 호출**되고, 영속성 컨텍스트의 변경내용을 DB와 동기(flush)화한다(**SQL 저장소의 쿼리를 실행**시킨다). 마지막으로 **DB에게 commit 쿼리문을 명령**한다.  

### 엔티티 조회

자바 어플리케이션에서 **JPA에게 DB 조회를 부탁**하면 1차적으로 **영속성 컨텍스트에서 엔티티를 찾는다**. 있으면 자바 **앱에 엔티티를 넘긴다**. 영속성 컨텍스트에 **없는 엔티티 조회**를 부탁하면 **쿼리문을 사용해 DB에서 찾아와 영속성 컨텍스트에 엔티티로 저장**하고, 자바 **앱에 그 엔티티를 넘긴다**. 

### 엔티티 변경

JPA는 **엔티티를 영속성 컨텍스트에 보관**할 때, **최초의 상태를 복사해 저장**해두는데, 이를 **스냅샷**이라고 한다. 

자바 앱에서 커밋 명령이 들어오면, 영속 컨텍스트에는 자동으로 `flush()`가 호출되고, 엔티티와 스냅샷을 비교해서 변경된 엔티티를 찾는다. 변경된 엔티티가 있으면 DB에 변경사항을 저장하기 위해 쿼리를 생성하고, 영속성 컨텍스트의 변경내용을 DB와 동기(flush)화 한다. 마지막으로 DB에 commit 쿼리문을 명령한다. 

> 엔티티의 변경사항을 DB에 자동으로 반영하는 기능을 변경감지(Dirty Checking)이라고 한다.

### 엔티티 삭제

자바 앱에서 엔티티 삭제 명령이 들어오면 엔티티를 찾고 쓰기 지연 SQL 저장소에 delete 쿼리를 생성한다. 그리고 자바 앱에서 커밋 명령이 들어오면 자동으로 `flush()`가 호출되고 영속성 컨텍스트의 변경 내용을 DB와 동기화한다. 마지막으로 DB에 commit 쿼리문을 명령한다.



> 준영속 상태의 엔티티 특징
>
> - 비영속 상태에 가깝다. 영속성 컨텍스트가 관리하지 않으므로 영속성 컨텍스트가 제공하는 어떤 기능도 동작하지 않는다. 
> - 식별자 값을 가지고 있다. 비영속 상태의 엔티티는 식별자를 가지지 않을 수도 있지만, 준영속 상태는 이미 한 번 영속 상태였기 때문에, 식별자 값을 가지고 있다. 

## JPA 메서드

- `flush()`: 영속성 컨텍스트의 변경 내용을 DB에 반영한다.

  - 변경 감지가 동작해서 영속성 컨텍스트에 있는 모든 엔티티를 스냅샷과 비교해서 수정된 엔티티를 찾고, 수정된 엔티티를 DB에 반영하기 위해 수정 쿼리를 생성하여 지연 SQL 저장소에 등록
  - 쓰기 지연 SQL 저장소의 쿼리를 DB에 전송 (등록, 수정, 삭제 쿼리)

- `detach()`: 특정 엔티티를 준영속 상태로 만든다. 

- `clear()`: 영속성 컨텍스트를 초기화한다. 영속성 컨텍스트의 모든 엔티티를 준영속 상태로 만든다.

- `close()`: 영속성 컨텍스트를 종료한다. 영속성 컨텍스트가 관리하던 영속 상태의 엔티티가 모두 준영속 상태가 된다. 

- `merge()`: 준영속 상태의 엔티티를 이용해서 새로운 영속 상태의 엔티티를 반환한다.

  - 파라미터로 넘어온 준영속 엔티티의 식별자 값으로 1차 캐시에서 엔티티를 조회
  - 조회한 영속 엔티티에 준영속 엔티티의 값을 채워 넣는다.
  - 생성된 새로운 영속 엔티티를 반환한다.

  새롭게 생성된 영속 상태의 엔티티를 가지고 영속성 컨텍스트가 지원하는 기능을 사용할 수 있다.

- `find()`: 식별자 값을 가지고 엔티티를 찾아서 반환한다.

- `persist()`: 자바 앱에서 생성된 엔티티를 영속성 컨텍스트와 DB에 저장한다. 

- `remove()`: 식별자 값을 가지고 엔티티를 찾아서 삭제한다. 

