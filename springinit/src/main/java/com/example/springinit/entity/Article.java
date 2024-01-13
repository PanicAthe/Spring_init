package com.example.springinit.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity //DB가 인식 가능한 객체 선언 = 여기 Article 클래스로 테이블 생성
@AllArgsConstructor //롬복을 사용해서 생성자 자동 생성
@NoArgsConstructor //인수 없는 디폴트 생성자 자동 생성
@ToString //롬복을 사용해서 toString 함수 대체
@Getter // 롬복을 통해 게터 생성
public class Article {

    @Id //대표값 설정, like a 주민등록번호
    @GeneratedValue(strategy = GenerationType.IDENTITY) // DB가 자동으로 생성하도록
    private Long id;
    @Column //열 설정
    private String title;
    @Column
    private String content;

    public void patch(Article article) {
        if (article.title != null)
            this.title = article.title;
        if (article.content != null)
            this.content = article.content;
    }

}
