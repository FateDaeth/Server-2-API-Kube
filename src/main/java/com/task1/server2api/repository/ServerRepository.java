package com.task1.server2api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.task1.server2api.entity.Server;

public interface ServerRepository extends MongoRepository<Server, String>{

}
