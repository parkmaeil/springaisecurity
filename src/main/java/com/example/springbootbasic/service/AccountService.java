package com.example.springbootbasic.service;

import com.example.springbootbasic.entiry.Account;
import com.example.springbootbasic.entiry.Authority;
import com.example.springbootbasic.repository.AccountRepository;
import com.example.springbootbasic.util.Roles;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountService  implements UserDetailsService {

     private final AccountRepository accountRepository;

     private final PasswordEncoder passwordEncoder; // 암호화객체?

     // 회원등록
     public Account save(Account account){
         // 비밀번호 암호화
         account.setPassword(passwordEncoder.encode(account.getPassword()));
         // 권한추가
         // ROLE_USER, ROLE_ADMIN, ROLE_EDITOR
         if(account.getRole()==null) {
             account.setRole(Roles.USER.getRole()); //ROLE_USER
             //account.setRole("ROLE_USER");
         }
         return accountRepository.save(account);
     }

     // 데이터베이스연동
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<Account> optionalAccount=accountRepository.findOneByEmailIgnoreCase(email);
        if(!optionalAccount.isPresent()){
              throw  new UsernameNotFoundException("Account not found");
        }
        //'1', 'user@user.com', 'User', 'lastname', '$2a$10$vx7kr1Ac7lPet4yQqt14oOt0CHlDMtLxZBTidYuX9VZ12sPQ/kGTq', 'ROLE_USER'
        Account account=optionalAccount.get();
        // "ROLE_USER"(String)-->GrantedAuthority("ROLE_USER")
        //                       hasRole()
        //                       hasAuthority
        // [GrantedAuthority("ROLE_USER")]
        //4 super_editor@editor.com	Editor	lastname	$2a$10$KR.tnisCM3iKRO.c4.lD8OP/Bo2G5bYvVTyuHH.5TA0XTNJj1m9N.	ROLE_EDITOR
        // ["ROLE_EDITOR","RESET_ANY_USER_PASSWORD","ACCESS_ADMIN_PANEL"]

        // 패스워드가 일치하면 => 인증성공 ?
        // HttpSession session=request.getSession();
        // session.setAttribute("account",account);

        // 권한 부여하기
        List<GrantedAuthority> grantedAuthorityList=new ArrayList<>();
        grantedAuthorityList.add(new SimpleGrantedAuthority(account.getRole()));

        for(Authority _auth :  account.getAuthorities()){
             grantedAuthorityList.add(new SimpleGrantedAuthority(_auth.getName()));
        }
        System.out.println(grantedAuthorityList.toString());
        // 1. return 전에 패스워드 체크가 이루어진다. => 실패 -> 로그인페이지로
        // 2. 성공하면 SecurityContextHolder(세션)객체를 생성한다.
        // 3. Authentication 객체를 생성하고 이 객체에 로그인 성공 정보(Account account)를 담아둔다.
        return new User(account.getEmail(), account.getPassword(), grantedAuthorityList);
        // return new User(account.getEmail(), account.getPassword(), grantedAuthorityList);
    }
}
