package com.xulihuazj.pms.common;

//import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

//public interface CustomerRepository extends MongoRepository<String, String> {
public interface CustomerRepository {

    String findByFirstName(String firstName);

    List<String> findByLastName(String lastName);

}
