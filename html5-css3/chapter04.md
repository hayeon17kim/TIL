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



## 3. `<input>` 태그의 다양한 속성



## 4. 여러 데이터 나열해 보여주기

