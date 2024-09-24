package com.example.springbootbasic.entiry;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String body;
    private LocalDateTime createdAt;

    @ManyToOne
    // FK(외래키)
    @JoinColumn(name = "account_id", referencedColumnName = "id", nullable = true)
    private Account account;
}
