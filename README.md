# 스프링부트 웹앱 프로젝트

## 개발 환경
- RESTful API 기반 Web application
- Spring Boot 1.5.10
- Gradle 
- Eclipse
- ...


## 개발 일정
|일정|주제|비고|
|---|---|---|
|9/4 화|0. 프로젝트 시작|  |
||1. 개발환경 세팅|Spring boot 프로젝트 구조 학습|
||2. DB 모델링|제3정규화|
||3. DB 생성 및 연동|AWS RDS PostgreSQL 활용|
||4. 회원가입/로그인 구현|JWT 활용|
||5. 상품목록 게시판 구현|다른 게시판은 추후에 추가|
||(6. 상품구매 구현)|결제기능은 가상 처리|
||(7. 구매이력확인 구현)|  |
||8. 화면 구현|jQuery,Bootstrap 활용|
||9. 테스트 및 배포자동화 구축|AWS CodeDeploy 및 CI도구 활용|
|~9/14 금|10. 배포 및 피드백|NginX 활용|



## 개발 과정
### gradle 빌드 속도 높이기
- `${HOME}/.gradle/gradle.properties` 생성해서 아래 내용 추가
  ~~~
  org.gradle.daemon=true
  ~~~

### EC2 생성
- Amazon Linux 2018 선택
- harusketch pem 키 생성
- Elastic IP 적용 (적용안하면 인스턴스 재시작 될 때마다 IP 바뀜)
- 보안그룹 설정(내 작업공간 IP, HTTP, HTTPS)
- EC2 터미널 접속
  - SSH 접속 쉽게하기
    - 키파일(.pem)을 `~./ssh/`로 복사
    - 키파일 위치에서 `cp harusketch.pem ~/.ssh/`
    - `~/.ssh/`에서 키 권한 변경 `chmod 600 ./harusketch.pem`
    - `~/.ssh/` 디렉토리에 `config`파일 생성 `nano config``
    - config 파일에 아래와 같이 설정
      ~~~config
      ### Haru Sketch
      Host 원하는 이름
            HostName Elastic IP
            User ec2-user (ubuntu 사용시 ubuntu. 그 외에는 ec2-user)
            IdentityFile ~/.ssh/harusketch.pem
      ~~~      
### AWS RDS PostgreSQL 생성
- 보안그룹 수정
  - EC2 인스턴스의 보안그룹
  
- 로컬PC에서 RDS 접근
