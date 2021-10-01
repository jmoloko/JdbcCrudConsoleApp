CREATE TABLE dev_skill
(
    dev_id INT NOT NULL,
    sk_id INT NOT NULL,
    PRIMARY KEY (dev_id, sk_id),
    FOREIGN KEY (dev_id)
        REFERENCES developer (id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    FOREIGN KEY (sk_id)
        REFERENCES skill (id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);