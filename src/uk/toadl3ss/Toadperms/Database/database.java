package uk.toadl3ss.Toadperms.Database;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

public class database {
    public static MongoClient mongoClient;
    public static DBCollection user;
    public static DB database;
    public static void main(String uri) {
        mongoClient = new MongoClient(new MongoClientURI(uri));
        database = mongoClient.getDB("MinecraftPermissions");
        user = database.getCollection("users");
        // Disable MongoDB logging in general
        System.setProperty("DEBUG.MONGO", "false");
        // Disable DB operation tracing
        System.setProperty("DB.TRACE", "false");
    }
}