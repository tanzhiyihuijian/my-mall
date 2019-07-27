package com.bobo.demo.mallServiceBoot.repo;

import com.bobo.demo.mallServiceBoot.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Integer> {

    Person getPersonByName(String name);



}
