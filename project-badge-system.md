## 개발자 소셜 네트워크 서비스: 뱃지 시스템 구현

개발자 소셜 네트워크 서비스를 구현할 때 꼭 해보고 싶은 서비스가 있었으니, 언어별 뱃지 수여/수집 시스템을 구현하는 것이었다.

### 기획 단계

어쩌다 '뱃지 시스템'이 나왔는가..? 기획의 계기는 다음 세 가지다.

**1. 로고 스티커가 붙은 개발자의 노트북**

![man wearing white top using MacBook](https://images.unsplash.com/photo-1456406644174-8ddd4cd52a06?ixlib=rb-1.2.1&ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&auto=format&fit=crop&w=1000&q=80)

개발자들을 보면 자신이 사용하는 언어의 로고 스티커를 노트북에 덕지덕지 붙이고 있는 것을 흔히 볼 수 있다. 이 점에서 착안하여, 각 언어에 대한 글을 많이 쓰면 해당 언어 로고 스티커를 얻을 수 있도록 만들어보자..! 는 것이 목표였다.

**2. `dev.to` 사이트의 뱃지 시스템**

스티커를 노트북에 붙이는 행위를 웹 사이트에 연결하기는 어려웠는데, `dev.to` 사이트를 통해 실마리를 찾을 수 있었다.

![image](https://user-images.githubusercontent.com/50407047/110243369-6bcb2300-7f9d-11eb-91df-181f46ae449b.png)

`dev.to` 사이트에서는 유저의 활동에 따라 뱃지를 수여하는 시스템을 가지고 있었다. 다음은 내가 받은 가입 1주년 뱃지이다.

![image](https://user-images.githubusercontent.com/50407047/110243638-9d90b980-7f9e-11eb-91ef-2e0f91adb06d.png)

이렇게 뱃지를 받으면 마이페이지에 뱃지 목록이 함께 뜬다. 유저는 많이 활동할 수록 다양한 뱃지로 자신의 마이페이지를 장식할 수 있다. 

**3. 네이버 카페 등업 시스템**

![image](https://user-images.githubusercontent.com/50407047/110244860-12b2bd80-7fa4-11eb-96b5-7339c4916b19.png)

네이버 카페 서비스에서는 멤버마다 등급을 부여하는 등급 관리 메뉴가 제공된다. 여기서는 자동 등업, 등업 게시판, 수동 등업으로 나눠져 있는데 내가 개발해야 할 커뮤니티는 자동 등업, 즉 유저가 뱃지의 조건을 채우면(특정 태그를 단 게시글을 100개 이상 쓴다) 뱃지가 자동으로 회원에게 부여되도록 구현해야 한다.

**결론**

- 각 태그에 해당하는 뱃지가 있다.

- 뱃지는 다음과 같이 회원 활동량을 기준으로 수여된다.

  이 외에도 기준을 추가하거나 삭제할 수 있도록 만든다.

  - 해당 태그를 사용해 작성한 게시글 개수
  - 해당 태그를 사용한 게시글에 작성한 댓글 개수
  - 가입일로부터 지난 날짜
  - 팔로워 수

- 뱃지에 대한 기준은 관리자 페이지에서 설정할 수 있다.

- 기준에 맞으면 회원에게 뱃지가 자동으로 수여된다.

- 유저는 받은 뱃지로 마이페이지를 꾸밀 수 있다.

### UI 프로토타입

UI 프로토타이핑 툴은 Figma를 사용했다. 

**관리자 화면**

- 뱃지를 추가, 수정, 조회, 삭제할 수 있다. 
- 뱃지를 추가, 수정할 때 뱃지 기준도 추가, 수정할 수 있다.

![image](https://user-images.githubusercontent.com/50407047/110245586-3a575500-7fa7-11eb-95db-e70a9dddbc0f.png)

![image](https://user-images.githubusercontent.com/50407047/110245556-1562e200-7fa7-11eb-930f-792360878388.png)

**마이페이지**

- 유저가 받은 뱃지는 유저 페이지에서 조회할 수 있다.
- 유저는 뱃지 순서를 변경할 수 있다.

![image](https://user-images.githubusercontent.com/50407047/110245680-9e7a1900-7fa7-11eb-9a2a-5fdc1a0d0244.png)

### ERD 설계

뱃지 시스템을 위한 테이블은 다음과 같다.

기본적으로 유저(user), 뱃지(badge), 뱃지기준(badge_standard), 평가(evalutation) 테이블이 있다. 여기에 다대다 관계를 위한 유저-뱃지(`user_badge`), 뱃지-태그(`badge_tag`) 관계형 테이블을 만들어주었다.

이때, 원래는 뱃지 평가 테이블 없이 뱃지 기준 테이블에 `가입일_count`, `팔로워수_count`, `게시글_count`, `댓글_count` 컬럼을 넣어 데이터를 저장하려고 했다. 그러나 평가되는 항목이 더 많아질 수 있음을 고려하여 확장성을 위해 평가(`badge_evalutation`) 테이블을 추가하였다. 

![image](https://user-images.githubusercontent.com/50407047/110246984-375f6300-7fad-11eb-80a0-c07ffb705226.png)

그렇게 해서 완성한 ERD는 다음과 같다.

![img](https://user-images.githubusercontent.com/50407047/105466251-4cbb4f00-5cd7-11eb-9075-35ad804753f5.png)

### 개발

관리자 화면에서 뱃지의 기준을 추가하고 수정하고 삭제할 수 있다.

**뱃지 기준 관리**

```java
@Controller
@RequestMapping("/badgeStan")
public class BadgeStanController {

  @Autowired
  BadgeStanService badgeStanService;

  @PostMapping("add")
  public String add(BadgeStan badgeStan) throws Exception {
    badgeStanService.add(badgeStan);
    return "redirect:../badge/" + badgeStan.getBadgeNo();
  }

  @PostMapping("update")
  public String update(BadgeStan badgeStan, int no) throws Exception {
    badgeStanService.update(badgeStan.setNo(no));
    return "redirect:../badge/" + badgeStan.getBadgeNo();
  }

  @GetMapping("delete")
  public String delete(BadgeStan badgeStan) throws Exception {
    badgeStanService.delete(badgeStan.getNo());
    return "redirect:../badge/" + badgeStan.getBadgeNo();
  }
}
```

뱃지 CRUD와 뱃지 기준 CRUD는 구현하기 어렵지 않았다. 

그러나 뱃지 조건에 맞는 유저를 확인하고 이를 수여하는 기능은 어떻게 구현해야 할 지 감이 잡히지 않았다. 

여태까지 구현한 Create 기능은 유저가 직접 폼에 값을 입력하여 제출할 때(게시글 Create), 혹은 버튼을 눌렀을 때(팔로우 Create) 실행되도록 하였다.

뱃지 수여도 다른 Create와 마찬가지로 특정 테이블(`user_badge` 관계형 테이블)에 튜플을 create하도록 구현해야 한다. create을 구현하는 것은 어렵지 않다. 그러나, 이 구현한 create 로직이 **언제 호출되도록 만들지**가 문제였다. 유저가 로그인을 할 때마다? 관리자가 '뱃지 수여하기' 버튼을 누를 때마다? 

그게 아니라 **자동으로** 로직이 실행되도록 만들어야 한다. 이를 위해 검색을 한 결과 배치 스케줄러라는 도구가 있다는 것을 발견했다. 

배치 스케줄러(Batch Scheduler)란 일괄 처리(Batch Processing) 작업이 설정된 주기에 맞춰 자동으로 수행되도록 지원해주는 도구를 말한다. 특정 업무(Job)를 원하는 시간에 처리할 수 있도록 지원한다는 특성 때문에 Job Scheduler라고도 불린다.

배치 스케줄러에는 Spring Batch와 Quartz가 대표적인데, 간단한 스케줄링의 경우 Quartz를 사용하면 편하다고 하여 Quartz를 사용하기로 하였다.

**뱃지 수집**

해당 어플은 특정 시간(새벽 3시)마다 회원들의 활동량과 뱃지 기준들을 비교해, 기준에 충족할 경우 회원이 뱃지를 수집하도록 만드는 (`DB`에 `insert`하는) 배치다.

```java
@Component
public class CollectJob {
  CollectService collectService;
  UserService userService;
  BadgeService badgeService;
  BadgeStanService badgeStanService;

  public CollectJob(CollectService collectService, UserService userService,
      BadgeService badgeService, BadgeStanService badgeStanService) {
    this.collectService = collectService;
    this.userService = userService;
    this.badgeService = badgeService;
  }

  // 초 분 시 일 월 요일
  @Scheduled(cron = "0 0 0 3 * *")
  public void add() throws Exception {
    List<Badge> badges = badgeService.list((String) null);
    List<User> users = userService.list(null);
    
    for (User user : users) {
      System.out.println(user.getNickname());
      List<Badge> collectedBadges = badgeService.list(user);
      List<Integer> collectedBadgeNos = new ArrayList<Integer>();
      
      for (Badge badge : collectedBadges) {
        collectedBadgeNos.add(badge.getNo());
      }
        
      for (Badge badge : badges) {
        Tag tag = badge.getTag();
        // 이미 가지고 있는 뱃지라면 건너뛰기
        if (collectedBadgeNos.contains(badge.getNo())) {
          continue;
        }
        // 가지고 있지 않은 뱃지 기준 충족되는지 알아보기
        List<BadgeStan> standards = badgeStanService.list(badge.getNo());
        // 전체 기준 갯수
        int totalStandards = standards.size();
        // 충족되는 기준 갯수
        int count = 0;
          
        for (BadgeStan standard : standards) {
          Map<String, Object> params = new HashMap<>();
          params.put("userNo", user.getNo());
          params.put("tagNo", tag == null? null : tag.getNo());
          params.put("evaluationNo", standard.getEvaluationNo());
          // 충족된다면 충족되는 기준 갯수를 하나씩 높인다.
          int userCount = collectService.getCount(params);
          if (userCount >= standard.getCount()) {
            count++;
          }
        }
          
        // 충족되는 기준 갯수와 뱃지의 전체 기준 갯수가 같으면 뱃지를 수여한다.
        if (totalStandards == count && totalStandards != 0) {
          collectService.add(new Collect().setUser(user).setBadge(badge));
        }
      }
    }
  }
}

```

![alarm](https://user-images.githubusercontent.com/50407047/110249055-709cd080-7fb7-11eb-999f-45fc42410778.JPG)

회원이 가지고 있지 않은 뱃지 중 기준에 충족하는 뱃지가 있다면 이를 새벽 3시에 자동으로 수여한다. 회원은 이를 알림창과 마이페이지에서 확인할 수 있다.

**마이페이지 꾸미기: 드래그 앤 드롭으로 뱃지 순서 변경하기**

받은 뱃지는 수집한 순서대로 마이페이지에 공개된다. 그러나 사용자 경험 측면에서 유저의 선호에 따라 순서를 변경할 수 있도록 만들고자 하였다. 백엔드 개발자라면 HTML, CSS보다 자바, 스프링 등 백엔드 관련 뱃지가 더 앞에 오길 원할 것이라는 생각에서다.

이때 드래그앤 드롭은 jQuery UI를 사용해 구현하였다. 문제는 드래그앤 드롭으로 순서를 변경했을 때, 변경한 순서를 `db` 에`update`해야 한다는 것이다. 따라서 순서가 변경되었을 때, 서버에 비동기 요청을 보내 서버가 `db`에 해당 유저의 전체 뱃지 순서를`update`하도록 만들었다. 

**뱃지 리스트**

유저가 드래그 앤 드롭으로 뱃지 순서를 변경할 때마다, **뱃지 번호가 변경된 순서대로 `,`로 연결된 문자열과 함께 서버에게 뱃지 순서를 업데이트하라는 비동기 요청**을 보낸다. 

![image](https://user-images.githubusercontent.com/50407047/110316118-9eccef80-804d-11eb-92ab-0482953abf92.png)

```jsp
<div class="col-3">
  <c:choose>
  	<c:when test="${user.no != loginUser.no && null ne user}">
       <ul class="badge-list">
     </c:when>
     <c:otherwise>
       <ul class="badge-list" id="sortable">
     </c:otherwise>
  </c:choose>
  <c:forEach items="${badgeList}" var="b">
         <li data-no="${b.no}">
           <img src="../../upload/badge/${b.photo}_160X160.png" /
         </li>
  </c:forEach>
      </ul>
<script>
$(function() {
    $("#sortable").sortable({
        start : function(e, ui) {
            $(this).attr('data-previndex', ui.item.index());
        },
        update : function(e, ui) {
            var arr = [];
            $("#sortable li")
                .each(function(index, element) {
                // 뱃지 번호를 변경된 "순서"대로 배열에 담는다.
                arr.push($(element).attr("data-no"));
            })
            $.ajax({
                type : "POST",
                url : "../ajax/collect/updateOrder", // 서버단 메소드 url
                data : {
                    // 배열을 문자로 바꿔 서버에게 전송한다.
                    "order" : arr.toString()
                },
                dataType : "text",
                success : function(data) {
                    
                }
            });
        }
    });
    $("#sortable").disableSelection();
});
</script>
```

**뱃지 순서 변경**

```java
@Controller("ajax.collectController")
@RequestMapping("/ajax/collect")
public class CollectController {
    
  @Autowired CollectService collectService;
  
  @PostMapping("updateOrder")
  public String updateOrder(String order, HttpSession session, HttpServletRequest request) throws Exception {
      
    String[] orders = order.split(",");
      
    List<Collect> collects = new ArrayList<>();
      
    for (int i = 0; i < orders.length; i++) {
      Collect collect = new Collect();
      collect.setUser((User) session.getAttribute("loginUser"));
      collect.setBadge(new Badge().setNo(Integer.parseInt(orders[i])));
      collect.setOrder(i);
      collects.add(collect);
    }
      
    collectService.updateAllOrder(collects);
      
    return "redirect:" + request.getHeader("Referer");
  }
}
```

**뱃지 변경 Service**

```java
@Service
public class DefaultCollectService implements CollectService {
  CollectDao collectDao;
  NotificationDao notificationDao;
  //..
  @Override
  public void updateAllOrder(List<Collect> collects) throws Exception {
    for (Collect collect : collects) {
      collectDao.updateOrder(collect);
    }
  }
  //..
}
```

