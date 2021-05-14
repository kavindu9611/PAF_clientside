-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 14, 2021 at 06:06 PM
-- Server version: 10.4.6-MariaDB
-- PHP Version: 7.3.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `pay`
--

-- --------------------------------------------------------

--
-- Table structure for table `payment_table`
--

CREATE TABLE `payment_table` (
  `payment_id` int(11) NOT NULL,
  `customer_n` varchar(50) NOT NULL,
  `amount` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `card_type` varchar(50) NOT NULL,
  `card_no` varchar(50) NOT NULL,
  `exp_month` varchar(50) NOT NULL,
  `exp_year` varchar(50) NOT NULL,
  `cvn` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `payment_table`
--

INSERT INTO `payment_table` (`payment_id`, `customer_n`, `amount`, `email`, `card_type`, `card_no`, `exp_month`, `exp_year`, `cvn`) VALUES
(2, 'Sumathipala', '1500', 'sumathipalaperera@gmail.com', 'Amex', '75964895786275589', '4', '25', '562'),
(3, 'Sanjuka', '200000.00', 'san@gmail.com', 'VISA', '852741963', '11', '2025', '258'),
(5, 'kavindu', '3000.00', 'kavindu3@gmail.com', 'Master', '3455664544', '04', '2022', '2344');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `payment_table`
--
ALTER TABLE `payment_table`
  ADD PRIMARY KEY (`payment_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `payment_table`
--
ALTER TABLE `payment_table`
  MODIFY `payment_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
