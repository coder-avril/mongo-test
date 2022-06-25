package com.lding.mongo.mapper;

import com.lding.mongo.povo.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * JPA方式调用MongoDB
 * MongoRepository<T, ID>
 * T 代表与MongoDB绑定的实体，ID表示该实体ID的类型
 */
public interface UserRepository extends MongoRepository<User, String> {
    /*
        继承MongoRepository<T, ID>后会自动拥有以下方法的实现
        User findByNameAndAge(String daname, int age);
        List<User> findByNameOrAge(String name, int age);
        List<User> findByAgeBetween(int i, int i1);
        List<User> findByAgeLessThan(int i);
        List<User> findByAgeLessThanEqual(int age);
        List<User> findByAgeGreaterThan(int age);
        List<User> findByAgeGreaterThanEqual(int i);
        User findByNameIsNull();
        List<User> findByNameIsNotNull();
        List<User> findByNameLike(String name);
        List<User> findByNameNotLike(String name);
        List<User> findByNameStartingWith(String da);
        List<User> findByNameEndingWith(String name);
        List<User> findByNameContaining(String a);
        List<User> findByIdOrderByAge(Long id);
        List<User> findByNameNot(String daname);
        List<User> findBySexTrue();
        User findByNameIgnoreCase(String daname);
    */
}
