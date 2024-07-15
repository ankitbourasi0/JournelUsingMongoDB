package com.bourasi.learn.journelusingmongodb.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "users")
@Data
@NoArgsConstructor //required for deserialization for json to pojo
public class User {

    @Id
    private ObjectId id;
    /*
        1. username must be unique, thats why we are using indexing.
        2. by default auto indexing will no happen here because it it the concept of mongo db so enable auto indexing using spring boot
        add this in application.properties:- spring.data.mongodb.auto-index-creation=true
    */
    @Indexed(unique = true)
    @NonNull
    private String username;

    @NonNull
    private String password;

    /*
        this list will store reference of journelEnteries not whole data only reference of it.
        either primary key or any unique key with that we can fetch them
        it act same like foreign key in SQL,
        this list have multiple journel reference
    */
    @DBRef
    private List<Journel> journelEntries = new ArrayList<>();
}
