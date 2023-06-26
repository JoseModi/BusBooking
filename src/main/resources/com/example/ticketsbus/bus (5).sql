-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 26, 2023 at 05:53 PM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.0.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bus`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `username` varchar(45) NOT NULL,
  `password` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`username`, `password`) VALUES
('admin', '1234');

-- --------------------------------------------------------

--
-- Table structure for table `book`
--

CREATE TABLE `book` (
  `from` varchar(50) NOT NULL,
  `to` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `book`
--

INSERT INTO `book` (`from`, `to`) VALUES
('Barishal', 'Jessore'),
('Chittagong', 'Rajshahi'),
('Dhaka', 'Sylhet'),
('Jessore', 'Chandpur'),
('Khulna', 'Chittagong'),
('Sylhet', 'Dhaka');

-- --------------------------------------------------------

--
-- Table structure for table `bookings`
--

CREATE TABLE `bookings` (
  `name` varchar(45) NOT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `source` varchar(45) DEFAULT NULL,
  `destination` varchar(45) DEFAULT NULL,
  `service` varchar(45) NOT NULL,
  `date` varchar(45) DEFAULT NULL,
  `seats` varchar(45) DEFAULT NULL,
  `amount` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `bookings`
--

INSERT INTO `bookings` (`name`, `phone`, `source`, `destination`, `service`, `date`, `seats`, `amount`) VALUES
('Arif', '076576443', 'Dhaka', 'Sylhet', 'Ena Enterprise', '2022-01-15', '3', '1350'),
('Arif', '67686900978', 'Dhaka', 'Chittagong', 'Sheba Greenline', '2022-01-16', '3', '1950'),
('Neaz', '0420939923', 'Dhaka', 'Sylhet', 'Ena Enterprise', '2022-01-15', '3', '1350');

-- --------------------------------------------------------

--
-- Table structure for table `bookk`
--

CREATE TABLE `bookk` (
  `location` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `bookk`
--

INSERT INTO `bookk` (`location`) VALUES
('Buru Buru'),
('CBD'),
('Chittagong'),
('Dhaka'),
('Jessore'),
('Khulna'),
('Rajshahi'),
('Sylhet');

-- --------------------------------------------------------

--
-- Table structure for table `search`
--

CREATE TABLE `search` (
  `service` varchar(50) NOT NULL,
  `source` varchar(45) DEFAULT NULL,
  `dest` varchar(50) DEFAULT NULL,
  `fare` int(11) DEFAULT NULL,
  `dtime` time DEFAULT NULL,
  `atime` time DEFAULT NULL,
  `seat` int(11) DEFAULT NULL,
  `date` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `search`
--

INSERT INTO `search` (`service`, `source`, `dest`, `fare`, `dtime`, `atime`, `seat`, `date`) VALUES
('Ena Enterprise', 'Dhaka', 'Sylhet', 450, '12:30:00', '07:00:00', 41, '2022-01-15'),
('Shaymoli Enterprise', 'Dhaka', 'Rajshahi', 500, '01:30:00', '06:00:00', 41, '2022-01-20'),
('Sheba Greenline', 'Dhaka', 'Chittagong', 650, '01:30:00', '10:00:00', 41, '2022-01-16');

-- --------------------------------------------------------

--
-- Table structure for table `seats`
--

CREATE TABLE `seats` (
  `seatname` varchar(50) NOT NULL,
  `uname` varchar(50) NOT NULL,
  `service` varchar(50) NOT NULL,
  `count` int(11) NOT NULL,
  `id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `seats`
--

