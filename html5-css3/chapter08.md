## CSS 박스모델

### CSS와 박스 모델

#### 블록 레벨 요소와 인라인 레벨 요소

블록레벨 요소는 태그를 사용해 요소를 삽입했을 때 혼자 한 줄을 차지하는 요소이다. 즉 해당 요소의 너비가 100%라는 의미이다. 너비나 마진, 패딩 등을 이용해 크기나 위치를 지정하려면 블록 레벨 요소여야 한다. 

인라인 레벨 요소는 줄을 차지하지 않는요소이다. 즉 화면에 표시되는 콘텐츠만큼만영역을차지하고 나머지 공간에는 다른요소가 올 수 있다.

- 블록 레벨 요소: `<p>`, `<h1>`~`<h6>`, `<ul>`, `<ol>`, `<div>`, `<blockquote>`, `<form>`, `<hr>`, `<table>`, `<fieldset>`, `<address>`
- 인라인 레벨 요소: `<img>`, `<object>`, `<br>`, `<sub>`, `<sup>`, `<span>`, `<input>`, `<textarea>`, `<label>`, `<button>`

#### 박스 모델(box model): 박스 형태의 콘텐츠

블록 레베르이 요소들은 모두 박스 형태이다. 웹 문서 안에 여러 요소들을 원하는 위치에 배치하려면 CSS 박스 모델에 대해 잘 알고 있어야 한다. 그래야만 한 줄에 배치할 지, 줄을 바꿔 배치할 지, 요소와 요소 사이의 간격을 어떻게 조절할 지 결정할 수 있기 때문이다. 

#### width, height 속성: 콘텐츠 영역의 크기

```css
width: <크기> | <백분율> | <auto>
height: <크기> | <백분율> | <auto>
```

- 크기: 너비나 높이 값을 px나 cm 같은 단위와 함께 수치로 지정
- 백분율: **박스 모델을 포함하는 부모 요소를 기준**으로 너비나 높이 값을 백분율(%)로 지정한다.
- auto: 박스 모델의 너비와 높이 값이 **콘텐츠 양에 따라 자동으로 결정**된다.

##### 실제 콘텐츠 크기 계산하기

CSS 박스 모델의 width 속성은 콘텐츠 영역의 너비를 말하기 때문에 여러 요소를 웹 문서에 배치할 때 실제 박스가 차지하는 너비는 width 값에 좌우 패딩 두께와 좌우 테두리 두께를 합쳐 계산한다. 그러나 인터넷 익스플로러 6에서 CSS를 처리할 때는 width 속성을 컨텐츠 영역의 너비 값이 아니라 패딩과 마진, 테두리까지 모두 포함된 박스의 너비로 인식한다. 

- 모던 브라우저에서 박스 모델의 전체 너비 = width 값 + 좌우 패딩 + 좌우 테두리
- 인터넷 익스플로러 6에서 박스 모델의 width = 콘텐츠 너비 + 좌우 패딩 + 좌우 테두리

#### display 속성: 화면 배치 방법 결정하기

- `display: none | contents | block | inline | inline-block | dtable | table-cell 등`

display 속성은 해당 요소가 화면에 어떻게 보일지를 지정할 때 사용한다. 

##### block 속성 값

인라인 레벨 요소도 블록 레벨 요소로 바꿀 수 있따.

##### inline 속성 값

주로 목록에서 사요애된다. `<li>` 태그는 원래 블록 레벨 요소이기 때문에 각 항목은 수직으로 배치된다. 그러나 목록 항목에 `display: inline`을 지정하면 항목을 한 줄로 배치할 수 있고 목록을 수평 내비게이션용으로 사용할 수 있다.

##### inline-block 속성 값

웹 요소를 inline으로 지정하면 한 줄로 배치할 수는 있지만 너비나 높이, 위 아래 마진, float 같은 값이 정확이 적용되지 않는다. 요소를 인라인 레벨로 배치하면서 내용에는 블록 레벨 속성을 지정하고 싶다면 display 속성 값 중 `inline-block`을 사용하면 된다. 이 속성은 블록 레벨 요소와 인라인 레벨 요소 두가지 특성을 모두 가진다. 

