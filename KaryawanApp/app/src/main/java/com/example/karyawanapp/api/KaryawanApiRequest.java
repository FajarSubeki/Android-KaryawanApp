package com.example.karyawanapp.api;

import com.example.karyawanapp.model.KaryawanDataModel;
import com.example.karyawanapp.model.KaryawanResponseModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface KaryawanApiRequest {

    @FormUrlEncoded
    @POST("insert.php")
    Call<KaryawanResponseModel> sendDataKaryawan(@Field("nik") String nik,
                                                 @Field("nama_karyawan") String nama_karyawan,
                                                 @Field("jenis_kelamin") String jenis_kelamin,
                                                 @Field("tempat_lahir") String tempat_lahir,
                                                 @Field("tanggal_lahir") String tanggal_lahir,
                                                 @Field("alamat") String alamat,
                                                 @Field("jabatan") String jabatan,
                                                 @Field("bagian") String bagian,
                                                 @Field("status") String status,
                                                 @Field("no_hp") String no_hp,
                                                 @Field("email") String email,
                                                 @Field("pendidikan") String pendidikan);

    @FormUrlEncoded
    @POST("login.php")
    Call<KaryawanDataModel> authUser2(@Field("nik") String username,
                                         @Field("nik") String password);

    @GET("read.php")
    Call<KaryawanResponseModel> getDataKaryawan();

    @FormUrlEncoded
    @POST("update.php")
    Call<KaryawanResponseModel> updateDataKaryawan( @Field("id_karyawan") String id_karyawan,
                                            @Field("nik") String nik,
                                            @Field("nama_karyawan") String nama_karyawan,
                                            @Field("jenis_kelamin") String jenis_kelamin,
                                            @Field("tempat_lahir") String tempat_lahir,
                                            @Field("tanggal_lahir") String tanggal_lahir,
                                            @Field("alamat") String alamat,
                                            @Field("jabatan") String jabatan,
                                            @Field("bagian") String bagian,
                                            @Field("status") String status,
                                            @Field("no_hp") String no_hp,
                                            @Field("email") String email,
                                            @Field("pendidikan") String pendidikan);

    @FormUrlEncoded
    @POST("delete.php")
    Call<KaryawanResponseModel> deleteDataKaryawan(@Field("id_karyawan") String id_karyawan);

    @FormUrlEncoded
    @POST("search.php")
    Call<KaryawanResponseModel> search(@Field("search")String search);


}