INSERT INTO `seats` (`seatname`, `uname`, `service`, `count`, `id`) VALUES
('A1', 'Neaz', 'Ena Enterprise', 0, 1),
('A2', 'Neaz', 'Ena Enterprise', 0, 2),
('A1', 'Neaz', 'Shaymoli Enterprise', 1, 3),
('A2', 'Neaz', 'Shaymoli Enterprise', 1, 4),
('A3', 'Neaz', 'Ena Enterprise', 0, 5),
('A4', 'Neaz', 'Ena Enterprise', 0, 6),
('B1', 'Neaz', 'Ena Enterprise', 0, 7),
('B2', 'Neaz', 'Ena Enterprise', 0, 8),
('B3', 'Neaz', 'Ena Enterprise', 0, 9),
('B4', 'Neaz', 'Ena Enterprise', 1, 10),
('C1', 'Neaz', 'Ena Enterprise', 1, 11),
('C2', 'Neaz', 'Ena Enterprise', 1, 12),
('C3', 'Neaz', 'Ena Enterprise', 1, 13),
('C4', 'Neaz', 'Ena Enterprise', 1, 14),
('D1', 'Neaz', 'Ena Enterprise', 1, 15),
('D2', 'Neaz', 'Ena Enterprise', 0, 16),
('D3', 'Neaz', 'Ena Enterprise', 0, 17),
('D4', 'Neaz', 'Ena Enterprise', 0, 18),
('A3', 'Neaz', 'Shaymoli Enterprise', 1, 19),
('A4', 'Neaz', 'Shaymoli Enterprise', 0, 20),
('B1', 'Neaz', 'Shaymoli Enterprise', 1, 21),
('B2', 'Neaz', 'Shaymoli Enterprise', 0, 22),
('B3', 'Neaz', 'Shaymoli Enterprise', 0, 23),
('B4', 'Neaz', 'Shaymoli Enterprise', 0, 24),
('C1', 'Neaz', 'Shaymoli Enterprise', 0, 25),
('C2', 'Neaz', 'Shaymoli Enterprise', 0, 26),
('C3', 'Neaz', 'Shaymoli Enterprise', 0, 27),
('C4', 'Neaz', 'Shaymoli Enterprise', 0, 28),
('D1', 'Neaz', 'Shaymoli Enterprise', 0, 29),
('D2', 'Neaz', 'Shaymoli Enterprise', 0, 30),
('D3', 'Neaz', 'Shaymoli Enterprise', 0, 31),
('D4', 'Neaz', 'Shaymoli Enterprise', 0, 32),
('A1', 'Neaz', 'Sheba Greenline', 0, 33),
('A2', 'Neaz', 'Sheba Greenline', 0, 34),
('A3', 'Neaz', 'Sheba Greenline', 0, 35),
('A4', 'Neaz', 'Sheba Greenline', 0, 36),
('B1', 'Neaz', 'Sheba Greenline', 0, 37),
('B2', 'Neaz', 'Sheba Greenline', 0, 38),
('B3', 'Neaz', 'Sheba Greenline', 0, 39),
('B4', 'Neaz', 'Sheba Greenline', 0, 40),
('C1', 'Neaz', 'Sheba Greenline', 0, 41),
('C2', 'Neaz', 'Sheba Greenline', 0, 42),
('C3', 'Neaz', 'Sheba Greenline', 0, 50),
('C4', 'Neaz', 'Sheba Greenline', 0, 51),
('D1', 'Neaz', 'Sheba Greenline', 0, 54),
('D2', 'Neaz', 'Sheba Greenline', 0, 55),
('D3', 'Neaz', 'Sheba Greenline', 0, 56),
('D4', 'Neaz', 'Sheba Greenline', 0, 57);

-- --------------------------------------------------------

--
-- Table structure for table `seat_names`
--

