package com.example.karyawanapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import cn.pedant.SweetAlert.SweetAlertDialog;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.karyawanapp.SessionManager.SessionManager;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.share.widget.ShareDialog;
import com.google.android.material.navigation.NavigationView;

import java.io.InputStream;

public class Navigation extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Button btnKaryawan, logout;
    SessionManager sessionManager;
    private TextView tvName, tvEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(this);
        setContentView(R.layout.activity_navigation);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.syncState();
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        sessionManager = new SessionManager(this);

        tvName = findViewById(R.id.nav_header_name);
        tvEmail = findViewById(R.id.nav_header_email);

        //tvName.setText(sessionManager.getKeyNama());
        //tvEmail.setText(sessionManager.getKeyEmail());

        btnKaryawan = findViewById(R.id.btnKaryawan);


        btnKaryawan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Navigation.this, KaryawanTampilData.class);
                startActivity(intent);
            }
        });

        Bundle inBundle = getIntent().getExtras();
        //String name = inBundle.get("name").toString();
        //String surname = inBundle.get("surname").toString();
        //String imageUrl = inBundle.get("imageUrl").toString();

        //TextView nameView = findViewById(R.id.nav_header_name);
        //nameView.setText("" + name + " " + surname);

        //new DonwloadImage((ImageView)findViewById(R.id.nav_header_image)).execute(imageUrl);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if(id == R.id.nav_logout){
            new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                    .setTitleText("Keluar dari Karyawan App")
                    .setContentText("Apakah anda ingin keluar ?")
                    .setConfirmText("Ya, Keluar !")
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            LoginManager.getInstance().logOut();
                            Intent intent = new Intent(Navigation.this, LoginActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    })
                    .setCancelButton("Cancel", new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            sweetAlertDialog.dismissWithAnimation();
                        }
                    })
                    .show();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



    public class DonwloadImage extends AsyncTask<String, Void, Bitmap>{

        ImageView bmImage;

        public DonwloadImage(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        @Override
        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try{
                InputStream in = new java.net.URL(urldisplay).openStream();
            }catch (Exception e){
                Log.e("Error",e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            bmImage.setImageBitmap(bitmap);
        }
    }
}
