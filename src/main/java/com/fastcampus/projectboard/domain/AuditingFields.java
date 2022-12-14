package com.fastcampus.projectboard.domain;

import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@ToString
@Getter //자동 세팅 되어야 하기 때문에 getter만 넣음
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public class AuditingFields {

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) //iso 객체를 써서 포맷팅
    @CreatedDate
    @Column(nullable = false, updatable = false) //최초 한번만 세팅되어야 하기 때문에 update가 불가능하게 옵션을 추가
    private LocalDateTime createdAt; //생성일시

    @CreatedBy
    @Column(nullable = false, length = 100)
    private String createdBy; //생성자

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @LastModifiedDate
    @Column(nullable = false) //최초 한번만 세팅되어야 하기 때문에 update가 불가능하게 옵션을 추가
    private LocalDateTime modifiedAt; //수정일시

    @LastModifiedBy
    @Column(nullable = false, length = 100)
    private String modifiedBy; //수정문
}
