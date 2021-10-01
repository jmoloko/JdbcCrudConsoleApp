CREATE TABLE tm_dev
(
    tm_id INT NOT NULL,
    dev_id INT NOT NULL,
    PRIMARY KEY (tm_id, dev_id),
    FOREIGN KEY (tm_id)
        REFERENCES team (id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    FOREIGN KEY (dev_id)
        REFERENCES developer (id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);