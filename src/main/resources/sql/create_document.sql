CREATE TABLE document
(
    id                    bigint NOT NULL,
    type                  VARCHAR(100) NOT NULL,
    status_id             bigint NOT NULL,
    CONSTRAINT PK_document_id PRIMARY KEY (id)
);
