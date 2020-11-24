package de.hbrs.ia.configuration;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import de.hbrs.ia.model.SalesMan;
import de.hbrs.ia.model.SalesManRecord;
import de.hbrs.ia.repository.ManagePersonal;
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
