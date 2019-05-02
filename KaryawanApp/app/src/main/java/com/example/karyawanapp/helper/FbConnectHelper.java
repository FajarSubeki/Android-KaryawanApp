package com.example.karyawanapp.helper;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;

import org.json.JSONObject;

import java.util.Arrays;
import java.util.Collection;

import androidx.fragment.app.Fragment;

public class FbConnectHelper {

    private Collection<String> permissions = Arrays.asList("public_profile ", "email", "user_birthday", "user_location");
    private CallbackManager callbackManager;
    private LoginManager loginManager;
    private ShareDialog shareDialog;
    private Activity activity;
    private Fragment fragment;
    private OnFbSignInListener fbSignInListener;

    public interface OnFbSignInListener {
        void onViewCreated(View view, Bundle savedInstanceState);

        void OnFbSuccess(GraphResponse graphResponse);

        void OnFbError(String errorMessage);
    }

    public FbConnectHelper(Activity activity, OnFbSignInListener fbSignInListener) {
        this.activity = activity;
        this.fbSignInListener = fbSignInListener;
    }

    public FbConnectHelper(Fragment fragment, OnFbSignInListener fbSignInListener) {
        this.fragment = fragment;
        this.fbSignInListener = fbSignInListener;
    }

    public FbConnectHelper(Activity activity) {
        shareDialog = new ShareDialog(activity);
    }

    public FbConnectHelper(Fragment fragment) {
        shareDialog = new ShareDialog(fragment);
    }

    public void connect(){
        callbackManager = CallbackManager.Factory.create();
        loginManager = LoginManager.getInstance();
        if(activity != null)
            loginManager.logInWithReadPermissions(activity, permissions);
        else
            loginManager.logInWithReadPermissions(fragment, permissions);
        loginManager.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                if (loginResult != null){

                }
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });
    }

    private void callGraphAPI(AccessToken accessToken){
        GraphRequest request = GraphRequest.newMeRequest(
                accessToken,
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        fbSignInListener.OnFbSuccess(response);
                    }
                });
        Bundle parameters = new Bundle();

        parameters.putString("fields", "id,birthday,email,first_name,gender,last_name,link,location,name");
        request.setParameters(parameters);
        request.executeAsync();
    }

    public void shareOnFBWall(String title, String description, String url) {
        if (ShareDialog.canShow(ShareLinkContent.class)) {
            ShareLinkContent linkContent = new ShareLinkContent.Builder()
                    .setContentTitle(title)
                    .setContentDescription(description)
                    .setContentUrl(Uri.parse(url))
                    .build();
            shareDialog.show(linkContent);
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (callbackManager != null)
            callbackManager.onActivityResult(requestCode, resultCode, data);
    }

}
