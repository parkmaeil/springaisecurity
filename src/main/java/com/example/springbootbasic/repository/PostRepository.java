package com.example.springbootbasic.repository;

import com.example.springbootbasic.entiry.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository  extends JpaRepository<Post, Long> {


}
