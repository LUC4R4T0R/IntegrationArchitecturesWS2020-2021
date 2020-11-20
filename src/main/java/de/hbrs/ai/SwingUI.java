package de.hbrs.ai;

import java.io.IOException;

import javax.swing.JFrame;

import de.hbrs.ai.repository.ManagePersonal;
import de.hbrs.ai.swing.Overview;
import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class SwingUI {
	
    private static MongoClient client;
    private static MongoDatabase supermongo;
    private static MongoCollection<Document> salesmen;
    private static MongoCollection<Document> records;

    public static ManagePersonal mP;
	
    public static void main(String[] args) throws IOException {
        client = new MongoClient("localhost", 27017);
        supermongo = client.getDatabase("highperformance");
        salesmen = supermongo.getCollection("salesmen");
        records = supermongo.getCollection("records");
        
        mP = new ManagePersonal(salesmen, records);

        JFrame frame = new Overview();
        frame.setVisible(true);
    }
}
