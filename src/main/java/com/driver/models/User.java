package com.driver.models;

import javax.persistence.*;
import java.util.List;

@Table
@Entity

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    private String username;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Blog> blogs;

}