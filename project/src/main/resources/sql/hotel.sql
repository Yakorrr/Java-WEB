-- MySQL Script generated by MySQL Workbench
-- Sat Jul 27 18:13:53 2019
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0;
SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0;
SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE =
        'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema hotel
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema hotel
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `hotel` DEFAULT CHARACTER SET utf8;
USE `hotel`;

-- -----------------------------------------------------
-- Table `hotel`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hotel`.`users`
(
    `id`               INT                    NOT NULL AUTO_INCREMENT,
    `first_name`       NVARCHAR(120)          NOT NULL,
    `last_name`        NVARCHAR(120)          NOT NULL,
    `date_of_birth`    DATE                   NOT NULL,
    `gender`           VARCHAR(10)            NOT NULL,
    `telephone_number` VARCHAR(20)            NOT NULL,
    `email`            VARCHAR(100)           NOT NULL,
    `role`             VARCHAR(45)            NOT NULL,
    `language`         VARCHAR(55) DEFAULT '' NOT NULL,
    `pass_encoded`     VARCHAR(200)           NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX idUsers_UNIQUE (`id` ASC) VISIBLE,
    UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hotel`.`requests`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hotel`.`requests`
(
    `id`         INT          NOT NULL AUTO_INCREMENT,
    `user_id`    INT          NOT NULL,
    `places`     INT          NOT NULL,
    `class`      VARCHAR(100) NOT NULL,
    `start_date` DATE         NOT NULL,
    `end_date`   DATE         NOT NULL,
    `isApproved` TINYINT      NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX idRequests_UNIQUE (`id` ASC) VISIBLE,
    INDEX `fk_requests_users2_idx` (`user_id` ASC) VISIBLE,
    CONSTRAINT `fk_requests_users2`
        FOREIGN KEY (`user_id`)
            REFERENCES `hotel`.`users` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hotel`.`rooms`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hotel`.`rooms`
(
    `id`         INT            NOT NULL AUTO_INCREMENT,
    `places`     INT            NOT NULL,
    `class`      VARCHAR(45)    NOT NULL,
    `isOccupied` TINYINT        NOT NULL,
    `picURL`     VARCHAR(200)   NOT NULL,
    `price`      DECIMAL(13, 2) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX idRooms_UNIQUE (`id` ASC) VISIBLE
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hotel`.`bills`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hotel`.`bills`
(
    `id`         INT            NOT NULL AUTO_INCREMENT,
    `sum`        DECIMAL(13, 2) NOT NULL,
    `isPaid`     TINYINT        NOT NULL,
    `request_id` INT            NOT NULL,
    `room_id`    INT            NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX idBills_UNIQUE (`id` ASC) VISIBLE,
    INDEX `fk_bills_requests1_idx` (`request_id` ASC) VISIBLE,
    UNIQUE INDEX `requests_id_UNIQUE` (`request_id` ASC) VISIBLE,
    INDEX `fk_bills_rooms2_idx` (`room_id` ASC) VISIBLE,
    CONSTRAINT `fk_bills_requests1`
        FOREIGN KEY (`request_id`)
            REFERENCES `hotel`.`requests` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_bills_rooms2`
        FOREIGN KEY (`room_id`)
            REFERENCES `hotel`.`rooms` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB;

INSERT INTO `hotel`.`users` (`first_name`, `last_name`, `date_of_birth`, `gender`, `telephone_number`, `email`, `role`,
                             `language`, `pass_encoded`)
VALUES ('user', 'user', '2023-01-01', 'male', '+123-456-78-90', 'user@user.com', 'USER', 'en',
        'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3');
INSERT INTO `hotel`.`users` (`first_name`, `last_name`, `date_of_birth`, `gender`, `telephone_number`, `email`, `role`,
                             `language`, `pass_encoded`)
VALUES ('admin', 'admin', '2020-06-12', 'male', '+(123)-456-9876', 'ad@ad.com', 'ADMIN', 'en',
        'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3');
