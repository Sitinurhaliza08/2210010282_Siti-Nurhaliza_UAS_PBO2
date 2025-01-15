-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 15 Jan 2025 pada 14.43
-- Versi server: 10.4.28-MariaDB
-- Versi PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `2210010282_siti_nurhaliza`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `kebudayaan`
--

CREATE TABLE `kebudayaan` (
  `id_kebudayaan` int(50) NOT NULL,
  `nama_kebudayaan` varchar(255) NOT NULL,
  `deskripsi` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `kebudayaan`
--

INSERT INTO `kebudayaan` (`id_kebudayaan`, `nama_kebudayaan`, `deskripsi`) VALUES
(1, 'Batik', 'salah satu warisan budaya Indonesia yang berupa seni mewarnai kain dengan tangan.'),
(2, 'Ngaben', 'Upacara pembakaran jenazah khas Bali untuk mengantar roh ke alam leluhur.'),
(3, 'Sekaten', 'Tradisi Jawa untuk merayakan Maulid Nabi Muhammad SAW, dengan gamelan dan pasar rakyat.'),
(4, 'Pukul Sapu', 'Tradisi Maluku di mana dua pria saling pukul dengan lidi sebagai bentuk syukur dan keberanian.'),
(5, 'Tiwah', 'Ritual Dayak di Kalimantan untuk mengantarkan roh leluhur ke alam akhir.');

-- --------------------------------------------------------

--
-- Struktur dari tabel `kesenian`
--

CREATE TABLE `kesenian` (
  `id_kesenian` int(50) NOT NULL,
  `nama_kesenian` varchar(255) NOT NULL,
  `deskripsi` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `kesenian`
--

INSERT INTO `kesenian` (`id_kesenian`, `nama_kesenian`, `deskripsi`) VALUES
(1, 'Wayang Kulit', 'Seni pertunjukan bayangan tradisional menggunakan boneka kulit, diiringi gamelan dan cerita epik Mahabharata atau Ramayana. '),
(2, 'Reog Ponorogo', 'Tarian tradisional dari Ponorogo dengan topeng besar berbentuk kepala singa dan bulu merak.'),
(3, 'Angklung', 'Alat musik bambu khas Sunda yang dimainkan dengan digoyangkan, menghasilkan nada harmonis.'),
(4, 'Tari Kecak', 'Tarian ritual Bali yang melibatkan paduan suara \"cak\" dari banyak pria, menceritakan kisah Ramayana. '),
(5, 'Tari Saman', 'Tarian tradisional Aceh yang dinamis dan seragam, menampilkan gerakan cepat dan kekompakan.');

-- --------------------------------------------------------

--
-- Struktur dari tabel `situs_makam`
--

CREATE TABLE `situs_makam` (
  `id_makam` int(50) NOT NULL,
  `nama_makam` varchar(255) NOT NULL,
  `desa` varchar(255) NOT NULL,
  `kecamatan` varchar(255) NOT NULL,
  `pengelola` varchar(255) NOT NULL,
  `juru_kunci` varchar(255) NOT NULL,
  `deskripsi` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `situs_makam`
--

INSERT INTO `situs_makam` (`id_makam`, `nama_makam`, `desa`, `kecamatan`, `pengelola`, `juru_kunci`, `deskripsi`) VALUES
(1, 'Makam Sunan Gunung Jati', 'Astana', 'Gunung Jati', 'Keraton Kasepuhan Cirebon', 'Keturunan Keraton', 'Makam wali terkenal, pusat ziarah religius. '),
(2, 'Makam Bung Karno', 'Bendogerit', 'Kecamatan: ', 'Pemkot Blitar', 'Petugas pemerintah', 'Tempat peristirahatan Presiden pertama RI.'),
(3, 'Makam Sultan Hasanuddin', 'Katangka', 'Somba Opu', 'Pemkab Gowa', 'Keturunan bangsawan', 'Makam pahlawan nasional Sulawesi Selatan.'),
(4, 'Makam Syekh Yusuf', 'Katangka', 'Somba Opu', 'Pemkab Gowa', 'Keluarga Syekh Yusuf', 'Makam ulama besar dan pahlawan nasional.'),
(5, 'Makam R.A. Kartini\r\n', 'Bulu', 'Bulu', 'Pemkab Rembang', 'Warga setempat', 'Makam pahlawan emansipasi wanita.\r\n');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tempat_sejarah`
--

CREATE TABLE `tempat_sejarah` (
  `id_tempat` int(50) NOT NULL,
  `nama_tempat` varchar(255) NOT NULL,
  `desa` varchar(255) NOT NULL,
  `kecamatan` varchar(255) NOT NULL,
  `pengelola` varchar(255) NOT NULL,
  `deskripsi` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `tempat_sejarah`
--

INSERT INTO `tempat_sejarah` (`id_tempat`, `nama_tempat`, `desa`, `kecamatan`, `pengelola`, `deskripsi`) VALUES
(1, 'Candi Borobudur', 'Borobudur', 'Borobudur', 'Balai Konservasi Borobudur', 'Candi Buddha terbesar di dunia, warisan UNESCO.'),
(2, 'Candi Prambanan', 'Bokoharjo', 'Prambanan', 'PT Taman Wisata Candi', 'Candi Hindu terbesar di Indonesia, untuk Trimurti.'),
(3, 'Monumen Nasional (Monas)', 'Gambir', 'Gambir', 'Pemprov DKI Jakarta', 'Simbol perjuangan kemerdekaan dengan museum.'),
(4, 'Taman Sari', 'Patehan', 'Kraton', 'Keraton Yogyakarta', 'Bekas taman istana sultan.'),
(5, 'Tugu Proklamasi', 'Pegangsaan', 'Menteng', 'Pemprov DKI Jakarta', 'Tempat pembacaan Proklamasi Kemerdekaan RI. ');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `kebudayaan`
--
ALTER TABLE `kebudayaan`
  ADD PRIMARY KEY (`id_kebudayaan`);

--
-- Indeks untuk tabel `kesenian`
--
ALTER TABLE `kesenian`
  ADD PRIMARY KEY (`id_kesenian`);

--
-- Indeks untuk tabel `situs_makam`
--
ALTER TABLE `situs_makam`
  ADD PRIMARY KEY (`id_makam`);

--
-- Indeks untuk tabel `tempat_sejarah`
--
ALTER TABLE `tempat_sejarah`
  ADD PRIMARY KEY (`id_tempat`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `kebudayaan`
--
ALTER TABLE `kebudayaan`
  MODIFY `id_kebudayaan` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT untuk tabel `kesenian`
--
ALTER TABLE `kesenian`
  MODIFY `id_kesenian` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT untuk tabel `situs_makam`
--
ALTER TABLE `situs_makam`
  MODIFY `id_makam` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT untuk tabel `tempat_sejarah`
--
ALTER TABLE `tempat_sejarah`
  MODIFY `id_tempat` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
