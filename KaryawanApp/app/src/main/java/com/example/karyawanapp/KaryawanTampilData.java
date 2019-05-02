package com.example.karyawanapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.karyawanapp.adapter.KaryawanAdapter;
import com.example.karyawanapp.api.KaryawanApiRequest;
import com.example.karyawanapp.api.KaryawanRetroServer;
import com.example.karyawanapp.model.KaryawanDataModel;
import com.example.karyawanapp.model.KaryawanResponseModel;

import java.util.ArrayList;
import java.util.List;

public class KaryawanTampilData extends AppCompatActivity implements SearchView.OnQueryTextListener{

    private RecyclerView mRecyler;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mManager;
    private List<KaryawanDataModel> mItems = new ArrayList<>();
    ProgressDialog pd;

    ProgressBar progressBar;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_karyawan_tampil_data);

        pd = new ProgressDialog(this);
        mRecyler = findViewById(R.id.recyclerTemp);
        mManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyler.setLayoutManager(mManager);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Karyawan");

        pd.setMessage("Loading");
        pd.setCancelable(false);
        pd.show();
        loadData();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        loadData();
    }

    private void loadData(){
        KaryawanApiRequest api = KaryawanRetroServer.getClient().create(KaryawanApiRequest.class);
        Call<KaryawanResponseModel> getdata = api.getDataKaryawan();
        getdata.enqueue(new Callback<KaryawanResponseModel>() {
            @Override
            public void onResponse(Call<KaryawanResponseModel> call, Response<KaryawanResponseModel> response) {
                pd.hide();
                Log.d("RETRO", "RESPONSE : " + response.body().getKode());
                mItems = response.body().getResult();

                mAdapter = new KaryawanAdapter(KaryawanTampilData.this, mItems);
                mRecyler.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<KaryawanResponseModel> call, Throwable t) {
                pd.hide();
                Log.d("RETRO", "FAILED : response gagal");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        final MenuItem item = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setQueryHint("Cari Karyawan");
        searchView.setIconified(false);
        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        mRecyler.setVisibility(View.GONE);
        KaryawanApiRequest api = KaryawanRetroServer.getClient().create(KaryawanApiRequest.class);
        Call<KaryawanResponseModel> getdata = api.search(newText);

        getdata.enqueue(new Callback<KaryawanResponseModel>() {
            @Override
            public void onResponse(Call<KaryawanResponseModel> call, Response<KaryawanResponseModel> response) {
                pd.hide();
                Log.d("RETRO", "RESPONSE : " + response.body().getKode());
                mItems = response.body().getResult();

                mRecyler.setVisibility(View.VISIBLE);

                mAdapter = new KaryawanAdapter(KaryawanTampilData.this, mItems);
                mRecyler.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<KaryawanResponseModel> call, Throwable t) {
                pd.hide();
                Log.d("RETRO", "FAILED : response gagal");
            }
        });

        return true;
    }

    private void whiteNotificationBar(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int flags = view.getSystemUiVisibility();
            flags |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            view.setSystemUiVisibility(flags);
            getWindow().setStatusBarColor(Color.WHITE);
        }
    }
}
