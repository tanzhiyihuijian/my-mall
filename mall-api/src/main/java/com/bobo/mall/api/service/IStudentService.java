package com.bobo.mall.api.service;

import com.bobo.mall.api.entity.Student;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface IStudentService {

    Student addStudent(Student student);

    void deleteUserById(Integer id);

    Student updateStudent(Student student);

    Student getStudentById(Integer id);

    Student getStudentByUserName(String username);

    Student getStudentByEmail(String email);

    List<Student> getStudentsByScoreGreaterThan(int score);

    List<Student> getSortedStudentList(int score, Sort sort);

    Student getStudentByEmailOrUserName(String email, String username);

    Student findStudentByEmailOrUsername(String email, String username);

    List<Student> getStudentList();

    List<Student> getStudentList(Specification<Student> specification);

    List<Student> getStudentList(Specification<Student> specification, Pageable pageable);

    List<Student> getStudentList(Specification<Student> specification, Sort sort);

}
