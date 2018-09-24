use phoenix;
select max(id)+1 from `program-slot`;
select * from `program-slot` order by `dateOfProgram` desc;
SELECT * FROM `program-slot` ORDER BY `dateOfProgram` DESC;
select * from `radio-program`;
select *  from  `user`;
select  `dateOfProgram` + `startTime`, `duration`, SEC_TO_TIME(TIME_TO_SEC(`startTime`) + TIME_TO_SEC(`duration`)) from `program-slot`;
select STR_TO_DATE(CONCAT(`dateOfProgram`, ' ', `startTime`)) from `program-slot`; 
select str_to_date(CONCAT(`dateOfProgram`, ' ', `startTime`), '%Y-%m-%d %H:%i:%s') from `program-slot`;

   
INSERT INTO `program-slot` (`id`, `program-name`, `dateOfProgram`, `startTime`,  `duration`, `presenter`,   `producer`) 
                SELECT ifnull(MAX(`id`) + 1,1), 'your choice','2018-08-27','19:00:00','00:30:00','wally','wally'
                FROM `program-slot`;
INSERT INTO `program-slot` (`id`, `program-name`, `dateOfProgram`, `startTime`,  `duration`, `presenter`,   `producer`) 
	SELECT ifnull(MAX(`id`) + 1,1), 'top 10','2018-09-27','19:00:00','00:40:00','wally','wally' FROM `program-slot`;

INSERT INTO `phoenix`.`program-slot` VALUES (1, 'charity', '2018-08-30', '19:00:00', '00:30:00','dilbert', 'dilbert');
INSERT INTO `phoenix`.`program-slot` VALUES (2, 'news', '2018-08-30', '19:30:00', '00:30:00', 'dilbert', 'wally');
INSERT INTO `phoenix`.`program-slot` VALUES (3, 'news', '2018-08-31', '19:30:00', '00:30:00', 'dilbert', 'wally');

select startTime, duration, startTime + duration from `program-slot`;
select startTime, duration, SEC_TO_TIME(TIME_TO_SEC(`startTime`) + TIME_TO_SEC(`duration`)) endTime
	from `program-slot` 
	where dateOfProgram = '2018-08-30' AND
    ((startTime >= '20:00:00'  AND startTime < '20:00:00') 
    OR (SEC_TO_TIME(TIME_TO_SEC(`startTime`) + TIME_TO_SEC(`duration`)) > '20:00:00' 
    AND SEC_TO_TIME(TIME_TO_SEC(`startTime`) + TIME_TO_SEC(`duration`)) <= '20:30:00'));
    
/* select * 
from `program-slot` 
where dateOfProgram = '2018-08-30' AND
    ((startTime >= '11:08:00'  AND startTime < '11:40:00') 
    OR (SEC_TO_TIME(TIME_TO_SEC(`startTime`) + TIME_TO_SEC(`duration`)) > '11:08:00' 
    AND SEC_TO_TIME(TIME_TO_SEC(`startTime`) + TIME_TO_SEC(`duration`)) <= '11:40:00' ));
    
select * 
from `program-slot` 
where dateOfProgram = '2018-09-30' AND
    ((startTime >= '12:02:00'  AND startTime < timediff(`startTime`, -`duration`)) 
    OR (timediff(`startTime`, -`duration`) > `startTime`
    AND timediff(`startTime`, -`duration`) <= timediff(`startTime`, -`duration`)));
    
select * 
from `program-slot` 
where dateOfProgram = '2018-08-30' AND
    ((startTime >= '19:04:00'  AND startTime < timediff('19:04:00', -'00:34:00')) 
    OR (timediff(`startTime`, -`duration`) > '19:04:00'
*/

# Create table
CREATE  TABLE IF NOT EXISTS `phoenix`.`program-slot` (
  `id` int(10) NOT NULL,
  `program-name` VARCHAR(45) NOT NULL ,
  `dateOfProgram` DATE NOT NULL ,
  `startTime` TIME NOT NULL ,
  `duration` TIME NULL ,
  `presenter` VARCHAR(45) NULL ,
  `producer` VARCHAR(45) NULL ,
  PRIMARY KEY (`id`) ,
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
CREATE INDEX `startTime` ON `phoenix`.`program-slot` (`startTime` ASC) ;
CREATE INDEX `name_program_slot` ON `phoenix`.`program-slot` (`program-name` ASC) ;
CREATE INDEX `dateOfProgram` ON `phoenix`.`program-slot` (`dateOfProgram` DESC) ;

drop table `program-slot`;

