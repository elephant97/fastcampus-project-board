package com.fastcampus.projectboard.controller;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DisplayName("View 컨트롤러 - 게시글")
@WebMvcTest(ArticleController.class) //지정 컨트롤러만 테스트 하도록
class ArticleControllerTest {
    private final MockMvc mvc;

    public ArticleControllerTest(@Autowired MockMvc mvc){ //테스트 코드에는 꼭 Autowired 명시해야함
        this.mvc = mvc;
    }

    @Disabled("구현 중")
    @DisplayName("[view] [GET] 게시글 리스트(게시판) 페이지 - 정상 호출")
    @Test
    public void givenNothing_whenRequestingArticlesView_thenReturnsArticlesView() throws Exception {
        //given

        //when & then
        mvc.perform(get("/articles"))
                .andExpect(status().isOk()) //200?
                .andExpect(content().contentType(MediaType.TEXT_HTML)) //view라서 타입이 HTML
                .andExpect(view().name("articles/index")) //뷰 이름에 대한 검사
                .andExpect(model().attributeExists("articles"));
    }

    @Disabled("구현 중")
    @DisplayName("[view] [GET] 게시글 상세페이지 - 정상 호출")
    @Test
    public void givenNothing_whenRequestingArticleView_thenReturnsArticleView() throws Exception {
        //given

        //when & then
        mvc.perform(get("/articles/1"))
                .andExpect(status().isOk()) //200?
                .andExpect(content().contentType(MediaType.TEXT_HTML)) //view라서 타입이 HTML
                .andExpect(view().name("articles/detail"))
                .andExpect(model().attributeExists("articles"))
                .andExpect(model().attributeExists("articleComments")); //게시글 데이터를 model attribute가 추가하도록
    }

    @Disabled("구현 중")
    @DisplayName("[view] [GET] 게시글 검색 전용 페이지 - 정상 호출")
    @Test
    public void givenNothing_whenRequestingArticleSearchView_thenReturnsArticleSerchView() throws Exception {
        //given

        //when & then
        mvc.perform(get("/articles/search"))
                .andExpect(status().isOk()) //200?
                .andExpect(content().contentType(MediaType.TEXT_HTML))//view라서 타입이 HTML
                .andExpect(model().attributeExists("articles/search"));
    }

    @Disabled("구현 중")
    @DisplayName("[view] [GET] 게시글 해시태그 검색 전용 페이지 - 정상 호출")
    @Test
    public void givenNothing_whenRequestingArticleHashtagSearchView_thenReturnsArticleHashtagSerchView() throws Exception {
        //given

        //when & then
        mvc.perform(get("/articles/search-hashtag"))
                .andExpect(status().isOk()) //200?
                .andExpect(content().contentType(MediaType.TEXT_HTML)) //view라서 타입이 HTML
                .andExpect(model().attributeExists("articles/search-hash-tag"));
    }


}