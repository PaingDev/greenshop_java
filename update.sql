CREATE TABLE `shop`.`slideimage` (
  `slideImageId` INT NOT NULL,
  `img` VARCHAR(45) NULL,
  PRIMARY KEY (`slideImageId`));
  
ALTER TABLE `shop`.`slideimage` 
CHANGE COLUMN `slideImageId` `slideImageId` INT(11) NOT NULL AUTO_INCREMENT ;


ALTER TABLE `shop`.`category` 
ADD COLUMN `img` VARCHAR(45) NULL AFTER `specialId`;



  
