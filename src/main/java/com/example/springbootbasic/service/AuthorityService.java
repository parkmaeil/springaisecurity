package com.example.springbootbasic.service;

import com.example.springbootbasic.entiry.Authority;
import com.example.springbootbasic.repository.AuthorityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorityService {

     private final AuthorityRepository authorityRepository;

     public Authority save(Authority authority){
         return authorityRepository.save(authority);
     }

     public Optional<Authority> findById(Long id){
            return authorityRepository.findById(id);
     }
}
