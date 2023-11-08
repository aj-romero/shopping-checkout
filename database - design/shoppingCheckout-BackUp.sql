-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: mysql_db:3306
-- Generation Time: Nov 08, 2023 at 07:39 AM
-- Server version: 8.1.0
-- PHP Version: 8.2.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `shoppingCheckout`
--

-- --------------------------------------------------------

--
-- Table structure for table `card__payments`
--

CREATE TABLE `card__payments` (
  `id` bigint NOT NULL,
  `id_customer` varchar(50) NOT NULL,
  `card_number` varchar(50) NOT NULL,
  `card_holdername` varchar(50) NOT NULL,
  `expiration_date` varchar(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `card__payments`
--

INSERT INTO `card__payments` (`id`, `id_customer`, `card_number`, `card_holdername`, `expiration_date`) VALUES
(1, '9680d900-b9e1-45c5-a032-18a9f1e09996', '4917480000000057', 'ALBER ROMERO', '08/25'),
(2, '9680d900-b9e1-45c5-a032-18a9f1e09996', '4005520000008411', 'ALBER ROMERO', '09/26');

-- --------------------------------------------------------

--
-- Table structure for table `checkouts`
--

CREATE TABLE `checkouts` (
  `id` bigint NOT NULL,
  `payment_code` varchar(255) DEFAULT NULL,
  `ck_status` varchar(10) DEFAULT NULL,
  `created_at` date DEFAULT NULL,
  `updated_at` date DEFAULT NULL,
  `id_customer` varchar(50) DEFAULT NULL,
  `id_payment` bigint DEFAULT NULL,
  `id_shipping_address` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `checkouts`
--

INSERT INTO `checkouts` (`id`, `payment_code`, `ck_status`, `created_at`, `updated_at`, `id_customer`, `id_payment`, `id_shipping_address`) VALUES
(1, '**0057 CP - 24ecf39f-f9bf-417b-8fe4-6568bfa2ba33 - 920.5 USD$', 'DONE', '2023-11-01', '2023-11-07', '9680d900-b9e1-45c5-a032-18a9f1e09996', 1, 1),
(2, NULL, 'OPEN', '2023-11-03', '2023-11-03', 'b32371e4-9603-405d-824d-8efda6221bd5', NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `checkout__products`
--

CREATE TABLE `checkout__products` (
  `id` bigint NOT NULL,
  `id_product` bigint NOT NULL,
  `id_checkout` bigint NOT NULL,
  `quantity` int NOT NULL DEFAULT '1',
  `price` double NOT NULL DEFAULT '0.1'
) ;

--
-- Dumping data for table `checkout__products`
--

INSERT INTO `checkout__products` (`id`, `id_product`, `id_checkout`, `quantity`, `price`) VALUES
(1, 1, 1, 5, 125),
(2, 2, 1, 1, 175),
(4, 4, 1, 1, 6),
(5, 5, 1, 1, 25),
(8, 7, 1, 1, 85),
(9, 8, 1, 1, 4.5),
(11, 2, 2, 5, 175),
(12, 1, 2, 1, 125);

-- --------------------------------------------------------

--
-- Table structure for table `customers`
--

CREATE TABLE `customers` (
  `id` varchar(50) NOT NULL,
  `first_name` varchar(30) DEFAULT NULL,
  `last_name` varchar(30) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `phone` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `customers`
--

INSERT INTO `customers` (`id`, `first_name`, `last_name`, `email`, `phone`) VALUES
('9680d900-b9e1-45c5-a032-18a9f1e09996', 'Noe', 'Romero', 'alber.romero@kodigo.org', '+503 7088 1388'),
('b32371e4-9603-405d-824d-8efda6221bd5', NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `customer__addresses`
--

CREATE TABLE `customer__addresses` (
  `id` bigint NOT NULL,
  `address_name` varchar(50) NOT NULL,
  `id_customer` varchar(50) NOT NULL,
  `state` varchar(50) NOT NULL,
  `city` varchar(50) NOT NULL,
  `street` varchar(100) NOT NULL,
  `reference_phone` varchar(30) NOT NULL,
  `house_number` varchar(10) NOT NULL,
  `reference_name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `customer__addresses`
--

INSERT INTO `customer__addresses` (`id`, `address_name`, `id_customer`, `state`, `city`, `street`, `reference_phone`, `house_number`, `reference_name`) VALUES
(1, 'My addres in Houston', '9680d900-b9e1-45c5-a032-18a9f1e09996', 'Texas', 'Houston', 'Alice foster ST Lot A 77498', '+503 3222 2990', '16604', 'Malyirah Navarro'),
(2, 'My Brother House in California', '9680d900-b9e1-45c5-a032-18a9f1e09996', 'California', 'Panorama City', 'Bellavista st', '+503 3222 2990', '101456', 'Henry Maynor Romero');

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

CREATE TABLE `products` (
  `id` bigint NOT NULL,
  `code` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL,
  `stock` int NOT NULL DEFAULT '0',
  `price` double NOT NULL DEFAULT '0'
) ;

--
-- Dumping data for table `products`
--

INSERT INTO `products` (`id`, `code`, `name`, `stock`, `price`) VALUES
(1, '101', 'Monitor 20 inch', 100, 125),
(2, '102', 'Monitor 27 inch', 100, 175),
(3, '103', 'Teclado Mecanico LR', 20, 70),
(4, '104', 'Mouse standar', 50, 6),
(5, '105', 'USB 128GB', 100, 25),
(6, '106', 'UPS 700VA', 20, 65),
(7, '107', 'CPU INTEL CORE PENTIUM', 50, 85),
(8, '108', 'Micro USB de 64GB', 100, 4.5);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `card__payments`
--
ALTER TABLE `card__payments`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id` (`id`),
  ADD UNIQUE KEY `card_number` (`card_number`),
  ADD KEY `FK_customers_cards` (`id_customer`);

--
-- Indexes for table `checkouts`
--
ALTER TABLE `checkouts`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id` (`id`),
  ADD KEY `FK_customers_checkouts` (`id_customer`),
  ADD KEY `id_payment` (`id_payment`),
  ADD KEY `FK_address_ck` (`id_shipping_address`);

--
-- Indexes for table `checkout__products`
--
ALTER TABLE `checkout__products`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id` (`id`),
  ADD KEY `FK_could_be` (`id_product`),
  ADD KEY `FK_must_have` (`id_checkout`);

--
-- Indexes for table `customers`
--
ALTER TABLE `customers`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id` (`id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Indexes for table `customer__addresses`
--
ALTER TABLE `customer__addresses`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id` (`id`),
  ADD UNIQUE KEY `address_name` (`address_name`),
  ADD KEY `FK_customers_addresses` (`id_customer`);

--
-- Indexes for table `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id` (`id`),
  ADD UNIQUE KEY `code` (`code`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `card__payments`
--
ALTER TABLE `card__payments`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `checkouts`
--
ALTER TABLE `checkouts`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `checkout__products`
--
ALTER TABLE `checkout__products`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `customer__addresses`
--
ALTER TABLE `customer__addresses`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `products`
--
ALTER TABLE `products`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `card__payments`
--
ALTER TABLE `card__payments`
  ADD CONSTRAINT `FK_customers_cards` FOREIGN KEY (`id_customer`) REFERENCES `customers` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Constraints for table `checkouts`
--
ALTER TABLE `checkouts`
  ADD CONSTRAINT `checkouts_ibfk_1` FOREIGN KEY (`id_payment`) REFERENCES `card__payments` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `FK_address_ck` FOREIGN KEY (`id_shipping_address`) REFERENCES `customer__addresses` (`id`),
  ADD CONSTRAINT `FK_customers_checkouts` FOREIGN KEY (`id_customer`) REFERENCES `customers` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Constraints for table `checkout__products`
--
ALTER TABLE `checkout__products`
  ADD CONSTRAINT `FK_could_be` FOREIGN KEY (`id_product`) REFERENCES `products` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `FK_must_have` FOREIGN KEY (`id_checkout`) REFERENCES `checkouts` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Constraints for table `customer__addresses`
--
ALTER TABLE `customer__addresses`
  ADD CONSTRAINT `FK_customers_addresses` FOREIGN KEY (`id_customer`) REFERENCES `customers` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
