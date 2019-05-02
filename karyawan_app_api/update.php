	<?php 
 require_once 'koneksi.php';

 if($_SERVER['REQUEST_METHOD'] == 'POST')
 {
 	$id_karyawan = $_POST['id_karyawan'];
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

 	$query = "UPDATE  tb_karyawan SET nik = '$nik',nama_karyawan = '$nama_karyawan', jenis_kelamin = '$jenis_kelamin', tempat_lahir='$tempat_lahir', tanggal_lahir='$tanggal_lahir', alamat='$alamat', jabatan='$jabatan', bagian='$bagian', status='$status', no_hp='$no_hp', email='$email', pendidikan='$pendidikan' WHERE id_karyawan='$id_karyawan'";

 	$exeQuery = mysqli_query($konek, $query); 

 	echo ($exeQuery) ? json_encode(array('kode' =>1, 'pesan' => 'data berhasil update')) :  json_encode(array('kode' =>2, 'pesan' => 'data gagal diupdate'));
 }
 else
 {
 	 echo json_encode(array('kode' =>101, 'pesan' => 'request tidak valid'));
 }

 ?>