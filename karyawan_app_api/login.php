<?php  

	require_once 'koneksi.php'; 

	$nik = $_POST['nik'];

	$query = "SELECT * FROM tb_karyawan WHERE nik = '$nik' AND nik = '$nik'";
	$result = mysqli_query($konek,$query);

	$cek = mysqli_fetch_array($result);

	if(isset($cek)){
		echo json_encode(array('kode' =>1, 'pesan' => 'Login Berhasil'));
	}else{
		echo json_encode(array("kode" => 0, "pesan"=>"Username atau Password Salah"));
	}

	mysqli_close($konek);
?>