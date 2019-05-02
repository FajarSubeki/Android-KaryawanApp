package com.example.karyawanapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.example.karyawanapp.DetailKaryawan;
import com.example.karyawanapp.KaryawanInput;
import com.example.karyawanapp.MainActivity;
import com.example.karyawanapp.R;
import com.example.karyawanapp.model.KaryawanDataModel;

import java.util.List;
import java.util.Random;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class KaryawanAdapter extends RecyclerView.Adapter<KaryawanAdapter.HolderData> {

    private List<KaryawanDataModel> mList;
    private Context ctx;

    public String[] mColors = {
            "#39add1", // light blue
            "#3079ab", // dark blue
            "#c25975", // mauve
            "#e15258", // red
            "#f9845b", // orange
            "#838cc7", // lavender
            "#7d669e", // purple
            "#53bbb4", // aqua
            "#51b46d", // green
            "#e0ab18", // mustard
            "#637a91", // dark gray
            "#f092b0", // pink
            "#b7c0c7"  // light gray
    };

    public KaryawanAdapter(Context ctx, List<KaryawanDataModel> mList)
    {
        this.ctx = ctx;
        this.mList = mList;
    }


    @NonNull
    @Override
    public KaryawanAdapter.HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.layoutlistkaryawan, parent, false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull KaryawanAdapter.HolderData holder, int position) {
        KaryawanDataModel dm = mList.get(position);
        holder.nik.setText(dm.getNik());
        holder.nama.setText(dm.getNama_karyawan());
        holder.jabatan.setText(dm.getJabatan());

        String nama_karyawan = dm.getNama_karyawan();
        String firstCharNamaKaryawan = nama_karyawan.substring(0,1);
        TextDrawable drawable = TextDrawable.builder()
                .buildRound(firstCharNamaKaryawan, getColor());

        holder.foto_karyawan.setImageDrawable(drawable);

        holder.dm = dm;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class HolderData extends RecyclerView.ViewHolder{

        TextView nama, jabatan, nik;
        ImageView foto_karyawan;
        KaryawanDataModel dm;

        public HolderData(@NonNull View v) {
            super(v);
            nama = v.findViewById(R.id.nama_karyawan);
            jabatan = v.findViewById(R.id.jabatan);
            nik = v.findViewById(R.id.nik);
            foto_karyawan = v.findViewById(R.id.foto_karaywan);

            v.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Intent goInput = new Intent(ctx, DetailKaryawan.class);
                    goInput.putExtra("id_karyawan", dm.getId_karyawan());
                    goInput.putExtra("nik", dm.getNik());
                    goInput.putExtra("nama_karyawan", dm.getNama_karyawan());
                    goInput.putExtra("jenis_kelamin", dm.getJenis_kelamin());
                    goInput.putExtra("tempat_lahir", dm.getTempat_lahir());
                    goInput.putExtra("tanggal_lahir", dm.getTanggal_lahir());
                    goInput.putExtra("alamat", dm.getAlamat());
                    goInput.putExtra("jabatan", dm.getJabatan());
                    goInput.putExtra("bagian", dm.getBagian());
                    goInput.putExtra("status", dm.getStatus());
                    goInput.putExtra("no_hp", dm.getNo_hp());
                    goInput.putExtra("email", dm.getEmail());
                    goInput.putExtra("pendidikan", dm.getPendidikan());
                    goInput.putExtra("tgl_masuk", dm.getTgl_masuk());
                    goInput.putExtra("foto_karyawan", dm.getFoto_karyawan());
                    ctx.startActivity(goInput);
                }
            });
        }
    }

    public int getColor(){
        String color;

        Random randomGenerator = new Random();
        int randomNumber = randomGenerator.nextInt(mColors.length);

        color = mColors[randomNumber];
        int colorAsInt = Color.parseColor(color);

        return colorAsInt;
    }

}
