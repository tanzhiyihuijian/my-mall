package com.bobo.mall.api.dao.repo;

import com.bobo.mall.api.entity.Student;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface StudentRepository extends JpaRepository<Student, Integer>, JpaSpecificationExecutor<Student> {



    /*
     @Query注解有两种使用方式, 一种是JPQL, 另一种是原生SQL


     */

    @Query(value = "select s from Student s where s.username = ?1")
    Student getStudentByUserName(String username);

    // 注意这里要使用 JPA的 @Param注解, 而不是 mybatis的 @Param注解
    @Query(value = "select s from Student s where s.email = :email or s.username = :username ")
    Student getStudentByEmailOrUserName(@Param("email") String email, @Param("username") String username);

    @Query(value = "select s from Student s where s.score >= :score ")
    List<Student> getSortedStudentList(@Param("score") int score, Sort sort);




    // jpa 特性
    // 通过固定语法的方法名来实现
    Student findStudentByEmail(String email);

    Student findStudentByEmailOrUsername(String email, String username);

    List<Student> getStudentsByScoreGreaterThan(int score);




}
