CREATE TABLE dev_skill
(
    dev_id int not null,
    sk_id int,
    PRIMARY KEY (dev_id, sk_id), # не заработает пока один из ключей может быть null (либо убрать primary_key, лидо все not_null)
    FOREIGN KEY (dev_id) REFERENCES developer (id) ON DELETE CASCADE,
    FOREIGN KEY (sk_id) REFERENCES skill (id) ON DELETE SET NULL
);

CREATE TABLE dev_skill
(
    dev_id int not null,
    sk_id int not null,
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

CREATE TABLE tm_dev
(
    tm_id int not null,
    dev_id int not null,
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