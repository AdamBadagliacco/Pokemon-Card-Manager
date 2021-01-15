-- MySQL Workbench Forward Engineering
SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
-- -----------------------------------------------------
-- Schema pokemondb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema pokemondb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `pokemondb` DEFAULT CHARACTER SET utf8 ;
USE `pokemondb` ;
-- -----------------------------------------------------
-- Table `pokemondb`.`pokemon`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pokemondb`.`pokemon` (
  `id` VARCHAR(45) NOT NULL,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  `pokedexNumber` VARCHAR(200) NULL DEFAULT NULL,
  `imgUrl` VARCHAR(200) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;
-- -----------------------------------------------------
-- Table `pokemondb`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pokemondb`.`user` (
  `username` VARCHAR(45) NULL DEFAULT 'pleasework',
  `password` VARCHAR(45) NULL DEFAULT NULL,
  `token` VARCHAR(45) NULL,
  PRIMARY KEY (`username`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;
-- -----------------------------------------------------
-- Table `pokemondb`.`collection`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pokemondb`.`collection` (
  `pokemon_id` VARCHAR(45) NOT NULL,
  `id` INT NOT NULL,
  `username` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`, `pokemon_id`, `username`),
  INDEX `fk_Pokemon_has_User1_Pokemon1_idx` (`pokemon_id` ASC) VISIBLE,
  INDEX `fk_collection_user1_idx` (`username` ASC) VISIBLE,
  CONSTRAINT `fk_Pokemon_has_User1_Pokemon1`
    FOREIGN KEY (`pokemon_id`)
    REFERENCES `pokemondb`.`pokemon` (`id`),
  CONSTRAINT `fk_collection_user1`
    FOREIGN KEY (`username`)
    REFERENCES `pokemondb`.`user` (`username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;
SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;