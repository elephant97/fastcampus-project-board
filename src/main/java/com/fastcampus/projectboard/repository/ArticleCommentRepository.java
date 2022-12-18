package com.fastcampus.projectboard.repository;

import com.fastcampus.projectboard.domain.ArticleComment;
import com.fastcampus.projectboard.domain.QArticleComment;
import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//@Repository 붙이지 않아도 됨 JpaRepository의 구현체에 붙어있음
@RepositoryRestResource //data repository라는 것을 명시
public interface ArticleCommentRepository extends
        JpaRepository<ArticleComment, Long>,
        QuerydslPredicateExecutor<ArticleComment>,
        QuerydslBinderCustomizer<QArticleComment>
{
    @Override //검색에 대한 세부 규칙 작성
    default void customize(QuerydslBindings bindings, QArticleComment root) {
        bindings.excludeUnlistedProperties(true); //선택적인 요소에만 검색기능이 동작 하도록
        bindings.including(root.content, root.createdBy, root.createdAt);// 어떤 요소만 검색이 가능할지 지정
//        bindings.bind(root.title).first(StringExpression::likeIgnoreCase); // like '${v}'
        bindings.bind(root.content).first(StringExpression::containsIgnoreCase); // like '%S{v}%'
        bindings.bind(root.createdAt).first(DateTimeExpression::eq); //원하는 날짜에 동일한 것
        bindings.bind(root.createdBy).first(StringExpression::containsIgnoreCase);
    }
}
