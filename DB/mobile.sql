-- phpMyAdmin SQL Dump
-- version 4.8.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: 2020-01-05 09:37:53
-- 服务器版本： 5.6.40
-- PHP Version: 7.1.16

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `mobile`
--

-- --------------------------------------------------------

--
-- 表的结构 `number`
--

CREATE TABLE `number` (
  `number_id` int(10) UNSIGNED NOT NULL,
  `number_number` varchar(45) DEFAULT NULL COMMENT '手机的号码',
  `number_status` varchar(45) DEFAULT NULL COMMENT '号码的状态【停用、注销、使用、未使用】',
  `user_id` int(11) DEFAULT NULL COMMENT '所属的用户ID',
  `packagelist_id_list` varchar(2000) DEFAULT NULL COMMENT '选择的套餐列表'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- 表的结构 `packagelist`
--

CREATE TABLE `packagelist` (
  `package_id` int(10) UNSIGNED NOT NULL,
  `package_monthRent` varchar(45) DEFAULT NULL COMMENT '月租',
  `package_minCharge` varchar(45) DEFAULT NULL,
  `package_msgCharge` varchar(45) DEFAULT NULL,
  `package_totalTraffic` varchar(45) DEFAULT NULL COMMENT '流量总额'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- 表的结构 `rent`
--

CREATE TABLE `rent` (
  `rent_id` int(10) UNSIGNED NOT NULL,
  `rent_content` varchar(45) DEFAULT NULL COMMENT '收费项名称',
  `rent_money` varchar(45) DEFAULT NULL COMMENT '费用'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- 表的结构 `user`
--

CREATE TABLE `user` (
  `user_id` int(10) UNSIGNED NOT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `user_number` varchar(255) DEFAULT NULL COMMENT '	',
  `user_password` varchar(45) DEFAULT NULL,
  `user_role` varchar(45) DEFAULT NULL,
  `user_permission` varchar(45) DEFAULT NULL,
  `user_sex` varchar(45) DEFAULT NULL,
  `user_addrs` varchar(45) DEFAULT NULL,
  `user_status` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `user`
--

INSERT INTO `user` (`user_id`, `user_name`, `user_number`, `user_password`, `user_role`, `user_permission`, `user_sex`, `user_addrs`, `user_status`) VALUES
(1, 'admin', 'admin', 'admin', 'admin', 'admin', '男', 'admin', '正常'),
(2, 'sale', 'sale', 'sale', 'sale', 'sale', '男', 'sale', '正常'),
(3, '菜菜', '123456', '123456', 'user', 'user', '男', 'user地址', '正常'),
(4, 'test停用状态的用户', '12345678', '12345678', 'user', 'user', '女', '用户的test地址', '停用'),
(5, '123456789test注销用户', '123456789', '123456789', 'user', 'user', '男', 'test地址123456789', '注销');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `number`
--
ALTER TABLE `number`
  ADD PRIMARY KEY (`number_id`);

--
-- Indexes for table `packagelist`
--
ALTER TABLE `packagelist`
  ADD PRIMARY KEY (`package_id`);

--
-- Indexes for table `rent`
--
ALTER TABLE `rent`
  ADD PRIMARY KEY (`rent_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`user_id`);

--
-- 在导出的表使用AUTO_INCREMENT
--

--
-- 使用表AUTO_INCREMENT `number`
--
ALTER TABLE `number`
  MODIFY `number_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- 使用表AUTO_INCREMENT `packagelist`
--
ALTER TABLE `packagelist`
  MODIFY `package_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- 使用表AUTO_INCREMENT `rent`
--
ALTER TABLE `rent`
  MODIFY `rent_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- 使用表AUTO_INCREMENT `user`
--
ALTER TABLE `user`
  MODIFY `user_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
