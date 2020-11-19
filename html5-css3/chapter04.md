# 폼 관련 태그들

## 1. 폼 만들기

- 폼(form): 사용자가 웹 사이트로 정보를 보낼 수 있는 모든 요소

  - 아이디와 비밀번호 입력, 로그인 버튼, 회원가입 창 등

- 폼의 동작 방식

  로그인할 때 아이디, 비밀번호를 입력을 하고 로그인 버튼을 클릭하면 **사용자가 입력한 내용이 웹 서버로 보내진다.** **서버**는 자신이 가지고 있는 **사용자 데이터베이스를 뒤져** 사용자가 보내 온 아이디와 비밀번호가 **서로 일치하는 정보인지 여부를 확인**하고 그 **결과를 브라우저에 보낸다.**

  - 폼과 관련된 작업: 정보 저장, 검색, 수정
    - **데이터베이스를 기반**으로 한다.

  - 텍스트 상자, 버튼 등 폼의 형태를 만드는 것은 HTML, CSS
  - 그 폼에 입력한 **사용자 정보를 처리**하는 것은 ASP, PHP, JSP 같은 **서버 프로그래밍**을 사용한다.



### `<form>`: 폼 만들기

```html
<form [속성="속성 값"]>
   여러 폼 요소
</form>
```

#### 속성

- `method`: 사용자가 입력한 내용을 서버 쪽 프로그램으로 어떻게 넘겨줄 지 지정
  - `get`: **주소 표시줄에 사용자가 입력한 내용이 그대로 드러난다**.
  - `post`: 사용자의 입력을 standard input으로 넘겨주기 때문에 입력 내용의 길이에 제한을 받지 않고 사용자가 입력한 내용이 드러나지 않는다. **대부분 이 값을 사용**
- `name`: **폼의 이름**을 지정한다. 한 문서 안에 여러 개의 `<form>` 태그가 있을 경우, 폼들을 구분하기 위해 사용한다.
- `action`: `<form>` **태그 안의 내용들을 처리해 줄** **서버 상의 프로그램을 지정**한다.
- `target`: `action` 속성에서 지정한 스크립트 파일을 현재 창이 아닌 다른 위치에 열도록 지정한다.

**예제: 폼 삽입하기**

