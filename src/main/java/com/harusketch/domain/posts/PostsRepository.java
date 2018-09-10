package com.harusketch.domain.posts;

import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

// MyBatis에서 Dao라고 불리는 DB Layer 접근자이다.
// JPA에선 Repository라고 부르며 interface로 생성한다.
// 단순히 interface 생성 후, JpaRepository<Entity클래스, PK타입>를 상속하면 기본적인 CRUD 메소드가 자동생성 된다.
// 특별히 @Repository를 추가할 필요도 없다.
public interface PostsRepository extends JpaRepository<Posts, Long>{

	//@Query를 쓰지 않아도 되지만, SpringDataJPA에서 제공하지 않는 메소드는 @Query로 작성 가능
	@Query("SELECT p FROM Posts p ORDER BY p.id DESC")
	Stream<Posts> findAllDesc();
}
