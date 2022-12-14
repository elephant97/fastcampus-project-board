package com.fastcampus.projectboard.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;


@Getter
@Table(indexes={ //table 생성 인덱스
        @Index(columnList = "title"),
        @Index(columnList = "hashtag"),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy")
})

//@EntityListeners(AuditingEntityListener.class) //auditing이 필요한 객체를 class로 따로 추출함
@Entity
public class Article extends AuditingFields{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter @Column(nullable = false) private String title; //제목
    @Setter @Column(nullable = false, length = 10000) private String content; //본문

    @Setter private String hashtag; //해시태그

    //양방향 바인딩 필드
    @ToString.Exclude
    @OrderBy("id")
    @OneToMany(mappedBy="article", cascade = CascadeType.ALL) //기본이름은 두 엔티티의 이름을 조합해서 만들어지기 때문이기 때문에 명시를 하는 것
    private final Set<ArticleComment> articleComments = new LinkedHashSet<>(); //한번만 컬렉션을 세팅하기 위해
    //이 Article에 연동되어있는 커멘트는 중복을 허용하지 않고 다 모아서 컬렉션으로 보기위해

    //meta data
    // 컬럼값을 개별로 아래와 같이 선언하는 대신에 @Embedded 클래스안에 필드값을 넣어 한번에 테이블에 적용할 수도 있음
//    @CreatedDate @Column(nullable = false) private LocalDateTime createdAt; //생성일시
//    @CreatedBy @Column(nullable = false, length = 100) private String createdBy; //생성자
//    @LastModifiedDate @Column(nullable = false) private LocalDateTime modifiedAt; //수정일시
//    @LastModifiedBy @Column(nullable = false, length = 100) private String modifiedBy; //수정문
    //extens 하도록 변경함


    protected Article() {}

    private Article(String title, String content, String hashtag) {
        this.title = title;
        this.content = content;
        this.hashtag = hashtag;
    }

    public static Article of(String title, String content, String hashtag) {
        return new Article(title, content, hashtag);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return id != null && id == article.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
