package com.fastcampus.projectboard.repository;

import com.fastcampus.projectboard.config.JpaConfig;
import com.fastcampus.projectboard.domain.Article;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;


import java.util.List;

import static org.assertj.core.api.Assertions.*;


@ActiveProfiles("testdb") // apllication.yaml파일에 설정 해 놓은 값 중 test파일에서 사용할 db 지정
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) //auto값을 사용하지 않고 activeprofile에 지정한 db로 사용하겠다는 명시 default는 any
@DisplayName("JPA 연결 테스트")
@Import(JpaConfig.class) //내가 만들어 준 config파일을 인식하기 위해 임포트함
@DataJpaTest //테스트는 단위별로 트랜잭션이고, rollback을 함

class JpaRepositoryTest {
    private final ArticleRepository articleRepository;
    private final ArticleCommentRepository articleCommentRepository;

    public JpaRepositoryTest(
           @Autowired ArticleRepository articleRepository,
           @Autowired ArticleCommentRepository articleCommentRepository)
    {
        this.articleRepository = articleRepository;
        this.articleCommentRepository = articleCommentRepository;
    }

    @DisplayName("select test")
    @Test
    void givenTestData_whenSelecting_thenWorksFine(){
        //Given

        //when
        List<Article> articles = articleRepository.findAll();

        //then
        assertThat(articles)
                .isNotNull()
                .hasSize(123);
    }

    @DisplayName("insert test")
    @Test
    void givenTestData_whenInserting_thenWorksFine(){
        //Given
        long previousCount = articleRepository.count();


        //when
        Article savedArticle = articleRepository.save(Article.of("new article", "new contest","#spring"));

        //then
        assertThat(articleRepository.count()).isEqualTo(previousCount + 1);
    }

    @DisplayName("update test")
    @Test
    void givenTestData_whenUpdating_thenWorksFine(){
        //Given
        Article article = articleRepository.findById(1L).orElseThrow();
        String updatedHashtag = "#springboot";
        article.setHashtag(updatedHashtag);

        //when
        Article savedArticle = articleRepository.saveAndFlush(article); // rollback되는 것을 막기위해 save 하는 동시에 flush함 하지만 실제로는 rollback됨

        //then
        assertThat(savedArticle).hasFieldOrPropertyWithValue("hashtag",updatedHashtag);
    }

    @DisplayName("delete test")
    @Test
    void givenTestData_whenDeleting_thenWorksFine(){
        //Given
        Article article = articleRepository.findById(1L).orElseThrow();
        long previousArticleCount = articleRepository.count();
        long previousArticleCommentCount = articleCommentRepository.count();
        int deletedCommentsSize = article.getArticleComments().size();


        //when
        articleRepository.delete(article);

        //then
        assertThat(articleRepository.count()).isEqualTo(previousArticleCount - 1);
        assertThat(articleCommentRepository.count()).isEqualTo(previousArticleCommentCount - deletedCommentsSize);

    }
}