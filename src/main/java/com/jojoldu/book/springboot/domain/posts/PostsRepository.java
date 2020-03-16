package com.jojoldu.book.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

//JpaRepository<Entity 클래스,PK 타입>를 상속하면 기본적인 CRUD 메소드가 자동 생성
//Entity 클래스와 기본 Entity Repository 는 함께 위치
public interface PostsRepository extends JpaRepository<Posts, Long> {

    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    List<Posts> findAllDesc();


}
