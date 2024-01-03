package com.example.springinit.controller;
import com.example.springinit.dto.ArticleForm;
import com.example.springinit.entity.Article;
import com.example.springinit.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ArticleController {
    @Autowired //스프링 부트가 미리 생성해 놓은 객체를 가져가 자동 연결
    private ArticleRepository aricleRRepository;
    @GetMapping("/articles/new")
    public String newArticleForm() {
        return "articles/new";
    }
    @PostMapping("aricles/create") 
    // new페이지에서 form 태그가 article/create로 폼 데이터를 post방식으로 던질 예정
    public String createArticle(ArticleForm form){
        //던져진 폼 데이터는 dto객체로 포장


        // 1. Dto -> Entity 변환
        Article article = form.toEntity();

        // 2. Repository에게 Entity를 DB에 저장하도록
        Article saved = aricleRRepository.save(article);
        
        return "";
    }
}