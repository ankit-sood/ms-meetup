DROP TABLE IF EXISTS category;
DROP TABLE IF EXISTS ms_group;

CREATE TABLE `category` (
  `category_id` INT NOT NULL,
  `category_name` VARCHAR(45) NULL,
  PRIMARY KEY (`category_id`));
  
CREATE TABLE `ms_group` (
  `group_id` INT NOT NULL,
  `group_name` VARCHAR(45) NULL,
  `group_desc` VARCHAR(45) NULL,
  `created_by` VARCHAR(45) NULL,
  PRIMARY KEY (`group_id`));