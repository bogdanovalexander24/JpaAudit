package com.myexample.audit.service;

import com.myexample.audit.entity.Document;
import com.myexample.audit.repository.DocumentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DocumentService {
    private final DocumentRepository documentRepository;

    public DocumentService(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    public Document save(Document document) {
       return documentRepository.save(document);
    }

    public Document findById(Long id) {
        return documentRepository.findById(id)
            .orElseThrow();
    }

    public List<Document> findByType(String type) {
        return documentRepository.findByType(type);
    }
}
