package de.hbrs.ia;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import de.hbrs.ia.model.SalesMan;
import de.hbrs.ia.model.SalesManRecord;
import de.hbrs.ia.repository.ManagePersonal;
import de.hbrs.ia.swing.Overview;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import javax.swing.*;
import java.io.IOException;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class SwingUI {

    public static ManagePersonal mP;
	
    public static void main(String[] args) throws IOException {
        ConnectionString connectionString = new ConnectionString("mongodb://localhost:27017");
        CodecRegistry pojoCodecRegistry = fromProviders(PojoCodecProvider.builder().automatic(true).build());
        CodecRegistry codecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), pojoCodecRegistry);
        MongoClientSettings clientSettings = MongoClientSettings.builder().applyConnectionString(connectionString).codecRegistry(codecRegistry).build();
        com.mongodb.client.MongoClient mongoClient = MongoClients.create(clientSettings);
        MongoDatabase supermongo = mongoClient.getDatabase("highperformance");
        MongoCollection<SalesMan> salesmen = supermongo.getCollection("salesmen", SalesMan.class);
        MongoCollection<SalesManRecord> records = supermongo.getCollection("records", SalesManRecord.class);
        
        mP = new ManagePersonal(salesmen, records);

        JFrame frame = new Overview();
        frame.setVisible(true);
    }
}
