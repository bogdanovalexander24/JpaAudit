//package com.myexample.audit;
//
//import com.myexample.audit.entity.Document;
//import com.myexample.audit.entity.Status;
//import com.myexample.audit.repository.StatusAuditRepository;
//import com.myexample.audit.service.DocumentService;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//@SpringBootTest
//@Slf4j
//class StatusAuditRepositoryTest {
//    private final DocumentService documentService;
//    private final StatusAuditRepository statusAuditRepository;
//
//    private final String THIRD_DOCUMENT_TYPE = "3";
//
//    @Autowired
//    public StatusAuditRepositoryTest(DocumentService documentService, StatusAuditRepository statusAuditRepository) {
//        this.documentService = documentService;
//        this.statusAuditRepository = statusAuditRepository;
//    }
//
//    @Test
//    void findAllRows() {
//       var result = statusAuditRepository.findAll();
//       result.forEach(statusAudit -> log.info(statusAudit.toString()));
//
//       assertTrue(result.size() > 0);
//    }
//
//    @Test
//    void findAllById() {
//        var status = Status.builder()
//            .updateBy("Jhon")
//            .verified(true)
//            .build();
//        var document = Document.builder()
//            .type(THIRD_DOCUMENT_TYPE)
//            .status(status)
//            .build();
//
//        var savedStatus = documentService.save(document).getStatus();
//
//        var result = statusAuditRepository.findAllById(savedStatus.getId());
//
//        result.forEach(statusAudit -> log.info(statusAudit.toString()));
//
//        assertTrue(result.size() > 0);
//    }
//}
