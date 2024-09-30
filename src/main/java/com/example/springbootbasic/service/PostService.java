package com.example.springbootbasic.service;

import com.example.springbootbasic.entiry.Post;
import com.example.springbootbasic.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

      private final PostRepository postRepository;

      public Optional<Post>  getById(Long id){
           return postRepository.findById(id);
      }

      public List<Post> getAll(){
           return postRepository.findAll();
      }

      public void delete(Post post){
            postRepository.delete(post);
      }

      public Post save(Post post){
            if(post.getId()==null){
                 post.setCreatedAt(LocalDateTime.now());
            }
            return postRepository.save(post);
      }

      public void deleteById(Long id){
            postRepository.deleteById(id);
      }
}
