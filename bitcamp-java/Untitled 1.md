## 클라이언트가 보낸 데이터 읽기

### GET 요청 데이터 읽기

- 웹 브라우저에  URL을 입력한 후 엔터를 치면 GET 요청이다.
- 웹 페이지에서 링크를 클릭하면(자바스크립트 처리하지 않은 상태)

 servlet path  물음표 변수=값; 서버에서는 어떻게 값을 꺼낼까? 값을 문자열로 리턴해주는 메서드가 있다. 이 메서드를 이용하여 값을 꺼낸다. 

```java
String name = req.getParameter("name");
String age = req.getParameter("age");

res.setContentType("text/plain;charset=UTF-8");
```



클라이언트가 알아서는 안되는 DB 관련 정보나 실행에 관련한 요청이기 때문에 web.inf 폴더에 잇는 파일은 클라이언트가 요청하지 않도록 만들었다. 