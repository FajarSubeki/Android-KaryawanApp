<?php 
 require_once 'koneksi.php';

 if($_SERVER['REQUEST_METHOD'] == 'POST')
 {
 	$nik = $_POST['nik'];
 	$nama_karyawan = $_POST['nama_karyawan'];
 	$jenis_kelamin = $_POST['jenis_kelamin'];
 	$tempat_lahir = $_POST['tempat_lahir'];
 	$tanggal_lahir = $_POST['tanggal_lahir'];
 	$alamat = $_POST['alamat'];
 	$jabatan = $_POST['jabatan'];
 	$bagian = $_POST['bagian'];
 	$status = $_POST['status'];
 	$no_hp = $_POST['no_hp'];
 	$email = $_POST['email'];
 	$pendidikan = $_POST['pendidikan'];

 	$query = "INSERT INTO tb_karyawan (nik, nama_karyawan, jenis_kelamin, tempat_lahir, tanggal_lahir, alamat, jabatan, bagian, status, no_hp, email, pendidikan, tgl_masuk) VALUES ('$nik','$nama_karyawan','$jenis_kelamin','$tempat_lahir','$tanggal_lahir','$alamat','$jabatan','$bagian','$status','$no_hp','$email','$pendidikan')";

 	$exeQuery = mysqli_query($konek, $query); 

 	echo ($exeQuery) ? json_encode(array('kode' =>1, 'pesan' => 'Data Berhasil Disimpan')) :  json_encode(array('kode' =>2, 'pesan' => 'Gagal Menyimpan Data'));
 }
 else
 {
 	 echo json_encode(array('kode' =>101, 'pesan' => 'request tidak valid'));
 }

 ?>