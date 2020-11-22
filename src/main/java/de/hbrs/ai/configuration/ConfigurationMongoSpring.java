package de.hbrs.ai.configuration;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import de.hbrs.ai.model.SalesMan;
import de.hbrs.ai.model.SalesManRecord;
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
        MongoCollection<SalesMan> salesmanCollection = supermongo.getCollection("salesmen", SalesMan.class);
        MongoCollection<SalesManRecord> recordsCollection = supermongo.getCollection("records",SalesManRecord.class);
        return new ManagePersonal(salesmanCollection, recordsCollection);
    }
}
