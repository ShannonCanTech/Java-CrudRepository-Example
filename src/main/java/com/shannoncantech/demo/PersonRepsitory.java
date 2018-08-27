package com.shannoncantech.demo;

import org.springframework.data.repository.CrudRepository;

public interface PersonRepsitory extends CrudRepository<Person, Long> {
}
