-- MySQL dump 10.13  Distrib 8.0.13, for Linux (x86_64)
--
-- Host: localhost    Database: INVMGMT
-- ------------------------------------------------------
-- Server version	8.0.12

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `BUYER`
--

DROP TABLE IF EXISTS `BUYER`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `BUYER` (
  `Name` varchar(100) NOT NULL,
  `Gstin` varchar(15) NOT NULL,
  `Address` varchar(200) NOT NULL,
  `Contact_No` varchar(45) NOT NULL,
  `Outstanding_Amount` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`Name`),
  UNIQUE KEY `Gst_No_UNIQUE` (`Gstin`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ORDERS`
--

DROP TABLE IF EXISTS `ORDERS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `ORDERS` (
  `Buyer_Name` varchar(100) NOT NULL,
  `Part_Name` varchar(200) NOT NULL,
  PRIMARY KEY (`Buyer_Name`,`Part_Name`),
  KEY `orders_part_name_fk_idx` (`Part_Name`),
  KEY `orders_buyer_name_fk_idx` (`Buyer_Name`),
  CONSTRAINT `orders_buyer_name_fk` FOREIGN KEY (`Buyer_Name`) REFERENCES `BUYER` (`name`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `orders_part_name_fk` FOREIGN KEY (`Part_Name`) REFERENCES `PART` (`name`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ORDER_BILL`
--

DROP TABLE IF EXISTS `ORDER_BILL`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `ORDER_BILL` (
  `Bill_No` int(11) NOT NULL,
  `Buyer_Name` varchar(100) NOT NULL,
  `Order_Date` date NOT NULL,
  `Remarks` varchar(250) NOT NULL DEFAULT '',
  `Mode_Of_Payment` char(4) NOT NULL DEFAULT 'UNPD',
  PRIMARY KEY (`Bill_No`),
  KEY `order_bill_buyer_name_idx` (`Buyer_Name`),
  CONSTRAINT `order_bill_buyer_name` FOREIGN KEY (`Buyer_Name`) REFERENCES `BUYER` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `ORDER_BILL_UPDATE_OUTSTANDING_ON_PAYMENT` AFTER UPDATE ON `ORDER_BILL` FOR EACH ROW BEGIN
	if old.Mode_Of_Payment = 'UNPD' AND  new.Mode_Of_Payment <> 'UNPD' then
		update BUYER set Outstanding_Amount = Outstanding_Amount -ORDER_BILLING_ITEM_SUMMER(new.Bill_No);
	end if;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `ORDER_BILLING`
--

DROP TABLE IF EXISTS `ORDER_BILLING`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `ORDER_BILLING` (
  `Part_Name` varchar(200) NOT NULL,
  `Bill_No` int(11) NOT NULL,
  `Quantity` int(11) NOT NULL,
  `Price_Pc` decimal(10,2) NOT NULL,
  PRIMARY KEY (`Part_Name`,`Bill_No`),
  KEY `order_billing_bill_no_fk_idx` (`Bill_No`),
  CONSTRAINT `order_billing_bill_no_fk` FOREIGN KEY (`Bill_No`) REFERENCES `ORDER_BILL` (`bill_no`),
  CONSTRAINT `order_billing_part_name_fk` FOREIGN KEY (`Part_Name`) REFERENCES `PART` (`name`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `ORDER_BILLING_ON ORDER_DECREMENT_PART_QUANTITY` AFTER INSERT ON `ORDER_BILLING` FOR EACH ROW BEGIN
	update PART set quantity=quantity-new.quantity where new.Part_Name=Name;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`mfd`@`localhost`*/ /*!50003 TRIGGER `ORDER_BILLING_ON_ORDER_UPDATE_ORDERS_TABLE` AFTER INSERT ON `ORDER_BILLING` FOR EACH ROW BEGIN
 DECLARE b_name varchar(200);
 set b_name = (SELECT Buyer_Name FROM ORDER_BILL ob WHERE ob.Bill_No = new.Bill_No);
 
 if not exists(select * from ORDERS o where o.Part_Name=new.Part_Name AND o.Buyer_Name = b_name ) then  
    insert into ORDERS values(b_name, New.Part_Name);  
   end if;
 END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`mfd`@`localhost`*/ /*!50003 TRIGGER `ORDER_BILLING_UPDATE_OUTSTANDING` AFTER INSERT ON `ORDER_BILLING` FOR EACH ROW BEGIN  
 if (select Mode_Of_Payment from ORDER_BILL ob WHERE New.Bill_No=ob.Bill_No) = 'UNPD' then 
 update  BUYER b set Outstanding_Amount= (Outstanding_Amount + New.Price_Pc * New.Quantity)
 WHERE b.Name= (SELECT ob.Buyer_Name FROM ORDER_BILL ob WHERE ob.Bill_No=new.Bill_No); 
 end if;   END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `PART`
--

DROP TABLE IF EXISTS `PART`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `PART` (
  `Name` varchar(200) NOT NULL,
  `Quantity` int(11) NOT NULL DEFAULT '0',
  `Tax_Slab` decimal(5,2) NOT NULL,
  `Image` longblob,
  `Description` varchar(250) DEFAULT '',
  PRIMARY KEY (`Name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `SUPPLIER`
--

DROP TABLE IF EXISTS `SUPPLIER`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `SUPPLIER` (
  `Name` varchar(100) NOT NULL,
  `Gstin` varchar(15) DEFAULT NULL,
  `Address` varchar(200) NOT NULL,
  `Contact_No` varchar(10) NOT NULL,
  `Outstanding_Amount` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`Name`),
  UNIQUE KEY `Gst_No_UNIQUE` (`Gstin`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `SUPPLIES`
--

DROP TABLE IF EXISTS `SUPPLIES`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `SUPPLIES` (
  `Supplier_Name` varchar(100) NOT NULL,
  `Part_Name` varchar(200) NOT NULL,
  PRIMARY KEY (`Supplier_Name`,`Part_Name`),
  KEY `supplies_part_name_fk` (`Part_Name`),
  CONSTRAINT `supplies_part_name_fk` FOREIGN KEY (`Part_Name`) REFERENCES `PART` (`name`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `supplies_supplier_name_fk` FOREIGN KEY (`Supplier_Name`) REFERENCES `SUPPLIER` (`name`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `SUPPLY_BILL`
--

DROP TABLE IF EXISTS `SUPPLY_BILL`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `SUPPLY_BILL` (
  `Bill_No` int(11) NOT NULL,
  `Supplier_Name` varchar(100) NOT NULL,
  `Bill_Date` date NOT NULL,
  `Remarks` varchar(250) NOT NULL DEFAULT '',
  `Mode_Of_Payment` char(4) NOT NULL DEFAULT 'UNPD',
  PRIMARY KEY (`Bill_No`,`Supplier_Name`),
  KEY `supply_bill_supplier_name_fk` (`Supplier_Name`),
  CONSTRAINT `supply_bill_supplier_name_fk` FOREIGN KEY (`Supplier_Name`) REFERENCES `SUPPLIER` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `SUPPLY_BILL_AFTER_UPDATE` AFTER UPDATE ON `SUPPLY_BILL` FOR EACH ROW BEGIN
if old.Mode_Of_Payment = 'UNPD' AND  new.Mode_Of_Payment <> 'UNPD' then
		update SUPPLIER set Outstanding_Amount = Outstanding_Amount -SUPPLY_BILLING_ITEM_SUMMER(new.Bill_No, new.Supplier_Name);
	end if;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `SUPPLY_BILLING`
--

DROP TABLE IF EXISTS `SUPPLY_BILLING`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `SUPPLY_BILLING` (
  `Part_Name` varchar(200) NOT NULL,
  `Supplier_Name` varchar(100) NOT NULL,
  `Bill_No` int(11) NOT NULL,
  `Price_Pc` decimal(10,2) NOT NULL,
  `Quantity` int(11) NOT NULL,
  PRIMARY KEY (`Part_Name`,`Supplier_Name`,`Bill_No`),
  KEY `supply_billing_bill_fk` (`Bill_No`,`Supplier_Name`),
  CONSTRAINT `supply_billing_bill_fk` FOREIGN KEY (`Bill_No`, `Supplier_Name`) REFERENCES `SUPPLY_BILL` (`bill_no`, `supplier_name`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `supply_part_name_fk` FOREIGN KEY (`Part_Name`) REFERENCES `PART` (`name`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `SUPPLY_BILLING_ON_SUPPLY_PART` AFTER INSERT ON `SUPPLY_BILLING` FOR EACH ROW BEGIN
	update PART set quantity=quantity+new.quantity where new.Part_Name=Name;

END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`mfd`@`localhost`*/ /*!50003 TRIGGER `SUPPLY_BILLING_ON_SUPPLY_UPDATE_SUPPLIES_TABLE` AFTER INSERT ON `SUPPLY_BILLING` FOR EACH ROW if not exists(select * from SUPPLIES s where s.Part_Name=new.Part_Name AND s.Supplier_Name = new.Supplier_Name) then
    insert into SUPPLIES values(New.Supplier_Name, New.Part_Name);
    end if */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`mfd`@`localhost`*/ /*!50003 TRIGGER `SUPPLY_BILLING_UPDATE_OUTSTANDING` AFTER INSERT ON `SUPPLY_BILLING` FOR EACH ROW BEGIN   if (select Mode_Of_Payment from SUPPLY_BILL ob WHERE New.Supplier_Name=ob.Supplier_Name AND New.Bill_No=ob.Bill_No) = 'UNPD' then   update  SUPPLIER s set Outstanding_Amount= (Outstanding_Amount + New.Price_Pc * New.Quantity) WHERE New.Supplier_Name=s.Name;  end if;  END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Dumping routines for database 'INVENTORY_MANAGEMENT'
--
/*!50003 DROP FUNCTION IF EXISTS `ORDER_BILLING_ITEM_SUMMER` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `ORDER_BILLING_ITEM_SUMMER`(billno int) RETURNS int(11)
    DETERMINISTIC
BEGIN
	declare amt int;
    set amt=0;
    SELECT  sum( ob.Quantity * ob.Price_Pc) INTO amt from ORDER_BILLING ob where ob.Bill_no=billno ;
RETURN amt;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `SUPPLY_BILLING_ITEM_SUMMER` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `SUPPLY_BILLING_ITEM_SUMMER`(billno int, sup varchar(200)) RETURNS int(11)
    DETERMINISTIC
BEGIN
	declare amt int;
	set amt=0;
SELECT 
    SUM(ob.Quantity * ob.Price_Pc)
INTO amt FROM
    SUPPLY_BILLING ob
WHERE
    ob.Bill_no = billno
        AND ob.Supplier_Name = sup;
RETURN amt;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-11-11 18:15:05
