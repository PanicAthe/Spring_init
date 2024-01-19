package com.example.springinit.controller;
import com.example.springinit.dto.ArticleForm;
import com.example.springinit.dto.CommentDto;
import com.example.springinit.entity.Article;
import com.example.springinit.repository.ArticleRepository;
import com.example.springinit.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@Slf4j //로킹을 위한 어노테이션
public class ArticleController {
    @Autowired //스프링 부트가 미리 생성해 놓은 객체를 가져다가 자동 연결
    private ArticleRepository articleRepository;
    @Autowired
    private CommentService commentService;

    @GetMapping("/articles/new")
    public String newArticleForm() {
        return "articles/new";
    }
    @PostMapping("/articles/create")
    // new페이지에서 form 태그가 article/create로 폼 데이터를 post방식으로 던질 예정
    public String createArticle(ArticleForm form){
        //던져진 폼 데이터는 dto(ArticleForm) 객체로 포장

        //System.out.println(form.toString()); -> 로깅 기능으로 대체
        log.info(form.toString());

        // 1. Dto -> Entity 변환
        Article article = form.toEntity();
        log.info(article.toString());

        // 2. Repository에게 Entity를 DB로 저장하게 함
        Article saved = articleRepository.save(article);
        log.info(saved.toString());

        // 리다이렉트는 새롭게 요청을 보내는 것
        // 리턴에 적는 값은 뷰페이지를 찾기 위한 경로를 잡는 것
        //return "/articles/" + saved.getId() = URL요청은 뷰페이지 경로(/templetes)에서 찾을 수 없다.
        return "redirect:/articles/" + saved.getId();
    }

    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model){ // article 내용을 id를 통해 호출
        //@PathVariable : URL 요청으로부터 파라미터 가져오게

        log.info("id = " + id);
        
        //1: db에서 id로 데이터를 가져옴
        // .orElse(null) : 해당 id의 데이터 없다면 null 반환
        Article articeEntity= articleRepository.findById(id).orElse(null);
        List<CommentDto> commentsDtos = commentService.comments(id);
        
        //2 : 가져온 데이터를 model에 등록
        model.addAttribute("article", articeEntity);
        model.addAttribute("commentDtos", commentsDtos);

        //3: 보여줄 view 설정!
        return "articles/show";
    }

    @GetMapping("/articles") //article 목록을 호출
    public String index(Model model){

        // 1: 모든 Article을 가져온다!
        // articleRepository.findAll()는 ArticleRepository extends 때문에 디폴트 리턴 타입은 iterable
        // (List<Article>)로 타입 캐스팅 or ArticleRepository에서 findAll함수 리턴타입 바꾸는 override
        List<Article> articleEntityList = articleRepository.findAll();

        // 2: 가져온 Article 묶음을 뷰로 전달!
        model.addAttribute("articleList", articleEntityList);

        // 3: 뷰 페이지를 설정!
        return "articles/index";
    }

    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {

        // 수정할 데이터 가져오기
        Article articleEntity = articleRepository.findById(id).orElse(null);

        // view에서 사용가능하도록 모델에 데이터 등록
        model.addAttribute("article", articleEntity);

        // 뷰 페이지 설정
        return "articles/edit";
    }

    @PostMapping("/articles/update")
    public String update(ArticleForm form){

        // 1: DTO를 엔티티로 변환
        Article articleEntity = form.toEntity();
        log.info(articleEntity.toString());

        // 2: 엔티티를 DB로 저장
        // 2-1: DB에서 기존 데이터를 가져옴
        Article target = articleRepository.findById(articleEntity.getId())
                .orElse(null);
        // 2-2: 기존 데이터가 있다면, 값을 갱신
        if (target != null) {
            articleRepository.save(articleEntity); //엔티티 저장
        }

        // 3: 수정 결과 페이지로 리다이렉트
        return "redirect:/articles/" + articleEntity.getId();

    }

    @GetMapping("/articles/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr) {

        log.info("삭제 요청이 들어왔습니다!!");

        // 1: 삭제 대상을 가져옴
        Article target = articleRepository.findById(id).orElse(null);
        log.info(target.toString());

        // 2: 대상을 삭제
        if (target != null) {
            articleRepository.delete(target);
            rttr.addFlashAttribute("msg", "삭제가 완료되었습니다.");
        }

        // 3: 결과 페이지로 리다이렉트
        return "redirect:/articles";
    }
}