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

