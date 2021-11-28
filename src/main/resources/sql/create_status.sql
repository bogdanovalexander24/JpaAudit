CREATE TABLE status
(
    id                    BIGINT NOT NULL,
    verified              BOOLEAN NOT NULL,
    updated_by            VARCHAR(100) NOT NULL,
    CONSTRAINT PK_status_id PRIMARY KEY (id)
);
