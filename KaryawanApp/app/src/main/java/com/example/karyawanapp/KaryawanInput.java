package com.example.karyawanapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatRadioButton;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.karyawanapp.api.KaryawanApiRequest;
import com.example.karyawanapp.api.KaryawanRetroServer;
import com.example.karyawanapp.helper.DatePickerView;
import com.example.karyawanapp.model.KaryawanResponseModel;
import com.google.android.material.snackbar.Snackbar;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.example.karyawanapp.utils.validate;

public class KaryawanInput extends AppCompatActivity {

    EditText nik, nama_karyawan, alamat, no_hp, email, pendidikan;
    private RadioButton radioSexButton, RadioStatusButton;
    DatePickerView tgl_lahir;
    Button save;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_karyawan_input);

        final MaterialSpinner tempat_lahir = findViewById(R.id.tempat_lahir);
        final MaterialSpinner jabatan = findViewById(R.id.jabatan);
        final MaterialSpinner bagian = findViewById(R.id.bagian);
        MaterialSpinner pendidikan = findViewById(R.id.pendidikan);

        nik = findViewById(R.id.nik);
        nama_karyawan = findViewById(R.id.nama_karyawan);
        final RadioGroup jenis_kelamin = findViewById(R.id.rgroupjk);
        tgl_lahir = findViewById(R.id.tgl_lahir);
        alamat = findViewById(R.id.alamat);
        final RadioGroup status = findViewById(R.id.rgroupstatus);
        no_hp = findViewById(R.id.nohp);
        email = findViewById(R.id.email);
        pendidikan = findViewById(R.id.pendidikan);
        save = findViewById(R.id.btn_save);

        tempat_lahir.setItems("Jakarta","Bogor","Cianjur","Sukabumi","Bekasi","Garut","Depok","Tanggerag","Banten","Semarang","Surabaya","Malang","Yogyakarta");
        tempat_lahir.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                Snackbar.make(view, "Clicked " + item, Snackbar.LENGTH_SHORT).show();
            }
        });

        jabatan.setItems("Direktur","Manager","ADM","Sekretaris","Karyawan");
        jabatan.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                Snackbar.make(view, "Clicked " + item, Snackbar.LENGTH_SHORT).show();
            }
        });

        bagian.setItems("Keuangan","Personalia","Pemasar","IT","Design","Lapangan","Kebersihan","Dapur");
        bagian.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                Snackbar.make(view, "Clicked " + item, Snackbar.LENGTH_SHORT).show();
            }
        });

        pendidikan.setItems("SMA/SMK","D1/D2/D3","S1/S2/S3");
        pendidikan.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                Snackbar.make(view, "Clicked " + item, Snackbar.LENGTH_SHORT).show();
            }
        });

        pd = new ProgressDialog(this);

        final MaterialSpinner finalPendidikan = pendidikan;
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate_input())
                {
                    pd.setMessage("send data ...");
                    pd.setCancelable(false);
                    pd.show();

                    String snik = nik.getText().toString();
                    String snama = nama_karyawan.getText().toString();

                    int selectedjk = jenis_kelamin.getCheckedRadioButtonId();
                    radioSexButton = findViewById(selectedjk);
                    String sjk = radioSexButton.getText().toString();

                    String stempatlahir = tempat_lahir.getItems().toString();

                    String stanggallahir = tgl_lahir.getDate().toString();
                    String salamat = alamat.getText().toString();

                    String sjabatan = jabatan.getItems().toString();

                    String sbagian = bagian.getItems().toString();

                    int selectedst = status.getCheckedRadioButtonId();
                    RadioStatusButton = findViewById(selectedst);
                    String sstatus = RadioStatusButton.getText().toString();

                    String snohp = no_hp.getText().toString();
                    String semail = email.getText().toString();

                    String spendidikan = finalPendidikan.getItems().toString();

                    KaryawanApiRequest api = KaryawanRetroServer.getClient().create(KaryawanApiRequest.class);

                    Call<KaryawanResponseModel> sendbio = api.sendDataKaryawan(snik, snama, sjk, stempatlahir, stanggallahir, salamat
                            , sjabatan, sbagian, sstatus, snohp, semail, spendidikan);
                    sendbio.enqueue(new Callback<KaryawanResponseModel>() {
                        @Override
                        public void onResponse(Call<KaryawanResponseModel> call, Response<KaryawanResponseModel> response) {
                            pd.hide();
                            Log.d("RETRO", "response : " + response.body().toString());
                            String kode = response.body().getKode();

                            if(kode.equals("1"))
                            {
                                Toast.makeText(KaryawanInput.this, "Data Berhasil Disimpan", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(KaryawanInput.this, "Gagal Menyimpan Data", Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<KaryawanResponseModel> call, Throwable t) {
                            pd.hide();
                            Log.d("RETRO", "Falure : " + "Gagal Mengirim Request");
                        }
                    });
                }
            }
        });


    }

    public boolean validate_input(){
        return(!validate.cek(nik)&&!validate.cek(nama_karyawan)&&!validate.cek(alamat)&&!validate.cek(no_hp)&&!validate.cek(email)&&!validate.cektanggal(tgl_lahir)) ? true : false;
    }

}
