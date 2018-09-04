package com.harusketch.domain;

import org.springframework.data.jpa.repository.JpaRepository;

// MyBatis에서 Dao라고 불리는 DB Layer 접근자이다.
// JPA에선 Repository라고 부르며 interface로 생성한다.
// 단순히 interface 생성 후, JpaRepository<Entity클래스, PK타입>를 상속하면 기본적인 CRUD 메소드가 자동생성 된다.
// 특별히 @Repository를 추가할 필요도 없다.
public interface PostsRepository extends JpaRepository<Posts, Long>{

}