![image](https://user-images.githubusercontent.com/50407047/100541104-f573a200-3284-11eb-9eed-48fc64b6405e.png)

```html
<style>
  nav {
    width:100%;
    height:60px;
    background-color: #069;
  }
  nav ul {
    list-style-type: none;
  }
  nav ul li {
    display: inline-block;
    margin:20px;
  }
  nav ul li a {
    color: white;
    text-decoration: none;
  }
</style>
</head>
<body>
  <nav>
    <ul>
      <li><a href="#">애완견 종류</a></li>
      <li><a href="#">입양하기</a></li>
      <li><a href="#">건강돌보기</a></li>
      <li><a href="#">더불어살기</a></li>
    </ul>
    <h2>애완견 종류</h2>
  </nav>
</body>
```

##### none 속성 값

이 속성 값을 해당 요소를 화면에 아예 표시하지 않는다. `visibility: hidden;`도 비슷한 역할을 하는데 visibility 속성은 화면에서 감추기만 할 뿐 원래 요소가 있는 공간은 그대로 차지하지만 이 속성은 공간조차 차지하지 않는다. 



세 개의 `<div>` 영역을 한 줄로 배치하면서 그 내용에는 블록 레벨 속성을 사용하려고 할 때

![image](https://user-images.githubusercontent.com/50407047/100541222-a712d300-3285-11eb-919d-da14ddeab16a.png)

```html
<style>
  div {
    width: 100px;
    height: 100px;
    margin: 30px;
    display: inline-block;
  }
</style>

<div style="background:green"></div>
<div style="background:orange"></div>
<div style="background:blue"></div>
```



### 테두리 관련 속성들

#### border-style 속성: 테두리 스타일 지정하기

```css
border-style: none | hidden | dashed | dotted | double | groove | inset | outset | ridge | solid
```

#### border-width 속성: 테두리 두께 지정하기 

```css
border-top-width: <크기> | thin | medium | thick
border-right-width: <크기> | thin | medium | thick
border-bottom-width: <크기> | thin | medium | thick
border-left-width: <크기> | thin | medium | thick
border-width: <크기> | thin | medium | thick
```

border-width 속성을 이용해 한 번에 표시할 때 속성 값이 2개라면 위아래와 좌우를 묶어 지정하고 4개라면 시계방향으로 적용한다. (top -> right -> bottom -> left)

#### border-color 속성: 테두리 색상 지정하기

```css
border-top-color: <색상>
border-right-color: <색상>
border-bottom-color: <색상>
border-left-color: <색상>
border-color: <색상>
```

#### border 속성: 테두리 스타일 묶어 지정하기

```css
border-top: <두께> <색상> <스타일>
border-right: <두께> <색상> <스타일>
border-bottom: <두께> <색상> <스타일>
border-left: <두께> <색상> <스타일>
border: <두께> <색상> <스타일>
```

#### border-radius: 박스 모서리 둥글게 만들기

```css
border-top-left-radius: <크기> | <백분율>
border-top-right-radius: <크기> | <백분율>
border-bottom-right-radius: <크기> | <백분율>
border-bottom-left-radius: <크기> | <백분율>
border-radius: <크기> | <백분율>
```

##### 타원 형태로 둥글게 만들기

```css
border-top-left-radius: <가로 크기> <세로 크기>
border-top-right-radius: <가로 크기> <세로 크기>
border-bottom-right-radius: <가로 크기> <세로 크기>
border-bottom-left-radius: <가로 크기> <세로 크기>
border-radius: <가로 크기> <세로 크기>
```

#### box-shadow: 선택한 요소에 그림자 효과 내기

```css
box-shadow: none | <그림자값> [, <그림자값>];
<그림자값> = <수평거리> <수직거리> <흐림정도> <번짐정도> <색상> inset
```

- 수평거리: 수평 옵셋거리(수평으로부터 얼마나 떨어져 있는지). 양수 값은 요소의 오른쪽, 음수 값은 요소의 왼쪽에 그림자를 만든다. 필수 속성
- 수직거리: 수직 옵셋거리. 양수 값은 요소의 아래쪽, 음수 값은 요소의 위쪽에 그림자를 만든다. 필수 속성
- 흐림정도: 생략하면 0을 기본값으로 해 진한 그림자를 표시한다. 값이 커질수록 부드러운 그림자를 표시하며 음수 값은 사용할 수 없다.
- 번짐 정도: 그림자의 번지는 정도를 나타낸다. 양수 값을 사용하면 그림자가 모든 방향으로 퍼져 나가기 때문에 그림자가 박스보다 크게 표시된다. 반대로 음수 값은 그림자가 모든 방향으로 축소되어 보인다.
- 색상: 여러 개의 색상을 지정할 수도 있다. 기본 값은 현재 글자 색이다.
- inset: 안쪽 그림자로 그린다. 선택 옵션

### 여백을 조절하는 속성들

#### margin 속성: 요소 주변 여백 설정하기

```css
margin-top: <크기> | <백분율> | auto
margin-right: <크기> | <백분율> | auto
margin-bottom: <크기> | <백분율> | auto
margin-left: <크기> | <백분율> | auto
margin: <크기> | <백분율> | auto
```

- 값이 4개라면 순서는 top->right->bottom->left

- 값이 3개라면 빠진 값은 마주보ㄷ는 방향의 스타일 속성값을 함께 사용한다. 

  ```css
  p { margin: 30px 20px 50px; }
  /* 위 마진: 30px, 좌우마진: 20px, 아래 마진: 50px */
  ```

만약 margin-left와 margin-right를 auto로 지정하면 요소의 너비값을 뺀 나머지 공간의 좌우 마진을 독같이 맞춘다. 이 방법은 웹 요소를 중앙에 배치하려고 할 때 자주 사용한다. 

![image](https://user-images.githubusercontent.com/50407047/100542354-bba69980-328c-11eb-9425-2490b21b02ce.png)

```html
<style>
  .box {
    width:200px;  /* 너비 */
    height:300px;  /* 높이 */
    background:#ff6a00;  /* 배경색 */			 
    margin:0 auto; /* 마진 - 0 auto 0 auto */
  }  
</style>
</head>
<body>
  <div class="box"></div>
</body>
</html>
```

#### margin overlap 현상

요소를 세로로 배치할 경우, 마진과 마진이 만날 때 마진 값이 큰 쪽으로 겹쳐지는데, 이를 마진 중첩(margin overlap) 또는 마진 상쇄(margin collapse)라고 한다.

이렇게 된 이유는 여러 요소를 세로로 배치할 때 맨 위의 마진과 맨 아래 마진에 비해 중간에 있는 마진들이 너무 커지는 것을 방지하기 위한 것이다. 

오른쪽 마진과 왼쪽 마진이 만날 경우에는 중첩되지 않는다. 

#### padding 속성: 콘텐츠 영역과 테두리 사이 여백 설정하기

```css
padding-top: <크기> | <백분율> | auto
padding-right: <크기> | <백분율> | auto
padding-bottom: <크기> | <백분율> | auto
padding-left: <크기> | <백분율> | auto
padding: <크기> | <백분율> | auto
```



