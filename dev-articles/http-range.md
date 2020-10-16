# HTTP range requests

- 범위 요청: HTTP의 일정 부분만 서버에서 클라이언트로 보내라는 것
- 부분 요청: 대형 미디어나 파일 다운로드 도중 일시정지와 다시 시작 기능에 유용



## 서버가 부분 요청을 지원하는지 확인

- 서버가 range 요청을 지원한다면, HTTP응답에 `Accept-Range`가 존재할 것
- HEAD를 cURL에서 요청함으로 확인할 수 있다.
- Accept-Ranges: bytes 

