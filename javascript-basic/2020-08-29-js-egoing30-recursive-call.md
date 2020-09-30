---
title: "[JS] 생활코딩 #30: 재귀함수"
categories: JavaScript
tags: [ JavaScript ]
---

# 재귀함수

## 패턴

- 정의: 자주 사용하는 로직의 구현 방법과 그것의 이름
- 장점
  - 문제 해결 방법 습득 용이
  - 해결 방법 논의 시 효율적인 의사소통 가능



## 재귀함수

- 자기 자신을 호출하는 함수를 말한다.



## 예제

```js
function traverse(target, callback) {
    // 텍스트 노드 제외하고 element 노드만 출력
    if(target.nodeType === Node.ELEMENT_NODE) {
        // 그 중 a 태그만을 출력
        if (target.nodeName === 'A')
            callback(target);
    	var c = target.childNodes;
    	for (var i = 0; i < c.length; i++) {
        	traverse(c[i], callback);
    	}
    }
}
traverse(document.getElementById('start'), function(elem){
    console.log(elem);
});
```

- 바디 태그를 조회하고 바디태그의 자식 앨리먼트를 조회하면서 자식 앨러먼트를  traverse함수의 첫번째 인자로 전달

- `nodeName`을 통해 작업하고자 하는 node들을 필터링
- `childNodes`을 통해 자식 노드에 접근할 수 있음
- 함수를 다른 함수의 인자로 전달: 값으로서의 함수, 콜백