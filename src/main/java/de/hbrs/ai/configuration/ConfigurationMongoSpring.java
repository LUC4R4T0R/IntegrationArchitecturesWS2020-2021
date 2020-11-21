package de.hbrs.ai.configuration;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import de.hbrs.ai.repository.ManagePersonal;
import org.bson.Document;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.mongodb.MongoClient;

@Configuration
public class ConfigurationMongoSpring {

    @Bean
    public ManagePersonal managePersonal() {
        MongoClient client = new com.mongodb.MongoClient("localhost", 27017);
        MongoDatabase supermongo = client.getDatabase("highperformance");
        MongoCollection<Document> salesmanCollection = supermongo.getCollection("salesmen");
        MongoCollection<Document> recordsCollection = supermongo.getCollection("records");
        return new ManagePersonal(salesmanCollection, recordsCollection);
    }
}
