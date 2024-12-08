package com.spark.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String chatName;
    private String chatImage;
    @ManyToMany
    private List<User>users=new ArrayList<>();

    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "chat")
    private List<Message> messages=new ArrayList<>();
}
