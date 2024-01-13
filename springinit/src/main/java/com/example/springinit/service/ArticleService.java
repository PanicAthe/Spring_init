package com.example.springinit.service;

import com.example.springinit.entity.Article;
import com.example.springinit.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service //서비스 선언(서브스 객체를 스프링부트에 생성)
public class ArticleService {

    @Autowired // DI
    private ArticleRepository articleRepository;

    public List<Article> index() {
        return articleRepository.findAll();
    }
}
