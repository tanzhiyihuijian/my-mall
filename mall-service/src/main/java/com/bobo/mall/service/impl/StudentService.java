package com.bobo.mall.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.bobo.mall.api.dao.repo.StudentRepository;
import com.bobo.mall.api.entity.Student;
import com.bobo.mall.api.service.IStudentService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Service
@Component
public class StudentService implements IStudentService {

    @Resource
    private StudentRepository studentRepository;

    @Override
    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public void deleteUserById(Integer id) {
        studentRepository.delete(id);
    }

    @Override
    public Student updateStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student getStudentById(Integer id) {
        return studentRepository.findOne(id);
    }

    @Override
    public Student getStudentByUserName(String username) {
        return studentRepository.getStudentByUserName(username);
    }

    @Override
    public Student getStudentByEmail(String email) {
        return studentRepository.findStudentByEmail(email);
    }

    @Override
    public List<Student> getStudentsByScoreGreaterThan(int score) {
        return studentRepository.getStudentsByScoreGreaterThan(score);
    }

    @Override
    public List<Student> getSortedStudentList(int score, Sort sort) {
        return studentRepository.getSortedStudentList(score, sort);
    }

    @Override
    public Student getStudentByEmailOrUserName(String email, String username) {
        return studentRepository.getStudentByEmailOrUserName(email, username);
    }

    @Override
    public Student findStudentByEmailOrUsername(String email, String username) {
        return studentRepository.findStudentByEmailOrUsername(email, username);
    }

    @Override
    public List<Student> getStudentList() {
        return studentRepository.findAll();
    }

    @Override
    public List<Student> getStudentList(Specification<Student> specification) {
        return studentRepository.findAll(specification);
    }

    @Override
    public List<Student> getStudentList(Specification<Student> specification, Pageable pageable) {
        return null;
    }

    @Override
    public List<Student> getStudentList(Specification<Student> specification, Sort sort) {
        return studentRepository.findAll(specification, sort);
    }

}
