package com.lding.mongo.povo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Data
@Document("users")
public class User {
    @Id
    private String id;
    private String name;
    private Integer age;
    private List<String> hobbies;
}
