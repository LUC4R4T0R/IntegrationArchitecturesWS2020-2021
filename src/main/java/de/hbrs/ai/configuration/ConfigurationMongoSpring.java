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
    public MongoDatabase mongoDatabase() {
        MongoClient client = new com.mongodb.MongoClient("localhost", 27017);
        MongoDatabase supermongo = client.getDatabase("highperformance");
        return supermongo;
    }

    @Bean
    public ManagePersonal managePersonal(MongoDatabase db) {
        MongoCollection<Document> salesmanCollection = db.getCollection("salesmen");
        MongoCollection<Document> recordsCollection = db.getCollection("records");
        return new ManagePersonal(salesmanCollection, recordsCollection);
    }
}
