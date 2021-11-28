package com.myexample.audit.repository;


import com.myexample.audit.entity.Document;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DocumentRepository extends CrudRepository<Document, Long> {
    List<Document> findByType(String type);
}
