/*
SQLyog Community v13.3.0 (64 bit)
MySQL - 10.4.32-MariaDB : Database - seminarski
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`seminarski` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;

USE `seminarski`;

/*Table structure for table `lokacija` */

DROP TABLE IF EXISTS `lokacija`;

CREATE TABLE `lokacija` (
  `idLokacija` int(11) NOT NULL AUTO_INCREMENT,
  `naziv` varchar(100) NOT NULL,
  `tipTerena` enum('PLANINSKI','RAVNICARSKI','BRDOVITI','SUMSKI') NOT NULL,
  `pristupacnost` enum('LAKA','UMERENA','TESKA') NOT NULL,
  PRIMARY KEY (`idLokacija`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `lokacija` */

insert  into `lokacija`(`idLokacija`,`naziv`,`tipTerena`,`pristupacnost`) values 
(1,'Planina Tara','PLANINSKI','TESKA'),
(2,'Kopaonik','PLANINSKI','UMERENA'),
(3,'Divčibare','SUMSKI','LAKA');

/*Table structure for table `lovackagrupa` */

DROP TABLE IF EXISTS `lovackagrupa`;

CREATE TABLE `lovackagrupa` (
  `idLovackaGrupa` int(11) NOT NULL AUTO_INCREMENT,
  `imeGrupe` varchar(100) NOT NULL,
  `brojClanova` int(11) NOT NULL,
  PRIMARY KEY (`idLovackaGrupa`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `lovackagrupa` */

insert  into `lovackagrupa`(`idLovackaGrupa`,`imeGrupe`,`brojClanova`) values 
(2,'Ubljani',10),
(3,'Grupa 3',3);

/*Table structure for table `oll` */

DROP TABLE IF EXISTS `oll`;

CREATE TABLE `oll` (
  `idOrganizator` int(11) NOT NULL,
  `idLokacija` int(11) NOT NULL,
  `datumOdrzavanja` date NOT NULL,
  PRIMARY KEY (`idOrganizator`,`idLokacija`,`datumOdrzavanja`),
  KEY `idLokacija` (`idLokacija`),
  CONSTRAINT `oll_ibfk_1` FOREIGN KEY (`idOrganizator`) REFERENCES `organizatorlova` (`idOrganizator`) ON DELETE CASCADE,
  CONSTRAINT `oll_ibfk_2` FOREIGN KEY (`idLokacija`) REFERENCES `lokacija` (`idLokacija`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `oll` */

insert  into `oll`(`idOrganizator`,`idLokacija`,`datumOdrzavanja`) values 
(1,1,'2025-03-01');

/*Table structure for table `opstina` */

DROP TABLE IF EXISTS `opstina`;

CREATE TABLE `opstina` (
  `idOpstina` int(11) NOT NULL AUTO_INCREMENT,
  `naziv` varchar(100) NOT NULL,
  PRIMARY KEY (`idOpstina`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `opstina` */

insert  into `opstina`(`idOpstina`,`naziv`) values 
(1,'Ub'),
(2,'Leskovac'),
(3,'Brzece');

/*Table structure for table `organizatorlova` */

DROP TABLE IF EXISTS `organizatorlova`;

CREATE TABLE `organizatorlova` (
  `idOrganizator` int(11) NOT NULL AUTO_INCREMENT,
  `ime` varchar(50) NOT NULL,
  `prezime` varchar(50) NOT NULL,
  `email` varchar(100) NOT NULL,
  `idOpstina` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  PRIMARY KEY (`idOrganizator`),
  UNIQUE KEY `username` (`username`),
  KEY `organizatorlova_ibfk_1` (`idOpstina`),
  CONSTRAINT `organizatorlova_ibfk_1` FOREIGN KEY (`idOpstina`) REFERENCES `opstina` (`idOpstina`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `organizatorlova` */

insert  into `organizatorlova`(`idOrganizator`,`ime`,`prezime`,`email`,`idOpstina`,`username`,`password`) values 
(1,'Marko','Marković','marko.markovic@email.com',1,'marko','marko'),
(2,'Mila','Mitic','milamitic22@gmail.com',2,'mila','mila'),
(4,'aaaaaaaaaaaaa','Aleksic','aleksa1202@gmail.com',1,'aleksa','aleksa');

/*Table structure for table `rezervacijalova` */

DROP TABLE IF EXISTS `rezervacijalova`;

CREATE TABLE `rezervacijalova` (
  `idRezervacijaLova` int(11) NOT NULL AUTO_INCREMENT,
  `datumRezervacije` date NOT NULL,
  `sezona` enum('Zima','Proleće','Jesen','Leto') NOT NULL,
  `iznosRezervacije` decimal(10,2) NOT NULL,
  `idOrganizator` int(11) NOT NULL,
  `idLovackaGrupa` int(11) NOT NULL,
  `idOpstina` int(11) NOT NULL,
  PRIMARY KEY (`idRezervacijaLova`),
  KEY `idOrganizator` (`idOrganizator`),
  KEY `idLovackaGrupa` (`idLovackaGrupa`),
  KEY `rezervacijalova_ibfk_3` (`idOpstina`),
  CONSTRAINT `rezervacijalova_ibfk_1` FOREIGN KEY (`idOrganizator`) REFERENCES `organizatorlova` (`idOrganizator`) ON DELETE CASCADE,
  CONSTRAINT `rezervacijalova_ibfk_2` FOREIGN KEY (`idLovackaGrupa`) REFERENCES `lovackagrupa` (`idLovackaGrupa`) ON DELETE CASCADE,
  CONSTRAINT `rezervacijalova_ibfk_3` FOREIGN KEY (`idOpstina`) REFERENCES `opstina` (`idOpstina`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `rezervacijalova` */

insert  into `rezervacijalova`(`idRezervacijaLova`,`datumRezervacije`,`sezona`,`iznosRezervacije`,`idOrganizator`,`idLovackaGrupa`,`idOpstina`) values 
(4,'2025-10-15','Jesen',20000.00,4,3,3);

/*Table structure for table `stavkarezervacijelova` */

DROP TABLE IF EXISTS `stavkarezervacijelova`;

CREATE TABLE `stavkarezervacijelova` (
  `idRezervacijaLova` int(11) NOT NULL,
  `rb` int(11) NOT NULL AUTO_INCREMENT,
  `uslovi` varchar(255) NOT NULL,
  `brojDana` int(11) NOT NULL,
  `cena` decimal(10,2) NOT NULL,
  `iznos` decimal(10,2) NOT NULL,
  `idVrstaLova` int(11) NOT NULL,
  PRIMARY KEY (`idRezervacijaLova`,`rb`),
  KEY `idVrstaLova` (`idVrstaLova`),
  KEY `rb` (`rb`),
  CONSTRAINT `stavkarezervacijelova_ibfk_1` FOREIGN KEY (`idRezervacijaLova`) REFERENCES `rezervacijalova` (`idRezervacijaLova`) ON DELETE CASCADE,
  CONSTRAINT `stavkarezervacijelova_ibfk_2` FOREIGN KEY (`idVrstaLova`) REFERENCES `vrstalova` (`idVrstaLova`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `stavkarezervacijelova` */

/*Table structure for table `vrstalova` */

DROP TABLE IF EXISTS `vrstalova`;

CREATE TABLE `vrstalova` (
  `idVrstaLova` int(11) NOT NULL AUTO_INCREMENT,
  `naziv` varchar(100) NOT NULL,
  `tipDivljaci` enum('SRNA','ZEC','LISICA','VUK') NOT NULL,
  `cenaPoDanu` decimal(10,2) NOT NULL,
  PRIMARY KEY (`idVrstaLova`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `vrstalova` */

insert  into `vrstalova`(`idVrstaLova`,`naziv`,`tipDivljaci`,`cenaPoDanu`) values 
(1,'Lov na zeca','ZEC',5000.00),
(2,'Lov na jelena','SRNA',7000.00),
(3,'Lov na lisicu','LISICA',3000.00);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
