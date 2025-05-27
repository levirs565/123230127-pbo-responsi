-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 27 Bulan Mei 2025 pada 07.01
-- Versi server: 10.4.32-MariaDB
-- Versi PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `123230127_pbo_responsi`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `product`
--

CREATE TABLE `product` (
  `id` varchar(36) NOT NULL DEFAULT uuid(),
  `nama` varchar(100) NOT NULL,
  `jumlah_unit` int(11) NOT NULL,
  `jam_kerja` int(11) NOT NULL,
  `jumlah_tenaga_kerja` int(11) NOT NULL,
  `biaya_bahan_baku` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `product`
--

INSERT INTO `product` (`id`, `nama`, `jumlah_unit`, `jam_kerja`, `jumlah_tenaga_kerja`, `biaya_bahan_baku`) VALUES
('7ca26964-3ab4-11f0-bdec-b445061472c1', 'Buku', 100, 4, 100, 15000),
('8409f746-3ab4-11f0-bdec-b445061472c1', 'Keledai', 100, 5, 100, 15000),
('e2bc49ce-3ab3-11f0-bdec-b445061472c1', 'Tas', 10, 2, 2, 10000),
('ffc7b96c-3ab6-11f0-bdec-b445061472c1', 'Laptop', 10, 2, 10, 150000);

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
