package com.lding.mongo.controller;

import com.lding.mongo.povo.User;
import com.lding.mongo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private IUserService iUserService;

    @GetMapping("/save")
    public String save(User user) {
        iUserService.save(user);
        return "success!";
    }

    @GetMapping("/{varId}")
    public User get(@PathVariable("varId") String id) {
        return iUserService.get(id);
    }

    @GetMapping("/remove/{varId}")
    public String remove(@PathVariable("varId") String id) {
        iUserService.delete(id);
        return "delete success!";
    }

    @GetMapping
    public String list() {
        return iUserService.list().toString();
    }
}
