package com.lding.mongo.povo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Data
@Document("world")
public class World {
    @Id
    private String id;
    private String name;
    private Integer area;
    private List<String> hobby;
}
