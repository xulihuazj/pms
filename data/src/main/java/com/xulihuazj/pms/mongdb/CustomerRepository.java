package com.xulihuazj.pms.mongdb;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CustomerRepository extends MongoRepository<String, String> {

    String findByFirstName(String firstName);

    List<String> findByLastName(String lastName);

}
