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
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.IOException;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Slf4j
class AuditApplicationTests {
    private final JdbcTemplate jdbcTemplate;
    private final DocumentService documentService;
    private final ApplicationContext context;

    @Autowired
    public AuditApplicationTests(JdbcTemplate jdbcTemplate, DocumentService documentService, ApplicationContext context) {
        this.jdbcTemplate = jdbcTemplate;
        this.documentService = documentService;
        this.context = context;
    }

    @BeforeEach
    void initTables() throws IOException {
        log.info("start init tables");
        var hibernateSequenceFile = context.getResource("classpath:sql/create_hibernate_sequence.sql").getFile();
        var documentSqlFile = context.getResource("classpath:sql/create_document.sql").getFile();
        var statusSqlFile = context.getResource("classpath:sql/create_status.sql").getFile();

        var statusSql = new String(Files.readAllBytes(statusSqlFile.toPath()));
        log.info("StatusSql : " + statusSql);
        var documentSql = new String(Files.readAllBytes(documentSqlFile.toPath()));
        log.info("DocumentSql : " + documentSql);
        var hibernateSequenceSql = new String(Files.readAllBytes(hibernateSequenceFile.toPath()));
        log.info("Hibernate sql: " + hibernateSequenceSql);


        try {
            jdbcTemplate.execute(documentSql);
            jdbcTemplate.execute(statusSql);
            jdbcTemplate.execute(hibernateSequenceSql);
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
            .type("0")
            .status(status)
            .build();

        var id = documentService.save(document).getId();

        assertNotNull(id);
    }
}
