package com.example.springbootbasic.controller;

import com.example.springbootbasic.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

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
    @PreAuthorize("isAuthenticated()")
    public @ResponseBody String del(@PathVariable Long id, Principal principal){
        // 삭제한 사람이 누구인지? (email)
        String email=principal.getName(); // username
        System.out.println(email);
        postService.deleteById(id);
        return email;
    }
}
