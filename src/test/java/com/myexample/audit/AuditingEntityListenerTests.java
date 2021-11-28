package com.myexample.audit;

import com.myexample.audit.entity.Document;
import com.myexample.audit.entity.Status;
import com.myexample.audit.service.DocumentService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.IOException;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class AuditingEntityListenerTests {
    private final JdbcTemplate jdbcTemplate;
    private final DocumentService documentService;
    private final ApplicationContext context;

    private final String FIRST_DOCUMENT_TYPE = "1";

    @Autowired
    public AuditingEntityListenerTests(JdbcTemplate jdbcTemplate, DocumentService documentService, ApplicationContext context) {
        this.jdbcTemplate = jdbcTemplate;
        this.documentService = documentService;
        this.context = context;
    }

    @BeforeEach
    void initTables() throws IOException {
        log.info("start init tables");
        var alterStatusFile = context.getResource("classpath:sql/jpaentitylistener/alter_status.sql").getFile();

        var alterStatusSql = new String(Files.readAllBytes(alterStatusFile.toPath()));
        log.info("StatusSql : " + alterStatusSql);

        try {
            jdbcTemplate.execute(alterStatusSql);
        } catch (Exception e) {
            log.warn("already exist");
        }

        log.info("finish init tables");
    }

    @Test
    void saveDocument() {
        var status = Status.builder()
            .updateBy("Jhon")
            .verified(true)
            .build();
        var document = Document.builder()
            .type(FIRST_DOCUMENT_TYPE)
            .status(status)
            .build();

        var savedStatus = documentService.createDocumentOrUpdate(document).getStatus();

        assertNotNull(savedStatus.getCreatedAt());
        assertNotNull(savedStatus.getUpdatedAt());
    }

    @Test
    void updateDocument() {
        var document = documentService.findByType(FIRST_DOCUMENT_TYPE).get(0);
        var status = document.getStatus();
        status.setVerified(false);

        var savedDocument = documentService.createDocumentOrUpdate(document);
        var savedStatus = savedDocument.getStatus();

        assertFalse(savedStatus.getVerified());
        assertNotSame(savedStatus.getUpdatedAt(), status.getUpdatedAt());
        assertSame(savedStatus.getCreatedAt(), status.getCreatedAt());
    }
}
