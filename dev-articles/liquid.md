# 소개

리퀴드는 Ruby로 작성된 오픈소스 템플릿 언어이다. 일반 텍스트(palin text)를 정적 웹사이트(static website)로 변환해주는 jekyll에 사용된다. 



## Objects (객체)

Objects는 Liquid에게 페이지에서 어디에 컨텐츠를 보여줄 지 말해준다. Objects와 변수명은 이중 중괄호(`{{}}`)로 감싸진다.

```liquid
{{ page.title }}
```

```
Introduction
```

Liquid는 page.title이라는 객체의 컨텐츠인 Introduction을 렌더링한다.



## Tags (태그)

Tags는 템플릿의 로직와 제어흐름을 생성한다. 중괄호와 퍼센트 기호(`{% %}`)로 감싸진다.

```liquid
{% if user %}
	Hello {{ user.name }}!
{% endif %}
```

```
Hello Adam!
```

Tags는 제어흐름(Content Flow), 반복(Iteration), 변수 할당(Variable assignment)라는 세 가지 타입으로 분류된다.



### Comment

주석은 {% comment %}와 {% endcomment %}로 감ㅅ싸준다.

```
Anything you put between {% comment %} and {% endcomment %} tags
is turned into a comment.
```

다음과 같이 출력된다.

```
Anything you put between  tags
is turned into a comment.
```



### 흐름제어문

#### if문

```
{% if product.title == "Awesome Shoes" %}
  These shoes are awesome!
{% endif %}
```

조건에 맞다면 다음과 같이 출력된다.

```
These shoes are awesome!
```



#### unless문

if문과 반대로 특정 조건이 맞지 않을 때 태그 본문이 실행된다.

```
{% unless product.title == "Awesome Shoes" %}
  These shoes are not awesome.
{% endunless %}
```

조건에 맞지 않다면 다음과 같이 출력한다.

```
These shoes are not awesome.
```

다음과 같이 if문으로도 unless문을 표현할 수 있다.

```
{% if product.title != "Awesome Shoes" %}
  These shoes are not awesome.
{% endif %}
```



#### elsif /else

if 또는 unless 블럭 안에 조건을 추가할 때 사용한다.

```
<!-- If customer.name = "anonymous" -->
{% if customer.name == "kevin" %}
  Hey Kevin!
{% elsif customer.name == "anonymous" %}
  Hey Anonymous!
{% else %}
  Hi Stranger!
{% endif %}
```

customer.name이 anonymous일 때 다음과 같이 출력된다.

```
Hey Anonymous!
```



#### case/when

다른 값을 가지고 있는 변수를 비교하기 위한 스위치문을 생성한다. `case`는 스위치문을 초기화시키고, `when`은 그 값을 비교한다.

```
{% assign handle = "cake" %}
{% case handle %}
  {% when "cake" %}
     This is a cake
  {% when "cookie" %}
     This is a cookie
  {% else %}
     This is not a cake nor a cookie
{% endcase %}
```

handle의 값이 "cake"이기 때문에 다음과 같이 출력된다.

```
This is a cake
```



### 반복문 (Iteration)
#### for

for 태그는 코드블럭을 반복적으로 실행시킨다. 전체 리스트의 속성을 반복적으로 출력할 때 유용하다.

```
{% for product in collection.products %}
  {{ product.title }}
{% endfor %}
```

결과

```
hat shirt pants
```



#### else








## Filters (필터)

Filters는 Liquid 객체의 출력을 변화시킨다. Filters는 출력 안에서 사용되고 `|`로 구분된다.

```liquid
{{ "/my/fancy/url" | append: ".html" }}
```

```
/my/fancy/url/html
```

다수의 Filters가 한 출력물에 사용될 수 있다. 왼쪽에서부터 오른쪽으로 적용된다.

```
{{ "adam!" | capitalize | prepend: "Hello " }}
```

```
Hello Adam!
```



# 연산자

## 기본 연산자

| `==`  | 일치          |
| ----- | ------------- |
| `!=`  | 일치하지 않음 |
| `>`   | 초과          |
| `<`   | 미만          |
| `>=`  | 이상          |
| `<=`  | 이하          |
| `or`  | 또는          |
| `and` | 그리고        |



```liquid
{% if product.title == "Awesome Shoes" %}
	This shoes are awesome!
{% endif %}
```

다수의 연산자를 하나의 태그에서 사용할 수 있다.

```liquid
{% if product.type == "Shirt" or product.type == "Shoes" %}
	This product's title contains the word Pack.
{% endif %}
```



## Escape

- Liquid 태그를 그대로 보여줄 수 있게 Escape 하는 방법이다.

- {% raw %}와 {% endraw %} 사이에 Liquid 문법을 입력하면 그대로 볼 수 있다.

```
{% raw %}
{% comment %}
<!-- 노출되지 않는 내용 -->
{% endcomment %}
{% endcomment %}
```

블로그에서는 다음과 같이 보인다.

```
{% comment %}
<!-- 노출되지 않는 내용 -->
{% endcomment %}
```

