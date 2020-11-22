package de.hbrs.ai;

import java.io.IOException;

import javax.swing.JFrame;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClients;
import de.hbrs.ai.model.SalesMan;
import de.hbrs.ai.model.SalesManRecord;
import de.hbrs.ai.repository.ManagePersonal;
import de.hbrs.ai.swing.Overview;
import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class SwingUI {
	
    private static MongoClient client;
    private static MongoDatabase supermongo;
    private static MongoCollection<SalesMan> salesmen;
    private static MongoCollection<SalesManRecord> records;

    public static ManagePersonal mP;
	
    public static void main(String[] args) throws IOException {
        ConnectionString connectionString = new ConnectionString("http://localhost:27017");
        CodecRegistry pojoCodecRegistry = fromProviders(PojoCodecProvider.builder().automatic(true).build());
        CodecRegistry codecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), pojoCodecRegistry);
        MongoClientSettings clientSettings = MongoClientSettings.builder().applyConnectionString(connectionString).codecRegistry(codecRegistry).build();
        com.mongodb.client.MongoClient mongoClient = MongoClients.create(clientSettings);
        supermongo = mongoClient.getDatabase("highperformance");
        salesmen = supermongo.getCollection("salesmen", SalesMan.class);
        records = supermongo.getCollection("records", SalesManRecord.class);
        
        mP = new ManagePersonal(salesmen, records);

        JFrame frame = new Overview();
        frame.setVisible(true);
    }
}
