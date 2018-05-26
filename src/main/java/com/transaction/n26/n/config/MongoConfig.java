package com.transaction.n26.n.config;

import com.mongodb.MongoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.io.IOException;
import cz.jirutka.spring.embedmongo.EmbeddedMongoFactoryBean;


@Configuration
public class MongoConfig {
    private static final String MONGO_DB_URL = "localhost";
    private static final String MONGO_DB_NAME = "embeded_db";
    private static final int MONGO_DB_PORT = 3306;

    @Bean
    public MongoTemplate mongoTemplate() throws IOException {
        EmbeddedMongoFactoryBean mongo = new EmbeddedMongoFactoryBean();
        mongo.setBindIp(MONGO_DB_URL);
        mongo.setPort(MONGO_DB_PORT);
        MongoClient mongoClient = mongo.getObject();
        return new MongoTemplate(mongoClient, MONGO_DB_NAME);
    }
}
