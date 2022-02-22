# Toy Project 001

새로운 토이 프로젝트로 아래와 같은 학습을 진행합니다

프로젝트에는 아래와 같은 내용이 포함됩니다

1. 개인적으로 평소에 학습했던 내용
2. 스프링과 JPA 기반 웹 애플리케이션 개발(인프런 백기선님)
3. java playground with tdd, clean code


### 하이버네이트 활용

1. Age이라는 객체를 이용해 User 엔티티를 정의합니다
2. 이때 Age이라는 객체는 입맛에 맞게 커스텀합니다
3. Age라는 객체와 DB의 int형 자료를 적절하게 매칭시켜줍니다

### 로그인 기능 구현

### 1. 이메일을 통한 회원가입 기능 구현하기

회원가입 폼에서 아이디, 패스워드, 이메일 정보를 입력 받는다

입력받은 이메일 주소로 확인 메일에 토큰을 담아 전송한다

회원이 수신한 메일을 통해 토큰과 함께 요청을 전송하고, 이를 validate한다

### 게시판 비슷한 기능 구현


### 프로젝트 일정

데드 라인: 2022년 2월

주어진 기간은 단 9일...

이전에 이미 구현했던 기능들이지만, 이번 프로젝트에서는 조금 더 깊이 있게 진행하기로 결정 한 상황

**이런 밑바탕 속에서 아래와 같은 의사 결정을 내리고 있습니다**

### 1. 프로젝트를 성공시키기 위해 먼저 생산성이 어느 정도 나오는지 측정 (2/21)

2/22 생산성 체크 요약

+ 하루 꼬박 약 200줄 가량의 소스 코드 작성 -> 마이너한 기능 1개 구현 분량

&#43; 예상못한 부분에서 터지는 각종 에러와 어떻게 동작하는지 알아보는데 수고스러움

### 2. 1번 내용을 바탕으로 한 계획

구현하기로 마음먹은 기능이 있고, 진행중에 처리해야하는 혹은 처리해야 할 문제가 쌓여가는 상황

시간은 정해져 있으니, 에러나 버그등을 붙잡고 하루 종일 시간을 사용 할 수 없다

반드시 얼마간의 시간 이상을 새로운 기능 구현에 투자하고, 나머지 시간을 활용하여 에러나 버그를 처리해보자...

### 3. swagger를 이용하여 front 개발을 스킵한다

도구를 이용해서 개발하는 영역을 최대한 줄인다

거의 대부분의 검증을 테스트로 하지만, 브라우저에서도 테스트 해 볼 필요가 있다

이런 부분대문에 프론트 개발을 하면 도저희 단기간에 끝낼 수 없을 것 같아 swagger를 도입했다