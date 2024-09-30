package com.example.springbootbasic.controller;

import com.example.springbootbasic.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/delete/{id}")
    public String deleteById(@PathVariable Long id){
        postService.deleteById(id);
        return "redirect:/";
    }

}
