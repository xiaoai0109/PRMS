use phoenix;
select * from `program-slot`;
select * from `radio-program`;
select * from  `user`;
select  SEC_TO_TIME(TIME_TO_SEC(`startTime`) + TIME_TO_SEC(`duration`)) from `program-slot`;


INSERT INTO `phoenix`.`program-slot` VALUES ('charity', '2018-08-30', '19:00:00', '00:30:00','dilbert', 'dilbert');
INSERT INTO `phoenix`.`program-slot` VALUES ('news', '2018-08-30', '19:30:00', '00:30:00', 'dilbert', 'wally');
INSERT INTO `phoenix`.`program-slot` VALUES ('news', '2018-08-31', '19:30:00', '00:30:00', 'wally', 'wally');

select startTime, duration, startTime + duration from `program-slot`;
select startTime, duration, SEC_TO_TIME(TIME_TO_SEC(`startTime`) + TIME_TO_SEC(`duration`)) endTime
	from `program-slot` 
	where dateOfProgram = '2018-08-30' AND
    ((startTime >= '20:00:00'  AND startTime < '20:30:00') 
    OR (SEC_TO_TIME(TIME_TO_SEC(`startTime`) + TIME_TO_SEC(`duration`)) > '20:00:00' 
    AND SEC_TO_TIME(TIME_TO_SEC(`startTime`) + TIME_TO_SEC(`duration`)) <= '20:30:00'));
    


CREATE  TABLE IF NOT EXISTS `phoenix`.`program-slot` (
  `program-name` VARCHAR(45) NOT NULL ,
  `dateOfProgram` DATE NOT NULL ,
  `startTime` TIME NOT NULL ,
  `duration` TIME NULL ,
  `presenter` VARCHAR(45) NULL ,
  `producer` VARCHAR(45) NULL ,
  PRIMARY KEY (`dateOfProgram`, `startTime`) ,
  CONSTRAINT `name`
    FOREIGN KEY (`program-name` )
    REFERENCES `phoenix`.`radio-program` (`name` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `presenter`
    FOREIGN KEY (`presenter` )
    REFERENCES `phoenix`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `producer`
    FOREIGN KEY (`producer` )
    REFERENCES `phoenix`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `name_program_slot` ON `phoenix`.`program-slot` (`program-name` ASC) ;

CREATE INDEX `dateOfProgram` ON `phoenix`.`program-slot` (`dateOfProgram` ASC) ;

drop table `program-slot`;
