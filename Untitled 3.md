## 결제

### 애스크로

- 중개업체: 돈을 받고 대기를 해주는 제 3의 업체

- 구매자와 판매자간 결제할 때 신리ㅗ를 더해주는 서비스

- 또다른 회사에 맡기고 이ㅆ다. 내부적으로 

- 유니크로랑 케이비가 다르다. 케이비는 유니크로 틀에맞춰서 만든다. 

- 유니크로 플로우에 케이비를 어거지로 맞춘 상황

- 현재 페이를 하려고 직접 피지를 하려고 하는 중

- 스마트 스토어에서 구매확정하세요 하는 게 네이버가 애스크로를 하고 있다는 것.

- 유니크로는 api 개인가 두개만. 셀러 에이피아이 바이어 에이피아이

- 플래그를 계속 주면서 통신. 입금은 직접 하는 게 아니니까 계쏙 액셔을 동기화시켜줘야 하다.

- KB :애스크로 하는 데가 아님.  

- 유니크로는 결제, 애스크로까지 다 한다. 유니크로는 유니크로의 배송 관련한 서드파티가 있다. 두 개가 각자의 서드파티로 체크한다,

- 케이비는 없다. 그래서 케이비는 온전히 백단을 관리해야 한다. 다른 건 그냥 동기화만 게속 해주면 되는데.

- CS는 다 유니크로가 하고 있다.

- 케이비는 CS를 우리가 해야 한다.

- 중나 배송등록과 제휴사(편의점) 배송등록 변경1

  

- 동기화는 유니크로 따로 어드민이 있다. 여기서 동기화를 확인한다. 

- 결제: 상품이 팔릴 ㅜㅅ 있냐 없냐가 중요

- 금액 제한이 피지사마다 다르다. 실시간일 때도 다르고 카드결제도 다르다. 카드결제가 아마 제일 클 것이다.

- 체크를 하다가 마지막 상품에 대해 락이 걸리게 되어 있다. => 여러 사용자가 사려고 할 때 다른 사람은 

15분동안 레디스 접근을 막는다. 

동시에 들어갔ㅇ르 때 읽고 쓰는 시간이 다를 수 이다. 동시 들어가는 경우에는 어쩔 수없다.

  예전보다는 사용량이 적어졌다. ㄱ

보통 상품은 한개고 업자들에게는 nㅇ개의 상품이니까

ㅈㅜㅇ복구매를 거르는.

상세페이지 들어갓을 때 지금 현재 들어갈 수 없다는 페이지가 나온다.

지금은 즉시송금 없음

결제 할 때마다 구매자, 판매자, 상품 저장.



케이비는 VPN 을 사용한다. 



KB  => 앱카드로 결제

프로세스가 유니크로와 p이비가 다르다. 

안전결제: 배송까지 추적

KB: 실제로 안전결제라기보다 즉시결제이다. 하지만 안전결제라고 하는데 배송추적까지 해서 안전결제처럼 사용하고 있다. 유니크로와 프로세스를 맞추기 위해. 





무조건 VPN을 통함.



IDc: 물릿버

공중망을 통해서 가면 프록시로 가로챌 수도 있으니까.  CI값 같은 경우는 VPN으로.

1회용 토큰이다.: 카드가 있으면 토큰 발급. (아이디, 상품 가격, 비밀키)

토큰을 가지고 사용자 정보 꺼냄.

어디나 VPN, (팝업창 => 공주망)

JTI: 



실제로는 클라우드이지만 IDC로 하게끔. 왜냐하면 전북은행 같은 경우 클라우드 안되고 물리서버만 된다. 

우리 게이트웨이를 통해서 KNIX 

Moid : Request ID. 유니크한 값

Tid: 

hidden 으로 토큰갑싱 들어 있따. 

팝업창 뜰 때까지 ㅌ한 트랜잭션. 





케이비처럼 암호화를 하지 않는다. 피지사랑 할 때는 꼼꼼하게 할 텐데 우리랑 할 때는 암호화를 하라고 하는 게 없다. 



m0dev.joogna.com



payway: 



kb는 결제 페이지를 아이프레임으로. =>  메인에이피아이의 JSP 걸쳐서 ㄱ산다. )파라미터 하고 바로 폼으로 넘긴다.

유니크로: 새창으로 띄운다 (새탭) => 그리고 바로 유니크로 페이지로 넘긴다.



팝업창

유니크로는 넘어가는 단계마다 메인 에이피아이와 통신 (상태  동기화)

KB: 마지막 한번만 최종. 성공 실패했는지. 햇다면 왜 실패했는지

배송 관련해서는 

유니크로 자체적으로 가지는 배치가 있다. KB는 없다. 

72시간 결제 취소 후 72시간 이후에 취소 처리를 해라 라는 배치가 KB. 



