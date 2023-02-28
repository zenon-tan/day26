package day26.lecture.repositories;

import java.util.Collections;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import static day26.lecture.services.Constants.*;

@Repository
public class TvShowRepo {



    @Autowired
    private MongoTemplate template;

    // db.shows.find({ language: "English" })
    public List<Document> findTvShowsByLang(String lang) {
        // create a criteria
        Criteria criteria = Criteria.where(FIELD_LANGUAGE).is(lang);
        // 
        Criteria criteria2 = Criteria.where(FIELD_LANGUAGE).regex(lang, "i");

        //create a query (core.query)
        Query query = Query.query(criteria);
        //returns List<Document>
        List<Document> results = template.find(query, Document.class, COLLECTION_TV);

        return results;

    }

    public List<String> getAllTypes() {
        List<String> types = template.findDistinct(new Query(),
         FIELD_TYPE, COLLECTION_TV, String.class);
         Collections.sort(types);

         return types;
    }

    public List<Document> findTvShowsByType(String type) {
        Criteria criteria = Criteria.where(FIELD_TYPE).is(type);

        Query query = Query.query(criteria)
        .with(Sort.by(Sort.Direction.DESC, "rating.average"));

        query.fields()
        .exclude("_id")
        .include(FIELD_ID, FIELD_NAME, "rating.average");

        return template.find(query, Document.class, COLLECTION_TV);
    }

    public List<Document> findTvShowsByType(String type, int limit, int skip) {
        Criteria criteria = Criteria.where(FIELD_TYPE).is(type);

        Query query = Query.query(criteria)
        .with(Sort.by(Sort.Direction.DESC, "rating.average"))
        .limit(limit)
        .skip(skip);
        
        query.fields()
        .exclude("_id")
        .include(FIELD_ID, FIELD_NAME, "rating.average");

        return template.find(query, Document.class, COLLECTION_TV);
    }

    public Document findTvShowsById(int id) {
        // create a criteria
        Criteria criteria = Criteria.where(FIELD_ID).is(id);

        //create a query (core.query)
        Query query = Query.query(criteria);
        //returns List<Document>
        Document results = template.find(query, Document.class, COLLECTION_TV).get(0);

        return results;

    }

    
}
