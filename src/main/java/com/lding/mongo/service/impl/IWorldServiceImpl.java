package com.lding.mongo.service.impl;

import com.lding.mongo.povo.World;
import com.lding.mongo.service.IWorldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class IWorldServiceImpl implements IWorldService {
    @Autowired
    private MongoTemplate template;

    @Override
    public void save(World world) {
        template.save(world, "world");
    }

    @Override
    public void delete(String id) {
        World world = new World();
        world.setId(id);
        template.remove(world);
    }

    @Override
    public World get(String id) {
        return template.findById(id, World.class);
    }

    @Override
    public List<World> list() {
        return template.findAll(World.class);
    }
}
