package hello.hellospring.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller //이거 적는 이유?
public class HelloController {
    @GetMapping("mine")
    public String hello(Model model){
        model.addAttribute("data", "hello!!!");
        return "mine";

    }
}
