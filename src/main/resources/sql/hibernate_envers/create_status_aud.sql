CREATE TABLE status_aud
(
    id                    bigint NOT NULL,
    verified              BOOLEAN NOT NULL,
    updated_by            VARCHAR(100) NOT NULL,
    REV                   INTEGER NOT NULL,
    REVTYPE               INTEGER,
    PRIMARY KEY (id, rev),
    CONSTRAINT FK5ecvi1a0ykunrriib7j28vpdj FOREIGN KEY (REV) REFERENCES REVINFO
);
