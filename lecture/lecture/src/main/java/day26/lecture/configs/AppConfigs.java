package day26.lecture.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

@Configuration
public class AppConfigs {

    // Use a constant for all database names
    public static final String SHOWS_DB = "shows";

    // Inject the mongo connection
    @Value("${MONGO_URL}")
    private String mongoUrl;
    

    // Create and initialise mongotemplate
    @Bean
    //@Bean("shows")
    public MongoTemplate createMongoTemplate() {
        // Create a mongo client with the mongo connection url
        MongoClient client = MongoClients.create(mongoUrl);

        System.out.println(mongoUrl);
        // Include the database name
        return new MongoTemplate(client, SHOWS_DB);
    
    }
    
}
