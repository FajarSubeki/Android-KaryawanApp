package com.example.karyawanapp.utils;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.example.karyawanapp.helper.DatePickerView;

public class validate {

    public static boolean cek(EditText et){
        View focusView = null;
        Boolean cancel = false;
        if(TextUtils.isEmpty(et.getText().toString().trim())){
            et.setError("Inputan Harus Diisi");
            focusView = et;
            cancel = true;
        }
        if (cancel){
            focusView.requestFocus();
        }
        return cancel;
    }

    public static boolean cektanggal(DatePickerView et) {
        View focusView = null;
        Boolean cancel=false;
        if (TextUtils.isEmpty(et.getText().toString().trim())) {
            et.setError("Inputan Harus Di Isi");
            focusView = et;
            cancel = true;
        }
        if (cancel) {
            focusView.requestFocus();
        }
        return cancel;
    }

}
