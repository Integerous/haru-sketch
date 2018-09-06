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
    - 키파일(.pem)을 `~./ssh/`로 복사 `$ cp harusketch.pem ~/.ssh/`
    - `~/.ssh/`에서 키 권한 변경 `$ chmod 600 ./harusketch.pem`
    - `~/.ssh/` 디렉토리에 `config`파일 생성 `$ nano config``
    - config 파일에 아래와 같이 설정
      ~~~config
      ### Haru Sketch
      Host haru(원하는 이름)
            HostName Elastic IP
            User ec2-user (ubuntu 사용시 ubuntu. 그 외에는 ec2-user)
            IdentityFile ~/.ssh/harusketch.pem
      ~~~
    - `ssh haru` 로 EC2 접속
### AWS RDS PostgreSQL 생성
- RDS 보안그룹 생성 (PostgreSQL 유형으로 생성)
  - EC2 인스턴스의 보안그룹 ID를 IP에 입력
  - 작업환경 IP 입력
  - RDS 보안그룹을 방금 생성한 보안그룹으로 변경
- 로컬 작업환경과 RDS 연동
  - DBeaver 실행하여 연결 추가
  - Host 주소에 RDS 인스턴스의 endpoint 입력
  - Database에 인스턴스 생성할때 지정한 DB이름 입력
  - 생성
- PostgreSQL은 client-encoding(MySQL에서는 Character-set) 값이 UTF8이 default로 지정되어 있어서 default parameter group 사용 가능

### EC2에 Git 설치 및 프로젝트 Clone
- Java8 설치
  - 현재 EC2의 자바 기본버전이 Java7 이므로 Java8로 버전업
    - `$ sudo yum install -y java-1.8.0-openjdk-devel.x86_64`
    - `$ sudo /usr/sbin/alternatives --config java`
  - 사용하지 않는 Java7 삭제
    - `$ sudo yum remove java-1.7.0-openjdk`
- Git 설치
  - `$ sudo yum install git`
- 프로젝트 Clone
  - 프로젝트를 저장할 디렉토리 생성
    - `$ mkdir app`
    - `$ mkdir app/git`
  - 생성한 디렉토리에서 프로젝트 clone
    - `$ git clone -b develop --single-branch [프로젝트 저장소 ssh주소]`
  - 프로젝트 잘 받아졌는지 테스트
    - `$ ./gradlew test`
    - `gradlew`파일 : EC2에 Gradle 설치하지 않았거나 Gradle 버전이 달라도 해당 프로젝트에 한해서 Gradle을 쓸 수 있도록 지원하는 Wrapper 파일
- 배포 준비
  - `$~app/git`에 `deploy.sh` 파일 생성
    ~~~sh
    #!/bin/bash

    REPOSITORY=/home/ec2-user/app/git
    cd $REPOSITORY/Restful-WebApp

    echo "> Git Pull"
    git pull

    echo "> 프로젝트 Build 시작"
    ./gradlew build

    echo "> Build 파일 복사"
    cp ./build/libs/*.jar $REPOSITORY/

    echo "> 현재 구동중인 애플리케이션 pid 확인"
    CURRENT_PID=$(pgrep -f harusketch)

    echo "$CURRENT_PID"
    if [ -z $CURRENT_PID ]; then
        echo "> 현재 구동중인 애플리케이션이 없으므로 종료하지 않습니다."
    else
        echo "> kill -2 $CURRENT_PID"
        kill -9 $CURRENT_PID
        sleep 5
    fi

    echo "> 새 어플리케이션 배포"
    JAR_NAME=$(ls $REPOSITORY/ |grep 'harusketch' | tail -n 1)
    echo "> 새 배포 버전의 이름은? ===> $JAR_NAME"

    nohup java -jar $REPOSITORY/$JAR_NAME &
    ~~~
  - `deploy.sh`에 실행권한 부여 `$ chmod 755 ./deploy.sh`
- 배포
  - `$ ./deploy.sh`
  - `ps -ef|grep harusketch`로 프로세스 실행 확인
- 서비스 접속 테스트
  - EC2 인바운드 규칙 편집
    - 8080 포트 오픈
  - EC2 인스턴스의 퍼블릭 DNS에 :8080 붙여서 접속 확인
