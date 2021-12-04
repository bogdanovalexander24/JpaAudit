//package com.myexample.audit;
//
//import com.myexample.audit.entity.Document;
//import com.myexample.audit.entity.Status;
//import com.myexample.audit.repository.StatusAuditRepository;
//import com.myexample.audit.service.DocumentService;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.context.ApplicationContext;
//import org.springframework.jdbc.core.JdbcTemplate;
//
//import java.io.IOException;
//import java.nio.file.Files;
//
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//
//@SpringBootTest
//@Slf4j
//class AuditOverrideTest {
//    private final JdbcTemplate jdbcTemplate;
//    private final DocumentService documentService;
//    private final ApplicationContext context;
//    private final StatusAuditRepository statusAuditRepository;
//
//    private final String FOURTH_DOCUMENT_TYPE = "4";
//
//    @Autowired
//    public AuditOverrideTest(JdbcTemplate jdbcTemplate, DocumentService documentService, ApplicationContext context, StatusAuditRepository statusAuditRepository) {
//        this.jdbcTemplate = jdbcTemplate;
//        this.documentService = documentService;
//        this.context = context;
//        this.statusAuditRepository = statusAuditRepository;
//    }
//
//
//    @BeforeEach
//    void initTables() throws IOException {
//        log.info("start init tables");
//        var alterStatusAudFile = context.getResource("classpath:sql/audit_override/alter_status_aud.sql").getFile();
//
//        var alterStatusAudSql = new String(Files.readAllBytes(alterStatusAudFile.toPath()));
//        log.info("StatusSql : " + alterStatusAudSql);
//
//        try {
//            jdbcTemplate.execute(alterStatusAudSql);
//        } catch (Exception e) {
//            log.warn("already exist");
//        }
//
//        log.info("finish init tables");
//    }
//
//    @Test
//    void combineAuditInfo() {
//        var status = Status.builder()
//            .updateBy("Mike")
//            .verified(true)
//            .build();
//        var document = Document.builder()
//            .type(FOURTH_DOCUMENT_TYPE)
//            .status(status)
//            .build();
//
//        var savedStatusId = documentService.save(document).getStatus().getId();
//
//        var statusAuds = statusAuditRepository.findAllById(savedStatusId);
//
//        statusAuds.forEach(statusAudit -> log.info(statusAudit.toString()));
//
//        assertNotNull(statusAuds.get(0).getCreatedAt());
//        assertNotNull(statusAuds.get(0).getUpdatedAt());
//    }
//}
