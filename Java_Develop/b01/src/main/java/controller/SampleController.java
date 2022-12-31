package controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Log4j2
@Controller
public class SampleController {

    @GetMapping("/hello")
    public void hello(Model model) {

        log.info("hello...");

        model.addAttribute("msg", "HELLO WORLD");
    }
}
