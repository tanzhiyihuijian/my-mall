package com.bobo.demo.mallServiceBoot;

import com.bobo.demo.mallServiceBoot.entity.Person;
import com.bobo.demo.mallServiceBoot.repo.PersonRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MallServiceBootApplicationTests {

    @Resource
    private PersonRepository personRepository;

    @Test
    public void contextLoads() {
    }


    @Test
    public void addPerson() {
        Person person = personRepository.getPersonByName("tom");
        System.out.println(person);
    }




}
