package com.spark.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Post {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Integer id;
     private String caption;
     private String image;
     private String video;
     @ManyToOne
     private User user;
     private LocalDate createdAt;
     @OneToMany
     private List<User> liked=new ArrayList<>();
}
