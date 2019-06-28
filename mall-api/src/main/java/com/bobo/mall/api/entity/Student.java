package com.bobo.mall.api.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;


@Data
@Accessors(chain = true)
@Entity
@Table(name = "student")
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;

    // @GenericGenerator(name = "idGenerator", strategy = "uuid")
    // @GeneratedValue(generator = "idGenerator")

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "username", unique = true, nullable = false, length = 64)
    private String username;

    @Column(name = "password", nullable = false, length = 64)
    private String password;

    @Column(name = "score", nullable = false, length = 11)
    private int score;

    @Column(name = "email", length = 64)
    private String email;

}
