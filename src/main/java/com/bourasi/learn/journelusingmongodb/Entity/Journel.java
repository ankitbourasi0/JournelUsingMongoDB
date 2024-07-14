package com.bourasi.learn.journelusingmongodb.Entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "journelentries")
@Data
public class Journel {

    @Id
    private ObjectId id;
    private String title;
    private String content;
    private LocalDateTime date;

}
