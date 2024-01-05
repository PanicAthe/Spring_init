package com.example.springinit.dto;

import com.example.springinit.entity.Article;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor //롬복을 사용해서 생성자 자동 생성
@ToString //롬복을 사용해서 toString 함수 대체
public class ArticleForm {
    private String title;
    private String content;
    public Article toEntity() {
        return new Article(null, title, content);
    }
}