## DAO, DTO, Entity Class의 차이

https://gmlwjd9405.github.io/2018/12/25/difference-dao-dto-entity.html

### DTO(Data Transfer Object)

`dto package`

- **계층간 데이터 교환**을 위한 객체(Java Beans)이다.
  - DB에서 데이터를 얻어 Service나 Controller등으로 보낼 때 사용하는 객체를 말한다.
  - **DB 데이터가 Presentation Logic Tier로 넘어올 때는 DTO의 모습으로 바뀌어 오게 된다.**
  - *로직을 갖고 있지 않은 순수한 데이터 객체*이며 getter/setter 메서드만을 갖는다.
  - 그러나 **DB에서 꺼낸 값을 임의로 변경할 필요가 없기 때문**에 **DTO 클래스에는 setter가 없다.**
- Request와 Response용 DTO는 View를 위한 클래스
  - 자주 변경이 필요한 클래스
  - Presentation Model
  - **`toEntity()` 메서드를 통해 DTO에서 필요한 부분을 이용하여 Entity로 만든다.** 
  - Controller Layer에서 Response DTO 형태로 Client에 전달한다.
- VO(Value Object) vs DTO
  - VO: DTO와 동일하지만 read only 속성
  - **VO는 특정 비즈니스 값을 담는 객체**고, **DTO는 Layer간 통신 용도로 오고가는 객체**를 말한다.

```java
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
  @NotBlank
  @Pattern(regexp = "^([\\w-]+(?:\\.[\\w-]+)*)@((?:[\\w-]+\\.)*\\w[\\w-]{0,66})\\.([a-z]{2,6}(?:\\.[a-z]{2})?)$")
  private String email;

  @JsonIgnore
  @NotBlank
  @Size(min = 4, max = 15)
  private String password;

  @NotBlank
  @Size(min = 6, max = 10)
  private String name;

  public User toEntity() {
      return new User(email, password, name);
  }

  public User toEntityWithPasswordEncode(PasswordEncoder bCryptPasswordEncoder) {
      return new User(email, bCryptPasswordEncoder.encode(password), name);
  }
}
```



### Entity Class

`domain package`

- 실제 DB의 테이블과 매칭될 클래스
  - 즉 테이블과 링크될 클래스이다.
  - `Entity` 클래스 또는 **가장 Core한 클래스**라고 부른다.
- 최대한 **외부에서 Entity 클래스의 `getter method`를 사용하지 않도록** 해당 클래스 안에서 필요한 로직 메서드를 구현한다.
  - Domain Logic만 가지고 있어야 하고 **Presentation Logic을 가지고 있으면 안된다.**
  - 여기서 구현한 method는 주로 Service Layer에서 사용된다.
- **Entity 클래스와 DTO 클래스를 분리하는 이유**
  - **View Layer와 DB Layer를 철저하게 분리**하기 위해 
  - 테이블과 매핑되는 Entity 클래스가 변경되면 여러 클래스에 영향을 끼치게 되는 반면 **View와 통신하는 DTO 클래스는 자주 변경되므로 분리해야 한다.** 
  - 각 **View 내에서 Domain Model의 getter만을 이용해서 원하는 정보를 표시하기가 어려운 경우**가 종종 있다. 이런 경우 **Domain Model 내에 Presentation을 위한 필드나 로직을 추가**하게 되는데, 이런 방식이 **모델링의 순수성을 깨고 Domain Model 객체를 망가뜨리게 된다.** 
  - 또한 Domain Model을 **복잡하게 조합한 형태의 Presentation 요구사항**이 있기 때문에 Domain Model을 직접 사용하는 것은 어렵다.
  - 즉 **DTO는 Domain Model을 복사한 형태**로, 다양한 Presentation Logic을 추가한 정도로 사용하며 Domain Model 객체는 Persistent만을 위해서 사용한다. 

![img](https://gmlwjd9405.github.io/images/spring-framework/spring-package-flow.png)

