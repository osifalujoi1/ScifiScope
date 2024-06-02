DROP TABLE IF EXISTS Movie;

CREATE TABLE IF NOT EXISTS Movie(
    id INT NOT NULL,
    title varchar(250) NOT NULL,
    release_date timestamp NOT NULL,
    prod_company varchar(250) NOT NULL,
    review INT NOT NULL,
    rating varchar(10) NOT NULL,
    url varchar(250) NOT NULL,
    version INT,
    PRIMARY KEY (id)
);