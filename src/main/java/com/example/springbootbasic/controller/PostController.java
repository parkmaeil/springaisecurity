package com.example.springbootbasic.controller;

import com.example.springbootbasic.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    // fetch() 요청(ajax)-->then(응답)
    // @ResponseBody -> @RestController
    @GetMapping("/del/{id}")
    public @ResponseBody String del(@PathVariable Long id){
        postService.deleteById(id);
        return "ok";
    }
}
