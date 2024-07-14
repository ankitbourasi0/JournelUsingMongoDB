package com.bourasi.learn.journelusingmongodb.Repository;

import com.bourasi.learn.journelusingmongodb.Entity.Journel;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  JournelRepository extends MongoRepository<Journel, ObjectId> {

}
