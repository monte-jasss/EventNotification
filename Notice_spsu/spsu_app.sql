-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 28, 2018 at 07:11 PM
-- Server version: 10.1.25-MariaDB
-- PHP Version: 7.1.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `spsu_app`
--

-- --------------------------------------------------------

--
-- Table structure for table `tb_branch`
--

CREATE TABLE `tb_branch` (
  `id` int(11) NOT NULL,
  `branch` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_branch`
--

INSERT INTO `tb_branch` (`id`, `branch`) VALUES
(2, 'Biotechnology'),
(3, 'Civil Engineering'),
(1, 'Computer Science and Engineering'),
(4, 'Mechanical Engineering');

-- --------------------------------------------------------

--
-- Table structure for table `tb_notice`
--

CREATE TABLE `tb_notice` (
  `id` int(11) NOT NULL,
  `subject` varchar(50) NOT NULL,
  `content` varchar(5000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_notice`
--

INSERT INTO `tb_notice` (`id`, `subject`, `content`) VALUES
(1, 'Monu got placed by SPSU', 'if($notice = mysqli_query($con, $query)){ 		while($item = mysqli_fetch_array($notice)){ 			array_push($result,array(\'id\'=>$item[\'id\'], \'subject\'=>$item[\'subject\'], \'content\'=>$item[\'content\'])); 		} 		echo json_encode($result); 	}'),
(2, 'Suraj got placed by Inchmark', 'if($notice = mysqli_query($con, $query)){ while($item = mysqli_fetch_array($notice)){ array_push($result,array(\'id\'=>$item[\'id\'], \'subject\'=>$item[\'subject\'], \'content\'=>$item[\'content\'])); } echo json_encode($result); }'),
(3, 'Ashwani got placed by ValuerHR', 'if($notice = mysqli_query($con, $query)){ while($item = mysqli_fetch_array($notice)){ array_push($result,array(\'id\'=>$item[\'id\'], \'subject\'=>$item[\'subject\'], \'content\'=>$item[\'content\'])); } echo json_encode($result); }'),
(4, 'SPSU is ranked no. 5', 'if($notice = mysqli_query($con, $query)){ while($item = mysqli_fetch_array($notice)){ array_push($result,array(\'id\'=>$item[\'id\'], \'subject\'=>$item[\'subject\'], \'content\'=>$item[\'content\'])); } echo json_encode($result); }'),
(5, 'Declared best university in udaipur', 'monu kesa h<br> tu kb  aa rh h'),
(6, 'Ranked no. 95 in India', 'monu\\n kesa\\n h tu kb \\n aa rh h'),
(7, 'Admission Open 2018', ''),
(8, 'Scholarship Provided to Monu', ''),
(9, 'Good news for SPSU', ''),
(10, 'Welcome to SPSU', ''),
(11, 'New Admission 2017', ''),
(12, 'Accept the invitation', ''),
(13, 'Test', ''),
(14, 'demo', ''),
(15, 'again', 'UPDATE `tb_notice` SET `content` = \'if($notice = mysqli_query($con, $query)){ while($item = mysqli_fetch_array($notice)){ array_push($result,array(\\\'id\\\'=>$item[\\\'id\\\'], \\\'subject\\\'=>$item[\\\'subject\\\'], \\\'content\\\'=>$item[\\\'content\\\'])); } echo json_encode($result); }\' WHERE `tb_notice`.`id` = 1;');

-- --------------------------------------------------------

--
-- Table structure for table `tb_result`
--

CREATE TABLE `tb_result` (
  `id` int(11) NOT NULL,
  `enrollment` varchar(20) NOT NULL,
  `name` varchar(500) NOT NULL,
  `code` varchar(10) NOT NULL,
  `subject` varchar(500) NOT NULL,
  `grade` varchar(10) NOT NULL,
  `semester` int(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_result`
--

INSERT INTO `tb_result` (`id`, `enrollment`, `name`, `code`, `subject`, `grade`, `semester`) VALUES
(1, '15CS001943', 'Monu Lakshkar', 'CS-369', 'Android App Development', 'A+', 5),
(3, '15CS001943', 'Monu Lakshkar', 'CS-366', 'Web Development', 'O', 5),
(4, '15CS001943', 'Monu Lakshkar', 'CS-368', 'Java and Python', 'A', 5);

-- --------------------------------------------------------

--
-- Table structure for table `tb_semester`
--

CREATE TABLE `tb_semester` (
  `id` int(11) NOT NULL,
  `semester` int(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_semester`
--

INSERT INTO `tb_semester` (`id`, `semester`) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5),
(6, 6),
(7, 7),
(8, 8);

-- --------------------------------------------------------

--
-- Table structure for table `tb_timetable`
--

CREATE TABLE `tb_timetable` (
  `semester` int(11) NOT NULL,
  `branch` varchar(500) NOT NULL,
  `image` varchar(1000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_timetable`
--

INSERT INTO `tb_timetable` (`semester`, `branch`, `image`) VALUES
(1, 'BIOTECHNOLOGY', '1 BIOTECHNOLOGY.jpg'),
(1, 'CIVIL ENGINEERING', '1 CIVIL ENGINEERING.jpg'),
(1, 'COMPUTER SCIENCE AND ENGINEERING', '1 COMPUTER SCIENCE AND ENGINEERING.jpg'),
(1, 'MECHANICAL ENGINEERING', '1 MECHANICAL ENGINEERING.png'),
(2, 'BIOTECHNOLOGY', '2 BIOTECHNOLOGY.jpg'),
(2, 'CIVIL ENGINEERING', '2 CIVIL ENGINEERING.jpg'),
(2, 'COMPUTER SCIENCE AND ENGINEERING', '2 COMPUTER SCIENCE AND ENGINEERING.jpg'),
(2, 'MECHANICAL ENGINEERING', '2 MECHANICAL ENGINEERING.png'),
(3, 'BIOTECHNOLOGY', '3 BIOTECHNOLOGY.jpg'),
(3, 'CIVIL ENGINEERING', '3 CIVIL ENGINEERING.jpg'),
(3, 'COMPUTER SCIENCE AND ENGINEERING', '3 COMPUTER SCIENCE AND ENGINEERING.jpg'),
(3, 'MECHANICAL ENGINEERING', '3 MECHANICAL ENGINEERING.png'),
(4, 'BIOTECHNOLOGY', '4 BIOTECHNOLOGY.jpg'),
(4, 'CIVIL ENGINEERING', '4 CIVIL ENGINEERING.jpg'),
(4, 'COMPUTER SCIENCE AND ENGINEERING', '4 COMPUTER SCIENCE AND ENGINEERING.jpg'),
(4, 'MECHANICAL ENGINEERING', '4 MECHANICAL ENGINEERING.png'),
(5, 'BIOTECHNOLOGY', '5 BIOTECHNOLOGY.jpg'),
(5, 'CIVIL ENGINEERING', '5 CIVIL ENGINEERING.png'),
(5, 'COMPUTER SCIENCE AND ENGINEERING', '5 COMPUTER SCIENCE AND ENGINEERING.jpg'),
(5, 'MECHANICAL ENGINEERING', '5 MECHANICAL ENGINEERING.png'),
(6, 'CIVIL ENGINEERING', '6 CIVIL ENGINEERING.png'),
(6, 'COMPUTER SCIENCE AND ENGINEERING', '6 COMPUTER SCIENCE AND ENGINEERING.jpg'),
(6, 'MECHANICAL ENGINEERING', '6 MECHANICAL ENGINEERING.png'),
(7, 'BIOTECHNOLOGY', '7 BIOTECHNOLOGY.jpg'),
(7, 'CIVIL ENGINEERING', '7 CIVIL ENGINEERING.png'),
(7, 'COMPUTER SCIENCE AND ENGINEERING', '7 COMPUTER SCIENCE AND ENGINEERING.png'),
(7, 'MECHANICAL ENGINEERING', '7 MECHANICAL ENGINEERING.jpg'),
(8, 'BIOTECHNOLOGY', '8 BIOTECHNOLOGY.jpg'),
(8, 'CIVIL ENGINEERING', '8 CIVIL ENGINEERING.jpg'),
(8, 'COMPUTER SCIENCE AND ENGINEERING', '8 COMPUTER SCIENCE AND ENGINEERING.png'),
(8, 'MECHANICAL ENGINEERING', '8 MECHANICAL ENGINEERING.jpg');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tb_branch`
--
ALTER TABLE `tb_branch`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `branch` (`branch`);

--
-- Indexes for table `tb_notice`
--
ALTER TABLE `tb_notice`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tb_result`
--
ALTER TABLE `tb_result`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `enrollment` (`enrollment`,`code`,`semester`);

--
-- Indexes for table `tb_semester`
--
ALTER TABLE `tb_semester`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `semester` (`semester`);

--
-- Indexes for table `tb_timetable`
--
ALTER TABLE `tb_timetable`
  ADD UNIQUE KEY `semester` (`semester`,`branch`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tb_branch`
--
ALTER TABLE `tb_branch`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `tb_notice`
--
ALTER TABLE `tb_notice`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;
--
-- AUTO_INCREMENT for table `tb_result`
--
ALTER TABLE `tb_result`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `tb_semester`
--
ALTER TABLE `tb_semester`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
