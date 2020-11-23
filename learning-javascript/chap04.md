

이 장의 예제에서는 보조 함수(helper function) 두 개가 필요하다.

```js
// m 이상 n 이하의 무작위 정수를 반환한다
function rand(m, n) {
  return m + Math.floor((n - m + 1)*Math.random());
}

// 클라운 앤 앵커 게임의 여섯 가지 도형 중 하나를 무작위 반환한다
function randFace() {
  return ["crown", "anchor", "heart", "spade", "club", "diamond"][rand(0,5)];
}
```

