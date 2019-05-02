<?php 

	 require_once 'koneksi.php';

	 if($_SERVER['REQUEST_METHOD']=='POST'){

	 	$search = $_POST['search'];
	 	$query = "SELECT * FROM tb_karyawan WHERE nama_karyawan LIKE '%$search%' ORDER BY nama_karyawan ASC";

	 	$res = mysqli_query($konek,$query);
	 	$result = array();

	 	while($row = mysqli_fetch_array($res)){
	 		array_push($result, array('id_karyawan'=>$row[0], 'nik'=>$row[1], 'nama_karyawan'=>$row[2], 'jenis_kelamin'=>$row[3], 'tempat_lahir'=>$row[4], 'tanggal_lahir'=>$row[5], 'alamat'=>$row[6], 'jabatan'=>$row[7], 'bagian'=>$row[8], 'status'=>$row[9], 'no_hp'=>$row[10], 'pendidikan'=>$row[11],'tgl_masuk'=>$row[12]));
	 	}

	 	echo ($res) ? json_encode(array("kode" => 1, "result"=>$result)) : json_encode(array("kode" => 0, "pesan"=>"data tidak ditemukan"));
	 	mysqli_close($konek);

	 }

 ?>