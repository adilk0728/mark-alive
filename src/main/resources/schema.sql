DROP TABLE IF EXISTS Bookmark;

CREATE TABLE IF NOT EXISTS Bookmark (
    id int NOT NULL AUTO_INCREMENT,
    url varchar(255) NOT NULL,
    created date,
    remind_after_day int,
    remind BIT,
    PRIMARY KEY(id)
);

ALTER TABLE Bookmark ALTER remind SET DEFAULT 0;
