package com.lding.mongo.controller;

import com.lding.mongo.povo.World;
import com.lding.mongo.service.IWorldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/worlds")
public class WorldController {
    @Autowired
    private IWorldService iWorldService;

    @GetMapping("/save")
    public String save(World world) {
        iWorldService.save(world);
        return "success!";
    }

    @GetMapping("/{varId}")
    public World get(@PathVariable("varId") String id) {
        return iWorldService.get(id);
    }

    @GetMapping("/remove/{varId}")
    public String remove(@PathVariable("varId") String id) {
        iWorldService.delete(id);
        return "delete success!";
    }

    @GetMapping
    public String list() {
        return iWorldService.list().toString();
    }
}