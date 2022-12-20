package org.zerock.springex.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.springex.dto.TodoDTO;

@Log4j2
@RequestMapping("/todo")
@Controller
public class TodoController {

    @RequestMapping("/list")
    public void list(Model model) {
        log.info("todo list...");
    }

    @GetMapping("/register")
    public void registerGET() {
        log.info("GET todo register...");
    }

    @PostMapping("/register")
    public String registerPost(TodoDTO todoDTO, RedirectAttributes redirectAttributes) {
        log.info("POST todo register...");
        log.info(todoDTO);
        return "redirect:/todo/list";
    }
}
