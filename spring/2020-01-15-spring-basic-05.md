# Section 5. 회원 관리 예제 - 웹 MVC 개발

## 1강. 회원 웹 기능 - 홈 화면 추가

**홈 컨트롤러 추가**

```java
@Controller
public class HomeController {

  @GetMapping("/")
  public String home() {
    return "home";
  }
}
```

**회원 관리용 홈**

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<body>
<div class="container">
    <div>
        <h1>Hello Spring</h1>
        <p>회원 기능</p>
        <p>
            <a href="/member/new">회원 가입</a>
            <a href="/members">회원 목록</a>
        </p>
    </div>
</div>
</body>
</html>
```

`index.html` 파일이 있는데도 `/` 요청을 보냈을 때 HomeController에 매핑된 뷰 템플릿을 실행한다. 왜일까? 컨트롤러가 정적 파일보다 우선순위가 높기 때문이다.

## 2강. 회원 웹 기능 - 등록

**회원 등록 폼 컨트롤러**

```java
@Controller
public class MemberController {
  private final MemberService memberService;

  @Autowired
  public MemberController(MemberService memberService) {
    this.memberService = memberService;
  }

  @GetMapping(value = "/member/new")
  public String createForm(MemberForm form) {
    return "members/createMemberForm";
  }
}
```

**회원 등록 폼 HTML**

```html
<!DOCTYPE html>
<html>
<body>
<div class="container">
  <form action="/members/new" method="post">
    <div class="form-group">
      <label for="name">이름</label>
      <input type="text" id="name" name="name" placeholder="이름을 입력하세요.">
    </div>
    <button type="submit">등록</button>
  </form>
</div>
</body>
</html>
```



**웹 등록 화면에서 데이터를 전달 받을 폼 객체**

```java
public class MemberForm {
  private String name;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
```

`form`의 `input`의 `name`을 보고 스프링이 `setName()`을 호출하여 값을 넣어준다. 그러면 이제 `MemberController` 의`create` 메서드에서 `getter`로 값을 꺼내 사용하면 된다. 

**회원 컨트롤러에서 회원을 실제 등록하는 기능**

```java
  @PostMapping(value = "/members/new")
  public String createForm(MemberForm form) {
    Member member = new Member();
    member.setName(form.getName());

    memberService.join(member);

    return "redirect:/";
  }
```

URL 은 같아도 GET 방식인지 POST 방식인지에 따라 다르게 매핑할 수 있다. 

## 3강. 회원 웹 기능 - 조회

**회원 컨트롤러에서 조회 기능**

```java
@GetMapping(value = "/members")
public String list(Model model) {
  List<Member> members = memberService.findMembers();
  model.addAttribute("members", members);
  return "members/memberList";
}
```

**회원 리스트 HTML**

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<body>
<div class="container">
  <div>
    <table>
      <thead>
      <tr>
        <th>#</th>
        <th>이름</th>
      </tr>
      </thead>
      <tbody>
      <tr th:each="member : ${members}">
        <td th:text="${member.id}"></td>
        <td th:text="${member.name}"></td>
      </tr>
      </tbody>
    </table>
  </div>
</div>
</body>
</html>
```

