package com.bourasi.learn.journelusingmongodb.Repository;

import com.bourasi.learn.journelusingmongodb.Entity.Journel;
import com.bourasi.learn.journelusingmongodb.Entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, ObjectId> {
    User findByUsername(String username);
}
