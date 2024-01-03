package com.example.springinit.repository;

import com.example.springinit.entity.Article;
import org.springframework.data.repository.CrudRepository;

public interface ArticleRepository extends CrudRepository<Article, Long> {



}
