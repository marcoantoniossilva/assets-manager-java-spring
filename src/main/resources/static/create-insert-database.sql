-- MySQL dump 10.16  Distrib 10.1.48-MariaDB, for debian-linux-gnu (x86_64)
--
-- Host: fdb30.atspace.me    Database: 3715574_assetsmanager
-- ------------------------------------------------------
-- Server version	5.7.20-log
DROP DATABASE IF EXISTS `assets-manager`;
CREATE DATABASE `assets-manager`;
USE `assets-manager`;

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `companies`
--

DROP TABLE IF EXISTS `companies`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `companies` (
  `id` smallint(5) unsigned NOT NULL AUTO_INCREMENT,
  `corporate_name` varchar(120) COLLATE utf8_unicode_ci DEFAULT NULL,
  `fantasy_name` varchar(120) COLLATE utf8_unicode_ci NOT NULL,
  `cnpj` varchar(18) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `companies`
--

LOCK TABLES `companies` WRITE;
/*!40000 ALTER TABLE `companies` DISABLE KEYS */;
INSERT INTO `companies` VALUES (17,'LINDA JIBOIA INDUSTRIA E COMERCIO DE CONFECCOES LTDA','LINDA JIBOIA','07.616.252/0001-38'),(18,'A Z S GUIMARAES - EIRELI','A Z S','24.661.396/0001-13'),(19,'SUPER JIBOIA INDUSTRIA E COMERCIO DE CONFECCOES LTDA','SUPER JIBOIA','06.312.191/0001-40'),(20,'JAILSON GUIMARAES SANTANA','ADVIRE','19.634.187/0001-40'),(21,'TESTE','TESTE','00.000.000/0000-00');
/*!40000 ALTER TABLE `companies` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `equipments`
--

DROP TABLE IF EXISTS `equipments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `equipments` (
  `id` smallint(5) unsigned NOT NULL AUTO_INCREMENT,
  `company_id` smallint(5) unsigned DEFAULT NULL,
  `sector_id` smallint(5) unsigned DEFAULT NULL,
  `sector_id_id` smallint(5) unsigned DEFAULT NULL,
  `user_id` smallint(5) unsigned DEFAULT NULL,
  `acquisition_value` decimal(9,2) DEFAULT NULL,
  `description` varchar(120) COLLATE utf8_unicode_ci DEFAULT NULL,
  `acquisition_date` date DEFAULT NULL,
  `nfe_path` varchar(120) COLLATE utf8_unicode_ci DEFAULT NULL,
  `status` enum('Ativo','Reserva','Inativo') COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `sector_id_id` (`sector_id_id`),
  KEY `company_id` (`company_id`),
  KEY `sector_id` (`sector_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `equipments_ibfk_1` FOREIGN KEY (`sector_id_id`) REFERENCES `types` (`id`) ON DELETE CASCADE,
  CONSTRAINT `equipments_ibfk_2` FOREIGN KEY (`company_id`) REFERENCES `companies` (`id`) ON DELETE CASCADE,
  CONSTRAINT `equipments_ibfk_3` FOREIGN KEY (`sector_id`) REFERENCES `sector` (`id`) ON DELETE CASCADE,
  CONSTRAINT `equipments_ibfk_4` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `equipments`
--

LOCK TABLES `equipments` WRITE;
/*!40000 ALTER TABLE `equipments` DISABLE KEYS */;
INSERT INTO `equipments` VALUES (4,19,10,11,10,384982.00,'MÁQUINA DE CORTE - LETRAC','2017-04-15','../../nfes/52-MAQUINA DE CORTE LETRAC - 11779.pdf','Ativo'),(5,19,10,15,10,129500.00,'ENFESTADEIRA - AUDAVES','2019-01-31','../../nfes/94-ENFESTADEIRA AUDACES - 3036.pdf','Ativo'),(6,17,14,17,10,47800.00,'GOL BCU1C38','2020-10-06','../nfes/41-GOL 02 - BCU1C38.pdf','Ativo'),(7,17,14,17,10,47800.00,'GOL RZU6G49','2021-10-06','../nfes/76-GOL 01 - RZU6G49.pdf','Ativo'),(8,21,19,7,10,1.00,'OVERLOCK ELETRÔNICA - SIRIBA','2020-09-01',NULL,'Ativo'),(9,21,19,7,10,1.00,'OVERLOCK ELETRÔNICA - SIRIBA','2021-09-01',NULL,'Ativo'),(10,21,19,7,10,1.00,'INTERLOCK ELETRÔNICA - SIRIBA','2021-09-01',NULL,'Ativo'),(11,21,19,7,10,1.00,'OVERLOCK ELETRÔNICA - SIRIBA','2021-09-01',NULL,'Ativo'),(12,21,19,7,10,1.00,'TRAVETE COMUM - SIRIBA','2021-09-01',NULL,'Ativo'),(13,21,19,7,10,1.00,'GALONEIRA COMUM - SIRIBA','2021-09-01',NULL,'Ativo'),(14,21,19,7,10,1.00,'OVERLOCK ELETRÔNICA - SIRIBA','2021-09-01',NULL,'Ativo'),(15,21,19,7,10,1.00,'OVERLOCK COMUM - SIRIBA','2021-09-01',NULL,'Ativo'),(16,21,19,7,10,1.00,'GALONEIRA ELETRÔNICA - SIRIBA','2021-09-01',NULL,'Ativo'),(17,21,19,7,10,1.00,'GALONEIRA COMUM - SIRIBA','2021-09-01',NULL,'Ativo'),(18,21,19,7,10,1.00,'GALONEIRA COMUM - SIRIBA','2021-09-01',NULL,'Ativo'),(19,21,19,7,10,1.00,'GALONEIRA COMUM - SIRIBA','2021-09-01',NULL,'Ativo'),(20,21,18,7,10,1.00,'OVERLOCK COMUM - SIRIBA','2021-09-01',NULL,'Reserva'),(21,21,18,7,10,1.00,'BOTONEIRA PINGENTE - SIRIBA','2021-09-01',NULL,'Ativo'),(22,21,18,7,10,1.00,'TRAVETE ELETRÔNICA - SIRIBA','2021-09-01',NULL,'Reserva'),(23,21,18,7,10,1.00,'PRESPONTADEIRA COMUM - SIRIBA','2021-09-01',NULL,'Ativo'),(24,21,18,7,10,1.00,'ZEROMACK COMUM - SIRIBA','2021-09-01',NULL,'Reserva'),(25,21,18,7,10,1.00,'ZEROMACK COMUM - NISSINMIOJO','2021-09-01',NULL,'Ativo'),(26,21,18,7,10,1.00,'OVERLOCK COMUM - SIRIBA','2021-09-01',NULL,'Ativo'),(27,21,18,7,10,1.00,'BATEDOR DE TAG ELETRÔNICA - CENSI','2021-09-01',NULL,'Ativo'),(28,21,18,7,10,1.00,'RETA ELETRÔNICA - SUNSPECIAL','2021-09-01',NULL,'Ativo'),(29,21,18,7,10,1.00,'TRAVETE ELETRÔNICA - SUNSTAR','2021-09-01',NULL,'Ativo'),(30,21,18,7,10,1.00,'PRESPONTADEIRA COMUM - SIRIBA','2021-09-01',NULL,'Ativo'),(31,21,18,7,10,1.00,'GALONEIRA ELETRÔNICA - SIRIBA','2021-09-01',NULL,'Ativo'),(32,21,18,7,10,1.00,'GALONEIRA COMUM - SIRIBA','2021-09-01',NULL,'Ativo'),(33,21,18,7,10,1.00,'GALONEIRA COMUM - SIRIBA','2021-09-01',NULL,'Ativo'),(34,21,18,7,10,1.00,'GALONEIRA COMUM - SIRIBA','2021-09-01',NULL,'Ativo'),(35,21,18,7,10,1.00,'GALONEIRA ELETRÔNICA - SIRIBA','2021-09-01',NULL,'Ativo'),(36,21,18,7,10,1.00,'ZEROMACK COMUM - SIRIBA','2021-09-01',NULL,'Ativo'),(37,21,18,7,10,1.00,'OVERLOCK ELETRÔNICA - SIRIBA','2021-09-01',NULL,'Ativo'),(38,21,18,7,10,1.00,'GALONEIRA COMUM - SIRIBA','2021-09-01',NULL,'Ativo'),(39,21,18,7,10,1.00,'ZEROMACK ELETRÔNICA - LANMAX','2021-09-01',NULL,'Ativo'),(40,21,18,7,10,1.00,'OVERLOCK COMUM - SIRIBA','2021-09-01',NULL,'Ativo'),(41,21,18,7,10,1.00,'OVERLOCK ELETRÔNICA - SIRIBA','2021-09-01',NULL,'Ativo'),(42,21,18,7,10,1.00,'OVERLOCK ELETRÔNICA - SIRIBA','2021-09-01',NULL,'Ativo'),(43,21,18,7,10,1.00,'OVERLOCK ELETRÔNICA - SIRIBA','2021-09-01',NULL,'Ativo'),(44,21,18,7,10,1.00,'OVERLOCK COMUM - SIRIBA','2021-09-01',NULL,'Ativo'),(45,21,18,7,10,1.00,'OVERLOCK ELETRÔNICA - SIRIBA','2021-09-01',NULL,'Ativo'),(46,21,18,7,10,1.00,'OVERLOCK ELETRÔNICA - JACK','2021-09-01',NULL,'Ativo'),(47,21,18,7,10,1.00,'GALONEIRA COMUM - SIRIBA','2021-09-01',NULL,'Ativo'),(48,21,18,7,10,1.00,'GALONEIRA COMUM - SIRIBA','2021-09-01',NULL,'Ativo'),(49,21,18,7,10,1.00,'ZEROMACK COMUM - NISSINMIOJO','2021-09-01',NULL,'Ativo'),(50,21,18,7,10,1.00,'ZEROMACK COMUM - NISSINMIOJO','2021-09-01',NULL,'Reserva'),(51,21,18,7,10,1.00,'GALONEIRA COMUM - SIRIBA','2021-09-01',NULL,'Ativo'),(52,21,18,7,10,1.00,'TRAVETE COMUM - SIRIBA','2021-09-01',NULL,'Ativo'),(53,21,18,7,10,1.00,'ZEROMACK COMUM - NISSINMIOJO','2021-09-01',NULL,'Ativo'),(54,21,18,7,10,1.00,'OVERLOCK COMUM - SIRIBA','2021-09-01',NULL,'Ativo'),(55,21,18,7,10,1.00,'OVERLOCK COMUM - SIRIBA','2021-09-01',NULL,'Ativo'),(56,21,18,7,10,1.00,'OVERLOCK COMUM - SIRIBA','2021-09-01',NULL,'Ativo'),(57,21,18,7,10,1.00,'OVERLOCK ELETRÔNICA - SIRIBA','2021-09-01',NULL,'Ativo'),(58,21,18,7,10,1.00,'OVERLOCK ELETRÔNICA - SIRIBA','2021-09-01',NULL,'Ativo');
/*!40000 ALTER TABLE `equipments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sector`
--

DROP TABLE IF EXISTS `sector`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sector` (
  `id` smallint(5) unsigned NOT NULL AUTO_INCREMENT,
  `description` varchar(120) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sector`
--

LOCK TABLES `sector` WRITE;
/*!40000 ALTER TABLE `sector` DISABLE KEYS */;
INSERT INTO `sector` VALUES (2,'PRODUÇÃO SUPER JIBOIA'),(10,'CORTE'),(11,'PRODUÇÃO LINDA JIBOIA'),(12,'PRODUÇÃO ADVIRE'),(13,'PRODUÇÃO A Z S'),(14,'VENDAS'),(15,'ADMINISTRATIVO'),(16,'ALMOXARIFADO'),(18,'PRAIA'),(19,'FITNESS'),(20,'PIJAMA'),(21,'CONJUNTOS'),(22,'SUTIÃ'),(23,'FANTASIA'),(24,'CALCINHA 120'),(25,'CALCINHA 119'),(26,'CALCINHA 117'),(27,'CALCINHA 106'),(28,'TREINAMENTO');
/*!40000 ALTER TABLE `sector` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `types`
--

DROP TABLE IF EXISTS `types`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `types` (
  `id` smallint(5) unsigned NOT NULL AUTO_INCREMENT,
  `description` varchar(120) COLLATE utf8_unicode_ci DEFAULT NULL,
  `depreciation` tinyint(3) unsigned DEFAULT NULL,
  `depreciation_term` tinyint(3) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `types`
--

LOCK TABLES `types` WRITE;
/*!40000 ALTER TABLE `types` DISABLE KEYS */;
INSERT INTO `types` VALUES (7,'MÁQUINA DE COSTURA',10,10),(11,'MÁQUINA DE CORTE',10,10),(12,'IMPRESSORA',NULL,NULL),(15,'ENFESTADEIRA',10,10),(16,'CAIXA PLASTICA',10,10),(17,'VEÍCULO',10,10);
/*!40000 ALTER TABLE `types` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` smallint(5) unsigned NOT NULL AUTO_INCREMENT,
  `password` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `name` varchar(120) COLLATE utf8_unicode_ci DEFAULT NULL,
  `login` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `email` varchar(120) COLLATE utf8_unicode_ci NOT NULL,
  `last_access` datetime DEFAULT NULL,
  `register_in` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'e10adc3949ba59abbe56e057f20f883e','Marco Antonio Santos Silva','marco','marco@gmail.com','2022-03-29 15:26:57','2020-12-23 22:57:56'),(10,'44359f1cdac23622360704dd79086a25','Edvar Neves','Edvar ','lindaJIBOIAcontabil@gmail.com','2021-09-24 11:56:00','2020-12-24 08:22:46'),(11,'f657674a5b96c7463917e31a68559fa4','Matheus','matheus','matheus@gmail.com','2022-02-14 09:22:17','2021-12-03 14:39:28');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database '3715574_assetsmanager'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-03-29 18:28:41
