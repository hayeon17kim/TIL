# 3. 이미지와 하이퍼링크

이번 장에서 만든 이력서 결과물: http://hayeonkim.dothome.co.kr/

![image](https://user-images.githubusercontent.com/50407047/97806791-c1ff2100-1ca0-11eb-92e1-2d1ff05287e4.png)

## 3.1. 이미지

### 웹에서 사용하는 이미지 형식

- GIF(Graphic Interchange Form)
  - 표시할 수 있는 색상 수가 최대 256가지
  - 다른 이미지 파일에 비해 파일 크기가 작기 때문에 아이콘이나 불릿 등 **작은 이미지에 주로 사용**
  - **투명한 배경**이나 **움직이는 이미지**를 만들수 있다.
- JPG/JPEG(Joint Photopgraphic Experts Group)
  - **사진**을 위해 개발된 형식으로 GIF보다 **다양한 색상과 명암을** 표현할 수 있다.
  - **저장을 반복**하다보면 **화질이 떨어질 수 있다.**
- PNG(Portable Network Graphics)
  - **투명 배경**을 만들면서 **다양한 색상**도 표현할 수 있으며 **네트워크용**으로 개발되었다.

### `<img>`: 이미지 삽입하기

- `<img src="경로" [속성="값"]>`

#### `src` 속성: 이미지 파일 경로 지정

- 경로: HTML 문서에서 이미지 파일까지 찾아가기 위한 길
  - 길이 정확하지 않다면 웹 문서에 이미지가 표시되지 않는다. 

##### 내 컴퓨터의 이미지 파일 경로 지정

- 웹 문서 파일과 이미지 파일이 같은 경로에 있는 경우: `<img src="lotus.jpg">`
- 웹 문서가 있는 폴더에 하위 폴더를 만들고 그 폴더에 이미지 파일을 저장한 경우: `<img src="images/lotus.jpg">` -> 보통 많이 사용하는 방식 

- 파일 경로를 표시하는 기호
  - `/`: 하위 폴더
  - `..`: 한 단계 위로 이동: 같은 레벨에 있는 폴더끼리 파일을 사용할 때 사용
  - css 폴더에 있는 main.css파일에서 css 폴더와 같은 레벨의 images 폴더에 있는 background.png 파일을 삽입할 경우: `body { background: url("../images/background.png"); }`
- **이미지가 삽입된 웹 문서를 다른 사람에게 공**유할 때는 html문서와 함께 **이미지 파일이 있는 폴더도 함께 전달**해 주어야 한다. 그래야 **해당 컴퓨터에서도 파일 경로를 읽을 수 있기 때문**이다. 웹 문서를 더 많은 사람에게 공유하려면 FTP 서버에 웹 문서와 함께 이미지 폴더 그대로 이미지 파일을 올려야 한다.

##### 웹 상의 링크를 복사해 이미지 경로 지정

- 크롬에서 이미지 주소 복사를 선택하면 이미지 경로를 알 수 있다. 이렇게 복사한 웹 이미지 파일의 경로를 `src` 속성의 값으로 지정한다. 다만, 웹 이미지는 인터넷에 접속할 수 있어야 화면에 표시된다.



#### alt 속성: 이미지를 설명해 주는 대체 텍스트

- 화면 낭독기는 문서 상의 텍스트만 읽어 주기 때문에 이미지는 건너 뛴다. 하지만 `alt` 속성을 이용하면 이미지에 대한 설명을 넣을 수 있고, **화면낭독기는 이를 시각 장애인에게 읽어줄 수 있다.** 
- 연결 속도가 느리거나 **이미지를 제대로 표시할 수 없는 상황**에서 이미지 자리에 `alt` 속성에 쓴 내용이 표시되어 **어떤 이미지가 사용되었는지 짐작**할 수 있다.
- 내용을 눈에 띄게 하기 위해 **그래픽으로 처리한 텍스트나 메뉴, 로고** 등의 내용이 포함된 이미지들을 사용했다면 `alt` 속성에 이미지 파일에서 보이는 글자들을 그대로 넣어주어야 한다.
  - 불릿 이미지나 작은 아이콘처럼 특별한 의미 없이 화면을 꾸미기 위해 사용한 이미지에는 대체 텍스트를 지정하지 않아도 된다 -> `<img src="bullet.gif" alt=""`



#### width, height 속성: 이미지 크기 조정하기

- width, height 속성을 지정하지 않으면 우너본 이미지 크기 그대로 브라우저 화면에 표시된다. 
- `<img src="images/gugudan.jpg" width="250" height="250" alt="바빠구구단">`

> 윈도우 그림판에서 이미지 파일을 불러오면 그림판 아래 상태 표시줄에 현재 이미지 크기와 용량이 나타난다. 



### `<figure>`, `<figcaption>` 태그: 이미지에 설명 글 붙이기

#### `<figure>` 태그: 설명 글을 붙일 대상 지정

- 설명글을 붙여할 대상을 지정하거나 웹문서에서 오디오나 비디오 같은 멀티미디어 파일을 비롯해 사진이나 표, 소스코드 등 웹 문서 안에서 한 단위가 되는 요소를 묶을 때 사용한다. 

#### `<figcaption>`: 설명 글 붙이기

- 설명 글이 필요한 대상은 `<figure>` 태그로 묶고 설명 글은 `<figcaption>` 태그로 묶는다.
- 이미지와 이미지 설명이 `<figure>` 태그로 묶여 있기 때문에 이미지 위치를 옮기면 설명도 함께 옮겨진다.

```html
<figure>
  <img src="images/prod.jpg" alt="예멘 모카 마타리">
  <figcaption>예멘 모카 마라티(Yemen Mocha Mattari)</figcaption>
</figure>
```



## 3.2. 링크 만들기

**링크**는 웹 문서가 다른 문서와 구별되는 가장 큰 특징이다.

### `<a>` 태그, `href` 속성: 링크 만들기

`<a>` 태그 안에서 사용할 수 있는 주요 특성

- `href`: 링크한 문서나 사이트의 주소 입력
- `target`: 링크한 내용이 표시될 위치(현재 창 
  또는 새 창)
- `download`: 링크한 애용을 보여주는 것이 아니라 다운로드한다.
- `rel`: 현재 문서와 링크한 문서의 관계를 알려준다.
- `hreflang`: 링크한 문서의 언어
- `type`: 링크한 문서의 파일 유형을 알려준다.

### `target` 속성: 새 탭에서 링크 열기

- `_blank`: 링크 내용이 새 창이나 새 탭에서 열린다.
- `_self`: **target 속성의 본 값으로 링크가 있는 화면에서 열린다.**
- `_parent`: 프레임을 사용햇을 때 링크 냉룡을 부모 프레임에 포함시키낟.
- `_top`: 프레임을 사용했을 때 프레임에서 벗어나 링크 내용을 전체 화면에 표시한다.

#### 아이프레임과 target

- 아이프레임은 프레임의 일종으로 프레임 중에서 문서 본문에 액자처럼 삽입하는 것을 말한다.
- `<iframe>`을 이용해 현재 문서에 다른 문서를 포함시키거나 자바스크립트를 이용해 팝업 창을 열도록 햇을 때 현재 문서는 부모 문서가 되고 `<iframe>`으로 삽입된 문서는 자식 문서가 된다. 자식 문서에서 링크할 때 target을 `_parent` 속성으로 지정하면 부모 문서 창에 표시할 수 있다. `target` 속성을 사용하지 않으면 아이프레임 화면에 그대로 표시되고, `target` 속성을 `_top`으로 지정하면 프레임을 벗어나 브라우저 창 전체에 링크 내용이 표시된다.



### 한 페이지 안에서 점프하는 앵커 만들기

- 앵커를 사용하려면 우선 읻종하고 싶은 위치마다 `id` 속성을 이용해 앵커를 만들고 각각 다른 이름을 지정해야 한다. 이렇게 붙여 놓은 앵커 이름들은 마치 링크를 만들 때처럼 `<a>` 태그의 `href` 속성을 사용해 링크한다. 다만, 앵커 이름 앞에 `#`를 붙여 앵커를 표시한다.

```html
<ul id="menu">
  <li><a href="#content1">메뉴1</a></li>
  <li><a href="#content2">메뉴2</a></li>
  <li><a href="#content3">메뉴3</a></li>
</ul>
<h2 id="content1">내용1</h2>
<p>웹 문서가 너무 길 경우... 앵커(anchor)라고 한다.</p>
<p><a href="#menu">[메뉴로]</a></p>
<h2 id="content2">내용2</h2>
<p>웹 문서가 너무 길 경우... 앵커(anchor)라고 한다.</p>
<p><a href="#menu">[메뉴로]</a></p>
<h2 id="content3">내용3</h2>
<p>웹 문서가 너무 길 경우... 앵커(anchor)라고 한다.</p>
<p><a href="#menu">[메뉴로]</a></p>
```

### `<map>`, `<area>` 태그, `usemap`  속성: 이미지 맵 지정하기

- 하나의 이미지상에서 클릭 위치에 따라 여러 개의 링크를 걸 수 있다.
-  이미지 맵은 이미지에 영역을 만든 후 링크를 추가해야 하기 때문에 `<map>` 태그를 이용해 이미지 맵을 만들고 `<img>` 태그에서 ` usemap` 속성으로 이미지 맵을 지정한다. 이미지 맵으로 사용할 이미지에 영역을 표시할 때는 `<area>` 태그를 사용하는데 `<area>` 태그에서 사용할 수 있는 속성은 다음과 같다.

```html
<map name="맵이름">
  <area>
  <area>
</map>
<img src="이미지파일" usemap="#맵이름">
```

다음은 이미지 왼쪽 영역을 클릭하면 카페로, 오른쪽 영역을 클릭하면 페북으로 가도록 만든 코드이다.

```html
<img src="images/kids.jpg" alt="" usemap="#favorites">
<map name="favorites">
  <area shape="rect" coords="10,10,160,200" href="http://cafe.name.com/doitHTML5">
  <area shape="rect" coords="220,10,380,200" href="http://www.facebook.com/do.it.HTML5" target="_blank">
</map>
```

- `alt`: 대체 택스트를 지정
- `coords`: 링크로 사용할 영역을 시작 좌표와 끝 좌표를 이용해 지정한다.
- `download`: 링크를 클릭했을 때 링크 문서를 다운로드한다.
- `href`: 링크 문서(사이트) 경로를 지정한다.
- `media`: 링크 문서(사이트)를 어떤 미디어에 최적화시킬지 지정한다.
- ` rel`: 현재 문서와 링크 문서 사이의 관계를 지정
  - Iternate, bookmark, help, license, next, nofollow, noreferer, prefetch, prev, search, tag
- `shape`: 링크로 사용할 영역의 형태를 지정한다.
  -  default, rect, circle, poly
- `target`: 링크를 표시할 대상을 지정한다.
  - `_blank`, `_parent`, `_self`, `_top`, `프레임이름`
- `type`: 링크 문서의 미디어 유형을 지정한다.

```html
<img src="images/event.jpg" alt="신간 안내" usemap="#eventMap">
<map name="eventMap">
  <area shape="circle" coords="328,240,55" href="http://www.easyspub.co.kr/20_Menu/BookList/EDU" alt="시간 소개 페이지로 가기" target="_blank">
  <area shape="rect" coords="43,770,690,1170" href="http://www.easyspub.co.kr/12_Menu/BoardView/C200/123/EDU" alt="본문 PDF 다운로드 페이지로 가기" target="_blank">
</map>
```

## 3.3. SVG 이미지

최근에 브라우저에서 svg 파일을 지원하게 되면서 아이콘이나 로고 이미지에  많이 사용하고 있다.



### SVG 형식이란?

- Scalable Vector Graphics
- 비트맵 이미지: 확대하면 이미지 테두리 부분이 깨진다. gif, jpg/jpeg, png 파일이 속한다.
- 벡터 이미지: 아무리 확대하거나 축소해도 원래의 깨끗한 상태 그대로 유지되는 이미지를 말한다. SVG 형식이 속한다.
- **로고나 아이**콘, 데이터 시각화의 **차트, 다이어그램, 지도** 등을 구현할 때 많이 사용된다.
- SVG 이미지는 태그를 이용해 직접 만들 수 있다. 웹 브라우저나 문서 편집기에서도 편집할 수 있다.
- 다시 말해, SVG는 이미지이면서도 소스 코딩을 통해 만들고 편집할 수 있어 ㅁ낳은 주목을 받고 있다.



### SVG 이미지 삽입하기

- `<img>` 태그를 이용해 삽입할 수 있다.



### SVG 파일을 지원하지 않는 브라우저를 위해

modernizr.com 에 접속하여 modernizr-custom.js를 다운받는다. 자바스크립트를 이용해 웹 브라우저에서 SVG 기능을 지원하는지 여부에 따라 다르게 표시할 수 있다.

```html
<head>
  <meta charset="utf-8">
  <title>Insert SVG</title>
  <script src="modernizr-custom.js"></script>
</head>
<body>
  <h1>SVG 이미지 삽입하기</h1>
  <img src="images/muffin.svg">
  <script>
    if (!Modernizr.svg) {
      $("img").attr("src", "images/muffin.png");
    }
  </script>
</body>
```