package day26.workshop.repo;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import jakarta.json.JsonObject;

@Repository
public class GameRepo {

    @Autowired
    MongoTemplate mongoTemplate;

    public List<Document> getAllGames(int offset, int limit) {

        List<Document> results = mongoTemplate.find(new Query().limit(limit).skip(offset), 
        Document.class, "game");

        return results;

    }

    public List<Document> getAllGamesByAsc(int offset, int limit) {

        List<Document> results = mongoTemplate.find(new Query().limit(limit).skip(offset)
        .with(Sort.by(Sort.Direction.ASC, "ranking")), 
        Document.class, "game");

        return results;

    }

    public Long countAllGames() {
        Long count = mongoTemplate.count(new Query(), "game");

        return count;
    }

    public List<Document> getGameByGid(int gid) {
        
        List<Document> result = mongoTemplate.find(new Query(Criteria.where("gid").is(gid)), 
        Document.class, "game");

        return result;
    }
    
}
