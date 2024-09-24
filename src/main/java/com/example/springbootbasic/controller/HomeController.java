package com.example.springbootbasic.controller;

import com.example.springbootbasic.entiry.Post;
import com.example.springbootbasic.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private  final PostService postService;

    @GetMapping("/")
    public String home(Model model){
          List<Post>  posts=postService.getAll();
          model.addAttribute("posts", posts);
          return "list";
    }

    @GetMapping("/editor")
    public String editor(Model model){
        return "editor";
    }
}
