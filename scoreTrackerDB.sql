
SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

-- Database: `scoretracker`

USE `scoretracker`;
-- Drop tables if they already exist 

DROP TABLE IF EXISTS `admin`;
DROP TABLE IF EXISTS MembershipList;
DROP TABLE IF EXISTS ScoreEntry;
DROP TABLE IF EXISTS TournamentList;


CREATE TABLE IF NOT EXISTS `admin` (
  `Admin_ID` int(11) NOT NULL AUTO_INCREMENT,
  `User_Name` varchar(50) NOT NULL,
  `Password` varchar(50) NOT NULL,
  PRIMARY KEY (`Admin_ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;


CREATE TABLE IF NOT EXISTS MembershipList (
  User_ID int (11) NOT NULL AUTO_INCREMENT,
  User_Name varchar(50) NOT NULL, 
  Password varchar(50) NOT NULL,
  PRIMARY KEY (User_Id)
);


CREATE TABLE IF NOT EXISTS ScoreEntry(
  Score_ID int (11) NOT NULL AUTO_INCREMENT PRIMARY KEY, 
  Tourney_ID int (11), 
  Player_ID int (11),
  Score_Number int (11) NOT NULL, 
  CONSTRAINT fkTourneyId FOREIGN KEY (Tourney_ID) REFERENCES TournamentList (Tourney_ID),
  CONSTRAINT fkPlayerId FOREIGN KEY (Player_ID) REFERENCES MembershipList (User_ID)
 );

CREATE TABLE IF NOT EXISTS TournamentList(
  Tourney_ID int (11) NOT NULL AUTO_INCREMENT PRIMARY KEY, 
  Tourney_Name varchar(50), 
  Score_ID int (11), 
  Num_Players int (11) NOT NULL, 
  CONSTRAINT fkScoreId FOREIGN KEY (Score_ID) REFERENCES ScoreEntry (Score_ID)
);


--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`Admin_ID`, `User_Name`, `Password`) VALUES
(1, 'explorerone', 'password');
