package com.example.springbootbasic.security;

import com.example.springbootbasic.util.Privileges;
import com.example.springbootbasic.util.Roles;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
//@EnableWebSecurity
public class WebSecurityConfig {
    private static final String[] WHITELIST={
        "/",
        "/login",
        "/register",
         "/css/**",
         "/fonts/**",
         "/images/**",
         "/js/**"
    };

    @Bean
    public static PasswordEncoder passwordEncoder(){
         return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        http
                .authorizeHttpRequests(authz->authz
                     .requestMatchers(WHITELIST).permitAll()
                     .requestMatchers("/profile/**").authenticated()
                     .requestMatchers("/admin/**").hasRole("ADMIN")
                     .requestMatchers("/editor/**").hasAnyRole("ADMIN", "EDITOR")
                     .requestMatchers("/test").hasAuthority(Privileges.ACCESS_ADMIN_PANEL.getPrivilege())
                     .anyRequest().authenticated()
                )
                .formLogin(frm->frm
                        .loginPage("/login") // ?
                        .loginProcessingUrl("/loginProc")
                        .usernameParameter("email")
                        .passwordParameter("password")
                        .defaultSuccessUrl("/", true)
                        .failureUrl("/login?error")
                        .permitAll()
                )
                .logout(logout->logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
                );
              //  .httpBasic(withDefaults());
        return  http.build();

    }
}
