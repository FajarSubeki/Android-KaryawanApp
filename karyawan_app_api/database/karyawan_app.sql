-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 19, 2019 at 04:41 AM
-- Server version: 10.1.28-MariaDB
-- PHP Version: 7.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `karyawan_app`
--

-- --------------------------------------------------------

--
-- Table structure for table `tb_karyawan`
--

CREATE TABLE `tb_karyawan` (
  `id_karyawan` int(11) NOT NULL,
  `nik` bigint(20) NOT NULL,
  `nama_karyawan` varchar(100) NOT NULL,
  `jenis_kelamin` varchar(10) NOT NULL,
  `tempat_lahir` varchar(50) NOT NULL,
  `tanggal_lahir` date NOT NULL,
  `alamat` text NOT NULL,
  `jabatan` varchar(50) NOT NULL,
  `bagian` varchar(50) NOT NULL,
  `status` varchar(15) NOT NULL,
  `no_hp` varchar(12) NOT NULL,
  `email` varchar(50) NOT NULL,
  `pendidikan` varchar(3) NOT NULL,
  `tgl_masuk` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `foto_karyawan` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_karyawan`
--

INSERT INTO `tb_karyawan` (`id_karyawan`, `nik`, `nama_karyawan`, `jenis_kelamin`, `tempat_lahir`, `tanggal_lahir`, `alamat`, `jabatan`, `bagian`, `status`, `no_hp`, `email`, `pendidikan`, `tgl_masuk`, `foto_karyawan`) VALUES
(1, 12912901290, 'Fajar Subeki', 'Laki-laki', 'Bogor', '2018-08-06', 'Bogor', 'Direktur', 'IT', 'Nikah', '081200001999', 'fajarsub06@gmail.com', 'S1', '2018-08-11 10:00:50', ''),
(2, 1723928392, 'Mahmudin', 'Perempuan', 'Bogor', '2018-08-08', 'Bekasi', 'Asisten Direktur ', 'TI', 'Belum Nikah', '081299992399', 'mahmudin@gmail.com', 'S1', '2018-08-14 07:29:23', ''),
(3, 1526323922, 'Dika Ramadhan', 'Laki-laki', 'Jakarta', '2018-08-02', 'Kuningan', 'Karyawan', 'Design', 'Belum Nikah', '081291291289', 'dikaramdhan@gmail.com', 'D3', '2018-08-19 01:49:24', ''),
(4, 12812920199, 'Farika Amanda', 'Perempuan', 'Bandung', '2018-08-02', 'Grogol', 'Sekretaris', 'Keuangan', 'Belum Nikah', '083823992399', 'farikaamanda@gmail.com', 'SMK', '2018-08-19 01:50:54', '');

-- --------------------------------------------------------

--
-- Table structure for table `tb_register`
--

CREATE TABLE `tb_register` (
  `id_register` int(11) NOT NULL,
  `id_karyawan` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_register`
--

INSERT INTO `tb_register` (`id_register`, `id_karyawan`, `username`, `password`) VALUES
(1, 1, 'fajarsubeki', 'sub060401'),
(2, 2, 'mahmudin', 'mahmudin123'),
(3, 4, 'subeki', 'subeki123');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tb_karyawan`
--
ALTER TABLE `tb_karyawan`
  ADD PRIMARY KEY (`id_karyawan`);

--
-- Indexes for table `tb_register`
--
ALTER TABLE `tb_register`
  ADD PRIMARY KEY (`id_register`),
  ADD KEY `id_karyawan` (`id_karyawan`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tb_karyawan`
--
ALTER TABLE `tb_karyawan`
  MODIFY `id_karyawan` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `tb_register`
--
ALTER TABLE `tb_register`
  MODIFY `id_register` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `tb_register`
--
ALTER TABLE `tb_register`
  ADD CONSTRAINT `tb_register_ibfk_1` FOREIGN KEY (`id_karyawan`) REFERENCES `tb_karyawan` (`id_karyawan`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
