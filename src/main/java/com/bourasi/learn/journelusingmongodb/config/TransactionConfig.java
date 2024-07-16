package com.bourasi.learn.journelusingmongodb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement//this will create context or container on all method have @Transactional annotation, so that will work as one operation or db work
public class TransactionConfig {
    //this method work as bean and tell spring to use mongotransaction manager for rollback and commit
    //also MongoDatabaseFactory is interface is do DB work, like connection, session for use
    //method name could be any add, ok, etc, spring find the bean who implement PTM
    @Bean
    public PlatformTransactionManager add(MongoDatabaseFactory dbFactory){
        return new MongoTransactionManager(dbFactory);
    }
}