CREATE TABLE `seat_names` (
  `seatname` varchar(50) NOT NULL,
  `picked` int(11) NOT NULL,
  `service` varchar(50) NOT NULL,
  `uname` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `seat_names`
--

INSERT INTO `seat_names` (`seatname`, `picked`, `service`, `uname`) VALUES
('A1', 1, 'Ena Enterprise', 'Arif'),
('A2', 1, 'Ena Enterprise', 'Neaz'),
('A3', 0, 'Ena Enterprise', ''),
('A4', 0, 'Ena Enterprise', ''),
('B1', 1, 'Ena Enterprise', 'Neaz'),
('B2', 1, 'Ena Enterprise', 'Arif'),
('B3', 0, 'Ena Enterprise', ''),
('B4', 1, 'Ena Enterprise', 'Neaz'),
('C1', 1, 'Ena Enterprise', 'Arif'),
('C2', 0, 'Ena Enterprise', ''),
('C3', 0, 'Shaymoli Enterprise', ''),
('C4', 0, 'Shaymoli Enterprise', ''),
('D1', 0, 'Shaymoli Enterprise', ''),
('D2', 0, 'Shaymoli Enterprise', ''),
('D3', 0, 'Shaymoli Enterprise', ''),
('D4', 0, 'Shaymoli Enterprise', ''),
('E1', 0, 'Shaymoli Enterprise', ''),
('E2', 0, 'Shaymoli Enterprise', ''),
('E3', 0, 'Shaymoli Enterprise', ''),
('E4', 0, 'Sheba Greenline', ''),
('F2', 0, 'Sheba Greenline', ''),
('F3', 0, 'Sheba Greenline', ''),
('F4', 1, 'Sheba Greenline', 'Arif'),
('G1', 0, 'Sheba Greenline', ''),
('G2', 1, 'Sheba Greenline', 'Arif'),
('G3', 1, 'Sheba Greenline', ''),
('G4', 1, 'Sheba Greenline', 'Neaz'),
('H1', 1, 'Sheba Greenline', 'Neaz'),
('H2', 0, 'Sheba Greenline', ''),
('H3', 1, 'Sheba Greenline', 'Arif'),
('H4', 1, 'Sheba Greenline', '');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `uname` varchar(50) NOT NULL,
  `password` varchar(45) DEFAULT NULL,
  `fname` varchar(45) DEFAULT NULL,
  `lname` varchar(45) DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `age` varchar(45) DEFAULT NULL,
  `state` varchar(45) DEFAULT NULL,
  `city` varchar(45) DEFAULT NULL,
  `gender` varchar(23) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`uname`, `password`, `fname`, `lname`, `phone`, `age`, `state`, `city`, `gender`, `email`) VALUES
('Arif', '1234', 'Arif', 'Hasan', '01658421', '25', 'Dhaka', 'Dhaka', 'Male', 'arif@gmail.com'),
('jose', '1234', 'Joseph', 'Odhiambo', '0799213371', '26', 'Kenya', 'Nairobi', 'Male', 'jose@email.com'),
('Neaz', '1234', 'Neaz', 'Mahmud', '02164654', '22', 'Dhaka', 'Dhaka', 'Male', 'neaz@gmail.com');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`username`);

--
-- Indexes for table `book`
--
ALTER TABLE `book`
  ADD PRIMARY KEY (`from`);

--
-- Indexes for table `bookings`
--
ALTER TABLE `bookings`
  ADD KEY `bookings_ibfk_1` (`name`);

--
-- Indexes for table `bookk`
--
ALTER TABLE `bookk`
  ADD PRIMARY KEY (`location`);

--
-- Indexes for table `search`
--
ALTER TABLE `search`
  ADD PRIMARY KEY (`service`);

--
-- Indexes for table `seats`
--
ALTER TABLE `seats`
  ADD PRIMARY KEY (`id`),
  ADD KEY `seatname` (`seatname`),
  ADD KEY `uname` (`uname`),
  ADD KEY `service` (`service`);

--
-- Indexes for table `seat_names`
--
ALTER TABLE `seat_names`
  ADD PRIMARY KEY (`seatname`),
  ADD KEY `service` (`service`),
  ADD KEY `uname` (`uname`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`uname`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `bookings`
--
ALTER TABLE `bookings`
  ADD CONSTRAINT `bookings_ibfk_1` FOREIGN KEY (`name`) REFERENCES `user` (`uname`);

--
-- Constraints for table `seats`
--
ALTER TABLE `seats`
  ADD CONSTRAINT `seats_ibfk_1` FOREIGN KEY (`seatname`) REFERENCES `seat_names` (`seatname`),
  ADD CONSTRAINT `seats_ibfk_2` FOREIGN KEY (`uname`) REFERENCES `user` (`uname`),
  ADD CONSTRAINT `seats_ibfk_3` FOREIGN KEY (`service`) REFERENCES `search` (`service`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
