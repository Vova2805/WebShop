-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0;
SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0;
SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE = 'TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema webshopdb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `webshopdb`;
-- -----------------------------------------------------
-- Schema webshopdb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `webshopdb`
  DEFAULT CHARACTER SET utf8;
USE `webshopdb`;

-- -----------------------------------------------------
-- Table `webshopdb`.`address`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `webshopdb`.`address` (
  `addressId`      INT  NOT NULL AUTO_INCREMENT
  COMMENT 'Contains all address (distinct) within system',
  `city`           TEXT NOT NULL,
  `street`         TEXT NOT NULL,
  `country`        TEXT NOT NULL,
  `region`         TEXT NOT NULL,
  `buildingNumber` INT  NULL,
  PRIMARY KEY (`addressId`)
)
  ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `webshopdb`.`role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `webshopdb`.`role` (
  `roleId` INT         NOT NULL AUTO_INCREMENT,
  `role`   VARCHAR(45) NOT NULL,
  PRIMARY KEY (`roleId`),
  UNIQUE INDEX `role_UNIQUE` (`role` ASC)
)
  ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `webshopdb`.`customer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `webshopdb`.`customer` (
  `customerId`   INT         NOT NULL AUTO_INCREMENT,
  `addressId`    INT         NULL,
  `customerName` TEXT        NOT NULL,
  `email`        TEXT        NULL,
  `password`     TEXT        NOT NULL,
  `enabled`      BIT(1)      NOT NULL DEFAULT b'1',
  `phone`        VARCHAR(45) NULL,
  `roleId`       INT         NOT NULL,
  PRIMARY KEY (`customerId`),
  INDEX `fk_customer_address1_idx` (`addressId` ASC),
  INDEX `fk_customer_role1_idx` (`roleId` ASC),
  CONSTRAINT `fk_customer_address1`
  FOREIGN KEY (`addressId`)
  REFERENCES `webshopdb`.`address` (`addressId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_customer_role1`
  FOREIGN KEY (`roleId`)
  REFERENCES `webshopdb`.`role` (`roleId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)
  ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `webshopdb`.`category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `webshopdb`.`category` (
  `categoryId`    INT  NOT NULL AUTO_INCREMENT
  COMMENT 'General category\nExample: \n	*Laptop, PC and tablet\n	*Apple',
  `categoryTitle` TEXT NOT NULL,
  PRIMARY KEY (`categoryId`)
)
  ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `webshopdb`.`subcategory`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `webshopdb`.`subcategory` (
  `subcategoryId`    INT  NOT NULL AUTO_INCREMENT
  COMMENT 'Subcategory\nExample: \n	*Laptop\n	*Tablet\n	*PC\n\n	*IPhone\n	*IPad\n	*IMac',
  `categoryId`       INT  NOT NULL,
  `subcategoryTitle` TEXT NOT NULL,
  PRIMARY KEY (`subcategoryId`),
  INDEX `fk_subcategory_category1_idx` (`categoryId` ASC),
  CONSTRAINT `fk_subcategory_category1`
  FOREIGN KEY (`categoryId`)
  REFERENCES `webshopdb`.`category` (`categoryId`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
  ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `webshopdb`.`subcategoryGroup`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `webshopdb`.`subcategoryGroup` (
  `subcategoryGroupId`    INT  NOT NULL AUTO_INCREMENT
  COMMENT 'Subcategory for subcategory. Funny.\n\nFor example - for subcategory Laptop:\n	*All laptops\n	*Accumulators for laptops\n	*External HDD',
  `subcategoryId`         INT  NOT NULL,
  `subcategoryGroupTitle` TEXT NOT NULL,
  PRIMARY KEY (`subcategoryGroupId`),
  INDEX `fk_subcategoryGroup_subcategory1_idx` (`subcategoryId` ASC),
  CONSTRAINT `fk_subcategoryGroup_subcategory1`
  FOREIGN KEY (`subcategoryId`)
  REFERENCES `webshopdb`.`subcategory` (`subcategoryId`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
  ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `webshopdb`.`product`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `webshopdb`.`product` (
  `productId`          INT         NOT NULL AUTO_INCREMENT,
  `subcategoryGroupId` INT         NOT NULL,
  `productTitle`       TEXT        NOT NULL,
  `generalDescr`       TEXT        NOT NULL,
  `availableAmount`    INT         NOT NULL DEFAULT 0,
  `isEnabled`          BIT(1)      NOT NULL DEFAULT b'1',
  `warrantyMonths`     INT         NOT NULL,
  `price`              DOUBLE      NOT NULL,
  `addedDate`          DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `showDiscount`       BIT(1)      NOT NULL DEFAULT b'0',
  `discount`           DOUBLE      NOT NULL DEFAULT 0,
  `rating`             DOUBLE      NOT NULL DEFAULT 0,
  `videoYouTubeV`      VARCHAR(50) NULL,
  PRIMARY KEY (`productId`),
  INDEX `fk_product_subcategoryGroup1_idx` (`subcategoryGroupId` ASC),
  CONSTRAINT `fk_product_subcategoryGroup1`
  FOREIGN KEY (`subcategoryGroupId`)
  REFERENCES `webshopdb`.`subcategoryGroup` (`subcategoryGroupId`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
  ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `webshopdb`.`wish`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `webshopdb`.`wish` (
  `wishId`     INT NOT NULL AUTO_INCREMENT,
  `customerId` INT NOT NULL,
  `productId`  INT NOT NULL,
  PRIMARY KEY (`wishId`),
  INDEX `fk_wish_customer1_idx` (`customerId` ASC),
  INDEX `fk_wish_product2_idx` (`productId` ASC),
  CONSTRAINT `fk_wish_customer1`
  FOREIGN KEY (`customerId`)
  REFERENCES `webshopdb`.`customer` (`customerId`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_wish_concreteProduct2`
  FOREIGN KEY (`productId`)
  REFERENCES `webshopdb`.`product` (`productId`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
  ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `webshopdb`.`productOrder`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `webshopdb`.`productOrder` (
  `productOrderId`     INT      NOT NULL AUTO_INCREMENT,
  `customerId`         INT      NOT NULL,
  `deliveryAddress`    INT      NULL,
  `productOrderDate`   DATETIME NOT NULL,
  `productOrderStatus` TEXT     NOT NULL,
  `paid`               BIT(1)   NOT NULL DEFAULT b'0'
  COMMENT 'Yes or no',
  `total`              DOUBLE   NOT NULL,
  `isReleased`         BIT(1)   NOT NULL DEFAULT b'0',
  PRIMARY KEY (`productOrderId`),
  INDEX `fk_productOrder_customer1_idx` (`customerId` ASC),
  INDEX `fk_productOrder_address1_idx` (`deliveryAddress` ASC),
  CONSTRAINT `fk_productOrder_customer1`
  FOREIGN KEY (`customerId`)
  REFERENCES `webshopdb`.`customer` (`customerId`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_productOrder_address1`
  FOREIGN KEY (`deliveryAddress`)
  REFERENCES `webshopdb`.`address` (`addressId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)
  ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `webshopdb`.`comment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `webshopdb`.`comment` (
  `commentId`     INT      NOT NULL AUTO_INCREMENT,
  `productId`     INT      NOT NULL,
  `customerId`    INT      NOT NULL,
  `commentDate`   DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `starsValue`    INT      NOT NULL DEFAULT 0,
  `commentBody`   TEXT     NOT NULL,
  `advantages`    TEXT     NULL,
  `disadvantages` TEXT     NULL,
  `useful`        INT      NULL     DEFAULT 0,
  `useless`       INT      NULL     DEFAULT 0,
  PRIMARY KEY (`commentId`),
  INDEX `fk_comment_customer1_idx` (`customerId` ASC),
  INDEX `fk_comment_product1_idx` (`productId` ASC),
  CONSTRAINT `fk_comment_customer1`
  FOREIGN KEY (`customerId`)
  REFERENCES `webshopdb`.`customer` (`customerId`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_comment_concreteProduct1`
  FOREIGN KEY (`productId`)
  REFERENCES `webshopdb`.`product` (`productId`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
  ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `webshopdb`.`viewedProduct`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `webshopdb`.`viewedProduct` (
  `viewedId`   INT NOT NULL AUTO_INCREMENT,
  `customerId` INT NOT NULL,
  `productId`  INT NOT NULL,
  PRIMARY KEY (`viewedId`),
  INDEX `fk_viewedProduct_customer1_idx` (`customerId` ASC),
  INDEX `fk_viewedProduct_product1_idx` (`productId` ASC),
  CONSTRAINT `fk_viewedProduct_customer1`
  FOREIGN KEY (`customerId`)
  REFERENCES `webshopdb`.`customer` (`customerId`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_viewedProduct_concreteProduct1`
  FOREIGN KEY (`productId`)
  REFERENCES `webshopdb`.`product` (`productId`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
  ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `webshopdb`.`comparison`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `webshopdb`.`comparison` (
  `comparisonId` INT NOT NULL AUTO_INCREMENT,
  `customerId`   INT NOT NULL,
  `productId`    INT NOT NULL,
  PRIMARY KEY (`comparisonId`),
  INDEX `fk_comparison_customer_idx` (`customerId` ASC),
  INDEX `fk_comparison_product1_idx` (`productId` ASC),
  CONSTRAINT `fk_comparison_customer`
  FOREIGN KEY (`customerId`)
  REFERENCES `webshopdb`.`customer` (`customerId`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_comparison_concreteProduct1`
  FOREIGN KEY (`productId`)
  REFERENCES `webshopdb`.`product` (`productId`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
  ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `webshopdb`.`subcategoryGroup`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `webshopdb`.`subcategoryGroup` (
  `subcategoryGroupId`    INT  NOT NULL AUTO_INCREMENT
  COMMENT 'Subcategory for subcategory. Funny.\n\nFor example - for subcategory Laptop:\n	*All laptops\n	*Accumulators for laptops\n	*External HDD',
  `subcategoryId`         INT  NOT NULL,
  `subcategoryGroupTitle` TEXT NOT NULL,
  PRIMARY KEY (`subcategoryGroupId`),
  INDEX `fk_subcategoryGroup_subcategory1_idx` (`subcategoryId` ASC),
  CONSTRAINT `fk_subcategoryGroup_subcategory1`
  FOREIGN KEY (`subcategoryId`)
  REFERENCES `webshopdb`.`subcategory` (`subcategoryId`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
  ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `webshopdb`.`cart`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `webshopdb`.`cart` (
  `cartProductId` INT NOT NULL AUTO_INCREMENT,
  `ProductId`     INT NOT NULL,
  `customerId`    INT NOT NULL,
  `ProductAmount` INT NOT NULL,
  PRIMARY KEY (`cartProductId`),
  INDEX `fk_cart_has_product_product1_idx` (`ProductId` ASC),
  INDEX `fk_cart_customer2_idx` (`customerId` ASC),
  CONSTRAINT `fk_cart_has_concreteProduct_concreteProduct1`
  FOREIGN KEY (`ProductId`)
  REFERENCES `webshopdb`.`product` (`productId`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_cart_customer2`
  FOREIGN KEY (`customerId`)
  REFERENCES `webshopdb`.`customer` (`customerId`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
  ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `webshopdb`.`propertyClass`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `webshopdb`.`propertyClass` (
  `propertyClassId`    INT         NOT NULL AUTO_INCREMENT
  COMMENT 'For example:\n	*Manufacturer',
  `propertyClassTitle` VARCHAR(60) NOT NULL,
  `propertyClassDescr` TEXT        NULL,
  PRIMARY KEY (`propertyClassId`)
)
  ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `webshopdb`.`property`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `webshopdb`.`property` (
  `propertyId`      INT  NOT NULL
  COMMENT 'For example:\n	*Acer\n	*HP\n	*LG',
  `propertyClassId` INT  NOT NULL,
  `propertyValue`   TEXT NOT NULL,
  PRIMARY KEY (`propertyId`),
  INDEX `fk_property_propertyClass1_idx` (`propertyClassId` ASC),
  CONSTRAINT `fk_property_propertyClass1`
  FOREIGN KEY (`propertyClassId`)
  REFERENCES `webshopdb`.`propertyClass` (`propertyClassId`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
  ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `webshopdb`.`descriptionPart`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `webshopdb`.`descriptionPart` (
  `descriptionPartId`     INT    NOT NULL AUTO_INCREMENT
  COMMENT 'Describes product by parts with headers and images (if needed)',
  `descriptionPartHeader` TEXT   NOT NULL,
  `descriptionPartBody`   TEXT   NOT NULL
  COMMENT 'This table can describe product by parts with heads and illustrations',
  `hasImg`                BIT(1) NOT NULL,
  `product_productId`     INT    NOT NULL,
  PRIMARY KEY (`descriptionPartId`),
  INDEX `fk_descriptionPart_product2_idx` (`product_productId` ASC),
  CONSTRAINT `fk_descriptionPart_product2`
  FOREIGN KEY (`product_productId`)
  REFERENCES `webshopdb`.`product` (`productId`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
  ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `webshopdb`.`answer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `webshopdb`.`answer` (
  `answerId`   INT      NOT NULL AUTO_INCREMENT,
  `commentId`  INT      NOT NULL,
  `answerBody` TEXT     NOT NULL,
  `customerId` INT      NOT NULL,
  `answerDate` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`answerId`),
  INDEX `fk_answer_comment1_idx` (`commentId` ASC),
  INDEX `fk_answer_customer1_idx` (`customerId` ASC),
  CONSTRAINT `fk_answer_comment1`
  FOREIGN KEY (`commentId`)
  REFERENCES `webshopdb`.`comment` (`commentId`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_answer_customer1`
  FOREIGN KEY (`customerId`)
  REFERENCES `webshopdb`.`customer` (`customerId`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
  ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `webshopdb`.`report`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `webshopdb`.`report` (
  `reportId`   INT      NOT NULL AUTO_INCREMENT,
  `commentId`  INT      NOT NULL,
  `customerId` INT      NOT NULL,
  `reportBody` TEXT     NOT NULL,
  `reportDate` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`reportId`),
  INDEX `fk_report_comment1_idx` (`commentId` ASC),
  INDEX `fk_report_customer1_idx` (`customerId` ASC),
  CONSTRAINT `fk_report_comment1`
  FOREIGN KEY (`commentId`)
  REFERENCES `webshopdb`.`comment` (`commentId`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_report_customer1`
  FOREIGN KEY (`customerId`)
  REFERENCES `webshopdb`.`customer` (`customerId`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
  ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `webshopdb`.`adminLog`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `webshopdb`.`adminLog` (
  `adminLogId` INT      NOT NULL AUTO_INCREMENT
  COMMENT 'There will be one HEAD admin and SUB rest. HEAD has ability to control his SUBs. If appropriate FLAG was ON then this table will be being filled.',
  `customerId` INT      NOT NULL,
  `date`       DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `body`       TEXT     NOT NULL,
  PRIMARY KEY (`adminLogId`),
  INDEX `fk_adminLog_customer1_idx` (`customerId` ASC),
  CONSTRAINT `fk_adminLog_customer1`
  FOREIGN KEY (`customerId`)
  REFERENCES `webshopdb`.`customer` (`customerId`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
  ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `webshopdb`.`watchedPrice`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `webshopdb`.`watchedPrice` (
  `watchedPriceId` INT NOT NULL AUTO_INCREMENT
  COMMENT 'This table is created for ability to watch how product price is changing. If user would like to get this info in the future - system will notify him through email (if price has changed)',
  `customerId`     INT NOT NULL,
  `productId`      INT NOT NULL,
  PRIMARY KEY (`watchedPriceId`),
  INDEX `fk_watchedPrice_customer1_idx` (`customerId` ASC),
  INDEX `fk_watchedPrice_product1_idx` (`productId` ASC),
  CONSTRAINT `fk_watchedPrice_customer1`
  FOREIGN KEY (`customerId`)
  REFERENCES `webshopdb`.`customer` (`customerId`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_watchedPrice_concreteProduct1`
  FOREIGN KEY (`productId`)
  REFERENCES `webshopdb`.`product` (`productId`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
  ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `webshopdb`.`image`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `webshopdb`.`image` (
  `imageId`   INT NOT NULL AUTO_INCREMENT
  COMMENT 'Image gallery for each product. Contains image name and reference to product.',
  `productId` INT NOT NULL,
  PRIMARY KEY (`imageId`),
  INDEX `fk_image_product1_idx` (`productId` ASC),
  CONSTRAINT `fk_image_concreteProduct1`
  FOREIGN KEY (`productId`)
  REFERENCES `webshopdb`.`product` (`productId`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
  ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `webshopdb`.`shop`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `webshopdb`.`shop` (
  `shopId`      INT         NOT NULL,
  `addressId`   INT         NOT NULL,
  `phone`       VARCHAR(45) NULL,
  `email`       TEXT        NOT NULL,
  `description` TEXT        NULL,
  `position`    TEXT        NULL,
  PRIMARY KEY (`shopId`),
  INDEX `fk_shop_address1_idx` (`addressId` ASC),
  CONSTRAINT `fk_shop_address1`
  FOREIGN KEY (`addressId`)
  REFERENCES `webshopdb`.`address` (`addressId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)
  ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `webshopdb`.`advertisement`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `webshopdb`.`advertisement` (
  `advertisementId` INT         NOT NULL AUTO_INCREMENT,
  `title`           VARCHAR(60) NOT NULL,
  `body`            TEXT        NOT NULL,
  `buttonHref`      TEXT        NOT NULL,
  PRIMARY KEY (`advertisementId`)
)
  ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `webshopdb`.`productOrderProduct`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `webshopdb`.`productOrderProduct` (
  `productOrderProductId` INT NOT NULL AUTO_INCREMENT,
  `productOrderId`        INT NOT NULL,
  `productId`             INT NOT NULL,
  `quantity`              INT NOT NULL,
  INDEX `fk_productOrderProduct_productOrder1_idx` (`productOrderId` ASC),
  INDEX `fk_productOrderProduct_product1_idx` (`productId` ASC),
  PRIMARY KEY (`productOrderProductId`),
  CONSTRAINT `fk_productOrderProduct_productOrder1`
  FOREIGN KEY (`productOrderId`)
  REFERENCES `webshopdb`.`productOrder` (`productOrderId`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_productOrderProduct_product1`
  FOREIGN KEY (`productId`)
  REFERENCES `webshopdb`.`product` (`productId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)
  ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `webshopdb`.`propertyProduct`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `webshopdb`.`propertyProduct` (
  `property_propertyId` INT NOT NULL,
  `product_productId`   INT NOT NULL,
  PRIMARY KEY (`property_propertyId`, `product_productId`),
  INDEX `fk_property_has_product_product3_idx` (`product_productId` ASC),
  INDEX `fk_property_has_product_property3_idx` (`property_propertyId` ASC),
  CONSTRAINT `fk_property_has_concreteProduct_property3`
  FOREIGN KEY (`property_propertyId`)
  REFERENCES `webshopdb`.`property` (`propertyId`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_property_has_concreteProduct_concreteProduct3`
  FOREIGN KEY (`product_productId`)
  REFERENCES `webshopdb`.`product` (`productId`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
  ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `webshopdb`.`relatedProduct`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `webshopdb`.`relatedProduct` (
  `relatedProductId` INT NOT NULL AUTO_INCREMENT,
  `productOwnerd`    INT NOT NULL,
  `productRelatedId` INT NOT NULL,
  PRIMARY KEY (`relatedProductId`),
  INDEX `fk_relatedProduct_product1_idx` (`productOwnerd` ASC),
  INDEX `fk_relatedProduct_product2_idx` (`productRelatedId` ASC),
  CONSTRAINT `fk_relatedProduct_product1`
  FOREIGN KEY (`productOwnerd`)
  REFERENCES `webshopdb`.`product` (`productId`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_relatedProduct_product2`
  FOREIGN KEY (`productRelatedId`)
  REFERENCES `webshopdb`.`product` (`productId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)
  ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `webshopdb`.`subscribedUser`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `webshopdb`.`subscribedUser` (
  `subscribedUserId` INT  NOT NULL AUTO_INCREMENT,
  `email`            TEXT NOT NULL,
  PRIMARY KEY (`subscribedUserId`)
)
  ENGINE = InnoDB;


SET SQL_MODE = @OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS;
