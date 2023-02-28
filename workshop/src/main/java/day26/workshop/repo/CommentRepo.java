package day26.workshop.repo;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class CommentRepo {

    @Autowired

    MongoTemplate mongoTemplate;

    public List<Document> getAverageByGid(int gid) {

        return mongoTemplate.find(new Query(Criteria.where("gid").is(gid)), 
        Document.class, "comment");

    }
    
}
