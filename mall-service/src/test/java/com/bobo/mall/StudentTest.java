package com.bobo.mall;

import com.bobo.mall.api.entity.Student;
import com.bobo.mall.api.service.IStudentService;
import com.github.wenhao.jpa.Specifications;
import org.junit.Test;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import javax.annotation.Resource;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class StudentTest extends BaseTest {

    @Resource
    private IStudentService studentService;


    @Test
    public void addStudent() {
        Student student = new Student().setUsername("lisi").setPassword("111").setEmail("123@qq.com");
        studentService.addStudent(student);
        System.out.println(student);
    }

    @Test
    public void getById() {
        Student s = studentService.getStudentById(1);
        System.out.println(s);
    }

    @Test
    public void deleteById() {
        studentService.deleteUserById(2);
    }

    @Test
    public void updateStudent() {
        Student student = new Student().setId(1).setUsername("名字").setPassword("111").setEmail("123333@qq.com");
        Student stu = studentService.updateStudent(student);
        System.out.println("stu: " + stu);
    }


    @Test
    public void getAllStudent() {
        List<Student> list = studentService.getStudentList();
        System.out.println("list: " + list);
    }

    @Test
    public void getStudentByCondition() {

        // 条件查询
        Student stu = studentService.getStudentByUserName("名字");
        System.out.println("stu: " + stu);

        Student stu2 = studentService.getStudentByEmail("123@gmail.com");
        System.out.println("stu2: " + stu2);

        Student stu3 = studentService.findStudentByEmailOrUsername("1233@gmail.com", "张三");
        System.out.println("stu3: " + stu3);

        Student stu4 = studentService.getStudentByEmailOrUserName("1233@gmail.com", "张三");
        System.out.println("stu4: " + stu4);

        List<Student> stuList = studentService.getStudentsByScoreGreaterThan(60);
        System.out.println("stuList: " + stuList);


        Sort sort = new Sort(new Sort.Order(Sort.Direction.ASC, "score"));
        List<Student> stuList2 = studentService.getSortedStudentList(30, sort);
        System.out.println("stuList2: " + stuList2);


        Specification<Student> specification = (root, query, cb) -> {

            // 用于暂时存放查询条件的集合
            List<Predicate> predicateList = new ArrayList<>();

            // 查询条件示例
            predicateList.add(cb.equal(root.get("username"), "张三"));
            predicateList.add(cb.between(root.get("score"), 60, 100));

            query.orderBy(cb.desc(root.get("score")));

            Predicate[] predicates = new Predicate[predicateList.size()];

            return cb.and(predicateList.toArray(predicates));
        };

        List<Student> stuList3 = studentService.getStudentList(specification);
        System.out.println("stuList3: " + stuList3);


        // 通过 jpa-spec这个第三方库来简化上述代码
        Specification<Student> spec2 = Specifications.<Student>and()
                .eq( "username", "张三")
                .between("score", 60, 100)
                .like("email", "%@gmail.com%")
                .build();

        // jpa-spec 需要的jpa版本较高, 目前版本下, 下面语句会报错
        // Sort sort2 = Sorts.builder().asc("score").build();

        List<Student> stuList4 = studentService.getStudentList(spec2, sort);
        System.out.println("stuList4: " + stuList4);



    }





}