import java.io.IOException;

import javax.swing.JFrame;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class Main {
	
    private static MongoClient client;
    private static MongoDatabase supermongo;
    private static MongoCollection<Document> salesmen;

    public static ManagePersonal mP;
	
    public static void main(String[] args) throws IOException {
        JFrame frame = new Overview();
        frame.setVisible(true);
        
        mP = new ManagePersonal();
        
        client = new MongoClient("localhost", 27017);
        supermongo = client.getDatabase("highperformance");
        salesmen = supermongo.getCollection("salesmen");
    }
    
    public static MongoCollection<Document> getSalesmen() {
		return salesmen;
	}
}
