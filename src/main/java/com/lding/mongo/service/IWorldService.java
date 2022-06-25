package com.lding.mongo.service;

import com.lding.mongo.povo.World;
import java.util.List;

public interface IWorldService {
    void save(World user);
    void delete(String id);
    World get(String id);
    List<World> list();
}