![image](https://user-images.githubusercontent.com/50407047/98559261-bd161f00-22e9-11eb-9724-7131fa9c3d96.png)

```html
<form action="search.php" method="post">
  <input type="text" title="검색">
  <input type="submit" value="검색">
</form>
```



#### `autocomplete` 속성: 자동 완성 기능

- 사용자가 입력했던 내용을 기억했다가 비슷한 내용을 입력할 경우, 이전에 입력했던 내용을 힌트로 보여준다.
- 기본값은 `on`이다.
- 그러나 중요한 개인정보 혹은 인증번호 입력과 같이 한 번 사용하고 말 정보는 자동 완성 기능을 끄는 것이 좋다.
- 보통 브라우저 환경 설정 명령을 이용해 꺼야 하는데, `<form>` 태그의 `autocomplete` 속성을 `off`로 지정해 끌 수도 있다. 

```html
<form action="register.php" autocomplete="off">
</form>
```



### `<label>`: 폼 요소에 레이블 붙이기

- 레이블: 입력창 옆에 붙여 놓은 텍스트

- **폼 요소와 레이블 텍스트가 서로 연결되어 있다**는 것을 **브라우저가 알 수 있다.**

#### 레이블을 붙이는 두 가지 방법

1. `<label>` 태그 안에 폼 요소 넣기

```html
<label [속성="속성 값"]>레이블 <input> </lable>
```

2. `<label>` 요소와 폼 요소를 따로 사용하고, `<label>` 태그에서 `for` 속성을 이용하고, 폼 요소에서는 `id` 속성을 이용해 서로 연결

```html
<label for="id이름">레이블</label> 
<input id="id이름" [속성="속성 값"]>
```

두 번째 방법은 `label` 태그를 사용한 텍스트 부분과 사용자 입력을 받는 `<input>` 소스가 떨어져 있더라도 둘 사이를 쉽게 연결할 수 있다는 장점이 있다.

#### 라디오 버튼과 체크박스에서 사용하는 `<label>` 태그

- `<label>` 태그를 사용하지 않은 경우 **텍스트만 클릭하면 체크박스 버튼이 선택되지 않는다.** 반드시 체크박스 부분을 클릭해야 선택된다.
- `<label>` 태그를 사용할 경우 **텍스트만 클릭해도 라디오 버튼이 선택된다.** **텍스트 영역까지 클릭 범위가 확장되어 편리**하다. 최근 **대부분의 폼에서 이 방식을 사용**한다.

```html
	<form>
		<h3>수강 분야(다수 선택)</h3>
    <!-- <label> 태그 사용하지 않음 -->
    <ul>
			<li><input type="checkbox" value="grm">문법</li>
			<li><input type="checkbox" value="wr">작문</li>
			<li><input type="checkbox" value="rd">독해</li> 
		</ul>
		<h3>수강 과목</h3>
    
		<!-- <label> 태그 사용 -->
		<ul>
			<li>
				<label><input type="radio" name="subject" value="eng">영어 회화</label>
			</li>
			<li>
				<label><input type="radio" name="subject" value="ch">중국어회화</label>
			</li>
			<li>
				<label><input type="radio" name="subject" value="jp">일본어 회화</label>
			</li>
		</ul>
	</form>
```



### `<fieldset>`, `<legend>`: 폼 요소 그룹으로 묶기

**하나의 폼 안에서 여러 구역을 나누어 표시**하려고 할 때 `<fieldset>`, `<legend>` 태그를 사용한다.

- `<fieldset>`: 태그 사이의 폼을 하나의 영역으로 묶고 외곽선을 그려준다.
- `<legend>`: `<fieldset>` 태그로 묶은 그룹에 제목을 붙여 준다.

**예제: 폼 요소 그룹으로 묶기**

![image](https://user-images.githubusercontent.com/50407047/98563163-3152c180-22ee-11eb-8ba4-0407298a8943.png)

```html
	<form>
		<fieldset>
			<legend>개인 정보</legend>
			<ul>
				<li>
					<label for="name">이름</label>
					<input type="text" id="name">
				</li>
				<li>
					<label for="mail">메일 주소</label>
					<input type="text" id="mail">
				</li>
			</ul>
		</fieldset>
		<fieldset>
			<legend>로그인 정보</legend>
			<ul>
				<li>
					<label for="id">아이디</label>
					<input type="text" id="id">
				</li>
				<li>
					<label for="pwd">비밀번호</label>
					<input type="text" id="pwd">
				</li>
			</ul>
		</fieldset>
	</form>
```



## 2. 사용자 입력을 위한 `<input>` 태그

### `<input>`: 입력 항목 만들기

```html
<input type="유형" [속성="속성 값"]>
```

웹에서의 **폼**은 크게 **사용자가 입력하는 부분**과 **입력한 내용을 서버로 보내는 버튼 부분**으로 나눌 수 있다. 이때 사용자가 입력하는 부분을 만들 때 사용하는 태그가 `<input>`태그이다. 체크박스나 로그인 버튼처럼 사용자가 클릭하는 버튼도 `<input>` 태그를 사용한다.

`<input>` 태그로 만들 수 있는 폼 요소는 굉장히 다양한데 이는 `<input>` 태그 안에 있는 `type` 속성을 이용해 구분한다.



#### `id` 속성 사용하기

여러 번 사용된 폼 요소를 구분하기 위해 사용하는 것이 `id` 속성이다. `id` 속성을 지정해 놓으면 `<label>` 태그를 이용해 캡션을 붙일 수도 있고, CSS를 이용해 **각 요소마다 다른 형태**로 꾸밀 수 있다.

`id` 속성 값은 최소 한 개 이상의 문자여야 하고, 공백이 있어서는 안 된다. 

```html
<input type="text" id="user-name" size="10">
<input type="text" id="addr" size="60">
```



#### `<input>` 태그의 `type` 속성에서 사용 가능한 유형

| 유형           | 설명                                                         |
| -------------- | ------------------------------------------------------------ |
| hidden         | 사용자에게는 보이지 않지만 서버로 넘겨지는 값을 가진다.      |
| text           | 한 줄짜리 텍스트를 입력할 수있는 텍스트 상자를 넣는다.       |
| search         | 검색 상자를 넣는다.                                          |
| tel            | 전화번호 입력 필드를 넣는다.                                 |
| url            | URL 주소를 입력할 수 있는 필드를 넣는다.                     |
| email          | 메일 주소를 입력할 수 있는 필드를 넣는다.                    |
| password       | 비밀번호를 입력할 수 있는 필드를 넣는다.                     |
| datetime       | 국제 표준시(UTC)로 설정된 날짜와 시간(연, 월, 일, 시, 분, 초, 분할 초)를 넣는다. |
| datetime-local | 사용자가 있는 지역을 기준으로 날짜와 시간(연, 월, 일, 시, 분, 초, 분할초)를 넣는다. |
| date           | 사용자 지역을 기준으로 날짜(연, 월, 일)를 넣는다.            |
| month          | 사용자 지역을 기준으로 날짜(연, 월)를 넣는다.                |
| week           | 사용자 지역을 기준으로 날짜(연, 주)를 넣는다.                |
| time           | 사용자 지역을 기준으로 시간(시, 분, 초, 분할 초)를 넣는다.   |
| number         | 숫자를 조절할 수 있는 화살표를 넣는다.                       |
| range          | 숫자를 조절할 수 있는 슬라이드 막대를 넣는다.                |
| color          | 색상 표를 넣는다.                                            |
| checkbox       | 주어진 항목에서 2개 이상 선택 가능한 체크박스를 넣는다.      |
| radio          | 주어진 항목에서 1개만 선택할 수 있는 라디오 버튼을 넣는다.   |
| file           | 파일을 첨부할 수 있는 버튼을 넣는다.                         |
| submit         | 서버 전송 버튼을 넣는다.                                     |
| image          | submit 버튼 대신 사용할 이미지를 넣는다.                     |
| reset          | 리셋 버튼을 넣는다.                                          |
| button         | 버튼을 넣는다.                                               |



### `type="hidden"`: 히든 필드 만들기

화면상의 폼에는 보이지 않지만 사용자가 입력을 마치고 폼을 서버로 전송할 때 서버로 함께 전송되는 요소이다. 사용자에게 굳이 보여줄 필요가 없지만 관리자가 알아야 하는 것을 히든 필드로 입력한다.

```html
<input type="hidden" name="이름" value="서버로 넘길 값">
```

![image](https://user-images.githubusercontent.com/50407047/98566021-a247a880-22f1-11eb-9d8e-d45a06f1e936.png)

이 부분은 아이디가 중복되는지 체크하는 것으로 사용자가 회원가입 양식을 작성하고 서버로 전송할 때 hidden 필드의 값도 함게 서버로 전송됨을 뜻한다(여기서 히든 필드의 값은 `value` 부분의 값을 뜻하고 `Y`는 아이디 중복확인을 했는지 여부를 뜻한다.) 이처럼 히든 필드를 삽입할 때는 **`name` 속성을** 이용해 히든 필드의 이름을 지정하고 그에 대한 값은 **`value` 속성**을 이용해 서버로 넘겨준다.



### `type="text"`: 텍스트 필드 만들기

```html
<input type="text" [속성="속성 값"]>
```

- 한 줄짜리 일반 텍스트를 입력하는필드
- 폼에서 가장 많이 사용하는 요소
- 아이디, 이름, 주소 등 텍스트를 입력할 때 쓰인다.

| 속성      | 설명                                                         |
| --------- | ------------------------------------------------------------ |
| name      | 텍스트 필드를 **구별**할 수 있도록 이름을 붙인다.            |
| size      | 텍스트 필드의 **길이를 지정**한다. 즉 화면에 몇 글자가 보이도록 할 것인지 지정한다. 예를 들어 최대 입력 가능한 글자 수가 10개인데 size를 5로 지정하면 텍스트 필드 크기가 5개 글자 크기에 맞추어져 나머지 5개 글자는 화면에 보이지 않는다. |
| value     | 텍스트 필드 요소가 화면에 표시될 때 **텍스트 필드 부분에 표시될 내용**이다. |
| maxlength | 텍스트 필드에 **입력할 수 있는 최대 문자 개수**를 지정한다.  |

```html
<input type="text" title="검색" size="5">
```

![when-set-size-5](https://user-images.githubusercontent.com/50407047/98566710-724cd500-22f2-11eb-8940-375d38090bfd.png)

```html
<input type="text" title="검색" value="입력해주세요">
```

![image](https://user-images.githubusercontent.com/50407047/98567017-cfe12180-22f2-11eb-920e-6a341065430f.png)

### `type="password"`: 비밀번호 입력란 만들기

사용자가 입력하는 내용이 화면에 표시되지 않고 `*` 등으로 표시된다. 속성되 `value` 속성이 없다는 점만 제외하면 텍스트 필드와 같다.

**예제: 로그인 폼 만들기**

![image](https://user-images.githubusercontent.com/50407047/98567961-e8057080-22f3-11eb-8682-0d2098970f9b.png)

```html
	<form>
		<fieldset>
			<label>아이디: <input type="text" id="user_id" size="10"></label>
			<label>비밀번호: <input type="password" id="user_pwd" size="10"></label>
			<input type="submit" value="로그인">
		</fieldset>
	</form>
```



### `type="search", type="url", type="email", type="tel"`: 분화된 텍스트 필드

#### `type="search"`: 검색 상자 만들기

크롬 브라우저나 MS 엣지 브라우저 등 일부 브라우저에서는 검색 창에 검색어를 입력했을 때 오른쪽에 검색어를 쉽게 지울 수 있는 버튼이 뜬다.

![image](https://user-images.githubusercontent.com/50407047/98568408-6a8e3000-22f4-11eb-9b15-017583f9412b.png)

```html
<input type="search" [속성="속성 값"]>
```

#### `type="url"`: URL 입력란 만들기

- 이 필드에는 반드시 'http://'로 시작하는 사이트 주소를 입력해야 한다.
- 공백이 없고 영문자와 마침표, 슬래시로만 이루어져 있다는 점을 기준으로 구분한다.



#### `type="email"`: 메일 주소 입력란 만들기

- **브라우저 자체에서** 사용자가 입력한 내용이 **메일주소 형식에 맞는지 자동으로 체크**해주기 때문에 이메일 필드를 손쉽게 만들 수 있다.

#### `type="tel"`: 전화번호 입력란 만들기

- 전화번호는 지역마다 형식이 다르기 때문에 사용자가 입력한 값을 체크하지 않고 사용자가 입력한 정보가 일반 텍스트가 아니라 전화번호라는 사실을 인식한다. 이 값을 이용하면 바로 전화를 걸 수 있도록 말이다.

위 `type`들은 데스크톱 웹 브라우저에서 보기에는 큰 변화가 없어 보이지만  모바일 기기의 웹 브라우저에서 확인해보면 이메일 주소나 전화번호를 입력할 때 가상 키보드 배열이 달라진다.

```html
	<ul>
		<li>
			<label for="user-name">이름</label>
			<input type="text" id="user-name">
		</li>
		<li>
			<label for="mail">메일 주소</label>
			<input type="email" id="mail">
		</li>
		<li>
			<label for="phone">전화</label>
			<input type="tel" id="phone">
		</li>
		<li>
			<label for="homp">홈페이지주소</label>
			<input type="url" id="homp">
		</li>
	</ul>
```

#### `type="number"`: 숫자 입력

- 입력창에 숫자를 입력하는 것이 기본이지만, 브라우저에 따라 스핀 박스가 표시되기도 한다. (창 오른쪽에 화살표를 표시에 화살표를 클릭하면 숫자를 증감시킬 수 있게 한 것이다)

```html
<b>주문 개수: </b> <input type="number" min="1" max="5" value="1"> 개
```

#### `type="range"`: 슬라이드 막대로 숫자 지정하기

- 속성
  - `min`: 최솟값. 기본 최솟값은 0
  - `max`: 최댓값. 기본 최댓값은 100
  - `step`: 숫자 간격 지정
  - `value`: 필드에 표시할 초기값

```html
<ul>
  <li>
    <label class="reg" for="member">참여인원<small>(최대10명)</small></label>
    <input type="number" id="member" value="1" min="0" max="10" step="1">
  </li>
  <li>
    <label class="reg" for="stuffs">지원물품<small>(1인당 5개)</small></label>
    <input type="number" id="stuffs" value="1" min="0" max="50" step="5">
  </li>
  <li>
    <label class="reg" for="satis">희망단계<small>(하,중,상)</small></label>
    <input type="range" id="satis" value="1" min="1" max="3" step="1">
  </li>
</ul>
```

#### `type="radio"`, `type="checkbox"`: 라디오 버튼과 체크박스 넣기

속성

- `name`: 여러 개 있을 때 구분하기 위해 이름을 지정한다. 라디오 버튼은 여러 개 중에서 하나만 선택하는 것이기 때문에 관련 그룹끼리 `name` 속성 값을 똑같이 만든다.
- `value`: 체크박스를 서버로 알려 줄 때 넘길 값을 지정한다. 필수 속성이다.
- `checked`: 기본으로 선택해 놓을 항목이 있다면 이 속성을 사용한다. 기본은 아무것도 선택되어 있지 않다.

![image](https://user-images.githubusercontent.com/50407047/99178657-c45d8280-2758-11eb-8bd5-97cf273b254c.png)

```html
<fieldset>
  <legend>신청 과목</legend>
  <p>이 달에 신청할 과목을 선택하시오 (1과목만 가능)</p>
  <label><input type="radio" name="subject" value="speaking">회화</label>
  <label><input type="radio" name="subject" value="grammer">문법</label>
  <label><input type="radio" name="subject" value="writing">작문</label>
</fieldset>
<fieldset>
  <legend>메일링</legend>
  <p>메일로 받고 싶은 뉴스 주제를 선택해주세요. (복수 선택 가능)</p>
  <label><input type="checkbox" name="mailing1" value="news">해외 단신</label>
  <label><input type="chekbox" name="mailing2" value="dialog">5분 회화</label>
  <label><input type="checkbox" name="mailing3" value="pops">모닝팝스</label>
</fieldset>
```



#### `type="color"`: 색상 선택 상자 표시

HTML4에서는 자바스크립트를 이용해 색상표를 직접 프로그래밍해 통합시켜야 했지만 HTML5에서는 간단히 `type="color"` 속성만 이용하면 된다.

![image](https://user-images.githubusercontent.com/50407047/99178713-54033100-2759-11eb-9f73-ea1eff33e317.png)

```html
<body>
	<p>태그의 색을 지정해주세요.</p>
	<label>태그명<input type="text"></label>
	<label>태그색상<input type="color" value="#00ff00"></label>
</body>
```



#### `type="date"`, `type="month"`, `type="week"`: 날짜 표시하기

![image](https://user-images.githubusercontent.com/50407047/99178775-fde2bd80-2759-11eb-992d-4afb4a0acf92.png)
![image](https://user-images.githubusercontent.com/50407047/99178783-089d5280-275a-11eb-9c9a-10542c6c6f95.png)
![image](https://user-images.githubusercontent.com/50407047/99178787-1521ab00-275a-11eb-9405-6dc1e63566db.png)

```html
	<label><input type="date">date</label>
	<label><input type="month">month</label>
	<label><input type="week">week</label>
```



#### `type="time"`. `type="datetime"`, `type="datetime-local"`: 시간 지정하기

```html
<input type="time | datetime | datetime-local">
```

속성

- `min`: 날짜나 시간의 최솟값
- `max`: 날짜나 시간의 최댓값
- `step`: 스핀박스 화살표를 누를 때마다 날짜나 시간을 얼마나 조절할지 지정
- `value`: 화면에 표시할 초기값 지정
  - 시간: 00:00 ~ 23:59
  - datetime, datetime-local: 날짜 다음 키워드 T를 쓰고 24시간제로 시간을 지정 

![image](https://user-images.githubusercontent.com/50407047/99178874-02f43c80-275b-11eb-9dce-c87ac9ba95c3.png)

```html
<h3>원하는 대관시간을 선택하세요!(오늘)</h3>
<label>시작 시간<input type="time" value="09:00" id="start1"></label>
<label>종료 시간<input type="time" value="18:00" id="end1"></label>

<h3>대관 시간을 선택하세요(다른 날짜)</h3>
<label>시작 시간<input type="datetime-local" value="2020-11-15T09:00" id="start2"></label>
<label>종료 시간<input type="datetime-local" value="2020-11-15T18:00" id="end2"></label>
```



### 버튼

#### `type="submit"`, `type="reset"`: 서버 전송, 리셋 버튼 넣기

- 리셋버튼: `<input>` 요소에 입력된 모든 정보를 재설정해 사용자가 입력한 내용을 모두 지울 수 있다. `value` 속성을 사용해 버튼에 표시할 내용 지정
- submit 버튼: 사용자가 폼에 입력한 정보를 서버로 전송하는 `submit` 버튼을 넣는다. 처음에 **`<form>` 태그에서 지정한 폼 처리 프로그램**에 넘겨진다.

```html
<input type="submit | reset" [value="버튼내용"] [속성="속성 값"]
```

```html
<form action="register.php" method="post">
  <label> 메일 주소 <input type="text"></label>
  <input type="submit" value="제출">
  <input type="reset" value="다시입력">
</form>
```



#### `type="image"`: 이미지 버튼 넣기

`submit` 버튼 대신 전송 이미지를 넣을 수 있다. 

```html
<input type="image" src="경로" alt="대체 텍스트">
```

```html
<label>아이디 <input type="text" size="5"></label>
<label>비밀번호 <input type="password" size="15"></label>
<input type="image" id="butt" src="images/login.jpg" alt="login">
```



#### `type="button"`: 버튼 넣기

**자체 기능은 없고** 오직 버튼만 넣기 때문에 **스크립트 함수 등을 연결해 사용**한다.

```html
<input type="button" value="새 탭 열기" onclick="window.open()">
```



#### `type="file"`파일 첨부하기

실제 파일 첨부 버튼에 표시되는 내용은 웹 브라우저에 따라 달라진다.

![image](https://user-images.githubusercontent.com/50407047/99179024-d93c1500-275c-11eb-9b5d-db0e3d915a33.png)

```html
<label> 첨부파일 <input type="file"></label>
```

## 3. `<input>` 태그의 다양한 속성

### `autofocus` 속성: 입력 커서 표시

페이지를 불러오자마자 폼의 요소 중에서 원하는 요소에 마우스 커서를 표시할 수 있다.

```html
<label class="reg" for="uname">이름</label>
<input type="text" id="username" autofocus required>
```

### `placeholder` 속성: 힌트 표시

사용자가 텍스트를 입력할 때 도움이 되도록 적당한 힌트 내용을 표시하다가 그 필드를 클릭하면 내용이 사라지도록 만들 수 있다. 사용자에게 해당 필드에 어떤 내용을 입력해야 할 지 알려줄 수 있어 편리하다.

```html
<label class="reg" for="uid">학번</label>
<input type="text" id="uid" placeholder="하이픈없이 입력">
```

### `readonly` 속성: 읽기 전용 필드 만들기

필드 안에 내용은 있지만 사용자에게 내용을 보여 주기만 하고 입력은 할 수 없게 만든다. readonly속성은 true나 false 값을 지정해도 되고 간단히 readonly라고만 쓰면 `readonly="readonly"`라고 써도 true로 인식한다.

```html
	<label class="reg" for="subj">영어회화</label>
	<input type="text" id="subj" value="오전 9:00~11:00" readonly>
```

### `required` 속성: 필수 필드 지정

`submit` 버튼을 눌러 폼을 서버로 전송할 대 필수 필드에 필요한 내용이 모두 채워졌는지 검사한다. 만약 필수 필드로 지정된 필드를 비워 두고 `submit` 버튼을 누른다면 오류 메시지를 표시한다. 필수 필드는 **브라우저에서 직접 체크하는 것**이므로 오류 메시지 내용은 브라우저들마다 다르게 나타난다.

```html
<label class="reg" for="uname">이름</label>
<input type="text" id="uname" autofocus required>
```

### `min`, `max`, `step` 속성

`<input>` 유형이 date, datetime, datetime-local, month, week, time, number, range인 경우에만 사용할 수 있다.

### `size`, `minlength`, `maxlength`속성: 길이, 최소 길이, 최대 길이 속성

- `size`: 한 줄짜리 텍스트와 관련된 필드에서 화면에 몇 글자까지 보이게 할 지 지정
- `maxlength`, `minlength`: 사용자가 최대 몇 글자부터 몇 글자까지 입력할 지 지정
- `minlength`: 크롬과 안드로이드 브라우저에서만 지원

```html
<label>아이디: <input type="text" id="user_id" size="10" minlength="4" maxlength="15"></label>
<small style="color:red;"> 4~15자리 영문과 숫자</small>
```



### 그 외 속성

- `formaction`: 실행할 프로그램을 연결한다. `type="submit"`이나 `type="image"`일 때 사용할 수 있다.
- `formenctype`: 서버로 폼을 전송했을 때 폼 데이터를 어떤 방식으로 해석할 것인지 지정한다. type이 `sumbmit`이나 `image`일 때 사용할 수 있다.
- `formmethod`: 서버로 폼을 전송하는 방식(`get`, `post` 등)을 지정한다. 이미 `form` 태그 안에서 지정한 방식이 있어도 그 방식은 무시된다.
- `formnovalidate`: `<form>` 태그 안에 `novalidate` 속성이 있어 서버로 전송할 때 폼 데이터가 유효한지 여부를 표시할 수 있는데, `<input>` 태그 안에서도 이 속성을 이용해 유효성 여부를 표시할 수 있다.
- `formtarget`: 폼 데이터를 서버로 전송한 후 서버의 응답을 어디에 표시할 것인지 타깃 지정
- `height, width`: `type="image"` 일 때 너비와 높이 지정
- `list`: `<datalist>`에 미리 정의해 놓은 옵션 값을 `<input>` 안에 나열해 보여준다. 
- `multiple`: `type="email"`이나 `type="file"`일 때 두 개 이상의 값을 입력한다. `<input>` 태그 안에서 속성 이름만 표시하면 된다.



### 실습: 상품 주문서 작성

![image](https://user-images.githubusercontent.com/50407047/99181715-95a0d580-2773-11eb-95d9-e75953db8021.png)

```html
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Document</title>
	<style>
		.container {
			width: 600px;
			margin: 0 auto;
		}
	  ul {
			list-style-type: none;
		}

		label.title {
			font-weight: bold;
			width: 80px;
			float: left;
		}

		div.centered {
			text-align:center;
		}

		fieldset {
			margin: 15px 10px;
		}

		fieldset legend {
			font-weight: bold;
			font-size: 18px;
			color: purple;
		}

		ul li {
			margin-bottom: 10px;
		}
	</style>
</head>
<body>
	<fieldset>
		<legend>개인 정보</legend>
		<ul>
			<li>
				<label for="uname" class="title">이름</label>
				<input type="text" id="uname" placeholder="여백없이 입력">
			</li>
			<li>
				<label for="tel1" class="title">연락처</label>
				<input type="text" id="uname" placeholder="연락가능한 번호">
			</li>
		</ul>
	</fieldset>
	<fieldset>
		<legend>배송지 정보</legend>
		<ul>
			<li>
				<label for="addr" class="title">주소</label>
				<input type="text" size=40 id="addr" required>
			</li>
			<li>
				<label for="tel2" class="title">전화번호</label>
				<input type="text" id="tel2" required>
			</li>
			<li>
				<label for="comment" class="title">메모</label>
				<input type="text" size=40 rows="3" id="comment" required>
			</li>
		</ul>
	</fieldset>
	<fieldset>
		<legend>주문 정보</legend>
		<ul>
			<li>
				<label><input type="checkbox">과테말라 안티구아</label>
				<label><input type="number" value="0" min="0" max="5">개</label>
			</li>
			<li>
				<label><input type="checkbox">인도네시아 만델링</label>
				<label><input type="number" value="0" min="0" max="5">개</label>
			</li>
			<li>
				<label><input type="checkbox">탐라는도다(블렌딩)</label>
				<label><input type="number" value="0" min="0" max="5">개</label>
			</li>
		</ul>
	</fieldset>
	<div>
		<input type="submit" value="조회하기">
		<input type="reset" value="다시 작성">
	</div>
</body>
```





## 4. 여러 데이터 나열해 보여주기

### `<select>`, `<optgroup>`, `<option>`: 드롭다운 목록 만들기

#### `<select>` 태그

속성

- `size`: 화면에 표시될 드롭다운 메뉴의 항목 개수를 지정한다. 크롬 브라우저의 경우 size에서 지정한 개수보다 하나 더 많은 옵션이 표시된다. 
- `multiple`: 브라우저 화면에 여러 개의 옵션이 함께 표시되면서 `Ctrl` 키를 누른 상태로 드롭다운 메뉴에 있는 여러 항목을 선택할 수 있다.

#### `<option>` 태그

-  `value`: 옵션을 선택했을 때 서버로 넘겨질 값
- `selected`: 화면에 표시될 때 기본으로 선택되어 있는 옵션 지정

#### `<optgroup>` 태그: 옵션끼리 묶기

![image](https://user-images.githubusercontent.com/50407047/99182057-f03b3100-2775-11eb-8332-8d583fb073ad.png)

```html
	<label class="reg" for="class">학과</label>
	<select size="5" id="class" multiple>
		<optgroup label="공과대학">
			<option value="archi">건축공학과</option>
			<option value="mechanic">기계공학과</option>
			<option value="indust">산업공학과</option>
			<option value="elec">전기전자공학과</option>
			<option value="computer"></option>
			<option value="chemical">화학공학과</option>
		</optgroup>

		<optgroup label="인문대학">
			<option value="history">사학과</option>
			<option value="lang">어문학부</option>
			<option value="phil">철학</option>
		</optgroup>
	</select>
```



### `<datalist>` 태그, `<option>` 태그

`<select>` 대신 `<datalist>` 를 선택하면 데이터 목록 중에서 값을 선택하도록 만들 수 있다. 즉 텍스트 필드에 직접 값을 입력하는 것이 아니라 데이터 목록에 제시한 값 중에서 선태갛면 그 값이 자동으로 입력되는 것이다. 

![image](https://user-images.githubusercontent.com/50407047/99183040-6a6eb400-277c-11eb-8658-60efa009f5d0.png)

```html
<input type="text" id="interest" list="choices">
<datalist id="choices">
  <option value="grammar" label="문법"></option>
  <option value="voca" label="단어"></option>
  <option value="speaking" label="회화"></option>
  <option value="writing" label="작문"></option>
</datalist>
```



### `<textarea>`: 여러 줄 입력하는 텍스트 영역 만들기

게시판에서 게시물을 입력하거나 회원가입 양식에서 사용자 약관을 표시할 때 자주 사용한다. `col` 속성과 `rows` 속성을 사용한다.

![image](https://user-images.githubusercontent.com/50407047/99183141-1d3f1200-277d-11eb-8512-c9032b14a68b.png)

```html
<textarea name="intro" cols="60" rows="10">
  텍스트 텍스트 텍스트 텍스트 텍스트 텍스트 텍스트 텍스트 
  텍스트 텍스트 텍스트 텍스트 텍스트 텍스트 텍스트 텍스트 
  텍스트 텍스트 텍스트 텍스트 텍스트 텍스트 텍스트 텍스트 
  텍스트 텍스트 텍스트 텍스트 텍스트 텍스트 텍스트 텍스트 
  텍스트 텍스트 텍스트 텍스트 텍스트 텍스트 텍스트 텍스트
  텍스트 텍스트 텍스트 텍스트 텍스트 텍스트 텍스트 텍스트 
  텍스트 텍스트 텍스트 텍스트 텍스트 텍스트 텍스트 텍스트 
  텍스트 텍스트 텍스트 텍스트 
</textarea>
```



## 기타 다양한 폼 요소들

### `<button>` : 버튼 넣기

```html
<form>
  <button type="submit" class="subm">
    <image src="images/tick.png">전송하기
   </button>
</form>
```

### `<output>`: 계산 결과

`<output>` 태그는 입력하는 값이 계산 결과라는 것을 브라우저에게 알려준다.

![image](https://user-images.githubusercontent.com/50407047/99183572-41e8b900-2780-11eb-95a6-0c70fda4490c.png)

```html
<form oninput="result.value=parseInt(num1.value)+parseInt(num2.value)">
  <input type="number" name="num1" value="0">
  +<input type="number" name="num2" value="0">
  =<output name="result" for="num"></output>
</form>
```

자바스크립트 함수가 두 수의 합을 구해준다. 결과 값만 골라 내 다른 연산을 할 수도 있고, 스크립트 프로그래밍에서 조건 처리에 이용할수 있다. 

### `<progress>`: 진행 상태 보여주기

- `value`: 작업 진행 상태를 나타내며 부동 소수점으로 표현한다.
- `max`: 작업이 완료되려면 얼마나 많은 작업을 해야 하는지 부동 소수점으로 표현한다.

![image](https://user-images.githubusercontent.com/50407047/99183678-ed920900-2780-11eb-8576-d73174461021.png)

```html
<ul>
  <li>
    <label>10초 남음</label>
    <progress value="50" max="60"></progress>
  </li>
  <li>
    <label>진행률 30%</label>
    <progress value="30" max="100"></progress>
  </li>
</ul>
```

자바스크립트를 이용하면 `progress` 막대의 진행을 순차적으로 계속 진행시켜 마치 애니메이션처럼 보이게 할 수 있다. 

### `<meter>`: 값이 차지하는 크기 표시

진행 상황을 나타내는 `progress` 태그와 달리 `meter` 태그는 전체 크기 중에서 얼마나 차지하는 지 표현할 때 사용한다. (사용량, 투표율)

![image](https://user-images.githubusercontent.com/50407047/99183774-a3f5ee00-2781-11eb-88e0-4427fc8da933.png)

```html
<ul>
  <li>
    <label>점유율 0.8</label>
    <meter value="0.8"></meter>
  </li>
  <li>
    <label>사용량 64%</label>
    <meter min="0" max="100" value="64"></meter>
  </li>
  <li>
    <label>트래픽 초과</label>
    <meter min="1024" max="10240" low="2048" high="8192" value="9216"></meter>
  </li>
  <li>
    <label>적절한 트래픽</label>
    <meter value="0.5" optimum="0.8"></meter>
  </li>
</ul>
```



### 실습

![image](https://user-images.githubusercontent.com/50407047/99184123-28e20700-2784-11eb-9b6a-f04b2242eecb.png)

```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <style>
      #container { 
        width:520px;
        border:1px solid black;
        padding:20px 40px;
        margin:0 auto;
      }
      fieldset { margin-bottom:15px; }
      legend { font-weight:bold; }
      ul {list-style-type: none;}
      li { line-height:30px;}
    </style>
  </head>
  <body>
    <div id="container">
      <h2>프론트엔드 개발자 지원서</h2>
      <p>HTML, CSS, JavaScript에 대한 기술적 이해와 경험이 있는 분을 찾습니다.</p>
      <hr>
      <form>
        <h4>개인정보</h4>
        <ul>
          <li>
            <label><input type="text" id="name" autofocus placeholder="공백 없이 입력하세요"></label>
          </li>
          <li>
            <label><input type="tel"></label>
          </li>
        </ul>
        <h4>지원분야</h4>
        <ul>
          <li>
            <label><input type="radio" name="field" value="an">웹 퍼블리싱</label>
          </li>
          <li>
            <label><input type="radio" name="field" value="pd">웹 애플리케이션 개발</label>
          </li>
          <li>
            <label><input type="radio" name="field" value="eng">개발환경 개선</label>
          </li>
        </ul>
        <h4>지원동기</h4>
        <label><textarea cols="60" rows="5" placeholder="본사 지원 동기를 간략히 써 주세요"></textarea></label>
        <div>
          <input type="submit" value="접수하기">
          <input type="reset" value="다시 쓰기">
        </div>
      </form>
    </div>
  </body>
</html>
```