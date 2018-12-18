package com.ferhat.multipartFileUpdate.repository;

import com.ferhat.multipartFileUpdate.entity.File;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FileRepository extends MongoRepository<File, String> {
    File findFirstById(String id);
}
