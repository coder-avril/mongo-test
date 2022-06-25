package com.lding.mongo.service;

import com.lding.mongo.povo.User;
import java.util.List;

public interface IUserService {
    void save(User user);
    void delete(String id);
    User get(String id);
    List<User> list();
}
