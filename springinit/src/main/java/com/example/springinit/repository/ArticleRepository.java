package com.example.springinit.repository;

import com.example.springinit.entity.Article;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;


// JPA에서 제공하는 Repository Interface 이용
// <> 파라미터는 1.Entity 객체 유형, 2.대표값 타입
public interface ArticleRepository extends CrudRepository<Article, Long> {

    @Override
    ArrayList<Article> findAll();
}
