package com.example.karyawanapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.karyawanapp.SessionManager.SessionManager;
import com.example.karyawanapp.api.KaryawanApiRequest;
import com.example.karyawanapp.api.KaryawanRetroServer;
import com.example.karyawanapp.model.KaryawanDataModel;
import com.example.karyawanapp.model.KaryawanResponseModel;
import com.example.karyawanapp.utils.move;

import androidx.appcompat.app.AppCompatActivity;
import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class LoginActivity extends AppCompatActivity{

    ProgressBar progressBar;
    LinearLayout view;

    private EditText tvUsername, tvPassword;
    String us, ps;
    Button show, btnlogin;
    private Context mContext;
    private ProgressDialog pDialog;
    private final String USERNAME="username";
    MediaPlayer mp, mp2;
    SessionManager sessionManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initUi();

        show = findViewById(R.id.show);
        show.setOnClickListener(new showOrHidePassword());
        btnlogin = findViewById(R.id.btn_login);

        tvUsername = findViewById(R.id.et_username);
        tvPassword= findViewById(R.id.et_password);

        pDialog = new ProgressDialog(this, R.style.ProgressBar);

        mp = MediaPlayer.create(this, R.raw.success);
        mp.setLooping(false);

        mp2 = MediaPlayer.create(this, R.raw.error);
        mp2.setLooping(false);
        mContext = this;
        progressBar = findViewById(R.id.progress_bar);
        view = findViewById(R.id.linearLayout);
        sessionManager = new SessionManager(this);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username, password;
                username = tvUsername.getText().toString();
                password = tvPassword.getText().toString();

                pDialog.setTitle("Mengautenfikasi");
                pDialog.setMessage("Please Wait");
                pDialog.show();
                KaryawanApiRequest apiRequest = KaryawanRetroServer.getClient().create(KaryawanApiRequest.class);
                retrofit2.Call<KaryawanDataModel> authUser = apiRequest.authUser2(username, password);
                authUser.enqueue(new Callback<KaryawanDataModel>() {
                    @Override
                    public void onResponse(Call<KaryawanDataModel> call, Response<KaryawanDataModel> response) {
                        if (response.body().getPesan() == null)
                        {
                            pDialog.hide();
                            mp2.start();
                            final SweetAlertDialog pDialog1 = new SweetAlertDialog(LoginActivity.this, SweetAlertDialog.ERROR_TYPE);
                            pDialog1.setTitleText("Informasi");
                            pDialog1.setContentText("Username atau Password Salah");
                            pDialog1.setCancelable(false);
                            pDialog1.setConfirmText("OK");
                            pDialog1.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                    pDialog1.hide();
                                }
                            });
                            pDialog1.show();
                        }else {
                            pDialog.dismiss();
                            mp.start();
                            final SweetAlertDialog pDialog = new SweetAlertDialog(LoginActivity.this, SweetAlertDialog.SUCCESS_TYPE);
                            pDialog.setTitleText("Informasi");
                            pDialog.setContentText("Login Berhasil!");
                            pDialog.setCancelable(false);
                            pDialog.setConfirmText("OK");
                            pDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                    Intent intent = new Intent(LoginActivity.this, Navigation.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    pDialog.hide();
                                    finish();
                                    startActivity(intent);
                                    mp.stop();

                                }
                            });
                            pDialog.show();
                            sessionManager.setKeyId(response.body().getId_karyawan());
                            sessionManager.setKeyNik(response.body().getNik());
                            sessionManager.setKeyNama(response.body().getNama_karyawan());
                            sessionManager.setIsLogin(true);
                            sessionManager.setImageKey(response.body().getFoto_karyawan());
                            sessionManager.setKeyNohp(response.body().getNo_hp());
                        }
                    }

                    @Override
                    public void onFailure(Call<KaryawanDataModel> call, Throwable t) {
                        pDialog.hide();
                        mp2.start();
                        Toast.makeText(LoginActivity.this,t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }

    public void initUi(){
        TextView logofont = findViewById(R.id.logofont);
        Typeface custom_fonts = Typeface.createFromAsset(getAssets(), "fonts/ArgonPERSONAL-Regular.otf");
        logofont.setTypeface(custom_fonts);
    }

    public void validasiLogin(View view){
        us = tvUsername.getText().toString();
        ps = tvPassword.getText().toString();

        if(isOnline()){
            //login(us, ps);
        }else{
            mp2.start();
            new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("Opps...")
                    .setContentText("No Internet Connection")
                    .show();
        }
    }


    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Tekan sekali lagi untuk keluar", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 4000);

    }

    class showOrHidePassword implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            if(show.getText().toString() == "SHOW"){
                tvPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                show.setText("HIDE");
            }else{
                tvPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                show.setText("SHOW");
            }
        }
    }

    private void showDialog(){
        if(!pDialog.isShowing()){
            pDialog.show();
        }
    }

    private void hideDialog(){
        if(pDialog.isShowing())
            pDialog.dismiss();
    }

    protected boolean isOnline(){

        ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo netInfo  = cm.getActiveNetworkInfo();

        if(netInfo != null && netInfo.isConnectedOrConnecting()){
            return true;
        }else{
            return false;
        }

    }


}
