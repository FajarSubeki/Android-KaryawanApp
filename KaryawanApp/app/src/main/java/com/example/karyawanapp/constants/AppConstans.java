package com.example.karyawanapp.constants;

public class AppConstans {

    public enum SharedPreferenceKeys {
        USER_NAME("userName"),
        USER_EMAIL("userEmail"),
        USER_IMAGE_URL("userImageUrl");


        private String value;

        SharedPreferenceKeys(String value) {
            this.value = value;
        }

        public String getKey() {
            return value;
        }
    }

    public static final String GOOGLE_CLIENT_ID = "35314520208-t8lujtn7410bpsi7el59hqi45uiqprcj.apps.googleusercontent.com";

}
