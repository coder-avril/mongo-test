package com.lding.mongo;

import com.lding.mongo.povo.TravelVO;
import com.lding.mongo.povo.User;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import java.util.*;
import com.alibaba.fastjson.JSON;
import org.springframework.data.mongodb.core.aggregation.Aggregation;

@SpringBootTest
public class TemplateTest {
    @Autowired
    private MongoTemplate template;

    // 分页查询文档，名字带有tom的，显示第2页，每页显示3个，按照id升序排列
    @Test
    public void case1Test() {
        Query query = new Query(); // 创建查询对象
        query.addCriteria(Criteria.where("name").regex("tom"));
        query.skip(3).limit(3); // 设置分页信息
        query.with(Sort.by(Sort.Direction.DESC, "name")); // 设置排序规则
        List<User> list = template.find(query, User.class, "users");
        list.forEach(System.out::println);
    }

    // MongoDB查询-Query Document方式
    // 查询名字等于jack的
    @Test
    public void case2Test() {
        DBObject object = new BasicDBObject("name", "jack"); // 构建限制条件 {"name": "jack"}
        BasicQuery query = new BasicQuery(object.toString());
        // 也可以直接写 BasicQuery query = new BasicQuery("{name: 'jack'}");
        List<User> users = template.find(query, User.class, "users");
        /* User(id=62b2f7a49e2891ce607eb800, name=jack, age=30, hobbies=[playing]) */
        users.forEach(System.out::println);
    }

    // MongoDB查询-Bson方式
    // 查询name等于jack 同时age小于40的数据
    @Test
    public void case3Test() {
        Bson bson = Filters.and(Arrays.asList(Filters.eq("name", "jack"),
                Filters.lt("age", 40)));
        MongoCursor<Document> cursor = null;
        try {
            FindIterable<Document> findIterable = template.getCollection("users").find(bson);
            cursor = findIterable.iterator();
            List<User> users = new ArrayList<>();
            while (cursor.hasNext()) {
                Document userDocument = cursor.next();
                User user = JSON.parseObject(userDocument.toJson(), User.class);
                users.add(user);
            }
            users.forEach(System.out::println);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    // 需求：2019年每月份平均人均消费金额
    @Test
    public void case4Test() {
        Aggregation aggregation = Aggregation.newAggregation(
            Aggregation.match(Criteria.where("travelTime")
                    .gte(this.getDate(2019,0, 1)).lt(this.getDate(2020,0, 1))),

            Aggregation.project("travelTime")
                    .andExpression("{$month:'$travelTime'}").as("travelMonth").andInclude("perExpend"),

            Aggregation.group("travelMonth")
                    .sum("perExpend").as("sum")
                    .avg("perExpend").as("avg")
                    .min("perExpend").as("min")
                    .max("perExpend").as("max"),

            Aggregation.project().and("_id").as("month").andInclude("sum","avg","min","max"),

            Aggregation.sort(Sort.Direction.ASC,"month")

        );
        AggregationResults<TravelVO> aggregationResults =
                template.aggregate(aggregation, "travel", TravelVO.class);
        for (TravelVO aggregationResult : aggregationResults) {
            System.out.println(aggregationResult);
        }
    }

    private Date getDate(int year, int month, int day){
        Calendar instance = Calendar.getInstance();
        instance.set(year, month, day);
        return instance.getTime();
    }
}
