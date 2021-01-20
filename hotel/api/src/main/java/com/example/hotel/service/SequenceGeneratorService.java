package com.example.hotel.service;

import com.example.hotel.model.DatabaseSequence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class SequenceGeneratorService {
    @Autowired
    private MongoOperations mongoOperations;

    /**
     * Reference : https://github.com/Java-Techie-jt/mongo-sequence-id-example/blob/main/src/main/java/com/javatechie/mongo/service/SequenceGeneratorService.java
     * @param sequenceName
     * @return
     */

    public int getSequenceNumber(String sequenceName) {
        Query query = new Query(Criteria.where("id").is(sequenceName));
        Update update = new Update().inc("seq", 1);
        DatabaseSequence counter = mongoOperations
                .findAndModify(query,
                        update, FindAndModifyOptions.options().returnNew(true).upsert(true),
                        DatabaseSequence.class);

        return !Objects.isNull(counter) ? counter.getSeq() : 1;
    }

}
