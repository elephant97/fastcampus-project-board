package com.fastcampus.projectboard.repository;

import com.fastcampus.projectboard.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//import org.springframework.stereotype.Repository;

//@Repository 붙이지 않아도 됨 JpaRepository의 구현체에 붙어있음
@RepositoryRestResource //data repository라는 것을 명시
public interface ArticleRepository extends JpaRepository<Article, Long> {

}