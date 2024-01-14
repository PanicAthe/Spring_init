package com.example.springinit.api;

import com.example.springinit.dto.ArticleForm;
import com.example.springinit.entity.Article;
import com.example.springinit.repository.ArticleRepository;
import com.example.springinit.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j //로그
@RestController
public class ArticleApiController {

    @Autowired // DI, 생성 객체를 가져와 연결
    private ArticleService articleService;

    // GET
    @GetMapping("/api/articles")
    public List<Article> index() {
        return articleService.index();
    }
    @GetMapping("/api/articles/{id}")
    public Article show(@PathVariable Long id) {
        return articleService.show(id);
    }

    // POST
    @PostMapping("/api/articles")
    public ResponseEntity<Article> create(@RequestBody ArticleForm dto) {
        Article created = articleService.create(dto);
        return (created != null) ?
                ResponseEntity.status(HttpStatus.OK).body(created) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    // PATCH 수정.
    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id,
                                          @RequestBody ArticleForm dto) {
        Article updated = articleService.update(id, dto);
        return (updated != null) ? //원래는 예외 처리를 해야..
                ResponseEntity.status(HttpStatus.OK).body(updated):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    // DELETE
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id) {
        Article deleted = articleService.delete(id);
        return (deleted != null) ?
                ResponseEntity.status(HttpStatus.NO_CONTENT).build() :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }


    // 트랜잭션 -> 실패 -> 롤백! 과정 확인
    @PostMapping("/api/transaction-test")
    public ResponseEntity<List<Article>> transactionTest(@RequestBody List<ArticleForm> dtos) {
        List<Article> createdList = articleService.createArticles(dtos);
        return (createdList != null) ?
                ResponseEntity.status(HttpStatus.OK).body(createdList) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }


// 여기는 서비스 계층 분리 전
//    @Autowired
//    private ArticleRepository articleRepository;
//
//
//    // GET
//    @GetMapping("/api/articles")
//    public List<Article> index() {
//        return articleRepository.findAll();
//    }
//    @GetMapping("/api/articles/{id}")
//    public Article show(@PathVariable Long id) {
//        return articleRepository.findById(id).orElse(null);
//    }
//
//
//    // POST
//    @PostMapping("/api/articles")  // 폼에서 데이터 받아올 때와 달리 RESTapi 에서는 @RequestBody 추가
//    public Article create(@RequestBody ArticleForm dto) {
//        Article article = dto.toEntity(); //dto 데이터를 entity로
//        return articleRepository.save(article); //DB에 저장
//    }
//
//
//    // PATCH (수정)
//    @PatchMapping("/api/articles/{id}")
//    public ResponseEntity<Article> update(@PathVariable Long id,
//                                          @RequestBody ArticleForm dto) {
//        //ResponseEntity에 Article 데이터가 담겨서 json으로 반환
//        //상태 코드도 보내주기 위해 ResponseEntity로 포장하는 것
//
//        // 1: DTO -> 엔티티
//        Article article = dto.toEntity();
//        log.info("id: {}, article: {}", id, article.toString());
//
//        // 2: 타겟 조회
//        Article target = articleRepository.findById(id).orElse(null);
//
//        // 3: 잘못된 요청 처리
//        if (target == null || id != article.getId()) {
//            // 400, 잘못된 요청 응답!
//            log.info("잘못된 요청! id: {}, article: {}", id, article.toString());
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null); //상태 코드 보내주기
//        }
//
//        // 4: 업데이트 및 정상 응답(200)
//        target.patch(article); //타겟을 수정
//        Article updated = articleRepository.save(target); //수정된 타겟을 저장
//        return ResponseEntity.status(HttpStatus.OK).body(updated); //상태 코드와 바디에 데이터 담아 보냄
//    }
//
//
//    // DELETE
//    @DeleteMapping("/api/articles/{id}")
//    public ResponseEntity<Article> delete(@PathVariable Long id) {
//
//        // 대상 찾기
//        Article target = articleRepository.findById(id).orElse(null);
//
//        // 잘못된 요청 처리
//        if (target == null) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//        }
//
//        // 대상 삭제
//        articleRepository.delete(target);
//        return ResponseEntity.status(HttpStatus.OK).build();
//    }


}