상품이 하나 있으면 주문이 여러건이 잇ㅇ르 수 있다. 추소할 수도 있고, 재고가 여러갤 수도 있고. 주문마다 배송이 n개가 될 수도 있다. (일반배송 + 반품배송 등등)



배치와 스케줄러의 차이점

기능은 같은데 목적이 다름

- 배치: 대용량 정보 처리 (파티셔닝, 전체 회원 대상으로.)  => 스프링 배치
- 스케줄러: 시간 단위로 실행. 처리를 하는 것. 그냥 시간마다 하는 것. => 젠킨스 등

웹 서버 + 웹 컨테이너



배포와 릴리즈 =>

릴리즈 버전 차이 어떤 차이가 있는지.  (메이저-마이너- ) 그 기준. => pom.xml 찾기



헷갈리는 개념을 다 질문하기. 



서버리스: 자신의 사애틀ㄹ 몰라도 된다. 리퀘스텡 따라서 한다. 

프로토타입 하기 좋다

잠자고 잇다가 깨운다. 처음 호출에 대한 응답이 느리다. (콜드 어쩌구)





## 회의 

- CTO님 오심
- 협업툴 교체
  - 컨플(느림) + 지라(복잡) + 비트버킷 
  - 컨플 => 노션 (확실x) (노션 칸반)
  - 지라 => 깃헙이슈 (개발실만.)
  - 비트버킷 => 깃헙 (프라이빗)
- 정리작업
  - 안쓰는 디비 없애고 인스턴스 
- 탄력근무 유지
- 결재선: 현행대로 (팀장님 or 실장님)
- 휴가 있음 => 한번에 6개 들어옴
  - 반차, 반반차 가능
- 4월 말에 방향 정리될 것
  - 현재는 C레벨들 현황파악 중
  - 로드맵 만들고 있는 중 => 아직은 없음
  - 조직개편 있을 예정
    - 실 단위 (개발실, 기획조정실, 디자인실) => 팀 단위
    - C 레벨 조직장 밑에 팀이 엮여 있는 모습
- 아이디어 
  - 웹 인터페이스: 모웹을 피씨에서도 쓰는 것
  - 로그인을 하겠다는 것이 아니라
  - 투트랙으로 가지만 빨리 적용하고자 하는 건 모웹에 얹고
  - 인기검색어 노출 영역 만들기

- 4월
  - 파악하는 시간
  - 4월 말에 구체적인 그림 나올 것
  - 새로운 일감이 도출되지 않은 상태
  - 지금은 스터디도 병행하면서 기회라고 생각하고 공부할 것
  - EKS/ AWS 부족한 점 보완하는 시간 갖기 추천!
- 예상 개편 내용
  - main 개편
- 매일 아침 팀장 스탠드업 미팅
- 질문
  - 팀 내부 개편은 없을 것 같음
  - 노션으로 이전 문제
    - export 할 수는 있는데 깔끔하지 않음. 뭐 어떤 방법으로 하는 것 같음
  - 현재 AWS 많이 쓰고 있긴 함 => 이걸 줄일 것 (절반정도 수준으로 갈 수도 있을 것 같음..)
  - 현재 그룹웨어를 안 쓸 수도 있다. (전자결재 쓰되 메일 등은 구글박스로)
  - 유니크로 계약 7월까지. 그 후에 결제수단을 뭘로 할까
    - 연장
    - 페이 진행할 가능성 높음 => 아임포트 사용하면 괜찮을 것 같긴한데 시간이 많이 걸릴 것 같음...
  - 광고주센터 페이드아웃
    - 공통개발팀에서 주로 함
    - 서비스개발팀: 연동 API 내리고 등등 롤백하는 단계
  - 페이코 -> 식권대장
- 교육
  - 팀장님 아웃트로: 모든 교육 끝나고
  - 백병남 매니저님 교육 좀 더 앞당길 예정
  - 교육 후 지라에서 할 수 있는 업무 선별해서 주실 것: 배포 + 개발..?
- 네트워크 문제  => CFO
- 



## 본인 인증, 소셜 로그인

- 정부에서 표준으로
- 가입 일자가 밀려나서
- 비용이 상당히 비싸다 : 본인인증서비스. 
- 한달에 천만원정도 => 하지만 쓸 수밖에 없다...
- 이거 아니면 다계정 점검할 수 ㅣㅇ는 게 ㅓㅂㅅ음.
- 주민번호 본인인증 한번 하면 이걸 어디서 쓰든 평생 . 

암호화, 복호화 모듈을 제공한다.

복호화할 때는 그 시점 키값이 알아야 한다.

복호화: decrypt



- 회원가입수 늘어남
- 비용 







- 모델 맵퍼란?
- 











