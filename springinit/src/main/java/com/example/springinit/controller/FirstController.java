package com.example.springinit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirstController {

    @GetMapping("/hi") //url 요청 연결, 어노테이션 인수로 접속할 url 넣어줌
    public String niceToMeetYou(Model model){ //여기에 모델을 인수로 넣어야
        //model이라는 객체가 username이라는 이름에 연결시켜서 "친칠라"라는 값 보내줌
        model.addAttribute("username", "친칠라");
        return "greetings"; //템플릿s/greeting.mustache 찾아서 전송
    }
}
