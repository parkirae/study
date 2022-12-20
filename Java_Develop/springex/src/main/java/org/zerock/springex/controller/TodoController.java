package org.zerock.springex.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.springex.dto.TodoDTO;
import org.zerock.springex.service.TodoService;

import javax.validation.Valid;

@Log4j2
@RequestMapping("/todo")
@RequiredArgsConstructor
@Controller
public class TodoController {

    private final TodoService todoService;

    @GetMapping("/register")
    public void registerGET() {
        log.info("GET todo register...");
    }

    // 등록
    @PostMapping("/register")
    public String registerPost(@Valid TodoDTO todoDTO, RedirectAttributes redirectAttributes, BindingResult bindingResult) {
        log.info("POST todo register...");

        if (bindingResult.hasErrors()) {
            log.info("has errors...");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());

            return "redirect:/todo/register";
        }

        log.info(todoDTO);

        todoService.register(todoDTO);

        return "redirect:/todo/list";
    }

    // 전체 조회
    @RequestMapping("/list")
    public void list(Model model) {
        log.info("todo list...");

        model.addAttribute("dtoList", todoService.getAll());
    }

    // 단건 조회 및 수정
    @GetMapping({"/read", "/modify"})
    public void read(Long tno, Model model) {
        TodoDTO todoDTO = todoService.getOne(tno);
        log.info(todoDTO);

        model.addAttribute("dto", todoDTO);
    }

    // 삭제
    @PostMapping("/remove")
    public String remove(Long tno, RedirectAttributes redirectAttributes) {
        log.info("---remove---");
        log.info("tno: " + tno);

        todoService.remove(tno);

        return "redirect:/todo/list";
    }
}
