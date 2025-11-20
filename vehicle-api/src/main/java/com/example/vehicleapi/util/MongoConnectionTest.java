package com.example.vehicleapi.util;

import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class MongoConnectionTest {
    
    private static final Logger logger = LoggerFactory.getLogger(MongoConnectionTest.class);
    
    @Autowired
    private MongoClient mongoClient;

    @PostConstruct
    public void testConnection() {
        try {
            MongoDatabase database = mongoClient.getDatabase("admin");
            database.runCommand(new Document("ping", 1));
            logger.info("Conexión a MongoDB verificada.");
        } catch (MongoException e) {
            logger.error("Error al conectar con MongoDB:", e);
        }
    }
}
