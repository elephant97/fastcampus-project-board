package com.fastcampus.projectboard.repository;

import com.fastcampus.projectboard.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;

//@Repository 붙이지 않아도 됨 JpaRepository의 구현체에 붙어있음
public interface ArticleRepository extends JpaRepository<Article, Long> {

}