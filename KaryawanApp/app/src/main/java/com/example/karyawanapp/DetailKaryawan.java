package com.example.karyawanapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;


public class DetailKaryawan extends AppCompatActivity {

    ImageView foto_karyawan;
    TextView name, nik, nama, jenis_kelamin, tempat_lahir, tanggal_lahir, alamat, jabatan, bagian, status, no_hp, email, pendidikan, tgl_masuk;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_karyawan);

        name = findViewById(R.id.name);
        nik = findViewById(R.id.nik2);
        nama = findViewById(R.id.nama_karyawan2);
        jenis_kelamin = findViewById(R.id.jenis_kelamin2);
        tempat_lahir = findViewById(R.id.tempat_lahir2);
        tanggal_lahir = findViewById(R.id.tanggal_lahir2);
        alamat = findViewById(R.id.alamat2);
        jabatan = findViewById(R.id.jabatan2);
        bagian = findViewById(R.id.bagian2);
        status = findViewById(R.id.status2);
        no_hp = findViewById(R.id.no_hp2);
        email = findViewById(R.id.email2);
        pendidikan = findViewById(R.id.pendidikan2);
        tgl_masuk = findViewById(R.id.tgl_masuk2);
        foto_karyawan = findViewById(R.id.foto_karaywan);

        Intent data = getIntent();
        final String iddata = data.getStringExtra("id_karyawan");
        if(iddata != null){
            name.setText(data.getStringExtra("nama_karyawan"));
            nik.setText(data.getStringExtra("nik"));
            nama.setText(data.getStringExtra("nama_karyawan"));
            jenis_kelamin.setText(data.getStringExtra("jenis_kelamin"));
            tempat_lahir.setText(data.getStringExtra("tempat_lahir"));
            tanggal_lahir.setText(data.getStringExtra("tanggal_lahir"));
            alamat.setText(data.getStringExtra("alamat"));
            jabatan.setText(data.getStringExtra("jabatan"));
            bagian.setText(data.getStringExtra("bagian"));
            status.setText(data.getStringExtra("status"));
            no_hp.setText(data.getStringExtra("no_hp"));
            email.setText(data.getStringExtra("email"));
            pendidikan.setText(data.getStringExtra("pendidikan"));
            tgl_masuk.setText(data.getStringExtra("tgl_masuk"));
            foto_karyawan.setImageDrawable(getDrawable(R.drawable.avatar));
        }

        pd = new ProgressDialog(this);
    }
}
