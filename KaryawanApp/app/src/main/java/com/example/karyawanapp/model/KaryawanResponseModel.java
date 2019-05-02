package com.example.karyawanapp.model;

import java.util.List;

public class KaryawanResponseModel {

    String kode, pesan;
    List<KaryawanDataModel> result;

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }

    public List<KaryawanDataModel> getResult() {
        return result;
    }

    public void setResult(List<KaryawanDataModel> result) {
        this.result = result;
    }
}
