package com.driver.models;
import javax.persistence.Entity;
import javax.persistence.*;
import javax.persistence.Table;

@Entity
@Table


public class Blog {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ye sab
    private int blogId;

    private String title;

    private String content;

    @ManyToOne
    @JoinColumn
    private User user;


}