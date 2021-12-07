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
import org.springframework.jdbc.core.RowCountCallbackHandler;

import java.io.IOException;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class HibernateEnversTests {
    private final JdbcTemplate jdbcTemplate;
    private final DocumentService documentService;
    private final ApplicationContext context;

    private final String SECOND_DOCUMENT_TYPE = "2";
    private final String SELECT_ALL_FROM_STATUS_AUD = "SELECT * FROM status_aud";

    @Autowired
    public HibernateEnversTests(JdbcTemplate jdbcTemplate, DocumentService documentService, ApplicationContext context) {
        this.jdbcTemplate = jdbcTemplate;
        this.documentService = documentService;
        this.context = context;
    }

    @BeforeEach
    void initTables() throws IOException {
        log.info("start init tables");
        var createRevInfoFile = context.getResource("classpath:sql/hibernate_envers/create_rev_info.sql").getFile();
        var createStatusAudFile = context.getResource("classpath:sql/hibernate_envers/create_status_aud.sql").getFile();

        var createRevInfoSql = new String(Files.readAllBytes(createRevInfoFile.toPath()));
        var createStatusAudSql = new String(Files.readAllBytes(createStatusAudFile.toPath()));
        log.info("RevInfoSql : " + createRevInfoSql);
        log.info("StatusAudSql : " + createRevInfoSql);

        try {
            jdbcTemplate.execute(createRevInfoSql);
            jdbcTemplate.execute(createStatusAudSql);
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
            .type(SECOND_DOCUMENT_TYPE)
            .status(status)
            .build();

        var rowHandler = new RowCountCallbackHandler();

        jdbcTemplate.query(SELECT_ALL_FROM_STATUS_AUD, rowHandler);

        var oldNumberOfRows = rowHandler.getRowCount();

        log.info("Status_Aud contains before " + oldNumberOfRows);

        var savedStatus = documentService.save(document).getStatus();

        rowHandler = new RowCountCallbackHandler();

        jdbcTemplate.query(SELECT_ALL_FROM_STATUS_AUD, rowHandler);

        var newNumberOfRows = rowHandler.getRowCount();

        log.info("Status_Aud contains now " + newNumberOfRows);

        assertTrue(oldNumberOfRows != newNumberOfRows);
    }
}
