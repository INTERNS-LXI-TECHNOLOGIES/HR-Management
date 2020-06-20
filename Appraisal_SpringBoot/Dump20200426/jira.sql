-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jun 20, 2020 at 02:08 PM
-- Server version: 5.6.17
-- PHP Version: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `appraisal`
--

-- --------------------------------------------------------

--
-- Table structure for table `jira`
--

CREATE TABLE IF NOT EXISTS `jira` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` date DEFAULT NULL,
  `hour` float DEFAULT NULL,
  `user_extra_id` bigint(20) DEFAULT NULL,
  UNIQUE KEY `id` (`id`),
  KEY `user_extra_id` (`user_extra_id`),
  KEY `user_extra_id_2` (`user_extra_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `jira`
--

INSERT INTO `jira` (`id`, `date`, `hour`, `user_extra_id`) VALUES
(1, NULL, 2, NULL),
(2, NULL, 2, NULL),
(3, '2020-06-19', 2, 14);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `jira`
--
ALTER TABLE `jira`
  ADD CONSTRAINT `jira_ibfk_1` FOREIGN KEY (`user_extra_id`) REFERENCES `user_extra` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
